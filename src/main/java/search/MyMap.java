package search;
import java.util.List;

public interface MyMap<K extends Comparable<K>, V> {
    void put(K key, V value);
    V get(K key);
    boolean containsKey(K key);
    void remove(K key);
    int size();
    boolean isEmpty();
    List<K> keys();
}
