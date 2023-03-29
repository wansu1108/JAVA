package wskim.java_dustmq.exception;

import java.io.IOException;

public class ExceptionV1 {
	
	static void handlingException() {
		try {
			throw new Exception();
		} catch (Exception e) {
			System.out.println("호출된 메소드에서 예외가 처리됨");
		}
	}
	
	public static void main(String[] args) {
		try {
			String msg = "hello World";
			byte[] list = {'a', 'b', 'c'};
			System.out.write(list);
			System.out.println(msg);
		} catch (IOException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
		try {
			System.out.println(5 / 0);
		} catch (ArithmeticException e) {
			System.out.println("발생한 예외 정보 : " + e.getMessage());
		}
		
		try {
			handlingException();
		} catch (Exception e) {
			System.out.println("main() 메소드에서 예외가 처리됨!");
		}
	}
}
