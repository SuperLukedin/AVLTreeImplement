package orderedDictionaryADT;

public class SkipListNode<V> {
	private int key;
	private V val; 	// using 'value' will cause error - seems this is a reserved keywords?
	private int level;
	private SkipListNode<V> prev;
	private SkipListNode<V> next;
	private SkipListNode<V> up;
	private SkipListNode<V> down;
	
	//private SkipListNode<K, V> lv0Next; // not sure whether we will need this or not..
	// Ravi's slides say every node will have a lv0 link pointing to next node, and 'may' have lv1 ... lv_m links
	
	public SkipListNode(int k, V v, int l){
		this.key = k;
		this.val = v;
		this.level = l;
		
		this.setUp(null);
		this.setDown(null);
	}
	
	public SkipListNode(int k, V v){
		this.key = k;
		this.val = v;
		this.level = 1;
		
		this.setUp(null);
		this.setDown(null);
	}
	
	public SkipListNode(int k){
		this.key = k;
		this.val = null;
		this.level = 0; // = base level
		
		this.setUp(null);
		this.setDown(null);
	}
	

	
	public int getKey() {return key;}
	public void setKey(int k) {this.key = k;}
	
	public V getValue() {return val;}
	public void setValue(V v) {this.val = v;}
	
	public int getLevel() {return level;}
	public void setLevel(int level) {this.level = level;}
	
	public SkipListNode<V> getPrev() {return prev;}
	public void setPrev(SkipListNode<V> pv) {this.prev = pv;}
	
	public SkipListNode<V> getNext() {return next;}
	public void setNext(SkipListNode<V> nx) {this.next = nx;}

	public SkipListNode<V> getUp() {return up;}
	public void setUp(SkipListNode<V> up) {this.up = up;}

	public SkipListNode<V> getDown() {return down;}
	public void setDown(SkipListNode<V> down) {this.down = down;}

//	public SkipListNode<K, V> getLv0Next() {return lv0Next;}
//	public void setLv0Next(SkipListNode<K, V> lv0Next) {this.lv0Next = lv0Next;}
}
