package justin.jvm.OutOfMemoryError;

import java.util.ArrayList;
import java.util.List;


/**
 * -Xms20m  -Xmx 20m
 * 
 * @author justin
 *
 */
public class OutOfMemoryErrorTest {
	public static void main(String[] args)
	{
		List<OutOfMemoryErrorTest> list = new ArrayList<OutOfMemoryErrorTest>();
		while (true)
		{
			list.add(new OutOfMemoryErrorTest());
		}
	}
}
