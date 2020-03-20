package justin.designPattern.adapter.objectAdapter;

public class ConcreteMiniSDCardReader implements IMiniSDCardReader {

	@Override
	public void read() {
		System.out.println("读取 Mini SD Card");
	}
}
