package justin.reflect;

import java.lang.reflect.Method;

public class CallPrivateMethodTest {

	public static void main(String[] args) throws Exception {
		Method method = PackageClazz.class.getDeclaredMethod("privilegedMethod", String.class, String.class);
		method.setAccessible(true);
		method.invoke(new PackageClazz(), "para1", "para2");
	}

}

class PackageClazz {
	@SuppressWarnings("unused")
	private void privilegedMethod(String invokerName, String adb) {
		System.out.println("---" + invokerName + "----" + adb);
	}
}
