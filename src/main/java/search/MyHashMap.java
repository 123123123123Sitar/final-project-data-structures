package search;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap<K extends Comparable<K>, V> implements MyMap<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private LinkedList<Entry<K, V>>[] table;
    private int size;
    private static class Entry<K, V> {
        K key;
        V value;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new LinkedList[INITIAL_CAPACITY];
        for (int i = 0; i < INITIAL_CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
    }
    private int hash(K key) {
        return Math.abs(key.hashCode()) % table.length;
    }
    @Override
    public void put(K key, V value) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        table[index].add(new Entry<>(key, value));
        size++;
        if (size > table.length * 0.75) {
            resize();
        }
    }
    @Override
    public V get(K key) {
        int index = hash(key);
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }
    @Override
    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> toRemove = null;
        for (Entry<K, V> entry : table[index]) {
            if (entry.key.equals(key)) {
                toRemove = entry;
                break;
            }
        }
        if (toRemove != null) {
            table[index].remove(toRemove);
            size--;
        }
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public List<K> keys() {
        List<K> list = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                list.add(entry.key);
            }
        }
        return list;
    }
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        size = 0;
        for (LinkedList<Entry<K, V>> bucket : oldTable) {
            for (Entry<K, V> entry : bucket) {
                put(entry.key, entry.value);
            }
        }
    }
}
