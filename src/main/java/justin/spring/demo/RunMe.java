package justin.spring.demo;

import justin.spring.context.JApplicationContext;
import justin.spring.demo.action.MyAction;
import justin.util.HttpUtil;

public class RunMe {


    public static void main(String[] args) throws Exception {
        RunMe r = new RunMe();

        JApplicationContext context = new JApplicationContext("classpath:application.properties");
        r.Ioc_DI_test(context);

        //r.MVC_test();

    }

    public  void Ioc_DI_test(JApplicationContext context) throws Exception {
        System.out.println(context);
        //context.getBean(MyAction.class.getSimpleName());
        System.out.println(MyAction.class.isInstance(context.getBean("myAction")));
    }

    public void MVC_test(){
        try {
            HttpUtil.httpClientGet("localhost/web/query.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
