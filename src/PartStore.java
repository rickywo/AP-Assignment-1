import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class PartStore.
 */
public class PartStore {

	/** The Constant TITLE. */
	static final String TITLE = "Computer Part Sales Program";
	
	/** Menu elements. */
	static String[] optionLines = { "Make Sale", "Accept Delivery",
			"Display Inventory", "Exit Program " };
	
	/** The option keys. */
	static char[] optionKeys = { 'A', 'B', 'C', 'X' };
	
	/** The options. */
	HashMap<Character, String> options = new HashMap<Character, String>();

	
	/** The parts. */
	Part[] parts = new Part[5];
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PartStore p = new PartStore();
		boolean run = true; // Keep program running until user exit it
		Scanner reader = new Scanner(System.in);
		
		// Initialize all Part objects here
		p.parts[0] = new Part("GBTITANX","Gigabyte 12GB GTX Titan X",1495.0,2);
		p.parts[1] = new Part("MSI980TI","MSI GTX 980 Ti 6GB Gaming",1089.0,3);
		p.parts[2] = new Part("ASR9290XOC","Asus 4GB R9 290X OC",455.0,1);
		p.parts[3] = new Part("ASR9270OC","Asus R9 270 OC",179.0,4);
		p.parts[4] = new Part("GW750TI2G","Gainward 2G GTX 750TI",169.0,2);
		p.initMenu();
		while(run) {
			p.printMenu();
			System.out.print(" Enter selection: ");
			char a = reader.next().charAt(0);
			switch(a) {
			case 'A':
				p.handleMakeSale();
				break;
			case 'B':
				p.handleAcceptDelivery();
				break;
			case 'C':
				p.handleDisplayInventory();
				break;
			case 'X':
				run = false;
				p.print("Goodbye ...");
				break;
			default: // Incorrect option is entered
				p.print("Invalid option ...");
				p.hold();
			}
		}
		
	}

	/**
	 * Handle make sale.
	 */
	private void handleMakeSale() {
		String pCode;
		boolean found = false;
		Scanner reader = new Scanner(System.in);
		print("\n Making Sale ...");
		System.out.print(" ->Enter product code: ");
		// Gets product code from user input
		pCode = reader.nextLine();
		for(Part o: parts) {
			if(pCode.equals(o.getProdCode())) {
				// Item with input product code is found 
				found = true;
				System.out.print(" ->Enter quantity: ");
				// Gets quantity of item to sale
				int quantity = reader.nextInt();
				if(Double.isNaN(o.makeSale(quantity))) {
					// Stock level less than acquired
					warning("Not enough items in the stock. ");
				}
				else {
					displayItem(o);
					print("\n\n Transaction completed. ");
				}
				break;
			}
		}
		if(!found) {
			// Cannot find the product with the code input by user
			warning("No such item in the stock.");
		}
		hold();
	}
	
	/**
	 * Handle accept delivery option.
	 */
	private void handleAcceptDelivery() {
		String pCode; // For Store user input
		boolean found = false;
		Scanner reader = new Scanner(System.in);
		print("\n Accepting Delivery ...");
		System.out.print(" ->Enter product code: ");
		// Gets product code from user input
		pCode = reader.nextLine();
		for(Part o: parts) { // Iteratively loop through all items 
			if(pCode.equals(o.getProdCode())) {
				// Item with input product code is found 
				found = true;
				System.out.print(" ->Enter quantity: ");
				// Gets quantity of item to accept
				int quantity = reader.nextInt();
				// Restock items
				o.restock(quantity);
				// Display item after restock
				displayItem(o);
				break;
			}
		}
		if(!found) {
			// Cannot find the product with the code input by user
			warning("No such item in the stock.");
		}
		hold();
	}
	
	/**
	 * Handle Display items in the inventory.
	 * Display as the following format:
	 * -------------------------------------------------------------------------------
	 * Product code  Description                                Price ($) Stock Level
	 * -------------------------------------------------------------------------------
	 * GBTITANX      Gigabyte 12GB GTX Titan X                    1495.00           2
	 * MSI980TI      MSI GTX 980 Ti 6GB Gaming                    1089.00           3
	 * ASR9290XOC    Asus 4GB R9 290X OC                           455.00           1
	 * ASR9270OC     Asus R9 270 OC                                179.00           4
	 * GW750TI2G     Gainward 2G GTX 750TI                         169.00           2
 	 */
	private void handleDisplayInventory() {
		print("\n");
		print("-------------------------------------------------------------------------------");
		print(String.format("%-14s%-40s%12s%12s","Product code","Description","Price ($)","Stock Level"));
		print("-------------------------------------------------------------------------------");
		for(Part o: parts) {
			print(o.toString());
		}
		hold();
	}
	
	/**
	 * Display single object as a item as the following format.
	 *  -------------------------------------------------------------------------------
	 *  Product code  Description                                Price ($) Stock Level
	 *  -------------------------------------------------------------------------------
	 *  ASR9290XOC    Asus 4GB R9 290X OC                           455.00          11
	 *
	 * @param o as a Part object
	 */
	private void displayItem(Part o) {
		print("\n\n\n\n");
		print("-------------------------------------------------------------------------------");
		print(String.format("%-14s%-40s%12s%12s","Product code","Description","Price ($)","Stock Level"));
		print("-------------------------------------------------------------------------------");
		print(o.toString());
	}
	
	/**
	 * Waiting for user presses any key to continue.
	 */
	private void hold() {
		Scanner reader = new Scanner(System.in);
		print("\n");
		print("Press enter to continue...");
		reader.nextLine();
	}
	
	/**
	 * Initializes the menu.
	 * Put each line and char into HashMap
	 */
	private void initMenu() {
		for (int i = 0; i < optionLines.length; i++) {
			options.put(optionKeys[i], optionLines[i]);
		}
	}

	/**
	 * Prints the menu as the following format.
	 * 
	 * Computer Part Sales Program
	 * ---------------------------
	 * Line1                     A
	 * Line2                     B
	 * Line3                     C
	 * Line4                     X
	 * 
	 */
	public void printMenu() {
		final String BR = "---------------------------"; // Separating line
		print(TITLE);
		print(BR);
		// Iteratively prints options in HashMap
		for (Map.Entry<Character, String> entry : options.entrySet()) {
			// Key: char, Value: String
			print(String.format("%-26s%c", entry.getValue(), entry.getKey()));
		}
	}

	/**
	 * Prints Warning message.
	 *
	 * @param s the warning message
	 */
	public void warning(String s) {
		print("(Warning) "+s);
	}
	/**
	 * Prints string with a space indent.
	 *
	 * @param s the string to be printed
	 */
	public void print(String s) {
		System.out.printf(" %s\n", s);
	}
}
