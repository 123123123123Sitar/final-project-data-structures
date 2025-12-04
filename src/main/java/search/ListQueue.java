package search;
import java.util.NoSuchElementException;

public class ListQueue<T> {
    private MyLinkedList<T> list;
    public ListQueue() {
        list = new MyLinkedList<>();
    }
    
    public void enqueue(T element) {
        list.add(element);
    }
    
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.remove(0);
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.get(0);
    }
    
    public int size() {
        return list.size();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
