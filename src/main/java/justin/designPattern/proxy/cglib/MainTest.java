package justin.designPattern.proxy.cglib;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("通过CGLIB直接实现动态代理：（被代理的类没有实现任何接口）");
		
		//普通做法
		FootballPlayer fp = new FootballPlayer();
		//fp.priceOffer();
		
		//经纪人来做
		FootballPlayer rso = (FootballPlayer) new Agent(fp).getProxyInstance();
		rso.priceOffer();
	}

}
