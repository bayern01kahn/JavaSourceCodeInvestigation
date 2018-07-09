package justin.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;


public class CustomFilter implements javax.servlet.Filter {
	
	private static final String WHITLIST_KEY = "whitelist";
	private Set<String> whitelist;
	
    public void init(FilterConfig filterConfig) throws ServletException {
    	String whilteListString = filterConfig.getInitParameter(WHITLIST_KEY);
    	
    	if (!StringUtils.isEmpty(whilteListString)) {
    		String[] list = whilteListString.split(",");
    		whitelist = Collections.unmodifiableSet(new HashSet<String>(Arrays.asList(list)));

    		System.out.println("过滤器URL白名单：");
    		for (String passByUrl : whitelist) {
                System.out.println(passByUrl);
            }
    	}
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {
       
    	//获取URI，对比白名单，属于范围内，则正常执行，否则结束
    	String url = ((HttpServletRequest) servletRequest).getRequestURI();
    	
    	System.out.println("过滤器-进入过滤阶段：  URL: "+ url);
    	if(isPassByUrl(url)){
    		System.out.println("属于白名单URL，允许执行");
    		filterChain.doFilter(servletRequest, servletResponse);
    	}else{
    		System.out.println("非白名单URL，结束");
    		destroy();
    	}
    }
    
    private boolean isPassByUrl(String url) {
    	if (!CollectionUtils.isEmpty(whitelist)) {
    		for (String whiteListUrl : whitelist) {
                //System.out.println("whiteListUrl:" + whiteListUrl);
                //System.out.println("current url:" + url);
                //System.out.println("url.indexOf(whiteListUrl) > -1:" + (url.indexOf(whiteListUrl) > -1));
    			if (url.indexOf(whiteListUrl) > -1) {
    				return true;
    			}
    		}
    	}
    	return false;
    }

	public void destroy() {
		
		System.out.println("过滤器-摧毁阶段");
	}
}
