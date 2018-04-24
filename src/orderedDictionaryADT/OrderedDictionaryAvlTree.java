package orderedDictionaryADT;

import java.util.ArrayList;
import java.util.Collections;

public class OrderedDictionaryAvlTree<V> extends AvlTree implements OrderedDictionary<V> {
	public OrderedDictionaryAvlTree(AVLTreeNode root, ArrayList theList) {
		super(root, theList);
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
		if (isFound(this.root, key)) {
			System.out.println("The key '" + key + "' is Found." );
			return find(this.root, key).key;
		} else {
			System.out.println("The key '" + key + "' does not exist.");
			return -1;
		}
	}

	@Override
	public int closestKeyAfter(int key) {
		if (isFound(this.root, key)) {
			AVLTreeNode theNode = find(this.root, key);
			if (theNode.right != null) {
				int ans = getInorderSuccessor(theNode.right).key;
				System.out.println("The closest key after '" + key + "' "
						+ " is " + " '" + ans + "'.\n");
				return ans;
			} else if (theNode.right == null){
				findAndVisitedArray(this.root, key);
				if ((int) Collections.max(visitedList) < key){
					System.out.println("No key is greater than '" + key + "'.\n");
					return -1;
				} else {
					for (int i = visitedList.size() - 1; i >= 0; i--) {
						if ((int) visitedList.get(i) > key) {
							int ans = (int) visitedList.get(i);
							System.out.println("The closest key after '" + key + "' is '" + ans + "'\n");
							return ans;
						}
					}
				}
			} else {
				return -1;
			}
		} else {
			System.out.println("The key '" + key + "' does not exist.");
			return -1;
		}
		return -1;
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
