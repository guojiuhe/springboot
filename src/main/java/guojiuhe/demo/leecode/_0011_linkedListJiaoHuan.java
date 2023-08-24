package guojiuhe.demo.leecode;

import java.util.HashSet;
import java.util.Set;

public class _0011_linkedListJiaoHuan {

	/*
	 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
	 * 题目数据 保证 整个链式结构中不存在环。
	 */
	
	/*
	 * 0 -> 1 -> 2 -> 6 -> 7
	 * 3 -> 4 -> 5 -> 6 -> 7  A
	 * 3 -> 4 -> 5 -> 6 -> 8  B
	 * 两个链表在6相交，由于链表的特性，6后面的链表必然相交，因为它们是同一条链
	 * 注意是指向同一个节点，即指针相同，不是值相同， 如 A B 的6是值相同，但不是一个节点，不相交。 A B 的 3 4 5 6 没有任何关系
	 * 也可以类比A B 人脉链中取相交点，相交之后后面必然是同一链，名字相同不行，必须是同一个人
	 * 
	 * 使用 hashset，先保存A, 遍历B, 只要set包含B 返回即可
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
	}
	
	/*
	 * 方案2 双指针 逻辑较复杂 分2种情况
	 * 1. A B 无交集 长度分别为 m n
	 * 	  m = n 时， A B 同时到达末尾, A B 都为null , 返回 null
	 * 	  m <> n 时, A B 不会同时到达末尾，A到达末尾后就变成了B， B到达末尾后就变成了A， A B 分别在遍历了 m+n后变为null
	 * 2. A B 有交集 长度分别为 m n , 交集长度为 X
	 * 	  m = n 时， A B 同时到达交集处， 返回任一即可
	 * 	  m <> n, A到达末尾后就变成了B， B到达末尾后就变成了A , 继续遍历, A B 会在 m + n -X 处汇合
	 * 	  示例:
	 * 	  0 -> 1 -> 2 -> 3 -> 6 -> 7
	 *    4 -> 5 -> 6 -> 7
	 *    A遍历6次后变为B，继续遍历2次到6位置, b遍历4次后变为A，继续遍历4次到6位置，A B汇合
	 */
	public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) { // 只要有一个为null ，必然无交集
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
	
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
