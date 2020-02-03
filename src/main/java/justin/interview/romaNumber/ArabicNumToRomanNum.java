package justin.interview.romaNumber;

import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 将阿拉伯数字转化为罗马数字
 * @author justin
 *
              基本字符：I、V、X、 L、 C、  D、  M  
   相应的阿拉伯数字表示为：1、5、10、50、100、500、1000   
(1)相同的数字连写，所表示的数等于这些数字相加得到的数，如： Ⅲ = 3； 
(2)小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数， 如：Ⅷ = 8；Ⅻ = 12；   
(3)小的数字，（限于Ⅰ、X 和C）在大的数字的左边，所表示的数等于大数减小数得到的数，如：Ⅳ= 4；Ⅸ= 9； 
(4)正常使用时连写的数字重复不得超过三次。（表盘上的四点钟--“IIII”，例外。）  
(5)在一个数的上面画一条横线，表示这个数增值1000 倍，例如有：Ⅻ=12,000 。
所以，根据(5)  , 4000只要 在 Ⅳ 上面画一条横线即可
 */
public class ArabicNumToRomanNum {
	
	private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");
    }
	
	
	
	
	public static void main(String[] args) {
        
		
//		System.out.println("1000 =>" + arabicToRome(1000) +"  -  " + arabicToRome2(1000));
//		System.out.println("2018 =>" + arabicToRome(2018) +"  -  " + arabicToRome2(2018));
//		System.out.println("99 =>" + arabicToRome(99)  +"  -  " + arabicToRome2(99));
//		System.out.println("422 =>" + arabicToRome(422)  +"  -  " + arabicToRome2(422));
//		System.out.println("2547 =>" + arabicToRome(2547)  +"  -  " + arabicToRome2(2547));
//		
//		 long startTime = System.currentTimeMillis();
//		 for (int i = 1; i< 4000; i++) {
//            System.out.println(i+"\t =\t "+arabicToRome_TreeMap(i));
//        }
//		 long endTime = System.currentTimeMillis();
//		 System.out.println("TreeMap执行时间：" + (endTime-startTime));
//		 
//		 
//		 
//		 startTime = System.currentTimeMillis();
//		 for (int i = 1; i< 4000; i++) {
//			 System.out.println(i+"\t =\t " + arabicToRome(i));
//		 }
//		 endTime = System.currentTimeMillis();
//		 System.out.println("数组解决执行时间：" + (endTime-startTime));
		
        while(true)  
        {  
        	System.out.println();
        	System.out.print("请输入0-3999以内的阿拉伯数字：");  
            @SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
            int s = Integer.parseInt(sc.next());

            if(s<1 || s>3999)  
            {  
                System.out.println("请确认输入的是 0-3999以内的阿拉伯数字");  
  
            }else  
            {  
            	String roma = arabicToRome_DoubleDimensionalArray(s);
            	System.out.print("转成罗马数字："+ roma +"\n");  
            	System.out.println("转回阿拉伯数字： "+ romeToArabic(roma));
            }
            continue;
        }  
    }
    
	
	
	// 阿拉伯数字转罗马数字：
	// 把所有小数字在前的组合也作为基本数字，再做一个对应的数值表就可以解决问题了。
	// I、V、X、 L、 C、 D、 M
	// 1．5、10、50、100、500、1000
	private static String arabicToRome(int aNumber) {
		long startTime = System.currentTimeMillis();
		
		
		if (aNumber < 1 || aNumber > 3999) {
			return "-1";
		}
		int[] aArray = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] rArray = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		String rNumber = "";
		for (int i = 0; i < aArray.length; i++) {
			while (aNumber >= aArray[i]) {
				rNumber += rArray[i];
				aNumber -= aArray[i];
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("执行时间： " + (endTime-startTime));
		
		
		return rNumber;
	}
    
   /**
    * 利用二维数组 和 位数求余
    * @param num
    * @return
    */
   private static String arabicToRome_DoubleDimensionalArray(int num) {
        String[][] s ={
            {"","I","II","III","IV","V","VI","VII","VIII","IX"},
            {"","X","XX","XXX","XL","L","LX","LXX","LXXX","XC"},
            {"","C","CC","CCC","CD","D","DC","DCC","DCCC","CM"},
            {"","M","MM","MMM"}
        };
        
        StringBuilder sbRoma = new StringBuilder();
        sbRoma.append(s[3][num / 1000 % 10]);
        sbRoma.append(s[2][num / 100 % 10]);
        sbRoma.append(s[1][num / 10 % 10]);
        sbRoma.append(s[0][num % 10]);
        
        return sbRoma.toString();
    }
   
   /**
    * 利用treemap 来递归完成数字相加
    * @param number
    * @return
    */
   private static String arabicToRome_TreeMap(int number){
		int l =  map.floorKey(number);
       if ( number == l ) {
           return map.get(number);
       }
       return map.get(l) + arabicToRome_TreeMap(number-l);
	}
   
   
   //罗马数字转阿拉伯数字：
   // 从前往后遍历罗马数字，如果某个数比前一个数小，则把该数加入到结果中；
   // 反之，则在结果中两次减去前一个数并加上当前这个数；
   // I、V、X、 L、 C、  D、  M
   // 1．5、10、50、100、500、1000
   private static int romeToArabic(String in){
       int graph[] = new int[89];  //X 对应ASCii码 为最大 88  所以 89位即可
       graph['I'] = 1;
       graph['V']=5;
       graph['X']=10;
       graph['L']=50;
       graph['C']=100;
       graph['D']=500;
       graph['M']=1000;
       char[] num = in.toCharArray();
       // 遍历这个数，用sum来总计和
       int sum = graph[num[0]];
       for(int i=0; i<num.length-1; i++){
           // 如果，i比i+1大的话，直接相加
           if(graph[num[i]] >= graph[num[i+1]]){
               sum += graph[num[i+1]];
           }
           // 如果i比i+1小的话，则将总和sum减去i这个地方数的两倍，同时加上i+1
           // 就相当于后边的数比左边的数大，则用右边的数减左边的数
           else{
               sum = sum + graph[num[i+1]] - 2*graph[num[i]];
           }
       }
       return sum;
   }
    
}
