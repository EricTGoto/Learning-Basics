// like insertion sort but with multiple subsequences
// this implementation will use h=3x+1
public class ShellSort {

    public static void sort(int[] a) {
        int length = a.length;

        int h = 1;
        while (h * 3 + 1 < length) h = h * 3 + 1; //calculates the biggest possible value of h

        for (int z = h; z >= 1; z /= 3) {
            for (int i = z; i < length; i++) {
                for (int k = i; k >= h && a[k] < a[k - z]; k -= z) {
                    exch(a, k, k - z);
                }
            }
            h /= 3;
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
