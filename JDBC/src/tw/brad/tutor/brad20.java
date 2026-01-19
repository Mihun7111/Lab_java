package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class brad20 {
	private static final String URL = "jdbc:mysql://localhost:3306/northwind";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_QUERY = """
			SELECT o.EmployeeID, e.LastName, SUM(od.UnitPrice * od.Quantity) AS sum 
			FROM orders o 
			JOIN employees e ON o.EmployeeID = e.EmployeeID 
			JOIN orderdetails od ON o.OrderID = od.OrderID 
			GROUP BY o.EmployeeID 
			ORDER BY sum DESC;
			""";
	public static void main(String[] args) {
		try (
			Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
			PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY);
			ResultSet rs = pstmt.executeQuery();
		){
		while (rs.next()) {
			
		}
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
