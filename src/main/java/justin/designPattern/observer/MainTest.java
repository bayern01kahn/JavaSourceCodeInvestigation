package justin.designPattern.observer;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("观察者模式：利用jdk自带的接口实现");
		
		System.out.println("\n\n经纪人关注球员");
		
		FootballPlayer fp = new FootballPlayer();
		fp.setLevel(1);
		fp.setName("托利索");
		
		Agent agent = new Agent();
		agent.setName("经纪人A");
		
		//经纪人对球员进行关注
		fp.addObserver(agent);
		fp.setLevel(2);
		fp.modifyPrice(fp);
		
		System.out.println("\n\n多个俱乐部关注球员");

		FootballPlayer sanchez = new FootballPlayer();
		sanchez.setLevel(3);
		sanchez.setName("桑切斯");

		Club bayern =new Club();
		bayern.setName("Bayern");
		Club arsenal =new Club();
		arsenal.setName("Arsenal");
		Club manchester =new Club();
		manchester.setName("Manchester");
		
		//加入俱乐部对桑切斯的关注
		sanchez.addObserver(arsenal);
		sanchez.addObserver(bayern);
		sanchez.addObserver(manchester);
		
		//评级发生变化
		sanchez.setLevel(5);
		sanchez.modifyPrice(sanchez);
	}

}
