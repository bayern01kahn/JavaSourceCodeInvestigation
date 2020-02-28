package justin.hashmap.hashcode_equals;

import java.util.HashMap;
import java.util.Iterator;

public class HashMap_hashcode_equals {
	public static <E> void main(String[] args) {

		System.out.println("未重写，相同的对象可以存入map");
		unOverrideObject();

		System.out.println("仅重写hashcode，任然可以放入相同对象，但是存入会被认为是不同的对象");
		overrideHashcodeNotEquals();
		
		System.out.println("仅重写equals，任然可以放入相同对象,但是存入会被认为是不同的对象");
		overrideEqualsNotHashcode();
		
		System.out.println("重写2个方法后,相同的对象只能存入一次");
		overrideObject();
	}
	
	public static <E> void unOverrideObject(){
		User user1 = new User(1,"佳");
		User user2 = new User(1,"佳");

		System.out.println("== => "+(user1 == user2));
		System.out.println("equals => "+ user1.equals(user2));

		HashMap hashmap = new HashMap();
		hashmap.put(user1, user1);
		hashmap.put(user2, user2);

		System.out.println("此时map size: "+ hashmap.size());
		
		Iterator<E> map1it=hashmap.entrySet().iterator();
		while(map1it.hasNext()){
			System.out.println(map1it.next());
		}
		
		System.out.println(hashmap.get(user1));
		System.out.println(hashmap.get(user2));
		System.out.println("map.getUser1 = map.getUser2 ?"+hashmap.get(user1) == hashmap.get(user2));
		System.out.println();
	}
	
	public static <E> void overrideHashcodeNotEquals(){
		UserRewriteHashCodeNotEquals user1 = new UserRewriteHashCodeNotEquals(1,"佳");
		UserRewriteHashCodeNotEquals user2 = new UserRewriteHashCodeNotEquals(1,"佳");

		System.out.println("== => "+(user1 == user2));
		System.out.println("equals => "+ user1.equals(user2));

		HashMap hashmap = new HashMap();
		hashmap.put(user1, user1);
		hashmap.put(user2, user2);

		System.out.println("此时map size: "+ hashmap.size());
		
		Iterator<E> map1it=hashmap.entrySet().iterator();
		while(map1it.hasNext()){
			System.out.println(map1it.next());
		}
		
		System.out.println(hashmap.get(user1));
		System.out.println(hashmap.get(user2));
		//System.out.println("map.getUser1 = map.getUser2 ? "+hashmap.get(user1) == hashmap.get(user2));
		System.out.println(hashmap.get(user1) == hashmap.get(user2));
		System.out.println();
	}
	
	public static <E> void overrideEqualsNotHashcode(){
		UserRewriteEqualsNotHashCode user1 = new UserRewriteEqualsNotHashCode(1,"佳");
		UserRewriteEqualsNotHashCode user2 = new UserRewriteEqualsNotHashCode(1,"佳");

		System.out.println("== => "+(user1 == user2));
		System.out.println("equals => "+ user1.equals(user2));

		HashMap hashmap = new HashMap();
		hashmap.put(user1, user1);
		hashmap.put(user2, user2);

		System.out.println("此时map size: "+ hashmap.size());
		
		Iterator<E> map1it=hashmap.entrySet().iterator();
		while(map1it.hasNext()){
			System.out.println(map1it.next());
		}
		
		System.out.println(hashmap.get(user1));
		System.out.println(hashmap.get(user2));
		System.out.println("map.getUser1 = map.getUser2 ?"+hashmap.get(user1) == hashmap.get(user2));
		System.out.println();
	}
	
	public static <E> void overrideObject(){
		UserRewriteHashCodeAndEquals user1 = new UserRewriteHashCodeAndEquals(1,"佳");
		UserRewriteHashCodeAndEquals user2 = new UserRewriteHashCodeAndEquals(1,"佳");

		System.out.println("== => "+(user1 == user2));
		System.out.println("equals => "+ user1.equals(user2));

		HashMap hashmap = new HashMap();
		hashmap.put(user1, user1);
		hashmap.put(user2, user2);     //key相同时 无法存入到map中...

		System.out.println("此时map size: "+ hashmap.size());

		Iterator<E> map1it=hashmap.entrySet().iterator();
		while(map1it.hasNext()){
			System.out.println(map1it.next());
		}

		System.out.println(hashmap.get(user1));
		System.out.println(hashmap.get(user2));
		System.out.println(hashmap.get(user1) == hashmap.get(user2));
		System.out.println("map.getUser1.equals(map.getUser2) ?"+hashmap.get(user1).equals(hashmap.get(user2)));
		System.out.println();
	}
	
}
