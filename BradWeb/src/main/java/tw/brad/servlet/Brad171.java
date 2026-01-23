package tw.brad.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Brad171")
public class Brad171 extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		Object obj1 = request.getAttribute("age");
		int age = (Integer)obj1;
		int x = (Integer)request.getAttribute("x");
		int y = (Integer)request.getAttribute("y");
		
		if (name != null) {
			out.println(String.format("Hello, %s:%d:%d:%d<br />", name,age,x,y));
		}
		
	}

}
