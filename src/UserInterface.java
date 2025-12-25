import java.sql.Connection;
import java.util.Scanner;

public class UserInterface {

    public static void userStartupPage() {
        System.out.println("Bilet satın alma uygulamasına hoş geldiniz. Menünün yanında yazan sayıları terminale girerek işlemlerinizi gerçekleştirebilirsiniz.");
        System.out.println("1. Kullanıcı Girişi");
        System.out.println("2. Kullanıcı Kayıt");

        while (true) {
            Scanner input = new Scanner(System.in);

            if (input.hasNextInt()) {
                int userChoise = input.nextInt();

                if (userChoise == 1) {
                    System.out.println("Kullanıcı giriş ekranına yönlendiriliyorsunuz...");
                    break;
                } else if (userChoise == 2) {
                    System.out.println("Kullanıcı kayıt ekranına yönlendiriliyorsunuz...");
                    UserInterface.userRegisterPage();
                    break;
                } else {
                    System.out.println("Geçersiz bir yanıt girdiniz lütfen sadece ekranda yazan sayıları kullanın.");
                }
            } else {
                System.out.println("Geçersiz bir yanıt girdiniz lütfen sadece ekranda yazan sayıları kullanın.");
                input.next();
            }
        }
    }

    public static void userRegisterPage(){
        String userName;
        String password;

        Scanner input = new Scanner(System.in);

        System.out.println("Lütfen kullanıcı adınızı giriniz: ");
        userName = input.nextLine();

        System.out.println("Lütfen parolanızı giriniz: ");
        password = input.nextLine();

        Connection connection = DatabaseConnection.getConnection();
        CustomerDao customerDao = new CustomerDao(connection);
        customerDao.insert(Customer.addNewCustomer(userName,password));


    }
}
