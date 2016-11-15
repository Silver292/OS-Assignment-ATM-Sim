package uk.tlscott.AtmSim;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * A class to model a bank card.
 * 
 * Simulates deposits and withdrawals from a bank account when ran.
 * 
 * @author Thomas Scott
 */
public class Card implements Runnable{
	
	private static AtomicInteger idCounter = new AtomicInteger(0);
	private int id;
	private int amountWithdrawn = 0;
	private int amountDeposited = 0;
	private Account account;
	
	/**
	 * Creates a new card for account passed.
	 * 
	 * @param account  to create card for.
	 */
	public Card(Account account) {
		this.account = account;
		this.id = idCounter.incrementAndGet();
	}
	
	/**
	 * Runs 20 transactions on the account.
	 * <br>
	 * Randomly determines whether to deposit or withdraw.
	 * Then takes/adds a random amount.
	 * <br>
	 * On completion prints the card id and the local balance.
	 * <br>
	 * Local balance calculated as : {@code amountWithdrawn - amountDeposited;}
	 * 
	 */
	public void run() {
		for (int i = 0; i < 20; i++) {
			int amount = (int)(Math.random()*10);
			if (Math.random() > 0.5) {
				amountWithdrawn += amount;
				account.withdraw(amount);
			} else {
				amountDeposited += amount;
				account.deposit(amount);
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {}
		}
		System.out.println("THREAD "+ getId() + " " + localBalance());
	}

	/**
	 * Returns the local balance of the card.
	 * <br>
	 * Local balance calculated as : {@code amountWithdrawn - amountDeposited;}
	 * @return int  the local balance.
	 */
	public int localBalance() {
		return amountWithdrawn - amountDeposited;
	}

	/**
	 * Returns the cards ID.
	 * 
	 * Card id is incremented every time a new card is created.
	 * 
	 * @return int  the cards id.
	 */
	public int getId() {
		return this.id;
	}
}
