package orderedDictionaryADT;

public class SkipList<K, V> {
	private SkipListNode<K, V> head;
	private SkipListNode<K, V> tail;
	
	// we might or might not need a variable of int MaxLevels
	
	public SkipList(K k, V v) {
		this.head = new SkipListNode<K, V>(k, v, 1);
		
	}
	
	
	
	public SkipListNode before(SkipListNode n) {
		// @TODO
		return null;
	}
	
	public SkipListNode after(SkipListNode n) {
		// @TODO
		return null;
	}
	
	public SkipListNode below(SkipListNode n) {
		// @TODO
		return null;
	}
	
	public SkipListNode above(SkipListNode n) {
		// @TODO
		return null;
	}
	

	public SkipListNode<K, V> getHead() {return head;}
	public void setHead(SkipListNode<K, V> head) {this.head = head;}


	public SkipListNode<K, V> getTail() {return tail;}
	public void setTail(SkipListNode<K, V> tail) {this.tail = tail;}




	private int numberLists;
	
}
