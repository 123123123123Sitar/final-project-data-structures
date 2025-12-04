package search;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

class ListQueueTest {
    @Test
    void testEnqueueAndDequeue() {
        ListQueue<String> queue = new ListQueue<>();
        queue.enqueue("a");
        queue.enqueue("b");
        assertEquals(2, queue.size());
        assertEquals("a", queue.dequeue());
        assertEquals("b", queue.peek());
        assertEquals(1, queue.size());
    }
    @Test
    void testPeek() {
        ListQueue<String> queue = new ListQueue<>();
        queue.enqueue("a");
        assertEquals("a", queue.peek());
        assertEquals(1, queue.size()); 
    }
    @Test
    void testIsEmpty() {
        ListQueue<String> queue = new ListQueue<>();
        assertTrue(queue.isEmpty());
        queue.enqueue("a");
        assertFalse(queue.isEmpty());
        queue.dequeue();
        assertTrue(queue.isEmpty());
    }
    @Test
    void testDequeueEmptyQueue() {
        ListQueue<Integer> queue = new ListQueue<>();
        assertThrows(NoSuchElementException.class, queue::dequeue);
    }
    @Test
    void testPeekEmptyQueue() {
        ListQueue<Integer> queue = new ListQueue<>();
        assertThrows(NoSuchElementException.class, queue::peek);
    }
    @Test
    void testFIFOOrder() {
        ListQueue<Integer> queue = new ListQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }
}
