package search;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
public class ReportGenerator {
    public static void main(String[] args) throws IOException {
        System.out.println("=== Java Search Engine Performance Report ===");
        System.out.println("\n--- Sorting Performance (ms) ---");
        int[] sizes = { 1000, 5000, 10000, 50000 };
        System.out.printf("%-10s %-15s %-15s%n", "Size", "Merge Sort", "Quick Sort");
        for (int size : sizes) {
            List<String> list = generateRandomList(size);
            List<String> copy1 = new ArrayList<>(list);
            long start = System.nanoTime();
            Sorters.mergeSort(copy1);
            long end = System.nanoTime();
            double mergeTime = (end - start) / 1e6;
            List<String> copy2 = new ArrayList<>(list);
            start = System.nanoTime();
            Sorters.quickSort(copy2);
            end = System.nanoTime();
            double quickTime = (end - start) / 1e6;
            System.out.printf("%-10d %-15.2f %-15.2f%n", size, mergeTime, quickTime);
        }
        System.out.println("\n--- Tree Insertion Performance (Sorted Input) ---");
        List<String> sortedWords = Files.readAllLines(Paths.get("documents/long_sorted.txt"));
        List<String> largeSorted = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            largeSorted.add(String.format("%06d", i));
        }
        System.out.println("Input size: " + largeSorted.size() + " sorted strings");
        measureMapInsertion("BSTMap", new BSTMap<>(), largeSorted);
        measureMapInsertion("AVLTreeMap", new AVLTreeMap<>(), largeSorted);
        measureMapInsertion("RBTreeMap", new RBTreeMap<>(), largeSorted);
        measureMapInsertion("MyHashMap", new MyHashMap<>(), largeSorted);
        System.out.println("\n--- Search Performance (Random Access) ---");
        List<String> randomWords = generateRandomList(10000);
        BSTMap<String, Integer> bst = new BSTMap<>();
        AVLTreeMap<String, Integer> avl = new AVLTreeMap<>();
        RBTreeMap<String, Integer> rb = new RBTreeMap<>();
        MyHashMap<String, Integer> hash = new MyHashMap<>();
        for (String w : randomWords) {
            bst.put(w, 1);
            avl.put(w, 1);
            rb.put(w, 1);
            hash.put(w, 1);
        }
        measureMapSearch("BSTMap", bst, randomWords);
        measureMapSearch("AVLTreeMap", avl, randomWords);
        measureMapSearch("RBTreeMap", rb, randomWords);
        measureMapSearch("MyHashMap", hash, randomWords);
    }
    private static List<String> generateRandomList(int size) {
        List<String> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            list.add(String.valueOf(rand.nextInt()));
        }
        return list;
    }
    private static void measureMapInsertion(String name, MyMap<String, Integer> map, List<String> words) {
        long start = System.nanoTime();
        for (String word : words) {
            map.put(word, 1);
        }
        long end = System.nanoTime();
        System.out.printf("%-15s: %.2f ms%n", name, (end - start) / 1e6);
    }
    private static void measureMapSearch(String name, MyMap<String, Integer> map, List<String> words) {
        long start = System.nanoTime();
        for (String word : words) {
            map.get(word);
        }
        long end = System.nanoTime();
        System.out.printf("%-15s: %.2f ms%n", name, (end - start) / 1e6);
    }
}
