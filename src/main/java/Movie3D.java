public class Movie3D extends Movie {
    public Movie3D(int id, String title, float duration) {
        super(id, title, duration);
    }

    @Override
    public float getPrice() {
        return 340;
    }
}
