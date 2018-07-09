package justin.aop.base;

public class AspectPointLog {

	public void LogBefore() {
		System.out.println("Log before method");
	}

	public void LogAfter() {
		System.out.println("Log after method");
	}
}
