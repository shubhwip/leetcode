package easy;

import java.util.ArrayList;
import java.util.List;

public class MinStack {
    List<Integer> nums;
    int min = Integer.MAX_VALUE;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        this.nums = new ArrayList<>();
    }

    public void push(int val) {
        if (val <= min) {
            nums.add(min);
            min = val;
        }
        nums.add(val);
    }

    public void pop() {
        int peek = nums.get(nums.size() - 1);
        nums.remove(nums.size() - 1);
        if (peek == min) {
            min = nums.get(nums.size() - 1);
            nums.remove(nums.size() - 1);
        }
    }

    public int top() {
        return nums.get(nums.size() - 1);
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2
    }
}
