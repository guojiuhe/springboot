package guojiuhe.demo.leecode;

public class _0013_yiWeiZiMu {
	/*
	 * 异位字母
	 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
	 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
	 * 示例 1:
	 * 输入: s = "anagram", t = "nagaram"
	 * 输出: true
	 * 示例 2:
	 * 输入: s = "rat", t = "car"
	 * 输出: false
	 * s 和 t 仅包含小写字母
	 */
	/*
	 * 方案 1 哈希表 数组也可以算为哈希表
	 */
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		int[] alpha = new int[26];
		for (int i = 0; i < s.length(); i++) {
			alpha[s.charAt(i) - 'a']++;
			alpha[t.charAt(i) - 'a']--;
		}
		for (int i = 0; i < 26; i++) {
			if (alpha[i] != 0) {
				return false;
			}
		}
		/* 
		 * 2种循环方式 
		 * 1. 循环字符串 同时 ++ --， 循环数组判断不为0
		 * 2. 循环字符串 ++, 再次循环字符串 --, 判断是否小于0. 因为长度相等，如果不是异位，必然有大于0和小于0
		 *    注意只能判断小于0，因为我们不知道是否循环完，循环完后大于0小于0都有问题，循环中只有小于0一定不是异位
		 * 
		 * */
		
		return true;
	}
}
