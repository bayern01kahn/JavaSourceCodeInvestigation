package justin.juc.threadPool.custom;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class JCallerBlocksPolicy implements RejectedExecutionHandler {
    private static final Log logger = LogFactory.getLog(JCallerBlocksPolicy.class);
    private final long maxWait;

    public JCallerBlocksPolicy(long maxWait) {
        this.maxWait = maxWait;
    }

    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if (!executor.isShutdown()) {
            try {
                BlockingQueue<Runnable> queue = executor.getQueue();
                if (logger.isDebugEnabled()) {
                    logger.debug("Attempting to queue task execution for " + this.maxWait + " milliseconds");
                }

                if (!queue.offer(r, this.maxWait, TimeUnit.SECONDS)) {
                    throw new RejectedExecutionException("Max wait time expired to queue task");
                } else {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Task execution queued");
                    }

                }
            } catch (InterruptedException var4) {
                Thread.currentThread().interrupt();
                throw new RejectedExecutionException("Interrupted", var4);
            }
        } else {
            throw new RejectedExecutionException("Executor has been shut down");
        }
    }
}
