package justin.designPattern.observer.jdk;

public class JDKObserverTest {

	public static void main(String args[]){
		
		User userA = new User("A");
		User userB = new User("B");
		User userC = new User("C");
		
		WechatAccount wa = new WechatAccount();
		
		wa.addObserver(userA);
		wa.addObserver(userB);
		wa.addObserver(userC);
		
		
		wa.sendMessage("如何写一个JDK自带的观察者模式示例");
	}
}
