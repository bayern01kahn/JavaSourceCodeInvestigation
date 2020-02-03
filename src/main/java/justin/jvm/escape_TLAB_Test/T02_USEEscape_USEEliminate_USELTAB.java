package justin.jvm.escape_TLAB_Test;



/**
 * -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:+UseTLAB -XX:+PrintGCDetails
 * 
 * 打开逃逸分析，打开标量替换，开打线程本地内存，打印详细GC信息 查看 eden 情况
 * 
 * eden space 65536K, 8% used 
 * 
 * 对比 不打开的 TLAB 的情况  Eden 使用 占比明显提高 ，说明 TLAB 肯定是 使用 Eden 来存储线程本地内存
 * @author justin
 */
public class T02_USEEscape_USEEliminate_USELTAB {

	public static void main(String[] args) {
		byte[] b = new byte[1024];
	}
}
