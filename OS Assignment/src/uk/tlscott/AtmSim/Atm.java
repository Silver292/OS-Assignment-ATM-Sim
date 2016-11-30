package uk.tlscott.AtmSim;

/**
 * 
 * Runs a number of transactions concurrently on an account with the balanced passed as an argument.
 * <br>
 * <br>
 * Execution: Atm number_of_cards balance
 * <br>
 * <br>
 * Where number_of_cards is the amount of threads to run concurrently 
 * and balance is the initial starting balance of the account.
 * <br>
 * Each thread will complete 20 transactions, randomly withdrawing or depositing a random amount.
 * <br>
 * The accounts balance should never be negative.
 * <br>
 * At the end of the transactions all transactions on the account will be printed.
 * 
 * @author Thomas Scott
 */
public class Atm {

	static final String ARGUMENTS_MUST_BE_NUMBERS = "Arguments must be numbers.";
	static final String USAGE_MESSAGE = "USAGE: Atm number_of_cards balance";
	
	public static void main(String[] args) {
		// Verify and store arguments
		int[] verifiedArgs = verifyArguments(args);
		
		int numberOfCards = verifiedArgs[0];
		int initialBalance = verifiedArgs[1];
		
		// create account and Thread array
		Account account = new Account(initialBalance);
		Thread[] cards = new Thread[numberOfCards];
		
		// fill the thread array and start the threads
		for (int i = 0; i < cards.length; i++) {
			cards[i] = new Thread(new Card(account));
			cards[i].setName(Integer.toString(i + 1));
			cards[i].start();
		}
		
		// Join the threads
		for (Thread card : cards) {
			try {
				card.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		System.out.println("\nComplete\n");

		// Print account transactions
		System.out.println(String.format("%-15s%-15s%-15s%-15s", "Transaction", "Withdrawal", "Deposit", "Balance"));
		for (Transaction transaction : account.getTransactions()) {
			System.out.println(transaction);
		}
	}

	/**
	 * Verifies the command line arguments passed to program.
	 * Returns an array of valid arguments.
	 * 
	 * Checks there are correct number of arguments, if not 
	 * will print error message and exit.
	 * 
	 * Checks all arguments can be parsed as integers, if not
	 * will print error message and exit.
	 * 
	 * @param args arguments passed via command line.
	 * @return verifiedArgs array of integers.
	 */
	private static int[] verifyArguments(String[] args) {
		int[] verifiedArgs = new int[2];
		
		// Check for two command line argument
		if(args.length != 2) {
			System.out.println(USAGE_MESSAGE);
			System.exit(1);
		}
		
		// make sure arguments are numbers
		try {
			verifiedArgs[0] = Integer.parseInt(args[0]);
			verifiedArgs[1] = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.out.println(ARGUMENTS_MUST_BE_NUMBERS);
			System.exit(1);
		}
		
		return verifiedArgs;
	}

}
