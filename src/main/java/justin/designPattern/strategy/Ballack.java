package justin.designPattern.strategy;

public class Ballack extends FootballPlayer {

	public Ballack() {
		super();
		super.setShotStragety(new LongRangeShot());
	}

	@Override
	public void run() {
		System.out.println("大范围跑动 Box To Box");
	}
}
