package justin.interceptor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import justin.respose.JsonResponse;


//1.被拦截的对象，他要做常规操作
//2.这时 我们有个拦截器对象，要在他做常规操作之前进行拦截，然后执行一Pre()方法，然后让他进行常规操作，再执行 After()方法。

@Service
@Path("/justin/interceptor-service")
public class InterceptorSerivice {

	@Autowired
	private DynamicProxyHandler dph; //用jdk自带的InvocationHandler 动态代理工具
	
	@Autowired
	public InterceptedTarget interceptorTarget;  //被拦截对象
	
	@GET
	@Path("/getTest")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public JsonResponse getTest(){
		JsonResponse response = new JsonResponse();
		
		System.out.println("\n拦截器服务类测试-开始");
		
		//用动态代理工具 绑定被拦截对象，
		InterceptedTarget itProxy= (InterceptedTarget) dph.bind(interceptorTarget);
		//执行常规操作
		itProxy.doSth();
		
		System.out.println("拦截器服务类测试-完成");
		
		response.setMessage("拦截器测试成功");
		response.setStatus(JsonResponse.SUCCESS);
		return response;
	}
}
