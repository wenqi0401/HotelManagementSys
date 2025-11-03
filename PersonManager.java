import java.util.Scanner;

public class PersonManager {
    private static Person[] personArr = new Person[100];

    public static Person[] getPersonArr() {
        return personArr;
    }

    public static void personMenu() {
        personArr[0] = new Employee("001010-10-1011", "Ali", "ali123@gmail.com", "012-3456789", "E001", "Manager", "HR",
                5000.00);
        personArr[1] = new Employee("001111-11-1111", "Abu", "abu456@gmail.com", "012-4567891", "E002", "Staff",
                "Customer Service", 3500.00);
        personArr[2] = new Customer("901111-10-3456", "Alice", "alice666@gmail.com", "018-9876543", "C001");
        personArr[3] = new Customer("911222-10-2468", "Ammy", "ammy789@gmail.com", "018-5678901", "C002");
        personArr[4] = new Housekeeper("920123-10-3579", "Ben", "ben@gmail.com", "012-5678912", "E003", "Housekeeper",
                "Housekeeping", 2500.00, "H001");
        personArr[5] = new Housekeeper("920112-10-1001", "Bryan", "bryan@gmail.com", "012-6789123", "E004",
                "Housekeeper", "Housekeeping", 2500.00, "H002");

        Employee employee = new Employee("", "", "", "", "", "", "", 0);
        Customer customer = new Customer("", "", "", "", "");
        Housekeeper housekeeper = new Housekeeper("", "", "", "", "", "", "", 0, "");
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {
            HotelApplication.clearScreen(input);
            System.out.println("\n===== Person Management System =====");// output option
            System.out.println("1. Employee Management");
            System.out.println("2. Customer Management");
            System.out.println("3. Housekeeper Management");
            System.out.println("0. Exit");
            System.out.print("Choose a management module: ");

            int moduleChoice = Validator.getUserChoice(input);

            switch (moduleChoice) {
                case 1:
                    manageEmployees(employee, input, personArr);
                    break;
                case 2:
                    manageCustomers(customer, input);
                    break;
                case 3:
                    manageHousekeepers(housekeeper, input);
                    break;
                case 0:
                System.out.println("Returning to Main Menu...");
                    return;
                default:
                    System.out.println(
                            HotelApplication.RED + "Invalid option. Please try again." + HotelApplication.RESET);
            }
        }
    }

    private static void manageEmployees(Employee service, Scanner input, Person[] personArr) {
        boolean manageEmployees = true;

        while (manageEmployees) {
            HotelApplication.clearScreen(input);
            HotelApplication.crud("Employee");
            System.out.print("Choose an option: ");

            int choice = Validator.getUserChoice(input);

            switch (choice) {
                case 1:
                    create(input, "Employee");
                    break;
                case 2:
                    list(personArr, "Employee");
                    break;
                case 3:
                    update(personArr, "Employee", input);
                    break;
                case 4:
                    delete(personArr, "Employee", input);
                    break;
                case 0:
                    manageEmployees = false;
                    break;
                default:
                    System.out.println(
                            HotelApplication.RED + "Invalid option. Please try again." + HotelApplication.RESET);
            }
        }
    }

    private static void manageCustomers(Customer service, Scanner input) {
        boolean manageCustomers = true;

        while (manageCustomers) {

            HotelApplication.clearScreen(input);
            HotelApplication.crud("Customer");
            System.out.print("Choose an option: ");

            int choice = Validator.getUserChoice(input);

            switch (choice) {
                case 1:
                    create(input, "Customer");
                    break;
                case 2:
                    list(personArr, "Customer");
                    break;
                case 3:
                    update(personArr, "Customer", input);
                    break;
                case 4:
                    delete(personArr, "Customer", input);
                    break;
                case 0:
                    manageCustomers = false;
                    break;
                default:
                    System.out.println(
                            HotelApplication.RED + "Invalid option. Please try again." + HotelApplication.RESET);
            }
        }
    }

    private static void manageHousekeepers(Housekeeper service, Scanner input) {
        boolean manageHousekeepers = true;

        while (manageHousekeepers) {
            HotelApplication.clearScreen(input);
            HotelApplication.crud("Housekeeper");
            System.out.print("Choose an option: ");

            int choice = Validator.getUserChoice(input);

            switch (choice) {
                case 1:
                    create(input, "Housekeeper");
                    break;
                case 2:
                    list(personArr, "Housekeeper");
                    break;
                case 3:
                    update(personArr, "Housekeeper", input);
                    break;
                case 4:
                    delete(personArr, "Housekeeper", input);
                    break;
                case 0:
                    manageHousekeepers = false;
                    break;
                default:
                    System.out.println(
                            HotelApplication.RED + "Invalid option. Please try again." + HotelApplication.RESET);
            }
        }
    }

    // Create new person
    public static void create(Scanner input, String type) {
        String ic = Validator.getValidatedIc(input, "");

        String name = Validator.getValidatedName(input);

        String email = Validator.getValidatedEmail(input);

        String phoneNumber = Validator.getValidatedPhone(input);

        int nextIndex = -1;
        for (int i = 0; i < PersonManager.personArr.length; i++) {
            if (PersonManager.personArr[i] == null) {
                nextIndex = i;
                break;
            }
        }

        if (nextIndex == -1) {
            System.out.println(
                    HotelApplication.RED + "No space available to add new " + type + "." + HotelApplication.RESET);
            return;
        }

        switch (type.toLowerCase()) {
            case "customer":
                String customerId = Validator.getValidId("Enter Customer ID:", 'C', input,"");
                PersonManager.personArr[nextIndex] = new Customer(ic, name, email, phoneNumber, customerId);
                break;

            case "employee":
                String employeeId = Validator.getValidId("Enter Employee ID: ", 'E', input,"");
                System.out.print("Enter Position: ");
                String position = input.nextLine();

                System.out.print("Enter Department: ");
                String department = input.nextLine();

                System.out.print("Enter Salary: ");
                double salary = Validator.getValidatedSalary(input);

                PersonManager.personArr[nextIndex] = new Employee(ic, name, email, phoneNumber, employeeId, position,
                        department, salary);
                break;

            case "housekeeper":
                String hkEmployeeId = Validator.getValidId("Enter Employee ID: ", 'E', input,"");

                System.out.print("Enter Position: ");
                String hkPosition = input.nextLine();

                System.out.print("Enter Department: ");
                String hkDepartment = input.nextLine();

                System.out.print("Enter Salary: ");
                double hkSalary = Validator.getValidatedSalary(input);

                String housekeeperId = Validator.getValidId("Enter Housekeeper ID: ", 'H', input,"");
                PersonManager.personArr[nextIndex] = new Housekeeper(ic, name, email, phoneNumber, hkEmployeeId,
                        hkPosition, hkDepartment, hkSalary, housekeeperId);
                break;

            default:
                System.out.println(HotelApplication.RED + "Invalid type specified." + HotelApplication.RESET);
                return;
        }

        System.out.println(HotelApplication.GREEN +
                type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase() + " created successfully!"
                + HotelApplication.RESET);
    }

    // List all of the person Array according to the type
    public static void list(Person[] personArr, String type) {
        System.out.println("\n--- " + type + " List ---");
        boolean found = false;
        for (Person person : personArr) {
            if (person != null) { // Check for null elements
                if (type.equals("All") || person.getRole().equalsIgnoreCase(type)) {
                    System.out.println(person + "\n");
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println(HotelApplication.RED + "No " + type + " found." + HotelApplication.RESET);
        }
    }

    // Update the person Array according to the IC
    public static void update(Person[] personArr, String type, Scanner input) {
        System.out.print("Enter Twice for IC (include \"-\"): ");

        String icToUpdate = input.nextLine();
        Validator.getValidatedIc(input, icToUpdate);

        boolean found = false;
        for (int i = 0; i < personArr.length; i++) {
            if (personArr[i] != null && personArr[i].getIc().equals(icToUpdate)) {

                personArr[i].setName(Validator.getValidatedName(input));

                personArr[i].setEmail(Validator.getValidatedEmail(input));

                personArr[i].setPhoneNumber(Validator.getValidatedPhone(input));

                if (personArr[i] instanceof Employee && !(personArr[i] instanceof Housekeeper)) {
                    Employee emp = (Employee) personArr[i];
                    System.out.print("Enter new position: ");
                    emp.setPosition(input.nextLine());
                    System.out.print("Enter new department: ");
                    emp.setDepartment(input.nextLine());
                    System.out.print("Enter new salary: ");
                    emp.setSalary(Validator.getValidatedSalary(input));
                } else if (personArr[i] instanceof Customer) {
                    Customer cust = (Customer) personArr[i];
                    cust.setCustomerId(Validator.getValidId("Enter new Customer ID: ", 'C', input,cust.getCustomerId()));
                } else if (personArr[i] instanceof Housekeeper) {

                    Housekeeper house = (Housekeeper) personArr[i];
                    System.out.print("Enter new position: ");
                    house.setPosition(input.nextLine());
                    System.out.print("Enter new department: ");
                    house.setDepartment(input.nextLine());
                    System.out.print("Enter new salary: ");
                    house.setSalary(Validator.getValidatedSalary(input));
                    house.sethousekeeperId(Validator.getValidId("Enter new Housekeeper ID: ", 'H', input,house.gethousekeeperId()));
                }

                System.out.println(HotelApplication.GREEN + type + " updated successfully!" + HotelApplication.RESET);
                found = true;
                break; // Exit the loop after updating
            }
        }

        if (!found) {
            System.out.println(
                    HotelApplication.RED + type + " with ID " + icToUpdate + " not found." + HotelApplication.RESET);
        }
    }

    // Delete person from Array
    public static void delete(Person[] personArr, String type, Scanner input) {
        System.out.print("Enter " + type + " IC to delete: ");
        String idToDelete = input.nextLine();

        boolean found = false;
        for (int i = 0; i < personArr.length; i++) {
            if (personArr[i] != null && personArr[i].getIc().equals(idToDelete)) {
                personArr[i] = null; // "Delete" by setting to null
                found = true;

                // Shift elements down to fill the gap (optional but good practice)
                for (int j = i; j < personArr.length - 1; j++) {
                    personArr[j] = personArr[j + 1];
                }
                personArr[personArr.length - 1] = null; // Set the last element to null after shifting

                System.out.println(HotelApplication.GREEN + type + " with IC " + idToDelete + " deleted."
                        + HotelApplication.RESET);
                break; // Exit the loop after deleting
            }
        }

        if (!found) {
            System.out.println(
                    HotelApplication.RED + type + " with ID " + idToDelete + " not found." + HotelApplication.RESET);
        }
    }

    public static boolean isDuplicateIc(String ic, String excludeIc) {
        for (Person p : personArr) {
            if (p instanceof Customer) {
                Customer c=(Customer)p;
                if (c.getIc().equalsIgnoreCase(ic) && !c.getIc().equalsIgnoreCase(excludeIc)) {
                    return true;
                }
            }
            else if (p instanceof Employee||p instanceof Housekeeper) {
                
                Employee e=(Employee)p;
                if (e.getIc().equalsIgnoreCase(ic) && !e.getIc().equalsIgnoreCase(excludeIc)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isDuplicateId(String Id, String excludeIc) {
        for (Person p : personArr) {
            if (p instanceof Customer) {
                Customer c = (Customer) p;
                if (c.getCustomerId().equalsIgnoreCase(Id) && !c.getIc().equalsIgnoreCase(excludeIc)) {
                    return true;
                }
                
            }
            else if (p instanceof Employee||p instanceof Housekeeper) {
                Employee e=(Employee)p;
                if (e.getEmployeeId().equalsIgnoreCase(Id)&&!e.getEmployeeId().equalsIgnoreCase(excludeIc)) {
                    return true;
                }
            }
        }
        return false;
    }

}