public class Room {
    private int roomId;
    private double pricePerNight;
    private String roomType;
    private boolean isAvailble;
    private Housekeeper assignedHousekeeper;
    public static final String SINGLE = "SINGLE";
    public static final String DOUBLE = "DOUBLE";
    public static final String SUITE = "SUITE";

    // getter and setter
    public void setAssignedHousekeeper(Housekeeper housekeeper) {
        this.assignedHousekeeper = housekeeper;
    }

    public Housekeeper getAssignedHousekeeper() {
        return assignedHousekeeper;
    }

    public void setIsAvailble(boolean isAvailble) {
        this.isAvailble = isAvailble;
    }

    public boolean getisIsAvailble() {
        return isAvailble;
    }

    public static String SINGLE() {
        return SINGLE;
    }

    public static String DOUBLE() {
        return DOUBLE;
    }

    public static String SUITE() {
        return SUITE;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomId() {
        return roomId;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public String getRoomType() {
        return roomType;
    }

    // constructor
    public Room(int roomId, double pricePerNight, String roomType) {
        this.roomId = roomId;
        this.pricePerNight = pricePerNight;
        this.roomType = roomType;
        this.isAvailble = true;
    }

    @Override
    public String toString() {

        return String.format("%-10d %-20.2f %-10s %-10s",
                roomId, pricePerNight, roomType, isAvailble ? "Available" : "Not Available");

    }

}