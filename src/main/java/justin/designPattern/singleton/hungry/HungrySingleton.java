package justin.designPattern.singleton.hungry;

public class HungrySingleton {
	//私有化构造函数
	private HungrySingleton(){}

	private static HungrySingleton hs = new HungrySingleton();
	private static Integer number = 7;
	
	
	public static HungrySingleton getHungrySingleton(){
		System.out.println(number);
		return hs;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		HungrySingleton.number = number;
	}
	
}
