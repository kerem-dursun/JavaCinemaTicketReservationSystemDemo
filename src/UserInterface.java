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
                    UserInterface.userLoginPage();
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

    public static void userLoginPage() {
        String userName;
        String password;

        Scanner input = new Scanner(System.in);

        for (int i = 0; i <= 5; i++) {

            System.out.println("Lütfen kullanıcı adınızı giriniz: ");
            userName = input.nextLine();

            System.out.println("Lütfen parolanızı giriniz: ");
            password = input.nextLine();

            Connection connection = DatabaseConnection.getConnection();
            CustomerDao customerDao = new CustomerDao(connection);

            if (customerDao.loginControl(userName, password)) {
                UserSession.login(Customer.addNewCustomer(userName, password));
                System.out.println(UserSession.getLoggedCustomer().getUserName());
                System.out.println("Kullanıcı girişi başarılı ana sayfaya yönlendiriliyorsunuz...");

                break;
            }
            if (i == 4) {
                System.out.println("Çok sayıda başarısız giriş denemesinde bulundunuz daha sonra tekrar deneyiniz.");
                System.exit(0);
            }
            System.out.println("Kullanıcı adınız veya şifreniz yanlış tekrar deneyiniz.");
        }
    }

    public static void userRegisterPage() {
        String userName;
        String password;
        boolean registered = false;

        Connection connection = DatabaseConnection.getConnection();

        CustomerDao customerDao = new CustomerDao(connection);

        Scanner input = new Scanner(System.in);

        while (!registered) {
            System.out.println("Lütfen kullanıcı adınızı giriniz: ");
            if(userName = input.nextLine())


            System.out.println("Lütfen parolanızı giriniz: ");
            password = input.nextLine();

            registered = customerDao.Register(Customer.addNewCustomer(userName, password));
        }
    }
}
