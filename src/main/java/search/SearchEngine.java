package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The main search engine class handling queries and history.
 */
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

    /**
     * Processes a query and returns a ranked list of documents.
     * Documents are ranked by total frequency of all search terms.
     * For multi-term queries, only documents containing ALL terms are returned.
     * 
     * @param query the search query
     * @return a list of matching document names, ranked by relevance
     */
    public List<String> processQuery(String query) {
        String[] terms = query.split("\\s+");
        if (terms.length == 0 || query.trim().isEmpty()) {
            return new ArrayList<>();
        }

        // Map to track cumulative frequency for each document
        Map<String, Integer> docScores = new HashMap<>();
        // Map to track how many query terms each document contains
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

        // Filter to only include documents that contain ALL query terms
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

        // Sort by score (frequency) descending
        Collections.sort(results);

        // Extract just the document names
        List<String> rankedDocs = new ArrayList<>();
        for (DocEntry entry : results) {
            rankedDocs.add(entry.getDocName());
        }

        return rankedDocs;
    }

    /**
     * Simulates viewing a document.
     * 
     * @param docName the name of the document to view
     */
    public void viewDocument(String docName) {
        if (currentDoc != null) {
            backStack.push(currentDoc);
        }
        currentDoc = docName;
        while (!forwardStack.isEmpty()) {
            forwardStack.pop(); // Clear forward history on new view
        }
    }

    /**
     * Navigates back in history.
     * 
     * @return the document navigated to, or null if no history
     */
    public String goBack() {
        if (backStack.isEmpty()) {
            return null;
        }
        forwardStack.push(currentDoc);
        currentDoc = backStack.pop();
        return currentDoc;
    }

    /**
     * Navigates forward in history.
     * 
     * @return the document navigated to, or null if no history
     */
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
