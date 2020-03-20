package justin.designPattern.observer.customImplementation;

public class User implements Observer {
	
	private String name;
	
	public User(String name){
		this.name = name;
	}

	@Override
	public void update(String message) {
		System.out.println(name+" 用户收到推送： " +message);
	}

}
