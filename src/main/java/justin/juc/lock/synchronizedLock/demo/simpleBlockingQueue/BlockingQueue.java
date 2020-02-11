package justin.juc.lock.synchronizedLock.demo.simpleBlockingQueue;

public interface BlockingQueue<T> {

    void put(T value) throws InterruptedException;

    T take() throws InterruptedException;
}
