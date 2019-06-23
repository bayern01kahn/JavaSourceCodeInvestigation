package justin.collections.blocking;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.cache.support.NullValue;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.*;

public class ArrayBlockingQueueT1Test {

    private ArrayBlockingQueueT1 queue;

    @Before
    public void setUp(){
        queue = new ArrayBlockingQueueT1();
    }

    @After
    public void tearDown(){
        queue = null;
    }

    @Test
    public void testAddNotExceedCapacity(){
        ArrayBlockingQueue<String> abq = queue.create(5);
        assertThat(abq.add("Hi1"), equalTo(true));
        assertThat(abq.add("Hi2"), equalTo(true));
        assertThat(abq.add("Hi3"), equalTo(true));
        assertThat(abq.add("Hi4"), equalTo(true));
        assertThat(abq.add("Hi5"), equalTo(true));
        assertThat(abq.size(), equalTo(5));
    }

    @Test(expected =IllegalStateException.class)
    public void testAddExceedCapacity(){
        ArrayBlockingQueue<String> abq = queue.create(5);
        assertThat(abq.add("Hi1"), equalTo(true));
        assertThat(abq.add("Hi2"), equalTo(true));
        assertThat(abq.add("Hi3"), equalTo(true));
        assertThat(abq.add("Hi4"), equalTo(true));
        assertThat(abq.add("Hi5"), equalTo(true));
        assertThat(abq.add("Hi6"), equalTo(true));
        fail("should not process to here");
    }

    @Test
    public void testOfferExceedCapacity(){
        ArrayBlockingQueue<String> abq = queue.create(5);
        assertThat(abq.offer("Hi1"), equalTo(true));
        assertThat(abq.offer("Hi2"), equalTo(true));
        assertThat(abq.offer("Hi3"), equalTo(true));
        assertThat(abq.offer("Hi4"), equalTo(true));
        assertThat(abq.offer("Hi5"), equalTo(true));
        assertThat(abq.offer("Hi6"), equalTo(false));
        assertThat(abq.size(), equalTo(5));
    }

    @Test
    public void testPutExceedCapacity() throws InterruptedException {
        ArrayBlockingQueue<String> abq = queue.create(5);
        abq.put("Hi1");
        abq.put("Hi2");
        abq.put("Hi3");
        abq.put("Hi4");
        abq.put("Hi5");
        abq.put("Hi6");
        fail("should not process to here");
    }

    @Test
    public void testPoll() throws InterruptedException {
        ArrayBlockingQueue<String> abq = queue.create(2);
        abq.add("Hi1");
        abq.add("Hi2");

        assertThat(abq.poll(),equalTo("Hi1"));
        assertThat(abq.poll(),equalTo("Hi2"));
        assertThat(abq.poll(), nullValue());
    }

    @Test
    public void testPeek()  {
        ArrayBlockingQueue<String> abq = queue.create(2);
        abq.add("Hi1");
        abq.add("Hi2");

        assertThat(abq.peek(),equalTo("Hi1"));
        assertThat(abq.peek(),equalTo("Hi1"));
        assertThat(abq.peek(),equalTo("Hi1"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testElement()  {
        ArrayBlockingQueue<String> abq = queue.create(2);
        abq.add("Hi1");
        abq.add("Hi2");

        assertThat(abq.element(),equalTo("Hi1"));
        assertThat(abq.element(),equalTo("Hi1"));
        assertThat(abq.element(),equalTo("Hi1"));
        abq.clear();
        assertThat(abq.element(),equalTo("Hi1"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemove()  {
        ArrayBlockingQueue<String> abq = queue.create(2);
        abq.add("Hi1");
        abq.add("Hi2");

        assertThat(abq.remove(),equalTo("Hi1"));
        assertThat(abq.remove(),equalTo("Hi2"));
        assertThat(abq.remove(),equalTo("Hi1"));
    }

}