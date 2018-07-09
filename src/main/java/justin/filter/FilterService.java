package justin.filter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import justin.respose.JsonResponse;

@Service
@Path("/justin/filter-service")
public class FilterService {
	
	@GET
	@Path("/getFilterWhiteListTest")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public JsonResponse getFilterWhiteListTest(){
		JsonResponse response = new JsonResponse();
		
		System.out.println("\n过滤器服务类测试-进入白名单过滤测试");
		System.out.println("过滤器服务类测试-完成");
		
		response.setMessage("过滤器测试成功");
		response.setStatus(JsonResponse.SUCCESS);
		return response;
	}
	
	@GET
	@Path("/getFilterBlackListTest")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
	public JsonResponse getFilterBlackListTest(){
		JsonResponse response = new JsonResponse();
		
		System.out.println("\n过滤器服务类测试-进入黑名单过滤测试");
		System.out.println("过滤器服务类测试-完成");
		
		response.setMessage("过滤器测试成功");
		response.setStatus(JsonResponse.SUCCESS);
		return response;
	}

}
