package wskim.java_dustmq.lambda;

import java.util.function.DoubleUnaryOperator;
import java.util.function.Function;

public class LambdaV1 {

	public static void main(String[] args) {
		DoubleUnaryOperator oper;
		oper = operand -> Math.abs(operand);
		System.out.println(oper.applyAsDouble(-5)); //5
		
		oper = Math::abs;
		System.out.println(oper.applyAsDouble(-5)); //5
		
		Function<Integer, double[]> func1 = a -> new double[a]; // 람다 표현식
		Function<Integer, double[]> func2 = double[]::new;      // 생성자 참조
	}
}
