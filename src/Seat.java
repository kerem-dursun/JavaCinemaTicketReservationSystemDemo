public class Seat {
    private int seatNo;
    private boolean isAvailable;

    public Seat(int seatNo, boolean isAvailable) {
        this.seatNo = seatNo;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void isReserved(boolean available) {
        isAvailable = available;
    }

    public int getSeatNo() {
        return seatNo;
    }
}
