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

                Customer customer =
                        customerDao.getCustomerByUsername(userName);

                UserSession.login(customer);

                System.out.println(customer.getUserName());
                System.out.println("Kullanıcı girişi başarılı ana sayfaya yönlendiriliyorsunuz...");
                UserInterface.mainPage();
                break;
            }

            if (i == 4) {
                System.out.println(
                        "Çok sayıda başarısız giriş denemesinde bulundunuz daha sonra tekrar deneyiniz."
                );
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
        System.out.println("3.Çıkış.");

        Scanner input = new Scanner(System.in);

        int userChoice = safeIntInput(input, 1, 3);

        if (userChoice == 1) {
            System.out.println("Vizyondaki filmlere yönlendiriliyorsunuz...");
            moviePage();
        } else if (userChoice == 2) {
            System.out.println("Geçmiş rezervasyonlar ekranına yönlendiriliyorsunuz...");
            historyPage();
        }else if (userChoice == 3) {
            System.exit(0);
        }
    }

    public static void moviePage() {

        Scanner input = new Scanner(System.in);

        Connection connection = DatabaseConnection.getConnection();
        MovieDao movieDao = new MovieDao(connection);
        ShowTimeDao showTimeDao = new ShowTimeDao(connection);

        System.out.println("Vizyondaki filmler:");

        List<Movie> movies = movieDao.getAllMovies();

        if(UserSession.getLoggedCustomer().isStudent()) {
            for (Movie movie : movies) {
                System.out.println(
                        movie.getId() +
                                " | " +
                                movie.getTitle() +
                                " | Süre: " + movie.getDuration() +
                                " dk | Fiyat: " + movie.getPrice() * 0.8 + " TL (Öğrenci indirimi!)"
                );
            }
        }
        else{
            for (Movie movie : movies) {
                System.out.println(
                        movie.getId() +
                                " | " +
                                movie.getTitle() +
                                " | Süre: " + movie.getDuration() +
                                " dk | Fiyat: " + movie.getPrice() + " TL"
                );
            }
        }
        System.out.println("Bilet almak istediğiniz filmi seçiniz:");
        int movieChoice = safeIntInput(input, 1, movies.size());

        Movie selectedMovie = movies.get(movieChoice - 1);

        System.out.println(selectedMovie.getTitle());
        if (UserSession.getLoggedCustomer().isStudent()) {
            System.out.println(selectedMovie.getPrice() * 0.8 + " TL");
        }
        else{
            System.out.println(selectedMovie.getPrice() + " TL");
        }

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

        List<Seat> seats = seatDao.getSeatsByShowTimeId(selectedShowTime.getShowTimeId());

        System.out.println("Koltuklar:");

        for (int i = 0; i < seats.size(); i++) {
            Seat seat = seats.get(i);

            if (seat.isBooked()) {
                System.out.println(
                        RED + (i + 1) + ") " + seat.getSeatNumber() + " [DOLU]" + RESET
                );
            } else {
                System.out.println(
                        GREEN + (i + 1) + ") " + seat.getSeatNumber() + " [BOŞ]" + RESET
                );
            }
        }

        System.out.println("Rezerve etmek istediğiniz koltuğu seçiniz:");

        int seatChoice;

        while (true) {
            seatChoice = safeIntInput(input, 1, seats.size());
            Seat selectedSeat = seats.get(seatChoice - 1);

            if (selectedSeat.isBooked()) {
                System.out.println(RED + "Bu koltuk dolu. Lütfen başka bir koltuk seçin." + RESET);
            } else {
                seatDao.bookSeat(selectedSeat.getId());
                Customer customer = UserSession.getLoggedCustomer();

                double finalPrice = BookingService.calculateFinalPrice(selectedMovie, customer);

                Booking booking = new Booking(
                        customer.getUserName(),
                        selectedShowTime.getShowTimeId(),
                        selectedSeat.getId(),
                        finalPrice
                );


                BookingDao bookingDao = new BookingDao(connection);
                bookingDao.save(booking);

                System.out.println(GREEN + "Koltuk başarıyla rezerve edildi!" + RESET);
                System.out.println("\n---REZERVASYON ÖZETİ---");
                System.out.println("Kullanıcı : " + booking.getUserName());
                System.out.println("Film      : " + selectedMovie.getTitle());
                System.out.println("Seans     : " + selectedShowTime.getTime());
                System.out.println("Koltuk    : " + selectedSeat.getSeatNumber());
                System.out.println("Fiyat     : " + finalPrice + " TL");
                System.out.println("-----------------------------");

                System.out.println("1.Devam.");
                System.out.println("2.Çıkış.");
                int userChoise = safeIntInput(input, 1, 2);
                if (userChoise == 1) {
                    UserInterface.mainPage();
                }
                else if (userChoise == 2) {
                    break;
                }
            }
        }

    }

    public static void historyPage() {

        Connection connection = DatabaseConnection.getConnection();
        BookingDao bookingDao = new BookingDao(connection);

        String username = UserSession.getLoggedCustomer().getUserName();

        List<String> history = bookingDao.getBookingHistoryByUsername(username);

        System.out.println("\n---REZERVASYON GEÇMİŞİ---");

        if (history.isEmpty()) {
            System.out.println("Henüz yapılmış bir rezervasyonunuz yok.");
        } else {
            for (String record : history) {
                System.out.println(record);
            }
        }
        System.out.println("-----------------------------");
        UserInterface.mainPage();
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

    public static final String RESET = "\u001B[0m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";

}