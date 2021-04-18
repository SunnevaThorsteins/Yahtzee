package tests;

import static org.junit.Assert.*;
import org.junit.Test;
import is.hi.yatzee.vinnsla.YatzeeImp;
import is.hi.yatzee.vinnsla.Teningar;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;

public class TestYhatzeeImpl {
	
	private YatzeeImp yhatzeeGame;
	private Teningar mockDice;

	@Before
	public void before() {
		yhatzeeGame = new YatzeeImp();
		mockDice = Mockito.mock(Teningar.class);
		
	}
	
	@Test
	public void testReiknaEinfaldaPlayer1PossiblePoints() {
		int[] diceArray = new int[]{1,2,2,2,2};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		yhatzeeGame.nuverandi = 1;
		int einfaldir1Value = yhatzeeGame.einfaldir1;
		yhatzeeGame.mogulegStig = true;
		int calculatedPoints = yhatzeeGame.reiknaEinfalda(0, mockDice);
		assertThat(1, is(calculatedPoints));
		assertThat(yhatzeeGame.stigin1[0], is(calculatedPoints));
		assertThat(einfaldir1Value, is(yhatzeeGame.einfaldir1));
	} 
	
	@Test
	public void testReiknaEinfaldaPlayer2PossiblePoints() {
		int[] diceArray = new int[]{6,6,6,6,6};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		yhatzeeGame.nuverandi = 2;
		int einfaldir2Value = yhatzeeGame.einfaldir2;
		yhatzeeGame.mogulegStig = true;
		int calculatedPoints = yhatzeeGame.reiknaEinfalda(5, mockDice);
		assertThat(30, is(calculatedPoints));
		assertThat(yhatzeeGame.stigin2[5], is(calculatedPoints));
		assertThat(einfaldir2Value, is(yhatzeeGame.einfaldir1));
	}
	
	@Test
	public void testReiknaEinfaldaPlayer1NoPossiblePoints() {
		int[] diceArray = new int[]{1,1,1,1,1};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int einfaldir1Value = yhatzeeGame.einfaldir1;
		yhatzeeGame.nuverandi = 1;
		yhatzeeGame.mogulegStig = false;
		int calculatedPoints = yhatzeeGame.reiknaEinfalda(1, mockDice);
		assertThat(0, is(calculatedPoints));
		assertThat(yhatzeeGame.stigin1[0], is(calculatedPoints));
		assertThat(einfaldir1Value+1, is(yhatzeeGame.einfaldir1));
		
	}
	
	@Test
	public void testReiknaEinfaldaPlayer2NoPossiblePoints() {
		int[] diceArray = new int[]{5,5,5,5,6};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int einfaldir2Value = yhatzeeGame.einfaldir2;
		yhatzeeGame.nuverandi = 2;
		yhatzeeGame.mogulegStig = false;
		int calculatedPoints = yhatzeeGame.reiknaEinfalda(5, mockDice);
		assertThat(6, is(calculatedPoints));
		assertThat(yhatzeeGame.stigin2[5], is(calculatedPoints));
		assertThat(einfaldir2Value+1, is(yhatzeeGame.einfaldir2));
	}
	
	@Test
	public void testReiknaSensinnPlayer1() {
		int[] diceArray = new int[]{1,1,1,1,1};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		yhatzeeGame.nuverandi = 1;
		int calculatedPoints = yhatzeeGame.reiknaSensinn(13, mockDice);
		assertThat(5, is(calculatedPoints));
		assertThat(yhatzeeGame.stigin1[13], is(calculatedPoints));
	}
	
	@Test
	public void testReiknaSensinnPlayer2() {
		int[] diceArray = new int[]{6,6,6,6,6};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		yhatzeeGame.nuverandi = 2;
		int calculatedPoints = yhatzeeGame.reiknaSensinn(0, mockDice);
		assertThat(30, is(calculatedPoints));
		assertThat(yhatzeeGame.stigin2[0], is(calculatedPoints));
	}
	
	@Test
	public void testReiknaSummaPlayer1() {
		yhatzeeGame.nuverandi = 1;
		int expectedSum = 0;
		for(int i = 0; i <= 13; i++) {
			yhatzeeGame.stigin1[i] = i;
			expectedSum += i;
		}
		int yhatzeeSum = yhatzeeGame.reiknaSumma();
		assertThat(yhatzeeSum, is(expectedSum));
	}
	
	@Test
	public void testReiknaSummaPlayer2() {
		yhatzeeGame.nuverandi = 2;
		int expectedSum = 0;
		int yhatzeeSum = yhatzeeGame.reiknaSumma();
		assertThat(yhatzeeSum, is(expectedSum));
	}
	
	@Test
	public void testReiknaEinsAllTheSame() {
		int[] diceArray = new int[]{6,6,6,6,6};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int sameDiceCalculated = yhatzeeGame.reiknaEins(6, mockDice);
		assertThat(30, is(sameDiceCalculated));
	}
	
	@Test
	public void testReiknaEinsNoneTheSame() {
		int[] diceArray = new int[]{1,2,3,5,6};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int sameDiceCalculated = yhatzeeGame.reiknaEins(7, mockDice);
		assertThat(0, is(sameDiceCalculated));
	}
	
	@Test
	public void testReiknaRodNoRow() {
		int[] diceArray = new int[]{1,6,1,6,1};
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaRod(9, mockDice);
		assertThat(0, is(points));
	}
	
	@Test
	public void testReiknaRodSmallRowPlayer1() {
		int[] diceArray = new int[]{1,2,3,4,6};
		yhatzeeGame.nuverandi = 1;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaRod(9, mockDice);
		assertThat(30, is(points));
		assertThat(30, is(yhatzeeGame.stigin1[9]));
	}
	
	@Test
	public void testReiknaRodSmallRowPlayer2() {
		int[] diceArray = new int[]{1,5,3,4,6};
		yhatzeeGame.nuverandi = 2;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaRod(9, mockDice);
		assertThat(30, is(points));
		assertThat(30, is(yhatzeeGame.stigin2[9]));
	}
	
	@Test
	public void testReiknaRodBigRowPlayer2() {
		int[] diceArray = new int[]{5,6,3,4,2};
		yhatzeeGame.nuverandi = 2;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaRod(10, mockDice);
		assertThat(40, is(points));
		assertThat(40, is(yhatzeeGame.stigin2[10]));
	}
	
	@Test
	public void testReiknaRodBigRowPlayer1() {
		int[] diceArray = new int[]{5,1,3,4,2};
		yhatzeeGame.nuverandi = 1;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaRod(10, mockDice);
		assertThat(40, is(points));
		assertThat(40, is(yhatzeeGame.stigin1[10]));
	}
	
	@Test
	public void testReiknaYhatzeeNoYhatzeePlayer1() {
		int[] diceArray = new int[]{1,1,1,1,2};
		yhatzeeGame.nuverandi = 1;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaYahtzee(13, mockDice);
		assertThat(0, is(points));
		assertThat(0, is(yhatzeeGame.stigin1[13]));
	}
	
	@Test
	public void testReiknaYhatzeeIsYhatzeePlayer2() {
		int[] diceArray = new int[]{1,1,1,1,1};
		yhatzeeGame.nuverandi = 2;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaYahtzee(13, mockDice);
		assertThat(50, is(points));
		assertThat(50, is(yhatzeeGame.stigin2[13]));
	}
	
	@Test
	public void testReiknaYhatzeeIsYhatzeePlayer1() {
		int[] diceArray = new int[]{6,6,6,6,6};
		yhatzeeGame.nuverandi = 1;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaYahtzee(13, mockDice);
		assertThat(50, is(points));
		assertThat(50, is(yhatzeeGame.stigin1[13]));
	}
	
	@Test
	public void testReiknaFulltHusNotFullHousePlayer1() {
		int[] diceArray = new int[]{2,6,6,6,1};
		yhatzeeGame.nuverandi = 2;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaFulltHus(8, mockDice);
		assertThat(0, is(points));
		assertThat(0, is(yhatzeeGame.stigin2[8]));
	}
	
	@Test
	public void testReiknaFulltHusFullHousePlayer2() {
		int[] diceArray = new int[]{1,6,6,1,1};
		yhatzeeGame.nuverandi = 2;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaFulltHus(8, mockDice);
		assertThat(25, is(points));
		assertThat(25, is(yhatzeeGame.stigin2[8]));
	}
	
	@Test
	public void testReiknaFulltHusFullHousePlayer1() {
		int[] diceArray = new int[]{6,6,6,1,1};
		yhatzeeGame.nuverandi = 1;
		Mockito.when(mockDice.getTeningar()).thenReturn(diceArray);
		int points = yhatzeeGame.reiknaFulltHus(8, mockDice);
		assertThat(25, is(points));
		assertThat(25, is(yhatzeeGame.stigin1[8]));
	}
	
	@Test
	public void testBonusNoBonus() {
		int bonus = yhatzeeGame.bonus(62);
		assertThat(0, is(bonus));
	}
	
	@Test
	public void testBonusBonusPlayer1() {
		yhatzeeGame.nuverandi = 1;
		int bonus = yhatzeeGame.bonus(63);
		assertThat(35, is(bonus));
		assertThat(35, is(yhatzeeGame.stigin1[13]));
	}
	
	@Test
	public void testBonusBonusPlayer2() {
		yhatzeeGame.nuverandi = 2;
		int bonus = yhatzeeGame.bonus(63);
		assertThat(35, is(bonus));
		assertThat(35, is(yhatzeeGame.stigin2[13]));
	}
	
	@Test
	public void testBonusSummaPlayer1() {
		yhatzeeGame.nuverandi = 1;
		int bonusSum = yhatzeeGame.bonusSumma();
		assertThat(bonusSum, is(0));
		
	}
	
	@Test
	public void testBonusSummaPlayer2() {
		yhatzeeGame.nuverandi = 2;
		int expectedSum = 0;
		for(int i = 0; i < 6; i++) {
			yhatzeeGame.stigin2[i] = i;
			expectedSum += i;
		}
		int bonusSum = yhatzeeGame.bonusSumma();
		assertThat(bonusSum, is(expectedSum));
	}
	
}
