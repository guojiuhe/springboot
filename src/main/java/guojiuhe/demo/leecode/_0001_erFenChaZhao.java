package guojiuhe.demo.leecode;

public class _0001_erFenChaZhao {

	public static void main(String[] args) {
		// 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
		//写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
		int[] nums = { -1,0,3,5,9,12 };
		demo(nums, 9);
	}
	// 二分算法
	public static int demo (int[] nums, int target) {
		int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            int num = nums[mid];
            if (num == target) {
                return mid;
            } else if (num > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
	}
}
