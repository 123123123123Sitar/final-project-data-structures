package search;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println("Building index...");
            // Create the map that stores List<DocEntry> for each term
            MyMap<String, List<DocEntry>> map = new BSTMap<>();
            InvertedIndex index = new InvertedIndex(map);
            index.buildIndex("documents");
            SearchEngine engine = new SearchEngine(index);
            System.out.println("Index built. Ready for queries.");
            System.out.println("Commands: :view <doc>, :back, :forward, :history, :quit");
            System.out.println("Or type a search query.");

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("\n> ");
                String line = scanner.nextLine().trim();

                if (line.isEmpty()) {
                    continue;
                }

                if (line.equalsIgnoreCase(":quit")) {
                    break;
                } else if (line.startsWith(":view ")) {
                    String docName = line.substring(6).trim();
                    engine.viewDocument(docName);
                    System.out.println("Viewing: " + docName);
                } else if (line.equalsIgnoreCase(":back")) {
                    String doc = engine.goBack();
                    if (doc != null) {
                        System.out.println("Back to: " + doc);
                    } else {
                        System.out.println("No history to go back to.");
                    }
                } else if (line.equalsIgnoreCase(":forward")) {
                    String doc = engine.goForward();
                    if (doc != null) {
                        System.out.println("Forward to: " + doc);
                    } else {
                        System.out.println("No history to go forward to.");
                    }
                } else if (line.equalsIgnoreCase(":history")) {
                    System.out.println("Current doc: " + engine.getCurrentDoc());
                } else {
                    // Search query
                    List<String> results = engine.processQuery(line);
                    if (results.isEmpty()) {
                        System.out.println("No results found.");
                    } else {
                        System.out.println("Found in: " + results);
                    }
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println("Error building index: " + e.getMessage());
        }
    }
}
