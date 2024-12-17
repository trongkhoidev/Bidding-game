package BiddingGame;

import java.io.*;
import java.util.HashMap;

public class Controller {
    private HashMap<String, Account> accounts;
    private final String ACCOUNT_FILE = "Account.txt";

    public Controller() {
        accounts = new HashMap<>();
        loadAccounts();
    }

    public boolean login(String username, String password) {
        Account account = accounts.get(username);
        return account != null && account.getPassword().equals(password);
    }

    public boolean createAccount(String username, String password, int balance) {
        if (accounts.containsKey(username)) {
            return false; 
        }
        accounts.put(username, new Account(username, password, balance));
        saveAccounts();
        return true;
    }

    public int getBalance(String username) {
        return accounts.get(username).getBalance();
    }

    public void updateBalance(String username, int newBalance) {
        if (accounts.containsKey(username)) {
            accounts.get(username).setBalance(newBalance);
            saveAccounts();
        }
    }

    public boolean validatePassword(String username, String oldPassword) {
        Account account = accounts.get(username);
        return account != null && account.getPassword().equals(oldPassword);
    }

    public void changePassword(String username, String newPassword) {
        if (accounts.containsKey(username)) {
            accounts.get(username).setPassword(newPassword);
            saveAccounts();
        }
    }

    public int randomNumber() {
        return (int) (Math.random() * 100) + 1; // Trả về số ngẫu nhiên từ 1 đến 100
    }

    private void loadAccounts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String username = data[0];
                String password = data[1];
                int balance = Integer.parseInt(data[2]);
                accounts.put(username, new Account(username, password, balance));
            }
        } catch (IOException e) {
            System.out.println("No accounts file found. Starting fresh.");
        }
    }

    private void saveAccounts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) {
            for (Account account : accounts.values()) {
                writer.write(account.getUsername() + "," + account.getPassword() + "," + account.getBalance());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save accounts.");
        }
    }
}
