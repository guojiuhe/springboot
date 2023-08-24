package guojiuhe.demo.leecode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
public class _0030_topKFrequent {
	/*
	 * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
	 * 进阶：你所设计算法的时间复杂度 必须 优于 O(nlogn) ，其中 n 是数组大小。
	 */
	
	/*
	 * 遍历数组，保存数值和出现次数到哈希表，对哈希表排序即可，但时间复杂度为O(nlogn)
	 * 使用优先队列（模拟堆）提高效率
	 */
	public int[] topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> occurrences = new HashMap<Integer, Integer>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        // int[] 的第一个元素代表数组的值，第二个元素代表了该值出现的次数
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>(){
			public int compare(int[] m, int[] n){
				return m[1] - n[1]; // 降序 前 - 后 ： 降序, 后 - 前 ： 升序
			}
		});
        
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (queue.size() == k) {
                if (queue.peek()[1] < count) {
                    queue.poll();
                    queue.offer(new int[]{num, count});
                }
            } else {
                queue.offer(new int[]{num, count});
            }
        }
        int[] ret = new int[k];
        for (int i = 0; i < k; ++i) {
            ret[i] = queue.poll()[0];
        }
        return ret;
	}
	
	/*
	 * 方案2 快速排序
	 */
	public int[] topKFrequent_1(int[] nums, int k) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : nums) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }

        List<int[]> values = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : occurrences.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            values.add(new int[]{num, count});
        }
        int[] ret = new int[k];
        qsort(values, 0, values.size() - 1, ret, 0, k);
        return ret;
    }

    public void qsort(List<int[]> values, int start, int end, int[] ret, int retIndex, int k) {
        int picked = (int) (Math.random() * (end - start + 1)) + start;
        Collections.swap(values, picked, start); // 随机快排,防止出现极限情况,即原数组已经排好序了

		/*
		 * end = length - 1 = 8, k = 5
		 * 5 1 2 6 7 4 3 8 index = 0, start = 0, pivot = 5
		 * i = 1, 1>=5
		 * i = 2, 2>=5
		 * i = 3, 6>=5 转换0+1与3 -> 5 6 2 1 7 4 3 8 index=1
		 * i = 4, 7>=5 转换2与4 -> 5 6 7 1 2 4 3 8 index=2
		 * i = 7, 8>=5 转换3与7 -> 5 6 7 8 2 4 3 1 index=3
		 * 
		 * 选定第1个数pivot，大于等于pivot的放在左边，小于pivot放在右边，pivot放中间（无论奇偶）
		 */        
        int pivot = values.get(start)[1];
        int index = start;
        for (int i = start + 1; i <= end; i++) {
            if (values.get(i)[1] >= pivot) {
                Collections.swap(values, index + 1, i);
                index++;
            }
        }
        // start = 0, index = 3
        Collections.swap(values, start, index);
    	// 8 6 7 5 2 4 3 1

        if (k <= index - start) { // 5<=3-0 随机找到的元素比k个多 继续排序继续找。因为大于等于pivot的在左边，所以k=index-start时也要继续
            qsort(values, start, index - 1, ret, retIndex, k);
        } else {
            for (int i = start; i <= index; i++) {// 随机找到的元素比k个少，先把右边满足条件的保存下来
                ret[retIndex] = values.get(i)[0];
                retIndex++;
            }
            if (k > index - start + 1) { // 右边继续排序继续找
                qsort(values, index + 1, end, ret, retIndex, k - (index - start + 1));
            }
        }
    }
}
