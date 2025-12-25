public class Customer {
    private int id ;
    private String userName;
    private String password;

    public Customer(String userName, String password){
        this.userName = userName;
        this.password = password;
    }

    public Customer(int id,String userName, String password){
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public int getId(){
        return id;
    }

    public Customer addNewCustomer(String userName, String password){
        return new Customer(userName,password);
    }
}
