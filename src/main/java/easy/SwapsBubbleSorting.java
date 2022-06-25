package easy;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SwapsBubbleSorting {

    public static int swaps(List<Integer> arr) {
        int swapCount = 0;
        for(int i=0;i<arr.size()-1;i++) {
            for(int j=0;j<arr.size()-i-1;j++) {
                if(arr.get(j)>arr.get(j+1)) {
                    Collections.swap(arr, j, j+1);
                    swapCount++;
                }
            }
        }
        return swapCount;
    }

    public static void main(String[] args) {
        System.out.println(swaps(Arrays.asList(8,7,1,9)));
    }
}
