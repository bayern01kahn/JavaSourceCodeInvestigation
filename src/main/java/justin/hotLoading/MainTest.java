package justin.hotLoading;


public class MainTest{

	public static void main(String args[]){
		new Thread(new MsgHandler()).start();
	}
}
