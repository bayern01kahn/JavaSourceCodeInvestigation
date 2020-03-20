package justin.designPattern.proxy.dynamicProxy;

import java.lang.reflect.Proxy;

public class MainTest {

	public static void main(String[] args) {

		System.out.println("通过jdk自带的代理功能实现动态代理：");
		
		//普通做法
		FootballPlayer fp = new FootballPlayer();
		//fp.priceOffer();
		
		//经纪人来做
		Agent invocationHandlerAgent  = new Agent(fp);
		Class<?> cls = fp.getClass();
		
		/**
		 * loader 类加载器        
		 * interfaces 实现接口    
		 * invocationHandler 实际接口实现类
		 * newProxyInstance(loader,interfaces,invocationHandler)
		*/
		IRequestSalaryOffer rso = (IRequestSalaryOffer) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), invocationHandlerAgent);
		rso.priceOffer();
	}

}
