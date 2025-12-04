package search;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
class PerformanceTest {
    @Test
    void testSortingPerformance() {
        int size = 10000;
        List<String> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(rand.nextInt()));
        }
        List<String> copy1 = new ArrayList<>(list);
        long start = System.nanoTime();
        Sorters.mergeSort(copy1);
        long end = System.nanoTime();
        System.out.println("Merge Sort time for " + size + " elements: " + (end - start) / 1e6 + " ms");
        List<String> copy2 = new ArrayList<>(list);
        start = System.nanoTime();
        Sorters.quickSort(copy2);
        end = System.nanoTime();
        System.out.println("Quick Sort time for " + size + " elements: " + (end - start) / 1e6 + " ms");
    }
}
