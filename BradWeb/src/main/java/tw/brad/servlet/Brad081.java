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

@WebServlet("/Brad081")
public class Brad081 extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String x, y, result, op;
		x = y = result = op = "";
		try {
			String tempx = request.getParameter("x");
			String tempy = request.getParameter("y");
			String tempop = request.getParameter("op");
			int r = 0, r1 = 0;
			switch (tempop) {
				case "1":
					r = Integer.parseInt(tempx) + Integer.parseInt(tempy);
					break;
				case "2":
					r = Integer.parseInt(tempx) - Integer.parseInt(tempy);
					break;
				case "3":
					r = Integer.parseInt(tempx) * Integer.parseInt(tempy);
					break;
				case "4":
					r = Integer.parseInt(tempx) / Integer.parseInt(tempy);
					r1 = Integer.parseInt(tempx) % Integer.parseInt(tempy);
					break;
			}
			
			result += r + (r1 > 0?(" ...... " + r1):"");
			x = tempx; y = tempy; op = tempop;
		}catch(Exception e) {
			System.out.println(e);
		}
		
		//--------------------------
		response.setContentType("text/html; charset=UTF=8");
		PrintWriter out = response.getWriter();
		
		BufferedInputStream bin = new BufferedInputStream(
			new FileInputStream("C:\\Users\\User\\eclipse-workspace\\BradWeb\\src\\main\\webapp\\brad062.html"));
		byte[] data = bin.readAllBytes();
		String html = new String(data);
		
		out.print(String.format(html, x,
				op.equals("1")?"selected":"",
				op.equals("2")?"selected":"",
				op.equals("3")?"selected":"",
				op.equals("4")?"selected":"",
				y,result));
		
		response.flushBuffer();
		
	}

}
