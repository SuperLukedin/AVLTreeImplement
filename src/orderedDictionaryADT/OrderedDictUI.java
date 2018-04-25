//package orderedDictionaryADT;
//
//import java.util.Scanner;
//
//public class OrderedDictUI {
//	private static OrderedDictionary<String> memory;
//	private final String HELP_MANUAL = ""
//			+ "help			: Show this help doc.\n"
//			+ "quit, exit		: Quit the test program.\n"
//			+ ""
//			+ "reset skl		: Clear existed Skl or avl instance, create a SkipList instance.\n"
//			+ "reset avl		: Clear existed Skl or avl instance, create a AVL_Tree instance.\n"
//			+ ""
//			+ ""
//			+ "ins $k $v		: Insert (key k, value v) .\n"
//			+ "del $k			: Remove (key k) .\n"
//			+ "find $k			: Find (key k) within the data.\n"
//			+ "caf	$k			: Returns closest key larger than k.\n"
//			+ ""
//			+ "NOTE:	"
//			+ "- Currently key should be integer and value should be String.\n"
//			+ "- commands and arguments should be separated by one space character.\n"
//			+ "- Ctrl-C signal not supported. Type 'quit' then hit ENTER to quit.\n"
//			+ "";
//
//	public static void main(String[] args) {
//		OrderedDictUI UI = new OrderedDictUI();
//		Scanner sc = new Scanner(System.in);
//		System.out.println("################################################");
//		System.out.println("---- WELCOME TO ORDERED DICT USER INTERFACE ----");
//		while(true) {
//			System.out.print("\n>>> ");
//			String in = sc.nextLine();
//
//
//			if (in.equals("quit") || in.equals("exit")) {break;}
//			else if (in.equals("help")) {System.out.println(UI.HELP_MANUAL);}
//			else if (in.equals("reset skl")) {
//				memory = new OrderedDictionarySkipList();
//				System.out.println("An empty SkipList was created after cleaning existing instance.");
//			}
//			else if (in.equals("reset avl")) {
//				memory = new OrderedDictionaryAvlTree();
//				System.out.println("An empty AVL Tree was created after cleaning existing instance.");
//			}
//
//			System.out.printf("YOUR INPUT IS %s", in);
//		}
//
//		sc.close();
//	}
//
//}
