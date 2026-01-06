import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void constructor_sets_fields_correctly() {
        Booking booking = new Booking("kerem", 1, 5, 200);

        assertEquals("kerem", booking.getUserName());
        assertEquals(1, booking.getShowTimeId());
        assertEquals(5, booking.getSeatId());
        assertEquals(200, booking.getPrice());
        assertNotNull(booking.getBookingDate());
    }
}
