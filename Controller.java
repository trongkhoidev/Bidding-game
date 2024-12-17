package BiddingGame; 

import java.io.*; // Import thư viện để đọc/ghi file
import java.util.HashMap; // Import HashMap để lưu trữ tài khoản

public class Controller { // Class quản lý logic chính của game

    private HashMap<String, Account> accounts; // HashMap lưu trữ danh sách tài khoản
    private final String ACCOUNT_FILE = "Account.txt"; // Tên file lưu thông tin tài khoản

    public Controller() { // Constructor của Controller
        accounts = new HashMap<>(); // Khởi tạo HashMap rỗng
        loadAccounts(); // Load dữ liệu tài khoản từ file
    }

    public boolean login(String username, String password) { // Phương thức kiểm tra đăng nhập
        Account account = accounts.get(username); // Lấy tài khoản từ HashMap
        return account != null && account.getPassword().equals(password); // Kiểm tra tồn tại và mật khẩu đúng
    }

    public boolean createAccount(String username, String password, int balance) { // Phương thức tạo tài khoản mới
        if (accounts.containsKey(username)) { // Kiểm tra username đã tồn tại
            return false; // Trả về false nếu đã tồn tại
        }
        accounts.put(username, new Account(username, password, balance)); // Thêm tài khoản mới vào HashMap
        saveAccounts(); // Lưu danh sách tài khoản vào file
        return true; // Trả về true nếu tạo thành công
    }

    public int getBalance(String username) { // Phương thức lấy số dư tài khoản
        return accounts.get(username).getBalance(); // Trả về số dư của tài khoản
    }

    public void updateBalance(String username, int newBalance) { // Phương thức cập nhật số dư
        if (accounts.containsKey(username)) { // Kiểm tra tài khoản tồn tại
            accounts.get(username).setBalance(newBalance); // Cập nhật số dư mới
            saveAccounts(); // Lưu thay đổi vào file
        }
    }

    public boolean validatePassword(String username, String oldPassword) { // Phương thức kiểm tra mật khẩu cũ
        Account account = accounts.get(username); // Lấy tài khoản từ HashMap
        return account != null && account.getPassword().equals(oldPassword); // Kiểm tra mật khẩu khớp
    }

    public void changePassword(String username, String newPassword) { // Phương thức đổi mật khẩu
        if (accounts.containsKey(username)) { // Kiểm tra tài khoản tồn tại
            accounts.get(username).setPassword(newPassword); // Cập nhật mật khẩu mới
            saveAccounts(); // Lưu thay đổi vào file
        }
    }

    public int randomNumber() { // Phương thức tạo số ngẫu nhiên
        return (int) (Math.random() * 100) + 1; // Trả về số ngẫu nhiên từ 1 đến 100
    }

    private void loadAccounts() { // Phương thức đọc dữ liệu từ file
        try (BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT_FILE))) { // Mở file để đọc
            String line; // Biến lưu dòng đọc được
            while ((line = reader.readLine()) != null) { // Đọc từng dòng trong file
                String[] data = line.split(","); // Tách dữ liệu bằng dấu phẩy
                String username = data[0]; // Lấy username
                String password = data[1]; // Lấy password
                int balance = Integer.parseInt(data[2]); // Lấy số dư
                accounts.put(username, new Account(username, password, balance)); // Thêm tài khoản vào HashMap
            }
        } catch (IOException e) { // Xử lý ngoại lệ khi đọc file
            System.out.println("No accounts file found. Starting fresh."); // Thông báo không tìm thấy file
        }
    }

    private void saveAccounts() { // Phương thức lưu dữ liệu vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT_FILE))) { // Mở file để ghi
            for (Account account : accounts.values()) { // Duyệt qua từng tài khoản
                writer.write(account.getUsername() + "," + account.getPassword() + "," + account.getBalance()); // Ghi thông tin tài khoản
                writer.newLine(); // Xuống dòng sau mỗi tài khoản
            }
        } catch (IOException e) { // Xử lý ngoại lệ khi ghi file
            System.out.println("Failed to save accounts."); // Thông báo lỗi khi lưu file
        }
    }
}
