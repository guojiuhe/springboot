package guojiuhe.demo.leecode;

import java.util.HashMap;
import java.util.Map;

public class _0016_sumOfTwoNumbers {
	/*
	 * 两数之和
	 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
	 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
	 * 你可以按任意顺序返回答案。
	 */
	
	/*
	 * 暴力枚举
	 */
	public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i ++) {
            for (int j =i+1; j < nums.length; j ++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i , j};
                }
            }
        }
        return new int[] {};
    }
	
	/*
	 * 哈希表
	 */
	public int[] twoSum_1(int[] nums, int target) {
		Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();//hashMap保存 值，下标， 值作为key，自动去重
		for (int i = 0; i < nums.length; ++i) {
			if (hashMap.containsKey(target - nums[i])) {
				return new int[] { hashMap.get(target - nums[i]), i };
			}
			hashMap.put(nums[i], i);
		}
		return new int[0];
	}
	// 手打一遍练习
	private int[] test(int[] nums, int sum) {
		HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
		for (int i = 0; i < nums.length; i ++) {
			if (map.containsKey(map.get(sum - nums[i]))) {
				return new int[]{i,map.get(sum - nums[i]) };
			}
			map.put(i, nums[i]);
		}
		return null;
	}
}
