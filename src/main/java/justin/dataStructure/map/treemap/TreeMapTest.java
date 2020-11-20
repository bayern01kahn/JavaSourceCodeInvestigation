package justin.dataStructure.map.treemap;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapTest {
	
	public static void main(String[] args) {
		TreeMap<Integer,String> treeMap = new TreeMap<Integer, String>();
		
		treeMap.put(1, "111"); //相同的键会被覆盖掉
		treeMap.put(1, "222");
		treeMap.put(3, "333");
		
		
		
		Set<Integer> keys = treeMap.keySet();
	    for (Integer key : keys){
	        System.out.println(key);
	    }
	    
	    Set<Entry<Integer,String>> set = treeMap.entrySet();
		Iterator iter=set.iterator();
		
        while(iter.hasNext()){  
            Entry ent=(Entry)iter.next();
            String keyt=ent.getKey().toString();  
            String valuet=ent.getValue().toString();  
            System.out.println(keyt+"-"+valuet);  
        }  
	}

}
