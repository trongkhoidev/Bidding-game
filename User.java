package BiddingGame;

import java.util.ArrayList;

public class User {
    private String name;
    private String username;
    private String password;
    private double balance;
    private ArrayList<String[]> bettingHistory;
    
    public User(){}
    public User(String name, String username, String password, double balance) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.bettingHistory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public ArrayList<String[]> getBettingHistory() {
        return bettingHistory;
    }
    
    public void addBettingHistory(String[] history) {
        this.bettingHistory.add(history);
    }
    
}
