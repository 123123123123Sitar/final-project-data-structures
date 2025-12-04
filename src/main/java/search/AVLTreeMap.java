package search;

/**
 * An AVL Tree implementation of MyMap.
 * 
 * @param <K> the type of keys
 * @param <V> the type of values
 */
public class AVLTreeMap<K extends Comparable<K>, V> extends BSTMap<K, V> {

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
            return node;
        }
        updateHeight(node);
        return balance(node);
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
        updateHeight(node);
        return balance(node);
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
        updateHeight(node);
        return balance(node);
    }

    private int height(Node<K, V> node) {
        return node == null ? 0 : node.height;
    }

    private void updateHeight(Node<K, V> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int balanceFactor(Node<K, V> node) {
        return height(node.left) - height(node.right);
    }

    private Node<K, V> balance(Node<K, V> node) {
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> y) {
        Node<K, V> x = y.left;
        Node<K, V> T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    private Node<K, V> rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right;
        Node<K, V> T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }
}
