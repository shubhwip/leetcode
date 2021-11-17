package mediumcard.sortingsearching;

import java.util.Arrays;

public class QuickSort {
    public static void qsort(int A[], int i, int j) {
        if (i >= j)
            return;
        int p = partition(A, i, j);
        qsort(A, i, p - 1);
        qsort(A, p + 1, j);
    }

    private static int partition(int A[], int i, int j) {
        // Select Pivot Element
        int pivot = A[i];
        int b1[] = new int[A.length];
        int b2[] = new int[A.length];
        int l = 0;
        int k = 0;
        for (int p = 1; p < A.length; p++) {
            if (A[p] >= pivot) {
                b1[l++] = A[p];
            } else {
                b2[k++] = A[p];
            }
        }
        b1[l++] = A[i];
        int z = 0;
        for (int a = 0; a < l; a++) {
            A[z++] = b1[a];
        }
        for (int a = 0; a < k; a++) {
            A[z++] = b2[a];
        }
        return l - 1;
    }

    public static void main(String[] args) {
        int a[] = new int[]{
                3, 2, 3, 1, 2, 4, 5, 5, 6
        };
        qsort(a, 0, 8);
        Arrays.stream(a).sequential().forEach(System.out::println);

    }
}
