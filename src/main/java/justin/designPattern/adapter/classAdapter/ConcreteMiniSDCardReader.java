package justin.designPattern.adapter.classAdapter;

public class ConcreteMiniSDCardReader implements IMiniSDCardReader {

	@Override
	public void readMiniSD() {
		System.out.println("读取 Mini SD Card");
	}
}
