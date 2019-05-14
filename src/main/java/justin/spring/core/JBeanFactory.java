package justin.spring.core;


/**
 * 单例工厂的顶层设计
 */
public interface JBeanFactory {

    /**
     * 根据beanName 从IOC容器获取 Bean对象实例
     * @param beanName
     * @return
     */
    Object getBean(String beanName) throws Exception;
}
