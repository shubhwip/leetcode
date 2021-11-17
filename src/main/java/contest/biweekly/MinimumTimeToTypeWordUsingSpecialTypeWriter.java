package contest.biweekly;

// https://leetcode.com/problems/minimum-time-to-type-word-using-special-typewriter/
public class MinimumTimeToTypeWordUsingSpecialTypeWriter {

    public static int minTimeToType(String word) {
        int sum = 0;
        int dis = (word.charAt(0) - 'a');
        if (dis < 0)
            dis = 0 - dis;
        if (dis > 13)
            dis = 26 - dis;
        sum += dis;
        for (int i = 0; i < word.length() - 1; i++) {
            dis = (word.charAt(i + 1) - word.charAt(i));
            if (dis < 0)
                dis = 0 - dis;
            if (dis > 13)
                dis = 26 - dis;
            sum += dis;
        }
        return sum + word.length();
    }

    public static void main(String[] args) {
        System.out.println(minTimeToType("zjpc"));
    }
}
