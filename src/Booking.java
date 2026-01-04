public class Booking implements Bookable {
    private Customer customer;
    private Movie movie;
    private Seat seat;

    public Booking(Customer customer, Movie movie, Seat seat) {
        this.customer = customer;
        this.movie = movie;
        this.seat = seat;
    }

    @Override
    public float calculatePrice() {
        return movie.getPrice();
    }

    @Override
    public void book() {
        if (!seat.isAvailable()) {
            seat.isReserved(true);
            System.out.println("Rezervasyon başarılı");
            System.out.println("Film: " + movie.getTitle());
            System.out.println("Koltuk: " + seat.getSeatNo());
            System.out.println("Fiyat: " + calculatePrice() + " TL");
        } else {
            System.out.println("Bu koltuk dolu!");
        }
    }
}
