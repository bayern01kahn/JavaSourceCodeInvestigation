package justin.designPattern.factory.simpleFactory;

public class SimpleFactory {
	
	
	/**
	 * 简单工厂模式 
	 * 把对象的创建放到一个工厂类中，通过参数来创建不同的对象。
	 * 【switch case  if eles】
	 *   违背了**开闭原则**
	 * @param position
	 */
	public void productFootballPlayer(String position){
		switch (position){
			case "CB": System.out.println("培训中后卫球员");break;
			case "LB": System.out.println("培训左后卫球员");break;
			case "RB": System.out.println("培训右后卫球员");break;
			case "MID": System.out.println("培训中场球员");break;
			case "FORWARD": System.out.println("培训前场球员");break;
			default:
			    System.out.println(0);
		}
	}
}
