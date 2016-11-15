package uk.tlscott.AtmSim;

/**
 * Immutable object storing the contents of an account transaction.
 * 
 * @author Thomas Scott
 *
 */
public class Transaction {

	private final int transactionNumber;
	private final String threadID;
	private final int amountWithdrawn;
	private final int amountDeposited;
	private final int balance;
	
	/**
	 * Creates a {@code Transaction} with a balance, all other fields will be {@code 0}.
	 * <br>
	 * Should be used for initial transaction on {@link Account} creation.
	 * 
	 * @param balance  to store in the transaction.
	 */
	public Transaction(int balance) {
		this.balance      = balance;
		transactionNumber = 0;
		threadID          = "";
		amountWithdrawn   = 0;
		amountDeposited   = 0;
		balance           = 0;
	}
	
	/**
	 * 
	 * Creates a {@code Transaction} with arguments passed.
	 * 
	 * @param transactionNumber  number of the transaction.
	 * @param threadID  id of the thread creating the transaction.
	 * @param amountWithdrawn  amount withdrawn from account.
	 * @param amountDeposited  amount deposited into the account.
	 * @param balance  balance after transaction is completed.
	 */
	public Transaction(int transactionNumber, String threadID, int amountWithdrawn, int amountDeposited, int balance) {
		this.transactionNumber = transactionNumber;
		this.threadID          = threadID;
		this.amountWithdrawn   = amountWithdrawn;
		this.amountDeposited   = amountDeposited;
		this.balance           = balance;
	}

	/**
	 * Returns the Transaction as a string.
	 * <br>
	 * Transaction is in the format:
	 * <br>
	 * {@code
	 * transactionNumber(Card/Thread ID)		amountWithdrawn 		amountDeposited			balance
	 *  }
	 *  <br>
	 *  {@code
	 * 	1(1)	10		10		100
	 * }
	 * 
	 * @return String  transaction
	 */
	@Override
	public String toString() {
		String countAndThread;
		countAndThread = transactionNumber == 0 ? "" : String.format("%d(%s)", this.transactionNumber, this.threadID);
		
		return String.format("%-15s%-15s%-15s%-15s", 
				countAndThread,
				formatBlankIfZero(amountWithdrawn),
				formatBlankIfZero(amountDeposited),
				this.balance);
	}

	/**
	 * Formats a string based on if integer is zero.
	 * If integers is zero will return an empty string.
	 * If integer is not zero will return integer as string.
	 * 
	 * @param value to format
	 * @return empty string <code>("")</code> if value is zero, value as string otherwise.
	 */
	private String formatBlankIfZero(int value) {
		return value == 0 ? "" : Integer.toString(value);
	}
}
