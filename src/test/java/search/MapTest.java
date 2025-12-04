package search;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    @Test
    void testBSTMapPutAndGet() {
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        assertEquals(1, map.get("apple"));
        assertEquals(2, map.get("banana"));
    }
    @Test
    void testBSTMapUpdate() {
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("apple", 1);
        map.put("apple", 10);
        assertEquals(10, map.get("apple"));
        assertEquals(1, map.size());
    }
    @Test
    void testBSTMapSearchMiss() {
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("apple", 1);
        assertNull(map.get("banana"));
    }
    @Test
    void testBSTMapRemove() {
        BSTMap<String, Integer> map = new BSTMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.remove("apple");
        assertNull(map.get("apple"));
        assertEquals(1, map.size());
    }
    @Test
    void testAVLMapPutAndGet() {
        AVLTreeMap<String, Integer> map = new AVLTreeMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        assertEquals(1, map.get("apple"));
        assertEquals(2, map.get("banana"));
    }
    @Test
    void testAVLMapBalanceSortedInput() {
        AVLTreeMap<String, Integer> map = new AVLTreeMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(String.format("%03d", i), i);
        }
        assertEquals(100, map.size());
        assertEquals(50, map.get("050"));
    }
    @Test
    void testRBMapPutAndGet() {
        RBTreeMap<String, Integer> map = new RBTreeMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        assertEquals(1, map.get("apple"));
        assertEquals(2, map.get("banana"));
    }
    @Test
    void testRBMapBalanceSortedInput() {
        RBTreeMap<String, Integer> map = new RBTreeMap<>();
        for (int i = 0; i < 100; i++) {
            map.put(String.format("%03d", i), i);
        }
        assertEquals(100, map.size());
        assertEquals(50, map.get("050"));
    }
    @Test
    void testHashMapPutAndGet() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        assertEquals(1, map.get("apple"));
        assertEquals(2, map.get("banana"));
    }
    @Test
    void testHashMapUpdate() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("apple", 1);
        map.put("apple", 10);
        assertEquals(10, map.get("apple"));
        assertEquals(1, map.size());
    }
    @Test
    void testHashMapSearchMiss() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("apple", 1);
        assertNull(map.get("banana"));
    }
    @Test
    void testHashMapRemove() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.remove("apple");
        assertNull(map.get("apple"));
        assertEquals(1, map.size());
    }
    @Test
    void testHashMapResizing() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, i);
        }
        assertEquals(100, map.size());
        assertEquals(50, map.get("key50"));
    }
}
