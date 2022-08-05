package algorithms.sorting;

public class QSort {

    // Using Two Arrays
    // Space Complexity High O(n*eachRecursiveCall)
    // Time Complexity Best Case and Average Case O(nlogn), Worst Case o(nxn)
    static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int[] l = new int[left + right + 1];
        int[] r = new int[left + right + 1];
        int leftPointer = 0;
        for (int i = left; i < right; i++) {
            if (arr[i] <= pivot) {
                l[leftPointer++] = arr[i];
            }
        }
        int rightPointer = 0;
        for (int i = left; i < right; i++) {
            if (arr[i] > pivot) {
                r[rightPointer++] = arr[i];
            }
        }
        int i = 0;
        int k = left;
        while (i < leftPointer && k < arr.length) {
            arr[k++] = l[i++];
        }
        int pivotFinalPosition = k;
        arr[k] = pivot;
        k++;
        i = 0;
        while (i < rightPointer && k < arr.length) {
            arr[k++] = r[i++];
        }
        return pivotFinalPosition;
    }

    // This is critical algorithm for solving many problems
    // Divide and Conquer Approach
    // reduce your computational space everytime by good amount
    static int partitionEnhanced(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (arr[j] >= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        return i + 1;
    }

    static void quicksort(int[] arr, int left, int right) {
        if (left < right) {
            int pivotFinalRestingPosition = partitionEnhanced(arr, left, right);
            quicksort(arr, left, pivotFinalRestingPosition - 1);
            quicksort(arr, pivotFinalRestingPosition + 1, right);
        }
    }

    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    public static void main(String[] args) {
        //int[] a = new int[]{55,15,23,117,87,6,10};
        //int[] a = new int[]{9,8,7,6,5,4,3,2,1};
        int[] a = new int[]{5, 3, 2, 3, 4, 4, 6, 1};
        quicksort(a, 0, a.length - 1);
        for (int p : a) {
            System.out.println(p);
        }
    }

}
