package orderedDictionaryADT;

public interface OrderedDictionary<V> {
	
	public void insertElement(int key, V elem);
	
	public V removeElement(int key);
	
	public V findElement(int key);
	
	public int closestAfter(int key);
}
