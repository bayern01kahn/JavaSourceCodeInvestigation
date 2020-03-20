package justin.designPattern.templateMethod;

public abstract class AFootballPlayerTraining {

	//模板方法必须是final的
	public final void trainTemplate(){
		run();
		pass();
		shot();
		
		if(doDribbleTrainingOrNot()){   //子类可以选择是否使用（挂钩），这里就是对应是否加其他东西
        	dribble();
        }
	}

	//钩子方法-提供一个默认或空的实现
    //具体的子类可以自行决定是否挂钩或者如何挂钩
    protected boolean doDribbleTrainingOrNot() {
        return false;
    }
	
	public void run(){
		System.out.println("跑步训练");
	}
	
	public abstract void pass();
	
	public abstract void shot();
	
	public abstract void dribble();
}
