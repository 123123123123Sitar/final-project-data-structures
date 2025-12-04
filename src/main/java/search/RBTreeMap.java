package search;

public class RBTreeMap<K extends Comparable<K>, V> extends BSTMap<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
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
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        return node;
    }
    private boolean isRed(Node<K, V> node) {
        if (node == null) {
            return BLACK;
        }
        return node.color == RED;
    }
    private Node<K, V> rotateLeft(Node<K, V> h) {
        Node<K, V> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private Node<K, V> rotateRight(Node<K, V> h) {
        Node<K, V> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private void flipColors(Node<K, V> h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
}
