package orderedDictionaryADT;

public interface OrderedDictionary<K, V> {
	
	public void insertElement(K key, V elem);
	
	public V removeElement(K key);
	
	public V findElement(K key);
	
	public K closestAfter(K key);
}
