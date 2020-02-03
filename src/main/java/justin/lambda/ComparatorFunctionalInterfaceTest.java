package justin.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorFunctionalInterfaceTest {

	/**
	 * Comparator.naturalOrder()是一个自然排序的实现，这里可以自定义排序方案。
	 * 你经常看到使用Java8操作集合的时候可以直接foreach的原因也是在Iterable接口中也新增了一个默认方法：
	 * forEach，该方法功能和 for 循环类似，但是允许 用户使用一个Lambda表达式作为循环体。 
	 */
	
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(2, 7, 3, 1, 8, 6, 4);
		list.sort(Comparator.naturalOrder());
		System.out.println(list);
	}
}
