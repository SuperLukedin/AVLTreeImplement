package orderedDictionaryADT;

public interface OrderedDictionary<V> {
	
	public void insertElement(int key, V elem);
	
	public void removeElement(int key);
	
	public int findElement(int key);
	
	public int closestAfter(int key);
}
