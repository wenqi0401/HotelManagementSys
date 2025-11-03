
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RoomManager {
    public static List<Room> rooms = new ArrayList<>();
    Scanner input = new Scanner(System.in);

    public void autoAssignHousekeeper() {
        for (Room assignRoom : rooms) {
            if (!assignRoom.getisIsAvailble()) {
                boolean housekeeperAssigned = false;
                for (Person p : PersonManager.getPersonArr()) {
                    if (p instanceof Housekeeper) {
                        Housekeeper housekeeper = (Housekeeper) p;
                        if (housekeeper.isAssigned()) {
                            housekeeper.setAssigned(false);
                            assignRoom.setAssignedHousekeeper(housekeeper);
                            housekeeperAssigned = true;
                            break;
                        }
                    }
                }
                if (!housekeeperAssigned) {

                    System.out.println(" \nNo available housekeeper for Room " + assignRoom.getRoomId());
                }
            }
        }

    }

    public void displayOccupiedRoomsWithHousekeepers() {
        System.out.println("\n===== Occupied Rooms and Housekeepers =====");
        boolean found = false;
        for (Room r : rooms) {
            if (!r.getisIsAvailble()) {
                found = true;
                System.out.print("Room " + r.getRoomId() + " | Status: Occupied");
                if (r.getAssignedHousekeeper() != null) {
                    System.out.print(" | Housekeeper: " + r.getAssignedHousekeeper().getName());
                } else {
                    System.out.print(" | Housekeeper: Not assigned yet");
                }
                System.out.println();
            }
        }
        if (!found) {
            System.out.println(" No occupied rooms at the moment.");
        }
    }

    // Create
    public void addRoom(Room r) {
        rooms.add(r);
        System.out.println(HotelApplication.GREEN + "Room added successfully." + HotelApplication.RESET);
    }

    // Delete
    public void deleteRoom(int roomId) {

        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomId() == roomId) {
                rooms.remove(i);
                System.out.println(HotelApplication.GREEN + "Room deleted successfully." + HotelApplication.RESET);
                HotelApplication.clearScreen(input);
                return;
            }
        }
        System.out.println(HotelApplication.RED + "Room ID not found." + HotelApplication.RESET);
        HotelApplication.clearScreen(input);
    }

    // Update
    public void modifyRoom(int roomId, double newPrice, String newType, boolean newAvailability) {
        for (Room r : rooms) {
            if (r.getRoomId() == roomId) {
                r.setPricePerNight(newPrice);
                r.setRoomType(newType);
                r.setIsAvailble(newAvailability);
                System.out.println(HotelApplication.GREEN + "Room modified successfully." + HotelApplication.RESET);
                return;
            }
        }
        System.out.println(HotelApplication.RED + "Room ID not found." + HotelApplication.RESET);
    }

    // Read
    public static void displayRooms() {
        System.out.printf("%-10s %-20s %-10s %-10s\n", "Room ID", "Price per Night", "Type", "Availability");
        for (Room r : rooms) {
            System.out.println(r);
        }
    }

    public static boolean inputValidation(int input) {

        if (input > 0) {
            return true;
        } else {
            System.out.println(HotelApplication.RED + " Please enter positive number." + HotelApplication.RESET);
            return false;
        }
    }

    // Checks if a room ID already exists in the room collection

    public static boolean isRoomIdUnique(int roomId) {
        for (Room existingRoom : RoomManager.rooms) {
            if (existingRoom.getRoomId() == roomId) {
                return false; // Room ID already exists
            }
        }
        return true; // Room ID is unique
    }

    // Prompts for a valid room number and performs basic validation

    public static int promptValidRoomNumber(Scanner input, String promptMessage, String invalidMessage) {
        while (true) {
            System.out.print(promptMessage);
            try {
                String inputStr = input.nextLine().trim();
                int roomNumber = Integer.parseInt(inputStr);

                if (inputValidation(roomNumber)) {
                    return roomNumber;
                }
            } catch (NumberFormatException e) {
                System.out.println(HotelApplication.RED + invalidMessage + HotelApplication.RESET);
            } catch (Exception e) {
                System.out.println(
                        HotelApplication.RED + "An error occurred: " + e.getMessage() + HotelApplication.RESET);
            }
        }
    }

    public double promptValidDouble(Scanner input, String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage);
                String inputStr = input.nextLine().trim();
                double value = Double.parseDouble(inputStr);

                if (inputValidation((int) value)) {
                    return value;
                } else {
                    throw new IllegalArgumentException(
                            HotelApplication.RED + "Invalid value" + HotelApplication.RESET);
                }
            } catch (NumberFormatException e) {
                System.out.println(HotelApplication.RED + " Not a valid decimal number." + HotelApplication.RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(HotelApplication.RED + e.getMessage() + HotelApplication.RESET);
            } catch (Exception e) {
                System.out.println(
                        HotelApplication.RED + " An error occurred: " + e.getMessage() + HotelApplication.RESET);
            }
        }
    }

    public String promptRoomType(Scanner input) {
        while (true) {
            System.out.print("Enter Room Type (1 SINGLE / 2 DOUBLE / 3 SUITE): ");
            int typenum = input.nextInt();
            input.nextLine(); // consume newline

            switch (typenum) {
                case 1:
                    return Room.SINGLE;
                case 2:
                    return Room.DOUBLE;
                case 3:
                    return Room.SUITE;
                default:
                    System.out.println(HotelApplication.RED + " Invalid room type number. Please enter 1, 2, or 3."
                            + HotelApplication.RESET);
            }
        }
    }

    public boolean promptBoolean(Scanner input, String promptMessage) {
        while (true) {
            try {
                System.out.print(promptMessage + " (true/false): ");
                String value = input.nextLine().trim().toLowerCase();
                if (value.equals("true"))
                    return true;
                else if (value.equals("false"))
                    return false;
                else
                    throw new IllegalArgumentException(
                            HotelApplication.RED + "Invalid boolean input" + HotelApplication.RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(HotelApplication.RED + " Please enter 'true' or 'false'." + HotelApplication.RESET);
            } catch (Exception e) {
                System.out.println(
                        HotelApplication.RED + " An error occurred: " + e.getMessage() + HotelApplication.RESET);
                input.nextLine(); // Clear the scanner buffer
            }
        }
    }

    // main menu for room
    public static void roomMenu() {
        Scanner input = new Scanner(System.in);
        RoomManager manager = new RoomManager();// obj to manage
        while (true) {
            HotelApplication.clearScreen(input);
            HotelApplication.crud("Room");

            System.out.println("5. View Assigned Housekeeper");

            System.out.print("Choose an option: ");

            int choice = Validator.getUserChoice(input);

            switch (choice) {
                case 1:
                    System.out.println("\nAdding new Room....");
                    int id = promptValidRoomNumber(input, "Enter Room ID: ", "Not a valid integer.");
                    if (!isRoomIdUnique(id)) {
                        System.out.println(HotelApplication.RED + "Room ID " + id
                                + " already exists. Please use a unique room ID." + HotelApplication.RESET);
                        id = promptValidRoomNumber(input, "Enter Room ID: ", "Not a valid integer.");
                    }
                    double price = manager.promptValidDouble(input, "Enter Price per Night: ");
                    String type = manager.promptRoomType(input);

                    Room newRoom = new Room(id, price, type.toUpperCase());
                    manager.addRoom(newRoom);
                    break;

                case 4:
                    displayRooms();
                    System.out.println("\nDeleting a Room....");
                    while (true) {
                        System.out.print("Enter Room ID to delete: ");

                        int deleteId = input.nextInt();
                        if (inputValidation(deleteId)) {
                            manager.deleteRoom(deleteId);
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("\nModifing a Room....");
                    int modifyId = promptValidRoomNumber(input, "Enter Room ID to modify: ",
                            "Not a valid integer.");
                    double newPrice = manager.promptValidDouble(input, "Enter new Price: ");
                    String newType = manager.promptRoomType(input);
                    boolean newAvailable = manager.promptBoolean(input, "Is the room available?");

                    manager.modifyRoom(modifyId, newPrice, newType.toUpperCase(), newAvailable);
                    break;

                case 2:
                    displayRooms();
                    break;

                case 5:
                    manager.autoAssignHousekeeper();

                    manager.displayOccupiedRoomsWithHousekeepers();
                    break;

                case 0:
                System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

}
