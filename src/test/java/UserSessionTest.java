import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserSessionTest {

    @Test
    void login_sets_logged_customer() {
        Customer customer = new Customer("kerem", "123");

        UserSession.login(customer);

        assertTrue(UserSession.isLogged());
        assertEquals(customer, UserSession.getLoggedCustomer());
    }

    @Test
    void logout_clears_session() {
        UserSession.loggedOut();
        assertFalse(UserSession.isLogged());
    }
}
