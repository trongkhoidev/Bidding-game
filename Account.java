package BiddingGame;

public class Account { // Class để quản lý thông tin tài khoản người chơi

    private String username; // Biến lưu tên đăng nhập của tài khoản
    private String password; // Biến lưu mật khẩu của tài khoản
    private int balance; // Biến lưu số dư của tài khoản

    public Account(String username, String password, int balance) { // Constructor khởi tạo tài khoản mới
        this.username = username; // Gán giá trị username
        this.password = password; // Gán giá trị password
        this.balance = balance; // Gán giá trị balance
    }

    public String getUsername() { // Phương thức lấy tên đăng nhập
        return username; // Trả về tên đăng nhập
    }

    public String getPassword() { // Phương thức lấy mật khẩu
        return password; // Trả về mật khẩu
    }

    public void setPassword(String password) { // Phương thức cập nhật mật khẩu mới
        this.password = password; // Gán giá trị mật khẩu mới
    }

    public int getBalance() { // Phương thức lấy số dư tài khoản
        return balance; // Trả về số dư hiện tại
    }

    public void setBalance(int balance) { // Phương thức cập nhật số dư mới
        this.balance = balance; // Gán giá trị số dư mới
    }
}
