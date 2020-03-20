package justin.designPattern.chainOfResponsibility.handler;

public class Lead extends Successor {

	@Override
	public void applyDiscount(float discount) {
		if(discount < 0.3){
			System.out.println("销售组长-可接受低于30%的折扣，则完成该订单");
		}else{
			successor.applyDiscount(discount);
		}

	}

}
