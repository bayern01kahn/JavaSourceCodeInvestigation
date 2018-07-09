package justin.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationMainTest {

	public static void main(String args[]) throws NoSuchMethodException, SecurityException, ClassNotFoundException{
		
		AnnotationMainTest amt = new AnnotationMainTest();
		
		amt.LoginAnnotationTest();
		amt.TestAnnotationTest();
	}
	
	public void LoginAnnotationTest() throws NoSuchMethodException, SecurityException {
		// 1.1通过反射获取info方法类
		Method method = AnnotationMainTest.class.getMethod("info");

		// 2.1判断该方法上是否存在@Login注释
		boolean annotationPresent = method.isAnnotationPresent(Login.class);
		if (annotationPresent) {
			System.out.println("info方法上存在@Login注释");
		} else {
			System.out.println("info方法上不存在@Login注释");
		}

		// 3.1获取方法上的所有注释
		Annotation[] annotations = method.getAnnotations();

		for (Annotation a : annotations) {
			// 如果是@Login注释，则强制转化，并调用username方法，和password方法。
			if (a != null && a instanceof Login) {
				String username = ((Login) a).username();
				String password = ((Login) a).password();
				System.out.println("username:" + username);
				System.out.println("password:" + password);
			}
			System.out.println(a);
		}
	}	
	
	@Login
	public void info(){
		
	}
	
	public void TestAnnotationTest() throws ClassNotFoundException{
		//1.1通过反射获取类
		Class<?> tClass = Class.forName("justin.annotation.TestAnnotationTest");
		
		//1.2获取该类自身声明的所有方法
		Method[] methods = tClass.getDeclaredMethods();
		
		int checkCount = 0; //测试的数量
        int uncheckCount = 0;  //未测试的数量
        
        for(Method method: methods){
        	if(method.isAnnotationPresent(Test.class)){
                checkCount++;
            }else{
                uncheckCount++;
            }
        }
        
        System.out.println("测试的方法有" + checkCount);
        System.out.println("未测试的方法有" + uncheckCount);
	}
}
