package guojiuhe.demo.leecode;

public class _0089_删除链表节点{
	/*
	给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
	输入：head = [1,2,3,4,5], n = 2
	输出：[1,2,3,5]
	输入：head = [1], n = 1
	输出：[]
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode dummy = new ListNode(0, head);
		int length = 0;
		while (head != null) {
			length += 1;
			head = head.next;
		}
		ListNode cur = dummy;
		for (int i = 1; i < length - n + 1; i ++) {
			cur = cur.next;
		}
		//start cur = 0,1,2,3,4,5; n = 2 删除4
		//length = 6, i<5-2+1, 循环1->3
		// i = 1, cur = 1,2,3,4,5
		// i = 2, cur = 2,3,4,5
		// i = 3, cur = 3,4,5
		// now cur.next = 4
		cur.next = cur.next.next; // 找到需要删除的节点，修改其前后节点
		ListNode ans = dummy.next;
		return ans;
	}

	class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			this.val = x;
			this.next = null;
		}
		ListNode(int x, ListNode next) {
			this.val = x;
			this.next = next;
		}
	}
}
