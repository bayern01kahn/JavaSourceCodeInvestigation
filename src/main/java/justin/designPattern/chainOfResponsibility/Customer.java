package justin.designPattern.chainOfResponsibility;

import justin.designPattern.chainOfResponsibility.handler.Successor;

import java.util.Random;


public class Customer {
	
	//引入后继处理器，并给与依赖
	private Successor successor;
	public void setSuccessor(Successor successor) {
		this.successor = successor;
	}

	public void requestDiscount() {
		Random rand = new Random();
		float request = rand.nextFloat(); 
		System.out.println("客户提出:"+ request+" 的折扣要求");
		
		//开始使用后继处理器处理，这里开始走销售折扣处理流程
		successor.applyDiscount(request);
	}
}
