package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import tw.brad.apis.BCrypt;
import tw.brad.apis.Member;

public class brad11 {
    // 資料庫連線設定
    private static final String URL = "jdbc:mysql://localhost:3306/iii"; // 連線到本機 MySQL，資料庫名稱為 iii
    private static final Properties prop = new Properties(); // JDBC 連線屬性物件
    private static final String USER = "root";   // 資料庫使用者帳號
    private static final String PASSWD = "root"; // 資料庫使用者密碼

    // SQL 指令：查詢會員資料 (依 email)
    private static final String SQL_LOGIN = """
            SELECT id, email, passwd, name
            FROM member
            WHERE email = ?
            """;

    public static void main(String[] args) {
        // 設定連線屬性
        prop.put("user", USER);
        prop.put("password", PASSWD);

        // 1. 執行登入流程
        Member member = login();
        if (member != null) {
            // 登入成功，顯示歡迎訊息
            System.out.printf("Welcome, %s\n", member.getName());
        }
        // 登入失敗會在 login() 方法中顯示提示訊息
    }

    /**
     * 會員登入方法
     * @return Member 物件 (若登入成功)，否則回傳 null
     */
    static Member login() {
        System.out.println("Member Login");
        Scanner scanner = new Scanner(System.in);

        // 讀取使用者輸入
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String passwd = scanner.nextLine();

        // 使用 JDBC 連線到資料庫，查詢會員資料
        try (Connection conn = DriverManager.getConnection(URL, prop);
             PreparedStatement pstmt = conn.prepareStatement(SQL_LOGIN)) {

            pstmt.setString(1, email); // 設定查詢條件 (email)
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // 找到該 email 的會員，檢查密碼是否正確
                if (BCrypt.checkpw(passwd, rs.getString("passwd"))) {
                    // 密碼正確，建立 Member 物件並回傳
                    Member member = new Member(
                            rs.getLong("id"),
                            rs.getString("email"),
                            rs.getString("passwd"),
                            rs.getString("name"));
                    return member;
                } else {
                    // 密碼錯誤
                    System.out.println("Login Failure(2) - 密碼錯誤");
                }
            } else {
                // 查無此 email
                System.out.println("Login Failure(1) - 帳號不存在");
            }

        } catch (Exception e) {
            // 例外處理 (例如資料庫連線失敗)
            System.out.println("ERROR: " + e);
        }

        return null; // 登入失敗
    }
}