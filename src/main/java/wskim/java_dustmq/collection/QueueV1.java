package wskim.java_dustmq.collection;

import java.util.LinkedList;

public class QueueV1 {
	
	public static void main(String args[]) {
		// Queue
		LinkedList<String> qu = new LinkedList<>();
		
		qu.add("넷");
		qu.add("둘");
		qu.add("셋");
		qu.add("하나");
		
		// 가장 먼저 저장된 요소의 반환
		System.out.println(qu.peek()); // 넷 , 큐에 저장된 요소가 없으면 null 반환
		System.out.println(qu.element()); // 넷 , 큐에 저장된 요소가 없으면 에러 발생 (NoSuchElementException)
		System.out.println(qu); // 넷 둘 셋 하나
		
		// poll() 메소드를 이용한 요소의 제거, 제일 먼저 저장된 요소의 제거
		System.out.println(qu.poll()); // 넷
		System.out.println(qu); // 셋 둘 하나
		
		// remove() 메소드를 이용한 요소의 제거
		qu.remove("하나");
		System.out.println(qu); // 셋 둘
	}
}
