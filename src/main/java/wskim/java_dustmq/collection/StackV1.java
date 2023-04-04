package wskim.java_dustmq.collection;

import java.util.Stack;

public class StackV1 {

	public static void main(String args[]) {
		// Stack은 후입선출(LIFO)
		Stack<Integer> st = new Stack<>();
		
		st.push(4);
		st.push(2);
		st.push(3);
		st.push(1);
		
		// 스택의 제일 상단에(가장 마지막으로 저장된)있는 요소 반환
		System.out.println(st.peek()); // 1
		System.out.println(st); // 4 2 3 1
		
		// 스택의 제일 상단에(가장 마지막으로 저장된)있는 요소 반환 및 스택에서 제거
		System.out.println(st.pop()); // 1
		System.out.println(st); // 4 2 3
		
		// 해당 요소가 존재하는 위치 반환
		System.out.println(st.search(4)); // 3
		System.out.println(st.search(3)); // 1
	}
}
