package guojiuhe.demo.leecode;

import java.util.Deque;
import java.util.LinkedList;

public class _0032_symmetricTree {
	/*
	 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
	 */
	
	// 下面是比较左右是否相等，不是轴对称
	public boolean isSymmetric_wrong(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (root.left == null && root.right == null) {
			return true;
		}
		if (root.left == null) {
			return false;
		}
		if (root.right == null) {
			return false;
		}
		if (root.left.val == root.right.val) {
			return isSymmetric_wrong(root.left) && isSymmetric_wrong(root.right);
		}
		else {
			return false;
		}
    }
	
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return dfs(root.left,root.right);
    }
	
	boolean dfs(TreeNode left, TreeNode right) {
		if(left==null && right==null) {
			return true;
		}
		if(left==null || right==null) {
			return false;
		}
		if(left.val!=right.val) {
			return false;
		}
		return dfs(left.left,right.right) && dfs(left.right,right.left);
	}
	
	// 迭代
	public boolean isSymmetric_1(TreeNode root) {
		if (root == null || (root.left == null && root.right == null)) {
			return true;
		}
		//LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		//List<TreeNode> queue = new LinkedList<TreeNode>(); // 这里不可以这样写 因为list接口没有removefirst方法
		Deque<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root.left);
		queue.add(root.right);
		while (queue.size() > 0) {
			TreeNode left = queue.removeFirst();
			TreeNode right = queue.removeFirst();
			if(left==null && right==null) {
				continue;
			}
			if(left==null || right==null) {
				return false;
			}
			if(left.val!=right.val) {
				return false;
			}
			queue.add(left.left);
			queue.add(right.right);
			queue.add(left.right);
			queue.add(right.left);
		}
		return true;
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
