package justin.lambda;

import java.util.function.Function;

public class FunctionFunctionalInterfaceTest {
	
	public static Integer convert(String str, Function<String, Integer> function) {
	    return function.apply(str);
	}
	public static void main(String[] args) {
	    Integer value = convert("28", x -> Integer.parseInt(x));
	    System.out.println(value.getClass().getTypeName());
	}
}
