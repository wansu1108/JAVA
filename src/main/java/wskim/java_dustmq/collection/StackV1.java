package wskim.java_dustmq.collection;

import java.util.Stack;

public class StackV1 {

	public static void main(String args[]) {
		// Stack�� ���Լ���(LIFO)
		Stack<Integer> st = new Stack<>();
		
		st.push(4);
		st.push(2);
		st.push(3);
		st.push(1);
		
		// ������ ���� ��ܿ�(���� ���������� �����)�ִ� ��� ��ȯ
		System.out.println(st.peek()); // 1
		System.out.println(st); // 4 2 3 1
		
		// ������ ���� ��ܿ�(���� ���������� �����)�ִ� ��� ��ȯ �� ���ÿ��� ����
		System.out.println(st.pop()); // 1
		System.out.println(st); // 4 2 3
		
		// �ش� ��Ұ� �����ϴ� ��ġ ��ȯ
		System.out.println(st.search(4)); // 3
		System.out.println(st.search(3)); // 1
	}
}
