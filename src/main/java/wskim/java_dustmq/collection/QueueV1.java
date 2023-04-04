package wskim.java_dustmq.collection;

import java.util.LinkedList;

public class QueueV1 {
	
	public static void main(String args[]) {
		// Queue
		LinkedList<String> qu = new LinkedList<>();
		
		qu.add("��");
		qu.add("��");
		qu.add("��");
		qu.add("�ϳ�");
		
		// ���� ���� ����� ����� ��ȯ
		System.out.println(qu.peek()); // �� , ť�� ����� ��Ұ� ������ null ��ȯ
		System.out.println(qu.element()); // �� , ť�� ����� ��Ұ� ������ ���� �߻� (NoSuchElementException)
		System.out.println(qu); // �� �� �� �ϳ�
		
		// poll() �޼ҵ带 �̿��� ����� ����, ���� ���� ����� ����� ����
		System.out.println(qu.poll()); // ��
		System.out.println(qu); // �� �� �ϳ�
		
		// remove() �޼ҵ带 �̿��� ����� ����
		qu.remove("�ϳ�");
		System.out.println(qu); // �� ��
	}
}
