package justin.designPattern.state;

public class WaterContext {

	public IState state;

	public IState getState() {
		return state;
	}

	public void setState(int i) {
		if (i == 0) {
			state = new IceWaterState();
			return;
		}

		if (i == 1) {
			state = new WarmWaterState();
			return;
		}

		if (i == 2) {
			state = new BoilingWaterState();
			return;
		}
	}

}
