package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RS123 {
	
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	
	public static void main(String[] args) {
		String sql = "SELECT id, name, email FROM member";

	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         // 1. 執行查詢並獲取結果集
	         ResultSet rs = pstmt.executeQuery()) {

	        System.out.println("ID\tName\tEmail");
	        System.out.println("-------------------------------");

	        // 2. 使用 while 搭配 rs.next() 逐列讀取
	        while (rs.next()) {
	            // 3. 透過型態對應的方法 (getInt, getString) 取得欄位值
	            int id = rs.getInt("id");          // 依照標籤名稱取值
	            String name = rs.getString("name");
	            String email = rs.getString(3);     // 也可以依照欄位索引取值 (從1開始)

	            System.out.println(id + "\t" + name + "\t" + email);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	}

}
