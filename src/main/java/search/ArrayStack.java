package search;

import java.util.EmptyStackException;

/**
 * A stack implementation using an array.
 * @param <T> the type of elements in the stack
 */
public class ArrayStack<T> {

    private T[] array;
    private int size;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public ArrayStack() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Pushes an element onto the stack.
     * @param element the element to push
     */
    public void push(T element) {
        if (size == array.length) {
            resize();
        }
        array[size++] = element;
    }

    /**
     * Removes and returns the element at the top of the stack.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = array[--size];
        array[size] = null; // Avoid memory leak
        return element;
    }

    /**
     * Returns the element at the top of the stack without removing it.
     * @return the element at the top of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[size - 1];
    }

    /**
     * Returns the number of elements in the stack.
     * @return the number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the stack is empty.
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newArray = (T[]) new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}
