package justin.proxy.cglibExample.proxy4interface;

import justin.proxy.cglibExample.target.TestInterface;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * 代理没有实现类的接口
 * 方式: cglib通过二进制流生成一个动态class,该class继承被代理类以实现动态代理
 *
 * 那么就提供了一个代理无实现类的接口的可能。
 */
public class TestCGLib4Interface implements MethodInterceptor {

    public Object getInstance(Class claxx) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(claxx);  // 为代理对象设置父类，即指定目标类
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("增加了自己的方法");
        return method.getName();
    }

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "/Users/justin/work/GitHub/JavaSourceCodeInvestigation/src/main/java/justin/proxy/cglibExample/proxy4interface/cglib_class");


        TestCGLib4Interface testCGLib = new TestCGLib4Interface();
        TestInterface o = (TestInterface) testCGLib.getInstance(TestInterface.class);
        System.out.println(o.getHalloWorld());
    }
}
