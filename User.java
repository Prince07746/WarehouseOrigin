package Warehouse4;

public class User {
    private String username;
    private String password;
    private String type;
    private double balance;

    public User(String username, String password, String type, double balance) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}