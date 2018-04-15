package orderedDictionaryADT;

public class SkipListNode<K, V> {
	private K key;
	private V val; 	// using 'value' will cause error - seems this is a reserved keywords?
	private int level;
	private SkipListNode<K, V> prev;
	private SkipListNode<K, V> next;
	
	//private SkipListNode<K, V> lv0Next; // not sure whether we will need this or not..
	// Ravi's slides say every node will have a lv0 link pointing to next node, and 'may' have lv1 ... lv_m links
	
	public SkipListNode(K k, V v, int l){
		this.key = k;
		this.val = v;
		this.level = l;
	}
	
	
	

	
	public K getKey() {return key;}
	public void setKey(K k) {this.key = k;}
	
	public V getValue() {return val;}
	public void setValue(V v) {this.val = v;}
	
	public int getLevel() {return level;}
	public void setLevel(int level) {this.level = level;}
	
	public SkipListNode<K, V> getPrev() {return prev;}
	public void setPrev(SkipListNode<K, V> prev) {this.prev = prev;}
	
	public SkipListNode<K, V> getNext() {return next;}
	public void setNext(SkipListNode<K, V> next) {this.next = next;}

//	public SkipListNode<K, V> getLv0Next() {return lv0Next;}
//	public void setLv0Next(SkipListNode<K, V> lv0Next) {this.lv0Next = lv0Next;}
}
