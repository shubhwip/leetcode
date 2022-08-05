package medium.search;

import java.util.Arrays;

public class KokoEatingBananas {

    public static boolean feasible(int[] piles, int speed, int h) {
        int sum = 0;
        for(int pile : piles) {
            float ceil =(float) pile / (float)speed;
            int a = (int) Math.ceil(ceil);
            sum += a;
        }
        return sum <= h;

//        int sum = Arrays.stream(piles).map(pile -> (int)((pile/speed) + 0.5)).sum();
//        return sum <= h;
    }

    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = Arrays.stream(piles).max().getAsInt();
        while(low < high) {
            int mid = (low+high)/2;
            if(feasible(piles, mid, h))
                high = mid + 1;
            else
                low = mid + 1;
        }
        return low;
    }

    public static void main(String[] args) {
        //System.out.println(minEatingSpeed(new int[]{30,11,23,4,20}, 5));
        System.out.println(minEatingSpeed(new int[]{1000000000}, 2));
    }
}
