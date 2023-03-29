package wskim.java_dustmq;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wskim.java_dustmq.generic.MyArray;

/***
 * Generic 
 * - �ڹٿ��� ���׸��̶� "�������� Ÿ���� �Ϲ�ȭ �Ѵ�" ��°��� �ǹ��Ѵ�.
 * - ���׸��� Ŭ������ �޼ҵ忡�� ����� "���� ������ Ÿ����" ������ �ÿ� �̸� �����ϴ� ����̴�.
 * - �̷��� ������ �ÿ� �̸� Ÿ�� �˻�(type check)�� �����ϸ� ������ ���� ������ ������.
 * 
 * 
 */
public class GenericTestV1 {
	
	@Test
	@DisplayName("���׸��� ���� �� ����")
	public void generictTest1() {
		MyArray<Integer> myArr1 = new MyArray<Integer>();	// 
		MyArray<Integer> myArr2 = new MyArray<>();			// JAVA SE 7���� ������ Ÿ���� ���������ϸ� ��������
		
	}
	
	@Test
	@DisplayName("������")
	public void genericTest2() {
		AnimalList<LandAnimal> landAnimal = new AnimalList<>(); // JAVA SE7 ���� ���� ����
		
		landAnimal.add(new LandAnimal());
		landAnimal.add(new Cat());
		landAnimal.add(new Dog());
//		landAnimal.add(new Sparrow()); // ������ �߻���.
		
		for(int i=0; i<landAnimal.size(); i++) {
			landAnimal.get(i).crying();
		}
	}
	
	class LandAnimal{public void crying() {System.out.println("��������");}}
	class Cat extends LandAnimal { public void crying() {System.out.println("�Ŀ˳Ŀ�");}}
	class Dog extends LandAnimal { public void crying() {System.out.println("�۸�");}}
	class Sparrow { public void crying() {System.out.println("��±");}}
	
	class AnimalList<T extends LandAnimal> {
		ArrayList<T> al = new ArrayList<T>();
		
		void add(T animal) { al.add(animal); }
		T get(int index) { return al.get(index); }
		boolean remove(T animal) { return al.remove(animal); }
		int size() { return al.size(); }
	}
}
