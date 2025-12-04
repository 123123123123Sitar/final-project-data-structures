package search;

import java.util.List;

/**
 * A simple map interface.
 * 
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public interface MyMap<K extends Comparable<K>, V> {
    void put(K key, V value);

    V get(K key);

    boolean containsKey(K key);

    void remove(K key);

    int size();

    boolean isEmpty();

    List<K> keys();
}
