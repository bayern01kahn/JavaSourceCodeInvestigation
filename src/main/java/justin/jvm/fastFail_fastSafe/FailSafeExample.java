package justin.jvm.fastFail_fastSafe;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public class FailSafeExample {

	 public static void main(String[] args)  
	    {  
	        ConcurrentHashMap<String,String> premiumPhone =   
	                               new ConcurrentHashMap<String,String>();  
	        premiumPhone.put("Apple", "iPhone");  
	        premiumPhone.put("HTC", "HTC one");  
	        premiumPhone.put("Samsung","S5");  
	          
	        Iterator iterator = premiumPhone.keySet().iterator();  
	          
	        while (iterator.hasNext())  
	        {  
	            System.out.println(premiumPhone.get(iterator.next()));  
	            premiumPhone.put("Sony", "Xperia Z");   
	        }  
	          
	    }  
	 
	 // 不会把Sony 加入到Map中 也不会抛出异常
}
