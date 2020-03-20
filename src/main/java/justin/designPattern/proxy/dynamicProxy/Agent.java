package justin.designPattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class Agent implements InvocationHandler {
	
	private Object target;

	public Agent(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println("【动态代理】经纪人，代理球员");
		method.invoke(target);
		
		return null;
	}

}
