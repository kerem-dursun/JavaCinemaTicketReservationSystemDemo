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

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public static Customer addNewCustomer(String userName, String password){
        return new Customer(userName,password);
    }
}
