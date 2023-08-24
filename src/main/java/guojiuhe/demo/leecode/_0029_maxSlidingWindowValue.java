package guojiuhe.demo.leecode;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class _0029_maxSlidingWindowValue {
	/*
	 * 滑动窗口最大值
	 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
	 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
	 * 返回 滑动窗口中的最大值 。
	 * 示例 1：
		输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
		输出：[3,3,5,5,6,7]
		解释：
		滑动窗口的位置                最大值
		---------------               -----
		[1  3  -1] -3  5  3  6  7       3
		 1 [3  -1  -3] 5  3  6  7       3
		 1  3 [-1  -3  5] 3  6  7       5
		 1  3  -1 [-3  5  3] 6  7       5
		 1  3  -1  -3 [5  3  6] 7       6
		 1  3  -1  -3  5 [3  6  7]      7
		 
		 暴力解法简单，优化解法困难
	 */
	
	/*
	 * 方案 1 
	 * 对于 length = 5, k = 2, 滑动 01 12 23 34 共4次
	 * 对于 length = 5, k = 3, 滑动 012 123 234  共3次
	 * 对于 length = 5, k = 4, 滑动 0123 1234   共2次
	 * 滑动次数 = length - k + 1 (最后一个滑动起始位置距末尾长度为k)
	 * 最容易想到 ： 遍历 0 -> length - k + 1, 二次遍历 0 -> k ，将最大值保存
	 * 时间复杂度 O((n−k+1)k)=O(nk) 这里没看懂是怎么=nk的，可以确定n越大值越大 k越大 n-k越小，理论上 n-k = k时最大（我暂时忽略1）
	 * (100 - 50) * 50
	 * 
	 * 下面需要找到更简单的方法
	 * 代码思想 ： 优先队列 PriorityQueue 使用其排序功能
	 * 常用功能 ： 
	 * peek()//返回队首元素
	 * poll()//返回队首元素，队首元素出队列
	 */
	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
            	// 排序规则 ： 值不相等，值大的在前，值相等，序号大的在前
            	// return 3个值一般是 -1 0 1， pair1在前 pair2在后，pair1已经存在，pair2是新来的
            	// 假设原来有一个10，就是pair1， 新加一个5， 就是pair2
            	// 默认就是 10 -> 5. pair1 -> pair2
            	//  > 0 需要调整位置 即改为 pair2 -> pair1
            	// < 0 不需要调整位置
            	// return pair2 - pair1; 这是降序
            	// return pair1 - pair2; 这是升序
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1]; // 这是降序
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i}); // 先把k个元素保存到队列，此队列已经排序.堆顶就是最大值
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0]; // 返回最大值
        for (int i = k; i < n; ++i) {
        	// 向右滑动一位，现在pg有k+1位了，还是第一位最大，需要判断最大值在不在该窗口（不包含第一位，后k位）
        	// pg[1] 记录了位置
            pq.offer(new int[]{nums[i], i}); 
            // 遍历pg（现在是k+1） 找到第一位删除。但是如果遍历最大需要遍历k+1次，严重耗时
            // 这里只判断最大值是不是第一位，是删除。（第2次判断是不是前2位）。是一种延时删除策略。类似订单过期策略，访问时判断是否过期
            // 难点在该处
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
	
	/*
	 * 方案2 单调队列。 优先队列保存值和索引，单调队列只保存索引，根据索引取值
	 * 对于同一窗口内的元素,有2个元素，m在n前面，但m的值<n.当窗口滑动时，只要m在窗口中，n一定在，并且m的值无意义（因为m<n）
	 * 实际上是对方案1的优化，方案1排序，方案2是把小值都删除掉。只要后面的值大，前面的就删除
	 */
	public int[] maxSlidingWindow_1(int[] nums, int k) {
        int n = nums.length;
        // 加入k=50,第40个元素的值比前39个都大,那前面的值直接删除就行了
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) { // 只要后面的值比前面的值大，直接删除前面的
                deque.pollLast();
            }
            deque.offerLast(i); // 注意这里保存的是索引 不是值
        }
        // 上述步骤处理完后，队列递减排序（删除条件>=，所以不存在相等数据）
        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()]; // 第一位一定最大
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            //while (deque.peekFirst() <= i - k) {
            if (deque.peekFirst() <= i - k) { // 方案1需要while，因为只删除最大值，不是每次都删除，删除时需要删多次
                deque.pollFirst(); // 方案2顺序保存索引，索引严格增序，数值严格降序。每次至少删1,不删说明已经被数值比较环节删除了
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
	
	/*
	 * 方案3 分块 + 预处理 方案1,2属于随着窗口的移动实时维护最大值
	 * 比较难懂这个，可以忽略
	 * 我们先将nums按每组k个元素分组，会有2种情况: 1.每组都有k个元素(length = mk) 2. 最后一组小于k
	 * 举例 n = 104, k = 50, 我们分成3组 0->49, 50->99, 100->103
	 * 循环遍历nums， 当 i % k == 0时，和我们分组的情况一致，每组最大值即为结果 (i = 0, 50 , 100时满足此情况)
	 * 大部分情况, i % k <> 0, 需要从前后分组各取一部分拼接, 例 i = 20，时，需要计算20->69, 需要取 20->49和50->69的最大值（分块算法）
	 * 为啥分块? 20-> 49的最大值和30->49的最大值怎么计算&保存? -- 同样用到了方案2的思想
	 * prefixMax[i]表示以i结尾的最大值，suffixMax[i]表示以i开头的最大值
	 * prefixMax[0]：= nums[0]
	 * prefixMax[49]：表示0->49最大值.
	 * prefixMax[50]：= nums[50], 此时无法知道 prefixMax[50]大小
	 * prefixMax[60], 表示50->60最大值, prefixMax[90], 表示50->90最大值 
	 * prefixMax[100]：= nums[100], 此时无法知道 prefixMax[100]大小
	 * prefixMax[103]：表示100->103最大值
	 * 
	 * suffixMax[103]:=nums[103]
	 * suffixMax[100] : 100->103最大值
	 * suffixMax[99] : nums[99]
	 * suffixMax[50] : 50->99最大值
	 * suffixMax[49] : nums[49]
	 * suffixMax[0] : 0->49最大值

	 * ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
	 * i = 0时,suffixMax[0] = prefixMax[49]
	 * i = 20时,suffixMax[20]=20到49最大值 prefixMax[69] = 50到69最大值
	 * 起点:prefixMax[49],suffixMax[0]
	 * 终点：prefixMax[103],suffixMax[54]
	 *  */
	public int[] maxSlidingWindow_2(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n - k + 1; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }
}
