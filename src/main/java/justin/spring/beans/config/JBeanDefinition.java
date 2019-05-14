package justin.spring.beans.config;


import lombok.Data;

@Data
public class JBeanDefinition {

    private String beanClassName;
    boolean lazyInit = false;
    private String factoryBeanName;
}
