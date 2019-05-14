package justin.spring.context;

import justin.spring.annotation.JAutowired;
import justin.spring.annotation.JController;
import justin.spring.annotation.JService;
import justin.spring.aop.JAopProxy;
import justin.spring.aop.JCglibAopProxy;
import justin.spring.aop.JdkDynamicAopProxy;
import justin.spring.aop.config.JAopConfig;
import justin.spring.aop.support.JAdvisedSupport;
import justin.spring.beans.config.JBeanPostProcessor;
import justin.spring.core.JBeanFactory;
import justin.spring.beans.JBeanWrapper;
import justin.spring.beans.support.JBeanDefinitionReader;
import justin.spring.beans.support.JDefaultListableBeanFactory;
import justin.spring.beans.config.JBeanDefinition;

import java.util.Properties;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class JApplicationContext extends JDefaultListableBeanFactory implements JBeanFactory {


    private String [] configLoactions;

    private JBeanDefinitionReader reader;

    //单例的IOC容器缓存
    private Map<String,Object> factoryBeanObjectCache = new ConcurrentHashMap<String, Object>();

    //所有的/总的/通用的IOC容器
    private Map<String,JBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<String, JBeanWrapper>();

    public JApplicationContext(String ... configLoactions) {
        this.configLoactions = configLoactions;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception{
        //1.定位配置文件
        reader = new JBeanDefinitionReader(this.configLoactions);

        //2.加载配置文件,扫描相关的类,把他们封装成BeanDefinition,
        List<JBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();

        //3、注册，把配置信息放到容器里面(伪IOC容器)
        doRegisterBeanDefinition(beanDefinitions);

        //4、把不是延时加载的类，有提前初始化
        doAutowrited();

    }



    private void doRegisterBeanDefinition(List<JBeanDefinition> beanDefinitions) throws Exception {

        for (JBeanDefinition beanDefinition: beanDefinitions) {
            if(super.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new Exception("The “" + beanDefinition.getFactoryBeanName() + "” is exists!!");
            }
            super.beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
        }
        //到这里为止，容器初始化完毕
    }

    //只处理非延时加载的情况
    private void doAutowrited() {
        for (Map.Entry<String, JBeanDefinition> beanDefinitionEntry : super.beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            if(!beanDefinitionEntry.getValue().isLazyInit()) {  //判断是否为延迟加载
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //真正的 DI[依赖注入] 操作 是从这里开始的

    //依赖注入，从这里开始，通过读取BeanDefinition中的信息
    //然后，通过反射机制创建一个实例并返回
    //Spring做法是，不会把最原始的对象放出去，会用一个BeanWrapper来进行一次包装
    //装饰器模式：
    //1、保留原来的OOP关系
    //2、我需要对它进行扩展，增强（为了以后AOP打基础）

    @Override
    public Object getBean(String beanName) throws Exception {

        //*** 先初始化再注入 是因为 避免 循环依赖

        JBeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        Object instance = null;

        //这个逻辑还不严谨，自己可以去参考Spring源码
        //工厂模式 + 策略模式
        //?
        //JBeanPostProcessor postProcessor = new JBeanPostProcessor();
        //postProcessor.postProcessBeforeInitialization(instance,beanName);

        //1.初始化
        instance = initBean(beanName, beanDefinition);

        //2.把这个对象封装到BeanWrapper中
        JBeanWrapper beanWrapper = new JBeanWrapper(instance);

        //3.拿到BeanWrapper之后, 需要把他保存到IOC容器中
        this.factoryBeanInstanceCache.put(beanName, beanWrapper);


        //?
        //postProcessor.postProcessAfterInitialization(instance,beanName);

        //4.注入
        populateBean(beanName, new JBeanDefinition(), beanWrapper);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }


    private Object initBean(String beanName, JBeanDefinition jBeanDefinition) {
        //1、拿到要实例化的对象的类名
        String className = jBeanDefinition.getBeanClassName();

        //2、反射实例化，得到一个对象
        Object instance = null;
        try {
            //jBeanDefinition.getFactoryBeanName()
            //假设默认就是单例,细节暂且不考虑，先把主线拉通
            if(this.factoryBeanObjectCache.containsKey(className)){
                instance = this.factoryBeanObjectCache.get(className);
            }else {
                Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();

                JAdvisedSupport config = initAopConfig(jBeanDefinition);
                config.setTargetClass(clazz);
                config.setTarget(instance);

                //符合PointCut的规则的话，闯将代理对象
                if(config.pointCutMatch()) {
                    instance = createProxy(config).getProxy();
                }

                this.factoryBeanObjectCache.put(className,instance);
                this.factoryBeanObjectCache.put(jBeanDefinition.getFactoryBeanName(),instance);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return instance;
    }

    private JAopProxy createProxy(JAdvisedSupport config) {

        Class targetClass = config.getTargetClass();
        if(targetClass.getInterfaces().length > 0){
            return new JdkDynamicAopProxy(config);
        }
        return new JCglibAopProxy(config);
    }

    private JAdvisedSupport initAopConfig(JBeanDefinition jBeanDefinition) {
            JAopConfig config = new JAopConfig();
            config.setPointCut(this.reader.getConfig().getProperty("pointCut"));
            config.setAspectClass(this.reader.getConfig().getProperty("aspectClass"));
            config.setAspectBefore(this.reader.getConfig().getProperty("aspectBefore"));
            config.setAspectAfter(this.reader.getConfig().getProperty("aspectAfter"));
            config.setAspectAfterThrow(this.reader.getConfig().getProperty("aspectAfterThrow"));
            config.setAspectAfterThrowingName(this.reader.getConfig().getProperty("aspectAfterThrowingName"));
            return new JAdvisedSupport(config);
    }


    private void populateBean(String beanName, JBeanDefinition jBeanDefinition, JBeanWrapper beanWrapper) {
        Object instance = beanWrapper.getWrappedInstance();

        Class<?> clazz = beanWrapper.getWrappedClass();
        //判断只有加了注解的类，才执行依赖注入
        if(!(clazz.isAnnotationPresent(JController.class) || clazz.isAnnotationPresent(JService.class))){
            return;
        }

        //获得所有的fields
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if(!field.isAnnotationPresent(JAutowired.class)){ continue;}

            JAutowired autowired = field.getAnnotation(JAutowired.class);

            String autowiredBeanName =  autowired.value().trim();
            if("".equals(autowiredBeanName)){
                autowiredBeanName = field.getType().getName();
            }

            //强制访问
            field.setAccessible(true);

            try {
                //为什么会为NULL，先留个坑
                if(this.factoryBeanInstanceCache.get(autowiredBeanName) == null){ continue; }
//                if(instance == null){
//                    continue;
//                }
                field.set(instance,this.factoryBeanInstanceCache.get(autowiredBeanName).getWrappedInstance());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }


    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new  String[this.beanDefinitionMap.size()]);
    }

    public Properties getConfig(){
        return this.reader.getConfig();
    }

}
