package guojiuhe.demo.leecode;

import java.util.HashSet;
import java.util.Set;

public class _0015_happyNumber {
	/*
	 * 快乐数
	 * 编写一个算法来判断一个数 n 是不是快乐数。「快乐数」 定义为：
	 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
	 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
	 * 如果这个过程 结果为 1，那么这个数就是快乐数。
	 * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
	 */
	
	/*
	 * 方案1 递归遍历 利用set判断是否存在，如果存在，意味进入了循环
	 * 最大数 999 = 81 + 81 + 81 = 243 = 4 + 16 + 9 = 29 = 4 + 81 = 85 =  64 + 25 = 89 = 64 + 81
	 * 9999 = 81 * 4 = 324
	 * 999999 = 81 * 6 = 486
	 * 9999999999999 = 1053
	 * 最大数9的平方是81 10个9也才是810 位数越大，计算一次后变小的幅度越大
	 * 4 位或以上的数字，计算一次或2次后都会小于999， 从而小于243 
	 */
	public boolean isHappy(int n) {
		Set<Integer> seen = new HashSet<Integer>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
	}
	private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10; // 678 % 10 = 8
            n = n / 10; // 678 / 10 = 67
            totalSum += d * d; // 8*8
        }
        return totalSum;
    }
	
	/*
	 * 方案2 快慢指针 类似环形链表 _0012_huanxingLinkedList
	 * 如果有环，总会相遇
	 * fastRunner 先到1则无环， 相当于链表到末尾
	 * fastRunner 不等于1 && slowRunner == fastRunner 则有环
	 * 比环形链表简单了，因为只要返回有环无环，不需要返回进入环的位置
	 */
    public boolean isHappy_1(int n) {
		int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}
