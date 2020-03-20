package justin.designPattern.factory.abstractFactory;

/** 
 * 抽象工厂模式 
 * 【创建多个产品的等级结构，多个抽象产品类】
 * 仅仅是工厂方法的复杂化，保存了多个new 大工程才用的上
 * @author justin
 */
public interface IMidFieldFootballPlayer {

	void coreTraining();
	void assistTraining();
}
