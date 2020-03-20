package justin.designPattern.proxy.staticProxy.extendsWay;

//通过直接继承的方式来完成静态代理
public class Agent extends FootballPlayer {

	@Override
	public void priceOffer() {
		System.out.println("【继承】经纪人，代理球员");
		super.priceOffer();
	}

	
}
