package justin.designPattern.factory;


import justin.designPattern.factory.abstractFactory.DefenseMidfieldFactory;
import justin.designPattern.factory.abstractFactory.OffenseMidfieldFactory;
import justin.designPattern.factory.factoryMethod.AFootballPlayer;
import justin.designPattern.factory.factoryMethod.CBFactory;
import justin.designPattern.factory.factoryMethod.ForwardFactory;
import justin.designPattern.factory.factoryMethod.MidfieldFactory;
import justin.designPattern.factory.simpleFactory.SimpleFactory;

public class MainTest {

	public static void main(String[] args) {
		
		System.out.println("工厂模式：三种不同得工厂模式的实现实例");

		System.out.println("=> 简单工厂模式");
		SimpleFactory sf = new SimpleFactory();
		sf.productFootballPlayer("CB");
		sf.productFootballPlayer("LB");
		sf.productFootballPlayer("RB");
		
		
		System.out.println("\n\n=> 工厂方法模式");
		AFootballPlayer bfp = new CBFactory();
		bfp.productPlayer();
		AFootballPlayer mfp = new MidfieldFactory();
		mfp.productPlayer();
		AFootballPlayer ffp = new ForwardFactory();
		ffp.productPlayer();
		
		
		System.out.println("\n\n=> 抽象工厂模式");
		DefenseMidfieldFactory dmf = new DefenseMidfieldFactory();
		dmf.training();
		OffenseMidfieldFactory omf = new OffenseMidfieldFactory();
		omf.training();
		
	}

}
