package search;

import java.util.ArrayList;
import java.util.List;

/**
 * A Binary Search Tree implementation of MyMap.
 * 
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class BSTMap<K extends Comparable<K>, V> implements MyMap<K, V> {

    protected Node<K, V> root;
    protected int size;

    protected static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;
        int height; // For AVL
        boolean color; // For Red-Black (true = red, false = black)

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.color = true;
        }
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node<K, V> put(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return node;
    }

    @Override
    public V get(K key) {
        Node<K, V> node = get(root, key);
        return node == null ? null : node.value;
    }

    private Node<K, V> get(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public void remove(K key) {
        if (containsKey(key)) {
            root = remove(root, key);
            size--;
        }
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node<K, V> t = node;
            node = min(t.right);
            node.right = removeMin(t.right);
            node.left = t.left;
        }
        return node;
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
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
        inorder(root, list);
        return list;
    }

    private void inorder(Node<K, V> node, List<K> list) {
        if (node == null) {
            return;
        }
        inorder(node.left, list);
        list.add(node.key);
        inorder(node.right, list);
    }
}
