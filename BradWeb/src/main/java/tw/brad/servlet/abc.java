package tw.brad.servlet;

import java.sql.Connection;
import java.sql.DriverManager;

public class abc {

	public static void main(String[] args) {
		try {
		    Connection conn = DriverManager.getConnection(
		        "jdbc:mysql://localhost:3306/iii", "root", "root");
		    System.out.println("Connection OK");
		    conn.close();
		} catch (Exception e) {
		    System.out.println("Connection FAILED");
		    e.printStackTrace();
		}



	}

}
