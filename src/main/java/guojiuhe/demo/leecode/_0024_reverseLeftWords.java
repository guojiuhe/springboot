package guojiuhe.demo.leecode;

public class _0024_reverseLeftWords {
	/*
	 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。
	 * 请定义一个函数实现字符串左旋转操作的功能。
	 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
	 */
	public static void main(String[] args) {
		String str = "abcdefgffas";
		String s = reverseLeftWords(str, 2);
		System.out.println(s);
	}
	
	public static String reverseLeftWords(String s, int n) {
		char[] arr = s.toCharArray();
		int length = arr.length;
		if (n > length) {
			return s; // 全部旋转相当于没旋转
		}
		// 0 1 2 3 4 5  2
		char[] new_arr = new char[length];
		for (int i = 0; i < length; i ++) {
			if (i >= n) {
				new_arr[i - n] = arr[i];
			}
			else {
				new_arr[length - n + i] = arr[i];
			}
		}
		return new String(new_arr);
    }
	
	/*
	 * 官方解法 1
	 */
	public String reverseLeftWords_1(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }
	
	/*
	 * 官方解法 2
	 */
	public String reverseLeftWords_2(String s, int n) {
        StringBuilder res = new StringBuilder();
        for(int i = n; i < s.length(); i++)
            res.append(s.charAt(i));
        for(int i = 0; i < n; i++)
            res.append(s.charAt(i));
        // 简化上面写法 例 length=5, n=2  abcde
        // 遍历为 2 3 4 5 6
        // 取余后 2 3 4 0 1
        for(int i = n; i < n + s.length(); i++)
            res.append(s.charAt(i % s.length()));
        return res.toString();
    }

}
