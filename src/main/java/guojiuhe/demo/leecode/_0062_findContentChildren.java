package guojiuhe.demo.leecode;

import java.util.Arrays;

public class _0062_findContentChildren {
	/*
	 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
	 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸
	 * 并且每块饼干 j，都有一个尺寸 s[j] 。
	 * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。
	 * 你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
	 */
	
	/*
	 * 优先满足胃口最小的孩子，优先用最小的饼干
	 */
    public int findContentChildren(int[] g, int[] s) {
    	Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length, n = s.length;
        int count = 0;
        for (int i = 0, j = 0; i < m && j < n; i++, j++) {
            while (j < n && g[i] > s[j]) { // 时刻判断j<n
                j++;
            }
            if (j < n) { // 因为while可以由j<n不成立结束循环，所以这里还要判断。
                count++;
            }
        }
        return count;
    }
}
