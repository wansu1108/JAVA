package wskim.java_dustmq.exception;

import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

public class ExceptionV2 {
	
	static class NotFoundMemberException extends RuntimeException{
		public NotFoundMemberException(String msg) {
			super(msg);
		}
	}
	
	@Data
	@AllArgsConstructor
	static class Member {
		private String username;
		private String nickname;
	}
	
	public static void main(String[] args) {
		Member member1 = new Member("홍길동","활빈당");
		Member member2 = new Member("이성계","위화도회군");
		List<Member> members = Arrays.asList(member1, member2);
		
		try {
			Member findMember = members
					.stream()
					.filter((o)-> o.getUsername().equals("홍길동1"))
					.findFirst()
					.orElseThrow(()-> new NotFoundMemberException("find Member Error"));
			
			System.out.println("nickname : " + findMember.getNickname());
			
		} catch (NotFoundMemberException e) {
			System.out.println("occured Error : " + e.getMessage());
		}
		
	}
	
}
