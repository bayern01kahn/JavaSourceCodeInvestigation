package justin.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitDataListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent event) {
		
		System.out.println("Spring容器自定义监听器InitDataListener初始化");
		
		// TODO Auto-generated method stub
		WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());  
		TestBean tb = (TestBean) context.getBean("testBean");  
        System.out.println("获取上下文中的bean的信息: "+ tb.getName()); 
        
        
        // 获取项目跟路径
        try{  
            String basePath = event.getServletContext().getRealPath("/");  
            System.out.println("获取项目跟路径 :" + basePath);  
            if (!(basePath.endsWith(File.separator))){  
                basePath = basePath + File.separator;  
            }  
            basePath = basePath + "WEB-INF" + File.separator + "classes" + File.separator;  
            System.out.println("获取项目编译路径 :" + basePath);  
        }  
        catch (Exception e){  
            e.printStackTrace();  
            System.exit(-1);  
        }  
	}

}
