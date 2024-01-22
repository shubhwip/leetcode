package medium.search;

import java.util.Arrays;

public class InsertIntervals {

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int rows = intervals.length;
        int start = newInterval[0];
        int end = newInterval[1];
        int[][] result = new int[rows][2];
        int p = 0;
        int endPosFound = -1;
        int startPosFound = -1;
        int discarded = 0;
        for(int i=0;i <rows; i++) {
            if(intervals[i][0] < start && intervals[i][1] < start) {
                result[p] = intervals[i];
                p++;
            } else if (intervals[i][0] > end && intervals[i][1] > end) {
                endPosFound = intervals[i-1][1];
                System.out.println("end" + endPosFound);
                result[p][0] = startPosFound;
                result[p][1] = Integer.max(intervals[i-1][1], end);
                p++;
                result[p] = intervals[i];
                p++;

            }
            else if(start > intervals[i][0] && start < intervals[i][1]) {
                startPosFound = intervals[i][0];
                System.out.println(startPosFound);
            }
            discarded++;
        }
        return Arrays.copyOf(result, rows-discarded);
    }

    public static int bSearch(int[][] intervals, int searchNumber) {
        int rows = intervals.length;
        int low = 0;
        int high = rows*2;
        while(low < high) {
            int mid = (low+high)/2;
            int start = intervals[mid][0];
            int end = intervals[mid][1];

        }
        return -1;
    }

    public static int[][] insert1(int[][] intervals, int[] newInterval) {
        int rows = intervals.length;
        int newStartLow = 0;
        int newStartHigh = rows - 1;
        int newEndLow = 0;
        int newEndHigh = rows - 1;
        int startIntervalPos = -1;
        int endIntervalPos = -1;
        while(newStartLow < newStartHigh ) {
            int mid = newStartLow + (newStartHigh-newStartLow)/2;
            int start = intervals[mid][0];
            int end = intervals[mid][1];
            if(newInterval[0] >= start && newInterval[0] <= end) {
                startIntervalPos = mid;
                break;
            } else if(newInterval[0] > start) {
                newStartLow = mid - 1;
            } else {
                newStartHigh = mid + 1;
            }
        }
        System.out.println(startIntervalPos);
        while(newEndLow < newEndHigh ) {
            int mid = newEndLow + (newEndHigh-newEndLow)/2;
            int start = intervals[mid][0];
            int end = intervals[mid][1];
            if(newInterval[1] >= start && newInterval[1] <= end) {
                endIntervalPos = mid;
                break;
            } else if(newInterval[1] > start) {
                newEndLow = mid;
            } else {
                newEndHigh = mid + 1;
            }
        }
        System.out.println(endIntervalPos);
        return new int[][]{{-1, -1}};
    }

    public static void main(String[] args) {
        System.out.println(insert(new int[][]{{1,3},{6,9}}, new int[]{2,5}));
    }
}
