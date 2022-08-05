package hard.greedy;

public class Candy {

    public static int candy(int[] ratings) {
        int len = ratings.length;
        int[] res = new int[len];
        int sum = 0;
        for(int i = 1; i < len; i++){
            if(ratings[i] > ratings[i - 1]) res[i] = res[i - 1] + 1;
        }
        for(int i = len - 2; i >= 0; i--){
            if(ratings[i] > ratings[i + 1]) res[i] = Math.max(res[i], res[i + 1] + 1);
            sum += res[i];
        }
        return sum + res[len - 1] + len;
    }

    // Passed 33/48 test cases
    public static int candy2(int[] ratings) {
        int ratingsLen = ratings.length;
        int minCandies = ratingsLen;
        int prevNeighbourCandies = 0;
        int nextNeighbourCandies = 0;
        if(ratingsLen == 1)
            return 1;

        for(int i=0;i<ratingsLen;i++) {
            if(i==0) {
                if(ratings[i] > ratings[i+1] ) {
                    nextNeighbourCandies += 1;
                    minCandies += nextNeighbourCandies;
                }
            } else if(i == ratingsLen - 1) {
                if(ratings[i] > ratings[i-1] ) {
                    prevNeighbourCandies += 1;
                    minCandies += prevNeighbourCandies;
                }
            } else {
                if(ratings[i] > ratings[i+1] && ratings[i] > ratings[i-1]) {
                    if(nextNeighbourCandies > prevNeighbourCandies) {
                        nextNeighbourCandies += 1;
                        minCandies += nextNeighbourCandies;
                    } else {
                        prevNeighbourCandies += 1;
                        minCandies += prevNeighbourCandies;
                    }
                }
                else if(ratings[i] > ratings[i+1]) {
                    nextNeighbourCandies += 1;
                    minCandies += nextNeighbourCandies;
                } else if(ratings[i] > ratings[i-1]) {
                    prevNeighbourCandies += 1;
                    minCandies += prevNeighbourCandies;
                } else {
                    nextNeighbourCandies = 0;
                    prevNeighbourCandies = 0;
                }
            }
        }

        return minCandies;
    }

    // passed 29 / 48 test cases passed first time
    public static int candy1(int[] ratings) {
        int ratingsLen = ratings.length;
        int minCandies = ratingsLen;
        int prevCandyCount = 0;
        if(ratingsLen == 1)
            return 1;

        for(int i=0;i<ratingsLen;i++) {
            if(i==0) {
                if(ratings[i] > ratings[i+1] ) {
                    prevCandyCount += 1;
                    minCandies += prevCandyCount;
                }
            } else if(i == ratingsLen - 1) {
                if(ratings[i] > ratings[i-1] ) {
                    prevCandyCount += 1;
                    minCandies += prevCandyCount;
                }
            } else {
                if(ratings[i] > ratings[i+1] || ratings[i] > ratings[i-1]) {
                    prevCandyCount += 1;
                    minCandies += prevCandyCount;
                } else {
                    prevCandyCount = 0;
                }
            }
        }

        return minCandies;
    }

    public static void main(String[] args) {
        System.out.println(candy(new int[]{1,6,10,8,7,3,2}));
    }
}
