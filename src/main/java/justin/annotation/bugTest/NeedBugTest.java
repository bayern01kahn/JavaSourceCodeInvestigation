package justin.annotation.bugTest;

public class NeedBugTest {

	@Check
	public void number() {
		System.out.println("1234567890");
	}

	@Check
	public void add() {
		System.out.println("1+1=" + 1+1);
	}

	@Check
	public void subtraction() {
		System.out.println("1-1=" + (1-1));
	}

	@Check
	public void multiplication() {
		System.out.println("3 x 5=" + 3*5);
	}

	@Check
	public void division() {
		System.out.println("6 / 0=" + 6/0);
	}

	public void intro() {
		System.out.println("我写的程序没有 bug!");
	}

}
