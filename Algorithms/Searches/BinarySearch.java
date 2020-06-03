// Implementation of binary search
// By Eric Goto
public class BinarySearch {

    private boolean search(int[] array, int n) {

        int length = array.length;
        int hi = length - 1;
        int lo = 0;
        int mid = lo + (hi - lo) / 2;

        while (mid <= hi) {
            if (n > array[mid]) {
                lo = mid + 1;
                mid = lo + (hi - lo) / 2;
                continue;
            } else if (n < array[mid]) {
                hi = mid - 1;
                mid = lo + (hi - lo) / 2;
                continue;
            } else return true;
        }
        return false;
    }

    // testing
    public static void main(String[] args) {
        BinarySearch b = new BinarySearch();
        int[] a = {1, 3, 4, 6, 7, 9, 12};

        System.out.println(b.search(a, 1));
        System.out.println(b.search(a, 2));
        System.out.println(b.search(a, 6));
        System.out.println(b.search(a, 8));
        System.out.println(b.search(a, 13));

    }
}
