package justin.designPattern.proxy.staticProxy.compositionWay;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("通过组合的方式实现静态代理：");
		
		//普通做法
		FootballPlayer fp = new FootballPlayer();
		//fp.priceOffer();
		
		//经纪人来做
		Agent a = new Agent(fp);
		a.priceOffer();
	}

}
