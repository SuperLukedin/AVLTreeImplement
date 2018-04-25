package orderedDictionaryADT;

import java.util.NoSuchElementException;
import java.util.Random;

public class OrderedDictionarySkipList<V> extends SkipList<V> implements OrderedDictionary<V> {
	private SkipListNode<V> negInf; // Level(h) head, i.e. Upper-Left node
	private SkipListNode<V> posInf; // Level(h) tail, i.e. Upper-right node
	private int height;
	private int size;
	private Random rand;
	
	
	public OrderedDictionarySkipList() {
		this.negInf = new SkipListNode<V>(Integer.MIN_VALUE);
		this.posInf = new SkipListNode<V>(Integer.MAX_VALUE);
		negInf.setNext(posInf);
		posInf.setPrev(negInf);
		
		this.setHeight(0); // once an element was added, height should be set to 2
		this.rand = new Random(0);
	}

	@Override
	public void insertElement(int key, V elem) { skipInsert(key, elem); }

	@Override
	public void removeElement(int key) { skipRemove(key); }

	@Override
	public int findElement(int key) { // AKA findClosestBefore
		// TODO Auto-generated method stub
		SkipListNode<V> pv = this.skipSearch(key);
		if (pv.getKey() == Integer.MIN_VALUE) {
			throw new NoSuchElementException();
		} else {
			return(pv.getKey());
		}
	}

	@Override
	public int closestKeyAfter(int key) {
		// TODO Auto-generated method stub
		SkipListNode<V> e = super.getElementByKey(key);
		while (below(e)!=null) {
			e = below(e);
		}
		return(after(e).getKey());
	}
	
	public static void main(String[] args) {
		
		OrderedDictionarySkipList<Integer> sl = new OrderedDictionarySkipList<Integer>();
		
		sl.setSeed(63100);
		sl.insertElement(1,1);
		sl.insertElement(61,2);
		sl.insertElement(71,3);
		sl.insertElement(51,4);
		sl.insertElement(41,5);
		sl.insertElement(31,6);
		sl.insertElement(21,7);
		sl.insertElement(12,8);
		
		sl.Visualize();
		//System.out.print(skl.skipSearch(1) + "\n"); // should print a pointer to new node
		
		sl.skipRemove(71);
		sl.Visualize();
		sl.skipRemove(31);
		sl.Visualize();
		sl.skipRemove(1);
		sl.skipRemove(12);
		sl.Visualize();
		
		// TODO test for find() and closestAfter()
		int p = sl.findElement(61); 
	}

}
