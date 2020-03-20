package justin.designPattern.facade;

public class AgentFacade {
	
	public SalarySubSystemClass salary;
	public TaxSubSystemClass tax;
	public TransferSubSystemClass transfer;

	public AgentFacade(){
		salary = new SalarySubSystemClass();
		tax = new TaxSubSystemClass();
		transfer = new TransferSubSystemClass();
	}
	
	public void delegateAllPlayerThing(){
		salary.wageDemands();
		tax.payDuty();
		transfer.transferRequest();
	}
}
