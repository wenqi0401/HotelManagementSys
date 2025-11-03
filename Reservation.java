import java.time.LocalDate;

public class Reservation {
    private int reservationId;
    private String guestName;
    private String contactNumber;
    private String roomType;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private static int nextReservationId = 1001;

    public Reservation(String guestName, String contactNumber, String roomType, int roomId, LocalDate checkInDate,
            LocalDate checkOutDate) {
        this.reservationId = nextReservationId++;
        this.guestName = guestName;
        this.contactNumber = contactNumber;
        this.roomType = roomType;
        this.roomId = roomId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    // Getters
    public int getReservationId() {
        return reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomId() {
        return roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    // Setters
    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    @Override
    public String toString() {
        return "Reservation ID: " + reservationId +
                "\nGuest Name: " + guestName +
                "\nContact Number: " + contactNumber +
                "\nRoom Type: " + roomType +
                "\nRoom Number: " + roomId +
                "\nCheck-in Date: " + checkInDate +
                "\nCheck-out Date: " + checkOutDate;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Reservation && this.reservationId == ((Reservation) o).getReservationId();
    }
}