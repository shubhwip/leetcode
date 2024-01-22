package lc75.twopointers;

public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length-1;
        int maxArea = Integer.MIN_VALUE;
        while(i < j) {
            int min = Integer.MAX_VALUE;
            int diff = (j-i);
            if(height[i] < height[j]) {
                min = height[i];
                i++;
            } else {
                min = height[j];
                j--;
            }
            int area = diff*(min);
            maxArea = Math.max(maxArea, area);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1,8,6,2,5,4,8,3,7}));
    }
}
