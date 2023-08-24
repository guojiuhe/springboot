package guojiuhe.demo.leecode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class _0014_arrayJiaoJi {
	/* 
	 * 两个数组的交集
	 * 给定两个数组 nums1 和 nums2 ，返回 它们的交集 。
	 * 输出结果中的每个元素一定是 唯一 的。
	 * 我们可以 不考虑输出结果的顺序 。 
	 * */
	
	/*
	 * 方案1 
	 * 循环nums1，判断元素是否在nums2
	 * 其实直接用数组也能判断，为啥把数组转成set呢，因为set contains效率更高（哈希算法）
	 * 数组判断contains需要从头遍历。nums1 nums2 长度分别为 m n 
	 * 时间复杂度
	 * 数组: O(m*n)
	 * 转成set : O(m+n) 
	 * 遍历小set : O(min(m,n)) 
	 * 总 ： O(m+n)  // 意思是只和m+n有关
	 */
	public int[] intersection(int[] nums1, int[] nums2) {
		Set<Integer> set1 = new HashSet<Integer>();
        Set<Integer> set2 = new HashSet<Integer>();
        for (int num : nums1) {
            set1.add(num);
        }
        for (int num : nums2) {
            set2.add(num);
        }
        return getIntersection(set1, set2);
	}
    public int[] getIntersection(Set<Integer> set1, Set<Integer> set2) {
        if (set1.size() > set2.size()) {
            return getIntersection(set2, set1); // 这里要遍历小的，因为效率快一些
        }
        Set<Integer> intersectionSet = new HashSet<Integer>();
        for (int num : set1) {
            if (set2.contains(num)) {
                intersectionSet.add(num);
            }
        }
        int[] intersection = new int[intersectionSet.size()];
        int index = 0;
        for (int num : intersectionSet) {
            intersection[index++] = num;
        }
        return intersection;
    }

	/* 
	 * 方案2 双指针
	 * 首先排序 2个数组升序
	 * 双指针遍历，如果不相等，小的指针右移，如果相等，保存数值，双指针同时向右移动一位
	 * 因为不能有重复，需要判断是否与前一位相同，相同跳过
	 * */
    public int[] intersection_1(int[] nums1, int[] nums2) {
        Arrays.sort(nums1); // 默认升序
        Arrays.sort(nums2);
        int length1 = nums1.length, length2 = nums2.length;
        int[] intersection = new int[length1 + length2];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < length1 && index2 < length2) { // 都到结尾结束
            int num1 = nums1[index1], num2 = nums2[index2];
            if (num1 == num2) {
                // 保证加入元素的唯一性
            	// 第一次相等，index=0 必保存，此时index - 1会溢出
                if (index == 0 || num1 != intersection[index - 1]) { 
                    intersection[index++] = num1;
                }
                index1++;
                index2++;
            } else if (num1 < num2) {
                index1++;
            } else {
                index2++;
            }
        }
        return Arrays.copyOfRange(intersection, 0, index); // 截取一下，节省空间
    }
}
