public class Seat {
    private int id;
    private String seatNumber;

    public Seat(int id, String seatNumber) {
        this.id = id;
        this.seatNumber = seatNumber;
    }

    public int getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }
}
