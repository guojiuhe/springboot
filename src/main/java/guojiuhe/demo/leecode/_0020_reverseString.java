package guojiuhe.demo.leecode;

public class _0020_reverseString {
	/*
	 * 反转字符串
	 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
	 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
	 */
	
	/*
	 * 双指针
	 * 第一位与最后一位交换
	 * left 左移， right右移， 第二位与倒数第二位交换
	 * 偶数 中间位置 left < right, 移动后left > right, end
	 * 基数 中间位置 left < right, 移动后left = right, end
	 */
    public void reverseString(char[] s) {
    	int n = s.length;
        for (int left = 0, right = n - 1; left < right; ++left, --right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;
        }
        // 对于for循环，++i和i++ 效果一样
        for(int i = 0; i<10; i++) {  
            System.out.println(i);  
        }
        for(int i = 0; i<10;) {  
            System.out.println(i);  
            i++;
            ++i;
        }
    }
}
