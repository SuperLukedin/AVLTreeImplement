package orderedDictionaryADT;

public class SkipListTest {
	public static void main(String[] args) {
		SkipList<Integer> skl = new SkipList<Integer>();
		skl.skipinsert(1, 1);
		skl.skipinsert(12, 1);
		skl.skipinsert(10, 1);
		skl.skipinsert(7, 1);
		skl.skipinsert(9, 1);
		skl.skipinsert(3, 1);
		skl.skipinsert(2, 1);
		skl.Visualize();
	}
}
