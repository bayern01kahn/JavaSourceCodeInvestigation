package justin.designPattern.adapter.objectAdapter;


public class SDCardObjAdapter implements IMiniSDCardReader {

	// 直接关联被适配类
	private SDCardAdaptee sdCardAdaptee;
	
	// 可以通过构造函数传入具体需要适配的被适配类对象
	public SDCardObjAdapter(SDCardAdaptee sdCardAdaptee) {
		this.sdCardAdaptee = sdCardAdaptee;
	}

	@Override
	public void read() {
		// 这里是使用委托的方式完成特殊功能
		sdCardAdaptee.readSDCard();
	}

}

