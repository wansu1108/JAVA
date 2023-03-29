package wskim.java_dustmq.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import lombok.Data;

public class ApiStreamV2 {
	public static void main(String[] args) {
		Stream<String> stream3 = Stream.of("HTML", "CSS", "JAVA", "JAVASCRIPT");
		stream3.map(Student::new).forEach((o) -> System.out.println(o.toString()));
		
		String[] arr = {"I study hard", "You study JAVA", "I am hungry"};
		Stream<String> stream = Arrays.stream(arr);
		stream.flatMap(s -> Stream.of(s.split(" +"))).forEach(System.out::println);
		
		IntStream stream4 = IntStream.range(0, 10);
		IntStream stream5 = IntStream.range(0, 10);
		IntStream stream6 = IntStream.range(0, 10);
		
		stream4.skip(4).forEach(e-> System.out.print(e + " "));
		System.out.println();
		
		stream5.limit(5).forEach(e-> System.out.print(e + " "));
		System.out.println();
		
		stream6.skip(3).limit(5).forEach(e-> System.out.print(e + " "));
		System.out.println();
		
		Stream<String> stream8 = Stream.of("JAVA", "HTML", "JAVASCRIPT", "CSS");
		Stream<String> stream9 = Stream.of("JAVA", "HTML", "JAVASCRIPT", "CSS");
		
		stream8.sorted().forEach(e-> System.out.print(e + " "));
		stream9.sorted(Comparator.reverseOrder()).forEach(e-> System.out.print(e + " "));
		System.out.println();
		
		IntStream stream10 = IntStream.of(7, 5, 5, 2, 1, 2, 3, 5, 4, 6);
		stream10
			.peek(s -> System.out.println("원본 스트림 : " + s))
			.skip(2)
			.peek(s -> System.out.println("peek(2) 실행 후: " + s))
			.limit(5)
			.peek(s -> System.out.println("limit(5) 실행 후: " + s))
			.sorted()
			.peek(s -> System.out.println("sorted() 실행 후: " + s))
			.forEach(n -> System.out.println(n));
		
		Stream<String> stream11 = Stream.of("넷", "둘", "셋", "하나");
		Stream<String> stream12 = Stream.of("넷", "둘", "셋", "하나");
		
		Optional<String> result11 = stream11.reduce((s1,s2)-> s1 + "++" + s2);
		result11.ifPresent(System.out::println);
		
		String result12 = stream12.reduce("시작",(s1,s2)-> s1 + "++" + s2);
		System.out.println(result12);
		
		IntStream stream13 = IntStream.of(4, 2, 7, 3, 5, 1, 6);
		IntStream stream14 = IntStream.of(4, 2, 7, 3, 5, 1, 6);
		
		OptionalInt result13 = stream13.sorted().findFirst();
		System.out.println(result13.getAsInt());
		
		OptionalInt result14 = stream14.sorted().findAny();
		System.out.println(result14.getAsInt());
		
		Stream<String> stream15 = Stream.of("넷", "둘", "하나", "셋");
		List<String> list = stream15.collect(Collectors.toList());
		Iterator<String> iter = list.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		Stream<String> stream16 = Stream.of("HTML", "CSS", "JAVA", "PHP");
		
		Map<Boolean, List<String>> partition = stream16.collect(Collectors.partitioningBy(s-> s.length()%2 == 0));
		
		List<String> oddLengthList = partition.get(false);
		System.out.println(oddLengthList);
		
		List<String> evenLengthList = partition.get(true);
		System.out.println(evenLengthList);
		
		
	}
	
	@Data
	static class Student{
		private String name; // 학생이름
		private String subject; //과목
		
		Student(String subject){
			this.subject = subject;
		}
	}
}
