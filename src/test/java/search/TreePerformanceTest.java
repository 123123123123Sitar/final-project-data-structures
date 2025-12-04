package search;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
class TreePerformanceTest {
    @Test
    void testTreePerformance() throws IOException {
        List<String> words = Files.readAllLines(Paths.get("documents/long_sorted.txt"));
        measurePerformance("BSTMap", new BSTMap<>(), words);
        measurePerformance("AVLTreeMap", new AVLTreeMap<>(), words);
        measurePerformance("RBTreeMap", new RBTreeMap<>(), words);
    }
    private void measurePerformance(String name, MyMap<String, Integer> map, List<String> words) {
        long start = System.nanoTime();
        for (String word : words) {
            map.put(word, 1);
        }
        long end = System.nanoTime();
        System.out.println(name + " insertion time: " + (end - start) / 1e6 + " ms");
    }
}
