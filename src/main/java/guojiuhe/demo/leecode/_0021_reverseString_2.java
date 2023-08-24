package guojiuhe.demo.leecode;

public class _0021_reverseString_2 {
	/*
	 * 反转字符串 II
	 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
	 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
	 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
	 */
	
	public String reverseStr(String s, int k) {
        int n = s.length();
        char[] arr = s.toCharArray();
        for (int i = 0; i < n; i += 2 * k) {
			/*
			 * 假设k=4，n=30
			 * 第1次参数 0, min(4,30) -1     0->3
			 * 第2次参数 8, min(12,30) -1    8->11
			 * 第3次参数 16, min(20,30) -1   16->19
			 * 第4次参数 24, min(28,30) -1   24->27
			 * 第3次参数 32, min(32,30) -1   32->29
			 */            
        	reverse(arr, i, Math.min(i + k, n) - 1);
        }
        return new String(arr);
    }
	
	// 反转字符串算法
	public void reverse(char[] arr, int left, int right) {
        while (left < right) {
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
}
