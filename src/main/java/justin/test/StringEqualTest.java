package justin.test;

class StringEqualTest {

	public static void main(String[] args) {
		String s1 = "Programming";
		String s2 = new String("Programming");
		String s3 = "Program" + "ming";
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
		System.out.println(s1 == s1.intern());
		
		
		StringBuilder sb1 = new StringBuilder("abc");
		StringBuilder sb2 = new StringBuilder("abc");
		System.out.println(sb1 == sb2);
		System.out.println(sb2.reverse());
		
	}
}