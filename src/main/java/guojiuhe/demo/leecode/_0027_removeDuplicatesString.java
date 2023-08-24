package guojiuhe.demo.leecode;

public class _0027_removeDuplicatesString {
	/*
	 * 删除字符串中的所有相邻重复项
	 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
	 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
	 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯
	 * 输入："abbaca"
	 * 输出："ca"
	 */
	public static void main(String[] args) {
		
		removeDuplicates_1("abbaca");
	}
	
	public String removeDuplicates(String s) {
		StringBuilder stack = new StringBuilder();
		int top = -1;
		for (int i = 0; i < s.length(); ++i) {
			char ch = s.charAt(i);
			if (top >= 0 && stack.charAt(top) == ch) {
				stack.deleteCharAt(top); // 进入此处后，删除已有stack && 不append，实现同时删除2个元素
				--top;
			} else {
				stack.append(ch);
				++top;
			}
		}
		return stack.toString();
	}
	/*
	 * 和上面算法一直 不使用StringBuilder 效率更高
	 */
	public static String removeDuplicates_1(String s) {
        char[] arr = s.toCharArray();
        int top = -1;
        for (int i = 0; i < arr.length; i++) {
            if (top == -1 || arr[top] != arr[i]) {
            	arr[++top] = arr[i]; // 满足条件 赋值
            } else {
                top--; //不满足条件不赋值，但是没有直接删除，而是下一次赋值时覆盖掉该值，所以最后一个值无法覆盖
            }
        }
        // 添加1个 top = 0; add 2 top = 1; add 10, top = 9;第11个和第10个一样，不加第11，但因为已到末尾，无法替换10
        // 此时 top--变成8 top最终意义是修改了arr的位数 所以需要截取，下面是2种写法
        //return String.valueOf(arr, 0, top + 1);
        return String.valueOf(arr).substring(0, top + 1);
    }
}
