
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelApplication {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";

    public static void mainMethod() {

        // Initialize components
        Scanner input = new Scanner(System.in);

        // Initialize with sample data
        initializeData();
        int choice;
        do {
            HotelApplication.clearScreen(input);
            System.out.println("\n===== Hotel Management System =====");
            System.out.println("1. Room Management");
            System.out.println("2. Reservation Management");
            System.out.println("3. Person Management");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Validator.getUserChoice(input);

            try {

                switch (choice) {
                    case 1:

                        RoomManager.roomMenu();
                        break;
                    case 2:
                        ReservationManager.ReservationMenu();
                        break;
                    case 3:
                        PersonManager.personMenu();
                        break;
                    case 0:
                        System.out.println(GREEN+"Thank you for using Hotel Management System!"+RESET);
                        break;
                    default:
                        System.out.println(RED + "Invalid choice! Please try again." + RESET);
                        clearScreen(input);
                }
            } catch (NumberFormatException e) {
                System.out.println(RED + "Invalid input! Please enter a number." + RESET);
                choice = -1;
            }
        } while (choice != 0);
        input.close();

    }

    private static void initializeData() {

        List<Room> rooms123 = new ArrayList<>();
        rooms123.add(new Room(1001, 100.0, Room.SINGLE));
        rooms123.add(new Room(1002, 100.0, Room.SINGLE));
        rooms123.add(new Room(1003, 100.0, Room.SINGLE));
        rooms123.add(new Room(2001, 150.0, Room.DOUBLE));
        rooms123.add(new Room(2002, 150.0, Room.DOUBLE));
        rooms123.add(new Room(3001, 250.0, Room.SUITE));
        RoomManager.rooms = rooms123;

        // Initialize sample persons in the array
        PersonManager.getPersonArr()[0] = new Employee("001010-10-1011", "Ali", "ali123@gmail.com", "012-3456789",
                "E001", "Manager", "HR", 5000.00);
        PersonManager.getPersonArr()[1] = new Employee("001111-11-1111", "Abu", "abu456@gmail.com", "012-4567891",
                "E002", "Staff", "Customer Service", 3500.00);
        PersonManager.getPersonArr()[2] = new Customer("901111-10-3456", "Alice", "alice666@gmail.com", "018-9876543",
                "C001");
        PersonManager.getPersonArr()[3] = new Customer("911222-10-2468", "Ammy", "ammy789@gmail.com", "018-5678901",
                "C002");
        PersonManager.getPersonArr()[4] = new Housekeeper("920123-10-3579", "Ben", "ben@gmail.com", "012-5678912",
                "E003", "Housekeeper", "Housekeeping", 2500.00, "H001");
        PersonManager.getPersonArr()[5] = new Housekeeper("920112-10-1001", "Bryan", "bryan@gmail.com", "012-6789123",
                "E004", "Housekeeper", "Housekeeping", 2500.00, "H002");
    }

    public static void crud(String obj) {
        System.out.println("\n=====" + obj + " Management =====");
        System.out.println("1. Add " + obj);
        System.out.println("2. List All " + obj);
        System.out.println("3. Update " + obj);
        System.out.println("4. Delete " + obj);
        System.out.println("0. Back to Previous Page");
    }

    public static void clearScreen(Scanner input) {
        System.out.println(GREEN + "\nPress Enter to continue..." + RESET);
        input.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();

    }
}