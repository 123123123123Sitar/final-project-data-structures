package search;

import java.util.NoSuchElementException;

/**
 * A queue implementation using MyLinkedList.
 * @param <T> the type of elements in the queue
 */
public class ListQueue<T> {

    private MyLinkedList<T> list;

    public ListQueue() {
        list = new MyLinkedList<>();
    }

    /**
     * Adds an element to the back of the queue.
     * @param element the element to add
     */
    public void enqueue(T element) {
        list.add(element);
    }

    /**
     * Removes and returns the element at the front of the queue.
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.remove(0);
    }

    /**
     * Returns the element at the front of the queue without removing it.
     * @return the element at the front of the queue
     * @throws NoSuchElementException if the queue is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return list.get(0);
    }

    /**
     * Returns the number of elements in the queue.
     * @return the number of elements
     */
    public int size() {
        return list.size();
    }

    /**
     * Checks if the queue is empty.
     * @return true if the queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
