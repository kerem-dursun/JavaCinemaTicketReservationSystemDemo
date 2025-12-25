import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {
    private final Connection connection;

    public CustomerDao(Connection connection){
        this.connection = connection;
    }

    public void Register(Customer customer){
        String sqlcode = """ 
                insert into Customer(username, password) 
                values (?, ?) 
        """;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlcode, PreparedStatement.RETURN_GENERATED_KEYS)){

            preparedStatement.setString(1,customer.getUserName());
            preparedStatement.setString(2,customer.getPassword());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()) {
                customer.setId(resultSet.getInt(1));
            }

            System.out.println("Kayıt işlemi başarılı.");

        } catch (SQLException e) {
            throw new RuntimeException("Müşteri eklenemedi",e);
        }
    }

    public boolean loginControl(String username, String password){
        String sqlcode = """
                select 1
                from Customer
                where userName = ? and password = ?
                """;

        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlcode)){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException("Kullanıcı bilgileri yanlış.",e);
        }
    }

}
