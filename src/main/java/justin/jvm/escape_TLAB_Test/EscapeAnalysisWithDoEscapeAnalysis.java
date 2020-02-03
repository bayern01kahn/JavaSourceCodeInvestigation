package justin.jvm.escape_TLAB_Test;

public class EscapeAnalysisWithDoEscapeAnalysis {
	 private static class Foo {  
	        private int x;  
	        private static int counter;  
	  
	        public Foo() {  
	            x = (++counter);  
	        }  
	    }  
	  
	    public static void main(String[] args) {  
	        long start = System.nanoTime();  
	        for (int i = 0; i < 1000 * 1000 * 10; ++i) {  
	            Foo foo = new Foo();  
	        }  
	        long end = System.nanoTime();  
	        System.out.println("Time cost is " + (end - start));  
	    } 
}


//开启逃逸分析设置为：
//-server -verbose:gc -XX:+DoEscapeAnalysis  