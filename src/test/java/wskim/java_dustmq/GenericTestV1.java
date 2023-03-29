package wskim.java_dustmq;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import wskim.java_dustmq.generic.MyArray;

/***
 * Generic 
 * - 자바에서 제네릭이란 "데이터의 타입을 일반화 한다" 라는것을 의미한다.
 * - 제네릭은 클래스나 메소드에서 사용할 "내부 데이터 타입을" 컴파일 시에 미리 지정하는 방법이다.
 * - 이렇게 컴파일 시에 미리 타입 검사(type check)를 수행하면 다음과 같은 장점을 가진다.
 * 
 * 
 */
public class GenericTestV1 {
	
	@Test
	@DisplayName("제네릭의 선언 및 생성")
	public void generictTest1() {
		MyArray<Integer> myArr1 = new MyArray<Integer>();	// 
		MyArray<Integer> myArr2 = new MyArray<>();			// JAVA SE 7부터 생성시 타입이 추정가능하면 생략가능
		
	}
	
	@Test
	@DisplayName("다형성")
	public void genericTest2() {
		AnimalList<LandAnimal> landAnimal = new AnimalList<>(); // JAVA SE7 부터 생략 가능
		
		landAnimal.add(new LandAnimal());
		landAnimal.add(new Cat());
		landAnimal.add(new Dog());
//		landAnimal.add(new Sparrow()); // 오류가 발생함.
		
		for(int i=0; i<landAnimal.size(); i++) {
			landAnimal.get(i).crying();
		}
	}
	
	class LandAnimal{public void crying() {System.out.println("육지동물");}}
	class Cat extends LandAnimal { public void crying() {System.out.println("냐옹냐옹");}}
	class Dog extends LandAnimal { public void crying() {System.out.println("멍멍");}}
	class Sparrow { public void crying() {System.out.println("쨱짹");}}
	
	class AnimalList<T extends LandAnimal> {
		ArrayList<T> al = new ArrayList<T>();
		
		void add(T animal) { al.add(animal); }
		T get(int index) { return al.get(index); }
		boolean remove(T animal) { return al.remove(animal); }
		int size() { return al.size(); }
	}
}
