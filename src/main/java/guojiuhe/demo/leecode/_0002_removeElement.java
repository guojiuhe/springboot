package guojiuhe.demo.leecode;

import java.util.Arrays;

public class _0002_removeElement {
	public static void main(String[] args) {
		//给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
		//不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
		//元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
		int[] nums = { 4,0,3,5,9,12,4,5};
		removeElement(nums , 4);
	}
	
	// 双指针算法
	public static int removeElement(int[] nums, int val) {
		int left = 0;
		for (int right = 0; right < nums.length; right++) {
			if (nums[right] != val) {
				nums[left] = nums[right];
				left ++;
			}
		}
		// 这里只能计算出新数组的长度，实际上数组只改了6位，最后2位没有修改 
		// 原数组 [4, 0, 3, 5, 9, 12, 4, 5]
		// 新数组 [0, 3, 5, 9, 12, 5, 4, 5]
		System.out.println("length is " + left); // 6
		System.out.println("nums.length is " + nums.length); // 8
		// 输出移除元素后的数组 2种方式
		int[] new_nums = Arrays.copyOf(nums, left);
		for (int i = 0; i < left; i++) {
			// 循环赋值
			//System.out.println(nums[i]);
		}
		for (int i = 0; i < new_nums.length; i++) {
			//System.out.println(nums[i]);
		}
		return left;
	}
	
}
