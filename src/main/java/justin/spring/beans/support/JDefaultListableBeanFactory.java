package justin.spring.beans.support;

import justin.spring.context.support.JAbstractApplicationContext;
import justin.spring.beans.config.JBeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JDefaultListableBeanFactory extends JAbstractApplicationContext {

    //存储注册信息的BeanDefinition  伪IOC容器
    protected final Map<String, JBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

}
