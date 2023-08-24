package guojiuhe.demo.leecode;

public class _0034_minDepthTree {
	/*
	 * 给定一个二叉树，找出其最小深度。
	 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
	 * 说明: 叶子节点是指没有子节点的节点。
	 */
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		int m1 = minDepth(root.left);
		int m2 = minDepth(root.right);
		// 1.如果左孩子和右孩子有为空的情况，直接返回m1+m2+1(此时m1或m2等于0) .如root.left为空，left不是叶子节点，计算最大就没有这个问题
		// 2.如果都不为空，返回较小深度+1
		return root.left == null || root.right == null ? m1 + m2 + 1 : Math.min(m1, m2) + 1;
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
