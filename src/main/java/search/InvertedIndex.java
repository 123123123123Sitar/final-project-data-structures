package search;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class InvertedIndex {
    private MyMap<String, List<DocEntry>> index;
    public InvertedIndex(MyMap<String, List<DocEntry>> mapImplementation) {
        this.index = mapImplementation;
    }
    
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
    
    public List<DocEntry> search(String term) {
        if (index.containsKey(term)) {
            return index.get(term);
        }
        return new ArrayList<>();
    }
}
