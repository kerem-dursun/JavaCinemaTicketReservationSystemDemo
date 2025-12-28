public class UserSession {
    private static Customer loggedCustomer;

    public static void login(Customer customer) {
        loggedCustomer = customer;
    }

    public static void loggedOut(){
        loggedCustomer = null;
    }

    public static Customer getLoggedCustomer() {
        return loggedCustomer;
    }

    public static boolean isLogged(){
        return loggedCustomer != null;
    }

}
