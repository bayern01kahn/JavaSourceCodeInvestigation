package justin.juc.lock.synchronizedLock.demo.example1;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//上厕所的任务
@RequiredArgsConstructor
public class ToiletTask implements Runnable {

    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final Toilet toilet;
    private final String name;
    private final Random random;

    @Override
    public void run() {
        synchronized (toilet.getLock()) {
            System.out.println(String.format("[%s]-%s得到了厕所的锁...", LocalDateTime.now().format(F), name));
            while (!toilet.getAvailable()) {
                try {
                    toilet.getLock().wait();
                } catch (InterruptedException e) {
                    //ignore
                }
                int time = random.nextInt(3) + 1;
                try {
                    // 模拟上厕所用时
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException e) {
                    //ignore
                }
                System.out.println(String.format("[%s]-%s上厕所用了%s秒...", LocalDateTime.now().format(F), name, time));
            }
        }
    }
}
