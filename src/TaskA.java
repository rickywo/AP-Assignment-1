import java.util.*;
// TODO: Auto-generated Javadoc
/**
 * The Class TaskA. a simple food guessing game in which the player (user) can
 * select five (5) letters to reveal from the hidden food name after which they
 * will be required to guess what the food is. When the game starts a target
 * food name should be selected randomly from the following pre-defined list of
 * foods: artichoke, asparagus, cranberry, jackfruit, nectarine, persimmon,
 * pineapple, raspberry, tangerine
 * 
 * @author Ricky Wu
 */
public class TaskA {
	
	/** The Constant NUMBERTRYING. */
	private final static int NUMBERTRYING = 5; // Numbers of trying predefined
	static Scanner reader = new Scanner(System.in);
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		// Main function start the veg guessing game
		String[] vegList = { "artichoke", "asparagus", "cranberry",
				"jackfruit", "nectarine", "persimmon", "pineapple",
				"raspberry", "tangerine" };
		int rIndex = (int)(Math.random()*vegList.length);
		// Get a random number from Math.random() as index of vegList
		char[] veg = vegList[rIndex].toCharArray();
		// Pick a food name accordingly
	
		String ans; // Empty string for user to input answer
		int loop = NUMBERTRYING;
		System.out.println("==========================================");
		System.out.println(" Welcome to \" Simple Food Guessing Game \"");
		System.out.println("==========================================");
		
		char[] maskName = new char[veg.length];
		for(int i = 0 ; i < veg.length ; i ++) {
			maskName[i] = '-';
		}
		
		// First prompt of masked name
		printMaskedName(maskName);
		
		// Loop for 5 times for guessing
		for(int i = 0 ; i < loop ; i ++) {
			System.out.printf("\n\n Round #%d... ", i+1);
			checkInputChar(veg, maskName);
			printMaskedName(maskName);
		}
		// Ask user to guess the final answer
		System.out.print("\n\n Give your fianl answer : ");
		ans = reader.next();
		if(checkAnswer(veg,ans.toCharArray())) {
			System.out.println("\n\n。☆ 。☆。。 。☆。。☆ 。☆。。 。☆。。☆ 。☆。");
			System.out.println(" Congrats!! You have successfully\n completed this game");
			System.out.println("。☆ 。☆。。 。☆。。☆ 。☆。。 。☆。。☆ 。☆。");
		}
		else {
			System.out.println("\n\n==========================================");
			System.out.println(" It's a pity that you lose this game");
			System.out.printf(" The correct answer is: %s\n", vegList[rIndex]);
			System.out.println("==========================================");
		}

	}
	
	/**
	 * Prints the masked name as following format
	 * _ _ _ _ _ _ _ _ _
	 *
	 * @param a the char array of masked name to be passed
	 */
	private static void printMaskedName(char[] a) {
		System.out.printf(" The Food Name to Guess (%d characters) : ", a.length);
		for(char ch:a) {
			System.out.printf("%c ", ch);
		}
	}
	
	/**
	 * Check input char.
	 * Replace '_' in masked array if find any input character 
	 * matches char in original array 
	 * For example:
	 * input char: 'c'
	 * original: cranberry
	 * masked: c_______ <---- after running this procedure
	 * @param oa the original char array
	 * @param om the masked char array
	 */
	private static void checkInputChar(char[] oa, char[] om) {
		boolean flag = false; // A flag indicates the correctness of input char
		char c = ' '; // char for storing input char
		System.out.print("\n Input a Character to reveal any matched alphabet : ");
		// To validate the input is in between a valid range (a-z)
		while(!flag) {
			c = reader.next().charAt(0);
			if(c >= 'a' && c <= 'z') {
				flag = true;
			}
			else {
				System.out.print("\n Input a valid Character between a to z : ");
			}
		}
		// To replace char(s) hit in masked name array
		for(int i = 0 ; i < oa.length ; i ++) {
			if(oa[i] == c) {
				om[i] = c;
			}
		}
	}
	
	/**
	 * Check answer.
	 *
	 * @param oa the original char array
	 * @param om the masked char array
	 * @return true, if successful
	 */
	private static boolean checkAnswer(char[] oa, char[] om) {
		int hitCount = 0;
		for(int i = 0 ; i < oa.length ;i ++) {
			if(oa[i] == om[i]) hitCount ++; // Increase hit count by 1 when chars matched
		}
		if(hitCount == oa.length) 
			return true;
		else
			return false;
	}

}
