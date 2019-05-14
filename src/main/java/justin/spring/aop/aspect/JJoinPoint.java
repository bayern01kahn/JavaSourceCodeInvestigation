package justin.spring.aop.aspect;

import java.lang.reflect.Method;

public interface JJoinPoint {

    Object getThis();

    Object[] getArguments();

    Method getMethod();

    void setUserAttribute(String key, Object value);

    Object getUserAttribute(String key);
}
