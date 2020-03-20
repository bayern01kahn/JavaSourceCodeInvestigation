package justin.designPattern.chainOfResponsibility.handler;

public class Sales extends Successor {

	@Override
	public void applyDiscount(float discount) {
		if(discount < 0.2){
			System.out.println("销售-可接受低于20%的折扣，则销售完成笔订单");
		}else{
			successor.applyDiscount(discount);
		}
	}

}
