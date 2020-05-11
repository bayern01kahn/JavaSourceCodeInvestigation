package justin.interview.AylaNetwork;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class exam {

    @Test
    public void test1() {
        System.out.println(f(5));
        System.out.println(30.0/(3*5*7*11*13));
    }

    @Test
    public void test4(){
        List<String> s = Lists.newArrayList("not", null, null, "add", "dog", "acid", "add", "elf", "gap", "cat", "rat");
        String str = "a";
        System.out.println(foo(s, str));
    }

    @Test
    public void test5(){
        srtfunction("eeaddabccccaaeec");
        srtfunction("zzzaccbb");
        srtfunction("xxxyyz");
    }

    public double f(int n){
        return sumf(n)/(3*5*7*11*13);
    }

    public int sumf(int n){
        if(n <=0) return 0;
        return sumf(n-1)+2*n;
    }


//4. 给定一个包含‘a’-’z’字符的字符串，请写一个方法打印出每个字符，并依照它们的出现次数由少到多来排序。如果有两个字符的出现次数相同的话，请依照字母顺序来排序。例如下列的字符串 “eeaddabccccaaeec”，计算过后找出各字母的出现次数为 a: 4次, b: 1次, c: 5次, d: 2次, e: 4次
//    → 以出现次数由少到多排序后为
//    b: 1次, d: 2次, a: 4次, e: 4次, c: 5次
//    输出结果为“bdaec”。其中b只出现了一次，所以是第一个。a和e都出现了四次，但因为a 的字母顺序在e前面，所以先输出a后再输出e。其他例子：
//            “zzzaccbb” -> “abcz”
//            “xxxyyz” -> “zyx”
//    提示：你可以使用Map和Java 8 API来解决。
    public String srtfunction(String s) {
        char[] chars = s.toCharArray();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<chars.length;i++){
            //System.out.println(chars[i]);
            String str =String.valueOf(chars[i]);
            if(map.get(str) == null ){
                map.put(str, 1);
            }else {
                map.put(str, map.get(str)+1);
            }
        }
        StringBuffer sb = new StringBuffer();
        map.entrySet().stream().sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sb.append(x.getKey()));
        System.out.println(sb.toString());
        return sb.toString();
    }



//5.  请写一个方法，传入值为一个字符串的List，以及一个字符串，并且回传一个List满足下列要求：
//    a. List中的字符串需包含传入的字符串
//    b. 每个List中的字符串的内容需要反转
//    c. List中的字符串需要去重
//    d. List中只需要前三个元素
//    e. 请使用Java Stream 实现此方法
//    例如给定一个串列list = [ “not”, “add”, “dog”, “acid”, “add”, “elf”, “gap”, “cat”, “rat” ] 和一个字符串”a”：
//    规则a → [ “add”, “acid”, “add”, “gap”, “cat”, “rat” ]  // 只有包含”a”的元素可留下
//    规则b → [ “dda”, “dica”, “dda”, “pag”, “tac”, “tar” ]  // 反转字符串
//    规则c → [ “dda”, “dica”, “pag”, “tac”, “tar” ]  // 去重
//    规则d → [ “dda”, “dica”, “pag” ]  // 只取前三个元素
//            → 输出结果
//    提示：请使用Java Stream语法，并考虑传入的串列中元素可能为null的情况。
    public List<String> foo(List<String> list, String subString) {
        return list.parallelStream().filter(Objects::nonNull)
                .distinct()
                .filter(str -> str.contains(subString))
                .limit(3)
                .map(s -> new StringBuffer(s).reverse().toString())
                .collect(toList());
    }
}
