package justin.hotLoading;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 自定义Java类加载器来实现Java类的热加载
 * @author justin
 */
public class MyClassLoader extends ClassLoader{
	
	//要加载的Java类的classpath
	private String classPath;
	
	public MyClassLoader(String classPath){
		//super.getSystemClassLoader();
		super(ClassLoader.getSystemClassLoader());
		this.classPath = classPath;
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		byte[] data = this.loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

	/**
	 * 加载 类文件的内容
	 * @param name
	 * @return
	 */
	private byte[] loadClassData(String name) {
		
		name = name.replace(".", "//");
		try {
			FileInputStream fis = new FileInputStream(new File(classPath + name + ".class"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int b=0;
			while((b = fis.read()) != -1){
				baos.write(b);
			}
			fis.close();
			return baos.toByteArray();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}
