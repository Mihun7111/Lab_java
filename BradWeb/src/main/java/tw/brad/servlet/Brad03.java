package tw.brad.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/Brad03")
public class Brad03 extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		Enumeration<String> names =  request.getHeaderNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			System.out.println(name+":"+request.getHeader(name));
			
		}
	}

}
