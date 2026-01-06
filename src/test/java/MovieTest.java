import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovieTest {

    @Test
    void movie2d_price_is_200() {
        Movie movie = new Movie2D(1, "Avatar", 120);
        assertEquals(200, movie.getPrice());
    }

    @Test
    void movie3d_price_is_340() {
        Movie movie = new Movie3D(2, "Avatar 3D", 130);
        assertEquals(340, movie.getPrice());
    }
}
