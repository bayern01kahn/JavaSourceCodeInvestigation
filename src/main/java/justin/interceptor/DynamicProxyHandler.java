package justin.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DynamicProxyHandler implements InvocationHandler {
	
	@Autowired
	public InterceptorClass ic;//拦截器实例
	
	public Object target; //被代理对象
	
	// 动态生成一个代理类对象，并绑定被代理类和代理处理器。
	public Object bind(Object target){
		
		this.target = target;
		
		/**
         * Proxy.newProxyInstance(参数1, 参数2, 参数3)
         *
         * 参数1, 表示被代理类的 ClassLoader
         * 参数2, 表示被代理的接口
         * 参数3, 表示代理处理器对象
         *
         * 该方法，返回代理实例
         */

		ClassLoader targetClassLoader = target.getClass().getClassLoader();
		Class<?>[] targetInterface = target.getClass().getInterfaces();
		
		return Proxy.newProxyInstance(targetClassLoader, targetInterface, this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		ic.before();//拦截器执行 pre
		
		//核心方法
		method.invoke(target, args);
		
		ic.after();//拦截器 执行 after
		
		return null;
	}

}
