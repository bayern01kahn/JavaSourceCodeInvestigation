package justin.interceptor;

import org.springframework.stereotype.Component;

@Component
public class InterceptedImplementationClass implements InterceptedTarget {

	public void doSth() {
		// TODO Auto-generated method stub
		System.out.println("在被拦截对象中，进行常规操作");
	}

}
