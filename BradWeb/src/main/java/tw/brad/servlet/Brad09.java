package tw.brad.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class Brad09
 */
@WebServlet("/Brad09")
public class Brad09 extends HttpServlet {
    public Brad09() {
        System.out.println("Brad09");
    }

    @Override
    public void init() throws ServletException {
    	System.out.println("init()");
    	super.init();
    }
    
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init(config)");
		super.init(config);
	}


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
	    System.out.println("service(1)"); 
	    //super.service(arg0, arg1); 

	    String method = req.getMethod(); 
	    System.out.println(method); 
	}


	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("service(2)");
		super.service(arg0, arg1);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Brad09.doGet");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Brad09.doPost");
	}

}
