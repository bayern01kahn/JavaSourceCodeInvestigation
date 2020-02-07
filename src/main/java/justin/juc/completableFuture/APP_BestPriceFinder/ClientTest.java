package justin.juc.completableFuture.APP_BestPriceFinder;

import justin.juc.completableFuture.APP_BestPriceFinder.completablefuture.Discount;
import justin.juc.completableFuture.APP_BestPriceFinder.completablefuture.Quote;
import justin.juc.completableFuture.APP_BestPriceFinder.completablefuture.Shop;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.toList;

/**
 * 让你的代码免受阻塞之苦
 *
 * @author itguang
 * @create 2017-11-21 16:50
 *
 *
 *
 *
 * https://github.com/itguang/java8/tree/master/java8_10
 **/
public class ClientTest {

    List<Shop> shops;

    @Before
    public void before() {
        shops = Arrays.asList(new Shop("淘宝"),
                new Shop("天猫"),
                new Shop("京东"),
                new Shop("当当"),
                new Shop("苏宁"),
                new Shop("国美"),
                new Shop("亚马逊"));

    }

    /**
     * 采用顺序查询所有商店的方式实现的 findPrices 方法
     * @param product
     * @return
     */
    public List<String> findPrice(String product) {
        List<String> list = shops.stream()
                .map(shop ->
                        String.format("%s price is %.2f RMB",
                                shop.getName(),
                                shop.getPrice(product)))

                .collect(toList());

        return list;
    }

    /**
     * 得到折扣商店信息(已经被解析过)
     */
    public List<String> findPrice1(String product){
        List<String> list = shops.stream()
                .map(discountShop -> discountShop.getDiscountPrice(product))
                .map(Quote::parse)
                .map(Discount::applyDiscount)
                .collect(toList());

        return list;
    }



    /**
     * 使用并行流对请求进行并行操作
     * @param product
     * @return
     */
    public List<String> findPrice2(String product) {
        List<String> list = shops.parallelStream()
                .map(shop ->
                        String.format("%s price is %.2f RMB",
                                shop.getName(),
                                shop.getPrice(product)))

                .collect(toList());

        return list;
    }

    /**
     * 使用 CompletableFuture 发起异步请求
     * @param product
     * @return
     */
    public List<String> findPrice3(String product) {
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f RMB",
                        shop.getName(),
                        shop.getPrice(product)))
                )
                .collect(toList());

        List<String> list = futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());

        //CompletableFuture.join() 操作.  其实我们自需要知道CompletableFuture 类中的 join 方法和 Future 接口中的 get 有相同的含义，
        //并且也声明在Future 接口中，它们唯一的不同是 join 不会抛出任何检测到的异常。
        //使用它你不再需要使用try / catch 语句块让你传递给第二个 map 方法的Lambda表达式变得过于臃肿


        //还有一点需要注意:这里使用了两个不同的 Stream 流水线，而不是在同一个处理流的流水线上一个接一个地放置两个 map 操作——这其实是有缘由的.
        //如果你在单一流水线中处理流,发向不同商家的请求只能以同步,顺序的方式才能执行成功.
        //这样一来,你的操作就相当于,每个ComletableFuture对象只能在前一个执行查询的操作后才能执行自己的查询操作,即通知join方法返回结果.下图清晰的表明了单一流水线和多流水线的执行过程:



        return list;
    }



    /**
     * 使用定制的 Executor 配置 CompletableFuture
     *
     * @param product
     * @return
     */
    public List<String> findPrice4(String product) {

        //为“最优价格查询器”应用定制的执行器 Execotor
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        //使用守护线程,使用这种方式不会阻止程序的关停
                        thread.setDaemon(true);
                        return thread;
                    }
                }
        );

        //注意:你现在创建的是一个由守护线程构建的线程池.java程序是没有办法终止或者退出一个正在运行的线程的,
        // 所以最后剩下的那个线程会由于一直等待无法发生的事件而引发问题
        //与此相反,如果将线程标记为守护线程,意味着程序退出时,它也会被回收.这二者之间没有性能之间的差异.



        //将执行器Execotor 作为第二个参数传递给 supplyAsync 工厂方法
        List<CompletableFuture<String>> futures = shops.stream()
                .map(shop -> CompletableFuture.supplyAsync(
                        () -> String.format("%s price is %.2f RMB", shop.getName(), shop.getPrice(product)), executor)
                )
                .collect(toList());

        List<String> list = futures.stream()
                .map(CompletableFuture::join)
                .collect(toList());


        return list;
    }


    /**
     * 使用 CompletableFuture 实现 findPrices 方法
     */
    public List<String> findPrice5(String product) {
        //为“最优价格查询器”应用定制的执行器 Execotor
        Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread thread = new Thread(r);
                        //使用守护线程,使用这种方式不会阻止程序的关停
                        thread.setDaemon(true);
                        return thread;
                    }
                }
        );

        List<CompletableFuture<String>> futureList = shops.stream()
                .map(discountShop -> CompletableFuture.supplyAsync(
                        //异步方式取得商店中产品价格
                        () -> discountShop.getDiscountPrice(product), executor))
                .map(future -> future.thenApply(Quote::parse))
                .map(future -> future.thenCompose(
                        quote -> CompletableFuture.supplyAsync(
                                //使用另一个异步任务访问折扣服务
                                () -> Discount.applyDiscount(quote), executor
                        )
                ))
                .collect(toList());

        //等待流中所有future执行完毕,并提取各自的返回值.
        List<String> list = futureList.stream()
                //join想但与future中的get方法,只是不会抛出异常
                .map(CompletableFuture::join)
                .collect(toList());

        return list;
    }



    /**
     *采用顺序查询所有商店的方式实现的 findPrices 方法,查询每个商店里的 iphone666s
     */
    @Test
    public void test() {
        long start = System.nanoTime();

        List<String> list = findPrice("iphone666s");

        System.out.println(list);
        System.out.println("顺序执行 Done in "+(System.nanoTime()-start)/1_000_000+" ms");

        start = System.nanoTime();
        list = findPrice2("iphone666s");
        System.out.println(list);
        System.out.println("并行流 Done in "+(System.nanoTime()-start)/1_000_000+" ms");

        start = System.nanoTime();
        list = findPrice3("iphone666s");
        System.out.println(list);
        System.out.println("CompletableFuture & 并行流 Done in "+(System.nanoTime()-start)/1_000_000+" ms");

        start = System.nanoTime();
        list = findPrice4("iphone666s");
        System.out.println(list);
        System.out.println("CompletableFuture & 线程池 & 并行流 Done in "+(System.nanoTime()-start)/1_000_000+" ms");

        start = System.nanoTime();
        list = findPrice5("iphone666s");
        System.out.println(list);
        System.out.println("为“最优价格查询器”应用定制的执行器 Execotor & CompletableFuture Done in "+(System.nanoTime()-start)/1_000_000+" ms");


    }

}
