package justin.jvm.escape_TLAB_Test;




/**
 * 
 * 
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:+UseTLAB -XX:+PrintGC
 * 
 * 关闭逃逸分析，关闭标量替换，使用线程本地内存，打印GC信息
 * 
 * @author justin
 *
 */
public class T01_NOEscape_NOEliminate_USETLAB {
	
	class User{
		int id;
		String name;
		
		User(int id, String name){
			this.id = id;
			this.name = name;
		}
	}
	
	void allocate (int i){
		new User(i, "name" + 1);
	}

	public static void main(String[] args) {
		T01_NOEscape_NOEliminate_USETLAB t =  new T01_NOEscape_NOEliminate_USETLAB();
		long s1 = System.currentTimeMillis();
		for(int i=0; i< 1000* 1000 * 10; i++){
			t.allocate(i);
		}
		long s2 = System.currentTimeMillis();
		System.out.println(s2-s1);
	}
}
