package justin.lambda;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {
	
	public static void beforeJava8Iteration(){
		List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		for (String feature : features) {
		    System.out.println(feature);
		}
	}
	
	public static void afterJava8Iteration(){
		// Java 8之后：
		List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
		features.forEach(n -> System.out.println(n));
		 
		// 使用Java 8的方法引用更方便，方法引用由::双冒号操作符标示，
		// 看起来像C++的作用域解析运算符
		features.forEach(System.out::println);
	}
	
	public static void mapTest(){
		// 不使用lambda表达式为每个订单加上12%的税
		List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
		for (Integer cost : costBeforeTax) {
		    double price = cost + .12*cost;
		    System.out.println(price);
		}
		 
		// 使用lambda表达式
		List<Integer> costBeforeTax2 = Arrays.asList(100, 200, 300, 400, 500);
		costBeforeTax2.stream().map((cost) -> cost + .12*cost).forEach(System.out::println);
	}
	
	public static void mapTest2(){
		// 将字符串换成大写并用逗号链接起来
		List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
		String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
		System.out.println(G7Countries);
	}
	
	public static void statistic(){
		//获取数字的个数、最小值、最大值、总和以及平均值
		List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
		IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Highest prime number in List : " + stats.getMax());
		System.out.println("Lowest prime number in List : " + stats.getMin());
		System.out.println("Sum of all prime numbers : " + stats.getSum());
		System.out.println("Average of all prime numbers : " + stats.getAverage());
	}
	
	
	public static void main(String[] args) {
		
		beforeJava8Iteration();
		System.out.println();
		afterJava8Iteration();
		System.out.println();
		mapTest();
		System.out.println();
		mapTest2();
		System.out.println();
		statistic();
	}
}
