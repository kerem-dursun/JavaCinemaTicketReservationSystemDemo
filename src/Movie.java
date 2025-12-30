public abstract class Movie {
    private int id;
    private String title;
    private float duration;

    public Movie(int id, String title, float duration){
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public abstract float getPrice();
}
