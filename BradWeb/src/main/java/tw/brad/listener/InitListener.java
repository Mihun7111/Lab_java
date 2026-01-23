package tw.brad.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Start");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("MySQL Driver Load Success");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL Driver ERROR");
		}
		
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("End");
	}


}
