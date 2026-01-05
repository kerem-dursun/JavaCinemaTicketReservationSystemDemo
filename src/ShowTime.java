import java.time.LocalDateTime;

public class ShowTime {
    private int showTimeId;
    private int movieId;
    private LocalDateTime dateTime;

    public ShowTime(int showTimeId,  int movieId, LocalDateTime dateTime) {
        this.showTimeId = showTimeId;
        this.movieId = movieId;
        this.dateTime = dateTime;
    }

    public int getShowTimeId() {return showTimeId;}
    public int getMovieId() {return movieId;}
    public LocalDateTime getDateTime() {return dateTime;}
}
