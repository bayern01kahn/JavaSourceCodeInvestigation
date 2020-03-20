package justin.designPattern.state;

public class MainTest {

	public static void main(String[] args) {
        IState iWaterState;
        WaterContext waterContext = new WaterContext();
        //模拟状态改变
        for (int i = 0; i < 3; i++) {
            waterContext.setState(i);
            iWaterState = waterContext.getState();
            System.out.println("i=" + i);
            iWaterState.doSth();
        }
    }
}
