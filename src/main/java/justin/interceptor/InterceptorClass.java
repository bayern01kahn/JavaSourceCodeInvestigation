package justin.interceptor;

import org.springframework.stereotype.Component;

@Component
public class InterceptorClass {
	
	public void before(){
        System.out.println("在拦截器 InterceptorClass 中调用方法: before()");
    }

    public void after(){
        System.out.println("在拦截器 InterceptorClass 中调用方法: after()");
    }
}
