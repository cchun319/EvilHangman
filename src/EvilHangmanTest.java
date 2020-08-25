import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class EvilHangmanTest {

	@Test
	public void testgetNum_of_guess() {
		EvilHangman EH = new EvilHangman();
		EH.setNum_of_guess(5);
		assertEquals(5, EH.getNum_of_guess());
	}
	
	@Test
	public void testgetRunning_total() {
		EvilHangman EH = new EvilHangman();
		EH.setRunning_total(true);
		assertTrue(EH.isRunning_total());
	}
	
	@Test
	public void testgetLengthOfWord() {
		EvilHangman EH = new EvilHangman();
		EH.setLengthOfWord(5);
		assertEquals(5, EH.getLengthOfWord());
	}
	
	@Test
	public void testnumOfLetterInAWord() {
		EvilHangman EH = new EvilHangman();

		assertEquals(1, EH.numOfLetterInAWord("apple", 'e'));
		assertEquals(2, EH.numOfLetterInAWord("apple", 'p'));
		assertEquals(0, EH.numOfLetterInAWord("apple", 'k'));
	}

	@Test
	public void testvalidGuess() {
		EvilHangman EH = new EvilHangman();
		assertTrue(EH.validGuess("c"));
		assertFalse(EH.validGuess("cba"));
		assertFalse(EH.validGuess("12"));
		assertFalse(EH.validGuess("1"));
		assertFalse(EH.validGuess("0"));
	}

	@Test
	public void testvalidLen() {
		EvilHangman EH = new EvilHangman();
		assertFalse(EH.validLen(-1));
		assertFalse(EH.validLen(30));
		assertFalse(EH.validLen(0));
		assertTrue(EH.validLen(15));

	}


	@Test(expected = IllegalArgumentException.class)
	public void testvalidLen2() {

		EvilHangman EH = new EvilHangman();
		EH.validLen(26);
	}


	@Test
	public void testini_most() {
		EvilHangman EH = new EvilHangman();
		EH.setLengthOfWord(5);
		EH.ini_most();
		assertEquals("_____", EH.getMostfam());
	}


	@Test
	public void testisGameWon() {
		EvilHangman EH = new EvilHangman();
		EH.setLengthOfWord(5);
		EH.setMostfam("_ab__");
		assertFalse(EH.isGameWon());
		EH.setMostfam("_____");
		assertFalse(EH.isGameWon());
		EH.setMostfam("apple");
		assertTrue(EH.isGameWon());		
	}


	@Test
	public void testwordWithSameLength() {
		EvilHangman EH = new EvilHangman();
		EH.setLengthOfWord(5);
		assertTrue(EH.wordWithSameLength("apple"));
		assertFalse(EH.wordWithSameLength("text"));
	}

	@Test
	public void testpadDashWord() {
		EvilHangman EH = new EvilHangman();
		assertEquals("_pp__", EH.padDashWord("apple", 'p'));
	}

	@Test
	public void testupdatePattern() {
		EvilHangman EH = new EvilHangman();
		assertEquals("_ppl_", EH.updatePattern("_pp__", "apple", 'l'));

	}

	@Test
	public void testsamePatternWord() {
		EvilHangman EH = new EvilHangman();
		assertTrue(EH.samePatternWord("_ppl_", "apple", 'l'));
		assertFalse(EH.samePatternWord("_pp__", "apple", 'l'));
		assertFalse(EH.samePatternWord("_p___", "apple", 'p'));

	}


	@Test
	public void testfindPattern() {
		EvilHangman EH = new EvilHangman();
		ArrayList<String> tt = new ArrayList<String>();
		tt.add("ally");
		tt.add("beta");
		tt.add("cool");
		tt.add("deal");
		tt.add("else");
		tt.add("flew");
		tt.add("good");
		tt.add("hope");
		tt.add("ibex");
		tt.add("doog");
		EH.setMatchWords(tt);
		EH.setMostfam("____");
		EH.findPattern('e');
		assertEquals(5,EH.getPattern().size());
	}

	@Test
	public void testexistInPattern() {
		EvilHangman EH = new EvilHangman();
		ArrayList<String> tt = new ArrayList<String>();
		tt.add("ally");
		tt.add("beta");
		tt.add("cool");
		tt.add("deal");
		tt.add("else");
		tt.add("flew");
		tt.add("good");
		tt.add("hope");
		tt.add("ibex");
		tt.add("doog");
		EH.setMatchWords(tt);
		EH.setMostfam("____");
		EH.findPattern('e');
		assertTrue(EH.existInPattern("____"));
		assertFalse(EH.existInPattern("e___"));
	}


	@Test
	public void testsameNumPattern() {
		EvilHangman EH = new EvilHangman();
		ArrayList<String> tt = new ArrayList<String>();
		tt.add("ally");
		tt.add("beta");
		tt.add("cool");
		tt.add("deal");
		tt.add("else");
		tt.add("flew");
		tt.add("good");
		tt.add("hope");
		tt.add("ibex");
		tt.add("doog");
		EH.setMatchWords(tt);
		EH.setMostfam("____");
		EH.findPattern('e');
		assertEquals(4, EH.sameNumPattern("____", 'e'));
	}


	@Test
	public void testgetFamilySize() {
		EvilHangman EH = new EvilHangman();
		ArrayList<String> tt = new ArrayList<String>();
		tt.add("ally");
		tt.add("beta");
		tt.add("cool");
		tt.add("deal");
		tt.add("else");
		tt.add("flew");
		tt.add("good");
		tt.add("hope");
		tt.add("ibex");
		tt.add("doog");
		EH.setMatchWords(tt);
		EH.setMostfam("____");
		EH.findPattern('e');
		EH.getFamilySize('e');
		assertEquals(EH.getFamilySize().size(), EH.getPattern().size());
	}

	@Test
	public void testindexOfBiggestFamily() {
		EvilHangman EH = new EvilHangman();
		ArrayList<String> tt = new ArrayList<String>();
		tt.add("ally");
		tt.add("beta");
		tt.add("cool");
		tt.add("deal");
		tt.add("else");
		tt.add("flew");
		tt.add("good");
		tt.add("hope");
		tt.add("ibex");
		tt.add("doog");
		EH.setMatchWords(tt);
		EH.setMostfam("____");
		EH.findPattern('e');
		EH.getFamilySize('e');
		assertEquals(0, EH.indexOfBiggestFamily());
	}


	@Test
	public void testmatchWordListAdd() {
		EvilHangman EH = new EvilHangman();
		ArrayList<String> tt = new ArrayList<String>();
		tt.add("ally");
		tt.add("beta");
		tt.add("cool");
		tt.add("deal");
		tt.add("else");
		tt.add("flew");
		tt.add("good");
		tt.add("hope");
		tt.add("ibex");
		tt.add("doog");
		EH.setMatchWords(tt);
		EH.setMostfam("____");
		EH.findPattern('e');
		EH.getFamilySize('e');
		EH.matchWordListAdd("____", 'e');
		assertEquals(4, EH.getMatchWords().size());
	}

	@Test
	public void testupdateMatchList() {
		EvilHangman EH = new EvilHangman();
		ArrayList<String> tt = new ArrayList<String>();
		tt.add("ally");
		tt.add("beta");
		tt.add("cool");
		tt.add("deal");
		tt.add("else");
		tt.add("flew");
		tt.add("good");
		tt.add("hope");
		tt.add("ibex");
		tt.add("doog");
		EH.setMatchWords(tt);
		EH.setMostfam("____");
		EH.findPattern('e');
		EH.getFamilySize('e');
		EH.updateMatchList('e');
		assertEquals("____", EH.getMostfam());
	}

	@Test
	public void testaddWordWithSameLengthToList() {
		EvilHangman EH = new EvilHangman();
		String[] allWords = new String[11]; 
		allWords[0] = "ally";
		allWords[1] = "beta";
		allWords[2] = "cool";
		allWords[3] = "deal";
		allWords[4] = "else";
		allWords[5] = "flew";
		allWords[6] = "good";
		allWords[7] = "hope";
		allWords[8] = "ibex";
		allWords[9] = "ibexcde";
		allWords[10] = "doog";
		EH.setAllWords(allWords);
		EH.setLengthOfWord(4);
		EH.addWordWithSameLengthToList();
		assertEquals(10, EH.getMatchWords().size());
	}
	
	@Test
	public void testloadWordFromDict() {
		EvilHangman EH = new EvilHangman();
		assertEquals(127142, EH.gettotalNumWords());
	}

}
