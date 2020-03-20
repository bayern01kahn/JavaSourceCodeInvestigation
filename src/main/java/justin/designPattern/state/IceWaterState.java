package justin.designPattern.state;

public class IceWaterState implements IState {

	@Override
	public void doSth() {
		System.out.println("状态为：冰水");
	}

}
