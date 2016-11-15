package uk.tlscott.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.tlscott.AtmSim.Transaction;

public class _TransactionTest {

	@Test
	public void transactionPrintsCorrectly() {
		Transaction transaction = new Transaction(1, "1", 40, 0, 960);
		String expectedString = String.format("%-15s%-15s%-15s%-15s", "1(1)", "40", "", "960");
		assertEquals(expectedString, transaction.toString());
	}
	
	@Test
	public void transactionConstructedWithBalancePrintsCorrectly() {
		Transaction transaction = new Transaction(1000);
		String expectedString = String.format("%45s%-15s", "", 1000);
		assertEquals(expectedString, transaction.toString());
	}

}
