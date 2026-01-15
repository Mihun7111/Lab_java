package tw.brad.tutor;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

public class brad061 {
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
			
			byte[] buffer = new byte[1024];
			int li;
			
			while(li = (bin.read(buffer)) != -1) {
				bout.writer(li, 0, buffer);
			}
			
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
		System.out.println(root.length());
		
		for (int i=0; i<root.length(); i++) {
			JSONObject row = root.getJSONObject(i);
			System.out.println(row.getString("Name"));
		}
		
		
		
	}
	
}