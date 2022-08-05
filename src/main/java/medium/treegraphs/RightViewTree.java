package medium.treegraphs;

import java.util.ArrayList;
import java.util.List;

// Amazing Solutions
// DFS - https://leetcode.com/problems/binary-tree-right-side-view/discuss/56012/My-simple-accepted-solution(JAVA)
// Divide and Conquer - https://leetcode.com/problems/binary-tree-right-side-view/discuss/56062/Java-Solution-using-Divide-and-Conquer
// Iterative/BFS/Level Order Traversal - https://leetcode.com/problems/binary-tree-right-side-view/discuss/56230/Share-my-Java-iterative-solution-based-on-level-order-traversal

public class RightViewTree {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        rightView(root, result, 0);
        return result;
    }

    // What an approach
    public void rightView(TreeNode curr, List<Integer> result, int currDepth){
        if(curr == null){
            return;
        }
        if(currDepth == result.size()){
            result.add(curr.val);
        }

        rightView(curr.right, result, currDepth + 1);
        rightView(curr.left, result, currDepth + 1);
    }

    public List<Integer> rightSideView1(TreeNode root) {
        List<Integer> rightSideView = new ArrayList<>();
        if(root == null)
            return rightSideView;
        rightSideView.add(root.val);
        int rightTreeDepth = findDepthTree(root.right);
        int leftTreeDepth = findDepthTree(root.left);
        if(rightTreeDepth > 0)
            findRightSideViewRecursionFromRight(root.right, rightSideView, false);
        if(rightTreeDepth < leftTreeDepth && rightTreeDepth == 0)
            findRightSideViewRecursionFromLeft(root.left, rightSideView, false, rightTreeDepth, 0);
        else if(rightTreeDepth < leftTreeDepth) {
            findRightSideViewRecursionFromLeft(root.left, rightSideView, true, rightTreeDepth, 0);
        }
        System.out.println(rightTreeDepth);
        System.out.println(leftTreeDepth);
        return rightSideView;

    }

    public int findDepthTree(TreeNode root) {
        if(root == null)
            return 0;
        return 1 + Math.max(findDepthTree(root.left), findDepthTree(root.right));
    }

    public List<Integer> findRightSideViewRecursionFromRight(TreeNode root, List<Integer> rightSideView, boolean ignore) {
        if(root == null)
            return rightSideView;

        if(!ignore)
            rightSideView.add(root.val);

        if(root.right != null) {
            findRightSideViewRecursionFromRight(root.right, rightSideView, false);
            findRightSideViewRecursionFromRight(root.left, rightSideView, true);
        }
        else {
            findRightSideViewRecursionFromRight(root.left, rightSideView, false);
        }
        return rightSideView;
    }

    public void findRightSideViewRecursionFromLeft(TreeNode root, List<Integer> rightSideView, boolean ignore, int rightTreeDepth, int currentDepth) {
        if(root == null)
            return;
        if(!ignore && currentDepth>=rightTreeDepth) {
            System.out.println("val" + root.val + ignore);
            rightSideView.add(root.val);
        }
        currentDepth+=1;
        if(root.right != null) {
            findRightSideViewRecursionFromLeft(root.right, rightSideView, false, rightTreeDepth, currentDepth);
            findRightSideViewRecursionFromLeft(root.left, rightSideView, true, rightTreeDepth, currentDepth);
        }
        else if(root.right == null) {
            findRightSideViewRecursionFromLeft(root.left, rightSideView, false, rightTreeDepth, currentDepth);
        }
    }

}
