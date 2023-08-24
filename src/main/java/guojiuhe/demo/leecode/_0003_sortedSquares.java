package guojiuhe.demo.leecode;

public class _0003_sortedSquares {

	public static void main(String[] args) {
		// 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
		

	}
	// 双指针
	public static int[] sortedSquares(int[] nums) {
		/*
		 * 递增 1 2 3 4 5 
		 * 递减 5 4 3 2 1 
		 * 非递增 5 4 4 2 1 （不是递增，但也不是严格递减） 
		 * 非递减 1 2 2 3 4
		 */
        int right = nums.length - 1;
        int left = 0;
        int[] result = new int[nums.length];
        int index = result.length - 1;
        while (left <= right) {
        	//  -7 -5 -3 -1 0 1 2 2 3 4 
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
            	//result[index] = nums[left] * nums[left]; // 此写法更好理解
            	//index --;
                ++left;
            } else {
                result[index--] = nums[right] * nums[right];
                --right;
            }
        }
        return result;
    }
}
