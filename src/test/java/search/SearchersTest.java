package search;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Searchers.
 */
class SearchersTest {

    @Test
    void testLinearSearchFound() {
        List<String> list = Arrays.asList("c", "a", "b");
        assertEquals(1, Searchers.linearSearch(list, "a"));
    }

    @Test
    void testLinearSearchNotFound() {
        List<String> list = Arrays.asList("c", "a", "b");
        assertEquals(-1, Searchers.linearSearch(list, "z"));
    }

    @Test
    void testBinarySearchFound() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertEquals(2, Searchers.binarySearch(list, "c"));
    }

    @Test
    void testBinarySearchNotFound() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertEquals(-1, Searchers.binarySearch(list, "z"));
    }

    @Test
    void testBinarySearchFirst() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertEquals(0, Searchers.binarySearch(list, "a"));
    }

    @Test
    void testBinarySearchMiddle() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertEquals(1, Searchers.binarySearch(list, "b"));
    }
}
