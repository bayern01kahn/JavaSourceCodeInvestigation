package justin.designPattern.chainOfResponsibility;

public class MainTest {
	
	public static void main(String args[]) {
		
		//责任链模式：模拟用户购买商品时，提出折扣需求，然后销售团队通过责任链的形式处理用户折扣
		
		Customer c = new Customer();
		
		//使用静态工厂方法建立完成的 折扣销售责任处理链，并指向用户的后继处理器
		c.setSuccessor(HandlerFactory.createHandlerFactory());
		
		//用户提出折扣需求
		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
//		c.requestDiscount();
	}


}
