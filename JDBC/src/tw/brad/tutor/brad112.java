package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import tw.brad.apis.BCrypt;
import tw.brad.apis.Member;

public class brad112 {
	private static final String URL = "jdbc:mysql://localhost:3306/iii";
	private static final Properties prop = new Properties();
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_LOGIN = """
			SELECT id, email, passwd, name
			FROM member
			WHERE email = ?
			""";

	private static final String SQL_CHPASSWD = """
			UPDATE member
			SET passwd = ?
			WHERE id = ?
			""";
	
	public static void main(String[] args) {
		prop.put("user", USER);
		prop.put("password", PASSWD);
		
		// 1. Login
		Member member = login();
		if (member != null) {
			System.out.printf("Welcome, %s\n", member.getName());
			// 2. Change Password
			boolean isOK = chPasswd(member);
		}
		
		
		//----------------------------------
		
		
	}
	
	static Member login() {
		System.out.println("Member Login");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Password: ");	
		String passwd = scanner.nextLine();

		try(Connection conn = DriverManager.getConnection(URL,prop);
				PreparedStatement pstmt = conn.prepareStatement(SQL_LOGIN)){
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				if (BCrypt.checkpw(passwd, rs.getString("passwd"))) {
					Member member = new Member(
							rs.getLong("id"), 
							rs.getString("email"),
							rs.getString("passwd"),
							rs.getString("name"));
					return member;
				}else {
					System.out.println("Login Failure(2)");
				}
			}else {
				System.out.println("Login Failure(1)");
			}
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		return null;
	}
	
	static boolean chPasswd(Member member) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Change Passwd: ");
		String chPasswd = scanner.nextLine();
		
		return true;
	}
	
	

}