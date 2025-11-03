import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationManager {
    private static List<Reservation> reservations = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    // Find room by room number
    public static Room findRoomByNumber(int roomId) {
        List<Room> rooms = RoomManager.rooms;
        for (Room r : rooms) {
            if (r.getRoomId() == roomId) {
                return r;
            }
        }
        return null;

    }

    // Get the available room from Room
    public static List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room r : RoomManager.rooms) {
            if (r.getisIsAvailble()) {
                availableRooms.add(r);
            }
        }
        return availableRooms;
    }

    public static Reservation reserveRoom(String guestName, String contactNumber,
            int roomId, LocalDate checkInDate, LocalDate checkOutDate) {
        Room selectedRoom = null;
        for (Room r : RoomManager.rooms) {
            if (roomId == r.getRoomId()) {
                selectedRoom = r;
                break;
            }
        }
        Reservation reservation = new Reservation(guestName, contactNumber,
                selectedRoom.getRoomType(), selectedRoom.getRoomId(), checkInDate, checkOutDate);
        reservations.add(reservation);
        selectedRoom.setIsAvailble(false);

        return reservation;
    }

    private static void createOrUpdateCustomerRecord(String guestName, String contactNumber, String ic, String id) {
        Person[] people = PersonManager.getPersonArr();

        // Try to update existing customer
        for (int i = 0; i < people.length; i++) {
            Person p = people[i];

            if (p instanceof Customer) {
                Customer customer = (Customer) p;

                if (customer.getIc().equalsIgnoreCase(ic)) {
                    customer.setEmail("---");
                    customer.setName(guestName);
                    customer.setPhoneNumber(contactNumber);
                    customer.setCustomerId(id);
                    System.out.println(HotelApplication.GREEN + "Customer record updated." + HotelApplication.RESET);
                    return;
                }
            }
        }

        // Else, create new
        int nextIndex = -1;
        for (int i = 0; i < people.length; i++) {
            if (people[i] == null) {
                nextIndex = i;
                break;
            }
        }

        if (nextIndex == -1) {
            System.out.println(
                    HotelApplication.RED + "No space available to add new customers." + HotelApplication.RESET);
            return;
        }

        people[nextIndex] = new Customer(ic, guestName, "--", contactNumber, id);
        System.out.println(HotelApplication.GREEN + "New customer record created." + HotelApplication.RESET);
    }

    // Find the reservation by Reservation ID
    public static Reservation findReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getReservationId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }

    public static boolean updateReservation(int reservationId, String guestName,
            String contactNumber, LocalDate checkInDate, LocalDate checkOutDate) {
        Reservation reservation = findReservationById(reservationId);

        if (reservation == null) {
            System.out.println("Reservation not found!");
            return false;
        }
        reservation.setGuestName(guestName);

        reservation.setContactNumber(contactNumber);

        reservation.setCheckInDate(checkInDate);

        reservation.setCheckOutDate(checkOutDate);

        return true;
    }

    public static boolean deleteReservation(int reservationId) {
        Reservation reservation = findReservationById(reservationId);

        if (reservation == null) {
            System.out.println("Reservation not found!");
            return false;
        }

        Room room = findRoomByNumber(reservation.getRoomId());
        if (room != null) {
            room.setIsAvailble(true);
        }

        return reservations.remove(reservation);
    }

    public static void ReservationMenu() {
        int choice;

        do {
            HotelApplication.clearScreen(input);
            System.out.println("\t =======================================");
            System.out.println("\t|       Hotel Reservation System        | ");
            System.out.println("\t =======================================");
            System.out.println("\n");
            HotelApplication.crud("Reservation");

            try {
                System.out.print("Enter your choice: ");
                choice = Validator.getUserChoice(input);
                switch (choice) {
                    case 1:
                        roomReservation();
                        break;

                    case 2:
                        viewReservations();
                        break;

                    case 3:
                        updateReservation();
                        break;

                    case 4:
                        deleteReservation();
                        break;

                    case 0:
                        System.out.println("Returning to Main Menu...");
                        return;

                    default:
                        System.out.println(
                                HotelApplication.RED + "Invalid choice! Please try again." + HotelApplication.RESET);
                        HotelApplication.clearScreen(input);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                choice = -1;
            }
        } while (choice != 0);
    }

    public static void roomReservation() {
        System.out.println("\t ===============================");
        System.out.println("\t|       Room Reservation        | ");
        System.out.println("\t ===============================");

        System.out.println("\nAvailable Rooms");

        // Get the available room
        List<Room> availableRooms = getAvailableRooms();

        if (availableRooms.isEmpty()) {
            System.out.println(HotelApplication.RED + "No rooms available." + HotelApplication.RESET);
            return;
        }

        System.out.println("--------------------------------------------");
        System.out.printf("%-15s %-15s %-15s\n", "Room Type", "Room Number", "Room Price");
        System.out.println("--------------------------------------------");

        for (Room r : availableRooms) {
            System.out.printf("%-15s %-15d %-15.2f\n", r.getRoomType(), r.getRoomId(), r.getPricePerNight());
        }

        System.out.println("-------------------------------------------");

        // Continue booking

        int roomNumber;

        // prompt room id
        try {
            roomNumber = RoomManager.promptValidRoomNumber(input, "Enter room number: ",
                    "Invalid room number! Reservation cancelled.");
        } catch (NumberFormatException e) {
            return;
        }

        Room selectedRoom = findRoomByNumber(roomNumber);
        if (selectedRoom == null) {
            System.out
                    .println(HotelApplication.RED + "Room not found! Reservation cancelled." + HotelApplication.RESET);
            HotelApplication.clearScreen(input);
            return;
        }

        if (!selectedRoom.getisIsAvailble()) {
            System.out.println(
                    HotelApplication.RED + "Room is not available! Reservation cancelled." + HotelApplication.RESET);
            return;
        }

        String guestName = Validator.getValidatedName(input);
        String ic = Validator.getValidatedIc(input, "");
        String customerId = Validator.getValidId("Enter Customer ID: ", 'C', input,"");
        String contactNumber = Validator.getValidatedPhone(input);

        // Get dates and calculate duration in one step
        LocalDate checkInDate = null;
        LocalDate checkOutDate = null;
        int duration = 0;

        boolean isValid = false;
        while (!isValid) {
            checkInDate = Validator.getValidDate(input, "Enter check-in date (yyyy-MM-dd): ");
            checkOutDate = Validator.getValidDate(input, "Enter check-out date (yyyy-MM-dd): ");

            duration = (int) checkInDate.until(checkOutDate).getDays();

            if (duration > 0) {
                isValid = true;
            } else {
                System.out.println(HotelApplication.RED
                        + "Check-out date must be after check-in date. Please try again." + HotelApplication.RESET);
            }
        }
        createOrUpdateCustomerRecord(guestName, contactNumber, ic, customerId);
        Reservation reservation = reserveRoom(guestName, contactNumber, roomNumber, checkInDate, checkOutDate);

        if (reservation != null) {
            System.out.println("\nReservation successful!");
            System.out.println(reservation);
            System.out.println("Stay Duration: " + duration + " days");
            HotelApplication.clearScreen(input);
        } else {
            System.out.println("\nReservation failed!");
        }
    }

    // View reservation
    public static void viewReservations() {
        HotelApplication.clearScreen(input);
        System.out.println("\n===== All Reservations =====");

        if (reservations.isEmpty()) {
            System.out.println("No reservations found.");
            return;
        }

        for (Reservation reservation : reservations) {
            System.out.println("\n" + reservation);
        }
    }

    // Update the reservation
    public static void updateReservation() {
        System.out.println("\n===== Update Reservation =====");

        System.out.print("Enter reservation ID: ");
        int reservationId;
        try {
            reservationId = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(HotelApplication.RED + "Invalid reservation ID!" + HotelApplication.RESET);
            HotelApplication.clearScreen(input);
            return;
        }

        Reservation reservation = findReservationById(reservationId);

        if (reservation == null) {
            System.out.println(HotelApplication.RED + "Reservation not found!" + HotelApplication.RESET);
            HotelApplication.clearScreen(input);
            return;
        }

        System.out.println("\nCurrent Reservation Details:");
        System.out.println(reservation);

        // Find associated customer by guest name
        Customer foundCustomer = null;
        for (Person p : PersonManager.getPersonArr()) {
            if (p instanceof Customer) {
                Customer c = (Customer) p;
                if (c.getName().equalsIgnoreCase(reservation.getGuestName())) {
                    foundCustomer = c;
                    break;
                }
            }
        }

        if (foundCustomer == null) {
            System.out.println(HotelApplication.RED + "Customer not found!" + HotelApplication.RESET);
            return;
        }

        // -------- Update Customer Info --------
        // Guest Name
        System.out.print("Enter guest name [" + foundCustomer.getName() + "]: ");
        String guestNameInput = input.nextLine().trim();
        if (!guestNameInput.isEmpty()) {
            foundCustomer.setName(guestNameInput);
        }

        // IC
        String newIc = "";
        while (true) {
            System.out.print("Enter IC [" + foundCustomer.getIc() + "]: ");
            String icInput = input.nextLine().trim();
            if (icInput.isEmpty()) {
                newIc = foundCustomer.getIc();
                break;
            }

            if (icInput.length() != 14) {
                System.out.println(HotelApplication.RED + "IC must be exactly 14 characters." + HotelApplication.RESET);
                continue;
            }

            if (PersonManager.isDuplicateIc(icInput, foundCustomer.getIc())) {
                System.out.println(
                        HotelApplication.RED + "This IC is used by another customer!" + HotelApplication.RESET);
                continue;
            }

            newIc = icInput;
            break;
        }
        foundCustomer.setIc(newIc);
        foundCustomer.setEmail("---");

        // Customer ID
        String newCustomerId = "";
        while (true) {
            System.out.print("Enter Customer ID [" + foundCustomer.getCustomerId() + "]: ");
            String idInput = input.nextLine().trim().toUpperCase();

            if (idInput.isEmpty()) {
                newCustomerId = foundCustomer.getCustomerId();
                break;
            }

            if (idInput.length() != 4 || idInput.charAt(0) != 'C' ||
                    !Character.isDigit(idInput.charAt(1)) ||
                    !Character.isDigit(idInput.charAt(2)) ||
                    !Character.isDigit(idInput.charAt(3))) {
                System.out
                        .println(HotelApplication.RED + "Invalid format. Must be like C001." + HotelApplication.RESET);
                continue;
            }

            if (PersonManager.isDuplicateId(idInput, foundCustomer.getIc())) {
                System.out.println(HotelApplication.RED + "This Customer ID is already used." + HotelApplication.RESET);
                continue;
            }

            newCustomerId = idInput;
            break;
        }
        foundCustomer.setCustomerId(newCustomerId);

        // Contact number
        System.out.print("Enter contact number [" + foundCustomer.getPhoneNumber() + "]: ");
        String phoneInput = input.nextLine().trim();
        if (!phoneInput.isEmpty()) {
            foundCustomer.setPhoneNumber(phoneInput);
        }

        // -------- Update Reservation Dates --------
        LocalDate checkInDate, checkOutDate;
        int duration;

        while (true) {
            checkInDate = Validator.getValidDate(input, "Enter check-in date (yyyy-MM-dd): ");
            checkOutDate = Validator.getValidDate(input, "Enter check-out date (yyyy-MM-dd): ");
            duration = (int) checkInDate.until(checkOutDate).getDays();

            if (duration > 0)
                break;

            System.out.println(HotelApplication.RED + "Check-out must be after check-in!" + HotelApplication.RESET);
        }

        // -------- Update Reservation --------
        boolean updated = updateReservation(reservationId, foundCustomer.getName(),
                foundCustomer.getPhoneNumber(), checkInDate, checkOutDate);

        if (updated) {
            System.out.println("\nReservation updated successfully!");
            System.out.println(findReservationById(reservationId));
            System.out.println("Stay Duration: " + duration + " days");
        } else {
            System.out.println("\nReservation update failed!");
        }

        HotelApplication.clearScreen(input);
    }

    // Delete a reservation
    public static void deleteReservation() {

        System.out.println("\n==== Delete Reservation ====");

        System.out.print("Enter reservation ID: ");
        int reservationId;
        try {
            reservationId = Integer.parseInt(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println(HotelApplication.RED + "Invalid reservation ID!" + HotelApplication.RESET);
            return;
        }

        Reservation reservation = findReservationById(reservationId);

        if (reservation == null) {
            System.out.println(HotelApplication.RED + "Reservation not found!" + HotelApplication.RESET);
            HotelApplication.clearScreen(input);
            return;
        }

        System.out.println("\nReservation Details:");
        System.out.println(reservation);

        System.out.print("\nAre you sure you want to delete this reservation? (y/n): ");
        String confirmation = input.nextLine();

        if (confirmation.equalsIgnoreCase("y")) {
            boolean deleted = deleteReservation(reservationId);

            if (deleted) {
                System.out
                        .println(HotelApplication.GREEN + "Reservation deleted successfully!" + HotelApplication.RESET);
            } else {
                System.out.println(HotelApplication.RED + "Reservation deletion failed!" + HotelApplication.RESET);
            }
        } else {
            System.out.println(HotelApplication.RED + "Reservation deletion cancelled!" + HotelApplication.RESET);
        }
    }

}