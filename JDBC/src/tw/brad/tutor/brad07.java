package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class brad07 {
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
    private static final String USER = "root";
    private static final String PASSWD = "root";

    // 分頁查詢 SQL：根據 id 排序，取出指定範圍的資料
    private static final String SQL_QUERY = """
        SELECT id, name, tel, addr
        FROM gift
        ORDER BY id
        LIMIT ?, ?
        """;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Page: ");
        int page = scanner.nextInt(); // 使用者輸入頁碼

        final int rpp = 7; // 每頁顯示筆數 rows per page
        int start = (page - 1) * rpp; // 計算起始位置

        // 建立資料庫連線設定
        Properties prop = new Properties();
        prop.put("user", USER);
        prop.put("password", PASSWD);

        // 建立連線並執行查詢
        try (
            Connection conn = DriverManager.getConnection(URL, prop);
            PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY)
        ) {
            pstmt.setInt(1, start); // 設定 LIMIT 的起始位置
            pstmt.setInt(2, rpp);   // 設定 LIMIT 的筆數

            ResultSet rs = pstmt.executeQuery(); // 執行查詢

            // 逐筆讀取結果並印出
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String addr = rs.getString("addr");
                System.out.printf("%s : %s : %s : %s\n", id, name, tel, addr);
            }

        } catch (Exception e) {
            System.out.println("錯誤：" + e);
        }
    }


}
