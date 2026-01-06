public class BookingService {

    public static double calculateFinalPrice(Movie movie,Customer customer) {
        double price = movie.getPrice();

        if (customer.isStudent()) {
            Discountable discount = new StudentDiscount();
            price = discount.applyDiscount(price);
        }

        return price;
    }
}
