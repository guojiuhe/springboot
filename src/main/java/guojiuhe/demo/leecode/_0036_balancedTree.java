package guojiuhe.demo.leecode;

public class _0036_balancedTree {
	/*
	 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
	 * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
	 */
	
	public boolean isBalanced(TreeNode root) {
		return recur(root) != -1;
    }
	
	private int recur(TreeNode root) {
        if (root == null) 
        	return 0;
        int left = recur(root.left);
        if(left == -1) 
        	return -1;
        int right = recur(root.right);
        if(right == -1) 
        	return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
	
	public class TreeNode {
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
}
