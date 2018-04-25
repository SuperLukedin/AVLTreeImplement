package orderedDictionaryADT;

public class SkipListTest {
	public static void main(String[] args) {
		SkipList<Integer> skl = new SkipList<Integer>();
		skl.skipInsert(1, 1);
		skl.skipInsert(12, 1);
		skl.skipInsert(10, 1);
		skl.skipInsert(7, 1);
		skl.skipInsert(9, 1);
		skl.skipInsert(3, 1);
		skl.skipInsert(2, 1);
		skl.Visualize();
	}
}
