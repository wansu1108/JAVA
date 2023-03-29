package wskim.java_dustmq;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class LambdaTestV1 {
	
	// 람다표현식
	// 1. 간단히 말해 메소드를 하나의 식으로 표현한것이다.
	// - 메소드    : int min(int x, int y) { return x < y ? x : y; }
	// - 람다표현식 : (x,y) -> x < y ? x:y;
	
	// 메소드를 람다 표현식으로 표현하면, 클래스를 작성하고 객체를 생성하지 않아도 메소드를 사용할 수 있습니다.
	// 클래스의 선언과 동시에 객체를 생성하므로, 단 하나의 객체만을 생성할 수 있는 클래스를 익명 클래스라고 합니다
	// 람다 표현식은 익명 클래스와 같다고 할 수 있다.
	
	// 이러한 람다 표현식은 메소드의 매개변수로 전달될 수도 있으며, 메소드의 결괏값으로 반환될 수도 있습니다.
	// 따라서 람다 표현식을 사용하면, 기존의 불필요한 코드를 줄여주고, 작성된 코드의 가독성을 높여줍니다.
	
	//
	@Test
	void TestV1() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("전통적인 방식의 일회용 스레드 생성");
			}
		}).start();
		
		new Thread(()->System.out.println("람다 표현식을 사용한 일회성 스레드 생성")).start();
		new Thread(()->System.out.println()).start();
	}
	
	@FunctionalInterface
	interface Calc { // 함수형 인터페이스
		public int min(int x, int y);
	}
	
	@Test
	void TestV2() {
		Calc minNum = (x,y)-> x > y ? x : y;  // 추상 메소드의 구현
		System.out.println(minNum.min(3, 4)); // 함수형 인터페이스 사용
	}
	
	// 메소드 참조
	// 1. 람다 표현식이 단하나의 메소드만을 호출하는 경우 
	// 2. 해당 람다 표현식에서 불필요한 매개변수를 제거하고 사용할 수 있도록 도와준다.
	@Test
	void TestV3() {
		DoubleUnaryOperator oper;
		oper = (n) -> Math.abs(n); // 람다 표현식
		System.out.println(oper.applyAsDouble(-5));
		
		oper = Math::abs; // 메소드 참조 , 불필요한 매개변수 n을 제거.
		System.out.println(oper.applyAsDouble(-5));
	}
	
	// 생성자 참조
	// 1. 생성자를 호출하는 람다 표현식도 앞서 살펴본 메소드 참조를 이용할 수 있다.
	// 2. 단순히 객체를 생성하고 반환하는 람다 표현식은 생성자 참조로 변환할 수 있다.
	// - (a) -> { return new Object(a); }
	// - Object::new;
	// 해당 생성자가 존재하지 않으면 컴파일 시 오류가 발생한다.
	@Test
	void TestV4() {
		Function<Integer, double[]> func1 = t -> new double[t]; // 람다 표현식
		Function<Integer, double[]> func2 = double[]::new;		// 생성자 참조
	}
	
//	@Test
//	void TestV5() {
//		List<String> names = Arrays.asList("김갑순" , "김갑돌");
//		
//		// Dog class의 생성자 매개변수 String
//		// map을 통해 전달받은 매개변수 1개
//		// 람다 표현식 -> 생성자 참조
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
