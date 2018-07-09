package justin.listener;

import java.util.LinkedList;  
import java.util.List;  
import java.util.Timer;  
import javax.servlet.ServletContextEvent;  
import javax.servlet.ServletContextListener;  
import javax.servlet.http.HttpSession;  
import javax.servlet.http.HttpSessionEvent;  
import javax.servlet.http.HttpSessionListener;  
import java.util.Collections;
/** 
 * @description 当网站用户量增加时，session占用的内存会越来越大，这时session的管理，将会是一项很大的 
 * 系统开销，为了高效的管理session，我们可以写一个监听器，定期清理掉过期的session 
 */  
public class SessionScanerListener implements HttpSessionListener,ServletContextListener{  
    // 创建一个线程安全的集合，用来存储session  
    @SuppressWarnings("unchecked")  
    List<HttpSession> sessionList = Collections.synchronizedList(new LinkedList<HttpSession>());  
    private Object lock = new Object();  
      
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {  
        System.out.println("session 创建成功...");  
        HttpSession httpSession = httpSessionEvent.getSession();  
        synchronized (lock){  
            sessionList.add(httpSession);  
        }  
    }  
  
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {  
        System.out.println("session 销毁成功...");  
    }  
    // web应用关闭时触发contextDestroyed事件  
    public void contextDestroyed(ServletContextEvent servletContextEvent) {  
        System.out.println("web应用关闭...");  
    }  
  
    // web应用启动时触发contextInitialized事件  
    public void contextInitialized(ServletContextEvent servletContextEvent) {  
        System.out.println("web应用初始化...");  
        // 创建定时器  
        Timer timer = new Timer();  
        // 每隔30秒就定时执行任务  
        timer.schedule(new MyTask(sessionList,lock), 0, 1000*30);  
    }  
}  