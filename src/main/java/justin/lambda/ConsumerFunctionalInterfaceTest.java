package justin.lambda;

import java.util.function.Consumer;

public class ConsumerFunctionalInterfaceTest {

	public static void donation(Integer money, Consumer<Integer> consumer){
	    consumer.accept(money);  
	}
	public static void main(String[] args) {
	    donation(1000, money -> System.out.println("好心的麦乐迪为Blade捐赠了"+money+"元")) ;
	}
}
