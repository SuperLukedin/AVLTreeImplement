package orderedDictionaryADT;
import java.util.Random;

public class SkipList<V> {
	private SkipListNode<V> negInf; // Level(h) head, i.e. Upper-Left node
	private SkipListNode<V> posInf; // Level(h) tail, i.e. Upper-right node
	private int height;
	private int size;
	private Random rand;
	
	// we might or might not need a variable of integer MAX_LEVEL, in here we do not implement MAX_LEVEL constraint. 
	// Experiment: For 200000 trials, only ~ 10 trials have >15 consecutive 'head' (Random.nextInt(2) = 0 OR 1)
	// Thus, ignoring MAX_LEVEL constraint is not a problem. 
	
	public SkipList() {
		this.negInf = new SkipListNode<V>(Integer.MIN_VALUE);
		this.posInf = new SkipListNode<V>(Integer.MAX_VALUE);
		negInf.setNext(posInf);
		posInf.setPrev(negInf);
		
		this.setHeight(0); // once an element was added, height should be set to 2
		this.rand = new Random(0);
	}
	
	public SkipListNode<V> skipSearch(int k) { // find largest key before k
		SkipListNode<V> p = this.negInf;
		while (below(p) != null) {
			p = below(p);
			while (after(p).getKey() <= k) {
				p = after(p);
			}
		}
		return p;
	}
	
	public void skipinsert(int k, V v) {
		if (skipSearch(k).getKey() == k) {
			System.out.print("key already existed.");
			return;
		} 
		
		System.out.print("---\n");
		SkipListNode<V> ins = new SkipListNode<V>(k, v);
		// search closest node before k
		SkipListNode<V> prevNode = skipSearch(k);
		// setup double link       prevNode === ins === after(prevNode) 
		System.out.printf("Inserting: elem. [%d] between [%d] & [%d]\n", 
				ins.getKey(), prevNode.getKey(), after(prevNode).getKey());
		if (prevNode.equals(this.negInf) && after(prevNode).equals(this.posInf)) {
			SkipListNode<V> newNeg = new SkipListNode<V>(Integer.MIN_VALUE, null, this.height+1);
			SkipListNode<V> newPos = new SkipListNode<V>(Integer.MAX_VALUE, null, this.height+1);
			// horizontal links
			newNeg.setNext(newPos);
			newPos.setPrev(newNeg);
			// vertical links
			newNeg.setDown(this.negInf);
			newPos.setDown(this.posInf);
			this.negInf.setUp(newNeg);
			this.posInf.setUp(newPos);
			
			this.negInf = newNeg;
			this.posInf = newPos;
			
			this.height++;
			System.out.print("Adding first element into SkipList (height++). \n");
		}
		
		ins.setNext(after(prevNode));
		ins.setPrev(prevNode);
		after(prevNode).setPrev(ins);
		prevNode.setNext(ins);
		
		SkipListNode<V> curr = ins;
		while (rand.nextInt(2) == 1) { //randomly generating 0 or 1, if 1 then add at top
			SkipListNode<V> prevCurr = curr;
			curr = new SkipListNode<V>(k, v, curr.getLevel()+1);
			
			while (above(prevNode) == null) { // ?????? should be if? what does 
				prevNode = before(prevNode); // scan backward until pointer can go up 1 level
			}
			// if prevNode == this.negInf, means currently in upper-most layer => should create top layer before insert
			if (curr.getLevel() == this.height) {
				/* create a new layer:
				 *   					| newNegInf(prevNode)=============== curr ====== newPosInf |
				 * 							||								  ||			||
				 * 						| this.negInf ======= (prevNode) === prevCurr ===== this.posInf |
				 */
				SkipListNode<V> newNegInf = new SkipListNode<V>(Integer.MIN_VALUE, null, this.height+1);
				SkipListNode<V> newPosInf = new SkipListNode<V>(Integer.MAX_VALUE, null, this.height+1);
				// horizontal links
				newNegInf.setNext(newPosInf);
				newPosInf.setPrev(newNegInf);
				// vertical links
				newNegInf.setDown(this.negInf);
				newPosInf.setDown(this.posInf);
				this.negInf.setUp(newNegInf);
				this.posInf.setUp(newPosInf);
				
				this.negInf = newNegInf;
				this.posInf = newPosInf;
				
				this.height++;
				System.out.print("Created new top level (height++). \n");
			}
			prevNode = above(prevNode);
			System.out.printf("Inserting: elem. [%d] between [%d] & [%d]\n", 
					curr.getKey(), prevNode.getKey(), after(prevNode).getKey());
			// add curr Node into upper layer	(horizontal links)
			curr.setNext(after(prevNode));
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
		if (skipSearch(k).getKey() != k) {throw new NullPointerException();}
		// scan up until top node for k
		while (above(del) != null) {del = above(del);} 
		// find before, after and below node for k
		SkipListNode<V> pv = before(del);
		SkipListNode<V> nx = after(del);
		SkipListNode<V> belowDel = below(del);	
		// count how many level has been reduced after deletion
		int levelCount= 0;
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
			
			if (above(pv) == this.negInf && above(nx) == this.posInf) { // should delete this layer
				this.negInf.setNext(null);
				this.posInf.setPrev(null);
				this.negInf = below(this.negInf);
				this.posInf = below(this.posInf);
				above(this.negInf).setDown(null);
				above(this.posInf).setDown(null);
				this.negInf.setUp(null);
				this.posInf.setUp(null);
				
				this.height--;
				levelCount++;
			}
			pv = before(del);
			nx = after(del);
			belowDel = below(del);
				
		}
		// when belowDel is null: Only reset vertical links
		pv.setNext(nx);
		nx.setPrev(pv);
		del.setPrev(null);
		del.setNext(null);
		
		size--;
		System.out.printf("Deleted elem. [%d] with %d levels\n", 
				k, levelCount);
	}
	
	public void Visualize() {
		System.out.printf("\n SkipList: Currently has %d element in %d levels: \n", 
				this.size, this.height);
		// loop thru each level and print element with tab-separated format
		
		// create an array (int[]) to store all keys (non-duplicate)
		int keys[] = new int[this.size];
		// loop thru the level0 to get all keys
		SkipListNode<V> itr = this.negInf;
		while(below(itr) != null) {
			itr = below(itr);
		}

		// copy all values to keys[]
		int i_keys = 0;
		while (after(after(itr)) != null) {
			itr = after(itr);
			keys[i_keys] = itr.getKey();
			i_keys++;
		}
		// loop thru each level to print element in this level
		for (int h = 0; h<=this.height; h++) {
			// initialize a pointer from negInf (Upper-left node)
			SkipListNode<V> ptr = this.negInf;
			for (int j =0; j<h; j++) {
				ptr = below(ptr);
			}
			// copy all nodes in this level
			int level_keys[] = new int[this.size];
			int j_keys = 0;
			while (after(after(ptr)) != null) {
				ptr = after(ptr);
				level_keys[j_keys] = ptr.getKey();
				j_keys++;
			}
			// array after $j_keys are empty. Use System.arraycopy to copy array between 0~$j_keys
			int[] partLevelKeys = new int[j_keys];
			System.arraycopy(level_keys, 0, partLevelKeys, 0, j_keys);
			
			// print the elements in this level in tab-separated format
			System.out.printf("Level[%d]\t", (this.height-h));
			System.out.print("negInf" + "\t");
			for (int i = 0; i<size; i++) {
				int flag = 0; // if (elem in keys[]) flag = 1, print elem; else flag = 0, print "."
				for (int j = 0; j<j_keys; j++) {
				
					if (keys[i] == partLevelKeys[j]) {
						flag = 1;
						break;
					} 
				}
				if (flag == 0) {
					System.out.print("---" + "\t");	
				} else {
					System.out.printf("-%d-\t", keys[i]);	
				}
			}
			System.out.print("posInf" + "\n");
		}
		System.out.print("\n");
	}
	
	public boolean isEmpty() {return (this.height == 0);}
	
	public SkipListNode<V> before(SkipListNode<V> n) {return n.getPrev();}
	public SkipListNode<V> after(SkipListNode<V> n) {return n.getNext();}
	public SkipListNode<V> below(SkipListNode<V> n) {return n.getDown();}
	public SkipListNode<V> above(SkipListNode<V> n) {return n.getUp();}
	
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;} // useless?

	public void setSeed(int seed) {
		this.rand = new Random(seed);
	}

	public static void main(String[] args) {
		SkipList<Integer> skl = new SkipList<Integer>();
		System.out.print("negInf addr:" + skl.posInf.getPrev() + "\n"); // pointer to negInf
		System.out.print("posInf addr:" + skl.negInf.getNext() + "\n"); // pointer to posInf
		// System.out.print(skl.skipSearch(1) + "\n"); // should print pointer to negInf
		
		skl.setSeed(631);
		skl.skipinsert(1, 1); // 1st elem
		skl.Visualize();
		//System.out.print(skl.skipSearch(1) + "\n"); // should print a pointer to new node
		skl.skipinsert(12, 1);
		skl.skipinsert(10, 1);
		skl.skipinsert(7, 1);
		skl.skipinsert(9, 1); // 5th elem
		skl.Visualize();
		skl.skipinsert(3, 1);
		skl.skipinsert(2, 1);
		skl.skipinsert(217, 1);
		skl.skipinsert(219, 1);
		skl.skipinsert(213, 1); // 10th elem
		skl.Visualize();
		
		skl.skipRemove(7);
		skl.Visualize();
		skl.skipRemove(10);
		skl.Visualize();
		skl.skipRemove(1);
		skl.skipRemove(219);
		skl.Visualize();
	}
}
