import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String connectionString =
            "jdbc:sqlserver://KeremDursun;" +
                    "databaseName=CinemaTicketReservationSystem;" +
                    "integratedSecurity=true;" +
                    "encrypt=true;" +
                    "trustServerCertificate=true;";

    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            throw new RuntimeException("Veritabanı bağlantı hatası",e);
        }
    }
}
