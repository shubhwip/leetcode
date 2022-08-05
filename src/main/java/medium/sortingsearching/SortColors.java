package medium.sortingsearching;

public class SortColors {
    public int partition(int[] nums, int left, int right, int pivot) {
        int i = left -1;
        for(int j=left;j<right;j++) {
            if(nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        return i+1;
    }

    public void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    public void sortColors(int[] nums) {
        //sortColorsNaive(nums);
        if(nums.length == 1)
            return;
        partition(nums, 0, nums.length, 0);
        partition(nums, 0, nums.length, 1);
        partition(nums, 0, nums.length, 2);
    }

    public void sortColorsNaive(int[] nums) {
        int zeroes = 0;
        int ones = 0;
        int twos = 0;
        for(int n : nums ){
            if(n == 0)
                zeroes++;
            else if(n == 1)
                ones++;
            else if(n==2)
                twos++;
        }
        int i=0;
        int k=0;
        while(i<zeroes) {
            nums[k] = 0;
            i++;
            k++;
        }
        i=0;
        while(i<ones) {
            nums[k] = 1;
            i++;
            k++;
        }
        i=0;
        while(i<twos) {
            nums[k] = 2;
            i++;
            k++;
        }
    }
}
