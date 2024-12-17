package BiddingGame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Processor {

    private Controller controller = new Controller();
    private String currentUser;
    private int currentNumber;
    private JFrame loginFrame;

    public void start() {
        showLoginScreen();
    }

    private void showLoginScreen() {
        loginFrame = new JFrame("Login");
        loginFrame.setSize(350, 200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        loginFrame.add(panel);
        placeLoginComponents(panel);
        loginFrame.setVisible(true);
    }

    private void placeLoginComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        JButton createButton = new JButton("Create Account");
        createButton.setBounds(150, 80, 150, 25);
        panel.add(createButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                if (controller.login(username, password)) {
                    currentUser = username;
                    currentNumber = controller.randomNumber();
                    loginFrame.dispose();
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    showGameScreen();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials");
                }
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateAccountScreen();
            }
        });
    }

    private void showCreateAccountScreen() {
        JFrame frame = new JFrame("Create Account");
        frame.setSize(350, 250);

        JPanel panel = new JPanel();
        frame.add(panel);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        JLabel balanceLabel = new JLabel("Balance:");
        balanceLabel.setBounds(10, 80, 80, 25);
        panel.add(balanceLabel);

        JTextField balanceText = new JTextField(20);
        balanceText.setBounds(100, 80, 165, 25);
        panel.add(balanceText);

        JButton createButton = new JButton("Create");
        createButton.setBounds(10, 120, 80, 25);
        panel.add(createButton);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());
                int balance = Integer.parseInt(balanceText.getText());
                if (controller.createAccount(username, password, balance)) {
                    JOptionPane.showMessageDialog(null, "Account Created Successfully!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists!");
                }
            }
        });

        frame.setVisible(true);
    }

    private void showGameScreen() {
    JFrame frame = new JFrame("Bidding");
    frame.setSize(400, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    frame.add(panel);
    panel.setLayout(null);

    JLabel numberLabel = new JLabel("Number: " + currentNumber);
    numberLabel.setBounds(150, 20, 150, 25);
    panel.add(numberLabel);

    JLabel balanceLabel = new JLabel("Balance: " + controller.getBalance(currentUser));
    balanceLabel.setBounds(150, 50, 150, 25);
    panel.add(balanceLabel);

    JLabel betLabel = new JLabel("Bet Amount:");
    betLabel.setBounds(10, 80, 80, 25);
    panel.add(betLabel);

    JTextField betField = new JTextField(10);
    betField.setBounds(100, 80, 100, 25);
    panel.add(betField);

    JButton upButton = new JButton("UP");
    upButton.setBounds(50, 120, 100, 25);
    panel.add(upButton);

    JButton downButton = new JButton("DOWN");
    downButton.setBounds(200, 120, 100, 25);
    panel.add(downButton);

    JButton addBalanceButton = new JButton("Add Balance");
    addBalanceButton.setBounds(50, 160, 120, 25);
    panel.add(addBalanceButton);

    JButton logoutButton = new JButton("Logout");
    logoutButton.setBounds(150, 300, 100, 25);
    panel.add(logoutButton);

    JLabel resultLabel = new JLabel("Result: ");
    resultLabel.setBounds(50, 200, 300, 25);
    panel.add(resultLabel);

    upButton.addActionListener(e -> handleBet("UP", betField, numberLabel, balanceLabel, resultLabel));
    downButton.addActionListener(e -> handleBet("DOWN", betField, numberLabel, balanceLabel, resultLabel));

    addBalanceButton.addActionListener(e -> {
        String input = JOptionPane.showInputDialog("Enter amount to add:");
        try {
            int addAmount = Integer.parseInt(input);
            if (addAmount > 0) {
                int balance = controller.getBalance(currentUser) + addAmount;
                controller.updateBalance(currentUser, balance);
                balanceLabel.setText("Balance: " + balance);
                JOptionPane.showMessageDialog(null, "Balance Updated!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid amount!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Please enter a valid number!");
        }
    });

    logoutButton.addActionListener(e -> {
        frame.dispose();
        showLoginScreen();
    });

    frame.setVisible(true);
}

//    private void showAccountInfo() {
//        int balance = controller.getBalance(currentUser);
//        JOptionPane.showMessageDialog(null, "Username: " + currentUser + "\nBalance: " + balance);
//    }
//
//    private void showAddBalanceScreen() {
//        String input = JOptionPane.showInputDialog("Enter amount to add:");
//        try {
//            int amount = Integer.parseInt(input);
//            if (amount > 0) {
//                int newBalance = controller.getBalance(currentUser) + amount;
//                controller.updateBalance(currentUser, newBalance);
//                JOptionPane.showMessageDialog(null, "Balance updated! New Balance: " + newBalance);
//            } else {
//                JOptionPane.showMessageDialog(null, "Invalid amount!");
//            }
//        } catch (NumberFormatException ex) {
//            JOptionPane.showMessageDialog(null, "Please enter a valid number!");
//        }
//    }

//    private void showChangePasswordScreen() {
//        JPasswordField oldPasswordField = new JPasswordField();
//        JPasswordField newPasswordField = new JPasswordField();
//        Object[] message = {
//            "Old Password:", oldPasswordField,
//            "New Password:", newPasswordField
//        };
//
//        int option = JOptionPane.showConfirmDialog(null, message, "Change Password", JOptionPane.OK_CANCEL_OPTION);
//        if (option == JOptionPane.OK_OPTION) {
//            String oldPassword = new String(oldPasswordField.getPassword());
//            String newPassword = new String(newPasswordField.getPassword());
//
//            if (controller.validatePassword(currentUser, oldPassword)) {
//                controller.changePassword(currentUser, newPassword);
//                JOptionPane.showMessageDialog(null, "Password changed successfully!");
//            } else {
//                JOptionPane.showMessageDialog(null, "Old password is incorrect!");
//            }
//        }
//    }

    private void handleBet(String choice, JTextField betField, JLabel numberLabel, JLabel balanceLabel, JLabel resultLabel) {
        try {
            int betAmount = Integer.parseInt(betField.getText());
            int balance = controller.getBalance(currentUser);

            if (betAmount <= 0 || betAmount > balance) {
                resultLabel.setText("Result: Invalid Bet Amount!");
                return;
            }

            int newNumber = controller.randomNumber();
            boolean win = (choice.equals("UP") && newNumber > currentNumber)
                    || (choice.equals("DOWN") && newNumber < currentNumber);

            if (win) {
                balance += betAmount;
                resultLabel.setText("Result: You Win! New number is: " + newNumber);
            } else {
                balance -= betAmount;
                resultLabel.setText("Result: You Lose! New number is: " + newNumber);
            }

            currentNumber = newNumber;
            controller.updateBalance(currentUser, balance);
            numberLabel.setText("Number: " + currentNumber);
            balanceLabel.setText("Balance: " + balance);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Result: Please enter a valid bet amount!");
        }
    }

    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();
    }
}
