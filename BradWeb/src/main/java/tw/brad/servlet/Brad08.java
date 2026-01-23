package tw.brad.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Brad08")
public class Brad08 extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String x, y, result;
		x = y = result = "";
		try {
			String tempx = request.getParameter("x");
			String tempy = request.getParameter("y");
			int r = Integer.parseInt(tempx) + Integer.parseInt(tempy);
			result += r;
			x = tempx; y = tempy;
			System.out.println(result);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		//--------------------------
		response.setContentType("text/html; charset=UTF=8");
		PrintWriter out = response.getWriter();
		
		BufferedInputStream bin = new BufferedInputStream(
			new FileInputStream("C:\\Users\\User\\eclipse-workspace\\BradWeb\\src\\main\\webapp\\brad061.html"));
		byte[] data = bin.readAllBytes();
		String html = new String(data);
		
		out.print(String.format(html, x, y,result));
		
		response.flushBuffer();
		
	}

}
