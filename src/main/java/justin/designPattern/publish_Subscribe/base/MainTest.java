package justin.designPattern.publish_Subscribe.base;


public class MainTest {

	public static void main(String args[]){
		InputLoop il = InputLoop.create();
		il.subscriber(Echo.create());
		il.subscriber(Quit.create());
		il.subscriber(Response.create("foo","bar"));
		il.subscriber(Response.create("1+1","2"));
		il.subscriber(Url.create());
		il.loop();
	}
}


