package justin.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/** 
 * @description HttpSessionListener监听器实现统计网站在线人数的功能 
 */
public class SessionListener implements HttpSessionListener {

	public static int TOTAL_ONLINE_USERS = 0;

	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
		TOTAL_ONLINE_USERS = (Integer) servletContext.getAttribute("TOTAL_ONLINE_USERS");
		// 如果用户退出，TOTAL_ONLINE_USERS自减1
		if (TOTAL_ONLINE_USERS == 0) {
			servletContext.setAttribute("TOTAL_ONLINE_USERS", 1);
		} else {
			TOTAL_ONLINE_USERS--;
			servletContext.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
		}
		System.out.println("用户退出，TOTAL_ONLINE_USERS自减1");
	}

	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		ServletContext servletContext = httpSessionEvent.getSession().getServletContext();
		TOTAL_ONLINE_USERS = (Integer) servletContext.getAttribute("TOTAL_ONLINE_USERS");
		// 如果用户登录，TOTAL_ONLINE_USERS自增1
		if (TOTAL_ONLINE_USERS == 0) {
			servletContext.setAttribute("TOTAL_ONLINE_USERS", 1);
		} else {
			TOTAL_ONLINE_USERS++;
			servletContext.setAttribute("TOTAL_ONLINE_USERS", TOTAL_ONLINE_USERS);
		}
		System.out.println("用户登录，TOTAL_ONLINE_USERS自增1");
	}
}
