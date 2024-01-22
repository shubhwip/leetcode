package lc75.prefixsum;

public class FindHighestAltitude {
    // 0
    //ms
    //Beats
    //100.00%
    //of users with Java
    public int largestAltitude(int[] gain) {
        int highest = 0;
        int prefixSum = 0;
        for(int g : gain) {
            prefixSum += g;
            highest = Math.max(highest, prefixSum);

        }
        return highest;
    }
}
