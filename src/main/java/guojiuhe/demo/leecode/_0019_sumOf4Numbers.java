package guojiuhe.demo.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _0019_sumOf4Numbers {
	/*
	 * 四数之和
	 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
	 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为两个四元组重复）：
	 * 0 <= a, b, c, d < n 
	 * a、b、c 和 d 互不相同
	 * nums[a] + nums[b] + nums[c] + nums[d] == target
	 * 你可以按 任意顺序 返回答案 。
	 */
	
	/*
	 * 排序 + 双指针
	 * 暴力解法需要循环4次，双指针减少2次循环
	 * 第 1 2 个数循环，3 4 使用指针
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<List<Integer>>();
        if (nums == null || nums.length < 4) {
            return quadruplets;
        }
        Arrays.sort(nums); // 升序
        int length = nums.length;
        for (int i = 0; i < length - 3; i++) { // 至少要预留出3个位置
            if (i > 0 && nums[i] == nums[i - 1]) { // 类似 _0018_sumOf3Numbers 去重
                continue;
            }
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) { //最小结果已经>target
                break;
            }
            if ((long) nums[i] + nums[length - 3] + nums[length - 2] + nums[length - 1] < target) {//最大结果还<target
                continue;
            }
            for (int j = i + 1; j < length - 2; j++) { // 至少要预留出2个位置
                if (j > i + 1 && nums[j] == nums[j - 1]) { // 去除。j > i + 1 这里主要目的是第一次不去重。i>0的作用防止数组越界
                    continue;
                }
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                if ((long) nums[i] + nums[j] + nums[length - 2] + nums[length - 1] < target) {
                    continue;
                }
                int left = j + 1, right = length - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        left++;
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return quadruplets;
    }
}
