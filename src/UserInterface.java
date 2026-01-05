import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    public static void userStartupPage() {
        System.out.println("Bilet satın alma uygulamasına hoş geldiniz. Menünün yanında yazan sayıları terminale girerek işlemlerinizi gerçekleştirebilirsiniz.");
        System.out.println("1. Kullanıcı Girişi");
        System.out.println("2. Kullanıcı Kayıt");

        Scanner input = new Scanner(System.in);

        int userChoice = safeIntInput(input, 1, 2);

        if (userChoice == 1) {
            System.out.println("Kullanıcı giriş ekranına yönlendiriliyorsunuz...");
            userLoginPage();
        } else {
            System.out.println("Kullanıcı kayıt ekranına yönlendiriliyorsunuz...");
            userRegisterPage();
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

        int userChoice = safeIntInput(input, 1, 2);

        if (userChoice == 1) {
            System.out.println("Vizyondaki filmlere yönlendiriliyorsunuz...");
            moviePage();
        } else {
            System.out.println("Geçmiş rezervasyonlar ekranına yönlendiriliyorsunuz...");
            historyPage();
        }
    }

    public static void moviePage() {

        Scanner input = new Scanner(System.in);

        Connection connection = DatabaseConnection.getConnection();
        MovieDao movieDao = new MovieDao(connection);
        ShowTimeDao showTimeDao = new ShowTimeDao(connection);

        System.out.println("Vizyondaki filmler:");

        List<Movie> movies = movieDao.getAllMovies();

        for (Movie movie : movies) {
            System.out.println(
                    movie.getId() +
                            " | " +
                            movie.getTitle() +
                            " | Süre: " + movie.getDuration() +
                            " dk | Fiyat: " + movie.getPrice() + " TL"
            );
        }

        System.out.println("Bilet almak istediğiniz filmi seçiniz:");
        int movieChoice = safeIntInput(input, 1, movies.size());

        Movie selectedMovie = movies.get(movieChoice - 1);

        System.out.println(selectedMovie.getTitle());
        System.out.println(selectedMovie.getPrice() + " TL");

        List<ShowTime> showTimes =
                showTimeDao.getShowTimeByMovieId(selectedMovie.getId());

        System.out.println("Seans seçiniz:");

        for (int i = 0; i < showTimes.size(); i++) {
            System.out.println((i + 1) + ") " + showTimes.get(i).getTime());
        }

        int showTimeChoice = safeIntInput(input, 1, showTimes.size());

        ShowTime selectedShowTime = showTimes.get(showTimeChoice - 1);

        System.out.println("Seçilen seans: " + selectedShowTime.getTime());

        SeatDao seatDao = new SeatDao(connection);

        List<Seat> seats =
                seatDao.getAvailableSeats(selectedShowTime.getShowTimeId());

        System.out.println("Boş koltuklar:");

        for (int i = 0; i < seats.size(); i++) {
            System.out.print(
                    (i + 1) + "-" + seats.get(i).getSeatNumber() + "  "
            );
        }

        System.out.println();

        int seatChoice = safeIntInput(input, 1, seats.size());

        Seat selectedSeat = seats.get(seatChoice - 1);

        System.out.println("Seçilen koltuk: " + selectedSeat.getSeatNumber());

    }


    public static void historyPage() {
        System.out.println("Geçmiş rezervasyonlar.");
    }

    private static int safeIntInput(Scanner input, int min, int max) {

        int value;

        while (true) {
            if (input.hasNextInt()) {
                value = input.nextInt();

                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println(
                            "Lütfen " + min + " ile " + max + " arasında bir değer giriniz."
                    );
                }
            } else {
                System.out.println("Lütfen sadece sayı giriniz.");
                input.next();
            }
        }
    }

}