package guojiuhe.demo.leecode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class _0043_combinationSum3 {
	/*
	 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
	 * 只使用数字1到9
	 * 每个数字 最多使用一次 
	 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
	 */
	
	public List<List<Integer>> combinationSum3(int k, int n) {
		List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<>();//存储根节点开始的路径
        dfs3(1, k, path, n, res);
        return res;
    }
	
	public void dfs3(int begin, int k, Deque<Integer> path, int target, List<List<Integer>> res) {
        // 1.结束条件
        if (target == 0 && path.size() == k) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 2.选择列表
        for (int i = begin; i < 10; i++) {
            // 大剪枝
            if (target - i < 0) return;
            // 选择
            path.addLast(i);
            System.out.println("递归之前 => " + path);
            // 递归
            dfs3(i + 1, k, path, target - i, res);
            // 撤销选择
            path.removeLast();
            System.out.println("递归之后 => " + path);
        }
    }
	
	public static void main(String[] args) {
		_0043_combinationSum3 solution = new _0043_combinationSum3();
        int k = 3;
        int n = 9;
        List<List<Integer>> res = solution.combinationSum3(k, n);
        System.out.println(res);
    }
}
