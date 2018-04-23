package orderedDictionaryADT;

public class OrderedDictionaryAvlTree<V> extends AvlTree implements OrderedDictionary<V> {
	public OrderedDictionaryAvlTree(AVLTreeNode root) {
		super(root);
	}

	@Override
	public void insertElement(int key, V elem) {
		insertion(this.root, key, elem);
	}

	@Override
	public void removeElement(int key) {
		if (isFound(this.root, key)) {
			remove(this.root, key);
			System.out.println("The key " + "'" + key + "' was removed.\n");
		} else {
			System.out.println("The key " + "'" + key + "' does not exist.\n");
		}
	}

	@Override
	public int findElement(int key) {
		return find(this.root, key).key;
	}

	@Override
	public int closestAfter(int key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void InOrderShow(AVLTreeNode root) {
		super.InOrderShow(root);
	}

	@Override
	public void show(AVLTreeNode root) {
		super.show(root);
	}
}