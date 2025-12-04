package search;
import java.util.List;

public class Searchers {
    
    public static int linearSearch(List<String> list, String target) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
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
