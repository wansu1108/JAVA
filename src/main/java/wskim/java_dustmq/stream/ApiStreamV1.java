package wskim.java_dustmq.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ApiStreamV1 {
	public static void main(String[] args) {
		/**
		 * 1. Collection
		 */
		ArrayList<Integer> list = new ArrayList<>();
		
		list.add(4);
		list.add(2);
		list.add(3);
		list.add(1);
		
		Stream<Integer> stream = list.stream(); //컬렉션에서 스트림 생성
		stream.forEach(System.out::print);	// forEach() 메소드를 이용한 스트림 요소의 순차 접근
		System.out.println();
		/***
		 * 2. Arrays
		 */
		String[] arr = new String[] {"넷","둘","셋","하나"};
		
		// 배열에서 스트림 생성
		Stream<String> stream1 = Arrays.stream(arr);
		stream1.forEach(e -> System.out.print(e + " "));
		System.out.println();
		
		// 배열의 특정 부분만을 이용한 스트림 생성
		Stream<String> stream2 = Arrays.stream(arr, 1,3);
		stream2.forEach(e -> System.out.print(e + " "));
		
		/***
		 * 3. 가변 매개변수
		 */
		System.out.println();
		Stream<Double> stream3 = Stream.of(4.2,2.5,3.1,1.9);
		stream3.forEach(e -> System.out.print(e + " "));
		
		/***
		 * 4. 지정된 범위의 연속된 정수
		 */
		System.out.println();
		
		IntStream stream4 = IntStream.range(1, 4);
		stream4.forEach(e -> System.out.print(e + " ")); // 1 2 3 
		System.out.println();
		
		IntStream stream5 = IntStream.rangeClosed(1, 4);
		stream5.forEach(e -> System.out.print(e + " ")); // 1 2 3 4
		System.out.println();
		
		/***
		 * 5. 특정 타입의 난수
		 */
		IntStream stream6 = new Random().ints(4);
		stream6.forEach(e -> System.out.println(e));
		
		Stream<Integer> stream7 = Stream.iterate(2, n-> n+2).limit(5); // 2, 4, 6, 8, 10
		stream7.forEach(e -> System.out.print(e + " "));	
		
		System.out.println();
		
		Stream<Integer> stream8 = Stream.generate(()->2).limit(5); // 2, 2, 2, 2, 2
		stream8.forEach(e -> System.out.print(e + " "));
		System.out.println();
		
		Stream<Object> stream9 = Stream.empty();
		System.out.println(stream9.count());
	}
}
