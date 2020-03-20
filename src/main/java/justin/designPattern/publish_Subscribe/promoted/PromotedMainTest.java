package justin.designPattern.publish_Subscribe.promoted;

import justin.designPattern.publish_Subscribe.base.Echo;
import justin.designPattern.publish_Subscribe.base.Quit;
import justin.designPattern.publish_Subscribe.base.Response;
import justin.designPattern.publish_Subscribe.base.Subscriber;
import justin.designPattern.publish_Subscribe.base.Url;

import java.util.ArrayList;
import java.util.List;


public class PromotedMainTest {

	public static void main(String args[]){
		
		PromotedInputLoop il = PromotedInputLoop.create();
		List<Subscriber<String>> subscribers =new ArrayList<Subscriber<String>>();
		
		SubscriberPublisherControlCenterInstance controlC =new SubscriberPublisherControlCenterInstance(il, subscribers);
		
		controlC.addSubScriber(Echo.create());
		controlC.addSubScriber(Quit.create());
		controlC.addSubScriber(Response.create("foo","bar"));
		controlC.addSubScriber(Response.create("1+1","2"));
		controlC.addSubScriber(Url.create());
		
		il.publish(controlC);
	}
}


