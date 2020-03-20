package justin.designPattern.chainOfResponsibility.handler;

public class Manager extends Successor {

	@Override
	public void applyDiscount(float discount) {
		if(discount < 0.4){
			System.out.println("经理-可接受低于40%的折扣，则完成该订单");
		}else{
			successor.applyDiscount(discount);
		}
	}

}
