import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Validator {
    public static int getUserChoice(Scanner input) {
        int choice = -1;

        while (true) {
            try {
                String inputStr = input.nextLine().trim(); // Read as string first
                choice = Integer.parseInt(inputStr);
                break;
            } catch (NumberFormatException e) {
                System.out.println(
                        HotelApplication.RED + "Invalid input! Please enter a number." + "\nPlease enter again: "
                                + HotelApplication.RESET);
            }
        }

        return choice;
    }

    public static String getValidatedName(Scanner input) {
        String name;

        do {
            System.out.print("Enter name: ");
            name = input.nextLine().trim();

            if (!name.matches("[a-zA-Z ]+")) {
                System.out.println(HotelApplication.RED + "Invalid name! Only alphabets and spaces are allowed."
                        + HotelApplication.RESET);
            }
        } while (!name.matches("[a-zA-Z ]+"));

        return name;
    }

    public static String getValidatedIc(Scanner input, String excludeIc) {
        String ic;

        while (true) {
            System.out.print("Enter IC (include \"-\"): ");
            ic = input.nextLine().trim();

            if (ic.length() != 14) {
                System.out.println(HotelApplication.RED + "Invalid IC! IC must be exactly 14 characters long."
                        + HotelApplication.RESET);
                continue;
            }

            if (PersonManager.isDuplicateIc(ic, excludeIc)) {
                System.out.println(HotelApplication.RED + "This IC already exists. Please enter a different one."
                        + HotelApplication.RESET);
                continue;
            }

            return ic;
        }
    }


    public static String getValidatedEmail(Scanner input) {
        String email;
        boolean valid = false;

        do {
            System.out.print("Enter Email: ");
            email = input.nextLine();
            if (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                valid = true;
            } else {
                System.out.print(
                        HotelApplication.RED + "Invalid email format! Please enter again: " + HotelApplication.RESET);
            }
        } while (!valid);

        return email;
    }

    public static String getValidatedPhone(Scanner input) {
        String phone;
        boolean valid = false;

        do {
            System.out.print("Enter Phone Number: ");
            phone = input.nextLine();
            if (phone.matches("\\d{3}-\\d{7}")) {
                valid = true;
            } else {
                System.out.print(
                        HotelApplication.RED + "Invalid phone format! Please use format XXX-XXXXXXX. Enter again: "
                                + HotelApplication.RESET);
            }
        } while (!valid);

        return phone;
    }

    public static LocalDate getValidDate(Scanner input, String prompt) {
        LocalDate date = null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (date == null) {
            System.out.print(prompt);
            String dateInput = input.nextLine();

            try {
                date = LocalDate.parse(dateInput, dateFormatter);

            } catch (DateTimeParseException e) {
                System.out.println(HotelApplication.RED + "Invalid date format! Please use yyyy-MM-dd format."
                        + HotelApplication.RESET);
            }
        }

        return date;
    }

    public static String getValidId(String promptMessage, char validPrefix, Scanner input,String excludeId) {
        while (true) {
            System.out.print(promptMessage);
            String id = input.nextLine().trim().toUpperCase();

            // Length must be exactly 4
            if (id.length() < 4) {
                System.out.println(HotelApplication.RED
                        + "Error: ID must be at least 4 characters long (e.g., C001). Please try again."
                        + HotelApplication.RESET);
                continue;
            }

            // Prefix check
            if (id.charAt(0) != validPrefix) {
                System.out.println(
                        HotelApplication.RED + "Error: Invalid ID prefix. Please try again." + HotelApplication.RESET);
                continue;
            }

            // Check if last 3 characters are digits
            boolean digitsValid = true;
            for (int i = 1; i < 4; i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    digitsValid = false;
                    break;
                }
            }

            if (!digitsValid) {
                System.out.println(HotelApplication.RED + "Error: Last 3 characters must be digits. Please try again."
                        + HotelApplication.RESET);
                continue;
            }
            if (PersonManager.isDuplicateId(id, excludeId)) {
                System.out.println(HotelApplication.RED
                        + "Error: Customer ID already exists. Please enter a different one." + HotelApplication.RESET);
                continue;
            }

            return id; // Valid ID
        }
    }

    public static double getValidatedSalary(Scanner input) {
        double salary = 0.0;
        boolean valid = false;
        while (!valid) {
            try {
                salary = Double.parseDouble(input.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println(HotelApplication.RED + "Invalid salary! Please enter a numeric value."
                        + HotelApplication.RESET);
                System.out.print("Enter Salary: ");
            }
        }
        return salary;
    }

}