package justin.designPattern.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class User implements Observer{

	private String name;
	
	public User(String name){
		this.name = name;
	}
	
	
	public void getWechatPushMessage(Object arg){
		System.out.println(name + "获取推送文章: " + arg.toString());
	}


	@Override
	public void update(Observable o, Object arg) {

		getWechatPushMessage(arg);
	}
}