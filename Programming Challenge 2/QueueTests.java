package lab07;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Harrison Smith
 * @date October 4, 2019
 *
 */
public class QueueTests {
    /**
     * Creates and returns a {@code Queue} with the given arguments as its elements,
     * enqueued in the same order.
     *
     * @param <T>  type of elements
     * @param args elements to be enqueued in the {@code Queue}
     * @return a {@code Queue} with the given elements
     */
    @SafeVarargs
    private final static <T> Queue<T> testQueueFromArgs(T... args) {
//        Queue<T> q = new ArrayQueue<T>(); /* comment this line out and .. */
          Queue<T> q = new CircularArrayQueue<T>(); /* .. uncomment this one to test your CircularArrayQueue implementation */
        for (T arg : args) {
            q.enqueue(arg);
        }
        return q;
    }

    @Test
    public final void testEnqueNonEmpty() {
        Queue<String> q = testQueueFromArgs("red", "blue");
        q.enqueue("green");
        assertEquals("<red,blue,green>", q.toString());
    }

    @Test
    public final void testEnqueEmpty() {
        Queue<String> q = testQueueFromArgs();
        q.enqueue("red");
        assertEquals("<red>", q.toString());
    }

    @Test
    public final void testDequeToNonEmpty() {
        Queue<String> q = testQueueFromArgs("red", "blue");
        String s = q.dequeue();
        assertEquals("red", s);
        assertEquals("<blue>", q.toString());
    }

    @Test
    public final void testDequeToEmpty() {
        Queue<String> q = testQueueFromArgs("red");
        String s = q.dequeue();
        assertEquals("red", s);
        assertEquals("<>", q.toString());
    }

    @Test
    public final void testSizeNonEmpty() {
        Queue<String> q = testQueueFromArgs("red", "blue");
        assertEquals(2, q.size());
        assertEquals("<red,blue>", q.toString());
    }

    @Test
    public final void testSizeEmpty() {
        Queue<String> q = testQueueFromArgs();
        assertEquals(0, q.size());
        assertEquals("<>", q.toString());
    }

    @Test
    public void testExpandedSize() {
        Queue<Integer> q = testQueueFromArgs(10, 20, 30, 40, 50, 60, 70, 80, 90,
                                             100, 110);
        assertEquals(11, q.size());
        assertEquals("<10,20,30,40,50,60,70,80,90,100,110>", q.toString());
    }

    @Test
    public void testRearWrapAround() {
        Queue<Integer> q = testQueueFromArgs(10, 20, 30, 40, 50, 60, 70, 80, 90,
                                             100);
        q.dequeue(); // 10 removed
        q.enqueue(-1);
        assertEquals("<20,30,40,50,60,70,80,90,100,-1>", q.toString());
    }

    @Test
    public void testPreFrontWrapAround() {
        Queue<Integer> q = testQueueFromArgs(10, 20, 30, 40, 50, 60, 70, 80, 90,
                                             100);
        for (int i = 0; i < 10; i++) {
            q.dequeue();
        }
        q.enqueue(-1);
        assertEquals(-1, (int) q.dequeue());
        assertEquals("<>", q.toString());
    }

    @Test
    public final void testFront() {
        Queue<String> q = testQueueFromArgs("red");
        assertEquals("red", q.front());
        assertEquals("<red>", q.toString());
    }

}

