package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class brad21 {
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_QUERY = """
			SELECT id, name, addr, tel, feature, lat, lng, picurl
			FROM gift
			""";
	public static void main(String[] args) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
//				PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY);
				PreparedStatement pstmt = conn.prepareStatement(SQL_QUERY,
						ResultSet.TYPE_SCROLL_SENSITIVE,
						ResultSet.CONCUR_UPDATABLE);
				ResultSet rs = pstmt.executeQuery();){
		
			rs.next();
			System.out.println(rs.getString("name"));
			rs.next();
			System.out.println(rs.getString("name"));
			rs.next();
			System.out.println(rs.getString("name"));
			
			rs.absolute(8);
			System.out.println(rs.getString("name"));
			
			rs.updateString("feature", "很香粉好喝");
			rs.updateString("addr", "賣茶的地方");
			rs.updateRow();
			
			rs.absolute(13);
			//rs.deleteRow();
			
			rs.moveToInsertRow();
			rs.updateString("name", "布萊德超值禮盒");
			rs.updateString("feature", "要買要快");
			rs.updateString("addr", "不來的大公司");
			rs.updateString("tel", "04-12345678");
			rs.updateString("picurl", "https://greateimg");
			rs.updateDouble("lat", 24);
			rs.updateDouble("lng", 123);
			rs.insertRow();
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
