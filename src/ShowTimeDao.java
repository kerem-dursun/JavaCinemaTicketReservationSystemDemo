import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowTimeDao {
    private Connection connection;

    public ShowTimeDao(Connection connection) {
        this.connection = connection;
    }

    public List<ShowTime> getShowTimeByMovieId(int movieId) {
        List<ShowTime> showTimes = new ArrayList<>();

        String sqlCode = "select * from ShowTime  where movieId = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlCode)) {
            preparedStatement.setInt(1, movieId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                showTimes.add(
                        new ShowTime(
                                resultSet.getInt("ShowTimeId"),
                                resultSet.getInt("MovieId"),
                                resultSet.getTime("ShowTime").toLocalTime()
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Seanslar getirilirken bir hata yaşandı.", e);
        }

        return showTimes;
    }
}
