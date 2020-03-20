package justin.designPattern.strategy;

public abstract class FootballPlayer {

	private IShotWay shotStragety;
	public void setShotStragety(IShotWay shotStragety) {
		this.shotStragety = shotStragety;
	}	
	
	public void run(){
		System.out.println("跑动");
	}
	
	public void shot(){
		shotStragety.shot();
	}
	
	public void play(){
		run();
		shot();
	}
}



