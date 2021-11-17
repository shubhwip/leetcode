package easycard.array;

import java.util.ArrayList;
import java.util.List;

public class IntersectionArrays2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        int size1 = nums1.length;
        int size2 = nums2.length;
        int size = size1 < size2 ? size1 : size2;
        List<Integer> l = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < size2; i++) {
            for (int j = k; j < size1; j++) {
                if (nums2[i] == nums1[j]) {
                    l.add(nums2[i]);
                    nums2[i] = -1;
                    nums1[j] = -1;
                    break;
                }
            }
            if (l.size() == size1 || l.size() == size2)
                break;
        }
        return l.stream().mapToInt(i -> i).toArray();
    }
}
