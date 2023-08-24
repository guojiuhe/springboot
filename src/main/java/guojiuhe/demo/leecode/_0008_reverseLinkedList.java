package guojiuhe.demo.leecode;

public class _0008_reverseLinkedList {

	public static void main(String[] args) {
		
	}
	// 反转链表 迭代
	public ListNode reverseLinkedList(ListNode head) {
		ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;// 保存下一个节点
            cur.next = prev;
            prev = cur;
            cur = temp;
            
			/*
			 * 此文件的head和虚拟head不同， 这里head = 0; 虚拟head.next = 0
			 * 0 -> 1 -> 2 -> 3 
			 * 0 <- 1 <- 2 <- 3
			 * 3 -> 2 -> 1 -> 0
			 * 第一次循环 
			 * cur = 0, temp = cur.next 保存 1
			 * 0.next = prev = null
			 * prev = 0
			 * cur = 1
			 * 
			 * 第2次循环 
			 * temp = cur.next 保存 2
			 * 1.next = 0
			 * prev = 1
			 * cur = 2
			 */
        }
        return prev;
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
