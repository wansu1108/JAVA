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
		Member member1 = new Member("ȫ�浿","Ȱ���");
		Member member2 = new Member("�̼���","��ȭ��ȸ��");
		List<Member> members = Arrays.asList(member1, member2);
		
		try {
			Member findMember = members
					.stream()
					.filter((o)-> o.getUsername().equals("ȫ�浿1"))
					.findFirst()
					.orElseThrow(()-> new NotFoundMemberException("find Member Error"));
			
			System.out.println("nickname : " + findMember.getNickname());
			
		} catch (NotFoundMemberException e) {
			System.out.println("occured Error : " + e.getMessage());
		}
		
	}
	
}
