package search;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    
    
    public static String normalize(String word) {
        return word.replaceAll("[^a-zA-Z0-9+]", "").toLowerCase();
    }
    
    public static List<String> tokenize(String filePath) throws IOException {
        List<String> tokens = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    String cleaned = normalize(word);
                    if (!cleaned.isEmpty()) {
                        tokens.add(cleaned);
                    }
                }
            }
        }
        return tokens;
    }
}
