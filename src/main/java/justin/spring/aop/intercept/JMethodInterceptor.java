package justin.spring.aop.intercept;

public interface JMethodInterceptor {
    Object invoke(JMethodInvocation invocation) throws Throwable;
}
