package search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A tokenizer that reads a text file and splits it into words.
 */
public class Tokenizer {

    /**
     * Tokenizes a file into a list of words.
     * Words are converted to lowercase and punctuation is removed.
     * 
     * @param filePath the path to the file to tokenize
     * @return a list of tokens
     * @throws IOException if an I/O error occurs
     */
    /**
     * Normalizes a word by removing invalid characters and converting to lowercase.
     * 
     * @param word the word to normalize
     * @return the normalized word
     */
    public static String normalize(String word) {
        return word.replaceAll("[^a-zA-Z0-9+]", "").toLowerCase();
    }

    /**
     * Tokenizes a file into a list of words.
     * Words are converted to lowercase and punctuation is removed.
     * 
     * @param filePath the path to the file to tokenize
     * @return a list of tokens
     * @throws IOException if an I/O error occurs
     */
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
