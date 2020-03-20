package justin.designPattern.decorator;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("装饰器模式：模拟 球员训练课。 在不修改前锋训练和中后卫训练方式的基础上 加入 其他的加练项，并完成 综合训练时间的总和计算");
		
		AFootballTrainingCourseComponent Lewandowski = new STTrainingComponent();
		System.out.println("\n\n"+ Lewandowski.getContent() + "=总时间: " + Lewandowski.trainingTime() + "h");
		
		Lewandowski = new ShotTrainingDecorator(Lewandowski);
		System.out.println(Lewandowski.getContent() + "=总时间: " + Lewandowski.trainingTime() + "h");
		Lewandowski = new SpeedTrainingDecorator(Lewandowski);
		System.out.println(Lewandowski.getContent() + "=总时间: " + Lewandowski.trainingTime() + "h");

		
		AFootballTrainingCourseComponent Boateng = new CBTrainingComponent();
		System.out.println("\n\n"+ Boateng.getContent() + "=总时间: " + Boateng.trainingTime() + "h");
		
		Boateng = new PhysicalTrainingDecorator(Boateng);
		System.out.println(Boateng.getContent() + "=总时间: " + Boateng.trainingTime() + "h");
		Boateng = new SpeedTrainingDecorator(Boateng);
		System.out.println(Boateng.getContent() + "=总时间: " + Boateng.trainingTime() + "h");
		Boateng = new PowerTrainingDecorator(Boateng);
		System.out.println(Boateng.getContent() + "=总时间: " + Boateng.trainingTime() + "h");
		
	}

}
