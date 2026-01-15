package tw.brad.tutor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class brad06 {
	private static final String URL_OPENDATA = "https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx";
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_INSERT = """
			INSERT INTO gift
				(name,feature,addr,tel,picurl,lat,lng)
			VALUES
				(?,?,?,?,?,?,?)
			""";	
	
	public static void main(String[] args) {
		String json;
		try {
			json = fetchFromURL(URL_OPENDATA);
			//System.out.println(json);
			parseJSON(json);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	static String fetchFromURL(String url) throws Exception {
		URL target = new URL(url);
		URLConnection conn = target.openConnection();

		try(BufferedInputStream bin = 
				new BufferedInputStream(conn.getInputStream())){
			byte[] data = bin.readAllBytes();
			return new String(data);
		}
		
		
//		try(BufferedReader reader = 
//				new BufferedReader(
//					new InputStreamReader(conn.getInputStream()))){
//			String line = null;
//			StringBuffer sb = new StringBuffer();
//			while ((line = reader.readLine()) != null) {
//				sb.append(line);
//			}
//			return sb.toString();
//		}
	}
	
	static void parseJSON(String json) {
	    JSONArray root = new JSONArray(json);
	    System.out.println("筆數：" + root.length());

	    try (
	        Connection conn = DriverManager.getConnection(URL, USER, PASSWD);
	        PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT);
	    ) {
	        for (int i = 0; i < root.length(); i++) {
	            JSONObject row = root.getJSONObject(i);

	            String name = row.optString("Name", "");
	            String feature = row.optString("Feature", "");
	            String addr = row.optString("County", "") +
	                          row.optString("Township", "") +
	                          row.optString("SalePlace", "");
	            String tel = row.optString("ContactTel", "");
	            String picurl = row.optString("Column1", "");
	            String lat = row.optString("Latitude", "");
	            String lng = row.optString("Longitude", "");

	            pstmt.setString(1, name);
	            pstmt.setString(2, feature);
	            pstmt.setString(3, addr);
	            pstmt.setString(4, tel);
	            pstmt.setString(5, picurl);

	            try {
	                pstmt.setDouble(6, Double.parseDouble(lat));
	                pstmt.setDouble(7, Double.parseDouble(lng));
	            } catch (Exception e) {
	                pstmt.setDouble(6, 0.0);
	                pstmt.setDouble(7, 0.0);
	            }

	            pstmt.executeUpdate();
	        }

	        System.out.println("資料匯入完成！");
	    } catch (SQLException e) {
	        System.out.println("資料庫錯誤：" + e);
	    }
	}
	
}