package wskim.java_dustmq.collection;

import java.util.HashSet;
import java.util.Iterator;

public class SetV1 {

	public static void main(String args[]) {
		HashSet<String> hs01 = new HashSet<String>();
		HashSet<String> hs02 = new HashSet<String>();
		
		// add() �޼ҵ带 �̿��� ����� ����
		hs01.add("ȫ�浿");
		hs01.add("�̼���");
		System.out.println(hs01.add("�Ӳ���")); // true
		System.out.println(hs01.add("�Ӳ���")); // false, �ߺ��� ����� ����
		for (String e : hs01) {
			System.out.print(e + " "); // ȫ�浿 �̼��� �Ӳ���
		}
		
		System.out.println();
		
		hs02.add("�Ӳ���");
		hs02.add("ȫ�浿");
		hs02.add("�̼���");
		Iterator<String> iter02 = hs02.iterator();
		while(iter02.hasNext()) {
			System.out.print(iter02.next() + " "); // ȫ�浿 �̼��� �Ӳ���
		}
		
		System.out.println("������ ũ�� : " + hs02.size()); // ������ ũ�� : 3
	}
}
