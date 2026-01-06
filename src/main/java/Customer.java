public class Customer implements Discountable{
    private String userName;
    private String password;
    private boolean isStudent;

    public Customer(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public Customer(String userName, String password, boolean isStudent) {
        this.userName = userName;
        this.password = password;
        this.isStudent = isStudent;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }

    public boolean isStudent(){return isStudent;}

    public static Customer addNewCustomer(String userName, String password) {
        return new Customer(userName,password);
    }

    @Override
    public double applyDiscount(double price) {
        if (isStudent) {
            return price * 0.8;
        }
        return price;
    }
}
