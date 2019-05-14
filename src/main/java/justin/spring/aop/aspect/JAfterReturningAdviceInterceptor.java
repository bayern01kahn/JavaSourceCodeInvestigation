package justin.spring.aop.aspect;

import justin.spring.aop.intercept.JMethodInterceptor;
import justin.spring.aop.intercept.JMethodInvocation;

import java.lang.reflect.Method;

public class JAfterReturningAdviceInterceptor extends JAbstractAspectAdvice implements JAdvice, JMethodInterceptor {

    private JJoinPoint joinPoint;

    public JAfterReturningAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    @Override
    public Object invoke(JMethodInvocation mi) throws Throwable {
        Object retVal = mi.proceed();
        this.joinPoint = mi;
        this.afterReturning(retVal,mi.getMethod(),mi.getArguments(),mi.getThis());
        return retVal;
    }

    private void afterReturning(Object retVal, Method method, Object[] arguments, Object aThis) throws Throwable {
        super.invokeAdviceMethod(this.joinPoint,retVal,null);
    }
}
