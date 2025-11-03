public class Employee extends Person {

    private String employeeId;
    private String position;
    private String department;
    private double salary;

    public Employee(String ic, String name, String email, String phoneNumber,
            String employeeId, String position, String department, double salary) {
        super(ic, name, email, phoneNumber);
        this.employeeId = employeeId;
        this.position = position;
        this.department = department;
        this.salary = salary;
    }

    // Getters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getPosition() {
        return position;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    // Setters
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nEmployee Id: %-5s Position: %-10s Department: %-10s Salary:RM %.2f",
                employeeId, position, department, salary);
    }

    @Override
    public String getRole() {
        return "Employee";
    }
}
