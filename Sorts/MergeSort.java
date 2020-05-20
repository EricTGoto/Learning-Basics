// implementation of merge sort
public class MergeSort {

    public static void merge(Comparable[] array, Comparable[] aux, int low, int high) {

        for (int k = 0; k < array.length; k++) {
            aux[k] = array[k];
        }

        int mid = low + (high - low) / 2;
        int i = low;
        int j = mid + 1;
        for (int k = low; k < array.length; k++) {
            if (i > mid) array[k] = aux[j++];
            else if (j > high) array[k] = aux[i++];
            else if (less(aux[j], aux[i]) < 0) array[k] = aux[j++];
            else array[k] = aux[i++];
        }
    }

    private static void sort(Comparable[] array, Comparable[] aux, int low, int high) {
        if (high <= low) return;
        int mid = low + (high - low) / 2;
        sort(array, aux, low, mid);
        sort(array, aux, mid, high);
        merge(array, aux, low, high);
    }

    public static void sort(Comparable[] array) {
        int high = array.length - 1;
        Comparable[] aux = new Comparable[array.length];

        sort(array, aux, 0, high);
    }

    private static int less(Comparable a, Comparable b) {
        return a.compareTo(b);
    }

    public static void main(String[] args) {
        Integer[] a = {1, 43, 56, 2, 4, 67, 2, 7, 567, 9, 335, 42, 2, 574, 3, 2, 7, 89, 2, 134, 34, 457, 2, 46, 7, 0};
        sort(a);
        System.out.print(a);

    }
}
