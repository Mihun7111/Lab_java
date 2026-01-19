package tw.brad.tutor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class brad15 {
	
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_QUERY = """
			SELECT id, email, name, icon
			FROM member
			WHERE id = ?
			""";
	
	public static void main(String[] args) {
	    try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
	         PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY)) {

	        pstmt.setInt(1, 1);  // 設定查詢條件，例如 WHERE id = 1
	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            String email = rs.getString("email");
	            String fname = String.format("dir2/%s.png", email);  // 依 email 命名檔案
	            InputStream in = rs.getBinaryStream("icon");          // 取得 BLOB 圖片資料
	            byte[] data = in.readAllBytes();                      // 讀取所有位元組

	            try (FileOutputStream fout = new FileOutputStream(fname)) {
	                fout.write(data);  // 寫入本地檔案
	                fout.flush();      // 確保資料完全寫入
	            }

	            System.out.println("OK2");  // 成功訊息
	        } else {
	            System.out.println("Member NOT EXIST");  // 查無資料
	        }}catch (Exception e) {
				System.out.println(e);
			}
	    }
	

}
