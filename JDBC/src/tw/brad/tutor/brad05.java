package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class brad05 {
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static final String SQL_INSERT = """
			INSERT INTO cust
				(cname, tel, birthday)
			VALUES
				(?,?,?)
			""";
	
	public static void main(String[] args) {

		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWORD);
		
		try (
			Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
		){
			Scanner scanner = new Scanner(System.in);
			System.out.println("Name:");
			String cname = scanner.next();
			System.out.println("Tel:");
			String tel = scanner.next();
			System.out.println("Birthday:");
			String bir = scanner.next();
			
			pstmt.setString(1, cname);
			pstmt.setString(2, tel);
			pstmt.setString(3, bir);

//			pstmt.setString(1, "tony");
//			pstmt.setString(2, "77777");
//			pstmt.setString(3, "2000-11-12");
			
			int n = pstmt.executeUpdate();
			System.out.println(n);
		}
			catch (SQLException e) {
			System.out.println(e);
		}

	}

}
