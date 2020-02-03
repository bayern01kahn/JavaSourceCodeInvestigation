package justin.jvm.escape_TLAB_Test;



/**
 * -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:-UseTLAB -XX:+PrintGCDetails
 * 
 * 打开逃逸分析，打开标量替换，关闭线程本地内存，打印详细GC信息 查看 eden 情况
 * 
 * eden space 65536K, 1% used
 * 
 * @author justin
 */
public class T02_USEEscape_USEEliminate_NOLTAB {

	public static void main(String[] args) {
		byte[] b = new byte[1024];
	}
}
