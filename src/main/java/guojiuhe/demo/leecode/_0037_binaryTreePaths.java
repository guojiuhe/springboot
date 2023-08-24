package guojiuhe.demo.leecode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class _0037_binaryTreePaths {
    /*
     * 给你一个二叉树的根节点 root ，按 任意顺序 ，返回所有从根节点到叶子节点的路径。
     * 叶子节点 是指没有子节点的节点。
     */

    /*
     * 深度优先搜索DFS vs 广度优先算法BFS
     * 深度优先搜索就是从根节点开始，一直往左子节点走，直到左子节点为空，让后返回到上一步从右子节点在执行同样的操作
     * DFS 栈 ; BFS 队列
     */

    // 深度优先算法 demo 递归
    public static void treeDFS_DG(TreeNode root) {
        //当前节点为空直接返回
        if (root == null)
            return;
        //打印当前节点的值
        System.out.println(root.val);
        //然后递归遍历左右子节点
        treeDFS_DG(root.left);
        treeDFS_DG(root.right);
    }

    public static void treeDFS_NOT_DG(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.println(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    // 深度优先搜索 递归
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }

    private void dfs(TreeNode root, String path, List<String> res) {
        //如果为空，直接返回
        if (root == null)
            return;
        //如果是叶子节点，说明找到了一条路径，把它加入到res中
        if (root.left == null && root.right == null) {
            res.add(path + root.val);
            return;
        }
        //如果不是叶子节点，在分别遍历他的左右子节点
        dfs(root.left, path + root.val + "->", res);
        dfs(root.right, path + root.val + "->", res);
    }

    /*
     * // 深度优先搜索 遍历
     */
    public List<String> binaryTreePaths_1(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        Stack<Object> stack = new Stack<>();
        //当前节点和路径同时入栈
        stack.push(root);
        stack.push(root.val + "");
        while (!stack.isEmpty()) {
            //节点和路径同时出栈
            String path = (String) stack.pop();
            TreeNode node = (TreeNode) stack.pop();
            //如果是子节点，说明找到了一条完整路径，把它加入到集合中
            if (node.left == null && node.right == null) {
                res.add(path);
            }
            //右子节点不为空就把右子节点和路径压栈
            if (node.right != null) {
                stack.push(node.right);
                stack.push(path + "->" + node.right.val);
            }
            //左子节点不为空就把左子节点和路径压栈
            if (node.left != null) {
                stack.push(node.left);
                stack.push(path + "->" + node.left.val);
            }
        }
        return res;
    }

    // 广度优先搜索 递归
    public List<String> binaryTreePaths_2(TreeNode root) {
        List<String> res = new LinkedList<>();
        if (root == null)
            return res;
        //到达叶子节点，把路径加入到集合中
        if (root.left == null && root.right == null) {
            res.add(root.val + "");
            return res;
        }
        //遍历左子节点的路径
        for (String path : binaryTreePaths_2(root.left)) {
            res.add(root.val + "->" + path);
        }
        //遍历右子节点的路径
        for (String path : binaryTreePaths_2(root.right)) {
            res.add(root.val + "->" + path);
        }
        return res;
    }

    // 广度优先搜索 遍历
    public List<String> binaryTreePaths_4(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null)
            return res;
        //队列，节点和路径成对出现，路径就是从根节点到当前节点的路径
        Queue<Object> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root.val + "");
        while (!queue.isEmpty()) {
            TreeNode node = (TreeNode) queue.poll();
            String path = (String) queue.poll();
            //如果到叶子节点，说明找到了一条完整路径
            if (node.left == null && node.right == null) {
                res.add(path);
            }

            //右子节点不为空就把右子节点和路径存放到队列中
            if (node.right != null) {
                queue.add(node.right);
                queue.add(path + "->" + node.right.val);
            }

            //左子节点不为空就把左子节点和路径存放到队列中
            if (node.left != null) {
                queue.add(node.left);
                queue.add(path + "->" + node.left.val);
            }
        }
        return res;
    }

    /*
     * 深度优先搜索 与 广度优先搜索 区别
     * 遍历 ： 深度使用 stack, top 插入 top删除; 广度使用 linked list, end 插入, top删除
     */

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
