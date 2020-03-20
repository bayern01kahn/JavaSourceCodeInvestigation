package justin.designPattern.adapter.classAdapter;

public class SDCardClassAdapter extends SDCardAdaptee implements IMiniSDCardReader {

	@Override
	public void readMiniSD() {
		System.out.println("调用父类适配器的读取SD方法");
		super.readSDCard();
		
	}
}
