// like insertion sort but with multiple subsequences
// this implementation will use h=3x+1
public class ShellSort {

    public static void sort(int[] a) {
        int length = a.length;
        int h = (length / 3) * 3 + 1; //dividing by 3 and then multiplying may look redundant but h is an integer

        for (int z = h; z >= 1; z -= 3) {
            for (int i = 0; i < length; i++) {
                for (int k = i + z; k < length; k += z) {
                    for (int r = k; r - z >= 0; r -= z) {
                        if (a[r] < a[r - z]) exch(a, r, r - z);
                    }
                }
            }
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
