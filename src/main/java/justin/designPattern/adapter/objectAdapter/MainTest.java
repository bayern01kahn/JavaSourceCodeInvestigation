package justin.designPattern.adapter.objectAdapter;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("《对象-适配器实例： 将MiniSD读卡器 适配为 可以读取SD Card的读卡器》");
		
		ConcreteMiniSDCardReader miniSDReader = new ConcreteMiniSDCardReader();
		miniSDReader.read();
		
		System.out.println("======使用转化适配器======");
		
		IMiniSDCardReader adapter = new SDCardObjAdapter(new SDCardAdaptee());
		adapter.read();
	}

}
