package justin.designPattern.facade;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("外观/门面模式:  模拟 经纪人帮球员处理各种业务\n\n");
		
		AgentFacade agent = new AgentFacade();
		agent.delegateAllPlayerThing();
		
	}

}
	