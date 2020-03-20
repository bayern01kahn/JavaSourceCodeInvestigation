package justin.designPattern.adapter.interfaceAdapter;

public class MainTest {

	/**
	 * 接口适配器使用场景：
	 * （1）想要使用接口中的某个或某些方法，但是接口中有太多方法，我们要使用时必须实现接口并实现其中的所有方法，可以使用抽象类来实现接口，并不对方法进行实现（仅置空），然后我们再继承这个抽象类来通过重写想用的方法的方式来实现。
	 * 这个抽象类就是适配器。
	 */
	public static void main(String[] args) {

		System.out.println("《接口-适配器实例： 只想适配接口中的部分方法 ，不用全部实现接口中所有方法");
		System.out.println("======使用转化适配器======");

		Common adapter = new ConcreteAdapter();
		adapter.a();
		adapter.b();
	}
}
