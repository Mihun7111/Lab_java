package tw.brad.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.brad.apis.Bike;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Brad18")
public class Brad18 extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		RequestDispatcher dispatcher = request.getRequestDispatcher("Brad19");
		
		request.setAttribute("age", 18);
		request.setAttribute("x", 10);
		request.setAttribute("y", 3);
		
		Bike bike = new Bike();
		bike.upSpeed().upSpeed().upSpeed().upSpeed();
		request.setAttribute("bike", bike);
		
		
		response.setContentType("text/html; charset=UTF=8");
		PrintWriter out = response.getWriter();
		
		out.println("<h1>Brad Big Company</h1>");
		out.println("<hr />");
		out.println("<div>Hello, Brad</div>");
		out.println("<hr />");
		dispatcher.forward(request, response);
		out.println("<hr />");
		
		
		response.flushBuffer();
	}

}
