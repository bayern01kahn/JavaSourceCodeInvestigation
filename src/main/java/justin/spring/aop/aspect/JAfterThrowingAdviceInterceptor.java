package justin.spring.aop.aspect;

import justin.spring.aop.intercept.JMethodInterceptor;
import justin.spring.aop.intercept.JMethodInvocation;

import java.lang.reflect.Method;

public class JAfterThrowingAdviceInterceptor extends JAbstractAspectAdvice implements JAdvice, JMethodInterceptor {


    private String throwingName;

    public JAfterThrowingAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(JMethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        }catch (Throwable e){
            invokeAdviceMethod(mi,null,e.getCause());
            throw e;
        }
    }

    public void setThrowName(String throwName){
        this.throwingName = throwName;
    }
}
