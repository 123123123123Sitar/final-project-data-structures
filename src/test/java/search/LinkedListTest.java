package search;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    void testAddAndSize() {
        MyLinkedList<String> list = new MyLinkedList<>();
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
        list.add("a");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        list.add("b");
        list.add("c");
        assertEquals(3, list.size());
    }
    @Test
    void testGet() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
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
        assertEquals(3, list.size());
    }
    @Test
    void testAddAtHead() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("b");
        list.add(0, "a");
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
    }
    @Test
    void testRemove() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals("b", list.remove(1));
        assertEquals(2, list.size());
        assertEquals("a", list.get(0));
        assertEquals("c", list.get(1));
    }
    @Test
    void testRemoveHead() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        assertEquals("a", list.remove(0));
        assertEquals(1, list.size());
        assertEquals("b", list.get(0));
    }
    @Test
    void testRemoveTail() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        assertEquals("b", list.remove(1));
        assertEquals(1, list.size());
    }
    @Test
    void testRemoveSingleItem() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        assertEquals("a", list.remove(0));
        assertTrue(list.isEmpty());
    }
    @Test
    void testIterator() {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        assertEquals("abc", sb.toString());
    }
    @Test
    void testMixedOperations() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(0); 
        list.add(4);
        list.add(5);
        list.remove(1); 
        assertEquals(3, list.size());
    }
}
