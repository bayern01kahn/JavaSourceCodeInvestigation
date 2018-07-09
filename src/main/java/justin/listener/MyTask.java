package justin.listener;

import java.util.List;  
import java.util.ListIterator;  
import java.util.TimerTask;  
import javax.servlet.http.HttpSession;  
/** 
 * 定时器，定义定时任务的具体内容 
 */  
public class MyTask extends TimerTask{  
    private List<HttpSession> list;  
    // 存储传递过来的锁  
    private Object lock;  
    // 构造方法  
    MyTask(List<HttpSession> list, Object lock){  
        this.list = list;  
        this.lock = lock;  
    }  
    @Override  
    public void run() {  
        // 考虑到多线程的情况，这里必须要同步  
        synchronized (lock){  
            System.out.println("定时器开始执行...");  
            ListIterator<HttpSession> listIterator = list.listIterator();  
            while(listIterator.hasNext()){  
                HttpSession httpSession = listIterator.next();  
                // httpSession.getLastAccessedTime() = session的最后访问时间  
                if(System.currentTimeMillis() - httpSession.getLastAccessedTime() > 1000*30){  
                    // 手动销毁session  
                    httpSession.invalidate();  
                    // 从集合中移除已经被销毁的session  
                    listIterator.remove();  
                }  
            }  
        }  
    }  
}  