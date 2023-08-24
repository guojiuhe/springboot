package guojiuhe.demo.leecode;

public class _0006_removeListNodeElements {

	public static void main(String[] args) {
		/*
		 * 给你一个链表的头节点 head 和一个整数 val ，
		 * 请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
		 * 示例
		 * 输入：head = [1,2,6,3,4,5,6], val = 6 输出：[1,2,3,4,5]
		 */
	}
	
	// 递归
    public ListNode removeElements_1(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements_1(head.next, val);
        return head.val == val ? head.next : head;
    }
    
    // 迭代
    public ListNode removeElements_2(ListNode head, int val) {
    	// 创建虚拟节点
    	ListNode dummyHead = new ListNode(0); // 因为头节点可能被删除，所以创建虚拟节点
        dummyHead.next = head;
        ListNode temp = dummyHead;
        
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next; // 删除temp.next
            } else {
                temp = temp.next;
            }
        }
        return dummyHead.next;
    }
    // 迭代2 不创建虚拟节点
    public ListNode removeElements_3(ListNode head, int val) {
    	while (head != null && head.val == val) {
            head = head.next;
        }
        // 已经为null，提前退出
        if (head == null) {
            return null;
        }
        // 已确定当前head.val != val
        while (head.next != null) {
            if (head.next.val == val) {
            	head.next = head.next.next;
            } else {
            	head = head.next;
            }
        }
        return head;
    }
	
	public class ListNode {
		int val;
		ListNode next;

		ListNode() {
		}

		ListNode(int val) {
			this.val = val;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}
	}
}
