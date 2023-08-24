package guojiuhe.demo.leecode;

public class _0086_numSquares_DTGH {
	/*
	 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
	 * 完全平方数 是一个整数，其值等于另一个整数的平方；
	 * 换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
	 */
	
	/*
	 * 四平方和定理:任意一个正整数都可以被表示为至多四个正整数的平方和
	 * 当且仅当 n≠4^k×(8m+7) 时n可以被表示为至多三个正整数的平方和
	 * 当n=4^k×(8m+7) 时n 只能被表示为四个正整数的平方和
	 * 涉及数学定理，此解法无参考性，写法思想可以借鉴
	 * 结果只能返回1 2 3 4
	 * 4可以根据是否满足4^k×(8m+7)判断
	 * 1根据是否完全平方数判断
	 * 2 根据是否满足a^2 + b^2判断，暴力循环
	 * 其余是3
	 */
	public int numSquares(int n) {
        if (isPerfectSquare(n)) {
            return 1;
        }
        if (checkAnswer4(n)) {
            return 4;
        }
        for (int i = 1; i * i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) {
                return 2;
            }
        }
        return 3;
    }

    // 判断是否为完全平方数
    public boolean isPerfectSquare(int x) {
        int y = (int) Math.sqrt(x);
        return y * y == x;
    }

    // 判断是否能表示为 4^k*(8m+7)
    public boolean checkAnswer4(int x){
        while (x % 4 == 0) {
            x /= 4;
        }
        return x % 8 == 7;
    }
    
    public int numSquares_1(int n) {
        int[] f = new int[n + 1]; // 0->n
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }
}
