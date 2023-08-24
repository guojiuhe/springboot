package guojiuhe.demo.leecode;

import java.util.ArrayDeque;
import java.util.Queue;

public class _0039_findBottomLeftValue {
	/*
	 * 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。 假设二叉树中至少有一个节点。
	 */

	/*
	 * 深度优先
	 */
    int curVal = 0;
    int curHeight = 0;
	public int findBottomLeftValue(_0038_sumOfLeftTreeLeaves.TreeNode root) {
        dfs(root, 0);
        return curVal;
	}

	public void dfs(_0038_sumOfLeftTreeLeaves.TreeNode root, int height) {
        if (root == null) {
            return;
        }
        height++;
        dfs(root.left, height);
        dfs(root.right, height);
        if (height > curHeight) {
            curHeight = height;
            curVal = root.val;
        }
    }
	
	/*
	 * 广度优先
	 * 遍历的最后一个节点就是最底层最左节点
	 */
	public int findBottomLeftValue_1(_0038_sumOfLeftTreeLeaves.TreeNode root) {
        int ret = 0;
        Queue<_0038_sumOfLeftTreeLeaves.TreeNode> queue = new ArrayDeque<_0038_sumOfLeftTreeLeaves.TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            _0038_sumOfLeftTreeLeaves.TreeNode p = queue.poll();
            if (p.right != null) {
                queue.offer(p.right);
            }
            if (p.left != null) {
                queue.offer(p.left);
            }
            ret = p.val;
        }
        return ret;
    }
}
