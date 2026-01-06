import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeatDao {
    private Connection connection;

    public SeatDao(Connection connection) {
        this.connection = connection;
    }

//    public List<Seat> getAvailableSeats(int showTimeId) {
//
//        List<Seat> seats = new ArrayList<>();
//
//        String sqlCode = "select * from Seat WHERE ShowTimeId = ? AND IsBooked = 0";
//
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sqlCode);
//            preparedStatement.setInt(1, showTimeId);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//                seats.add(
//                        new Seat(
//                                rs.getInt("SeatId"),
//                                rs.getString("SeatNumber")
//                        )
//                );
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return seats;
//    }

    public List<Seat> getSeatsByShowTimeId(int showTimeId) {

        List<Seat> seats = new ArrayList<>();

        String sql = """
                    select SeatId, SeatNumber, IsBooked
                    from Seat
                    where ShowTimeId = ?
                    order by SeatNumber
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, showTimeId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                seats.add(
                        new Seat(
                                resultSet.getInt("SeatId"),
                                resultSet.getString("SeatNumber"),
                                resultSet.getBoolean("IsBooked")
                        )
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seats;
    }

    public void bookSeat(int seatId) {

        String sql = "update Seat set IsBooked = 1 where SeatId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, seatId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
