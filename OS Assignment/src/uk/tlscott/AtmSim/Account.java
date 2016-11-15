package uk.tlscott.AtmSim;

import java.util.ArrayList;

/**
 * A class to model a bank account.
 * 
 * All transactions with the account are stored in a list of {@link Transaction Transactions}.
 * <br>
 * Money can be deposited or withdrawn from the account.
 * 
 * @author Thomas Scott
 */
public class Account {
	
	private int  balance;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();

	/**
	 * Creates a new account with the balance passed.
	 * <br>
	 * The initial balance is added as the first {@link Transaction} 
	 * of the account. 
	 * 
	 * @param startingBalance  the initial balance of the account.
	 */
	public Account(int startingBalance) {
		this.balance = startingBalance;
		transactions.add(new Transaction(balance));
	}

	/**
	 * Returns the current balance of the account.
	 * 
	 * @return current  account balance.
	 */
	public int balance() {
		return balance;
	}

	/**
	 * Withdraws amount from account, 
	 * reduces account balance by amount.
	 * <p>
	 * If amount to withdraw will result in the balance
	 * being less than zero, thread will {@link Object#wait wait} until the balance
	 * is large enough to make the withdrawal.
	 * <p>
	 * The withdrawal is recorded by the account as a {@link Transaction}.
	 * 
	 * @param amount  	to be withdrawn from account.
	 */
	public synchronized void withdraw(int amount) {
		// have the thread wait if withdrawal will result in negative balance
		while(amount > balance()) {
			try {
				wait();
			} catch (InterruptedException e) {}
		}
		
		balance -= amount;
		transactions.add(new Transaction(numberOfTransactions(), Thread.currentThread().getName(), amount, 0, balance));
	}

	/**
	 * Deposits amount into account, 
	 * increases current account balance by amount.
	 * <p>
	 * If the balance after deposit is greater than zero,
	 * will call {@link Object#notifyAll notifyAll()} to allow other threads to 
	 * attempt to withdraw.
	 * <p>
	 * The withdrawal is recorded by the account as a {@link Transaction}.
	 * 
	 * @param 	amount 	to deposit into account.
	 */
	public synchronized void deposit(int amount) {
		balance += amount;
		transactions.add(new Transaction(numberOfTransactions(), Thread.currentThread().getName(), 0, amount, balance));
		
		notifyAll();
	}

	/**
	 * Returns an ArrayList of all {@link Transaction Transactions} 
	 * the account has made.
	 * 
	 * 
	 * @return {@code ArrayList<Transaction>}	 containing all transactions on the account.
	 */
	public ArrayList<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * Returns the number of transactions the account has made.
	 * 
	 * @return	int	the number of transactions.
	 */
	private int numberOfTransactions() {
		return transactions.size();
	}
	
}
