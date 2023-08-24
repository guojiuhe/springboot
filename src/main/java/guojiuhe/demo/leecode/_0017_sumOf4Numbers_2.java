package guojiuhe.demo.leecode;

import java.util.HashMap;
import java.util.Map;

public class _0017_sumOf4Numbers_2 {
	/*
	 * 四数相加 II
	 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
	 * 0 <= i, j, k, l < n
	 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
	 */
	
	/*
	 * 方案 分组+哈希表
	 * A B 一组， C D 一组
	 * 这样就类似于2数之和
	 */
	
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> countAB = new HashMap<Integer, Integer>();
        for (int u : A) {
            for (int v : B) {
                countAB.put(u + v, countAB.getOrDefault(u + v, 0) + 1);
            }
        }
        int ans = 0;
        for (int u : C) {
            for (int v : D) {
                if (countAB.containsKey(-u - v)) {
                    ans += countAB.get(-u - v);
                }
            }
        }
        return ans;
    }
}
