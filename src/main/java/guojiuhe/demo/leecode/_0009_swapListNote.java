package guojiuhe.demo.leecode;

public class _0009_swapListNote {

	/*
	 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。 
	 * 输入：head = [1,2,3,4] 输出：[2,1,4,3]
	 */
	
	// 递归
	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {// head为空直接返回，head.next为空，无法翻转，返回
			return head;
		}
		/*
		 *  0 -> 1 -> 2 -> 3 -> 4 -> 5
		 *  改成 0 -> 2, 1 -> 0 即完成0 1 的翻转
		 *  传入2进行递归
		 *  
		 */
		ListNode next = head.next; // head = 0, next = 1
		head.next = swapPairs(next.next);// 传入2递归 返回的链表链接到head， 完成 0 -> 2
		next.next = head; // 1.next = head 完成 1 -> 0
		return next;
	}
	// 迭代
    public ListNode swapPairs_1(ListNode head) {
        ListNode pre = new ListNode(0); // 创建虚拟节点 注意这里是pre 为了和head区分 这里0是head
        pre.next = head;
        ListNode temp = pre;
		/* 
		 * pre -> 0 -> 1 -> 2 -> 3 -> 4 
		 * pre -> 1, 1 -> 0, 0 -> 2 完成一轮
		 * pre -> 1 -> 0 -> 2 -> 3 -> 4
		 * 从前一轮的最后节点开始继续
		 * 0 -> 3, 3 -> 2, 2 -> 4
		 */
        while(temp.next != null && temp.next.next != null) {
            ListNode start = temp.next; // start = 0
            ListNode end = temp.next.next; // end = 1
            temp.next = end; // 完成 pre -> 1
            start.next = end.next; // 完成 0 -> 2
            end.next = start; // 完成 1 -> 0
            temp = start; // 从0继续迭代
        }
        return pre.next;
    }

	class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}
	}
}
