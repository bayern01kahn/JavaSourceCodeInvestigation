package justin.designPattern.chainOfResponsibility.handler;

public class CEO extends Successor {

	@Override
	public void applyDiscount(float discount) {
		if(discount < 0.8){
			System.out.println("Boss-可接受低于80%的折扣，则完成该订单");
		}else{
			System.out.println("要求太高，无法完成，不做这单生意");
		}
	}
}
