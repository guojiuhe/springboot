package guojiuhe.demo.leecode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class _0010_removeListNode {

	/*
	 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
	 * 
	 *  
	 *  方案1: 从头遍历链表得到length, 再次遍历length - n + 1 ,
	 * 修改其next pre
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		//ListNode dummy = new ListNode(0, head);
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
		ListNode cur = dummy;
		// dummy -> head(0) -> 1 -> 2 -> 3 -> 4
		// length = 5, n = 2, 即需要删除 3, 获取到 2, 修改 2.next = 4
		// i = 1 : head(0); i = 2 : 1; i = 3 : 2
		for (int i = 1; i < length - n + 1; ++i) {
			cur = cur.next;
		}
		cur.next = cur.next.next; // 单向链表只修改next即可
		ListNode ans = dummy.next;
		return ans;
	}
	
	/* 
	 * 方案 2 : 利用栈
	 *  
	 *  */
	public ListNode removeNthFromEnd_1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 先进后出， 删除倒数第n个元素, 将最后n个元素出栈
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek(); // 和方案1思路一样，通过各种方法获取到前节点
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        
        /*
         * 这里利用LinkedList模拟栈，实际不是栈
         * push : addFirst(e)
         * pop : return removeFirst();
         * peek :  Retrieves, but does not remove, the head (first element) of this list.
         * 0 -> 1 -> 2 -> 3 -> 4
         * push 每次都add在头部， 4 -> 3 -> 2 -> 1 -> 0
         * pop 移除first，这样就模拟成栈
         * 
         * 模拟栈
         * 顺序插入，每次删除end
         * 倒叙插入，即每次都在头插入，每次删除头
         * 用ArrayList模拟栈
         */
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // 出栈后两个元素，及删除3 4
        int length = list.size();
        // 出栈后两个元素，及删除3 4
        for (int i = 0; i < 2; i ++) {
            list.remove(length - 1 - i);
        }
        /*
         * 补充arraylist 删除元素
         * for循环删除元素后，需要索引向前移动一位，因为删除元素后，后面的数据向前移动了一位
         * for循环逆序删除就不存在这个问题了，需要注意size会变化
         * 增强for循环，只能删除1个元素，必须break，否则ConcurrentModificationException
         * Iterator 可以完美删除
         */
        Iterator it = list.iterator();
        while (it.hasNext()) {
        	if (it.next() == "3") {
        		it.remove();
        	}
        }
        
        return ans;
	}

	/*
	 * 方案3 ： 双指针
	 * first second同时遍历， first比second超前n个节点， 当first到末尾null时，second指向倒数第n个节点
	 * 实际上我们需要获取倒数第n个节点的前一节点，这样方便删除
	 * 可以让first比second超前n+1， 也可以让first到末尾前一位（末尾：first == null, 前一位 ： first.next == null）
	 */
	public ListNode removeNthFromEnd_2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = dummy;
        ListNode second = dummy;
        // first先超前n个节点 , 如果超前n+1 , for (int i = 0; i < n + 1; i++) {
        // 0 -> 1 -> 2 -> 3 -> 4 -> null
        // n = 2, 需要删除3, 找到2, second : 0, first : 2
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        // first : 3, second : 1
        // first : 4, second : 2
        // first : null, second : 3 这里就没有让first到null, 因为要获取2
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
	
	class ListNode {
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
