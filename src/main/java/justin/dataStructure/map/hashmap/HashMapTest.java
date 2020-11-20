package justin.dataStructure.map.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapTest {
	public static void main(String[] args) {
		
		List<String> l = new ArrayList<String>();
		l.add("l");
		l.add("j");
		
		List<String> l1 = new ArrayList<String>();
		l.add("l1");
		l.add("j2");
		
		HashMap<Integer,List> hashmap = new HashMap<Integer,List>();
		hashmap.put(1, l);
		hashmap.put(2, l);
		hashmap.put(3, l1);
		hashmap.put(4, l1);
		hashmap.put(5, l1);
		hashmap.put(6, l1);
		hashmap.put(7, l1);
		hashmap.put(8, l1);
		hashmap.put(9, l1);
		hashmap.put(10, l1);
		hashmap.put(11, l1);
		hashmap.put(12, l1);
		hashmap.put(13, l1);
		hashmap.put(14, l1);
		hashmap.put(15, l1);
		hashmap.put(16, l1);
		hashmap.put(17, l1);
		hashmap.put(18, l1);
		hashmap.put(19, l1);
		hashmap.put(20, l1);
		
		
		
//		System.out.println(l.hashCode());
//		System.out.println(l.hashCode() >>> 16);
//		System.out.println(99 >>> 16);
//		System.out.println(9999 >>> 16);
//		System.out.println(99999 >>> 16);
//		System.out.println(999999 >>> 16);
//		System.out.println(9999999 >>> 16);
//		System.out.println(99999999 >>> 16);
		
		
		
//		Integer h= l.hashCode();
//		System.out.println("hashcode: "+ h);
//		System.out.println("对应2进制 : " +java.lang.Integer.toBinaryString(h));
//		System.out.println("右移16位对应2进制：  " +java.lang.Integer.toBinaryString(h >>> 16));
//		System.out.println("2者进行异或操作对应2进制：  " + java.lang.Integer.toBinaryString(h ^ (h >>> 16)));
		
		
		//为什么需要 右移16位 再进行 异或运算  因为下面的hash值 会出现 低位全为0的情况，从而出现异或失去意义，进而产生hash碰撞
		System.out.println(Integer.toBinaryString(new Float(1).hashCode()));
		System.out.println(Integer.toBinaryString(new Float(2).hashCode()));
		System.out.println(Integer.toBinaryString(new Float(3).hashCode()));

		
		

		
	}
	
	//dataStructure.hashmap hash 实现
	 static final int hash(Object key) {
	        int h;
	        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	    }
}
