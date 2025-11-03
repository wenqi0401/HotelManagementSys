public abstract class Person {
    private String ic;
    private String name;
    private String email;
    private String phoneNumber;

    public Person(String ic, String name, String email, String phoneNumber) {
        this.ic = ic;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getIc() {
        return ic;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return String.format("IC: %s,Name: %s, Email: %s, Phone Number: %s", ic, name, email, phoneNumber);
    }

    public abstract String getRole();
}