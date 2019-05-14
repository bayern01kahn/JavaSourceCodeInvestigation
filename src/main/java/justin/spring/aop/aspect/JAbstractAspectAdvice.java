package justin.spring.aop.aspect;

import org.aspectj.lang.JoinPoint;

import java.lang.reflect.Method;

public class JAbstractAspectAdvice  implements JAdvice {

    private Method aspectMethod;
    private Object aspectTarget;

    public JAbstractAspectAdvice(Method aspectMethod, Object aspectTarget) {
        this.aspectMethod = aspectMethod;
        this.aspectTarget = aspectTarget;
    }


    public Object invokeAdviceMethod(JJoinPoint joinPoint, Object returnValue, Throwable tx) throws Throwable{

        Class<?> [] paramTypes = this.aspectMethod.getParameterTypes();

        if(null == paramTypes || paramTypes.length == 0){
            return this.aspectMethod.invoke(aspectTarget);
        }else{
            Object [] args = new Object[paramTypes.length];
            for (int i = 0; i < paramTypes.length; i ++) {
                if(paramTypes[i] == JJoinPoint.class){
                    args[i] = joinPoint;
                }else if(paramTypes[i] == Throwable.class){
                    args[i] = tx;
                }else if(paramTypes[i] == Object.class){
                    args[i] = returnValue;
                }
            }
            return this.aspectMethod.invoke(aspectTarget,args);
        }
    }

}
