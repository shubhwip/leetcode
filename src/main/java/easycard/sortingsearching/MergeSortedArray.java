package easycard.sortingsearching;

import java.util.Arrays;

public class MergeSortedArray {

    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        for (int i = m; i < nums1.length; i++) {
            nums1[i] = nums2[j];
            j++;
        }
        Arrays.sort(nums1);
    }


    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int flag = 0;

        if (n == 0) {
            return;
        }
        while (i < (m + n) && j < n) {

            if (flag == m) {
                nums1[i] = nums2[j];
                j++;
                i++;
            } else if (nums1[i] == nums2[j] || nums1[i] > nums2[j]) {
                rightShift(nums1, i);
                nums1[i] = nums2[j];
                j++;
                i++;
            } else if (nums1[i] < nums2[j]) {

                i++;
                flag++;

            }
        }
    }

    public int[] rightShift(int arr[], int index) {

        for (int i = arr.length - 1; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        return arr;
    }

    public static void shiftArray(int[] nums1, int pos) {
        for (int i = nums1.length - 1; i > pos; i--) {
            nums1[i] = nums1[i - 1];
        }
        nums1[pos] = 0;
    }

    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int tail1 = m - 1, tail2 = n - 1, finished = m + n - 1;
        while (tail1 >= 0 && tail2 >= 0) {
            nums1[finished--] = (nums1[tail1] > nums2[tail2]) ?
                    nums1[tail1--] : nums2[tail2--];
        }

        while (tail2 >= 0) {
            nums1[finished--] = nums2[tail2--];
        }
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums2.length == 0 || n == 0)
            return;
        int i = 0;
        int j = 0;
        while (i < (m + n) && j < n) {
            if (nums1[i] > nums2[j]) {
                shiftArray(nums1, i);
                nums1[i] = nums2[j];
                i++;
                j++;
            } else if ((i + j) < m && nums1[i] == 0) {
                nums1[i] = nums2[j];
                i++;
                j++;
            } else {
                i++;
            }
        }
        while (j < n) {
            nums1[i] = nums2[j];
            j++;
            i++;
        }
    }

    public static void main(String[] args) {
//        int[] nums1 = new int[]{4, 5, 6, 0, 0, 0};
//        int[] nums2 = new int[]{1, 2, 3};
//        merge(nums1, 3, nums2, 3);
//        int[] nums1 = new int[]{1, 2, 4, 5, 6, 0};
//        int[] nums2 = new int[]{3};
//        merge(nums1, 5, nums2, 1);

//        int[] nums1 = new int[]{1, 0, 0, 0, 0, 0};
//        int[] nums2 = new int[]{1, 2, 4, 5, 6};
//        merge(nums1, 1, nums2, 5);
//
//        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
//        int[] nums2 = new int[]{2, 5, 6};
//        merge(nums1, 3, nums2, 3);
//        int[] nums1 = new int[]{-1,0,0,3,3,3,0,0,0};
//        int[] nums2 = new int[]{1,2,2};
//        merge(nums1, 6, nums2, 3);

        int[] nums1 = new int[]{-1, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0};
        int[] nums2 = new int[]{-1, -1, 0, 0, 1, 2};
        merge(nums1, 5, nums2, 6);

//        int[] nums1 = new int[]{0};
//        int[] nums2 = new int[]{1};
//        merge(nums1, 0, nums2, 1);

        Arrays.stream(nums1).sequential().forEach(System.out::println);


    }
}
