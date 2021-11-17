package easycard.tree;

import java.util.ArrayList;
import java.util.List;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class TreeOperations {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            return null;
        return build(nums, 0, nums.length);
    }

    public TreeNode build(int[] nums, int start, int end) {
        if (start >= end)
            return null;
        if (start < 0 || end > nums.length)
            return null;
        int mid = (start + end);
        int m = mid % 2 == 0 ? mid / 2 : (mid / 2) + 1;
        TreeNode root = new TreeNode(nums[m - 1]);
        root.left = build(nums, start, m - 1);
        root.right = build(nums, m, end);
        return root;
    }

    public int maxDepth(TreeNode root) {
        return 0;
    }

    public static List<TreeNode> generateTrees(int n) {
        return gen(1, n);
    }

    public static List<TreeNode> gen(int s, int e) {
        List<TreeNode> l = new ArrayList<>();
        if (s > e) {
            l.add(null);
            return l;
        }
        if (s == e) {
            l.add(new TreeNode(s));
            return l;
        }
        for (int i = s; i <= e; i++) {
            List<TreeNode> lnodes = gen(s, i - 1);
            List<TreeNode> rnodes = gen(i + 1, e);
            for (TreeNode p : lnodes) {
                for (TreeNode q : rnodes) {
                    TreeNode root = new TreeNode(i);
                    root.left = p;
                    root.right = q;
                    l.add(root);
                }
            }
        }
        return l;
    }

    public static void main(String[] args) {

    }
}
