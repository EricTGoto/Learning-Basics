public class SelectionSort {

    public static void sort(int[] a) {
        int length = a.length;

        for (int i = 0; i < length; i++) {
            int smallestIndex = i;
            for (int k = i + 1; k < length; k++) {
                if (a[k] < a[smallestIndex]) smallestIndex = k;
            }
            exch(a, i, smallestIndex);
        }
    }

    public static void exch(int[] a, int i, int k) {
        int temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }

    public static void main(String[] args) {
        int[] test = {3, 7, 43, 7, 88, 9, 1, 5, 6, 34, 8};
        sort(test);

        for (int i = 0; i < test.length; i++) {
            System.out.print(test[i] + " ");
        }
    }

}
