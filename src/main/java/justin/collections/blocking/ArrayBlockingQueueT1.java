package justin.collections.blocking;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueT1 {


    /**
     * FIFO
     * Once created, the capacity can not changed.
     */
    public <T>ArrayBlockingQueue<T> create(int size){
        return new ArrayBlockingQueue<>(size);
    }
}
