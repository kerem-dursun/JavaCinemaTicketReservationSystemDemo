import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingServiceTest {

    @Test
    void student_customer_gets_discounted_price() {
        Movie movie = new Movie2D(1, "Test", 100);
        Customer student = new Customer("kerem", "123", true);

        double price = BookingService.calculateFinalPrice(movie, student);

        assertEquals(160, price);
    }

    @Test
    void normal_customer_pays_full_price() {
        Movie movie = new Movie3D(1, "Test", 100);
        Customer normal = new Customer("kerem", "123", false);

        double price = BookingService.calculateFinalPrice(movie, normal);

        assertEquals(340, price);
    }
}
