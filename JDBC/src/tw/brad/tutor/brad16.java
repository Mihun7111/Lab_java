package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import tw.brad.apis.Bike;

public class brad16 {
	
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_UPDATE = """
			UPDATE member
			SET bike = ?
			WHERE id = ?
			""";
	
	public static void main(String[] args) {
		Bike bike = new Bike();
		bike.upSpeed().upSpeed().upSpeed().upSpeed();
		System.out.println(bike);
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
			PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE)){
			
			pstmt.setObject(1, bike);
			pstmt.setInt(2, 1);
			int n = pstmt.executeUpdate();
			System.out.println(n);
			
		}catch (SQLException e) {
			System.out.println(e);
		}		
	}
}
