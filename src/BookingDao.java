import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getBookingHistoryByUsername(String username) {

        List<String> history = new ArrayList<>();

        String sql = """
                    select 
                        m.Title,
                        s.ShowTime,
                        st.SeatNumber,
                        b.Price,
                        b.BookingDate
                    from Booking b
                    join ShowTime s on b.ShowTimeId = s.ShowTimeId
                    join Movie m on s.MovieId = m.id
                    join Seat st on b.SeatId = st.SeatId
                    where b.Username = ?
                    order by b.BookingDate desc
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String record =
                        "Film: " + resultSet.getString("Title") +
                                " | Seans: " + resultSet.getString("ShowTime") +
                                " | Koltuk: " + resultSet.getString("SeatNumber") +
                                " | Fiyat: " + resultSet.getDouble("Price") + " TL" +
                                " | Tarih: " + resultSet.getTimestamp("BookingDate");

                history.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return history;
    }

}
