public class Movie2D extends Movie {
    public Movie2D(int id, String title, float duration) {
        super(id, title, duration);
    }

    @Override
    public float getPrice() {
        return 200;
    }
}
