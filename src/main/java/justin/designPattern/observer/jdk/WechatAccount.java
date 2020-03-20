package justin.designPattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class WechatAccount  extends Observable {

	String message;
	
	public void sendMessage(String message) {
		System.out.println("微信公众号，推送了一篇文章：" + message);
		
		setChanged();
        notifyObservers(message); //拉模式
	}

}
