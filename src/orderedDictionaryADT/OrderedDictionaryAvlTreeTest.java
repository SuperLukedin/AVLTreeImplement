package orderedDictionaryADT;

import java.util.ArrayList;

public class OrderedDictionaryAvlTreeTest {
    public static void main(String[] args) {
        AVLTreeNode root = new AVLTreeNode(40, 22);
        ArrayList<Integer> newList = new ArrayList();
        OrderedDictionaryAvlTree tree = new OrderedDictionaryAvlTree(root, newList);
        tree.insertElement(33, 22);
        tree.insertElement(43, 32);
        tree.insertElement(7, 12);
        tree.insertElement(88, 13);
        tree.insertElement(78, 66);
        tree.insertElement(7, 12);
        tree.insertElement(2, 12);
        tree.insertElement(3, 12);
        tree.insertElement(4, 12);
        tree.insertElement(47, 12);
        tree.insertElement(87, 12);
        tree.insertElement(71, 12);
        tree.insertElement(28, 12);
        tree.insertElement(38, 12);
        tree.insertElement(99, 12);
        tree.insertElement(89, 12);
        tree.insertElement(1000, 12);
        tree.insertElement(165, 12);
        tree.InOrderShow(root);
        tree.closestKeyAfter(1000);
        tree.closestKeyAfter(165);
        tree.closestKeyAfter(71);
        tree.closestKeyAfter(333);

    }
}
