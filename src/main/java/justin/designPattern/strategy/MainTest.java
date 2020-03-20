package justin.designPattern.strategy;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IShotWay shotWay = new LongRangeShot();
		shotWay.shot();
		
		IShotWay shotWay2 = new LowShot();
		shotWay2.shot();
		
		IShotWay shotWay3 = new Thump();
		shotWay3.shot();
		
		
		System.out.println("\n\n");
		
		FootballPlayer ballack = new Ballack();
		ballack.play();
		
		FootballPlayer makaay = new Makaay();
		makaay.play();
		
	}

}
