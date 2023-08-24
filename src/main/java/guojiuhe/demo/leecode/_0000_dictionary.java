package guojiuhe.demo.leecode;

public class _0000_dictionary {
	
	public enum DataStructure {
		ARRAY("数组","原生数组int[],String[]; ArrayList"),
		LISTNODE("链表",""),
		STACK("栈","先进(push)后出(pop),java.util.Stack;LinkedList模拟栈:add/addLast, removeLast or addFirst, removeFirst"),
		QUEUE("队列","先进先出, LinkedList模拟队列:add(尾加), poll(头出)"),
		TREE("树",""),
		BINARYTREE("二叉树","每个节点最多有2个子节点, 叶子节点：没有子节点的节点"),
		COMPLETEBINARYTREE("完全二叉树","除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。"),
		BALANCEBINARYTREE("平衡二叉树,AVL","一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1"),
		HEAP("堆","堆是一个完全二叉树；堆中每一个节点的值都必须大于等于（或者小于等于）其子树中每个节点的值。PriorityQueue 默认是小顶堆，但可以接收一个 Comparator 作为构造参数，从而来自定义元素优先级的先后"),
		BTree("BALANCE平衡树","多个叉,用于数据库，每个节点都保存数据"),
		BPlusTree("B+树","多个叉,用于数据库，叶子节点保存数据，子节点保存索引，每个叶子节点都有指向下一叶子节点的指针"),
		BLACKREDTREE("红黑树","根节点&叶子节点(NIL节点)必为黑色，红色不会连续，红色节点必须有两个黑色的子节点，从任一节点到其每个叶子的所有简单路径都包含相同数目的黑色节点"),
		HASH("哈希表","");
		
		/*
		 * ArrayList<E> extends AbstractList<E> 
		 * 		implements List<E>, RandomAccess, Cloneable, java.io.Serializable
		 * LinkedList<E> extends AbstractSequentialList<E>
    			implements List<E>, Deque<E>, Cloneable, java.io.Serializable
		 */

		private String name;
		private String note;
		DataStructure(String name, String note) {
			this.name = name;
			this.note = note;
		}
		public String getName() {
			return this.name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getNote() {
			return this.note;
		}
		public void setNote(String note) {
			this.note = note;
		}
	}
}
