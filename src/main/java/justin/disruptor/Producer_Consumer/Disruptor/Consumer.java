package justin.disruptor.Producer_Consumer.Disruptor;

import com.lmax.disruptor.WorkHandler;

public class Consumer implements WorkHandler<PCData> {
    public void onEvent(PCData event) throws Exception {
        System.out.print("Consumer:"+Thread.currentThread().getId() + " 获取队列中的值: " + event.get());
        System.out.println(" => 消费:求其平方: " + event.get() * event.get());
    }
}