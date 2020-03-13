package justin.proxy.cglibExample.proxy4class;

import justin.proxy.cglibExample.target.TestInterface;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class TestCGLib4Class implements MethodInterceptor {

    public Object getInstance(Class claxx) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(claxx);
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        methodProxy.invokeSuper(o,objects);
        return method.getName();
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/justin/work/GitHub/JavaSourceCodeInvestigation/src/main/java/justin/proxy/cglibExample/proxy4class/cglib_class");

        TestCGLib4Class testCGLib = new TestCGLib4Class();
        TestInterface o = (TestInterface) testCGLib.getInstance(TestInterfaceImpl.class);
        System.out.println(o.getHalloWorld());
    }
}
