package guojiuhe.demo.leecode;

public class _0022_replaceSpace {
	/*
	 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
	 */
	public String replaceSpace(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (c == ' ') {
				sb.append("%20");
			}
			else {
				sb.append(c);
			}
		}
		// s.replaceAll(" ", "%20"); // 查看源码 底层 Matcher.replaceAll 
		return sb.toString();
    }
}
