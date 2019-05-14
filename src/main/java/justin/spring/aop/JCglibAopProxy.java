package justin.spring.aop;

import justin.spring.aop.support.JAdvisedSupport;

public class JCglibAopProxy implements JAopProxy {
    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(ClassLoader classLoader) {
        return null;
    }

    public JCglibAopProxy(JAdvisedSupport config) {
    }
}
