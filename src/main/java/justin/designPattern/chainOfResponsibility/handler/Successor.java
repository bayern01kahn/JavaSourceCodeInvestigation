package justin.designPattern.chainOfResponsibility.handler;

public abstract class Successor {

	//一个后继处理器
	protected Successor successor;
	
	//获取后继处理器的方法
	public void setSuccessor(Successor successor) {
		this.successor = successor;
	}

    //公共部分
	//具体的请求处理方法（这个方法需要在每个具体处理对象中实现），这里定义为抽象方法。
	public abstract void applyDiscount(float discount);
}
