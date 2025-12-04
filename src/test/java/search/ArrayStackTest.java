package search;

import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for ArrayStack.
 */
class ArrayStackTest {

    @Test
    void testPushAndPop() {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("a");
        stack.push("b");
        assertEquals(2, stack.size());
        assertEquals("b", stack.pop());
        assertEquals("a", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void testPeek() {
        ArrayStack<String> stack = new ArrayStack<>();
        stack.push("a");
        assertEquals("a", stack.peek());
        assertEquals(1, stack.size()); // Peek doesn't remove
    }

    @Test
    void testIsEmpty() {
        ArrayStack<String> stack = new ArrayStack<>();
        assertTrue(stack.isEmpty());
        stack.push("a");
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    @Test
    void testPopEmptyStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        assertThrows(EmptyStackException.class, stack::pop);
    }

    @Test
    void testPeekEmptyStack() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        assertThrows(EmptyStackException.class, stack::peek);
    }

    @Test
    void testResizing() {
        ArrayStack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());
        assertEquals(99, stack.pop());
    }
}
