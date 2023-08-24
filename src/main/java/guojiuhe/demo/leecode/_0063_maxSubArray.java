package guojiuhe.demo.leecode;

public class _0063_maxSubArray {
	/*
	 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
	 * 子数组 是数组中的一个连续部分。
	 */
	
    public int maxSubArray(int[] nums) {
    	int result = Integer.MIN_VALUE;
    	int sum = 0;
    	for (int i = 0; i < nums.length; i++) {
    		int temp = nums[i];
    		sum = sum + temp;
    		if (sum > result) {
    			result = sum;
    		}
    		if (sum < 0) {
    			sum = 0;
    		}
    	}
    	
    	return result;
    }
}
