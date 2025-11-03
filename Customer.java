public class Customer extends Person {
    private String customerId;

    public Customer(String ic, String name, String email, String phoneNumber, String customerId) {
        super(ic, name, email, phoneNumber);
        this.customerId = customerId;
    }

    // Getter and Setter
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCustomer Id: " + customerId;
    }

    @Override
    public String getRole() {
        return "Customer";
    }
}
