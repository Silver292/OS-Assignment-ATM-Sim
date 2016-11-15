package uk.tlscott.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import uk.tlscott.AtmSim.Account;
import uk.tlscott.AtmSim.Card;

public class _CardTest {

	private Account account = new Account(1000);
	
	@Test
	public void cardIDShouldIncrementWithCreationOfNewCard() {
		Card[] cards = new Card[10];
		
		for (int i = 0; i < 10; i++) {
			cards[i] = new Card(account);
		}
		
		for (int i = 0; i < cards.length; i++) {
			assertEquals("card id should be", i+1, cards[i].getId());
		}
	}

	
	@Test 
	public void cardLocalBalanceIsZeroBeforeSimulation() {
		Card card = new Card(account);
		assertEquals(0, card.localBalance());
	}
}
