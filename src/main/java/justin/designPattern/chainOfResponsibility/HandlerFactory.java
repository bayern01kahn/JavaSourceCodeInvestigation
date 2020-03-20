package justin.designPattern.chainOfResponsibility;


import justin.designPattern.chainOfResponsibility.handler.CEO;
import justin.designPattern.chainOfResponsibility.handler.Director;
import justin.designPattern.chainOfResponsibility.handler.Lead;
import justin.designPattern.chainOfResponsibility.handler.Manager;
import justin.designPattern.chainOfResponsibility.handler.Sales;
import justin.designPattern.chainOfResponsibility.handler.VP;

public class HandlerFactory {

	public static Sales createHandlerFactory(){
		
		Sales sales = new Sales();
		Lead lead = new Lead();
		Manager mgr = new Manager();
		Director director = new Director();
		VP vp = new VP();
		CEO ceo = new CEO();
		
		sales.setSuccessor(lead);
		lead.setSuccessor(mgr);
		mgr.setSuccessor(director);
		director.setSuccessor(vp);
		vp.setSuccessor(ceo);
		
		return sales;
	}
}
