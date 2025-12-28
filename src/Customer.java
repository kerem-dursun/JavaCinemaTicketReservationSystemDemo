public class Customer {
    private String userName;
    private String password;

    public Customer(String userName, String password){
        this.userName = userName;
        this.password = password;
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
