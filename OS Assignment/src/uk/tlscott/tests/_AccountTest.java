package uk.tlscott.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import uk.tlscott.AtmSim.Account;
import uk.tlscott.AtmSim.Transaction;

public class _AccountTest {

	private static final int STARTING_BALANCE = 1000;

	private Account createAccount() {
		return new Account(STARTING_BALANCE);
	}


	@Test
	public void balanceIsSetInConstructor() {
		Account account = createAccount();
		assertEquals(1000, account.balance());
		
		account = new Account(500);
		assertEquals(500, account.balance());
	}
	
	
	@Test
	public void balanceChangesAfterWithdrawal() {
		Account account = createAccount();
		
		account.withdraw(500);
		assertEquals(500, account.balance());
		account.withdraw(300);
		assertEquals(200, account.balance());
	}


	@Test
	public void balanceIncreasesAfterDeposit() {
		Account account = createAccount();
		
		account.deposit(500);
		assertEquals(1500, account.balance());
		account.deposit(500);
		assertEquals(2000, account.balance());
	}
	
	@Test
	public void withdrawBlocksIfWithrawalIsMoreThanBalance() {
		Account account    = new Account(0);
		WithdrawCard withdraw = new WithdrawCard(account, 100);
		
		withdraw.start();
		
		assertFalse("Withdrawal should not complete", withdraw.hasCompleted());
	
		account.deposit(100);
		
		// wait for thread to complete
		try { Thread.sleep(100); } catch (InterruptedException e1) {}
		
		assertTrue("Withdrawal should complete", withdraw.hasCompleted());
		assertEquals("Balance should be zero", 0, account.balance());
		
		try {
			withdraw.join();
		} catch (InterruptedException e) {}
	}
	
	@Test
	public void accountTransactionsRecordStartingBalance() {
		Account account = createAccount();
		List<Transaction> transactions = account.getTransactions();
		
		assertEquals("Starting size should be ", 1, transactions.size());
		assertEquals("first transaction balance should be", String.format("%45s%-15s", "", STARTING_BALANCE), transactions.get(0).toString());
	}
	
	// Card test object
	class WithdrawCard extends Thread {

		protected Account account;
		protected int amount;
		protected boolean completed = false;
		
		public WithdrawCard(Account account, int amount) {
			this.account = account;
			this.amount  = amount;
		}
		
		@Override
		public void run() {
			account.withdraw(amount);
			completed = true;
		}
		
		public boolean hasCompleted() {
			return completed;
		}
	}
}
