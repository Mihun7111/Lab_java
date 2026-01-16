package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class brad071 {
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_QUERY = """
			SELECT id, name, tel, addr, feature
			FROM gift
			WHERE name LIKE ? OR tel LIKE ? OR addr LIKE ? OR feature LIKE ?
			ORDER BY id
			LIMIT ?, ?
			""";
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Page: ");
		int page = scanner.nextInt();
		System.out.print("Keyword: ");
		String key = scanner.next();
		String skey = "%" + key + "%";
		
		final int rpp = 10;		// rows per page
		int start = (page - 1) * rpp;
		
		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWD);
		
		try(Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY)){
			
			pstmt.setString(1, skey);
			pstmt.setString(2, skey);
			pstmt.setString(3, skey);
			pstmt.setString(4, skey);
			pstmt.setInt(5, start);
			pstmt.setInt(6, rpp);
			
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String id = rs.getString("id");
				String name = rs.getString("name");
				String tel = rs.getString("tel");
				String addr = rs.getString("addr");
				String feature = rs.getString("feature");
				System.out.printf("%s:%s:%s:%s\n\t%s\n", id,name,tel,addr, feature);
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
	}

}
