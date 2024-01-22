package lc75.arraystring;

import java.util.ArrayList;
import java.util.List;

public class KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> kidsWithCandiesMax = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(int candy : candies) {
            max = Math.max(candy, max);
        }
        for(int i=0;i<candies.length; i++) {
            kidsWithCandiesMax.add(i, candies[i] + extraCandies >= max ? true : false);
        }
        return kidsWithCandiesMax;
    }
}
