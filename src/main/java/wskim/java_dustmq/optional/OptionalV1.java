package wskim.java_dustmq.optional;

import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class OptionalV1 {

	public static void main(String[] args) {
		Optional<String> opt = Optional.of("�ڹ� Optional ��ü");
		System.out.println(opt.get());
		
		if(opt.isPresent()) {
			System.out.println(opt.get());
		}
		
		Optional<String> opt1 = Optional.empty(); //null�� �ʱ�ȭ ���ش�.
		
		System.out.println(opt1.orElse("�� Optional ��ü"));
		System.out.println(opt1.orElseGet(String::new));
		
		
		IntStream stream = IntStream.of(4, 2, 1, 3);
		OptionalInt result = stream.findFirst();
		
		System.out.println(result.getAsInt());
	}
}
