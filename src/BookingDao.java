import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class BookingDao {

    private Connection connection;

    public BookingDao(Connection connection) {
        this.connection = connection;
    }

    public void save(Booking booking) {

        String sql = """
            insert into Booking
            (userName, ShowTimeId, SeatId, Price, BookingDate)
            values (?, ?, ?, ?, ?)
        """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, booking.getUserName());
            preparedStatement.setInt(2, booking.getShowTimeId());
            preparedStatement.setInt(3, booking.getSeatId());
            preparedStatement.setDouble(4, booking.getPrice());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(booking.getBookingDate()));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
