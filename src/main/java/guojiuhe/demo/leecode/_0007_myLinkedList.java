package guojiuhe.demo.leecode;

public class _0007_myLinkedList {
	/*
	 * 设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。val 是当前节点的值，next
	 * 是指向下一个节点的指针/引用。如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。假设链表中的所有节点都是 0-index 的。
	 * 
	 * 在链表类中实现这些功能：
	 * 
	 * get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。 
	 * addAtHead(val)：在链表的第一个元素之前添加一个值为val 的节点。插入后，新节点将成为链表的第一个节点。 
	 * addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
	 * addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val 的节点。如果 index等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
	 * deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。 
	 * 
	 * MyLinkedList linkedList = new MyLinkedList(); 
	 * linkedList.addAtHead(1);
	 * linkedList.addAtTail(3); 
	 * linkedList.addAtIndex(1,2); //链表变为1-> 2-> 3
	 * linkedList.get(1); //返回2 
	 * linkedList.deleteAtIndex(1); //现在链表是1-> 3
	 * linkedList.get(1); //返回3
	 */
	
	//size存储链表元素的个数
    int size;
    //虚拟头结点
    ListNode head;
	
	public _0007_myLinkedList() {
		size = 0;
		head = new ListNode(0);
	}
	//获取第index个节点的数值，注意index是从0开始的，第0个节点就是头结点
    public int get(int index) {
    	if (index < 0 || index >= size) {
            return -1;
        }
    	ListNode currentNode = head;
        //包含一个虚拟头节点，所以查找第 index+1 个节点, 详见 line 69 -> 73
    	// 链表的查找都是从头往后遍历 对比linkedlist 和 arraylist
        for (int i = 0; i <= index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.val;
    }
    //在链表最前面插入一个节点，等价于在第0个元素前添加
    public void addAtHead(int val) {
    	addAtIndex(0, val);
    }
    //在链表的最后插入一个节点，等价于在(末尾+1)个元素前添加
    public void addAtTail(int val) {
    	addAtIndex(size, val);
    }
    // 在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。
    // 如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
    // 相当于找到第 index 个节点， new.next = index.next, index.next = new, 
    public void addAtIndex(int index, int val) {
    	if (index > size) { // 超出范围
            return;
        }
        if (index < 0) { // 做出适配，也可报错 题目要求适配
            index = 0;
        }
        size++; // 相当于扩容
        //找到要插入节点的前驱
        // head -> 0 -> 1 -> 2 -> 3  head是虚拟节点， 0 1 2 3 是对应索引
        ListNode pred = head;
        // index = 0, 不进循环 head
        // index = 1, pred.next -> 0
        // index = 2,  pred.next.next -> 1
        // index = 3,  pred.next.next.next -> 2
        // 这里index比实际索引大1,正好获取的是index的前节点，所以可以直接修改 next&pre
        for (int i = 0; i < index; i++) { 
            pred = pred.next;
        }
        // 此时pred是index的前节点 pred.next = index节点
        ListNode newNode = new ListNode(val);
        newNode.next = pred.next; // 先 next 后 pre
        pred.next = newNode;
    }
    
    public void deleteAtIndex(int index) {
    	 if (index < 0 || index >= size) {
             return;
         }
         size--;
         ListNode pred = head;
         for (int i = 0; i < index; i++) {
             pred = pred.next;
         }
         pred.next = pred.next.next;
    }
    
    class ListNode {
        int val;
        ListNode next;
        ListNode(){}
        ListNode(int val) {
            this.val=val;
        }
    }
}
