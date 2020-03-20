package justin.designPattern.observer.customImplementation;

public class CustomImplementationObserverTest {

	public static void main(String[] args) {

		User u1 = new User("u1");
		User u2 = new User("u2");
		User u3 = new User("u3");
		User justin = new User("Justin");
		
		WechatOfficialAccount woa = new WechatOfficialAccount();
		
		woa.addObserver(u1);
		woa.addObserver(u2);
		woa.addObserver(u3);
		woa.addObserver(justin);
		
		String article = "如何自己实现一个观察者模式示例";
		
		System.out.println("全推送");
		woa.sendAllMessage(article);
		
		woa.removeObserver(u3);
		System.out.println("\n移除u3 再全推送");
		woa.sendAllMessage(article);
		
		System.out.println("\n单独推送给 justin");
		woa.sendDirectMessage(justin, article);
	}

}
