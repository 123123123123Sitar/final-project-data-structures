package search;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Tokenizer.
 */
class TokenizerTest {

    @Test
    void testNormalize() {
        assertEquals("hello", Tokenizer.normalize("Hello"));
        assertEquals("hello", Tokenizer.normalize("HELLO!"));
        assertEquals("c++", Tokenizer.normalize("C++"));
        assertEquals("world", Tokenizer.normalize("(world)"));
        assertEquals("test", Tokenizer.normalize("test..."));
    }

    @Test
    void testTokenizeDoc1() throws IOException {
        List<String> tokens = Tokenizer.tokenize("documents/doc1.txt");
        assertEquals(List.of("the", "quick", "brown", "fox"), tokens);
    }

    @Test
    void testTokenizeDoc2() throws IOException {
        List<String> tokens = Tokenizer.tokenize("documents/doc2.txt");
        assertEquals(List.of("the", "quick", "dog", "and", "the", "lazy", "fox"), tokens);
    }

    @Test
    void testTokenizeDoc3() throws IOException {
        List<String> tokens = Tokenizer.tokenize("documents/doc3.txt");
        assertEquals(List.of("a", "fast", "c++", "program", "is", "a", "good", "c++", "program"), tokens);
    }

    @Test
    void testTokenizeDoc4() throws IOException {
        List<String> tokens = Tokenizer.tokenize("documents/doc4.txt");
        assertEquals(List.of("hello", "world", "hello", "world"), tokens);
    }

    @Test
    void testTokenizeEmpty() throws IOException {
        List<String> tokens = Tokenizer.tokenize("documents/empty.txt");
        assertTrue(tokens.isEmpty());
    }
}
