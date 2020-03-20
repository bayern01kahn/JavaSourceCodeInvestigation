package justin.designPattern.chainOfResponsibility.handler;

public class Director extends Successor {

	@Override
	public void applyDiscount(float discount) {
		if(discount < 0.5){
			System.out.println("总监-可接受低于50%的折扣，则完成该订单");
		}else{
			successor.applyDiscount(discount);
		}

	}

}
