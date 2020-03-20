package justin.designPattern.adapter.classAdapter;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("《类适配器实例： 将MiniSD读卡器 适配为 可以读取SD Card的读卡器》");
		
		ConcreteMiniSDCardReader miniSDReader = new ConcreteMiniSDCardReader();
		miniSDReader.readMiniSD();
		
		System.out.println("======使用转化适配器======");
		
		IMiniSDCardReader adapter = new SDCardClassAdapter();
		adapter.readMiniSD();
	}

}
