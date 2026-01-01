import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {
    private final Connection connection;

    public MovieDao(Connection connection) {
        this.connection = connection;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String sqlcode = "select id, title, duration, movieType from Movie";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlcode)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                String title = resultSet.getString("Title");
                float duration = resultSet.getFloat("Duration");
                String type = resultSet.getString("MovieType");

                Movie movie;

                if (type.equalsIgnoreCase("2D")) {
                    movie = new Movie2D(id, title, duration);
                } else if (type.equalsIgnoreCase("3D")) {
                    movie = new Movie3D(id, title, duration);
                } else {
                    throw new RuntimeException("Bilinmeyen film tipi!");
                }

                movies.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Filmler getirilirken bir hata oluştu.", e);
        }
        return movies;
    }
}
