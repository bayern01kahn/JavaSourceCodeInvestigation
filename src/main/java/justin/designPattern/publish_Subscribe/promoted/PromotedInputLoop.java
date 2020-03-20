package justin.designPattern.publish_Subscribe.promoted;

import justin.designPattern.publish_Subscribe.base.Publisher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class PromotedInputLoop implements Publisher<SubscriberPublisherControlCenterInstance> {
	
	public static PromotedInputLoop create() {
		return new PromotedInputLoop();
	}

	@Override
	public void publish(SubscriberPublisherControlCenterInstance spcc) {
		spcc.dispatcherEvent();
	}
	
}
