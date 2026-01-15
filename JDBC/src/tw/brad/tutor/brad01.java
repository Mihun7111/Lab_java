package tw.brad.tutor;

public class brad01 {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
