public class Housekeeper extends Employee {

    private String housekeeperId;
    private boolean isAssigned;

    public Housekeeper(String ic, String name, String email, String phoneNumber, String employeeId, String position,
            String department, double salary, String housekeeperId) {
        super(ic, name, email, phoneNumber, employeeId, position, department, salary);
        this.housekeeperId = housekeeperId;
        this.isAssigned = true;
    }

    // Getter and Setter
    public String gethousekeeperId() {
        return housekeeperId;
    }

    public void sethousekeeperId(String housekeeperId) {
        this.housekeeperId = housekeeperId;
    }

    public boolean isAssigned() {
        return isAssigned;
    }

    public void setAssigned(boolean assigned) {
        this.isAssigned = assigned;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nHousekeeper Id: %-5s", housekeeperId);
    }

    @Override
    public String getRole() {
        return "Housekeeper";
    }
}
