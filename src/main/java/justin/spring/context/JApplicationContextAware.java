package justin.spring.context;


/**
 * 通过解耦的方式获取IOC容易的顶层设计
 * 后面将通过哟i个监听器去扫描所有的类,只要实现了这个类将自动调用 setApplicationContext 从而将IOC容器注入 到目标类中
 */
public interface JApplicationContextAware {

    void setApplicationContext(JApplicationContext applicationContext);
}
