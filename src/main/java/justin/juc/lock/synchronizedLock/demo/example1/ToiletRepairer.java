package justin.juc.lock.synchronizedLock.demo.example1;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// 厕所维修工
@RequiredArgsConstructor
public class ToiletRepairer implements Runnable {

    private static final DateTimeFormatter F = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
    private final Toilet toilet;

    @Override
    public void run() {
        synchronized (toilet.getLock()) {
            System.out.println(String.format("[%s]-厕所维修员得到了厕所的锁,维修厕所要用3000ms...", LocalDateTime.now().format(F)));
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                // ignore
            }
            toilet.setAvailable(true);
            toilet.getLock().notifyAll();
            System.out.println(String.format("[%s]-厕所维修员维修完毕...", LocalDateTime.now().format(F)));
        }
    }
}
