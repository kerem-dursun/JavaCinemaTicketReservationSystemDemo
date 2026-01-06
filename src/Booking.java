import java.time.LocalDateTime;

public class Booking implements Bookable {

    private int id;
    private String userName;
    private int showTimeId;
    private int seatId;
    private double price;
    private LocalDateTime bookingDate;

    public Booking(String userName, int showTimeId, int seatId, double price) {
        this.userName = userName;
        this.showTimeId = showTimeId;
        this.seatId = seatId;
        this.price = price;
        this.bookingDate = LocalDateTime.now();
    }

    public String getUserName() {
        return userName;
    }

    public int getShowTimeId() {
        return showTimeId;
    }

    public int getSeatId() {
        return seatId;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    @Override
    public void book() {
        System.out.println("Rezervasyon oluşturuldu.");
    }
}
