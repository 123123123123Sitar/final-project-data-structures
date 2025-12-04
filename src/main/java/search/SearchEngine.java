package search;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchEngine {
    private InvertedIndex index;
    private ArrayStack<String> backStack;
    private ArrayStack<String> forwardStack;
    private String currentDoc;
    public SearchEngine(InvertedIndex index) {
        this.index = index;
        this.backStack = new ArrayStack<>();
        this.forwardStack = new ArrayStack<>();
        this.currentDoc = null;
    }
    
    public List<String> processQuery(String query) {
        String[] terms = query.split("\\s+");
        if (terms.length == 0 || query.trim().isEmpty()) {
            return new ArrayList<>();
        }
        Map<String, Integer> docScores = new HashMap<>();
        Map<String, Integer> termCount = new HashMap<>();
        for (String rawTerm : terms) {
            String term = Tokenizer.normalize(rawTerm);
            if (term.isEmpty())
                continue;
            List<DocEntry> entries = index.search(term);
            for (DocEntry entry : entries) {
                String docName = entry.getDocName();
                docScores.put(docName, docScores.getOrDefault(docName, 0) + entry.getFrequency());
                termCount.put(docName, termCount.getOrDefault(docName, 0) + 1);
            }
        }
        int numTerms = 0;
        for (String rawTerm : terms) {
            if (!Tokenizer.normalize(rawTerm).isEmpty()) {
                numTerms++;
            }
        }
        List<DocEntry> results = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : docScores.entrySet()) {
            String docName = entry.getKey();
            if (termCount.get(docName) == numTerms) {
                results.add(new DocEntry(docName, entry.getValue()));
            }
        }
        Collections.sort(results);
        List<String> rankedDocs = new ArrayList<>();
        for (DocEntry entry : results) {
            rankedDocs.add(entry.getDocName());
        }
        return rankedDocs;
    }
    
    public void viewDocument(String docName) {
        if (currentDoc != null) {
            backStack.push(currentDoc);
        }
        currentDoc = docName;
        while (!forwardStack.isEmpty()) {
            forwardStack.pop(); 
        }
    }
    
    public String goBack() {
        if (backStack.isEmpty()) {
            return null;
        }
        forwardStack.push(currentDoc);
        currentDoc = backStack.pop();
        return currentDoc;
    }
    
    public String goForward() {
        if (forwardStack.isEmpty()) {
            return null;
        }
        backStack.push(currentDoc);
        currentDoc = forwardStack.pop();
        return currentDoc;
    }
    public String getCurrentDoc() {
        return currentDoc;
    }
}
