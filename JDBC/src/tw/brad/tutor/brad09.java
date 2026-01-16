package tw.brad.tutor;

import tw.brad.apis.BCrypt;

public class brad09 {

	public static void main(String[] args) {
		String passwd = "12345678";
		String hashPasswd = BCrypt.hashpw(passwd, BCrypt.gensalt());
		System.out.println(hashPasswd);
		String input = new String("12345678");
		if (BCrypt.checkpw(input, hashPasswd)) {
			System.out.println("OK");
		} else {
			System.out.println("XX");
		}

	}

}
