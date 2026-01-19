package tw.brad.tutor;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class brad14 {
	
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_UPDATE = """
			UPDATE member
			SET icon = ?
			WHERE id = ?
			""";
	
	public static void main(String[] args) {
		try (FileInputStream fin = new FileInputStream("dir1/ball2.png");
			 Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
			 PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)) {

			   pstmt.setBinaryStream(1, fin);  // 將圖片檔案作為二進位資料設為第一個參數
			   pstmt.setInt(2, 2);             // 設定第二個參數為整數 2（可能是主鍵或條件）
			   int n = pstmt.executeUpdate();  // 執行更新操作
			   System.out.println(n);          // 印出受影響的列數

			} catch (IOException e) {
			    System.out.println(e);          // 處理檔案讀取錯誤
			} catch (SQLException e) {
			    System.out.println(e);          // 處理資料庫操作錯誤
			}

	}

}
