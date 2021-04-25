package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import is.hi.yatzee.vinnsla.YatzeeImp;
import is.hi.yatzee.vinnsla.Teningar;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;

public class TestTeningar {
	
	private Teningar dice;

	@Before
	public void before() {
		dice = new Teningar(5);
	}
	
	@Test
	public void testSetAndGetGeymdur() {
		boolean[] allStoredDices = dice.getGeymdur();
		for(int i = 0; i < allStoredDices.length;i++) {
			assertThat(allStoredDices[i], is(false));
		}
		int lastDice = allStoredDices.length-1;
		dice.setGeymdur(lastDice, true);
		allStoredDices = dice.getGeymdur();
		assertThat(allStoredDices[lastDice], is(true));
	}
	
	@Test
	public void testSetAndGetTeningar() {
		int[] allDice = dice.getTeningar();
		for(int i = 0; i < allDice.length;i++) {
			assertThat(allDice[i], is(0));
		}
		dice.setTeningur(0, 6);
		allDice = dice.getTeningar();
		assertThat(allDice[0], is(6));
	}
	 
	@Test
	public void testKastaFirstDiceNotStored() {
		Teningar watchedDice = Mockito.spy(dice);
		watchedDice.setTeningur(0, 0);
		Mockito.doReturn(6).when(watchedDice).naestaRandomTala();
		int result = watchedDice.kasta(0);
		assertThat(result, is(watchedDice.getTeningar()[0]));
		assertThat(result, is(6));
	}
	
	@Test
	public void testKastaLastDiceStored() {
		Teningar watchedDice = Mockito.spy(dice);
		watchedDice.setTeningur(4, 0);
		Mockito.doReturn(6).when(watchedDice).naestaRandomTala();
		watchedDice.setGeymdur(4, true);
		int result = watchedDice.kasta(4);
		assertThat(result, is(watchedDice.getTeningar()[4]));
		assertThat(result, is(0));
	}
	
}
