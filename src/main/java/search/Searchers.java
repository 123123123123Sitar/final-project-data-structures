package search;

import java.util.List;

/**
 * A collection of searching algorithms.
 */
public class Searchers {

    /**
     * Performs a linear search for a target string in a list.
     * 
     * @param list   the list to search
     * @param target the target string
     * @return the index of the target, or -1 if not found
     */
    public static int linearSearch(List<String> list, String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Performs a binary search for a target string in a sorted list.
     * 
     * @param list   the sorted list to search
     * @param target the target string
     * @return the index of the target, or -1 if not found
     */
    public static int binarySearch(List<String> list, String target) {
        int low = 0;
        int high = list.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = target.compareTo(list.get(mid));

            if (res == 0) {
                return mid;
            }
            if (res > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
