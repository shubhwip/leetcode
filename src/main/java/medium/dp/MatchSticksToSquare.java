package medium.dp;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MatchSticksToSquare {

    List<Integer> sticks;
    int[] sides;
    int possibleSquareSide = -1;

    public boolean findCanMakeSquare(int index) {
        if(index == sticks.size()) {
            if(sides[0] == sides[1] && sides[1] == sides[2] && sides[2] == sides[3])
                return true;
            else
                return false;
        }
        int element = sticks.get(index);
        for(int i=0;i<4;i++) {
            if(sides[i] + element <= possibleSquareSide) {
                sides[i] += element;
                if(findCanMakeSquare(index+1))
                    return true;
                sides[i] -= element;
            }
            if(sides[i] == 0)
                break;
        }
        return false;
    }

    public boolean makesquare(int[] matchsticks) {
        // Cut array into 4 parts
        sides = new int[4];
        sticks = Arrays.stream(matchsticks).boxed().collect(Collectors.toList());
        Collections.sort(this.sticks, Collections.reverseOrder());

        int matchSticksLen = matchsticks.length;
        int matchSticksSum = 0;
        for(int n : matchsticks) {
            matchSticksSum += n;
        }
        if(matchSticksLen<4 || matchSticksSum % 4 != 0)
            return false;
        possibleSquareSide = matchSticksSum/4;
        return findCanMakeSquare(0);
    }
}
