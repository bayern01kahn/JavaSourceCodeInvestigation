package justin.spring.aop;

public interface JAopProxy {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
