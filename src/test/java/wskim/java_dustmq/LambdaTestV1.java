package wskim.java_dustmq;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class LambdaTestV1 {
	
	// ����ǥ����
	// 1. ������ ���� �޼ҵ带 �ϳ��� ������ ǥ���Ѱ��̴�.
	// - �޼ҵ�    : int min(int x, int y) { return x < y ? x : y; }
	// - ����ǥ���� : (x,y) -> x < y ? x:y;
	
	// �޼ҵ带 ���� ǥ�������� ǥ���ϸ�, Ŭ������ �ۼ��ϰ� ��ü�� �������� �ʾƵ� �޼ҵ带 ����� �� �ֽ��ϴ�.
	// Ŭ������ ����� ���ÿ� ��ü�� �����ϹǷ�, �� �ϳ��� ��ü���� ������ �� �ִ� Ŭ������ �͸� Ŭ������� �մϴ�
	// ���� ǥ������ �͸� Ŭ������ ���ٰ� �� �� �ִ�.
	
	// �̷��� ���� ǥ������ �޼ҵ��� �Ű������� ���޵� ���� ������, �޼ҵ��� �ᱣ������ ��ȯ�� ���� �ֽ��ϴ�.
	// ���� ���� ǥ������ ����ϸ�, ������ ���ʿ��� �ڵ带 �ٿ��ְ�, �ۼ��� �ڵ��� �������� �����ݴϴ�.
	
	//
	@Test
	void TestV1() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("�������� ����� ��ȸ�� ������ ����");
			}
		}).start();
		
		new Thread(()->System.out.println("���� ǥ������ ����� ��ȸ�� ������ ����")).start();
		new Thread(()->System.out.println()).start();
	}
	
	@FunctionalInterface
	interface Calc { // �Լ��� �������̽�
		public int min(int x, int y);
	}
	
	@Test
	void TestV2() {
		Calc minNum = (x,y)-> x > y ? x : y;  // �߻� �޼ҵ��� ����
		System.out.println(minNum.min(3, 4)); // �Լ��� �������̽� ���
	}
	
	// �޼ҵ� ����
	// 1. ���� ǥ������ ���ϳ��� �޼ҵ常�� ȣ���ϴ� ��� 
	// 2. �ش� ���� ǥ���Ŀ��� ���ʿ��� �Ű������� �����ϰ� ����� �� �ֵ��� �����ش�.
	@Test
	void TestV3() {
		DoubleUnaryOperator oper;
		oper = (n) -> Math.abs(n); // ���� ǥ����
		System.out.println(oper.applyAsDouble(-5));
		
		oper = Math::abs; // �޼ҵ� ���� , ���ʿ��� �Ű����� n�� ����.
		System.out.println(oper.applyAsDouble(-5));
	}
	
	// ������ ����
	// 1. �����ڸ� ȣ���ϴ� ���� ǥ���ĵ� �ռ� ���캻 �޼ҵ� ������ �̿��� �� �ִ�.
	// 2. �ܼ��� ��ü�� �����ϰ� ��ȯ�ϴ� ���� ǥ������ ������ ������ ��ȯ�� �� �ִ�.
	// - (a) -> { return new Object(a); }
	// - Object::new;
	// �ش� �����ڰ� �������� ������ ������ �� ������ �߻��Ѵ�.
	@Test
	void TestV4() {
		Function<Integer, double[]> func1 = t -> new double[t]; // ���� ǥ����
		Function<Integer, double[]> func2 = double[]::new;		// ������ ����
	}
	
//	@Test
//	void TestV5() {
//		List<String> names = Arrays.asList("�谩��" , "�谩��");
//		
//		// Dog class�� ������ �Ű����� String
//		// map�� ���� ���޹��� �Ű����� 1��
//		// ���� ǥ���� -> ������ ����
//		List<Dog> dog1 = names.stream().map(x-> new Dog(x)).collect(Collectors.toList());
//		List<Dog> dog2 = names.stream().map(Dog::new).collect(Collectors.toList());
//	}
//	
//	@Getter @Setter
//	class Dog {
//		String name;
//		String species;
//		
//		public Dog(String name) {
//			this.name = name;
//		}
//	}
}
