package justin.spring.aop.aspect;

import justin.spring.aop.intercept.JMethodInterceptor;
import justin.spring.aop.intercept.JMethodInvocation;

import java.lang.reflect.Method;

public class JMethodBeforeAdviceInterceptor extends JAbstractAspectAdvice implements JAdvice, JMethodInterceptor {

    private JJoinPoint joinPoint;

    public JMethodBeforeAdviceInterceptor(Method aspectMethod, Object aspectTarget) {
        super(aspectMethod, aspectTarget);
    }

    private void before(Method method,Object[] args,Object target) throws Throwable{
        //传送了给织入参数
        //method.invoke(target);
        super.invokeAdviceMethod(this.joinPoint,null,null);
    }

    @Override
    public Object invoke(JMethodInvocation mi) throws Throwable {
        //从被织入的代码中才能拿到，JoinPoint
        this.joinPoint = mi;
        before(mi.getMethod(), mi.getArguments(), mi.getThis());
        return mi.proceed();
    }
}
