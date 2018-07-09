package justin.hotLoading;
/**
 * 封装加载类的信息
 * @author justin
 *
 */
public class LoadInfo {

	//自定义的类加载器
	private MyClassLoader myClassLoader;
	//记录要加载类的时间戳 -- 加载时间
	private long  loadTime;
	
	public LoadInfo(MyClassLoader myClassLoader, long loadTime) {
		super();
		this.myClassLoader = myClassLoader;
		this.loadTime = loadTime;
	}

	private DynamicLoad dLoader;
	
	public DynamicLoad getdLoader() {
		return dLoader;
	}
	public void setdLoader(DynamicLoad dLoader) {
		this.dLoader = dLoader;
	}

	public MyClassLoader getMyClassLoader() {
		return myClassLoader;
	}

	public void setMyClassLoader(MyClassLoader myClassLoader) {
		this.myClassLoader = myClassLoader;
	}

	public long getLoadTime() {
		return loadTime;
	}

	public void setLoadTime(long loadTime) {
		this.loadTime = loadTime;
	}


	
	
}
