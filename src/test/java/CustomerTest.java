import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    @Test
    void student_customer_gets_discount() {
        Customer student = new Customer("ali", "123", true);
        double discounted = student.applyDiscount(200);

        assertEquals(160, discounted);
    }

    @Test
    void normal_customer_no_discount() {
        Customer normal = new Customer("veli", "123", false);
        double price = normal.applyDiscount(200);

        assertEquals(200, price);
    }
}
