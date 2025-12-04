package search;

import java.util.List;

/**
 * A collection of sorting algorithms for lists of strings.
 */
public class Sorters {

    /**
     * Sorts a list of strings using Merge Sort.
     * 
     * @param list the list to sort
     */
    public static void mergeSort(List<String> list) {
        if (list.size() <= 1) {
            return;
        }
        int mid = list.size() / 2;
        List<String> left = list.subList(0, mid);
        List<String> right = list.subList(mid, list.size());

        // Note: subList returns a view, so we need to copy if we want to sort
        // recursively in place
        // But for simplicity with List interface, we can just recursively call and then
        // merge.
        // However, standard merge sort on arrays is easier. On Lists, it's a bit tricky
        // with subList views.
        // Let's create copies to be safe and clear.
        java.util.ArrayList<String> leftCopy = new java.util.ArrayList<>(left);
        java.util.ArrayList<String> rightCopy = new java.util.ArrayList<>(right);

        mergeSort(leftCopy);
        mergeSort(rightCopy);

        merge(list, leftCopy, rightCopy);
    }

    private static void merge(List<String> result, List<String> left, List<String> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                result.set(k++, left.get(i++));
            } else {
                result.set(k++, right.get(j++));
            }
        }
        while (i < left.size()) {
            result.set(k++, left.get(i++));
        }
        while (j < right.size()) {
            result.set(k++, right.get(j++));
        }
    }

    /**
     * Sorts a list of strings using Quick Sort.
     * 
     * @param list the list to sort
     */
    public static void quickSort(List<String> list) {
        quickSort(list, 0, list.size() - 1);
    }

    private static void quickSort(List<String> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private static int partition(List<String> list, int low, int high) {
        String pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).compareTo(pivot) <= 0) {
                i++;
                swap(list, i, j);
            }
        }
        swap(list, i + 1, high);
        return i + 1;
    }

    private static void swap(List<String> list, int i, int j) {
        String temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
