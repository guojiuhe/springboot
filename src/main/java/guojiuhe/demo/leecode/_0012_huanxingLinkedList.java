package guojiuhe.demo.leecode;

import java.util.HashSet;
import java.util.Set;

public class _0012_huanxingLinkedList {

	/*
	 * 给定一个链表的头节点 head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。 
	 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 
	 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
	 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
	 */

	/*
	 * 方案 1 双指针
	 * fast指针每次走2步 slow指针每次走一步, 移动次数为N， fast = 2*N, slow = N
	 * 若无环, fast走到null结束， 因为有环，fast一定为追杀slow
	 * 示例 
	 * 0 -> 1 -> 2 - > 3 -> 4 -> 5 -> null
	 * fast : 2 -> 4 -> 0 -> 2 -> 4 -> 0 -> 2 -> 4
	 * slow : 1 -> 2 -> 3 -> 4 -> 5 -> 0 -> 1 -> 2
	 * fast在0处追上了slow
	 * 
	 * 当fast=slow时终止循环，我们需要知道fast走了几圈追上slow
	 * 假设链表非环长度a, 环长度b, m n 分别是fast， slow走过的圈数
	 * fast 路程 = a + b * m 
	 * slow 路程 = a + b * n
	 * fast的路程是slow的2倍， 即 a + b * m = 2 * (a + b * n)
	 * b * (m - 2n) = a 这样计算无法得出结果
	 * 换个思路， fast 比 slow多走多少？ a + b * m  - a + b * n = (m - n) * b
	 * 和以下思路一样
	 * fast slow分别走完a后 进行绕圈， fast多绕X圈后追上slow
	 * fast多走了X * b 的路程，因为fast路程是slow的2倍， 相当于 slow走了 X * b ， fast在走了 2 * X * b
	 * 我们最终知道 fast走过 2 * X * b, slow走过 X * b
	 * 
	 * 但是我们怎么知道 a b 是多少呢?
	 * 我们知道slow走完n圈的路程是 a + b * n
	 * fast追上slow时 slow正好走了 X * b, 只要slow再走a, 即走到入圈处
	 * 重新构建双指针，fast从头开走， slow 从原地开走，速度一致， 这样fast slow在a步后相遇，即我们找到了入环位置
	 */
	public ListNode detectCycle(ListNode head) {
		ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null; // 因为奇偶性
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
	}
	
	/*
	 * 方案 2 遍历 hashset
	 * 如果没有环，遍历一次变结束 return null
	 * 如果有环，返回第一个重合点即可
	 * 
	 */
	public ListNode detectCycle_1(ListNode head) {
        ListNode pos = head;
        Set<ListNode> visited = new HashSet<ListNode>();
        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }
            pos = pos.next;
        }
        return null;
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
