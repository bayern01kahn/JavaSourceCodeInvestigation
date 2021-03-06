package justin.designPattern.publish_Subscribe.base;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class InputLoop implements Publisher<String> {
	
	private List<Subscriber<String>> subscribers;

	private InputLoop () {
		subscribers = new ArrayList<Subscriber<String>>();
	}
	
	public static InputLoop create() {
		return new InputLoop();
	}

	public void subscriber (Subscriber<String> sub) {
		subscribers.add(sub);
	}
	
	@Override
	public void publish(String data) {
		for (Subscriber<String> sub : subscribers)
			sub.getPublication(data);
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
		while (true) {
			System.out.print("> ");
			String s = getInput();
			publish(s);
		}
	}

}
