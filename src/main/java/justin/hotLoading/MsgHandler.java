package justin.hotLoading;
/**
 * 后台启动一条线程不断刷新重新加载实现了热加载的类
 * @author liuyazhuang
 *
 */
public class MsgHandler implements Runnable {

	public void run() {
		while (true) {
			DynamicLoad dyLoad = DynamicLoadFactory.getDynamicLoader();
			dyLoad.dynamicUpdate();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
