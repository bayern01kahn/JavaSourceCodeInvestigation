package justin.designPattern.state;

public class WarmWaterState implements IState {

	@Override
	public void doSth() {
		System.out.println("状态为：温水");
	}

}
