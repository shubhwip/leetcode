package mediumcard.sortingsearching;

public class SearchForARange {

    public static int[] searchRange1(int[] nums, int target) {
        int[] a = new int[2];
        int i = findInitialPositionOfTarget(nums, target);
        int j = findLastPositionOfTarget(nums, target);
        a[0] = i;
        a[1] = j;
        return a;
    }

    public static int[] searchRange(int[] nums, int target) {
        int[] a = new int[2];
        int i = findTarget(nums, target);
        if (i != -1) {
            int p = i;
            while (p > 0 && nums[p] == target) {
                p--;
            }
            int q = i;
            while (q < nums.length - 1 && nums[q] == target) {
                q++;
            }
            a[0] = p - 1;
            a[1] = q - 1;
        } else {
            a[0] = -1;
            a[1] = -1;
        }
        return a;
    }

    public static int findTarget(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static int findInitialPositionOfTarget(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (mid > 0 && nums[mid] == target && nums[mid - 1] != target)
                return mid;
            else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static int findLastPositionOfTarget(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            if (mid < nums.length - 1 && nums[mid] == target && nums[mid + 1] != target)
                return mid;
            else if (target >= nums[mid]) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(searchRange1(new int[]{5, 7, 7, 8, 8, 10}, 8));
    }
}
