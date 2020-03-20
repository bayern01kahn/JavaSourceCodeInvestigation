package justin.designPattern.strategy;

public class Makaay extends FootballPlayer {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("前场迂回寻找机会");
	}

	public Makaay() {
		super();
		super.setShotStragety(new LowShot());
	}

	
}
