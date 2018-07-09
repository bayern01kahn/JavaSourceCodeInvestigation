package justin.hotLoading;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * 加载 DynamicLoad的静态工厂
 * @author justin
 *
 */
public class DynamicLoadFactory {
	//记录热加载类的加载信息
	private static final Map<String, LoadInfo> loadTimeMap = new HashMap<String, LoadInfo>();
	
	//要用来测试的类的classPath
	private static final String CLASSPATH = "/Users/justin/work/GitHub/JavaSourceCodeInvestigation/JavaSourceCodeInvestigation/target/classes/";
	
	//实现热加载的类的全名（包名+类名）
	public static final String CLASSNAME = "justin.hotLoading.MyDynamicLoadInstance";
	
	public static DynamicLoad getDynamicLoader(){
		
		File f = new File(CLASSPATH+CLASSNAME.replaceAll("\\.","/")+ ".class");
		long lastModified = f.lastModified();
		System.out.println("时间戳: "+ lastModified);
		if(loadTimeMap.get(CLASSNAME) == null){  //loadTimeMap不包含className为key对应的LoadInfo信息。证明该类未被加载，那么加载
			load(CLASSNAME, lastModified);
		}else if(loadTimeMap.get(CLASSNAME).getLoadTime() != lastModified){  //类的时间戳发生变化，说明类被修改，则重新加载到JVM
			load(CLASSNAME, lastModified);
		}
	
		return loadTimeMap.get(CLASSNAME).getdLoader();
	}

	private static void load(String className, long lastModified) {
		MyClassLoader myClassLoader = new MyClassLoader(CLASSPATH);
		Class<?> loadClass = null;
		try {
			loadClass = myClassLoader.findClass(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DynamicLoad myClassLoaderInstance = newInstance(loadClass);
		LoadInfo loadInfo = new LoadInfo(myClassLoader, lastModified);
		loadInfo.setdLoader(myClassLoaderInstance);
		loadTimeMap.put(className, loadInfo);
	}

	//以反射的方式创建MyDynamicLoadInstance实例对象
	private static DynamicLoad newInstance(Class<?> loadClass) {
		try {
			return (DynamicLoad) loadClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
