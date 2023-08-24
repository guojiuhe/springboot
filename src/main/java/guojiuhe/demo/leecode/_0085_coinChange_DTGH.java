package guojiuhe.demo.leecode;

import java.util.Arrays;

public class _0085_coinChange_DTGH {
	/*
	 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
	 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
	 * 你可以认为每种硬币的数量是无限的。
	 * 输入：coins = [1, 2, 5], amount = 11
	 * 输出：3 
	 * 解释：11 = 5 + 5 + 1
	 * 输入：coins = [1], amount = 0
	 * 输出：0
	 */
	
	/*
	 * 假如 coins = [1, 2, 5], amount = 11
	 * 11 = min(10 9 6) + 1
	 * 10 = min(9 8 5) + 1
	 * 9 = min(8 7 4) + 1
	 * 5 = min(4 3 0) + 1
	 * 4 = min(3,2) + 1
	 * 2 = min(1,0) + 1
	 * 1 = min(0) + 1
	 * 0 = 0
	 */
	public int coinChange(int[] coins, int amount) {
		int[] dp = new int[amount + 1]; // 计算出0->10所需min数量
		Arrays.fill(dp, amount + 1);
		dp[0] = 0; // amount = 0 时一定结果一定是0
		for (int i = 1; i <= amount; i ++) {
			for (int j = 0; j < coins.length; j ++) {
				if (i >= coins[j]) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
					if (dp[i - coins[j]] < amount + 1) {
						System.out.println("amount " + i + "可以由 amount " + (i - coins[j]) + " + 1个 " + coins[j] + " 组成,需要" + (dp[i - coins[j]] + 1) + " 硬币");
					}
				}
			}
			if (dp[i] < amount + 1) {
				System.out.println("amount " + i + "最少需要 " + dp[i] + " 硬币");
			}
			else {
				System.out.println("amount " + i + " 无法组合");
			}
		}
		return dp[amount] == amount + 1 ? -1 : dp[amount];
    }
	
	public static void main(String[] args) {
		_0085_coinChange_DTGH test = new _0085_coinChange_DTGH();
		int[] coins = new int[] {1,2,5};
		int amount = 11;
		test.coinChange(coins, amount);
	}
}
