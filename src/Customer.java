public class Customer {
    private int id ;
    private String userName;
    private String password;
    private static int idCounter = 0;

    public Customer(String userName, String password){
        this.id = idCounter++;
        this.userName = userName;
        this.password = password;
    }


}
