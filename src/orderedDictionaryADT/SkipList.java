package orderedDictionaryADT;
import java.util.Random;

public class SkipList<V> {
	private SkipListNode<V> negInf; // Level(h) head, i.e. Upper-Left node
	private SkipListNode<V> posInf; // Level(h) tail, i.e. Upper-right node
	private int height;
	private int size;
	// we might or might not need a variable of int MAX_LEVEL, in here we do not implement MAX_LEVEL constraint. 
	// Experiment: For 200000 trials, only ~ 10 trials have >15 consecutive 'head' (Random.nextInt(2) = 0 OR 1)
	// Thus, ignoring MAX_LEVEL constraint is not a problem. 
	
	public SkipList() {
		this.negInf = new SkipListNode<V>(Integer.MIN_VALUE);
		this.posInf = new SkipListNode<V>(Integer.MAX_VALUE);
		negInf.setNext(posInf);
		posInf.setPrev(negInf);
		
		this.setHeight(0); // once an element was added, height should be set to 2
	}
	
	public SkipListNode<V> skipSearch(int k) { // find largest key before k
		//while(below(k) != null) {
			
		//}
		

		return null;
	}
	
	public void skipinsert(int k, V v) {
		SkipListNode<V> ins = new SkipListNode<V>(k, v);
		// search closest node before k
		SkipListNode<V> prevNode = skipSearch(k);
		// setup double link       prevNode === ins === after(prevNode) 
		ins.setNext(after(prevNode));
		ins.setPrev(prevNode);
		after(prevNode).setPrev(ins);
		prevNode.setNext(ins);
		
		Random rand = new Random();
		rand.setSeed(631);
		
		SkipListNode<V> curr = ins;
		while (rand.nextInt(2) == 1) { //randomly generating 0 or 1, if 1 then add at top
			SkipListNode<V> prevCurr = curr;
			while (above(prevNode) == null) {
				prevNode = before(prevNode); // scan backward
			}
			// if prevNode == this.negInf, means currently in upper-most layer => should create top layer before insert
			if (prevNode.equals(this.negInf)) {
				/* create a new layer:
				 *   					| newNegInf(prevNode)=============== curr ====== newPosInf |
				 * 							||								  ||			||
				 * 						| this.negInf ======= (prevNode) === prevCurr ===== this.posInf |
				 */
				SkipListNode<V> newNegInf = new SkipListNode<V>(Integer.MIN_VALUE, null, this.height+1);
				SkipListNode<V> newPosInf = new SkipListNode<V>(Integer.MAX_VALUE, null, this.height+1);
				newNegInf.setNext(newPosInf);
				newPosInf.setPrev(newNegInf);
				
				newNegInf.setDown(this.negInf);
				newPosInf.setDown(this.posInf);
				this.negInf = newNegInf;
				this.posInf = newPosInf;
				
				this.height++;
			}
			prevNode = above(prevNode);
			// add curr Node into upper layer	(horizontal links)		
			curr.setNext(this.after(prevNode));
			curr.setPrev(prevNode);
			after(prevNode).setPrev(curr);
			prevNode.setNext(curr);
			// connect curr with prevCurr (vertical links)
			prevCurr.setUp(curr);
			curr.setDown(prevCurr);
		}
		size++;
	}
	
	public void skipRemove(int k) {
		SkipListNode<V> del = skipSearch(k);
		if (this.isEmpty()) {throw new IllegalStateException();}
		if (del.getKey() != k) {throw new NullPointerException();}
		
		while (above(del) != null) {del = above(del);} // scan up
		
		SkipListNode<V> pv = before(del);
		SkipListNode<V> nx = after(del);
		SkipListNode<V> belowDel = below(del);	
		
		while (belowDel != null) {
			// delete vertical link
			pv.setNext(nx);
			nx.setPrev(pv);
			del.setPrev(null);
			del.setNext(null);
			// delete horizontal link
			del.setDown(null);
			del = belowDel; // scan down 1 level
			del.setUp(null);
			
			if (pv == this.negInf && nx == this.posInf) { // should delete this layer
				this.negInf.setNext(null);
				this.posInf.setPrev(null);
				this.negInf = below(this.negInf);
				this.posInf = below(this.posInf);
				above(this.negInf).setDown(null);
				above(this.posInf).setDown(null);
				this.negInf.setUp(null);
				this.posInf.setUp(null);
				
				this.height--;
			}
			
			pv = before(del);
			nx = after(del);
			belowDel = below(del);	
		}
		// when belowDel is null:
		pv.setNext(nx);
		nx.setPrev(pv);
		
		size--;
	}
	
	public void Visualize() {
		System.out.printf("Currently the SkipList has %d element in %d levels\n", this.size, this.height);
		// loop thru each level and print element with tab-separated format
	}
	
	
	
	
	public boolean isEmpty() {return (this.height == 1);}
	
	public SkipListNode<V> before(SkipListNode<V> n) {return n.getPrev();}
	public SkipListNode<V> after(SkipListNode<V> n) {return n.getNext();}
	public SkipListNode<V> below(SkipListNode<V> n) {return n.getDown();}
	public SkipListNode<V> above(SkipListNode<V> n) {return n.getUp();}
	
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;} // useless?

	
}
