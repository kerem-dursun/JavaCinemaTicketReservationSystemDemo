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
                UserInterface.mainPage();
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
            while (true) {
                System.out.println("Lütfen kullanıcı adınızı giriniz (en az 8 karakter giriniz): ");
                userName = input.nextLine().trim();

                if (userName.isEmpty()) {
                    System.out.println("Kullanıcı adı boş girilemez.");
                } else if (userName.length() < 8) {
                    System.out.println("Kullanıcı adı en az 8 karakterden oluşmalıdır.");
                } else {
                    break;
                }
            }

            while (true) {
                System.out.println("Lütfen parolanızı giriniz: ");
                password = input.nextLine().trim();

                if (password.isEmpty()) {
                    System.out.println("Parola adı boş girilemez.");
                } else if (password.length() < 5) {
                    System.out.println("Parola en az 5 karakterden oluşmalıdır.");
                } else {
                    break;
                }
            }
            registered = customerDao.Register(Customer.addNewCustomer(userName, password));
        }
        System.out.println("Lütfen kayıt bilgileriniz ile giriş yapınız.");
        UserInterface.userLoginPage();
    }

    public static void mainPage() {
        System.out.println("1.Vizyondaki filmler.");
        System.out.println("2.Geçmiş.");

        Scanner input = new Scanner(System.in);
        while (true) {
            if (input.hasNextInt()) {
                int userChoise = input.nextInt();

                if (userChoise == 1) {
                    System.out.println("Vizyondaki filmlere yönlendiriliyorsunuz...");
                    UserInterface.moviePage();
                    break;
                } else if (userChoise == 2) {
                    System.out.println("Geçmiş rezervasyonlar ekranına yönlendiriliyorsunuz...");
                    UserInterface.historyPage();
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

    public static void moviePage() {
        Connection connection = DatabaseConnection.getConnection();
        MovieDao movieDao = new MovieDao(connection);
        System.out.println("Vizyondaki filmler:");

        for (Movie movie : movieDao.getAllMovies()) {
            System.out.println(
                    movie.getId() +
                            " | " +
                            movie.getTitle() +
                            " | Süre: " + movie.getDuration() +
                            " dk | Fiyat: " + movie.getPrice() + " TL"
            );
        }
        System.out.println("Bilet almak istediğiniz filmi seçiniz: "); // moviedao.getallmoveis().get komutu ile film seçilebiliyor
    }

    public static void historyPage() {
        System.out.println("Geçmiş rezervasyonlar.");
    }
}