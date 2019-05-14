package justin.test.beanwrapperTest;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.PropertyValue;

public class BeanWrapperTest {
    public static void main(String[] args) {
        BeanWrapperTest swt = new BeanWrapperTest();

        User user=new User();
        //通过PropertyAccessorFactory将user对象封装成BeanWrapper
        BeanWrapper bw= PropertyAccessorFactory.forBeanPropertyAccess(user);
        //方式一：直接对属性值进行设置
        bw.setPropertyValue("userName", "张三");
        System.out.println(user.getUserName());


        //方式二：通过PropertyValue进行设置
        PropertyValue pv=new PropertyValue("userName","李四");
        bw.setPropertyValue(pv);


        System.out.println(user.getUserName());

        swt.test3();

    }

    public void test3(){
        User user=new User();
        BeanWrapper bw = new BeanWrapperImpl(user);
        bw.setPropertyValue("propertyName", "updateValue");//propertyName属性名称，updateValue属性值
        System.out.println(bw.getPropertyValue("propertyName"));
    }


}

//一个User类
