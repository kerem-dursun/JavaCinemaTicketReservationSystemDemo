public class Seat {

    private int id;
    private String seatNumber;
    private boolean isBooked;

    public Seat(int id, String seatNumber, boolean isBooked) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.isBooked = isBooked;
    }

    public int getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isBooked() {
        return isBooked;
    }
}
