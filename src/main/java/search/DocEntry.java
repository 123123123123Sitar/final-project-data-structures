package search;

/**
 * Represents a document entry in the inverted index, storing the document name
 * and term frequency.
 */
public class DocEntry implements Comparable<DocEntry> {
    private String docName;
    private int frequency;

    public DocEntry(String docName, int frequency) {
        this.docName = docName;
        this.frequency = frequency;
    }

    public String getDocName() {
        return docName;
    }

    public int getFrequency() {
        return frequency;
    }

    public void incrementFrequency() {
        this.frequency++;
    }

    @Override
    public int compareTo(DocEntry other) {
        // Sort by frequency descending, then by name ascending
        int freqCompare = Integer.compare(other.frequency, this.frequency);
        if (freqCompare != 0) {
            return freqCompare;
        }
        return this.docName.compareTo(other.docName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        DocEntry other = (DocEntry) obj;
        return docName.equals(other.docName);
    }

    @Override
    public String toString() {
        return docName + "(" + frequency + ")";
    }
}
