package justin.designPattern.publish_Subscribe;

import justin.designPattern.publish_Subscribe.base.Echo;
import justin.designPattern.publish_Subscribe.base.Quit;
import justin.designPattern.publish_Subscribe.base.Response;
import justin.designPattern.publish_Subscribe.base.Subscriber;
import justin.designPattern.publish_Subscribe.base.Url;
import justin.designPattern.publish_Subscribe.promoted.PromotedInputLoop;
import justin.designPattern.publish_Subscribe.promoted.SubscriberPublisherControlCenterInstance;

import java.util.ArrayList;
import java.util.List;


public class MainTest {

	public static void main(String args[]){
//		InputLoop il = InputLoop.create();
//		il.subscriber(Echo.create());
//		il.subscriber(Quit.create());
//		il.subscriber(Response.create("foo","bar"));
//		il.subscriber(Response.create("1+1","2"));
//		il.subscriber(Url.create());
//		il.loop();
		
		PromotedInputLoop il = PromotedInputLoop.create();
		List<Subscriber<String>> subscribers =new ArrayList<Subscriber<String>>();
		
		SubscriberPublisherControlCenterInstance controlC =new SubscriberPublisherControlCenterInstance(il, subscribers);
		
		controlC.addSubScriber(Echo.create());
		controlC.addSubScriber(Quit.create());
		controlC.addSubScriber(Response.create("foo","bar"));
		controlC.addSubScriber(Response.create("1+1","2"));
		controlC.addSubScriber(Url.create());
		
		controlC.dispatcherEvent();
	}
}


