package search;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SearchEngineTest {
    private SearchEngine engine;
    @BeforeEach
    void setup() throws IOException {
        MyMap<String, List<DocEntry>> map = new BSTMap<>();
        InvertedIndex index = new InvertedIndex(map);
        index.buildIndex("documents");
        engine = new SearchEngine(index);
    }
    @Test
    void testSingleTermQueryFox() {
        List<String> results = engine.processQuery("fox");
        assertTrue(results.contains("doc1.txt"));
        assertTrue(results.contains("doc2.txt"));
        assertEquals(2, results.size());
    }
    @Test
    void testSingleTermQueryCasInsensitive() {
        List<String> results = engine.processQuery("C++");
        assertTrue(results.contains("doc3.txt"));
        assertEquals(1, results.size());
    }
    @Test
    void testSingleTermQueryWorld() {
        List<String> results = engine.processQuery("world");
        assertTrue(results.contains("doc4.txt"));
        assertEquals(1, results.size());
    }
    @Test
    void testMultiWordQueryQuickFox() {
        List<String> results = engine.processQuery("quick fox");
        assertTrue(results.contains("doc1.txt"));
        assertTrue(results.contains("doc2.txt"));
        assertEquals(2, results.size());
    }
    @Test
    void testMultiWordQueryLazyDog() {
        List<String> results = engine.processQuery("lazy dog");
        assertTrue(results.contains("doc2.txt"));
        assertEquals(1, results.size());
    }
    @Test
    void testMultiWordQueryFastProgram() {
        List<String> results = engine.processQuery("fast program");
        assertTrue(results.contains("doc3.txt"));
        assertEquals(1, results.size());
    }
    @Test
    void testMultiWordMiss() {
        List<String> results = engine.processQuery("hello fox");
        assertTrue(results.isEmpty());
    }
    @Test
    void testQueryMiss() {
        List<String> results = engine.processQuery("zzz");
        assertTrue(results.isEmpty());
    }
    @Test
    void testBackAndForward() {
        engine.viewDocument("doc1.txt");
        engine.viewDocument("doc3.txt");
        assertEquals("doc3.txt", engine.getCurrentDoc());
        assertEquals("doc1.txt", engine.goBack());
        assertEquals("doc1.txt", engine.getCurrentDoc());
        assertEquals("doc3.txt", engine.goForward());
        assertEquals("doc3.txt", engine.getCurrentDoc());
        assertNull(engine.goForward()); 
    }
    @Test
    void testNewViewClearsForwardHistory() {
        engine.viewDocument("doc1.txt");
        engine.viewDocument("doc2.txt");
        engine.viewDocument("doc4.txt");
        assertEquals("doc2.txt", engine.goBack());
        engine.viewDocument("doc3.txt"); 
        assertNull(engine.goForward());
    }
}
