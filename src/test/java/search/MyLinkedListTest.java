package search;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Iterator;
class MyLinkedListTest {
    @Test
    void testAddAndGet() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
    }
    @Test
    void testAddAtIndex() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("c");
        list.add(1, "b");
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }
    @Test
    void testRemove() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals("b", list.remove(1));
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
        assertEquals(2, list.size());
    }
    @Test
    void testSizeAndIsEmpty() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        list.add("a");
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
    }
    @Test
    void testIterator() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("a", it.next());
        assertTrue(it.hasNext());
        assertEquals("b", it.next());
        assertFalse(it.hasNext());
    }
}
