package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class brad03 {
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWORD = "root";
	private static final String SQL_INSERT = """
			INSERT INTO cust
				(cname, tel, birthday)
			VALUES
				('Amy', '11223344', '1999-12-25')
			""";
	
//	private static final String SQL_DELETE = """
//			
//			""";
	
	public static void main(String[] args) {

		Properties prop = new Properties();
		prop.put("user", USER);
		prop.put("password", PASSWORD);
		
		try (
			Connection conn = DriverManager.getConnection(URL, prop);
			Statement stmt = conn.createStatement()
		){
			int n = stmt.executeUpdate(SQL_INSERT);
			System.out.println(n);
			
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}
