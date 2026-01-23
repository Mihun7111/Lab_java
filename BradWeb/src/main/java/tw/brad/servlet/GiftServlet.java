package tw.brad.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tw.brad.apis.Gift;
import tw.brad.dao.GiftDAO;

import java.io.IOException;
import java.util.List;


@WebServlet("/GiftMain")
public class GiftServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. User
		// 2. Model
		try {
			List<Gift> gifts = new GiftDAO().findAll();
			
			request.setAttribute("gifts", gifts);
			request.setAttribute("prev", 1);
			request.setAttribute("page", 2);
			request.setAttribute("next", 3);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// 3. Viewer
		request.getRequestDispatcher("GiftViewer").forward(request, response);
	}

}