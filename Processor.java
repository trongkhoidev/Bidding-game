package BiddingGame; 

import javax.swing.*; // Import thư viện để tạo giao diện
import java.awt.event.ActionEvent; // Import để xử lý sự kiện
import java.awt.event.ActionListener; // Import để xử lý sự kiện cho button

public class Processor { // Class chính để xử lý giao diện game
    private Controller controller = new Controller(); // Tạo đối tượng controller để xử lý logic game
    private String currentUser; // Lưu username của người chơi hiện tại
    private int currentNumber; // Lưu số hiện tại trong game
    private JFrame loginFrame; // Frame chứa giao diện đăng nhập

    public void start() { // Phương thức khởi động chương trình
        showLoginScreen(); // Hiển thị màn hình đăng nhập
    }

    private void showLoginScreen() { // Phương thức tạo và hiển thị màn hình đăng nhập
        loginFrame = new JFrame("Login"); // Tạo frame đăng nhập với tiêu đề "Login"
        loginFrame.setSize(350, 200); // Đặt kích thước frame
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đặt hành động khi đóng frame

        JPanel panel = new JPanel(); // Tạo panel để chứa các components
        loginFrame.add(panel); // Thêm panel vào frame
        placeLoginComponents(panel); // Thêm các components vào panel
        loginFrame.setVisible(true); // Hiển thị frame
    }

    private void placeLoginComponents(JPanel panel) { // Phương thức thêm các components vào panel đăng nhập
        panel.setLayout(null); // Sử dụng null layout để tự định vị trí components

        JLabel userLabel = new JLabel("Username:"); // Tạo label cho username
        userLabel.setBounds(10, 20, 80, 25); // Đặt vị trí và kích thước cho label
        panel.add(userLabel); // Thêm label vào panel

        JTextField userText = new JTextField(20); // Tạo textfield để nhập username
        userText.setBounds(100, 20, 165, 25); // Đặt vị trí và kích thước cho textfield
        panel.add(userText); // Thêm textfield vào panel

        JLabel passwordLabel = new JLabel("Password:"); // Tạo label cho password
        passwordLabel.setBounds(10, 50, 80, 25); // Đặt vị trí và kích thước
        panel.add(passwordLabel); // Thêm vào panel

        JPasswordField passwordText = new JPasswordField(20); // Tạo field nhập password (ẩn ký tự)
        passwordText.setBounds(100, 50, 165, 25); // Đặt vị trí và kích thước
        panel.add(passwordText); // Thêm vào panel

        JButton loginButton = new JButton("Login"); // Tạo nút đăng nhập
        loginButton.setBounds(10, 80, 80, 25); // Đặt vị trí và kích thước
        panel.add(loginButton); // Thêm vào panel

        JButton createButton = new JButton("Create Account"); // Tạo nút tạo tài khoản mới
        createButton.setBounds(150, 80, 150, 25); // Đặt vị trí và kích thước
        panel.add(createButton); // Thêm vào panel

        loginButton.addActionListener(new ActionListener() { // Thêm sự kiện click cho nút đăng nhập
            @Override
            public void actionPerformed(ActionEvent e) { // Xử lý sự kiện click
                String username = userText.getText(); // Lấy username đã nhập
                String password = new String(passwordText.getPassword()); // Lấy password đã nhập
                if (controller.login(username, password)) { // Kiểm tra đăng nhập
                    currentUser = username; // Lưu username hiện tại
                    currentNumber = controller.randomNumber(); // Tạo số ngẫu nhiên mới
                    loginFrame.dispose(); // Đóng frame đăng nhập
                    JOptionPane.showMessageDialog(null, "Login Successful!"); // Thông báo thành công
                    showGameScreen(); // Hiển thị màn hình game
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Credentials"); // Thông báo thất bại
                }
            }
        });

        createButton.addActionListener(new ActionListener() { // Thêm sự kiện click cho nút tạo tài khoản
            @Override
            public void actionPerformed(ActionEvent e) {
                showCreateAccountScreen(); // Hiển thị màn hình tạo tài khoản
            }
        });
    }

    private void showCreateAccountScreen() { // Phương thức hiển thị màn hình tạo tài khoản
        JFrame frame = new JFrame("Create Account"); // Tạo frame mới
        frame.setSize(350, 250); // Đặt kích thước frame

        JPanel panel = new JPanel(); // Tạo panel mới
        frame.add(panel); // Thêm panel vào frame
        panel.setLayout(null); // Sử dụng null layout

        JLabel userLabel = new JLabel("Username:"); // Label username
        userLabel.setBounds(10, 20, 80, 25); // Đặt vị trí và kích thước
        panel.add(userLabel); // Thêm vào panel

        JTextField userText = new JTextField(20); // TextField nhập username
        userText.setBounds(100, 20, 165, 25); // Đặt vị trí và kích thước
        panel.add(userText); // Thêm vào panel

        JLabel passwordLabel = new JLabel("Password:"); // Label password
        passwordLabel.setBounds(10, 50, 80, 25); // Đặt vị trí và kích thước
        panel.add(passwordLabel); // Thêm vào panel

        JPasswordField passwordText = new JPasswordField(20); // Field nhập password
        passwordText.setBounds(100, 50, 165, 25); // Đặt vị trí và kích thước
        panel.add(passwordText); // Thêm vào panel

        JLabel balanceLabel = new JLabel("Balance:"); // Label số dư
        balanceLabel.setBounds(10, 80, 80, 25); // Đặt vị trí và kích thước
        panel.add(balanceLabel); // Thêm vào panel

        JTextField balanceText = new JTextField(20); // TextField nhập số dư
        balanceText.setBounds(100, 80, 165, 25); // Đặt vị trí và kích thước
        panel.add(balanceText); // Thêm vào panel

        JButton createButton = new JButton("Create"); // Nút tạo tài khoản
        createButton.setBounds(10, 120, 80, 25); // Đặt vị trí và kích thước
        panel.add(createButton); // Thêm vào panel

        createButton.addActionListener(new ActionListener() { // Xử lý sự kiện click nút tạo
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText(); // Lấy username
                String password = new String(passwordText.getPassword()); // Lấy password
                int balance = Integer.parseInt(balanceText.getText()); // Lấy số dư
                if (controller.createAccount(username, password, balance)) { // Tạo tài khoản mới
                    JOptionPane.showMessageDialog(null, "Account Created Successfully!"); // Thông báo thành công
                    frame.dispose(); // Đóng frame
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists!"); // Thông báo thất bại
                }
            }
        });

        frame.setVisible(true); // Hiển thị frame
    }
    
    private void showAddBalance(JLabel balanceToUpdate) { // Phương thức hiển thị màn hình nạp tiền
        JFrame addBalanceFrame = new JFrame("Add Balance"); // Tạo frame mới
        addBalanceFrame.setSize(300, 150); // Đặt kích thước frame
        
        JPanel panel = new JPanel(); // Tạo panel mới
        addBalanceFrame.add(panel); // Thêm panel vào frame
        panel.setLayout(null); // Sử dụng null layout

        JLabel amountLabel = new JLabel("Amount to add:"); // Label số tiền cần nạp
        amountLabel.setBounds(10, 20, 100, 25); // Đặt vị trí và kích thước
        panel.add(amountLabel); // Thêm vào panel

        JTextField amountField = new JTextField(); // TextField nhập số tiền
        amountField.setBounds(120, 20, 150, 25); // Đặt vị trí và kích thước
        panel.add(amountField); // Thêm vào panel

        JButton confirmButton = new JButton("Confirm"); // Nút xác nhận
        confirmButton.setBounds(100, 60, 100, 25); // Đặt vị trí và kích thước
        panel.add(confirmButton); // Thêm vào panel

        confirmButton.addActionListener(event -> { // Xử lý sự kiện click nút xác nhận
            try {
                int addAmount = Integer.parseInt(amountField.getText()); // Lấy số tiền nhập vào
                if (addAmount > 0) { // Kiểm tra số tiền hợp lệ
                    int balance = controller.getBalance(currentUser) + addAmount; // Tính số dư mới
                    controller.updateBalance(currentUser, balance); // Cập nhật số dư
                    balanceToUpdate.setText("Balance: " + balance); // Cập nhật hiển thị số dư
                    JOptionPane.showMessageDialog(null, "Balance Updated Successfully!"); // Thông báo thành công
                    addBalanceFrame.dispose(); // Đóng frame
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a positive amount!"); // Thông báo lỗi
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number!"); // Thông báo lỗi định dạng
            }
        });

        addBalanceFrame.setLocationRelativeTo(null); // Đặt vị trí frame ở giữa màn hình
        addBalanceFrame.setVisible(true); // Hiển thị frame
    }

    private void showGameScreen() { // Phương thức hiển thị màn hình game chính
        JFrame frame = new JFrame("Bidding"); // Tạo frame game
        frame.setSize(400, 400); // Đặt kích thước
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Đặt hành động khi đóng

        JPanel panel = new JPanel(); // Tạo panel mới
        frame.add(panel); // Thêm panel vào frame
        panel.setLayout(null); // Sử dụng null layout

        // Tạo và thêm các components cho màn hình game
        JLabel numberLabel = new JLabel("Number: " + currentNumber); // Label hiển thị số hiện tại
        numberLabel.setBounds(150, 20, 150, 25); // Đặt vị trí và kích thước
        panel.add(numberLabel); // Thêm vào panel

        JLabel balanceLabel = new JLabel("Balance: " + controller.getBalance(currentUser)); // Label hiển thị số dư
        balanceLabel.setBounds(150, 50, 150, 25); // Đặt vị trí và kích thước
        panel.add(balanceLabel); // Thêm vào panel

        JLabel betLabel = new JLabel("Bet Amount:"); // Label số tiền cược
        betLabel.setBounds(10, 80, 80, 25); // Đặt vị trí và kích thước
        panel.add(betLabel); // Thêm vào panel

        JTextField betField = new JTextField(10); // TextField nhập số tiền cược
        betField.setBounds(100, 80, 100, 25); // Đặt vị trí và kích thước
        panel.add(betField); // Thêm vào panel

        JButton upButton = new JButton("UP"); // Nút đặt cược lên
        upButton.setBounds(50, 120, 100, 25); // Đặt vị trí và kích thước
        panel.add(upButton); // Thêm vào panel

        JButton downButton = new JButton("DOWN"); // Nút đặt cược xuống
        downButton.setBounds(200, 120, 100, 25); // Đặt vị trí và kích thước
        panel.add(downButton); // Thêm vào panel

        JButton addBalanceButton = new JButton("Add Balance"); // Nút nạp tiền
        addBalanceButton.setBounds(50, 160, 120, 25); // Đặt vị trí và kích thước
        panel.add(addBalanceButton); // Thêm vào panel

        JButton logoutButton = new JButton("Logout"); // Nút đăng xuất
        logoutButton.setBounds(150, 300, 100, 25); // Đặt vị trí và kích thước
        panel.add(logoutButton); // Thêm vào panel

        JLabel resultLabel = new JLabel("Result: "); // Label hiển thị kết quả
        resultLabel.setBounds(50, 200, 300, 25); // Đặt vị trí và kích thước
        panel.add(resultLabel); // Thêm vào panel

        // Thêm xử lý sự kiện cho các nút
        upButton.addActionListener(e -> handleBet("UP", betField, numberLabel, balanceLabel, resultLabel)); // Xử lý đặt cược lên
        downButton.addActionListener(e -> handleBet("DOWN", betField, numberLabel, balanceLabel, resultLabel)); // Xử lý đặt cược xuống

        addBalanceButton.addActionListener(e -> { // Xử lý nạp tiền
            showAddBalance(balanceLabel); // Hiển thị màn hình nạp tiền
        });

        logoutButton.addActionListener(e -> { // Xử lý đăng xuất
            frame.dispose(); // Đóng frame game
            showLoginScreen(); // Hiển thị màn hình đăng nhập
        });

        frame.setVisible(true); // Hiển thị frame game
    }

    private void handleBet(String choice, JTextField betField, JLabel numberLabel, 
                          JLabel balanceLabel, JLabel resultLabel) { // Phương thức xử lý đặt cược
        try {
            int betAmount = Integer.parseInt(betField.getText()); // Lấy số tiền cược
            int balance = controller.getBalance(currentUser); // Lấy số dư hiện tại

            if (betAmount <= 0 || betAmount > balance) { // Kiểm tra số tiền cược hợp lệ
                resultLabel.setText("Result: Invalid Bet Amount!"); // Thông báo lỗi
                return;
            }

            int newNumber = controller.randomNumber(); // Tạo số mới
            boolean win = (choice.equals("UP") && newNumber > currentNumber) // Kiểm tra thắng/thua
                    || (choice.equals("DOWN") && newNumber < currentNumber);

            if (win) { // Xử lý khi thắng
                balance += betAmount; // Cộng tiền thắng
                resultLabel.setText("Result: You Win! New number is: " + newNumber); // Thông báo thắng
            } else { // Xử lý khi thua
                balance -= betAmount; // Trừ tiền thua
                resultLabel.setText("Result: You Lose! New number is: " + newNumber); // Thông báo thua
            }

            currentNumber = newNumber; // Cập nhật số mới
            controller.updateBalance(currentUser, balance); // Cập nhật số dư
            numberLabel.setText("Number: " + currentNumber); // Cập nhật hiển thị số
            balanceLabel.setText("Balance: " + balance); // Cập nhật hiển thị số dư
        } catch (NumberFormatException ex) {
            resultLabel.setText("Result: Please enter a valid bet amount!"); // Thông báo lỗi định dạng
        }
    }

    public static void main(String[] args) { // Phương thức main
        Processor processor = new Processor(); // Tạo đối tượng Processor
        processor.start(); // Khởi động chương trình
    }
}