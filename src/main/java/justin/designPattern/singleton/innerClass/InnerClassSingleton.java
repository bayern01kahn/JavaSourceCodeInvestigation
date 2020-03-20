package justin.designPattern.singleton.innerClass;

/**
 * 推荐使用1
 */
public class InnerClassSingleton {

	private InnerClassSingleton(){}
	
	private static class SingletonHelp {
        static InnerClassSingleton instance = new InnerClassSingleton();
	}

	public static InnerClassSingleton getInstance() {
	        return SingletonHelp.instance;
	}
}
