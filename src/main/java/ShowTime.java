import java.time.LocalTime;

public class ShowTime {
    private int showTimeId;
    private int movieId;
    private LocalTime dateTime;

    public ShowTime(int showTimeId,  int movieId, LocalTime dateTime) {
        this.showTimeId = showTimeId;
        this.movieId = movieId;
        this.dateTime = dateTime;
    }

    public int getShowTimeId() {return showTimeId;}
    public int getMovieId() {return movieId;}
    public LocalTime getTime() {return dateTime;}
}
