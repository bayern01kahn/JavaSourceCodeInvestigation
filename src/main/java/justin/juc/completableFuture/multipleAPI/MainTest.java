package justin.juc.completableFuture.multipleAPI;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

public class MainTest {

    List<Api> apis = Lists.newArrayList();

    public static void main(String[] args) {
        MainTest mainTest = new MainTest();

    }

    @Before
    public void init(){
        apis.add(new ServiceA());
        apis.add(new ServiceB());
        apis.add(new ServiceC());
        apis.add(new ServiceD());
    }



    @Test
    public void test() {
        long start = System.nanoTime();
        System.out.println("Total Value: "+serialRun().stream().mapToInt(i-> i.intValue()).sum());
        System.out.println("顺序执行 Done in "+(System.nanoTime()-start)/1_000_000+" ms");

        System.out.println("----------------------------------");

        start = System.nanoTime();
        System.out.println("Total Value: "+parallelRun().stream().mapToInt(i-> i.intValue()).sum());
        System.out.println("并行执行 Done in "+(System.nanoTime()-start)/1_000_000+" ms");

        System.out.println("----------------------------------");

        start = System.nanoTime();
        System.out.println("Total Value: "+completableRun1().stream().mapToInt(i-> i.intValue()).sum());
        System.out.println("并行执行 Done in "+(System.nanoTime()-start)/1_000_000+" ms");

        System.out.println("----------------------------------");

        start = System.nanoTime();
        System.out.println("Total Value: "+completableRun2().stream().mapToInt(i-> i.intValue()).sum());
        System.out.println("并行异步执行多个api 的2个不同方法  执行 Done in "+(System.nanoTime()-start)/1_000_000+" ms");

        System.out.println("----------------------------------");

        start = System.nanoTime();
        System.out.println("Total Value: "+completableRun2().stream().mapToInt(i-> i.intValue()).sum());
        System.out.println("并行异步执行 不同api端 的 2个不同方法, 第二个方法依赖第一个方法的结果  执行 Done in "+(System.nanoTime()-start)/1_000_000+" ms");


    }



    public List<Integer> serialRun(){
        return apis.stream().map(api -> api.getValue()).collect(toList());
    }

    public List<Integer> parallelRun(){
        return apis.stream().parallel().map(api -> api.getValue()).collect(toList());
    }

    public List<Integer> completableRun1(){
        List<CompletableFuture<Integer>> futures = apis.stream()
                .map(api -> CompletableFuture.supplyAsync(() -> api.getValue())).collect(toList());

        List<Integer> list = futures.stream().map(future -> future.join()).collect(toList());
        return list;
    }

    /**
     * 并行异步执行 不同api端 的 2个不同方法 来达到最搞笑完成的效果
     */
    public List<Integer> completableRun2(){
        List<CompletableFuture<Integer>> futures = apis.stream()
                .map(api -> {
//                    CompletableFuture cf1 = CompletableFuture.supplyAsync(() -> api.getValue());
//                    CompletableFuture cf2 = CompletableFuture.supplyAsync(() -> api.getSecondValue());
//                    CompletableFuture<Integer> result = cf1.thenCombine(cf2, (v1, v2) -> (Integer)v1 + (Integer) v2);
//                    return result;

                    return CompletableFuture.supplyAsync(() -> api.getValue())
                            .thenCombine(CompletableFuture.supplyAsync(() -> api.getSecondValue()),
                                    (v1, v2) -> v1 + v2);
                }).collect(toList());

        List<Integer> list = futures.stream().map(future -> future.join()).collect(toList());
        return list;
    }


    /**
     * 并行异步执行 不同api端 的 2个不同方法, 二个方法依次流水执行
     */
    public List<Integer> completableRun3(){
        List<CompletableFuture<Integer>> futures = apis.stream()
                .map(api -> CompletableFuture.supplyAsync(() -> api.getValue())
                        .thenCompose(v -> CompletableFuture.supplyAsync(() -> api.getSecondValue()* (int)v)))
                .collect(toList());
        List<Integer> list = futures.stream().map(future -> future.join()).collect(toList());
        return list;
    }
}
