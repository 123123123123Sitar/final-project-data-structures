package search;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SortersTest {
    @Test
    void testMergeSortEmpty() {
        List<String> list = new ArrayList<>();
        Sorters.mergeSort(list);
        assertTrue(list.isEmpty());
    }
    @Test
    void testMergeSortSingleElement() {
        List<String> list = new ArrayList<>(Arrays.asList("a"));
        Sorters.mergeSort(list);
        assertEquals(Arrays.asList("a"), list);
    }
    @Test
    void testMergeSortPreSorted() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Sorters.mergeSort(list);
        assertEquals(Arrays.asList("a", "b", "c"), list);
    }
    @Test
    void testMergeSortReverseSorted() {
        List<String> list = new ArrayList<>(Arrays.asList("c", "b", "a"));
        Sorters.mergeSort(list);
        assertEquals(Arrays.asList("a", "b", "c"), list);
    }
    @Test
    void testMergeSortDuplicates() {
        List<String> list = new ArrayList<>(Arrays.asList("c", "a", "c", "b", "a"));
        Sorters.mergeSort(list);
        assertEquals(Arrays.asList("a", "a", "b", "c", "c"), list);
    }
    @Test
    void testQuickSortEmpty() {
        List<String> list = new ArrayList<>();
        Sorters.quickSort(list);
        assertTrue(list.isEmpty());
    }
    @Test
    void testQuickSortSingleElement() {
        List<String> list = new ArrayList<>(Arrays.asList("a"));
        Sorters.quickSort(list);
        assertEquals(Arrays.asList("a"), list);
    }
    @Test
    void testQuickSortPreSorted() {
        List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
        Sorters.quickSort(list);
        assertEquals(Arrays.asList("a", "b", "c"), list);
    }
    @Test
    void testQuickSortReverseSorted() {
        List<String> list = new ArrayList<>(Arrays.asList("c", "b", "a"));
        Sorters.quickSort(list);
        assertEquals(Arrays.asList("a", "b", "c"), list);
    }
    @Test
    void testQuickSortDuplicates() {
        List<String> list = new ArrayList<>(Arrays.asList("c", "a", "c", "b", "a"));
        Sorters.quickSort(list);
        assertEquals(Arrays.asList("a", "a", "b", "c", "c"), list);
    }
}
