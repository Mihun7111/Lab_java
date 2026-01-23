package tw.brad.listener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet("/abc")
public class abc extends HttpServlet {
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
