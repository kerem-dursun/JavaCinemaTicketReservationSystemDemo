import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
    private final Connection connection;

    public CustomerDao(Connection connection) {
        this.connection = connection;
    }

    public boolean Register(Customer customer) {
        String sqlControl = """
                select 1 
                from Customer
                where userName = ?
                """;
        boolean doesExist;
        try (PreparedStatement statement = connection.prepareStatement(sqlControl);) {
            statement.setString(1, customer.getUserName());
            ResultSet rs = statement.executeQuery();

            doesExist = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (!doesExist) {
            String sqlcode = """ 
                    insert into Customer(username, password) 
                    values (?, ?) 
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlcode)) {

                preparedStatement.setString(1, customer.getUserName());
                preparedStatement.setString(2, customer.getPassword());
                preparedStatement.executeUpdate();

                System.out.println("Kayıt işlemi başarılı.");
                return true;
            } catch (SQLException e) {
                throw new RuntimeException("Müşteri eklenemedi", e);
            }
        } else if(doesExist) {
            System.out.println("Bu kullanıcı adına sahip bir kullanıcı zaten bulunuyor lütfen başka bir kullanıcı adı deneyin.");
            return false;
        }
        return false;
    }

    public boolean loginControl(String username, String password) {
        String sqlcode = """
                select 1
                from Customer
                where userName = ? and password = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlcode)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException("Kullanıcı bilgileri yanlış.", e);
        }
    }

}
