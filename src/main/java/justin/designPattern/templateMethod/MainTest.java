package justin.designPattern.templateMethod;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("模板方法模式：");

		System.out.println("\n\n前锋训练方案");
		STTraining st = new STTraining();
		st.trainTemplate();
		
		
		System.out.println("\n\n中后卫训练方案");
		CBTraining cb = new CBTraining();
		cb.trainTemplate();
	}

}
