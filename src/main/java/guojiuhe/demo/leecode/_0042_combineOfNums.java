package guojiuhe.demo.leecode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _0042_combineOfNums {
	/*
	 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。 你可以按 任何顺序 返回答案。
	 * 组合与顺序无关，排列与顺序有关
	 */

	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> res = new ArrayList<>();
        if (k <= 0 || n < k) {
            return res;
        }
        // 从 1 开始是题目的设定
        Deque<Integer> path = new ArrayDeque<>();
        dfs(n, k, 1, path, res);
        return res;
	}
	private void dfs(int n, int k, int begin, Deque<Integer> path, List<List<Integer>> res) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        //for (int i = begin; i <= n; i++) {
        for (int i = begin; i <= n - (k - path.size()) + 1; i++) {
            // 向路径变量里添加一个数
            path.addLast(i);
            System.out.println("递归之前 => " + path);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            dfs(n, k, i + 1, path, res);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }
	
	public static void main(String[] args) {
		_0042_combineOfNums solution = new _0042_combineOfNums();
        int n = 5;
        int k = 3;
        List<List<Integer>> res = solution.combine(n, k);
        System.out.println(res);
    }
}
