package justin.designPattern.proxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class Agent implements MethodInterceptor {

	//维护目标对象
    private Object target;
	
	public Agent(Object target) {
		super();
		this.target = target;
	}
	
	//给目标对象创建一个代理对象
    public Object getProxyInstance(){
        //1.工具类
        Enhancer en = new Enhancer();
        //2.设置父类
        en.setSuperclass(target.getClass());
        //3.设置回调函数
        en.setCallback(this);
        //4.创建子类(代理对象)
        return en.create();
    }

	@Override
	public Object intercept(Object obj, Method method, Object[] arg2, MethodProxy arg3) throws Throwable {

		System.out.println("【CGLIB动态代理】经纪人，代理球员");
		return method.invoke(target,arg2);
	}
}
