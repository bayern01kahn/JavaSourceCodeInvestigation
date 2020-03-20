package justin.designPattern.publish_Subscribe.promoted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import justin.designPattern.publish_Subscribe.base.Subscriber;

public class SubscriberPublisherControlCenterInstance implements ControlCenter {
	
	private PromotedInputLoop publisher;
	private List<Subscriber<String>> subscribers;
	
	public SubscriberPublisherControlCenterInstance(){}

	public SubscriberPublisherControlCenterInstance(PromotedInputLoop publisher, List<Subscriber<String>> subscribers) {
		this.publisher = publisher;
		this.subscribers = subscribers;
	}

	public void addSubScriber(Subscriber sub) {
		subscribers.add(sub);
	}
	
	public static String getInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String response = "";

		try {
			response = br.readLine();
			if (response == null) {
				return "";
			}
		} catch (IOException ioe) {
			System.out.println("IO error reading from terminal\n");
			System.exit(1);
		}
		return response;
	}
	
	public void loop() {
		
	}
	
	@Override
	public void dispatcherEvent(){
		
		while (true) {
			System.out.print("> ");
			String s = getInput();
			for (Subscriber<String> sub : subscribers)
				sub.getPublication(s);
		}
		
	}

}
