package search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds an inverted index from a directory of documents.
 */
public class InvertedIndex {

    private MyMap<String, List<DocEntry>> index;

    public InvertedIndex(MyMap<String, List<DocEntry>> mapImplementation) {
        this.index = mapImplementation;
    }

    /**
     * Builds the index from files in the specified directory.
     * 
     * @param directoryPath the path to the directory containing documents
     * @throws IOException if an I/O error occurs
     */
    public void buildIndex(String directoryPath) throws IOException {
        File folder = new File(directoryPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null) {
            return;
        }

        for (File file : files) {
            List<String> tokens = Tokenizer.tokenize(file.getPath());
            for (String token : tokens) {
                if (!index.containsKey(token)) {
                    index.put(token, new ArrayList<>());
                }
                List<DocEntry> docList = index.get(token);

                // Check if document already exists in the list for this term
                boolean found = false;
                for (DocEntry entry : docList) {
                    if (entry.getDocName().equals(file.getName())) {
                        entry.incrementFrequency();
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    docList.add(new DocEntry(file.getName(), 1));
                }
            }
        }
    }

    /**
     * Returns the list of documents containing the specified term.
     * 
     * @param term the term to search for
     * @return a list of document entries, or an empty list if not found
     */
    public List<DocEntry> search(String term) {
        if (index.containsKey(term)) {
            return index.get(term);
        }
        return new ArrayList<>();
    }
}
