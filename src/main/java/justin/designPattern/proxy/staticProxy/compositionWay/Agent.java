package justin.designPattern.proxy.staticProxy.compositionWay;



//通过组合的方式来完成静态代理
public class Agent implements IRequestSalaryOffer {

	private FootballPlayer fp;

	public Agent(FootballPlayer fp) {
		super();
		this.fp = fp;
	}

	@Override
	public void priceOffer() {
		System.out.println("【组合】经纪人，代理球员");
		fp.priceOffer();
	}

}
