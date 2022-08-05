package medium.backtracking;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Missed Optimization - https://leetcode.com/problems/partition-to-k-equal-sum-subsets/discuss/1772704/Java-Solution-(with-comments)-oror-100-faster-oror-1ms
public class PartitionWithKEqualSubsets {
    List<Integer> numbers;
    int[] subsets;
    int possibleValue;
    int z;

    // 153/162 Test Cases Passed In One Go - Happy Me
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int numsLen = nums.length;
        z = k;
        if(nums == null || numsLen == 0 || numsLen < k)
            return false;
        numbers = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(numbers, Collections.reverseOrder());
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % k != 0)
            return false;
        subsets = new int[k];
        possibleValue = sum/k;
        return canPartition1(0);
    }

    public boolean canPartition1(int index) {
        if(index == numbers.size()) {
            for(int i=1;i<z;i++) {
                if(subsets[i] != subsets[i-1])
                    return false;
            }
            return true;
        }

        int number = numbers.get(index);

        for(int i=0;i<z;i++) {
            if(subsets[i] + number <= possibleValue) {
                subsets[i] += number;
                if(canPartition1(index+1))
                    return true;
                subsets[i] -= number;
            }
            // This condition led to AC Solution
            if(subsets[i] == 0)
                break;
        }
        return false;
    }

    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int sum = 0;
        int[] arrDesc = Arrays.stream(nums).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        for(int num:nums)sum += num;
        if(k <= 0 || sum%k != 0)return false;
        int[] visited = new int[nums.length];
        return canPartition(arrDesc, visited, 0, k, 0, 0, sum/k);
    }

    public boolean canPartition(int[] nums, int[] visited, int start_index, int k, int cur_sum, int cur_num, int target){
        if(k==1)return true;
        if(cur_sum == target && cur_num>0)return canPartition(nums, visited, 0, k-1, 0, 0, target);
        for(int i = start_index; i<nums.length; i++){
            if(visited[i] == 0){
                visited[i] = 1;
                if(canPartition(nums, visited, i+1, k, cur_sum + nums[i], cur_num++, target))return true;
                visited[i] = 0;
            }
        }
        return false;
    }

}
