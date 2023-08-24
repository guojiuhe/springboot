package guojiuhe.demo.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _0018_sumOf3Numbers {
	/*
	 * 三数之和
	 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 
	 * 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。
	 * 请你返回所有和为 0 且不重复的三元组。
	 * 注意：答案中不可以包含重复的三元组。
	 */
	
	//定义三个指针，保证遍历数组中的每一个结果
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        Arrays.sort(nums);// 默认升序
        int len = nums.length;
        for(int i = 0;i < len;++i) {
            if(nums[i] > 0) 
            	return lists; // 已经升序，起始大于0直接退出
            if(i > 0 && nums[i] == nums[i-1]) 
            	continue; //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同。因为你遍历前一元素时已经覆盖过
            int curr = nums[i];
            int L = i+1, R = len-1;
            while (L < R) {
            	// 起始 + 起始下一位 + 末尾 （注意已经升序）
            	// 从起始开始循环，下一位最小，末尾最大，遍历
                int tmp = curr + nums[L] + nums[R];
                if(tmp == 0) {
                    List<Integer> list = new ArrayList<>();
                    list.add(curr);
                    list.add(nums[L]);
                    list.add(nums[R]);
                    lists.add(list);
                    while(L < R && nums[L+1] == nums[L]) //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同。因为你遍历前一元素时已经覆盖过
                    	++L;
                    while (L < R && nums[R-1] == nums[R])//去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同。因为你遍历前一元素时已经覆盖过
                    	--R;
                    ++L;
                    --R;
                } else if(tmp < 0) {
                    ++L;
                } else {
                    --R;
                }
            }
        }
        return lists;
    }
}
