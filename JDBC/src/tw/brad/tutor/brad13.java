package tw.brad.tutor;

import java.util.List;

import tw.brad.apis.Gift;
import tw.brad.apis.JdbcTool;
import tw.brad.apis.Member;

public class brad13 {

	public static void main(String[] args) {
		JdbcTool tool = new JdbcTool();
		String sql = """
				SELECT email, name 
				FROM member
				""";
		
		List<Member> gifts = tool.query(sql, rs -> {
			Member member = new Member();
			member.setName(rs.getString("name"));
			member.setEmail(rs.getString("email"));
			return member;
		});
		
		
		gifts.forEach(System.out::println);
		
		
	}

}
