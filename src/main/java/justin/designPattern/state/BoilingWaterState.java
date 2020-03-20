package justin.designPattern.state;

public class BoilingWaterState implements IState {

	@Override
	public void doSth() {
		System.out.println("状态为：开水");
	}

}
