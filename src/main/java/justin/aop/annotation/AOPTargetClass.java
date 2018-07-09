package justin.aop.annotation;

public class AOPTargetClass {
	
	private String para1;
	
	public String getPara1() {
		return para1;
	}

	public void setPara1(String para1) {
		this.para1 = para1;
	}
	

	public void add() {
		setPara1("parameter-value");
		System.out.println("AOPTargetClass add()");
	}

	public boolean delete() {
		System.out.println("AOPTargetClass delete()");
		return true;
	}

	public void edit() {
		System.out.println("AOPTargetClass edit()");
		int i = 5 / 0;
	}


}
