package guojiuhe.demo.leecode;

public class _0025_findIndexOfStr {
	/*
	 * 找出字符串中第一个匹配项的下标
	 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
	 * 如果 needle 不是 haystack 的一部分，则返回  -1 。
	 */
	
	public int strStr(String A, String B) {
		// return A.indexOf(B); // 提交直接通过
		char[] arrA = A.toCharArray();
		char[] arrB = B.toCharArray();
		int n = arrA.length, m = arrB.length;
		for (int i = 0; i <= n - m; i ++) { // n = 10, m = 5, 只能循环0->5 6次，否则后面不够5
			int a = i, b = 0;
			while (b < m && arrA[a] == arrB[b]) { // b = m 时已经匹配完B,此时返回i即可
				a++;
				b++;
			}
			if (b == m)
				return i;
		}
		return -1;
	}

	// KMP 算法 我还没看 直接粘贴了答案
	// ss: 原串(string)  pp: 匹配串(pattern)
	public int strStr_1(String ss, String pp) {
        if (pp.isEmpty()) 
        	return 0;
        int n = ss.length(), m = pp.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }
        return -1;
    }
}
