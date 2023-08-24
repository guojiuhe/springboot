package guojiuhe.demo.leecode;

public class _0026_repeatedSubstringPattern {
	/*
	 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
	 */
	
	/*
	 * 012345
	 * aaaaaa 3*2 (1*6 or 2*3 找到最长子串)
	 * ababab 2*3
	 * abcabc 3*2
	 * 
	 * 长度为7 11 13 肯定return false (7*1除外)
	 * 满足1*6 一定满足3*2
	 */
	public boolean repeatedSubstringPattern(String s) {
		int length = s.length();
		for (int i = 1; i < length / 2 + 1; i ++) { // 最少重复2次，所以最多循环到一半, 从1开始，所以要多循环一次
			// length = 6, 1 2 3 进循环
			if (length % i == 0) { // 一定得能整除
                boolean match = true;
				/*
				 * ababab 
				 * 第1次 i=1, 1 : 0 -> b : a, break
				 * 第2次 i=2, 2 : 0 -> a : a, 3 : 1 -> b : b, 4 : 2 -> a : 2... match = true
				 * 
				 * i 是符合条件的子串长度，只有i符合条件，才进循环
				 * j 从 i开始遍历， 只要不符合条件即退出
				 * 
				 * 总结 : 
				 * 首先第一次循环，判断符合长度的子串进入二级循环，i为步长
				 * 根据步长依次比较，不符合即退出
				 * 循环完都符合，return true
				 */                
                for (int j = i; j < length; ++j) {
                    if (s.charAt(j) != s.charAt(j - i)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    return true;
                }
            }
		}
		return false;
    }
	
	/*
	 * 方案 2
	 */
	public boolean repeatedSubstringPattern_1(String s) {
		/*
		 * abcabc + abcabc = abcabcabcabc
		 * "abcabcabcabc".indexOf("abcabc", 1) 从index1开始匹配s = 3
		 * 
		 * abcabd + abcabd = abcabdabcabd
		 * "abcabdabcabd".indexOf("abcabd",1) = 6
		 * 
		 * 假设s有符合条件的子串s1
		 * 即 s = s1 + s1 + ...
		 * s + s = s1 + s1 + s1 + s1 + ...
		 * s = s1 + s2
		 * s + s = s1 + s2 + s1 + s2
		 * 可以比较容易的想到，如果不相等，肯定不符合题目要求
		 * 重点是如果相等，怎么确定一定符合题目要求？
		 * leecode证明了一波，没看懂
		 */		
        return (s + s).indexOf(s, 1) != s.length();
    }
	
	/*
	 * 方案3
	 * 对于 abc abc abc
	 * 移位1次 bc abc abc a
	 * 移位2次 c abc abc ab
	 * 移位3次 abc abc abc
	 * 可以发现把子串移动到末尾后和原来字符串相同，但是怎么证明相同一定符合题目要求？
	 * 可以发现abc的3种移动状态 abc bca cab 都包含在原串中
	 * 
	 * 拼接之后去掉首尾，即同时破坏了2个s, 如果还包含s即符合要求
	 * 
	 * s有符合条件的子串s1
     * 即 s = s1 + s1 + ...(n * s1) (n >= 2)
	 * s + s = s1 + s1 + s1 + s1 + ...(m * s1) (m=2n>=4)
	 * 破坏首尾 = new_s1 + s1 + s1 + ... + new_s1 (至少剩2个s1),所以符合题目要求一定包含s，下面证明不符合一定不包含
	 * s没有符合条件的子串
	 * s = s1 + s2 (s1 <> s2) (s1 s2是任意拆分的)
	 * s + s = s1 + s2 + s1 + s2
	 * 破坏首尾 = new_s1 + s2 + s1 + new_s2 会包含 s1 + s2 吗
	 * new_s1 new_s2 分别比是=s1 s2少一位， 所以 new_s1 + s2 和 s1 + new_s2肯定不会包含s1+s2
	 * 现在考虑 new_s1出一部分 + s2 + s1出一部分 或 s2出一部分 + s1 + new_s1出一部分
	 * 其实就相当于移串了
	 * new_s1出全部 + s2 + s1出1 ，相当于原字符串s移动1位后不变 : aaaa这种形式才符合
	 * new_si从第2位出 + s2 + s1出2 ，相当于原字符串s移动2位后不变 ： abab这种形式才符合
	 * new_si从第3位出 + s2 + s1出3 ，相当于原字符串s移动3位后不变 ： abcabc这种形式才符合
	 * 可以得出结论
	 */
	public boolean repeatedSubstringPattern_2(String s) {
		String str = s + s;
		return str.substring(1, str.length() - 1).contains(s);
	}
}
