package justin.spring.aop;

import justin.spring.aop.intercept.JMethodInvocation;
import justin.spring.aop.support.JAdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class JdkDynamicAopProxy implements JAopProxy, InvocationHandler {

    private JAdvisedSupport advised;

    public JdkDynamicAopProxy(JAdvisedSupport config){
        this.advised = config;
    }

    @Override
    public Object getProxy() {
        return getProxy(this.advised.getTargetClass().getClassLoader());
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader, this.advised.getTargetClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        List<Object> interceptorsAndDynamicMethodMatchers = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method,this.advised.getTargetClass());

        JMethodInvocation invocation = new JMethodInvocation(proxy,
                                                             this.advised.getTarget(),
                                                             method,
                                                             args,
                                                             this.advised.getTargetClass(),
                                                             interceptorsAndDynamicMethodMatchers);
        return invocation.proceed();
    }



}
