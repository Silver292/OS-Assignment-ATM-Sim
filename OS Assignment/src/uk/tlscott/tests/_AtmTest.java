package uk.tlscott.tests;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.Assertion;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import org.junit.contrib.java.lang.system.SystemOutRule;

import uk.tlscott.AtmSim.Atm;

public class _AtmTest {

	private static final String[] NO_ARGS = new String[] {""};
	private static final String ARGUMENTS_MUST_BE_NUMBERS = "Arguments must be numbers.";
	private static final String USAGE_MESSAGE = "USAGE: Atm number_of_cards balance";

	@Rule
	public final ExpectedSystemExit exit = ExpectedSystemExit.none();
	
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
	
	@Test
	public void programGivesUsageAndExits_whenNoArgumentsPassed() {
		exit.expectSystemExitWithStatus(1);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals(USAGE_MESSAGE + "\n", systemOutRule.getLogWithNormalizedLineSeparator());
			}
		});
		Atm.main(NO_ARGS);
	}

	@Test
	public void programGivesUsageAndExits_whenTooFewArgumentsPassed() {
		exit.expectSystemExitWithStatus(1);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals(USAGE_MESSAGE + "\n", systemOutRule.getLogWithNormalizedLineSeparator());
			}
		});
		Atm.main(new String[] {"test"});
	}
	
	@Test
	public void programGivesUsageAndExits_whenTooManyArgumentsPassed() {
		exit.expectSystemExitWithStatus(1);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals(USAGE_MESSAGE + "\n", systemOutRule.getLogWithNormalizedLineSeparator());
			}
		});
		Atm.main(new String[] {"too", "many", "args"});
	}

	@Test
	public void programGivesErrorAndExits_whenPassedStrings() {
		exit.expectSystemExitWithStatus(1);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals(ARGUMENTS_MUST_BE_NUMBERS + "\n", systemOutRule.getLogWithNormalizedLineSeparator());
			}
		});
		Atm.main(new String[] {"test", "string"});
	}
	
	@Test
	public void programGivesErrorAndExits_whenPassedFloats() {
		exit.expectSystemExitWithStatus(1);
		exit.checkAssertionAfterwards(new Assertion() {
			public void checkAssertion() {
				assertEquals(ARGUMENTS_MUST_BE_NUMBERS + "\n", systemOutRule.getLogWithNormalizedLineSeparator());
			}
		});
		Atm.main(new String[] {"1.0", "100.0"});
	}
}
