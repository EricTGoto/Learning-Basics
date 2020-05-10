public class InsertionSort {

    public static void sort(int[] a) {
        int length = a.length;

        for (int i = 1; i < length; i++) {
            for (int k = i; k > 0; k--) {
                if (a[k] < a[k - 1]) exch(a, k - 1, k);
            }
        }

    }

    public static void exch(int[] a, int i, int k) {
        int temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }

    public static void main(String[] args) {
        int[] test = {1, 7, 43, 7, 88, 9, 3, 5, 6, 34, 8};
        sort(test);

        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }

    }
}
