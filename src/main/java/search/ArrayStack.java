package search;
import java.util.EmptyStackException;

public class ArrayStack<T> {
    private T[] array;
    private int size;
    private static final int INITIAL_CAPACITY = 10;
    @SuppressWarnings("unchecked")
    public ArrayStack() {
        array = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }
    
    public void push(T element) {
        if (size == array.length) {
            resize();
        }
        array[size++] = element;
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T element = array[--size];
        array[size] = null; 
        return element;
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[size - 1];
    }
    
    public int size() {
        return size;
    }
    
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
