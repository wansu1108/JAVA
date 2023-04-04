package wskim.java_dustmq.collection;

import java.util.Iterator;
import java.util.TreeSet;

public class TreeSetV1 {
	
	public static void main(String args[]) {
		// ���ĵ� ���·� ����Ǵ� ���� �˻� Ʈ��(binary search tree)�� ���·� ��Ҹ� ����
		TreeSet<Integer> ts = new TreeSet<Integer>();
		
		ts.add(30);
		ts.add(40);
		ts.add(20);
		ts.add(10);
		
		for(int e : ts) {
			System.out.println(e + " "); // 10 20 30 40
		}
		
		ts.remove(40);
		
		Iterator<Integer> iter = ts.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next() + " "); // 10 20 30
		}
		
		System.out.println("���� �˻� Ʈ���� ũ�� : " + ts.size()); // 3
		
		System.out.println(ts.subSet(10, 20)); // 10
		System.out.println(ts.subSet(10, true, 20, true));  // 10 20
		System.out.println(ts.subSet(10, true, 20, false)); // 10
		System.out.println(ts.subSet(10, false, 20, true)); // 20
	}
}
