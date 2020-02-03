package justin.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

	/**
     * 在List的末尾进行数据添加
     */
    public static void addLast(List<Integer> list){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        if (list instanceof ArrayList) {
            System.out.println("AddLast    ArrayList耗时："+(System.currentTimeMillis() - startTime));
        }else{
            System.out.println("AddLast    LinkedList耗时："+(System.currentTimeMillis() - startTime));
        }
    }
    
    
    /**
     *  在List的头部进行数据添加
     */
    public static void addFirst(List<Integer> list){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(0, i);
        }
        if (list instanceof ArrayList) {
            System.out.println("AddFirst    ArrayList耗时："+(System.currentTimeMillis() - startTime));
        }else{
            System.out.println("AddFirst    LinkedList耗时："+(System.currentTimeMillis() - startTime));
        }
    }

    /**
     * 获取List中所有的元素 
     */
    public static void getAll(List<Integer> list){

        for (int i = 0; i < 100000; i++) {//存放1000个数据
            list.add(i);
        }
        long startTime = System.currentTimeMillis();//插完数据后开始计时
        for (int i = 0, sum=0; i < 100000; i++) {
            sum+=list.get(i);
        }
        
        if (list instanceof ArrayList) {
            System.out.println("For    ArrayList耗时："+(System.currentTimeMillis() - startTime));
        }else{
            System.out.println("For    LinkedList耗时："+(System.currentTimeMillis() - startTime));
        }
        
        //再查询操作的时候，显然ArrayList的操作更优越，因为它其实就是一个O(N)的操作，而LinkedList这个时候就是O(N^2)的操作了。 
    }
    
    /**
     * for-each获取List中所有的元素
     * 这里可以用for each 循环来进行遍历，因为增强for循环是建立在Iterator的基础上的。 
     */
    public static void getAll2(List<Integer> list){

        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        long startTime = System.currentTimeMillis();//插完数据后开始计时
        int sum = 0;
        for (Integer integer : list) {
            sum += integer;
        }
        if (list instanceof ArrayList) {
            System.out.println("For each    ArrayList耗时："+(System.currentTimeMillis() - startTime));
        }else{
            System.out.println("For each    LinkedList耗时："+(System.currentTimeMillis() - startTime));
        }
    }
    
    /**
     * List 本身的remove 方法  删除指定数据
     * 
     *  会抛出  java.util.ConcurrentModificationException
     */
   public static void listRemoveOne(List<Integer> list){

       for (int i = 0; i < 100000; i++) {//存放1000个数据
           list.add(i);
       }
       long startTime = System.currentTimeMillis();//插完数据后开始计时
       try {
		for (Integer integer : list) {
		       if(integer < 100000);
		       list.remove(integer);
		   }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       if (list instanceof ArrayList) {
           System.out.println("List Remove    ArrayList耗时："+(System.currentTimeMillis() - startTime));
       }else{
           System.out.println("List Remove    LinkedList耗时："+(System.currentTimeMillis() - startTime));
       }
   }
   
   
   /**
    * List 使用Iterator 删除指定数据
    */
  public static void removeOneByIteratorRemove(List<Integer> list){

      for (int i = 0; i < 100000; i++) {//存放1000个数据
          list.add(i);
      }
      long startTime = System.currentTimeMillis();//插完数据后开始计时
      Iterator<Integer> iterator = list.iterator();
      while (iterator.hasNext()) {
          if(iterator.next() < 100000){
              iterator.remove();
          }
      }
      if (list instanceof ArrayList) {
          System.out.println("Iterator Remove    ArrayList耗时："+(System.currentTimeMillis() - startTime));
      }else{
          System.out.println("Iterator Remove    LinkedList耗时："+(System.currentTimeMillis() - startTime));
      }
      //从上面代码可以看出，在使用迭代器的遍历过程中，ListkedList显得更加优越。为什么同样都是迭代方式，但是ArrayList会耗时这么严重呢？
      //因为不论如何，删除之后数组的移动都是会带有巨大的开销
  }
    
    
    
    
    
    
    public static void AddFirstTest(List arrList, List linkList){
    	
    	addFirst(arrList);
        addFirst(linkList);
        //在这段代码中，LinkedList的执行时间复杂度还是O(N)，但是ArrayList已经变成O(N^2)了，因为在ArrayList中在头部添加本身就是一个O(N)的操作
        //ArrayList 在首部 添加元素 ，其后所有元素都需要依次移动调整位次  所以 复杂度就是 O(N)
        System.out.println();
    }
    
    public static void AddLastTest(List arrList, List linkList){
    	
        addLast(arrList);
        addLast(linkList);
        //如果插入的数量足够大，其实是LinkedList开销更加大，因为LinkedList需要生产一个新的Node节点，而ArrayList只需要尾部加一个元素就好了，
        //当然，有的小伙伴会说ArrayList会扩容，但是扩容的时间来说是非常小的。
        System.out.println();
    }
    
    private static void getAllByForEachTest(List arrList, List linkList) {

        getAll2(arrList);
	    getAll2(linkList);
	    System.out.println();
	}


	private static void getAllByNormalForTest(List arrList, List linkList) {
		
        getAll(arrList);
        getAll(linkList);
        System.out.println();
	}
    
	private static void removeOneByListRemoveTest(List arrList, List linkList) {
		
		listRemoveOne(arrList);
		listRemoveOne(linkList);
        System.out.println();
	}
	
	private static void removeOneByIteratorRemoveTest(List arrList, List linkList) {
		
		removeOneByIteratorRemove(arrList);
		removeOneByIteratorRemove(linkList);
        System.out.println();
	}
	
	
    
    public static void main(String[] args) {
    	List<Integer> arrList = new ArrayList<Integer>();
        List<Integer> linkList = new LinkedList<Integer>();
    	
        
    	AddFirstTest(arrList,linkList);
    	AddLastTest(arrList,linkList);
    	
    	getAllByNormalForTest(arrList,linkList);
    	getAllByForEachTest(arrList,linkList);
    	
    	
    	//removeOneByListRemoveTest(arrList,linkList);
    	removeOneByIteratorRemoveTest(arrList,linkList);
    }


	
}
