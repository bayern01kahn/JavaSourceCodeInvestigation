package justin.designPattern.chainOfResponsibility.handler;

public class VP extends Successor {

	@Override
	public void applyDiscount(float discount) {
		if(discount < 0.6){
			System.out.println("副总-可接受低于60%的折扣，则完成该订单");
		}else{
			successor.applyDiscount(discount);
		}
	}

}
