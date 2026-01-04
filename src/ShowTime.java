import java.time.LocalDateTime;

public class ShowTime {
    private int showTimeId;
    private Movie movie;
    private LocalDateTime dateTime;

    public ShowTime(int showTimeId,  Movie movie, LocalDateTime dateTime) {
        this.showTimeId = showTimeId;
        this.movie = movie;
        this.dateTime = dateTime;
    }

    public int getShowTimeId() {return showTimeId;}
    public Movie getMovie() {return movie;}
    public LocalDateTime getDateTime() {return dateTime;}
}
