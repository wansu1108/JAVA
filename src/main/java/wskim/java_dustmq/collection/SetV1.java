package wskim.java_dustmq.collection;

import java.util.HashSet;
import java.util.Iterator;

public class SetV1 {

	public static void main(String args[]) {
		HashSet<String> hs01 = new HashSet<String>();
		HashSet<String> hs02 = new HashSet<String>();
		
		// add() 메소드를 이용한 요소의 저장
		hs01.add("홍길동");
		hs01.add("이순신");
		System.out.println(hs01.add("임꺽정")); // true
		System.out.println(hs01.add("임꺽정")); // false, 중복된 요소의 저장
		for (String e : hs01) {
			System.out.print(e + " "); // 홍길동 이순신 임꺽정
		}
		
		System.out.println();
		
		hs02.add("임꺽정");
		hs02.add("홍길동");
		hs02.add("이순신");
		Iterator<String> iter02 = hs02.iterator();
		while(iter02.hasNext()) {
			System.out.print(iter02.next() + " "); // 홍길동 이순신 임꺽정
		}
		
		System.out.println("집합의 크기 : " + hs02.size()); // 집합의 크기 : 3
	}
}
