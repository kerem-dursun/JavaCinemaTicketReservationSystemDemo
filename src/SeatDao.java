import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDao {
    private Connection connection;

    public SeatDao(Connection connection) {
        this.connection = connection;
    }

    public List<Seat> getAvailableSeats(int showTimeId) {

        List<Seat> seats = new ArrayList<>();

        String sqlCode =
                "select * from Seat WHERE ShowTimeId = ? AND IsBooked = 0";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlCode);
            preparedStatement.setInt(1, showTimeId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                seats.add(
                        new Seat(
                                rs.getInt("SeatId"),
                                rs.getString("SeatNumber")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }
}
