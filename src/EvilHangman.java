import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author chunchang
 *
 */
public class EvilHangman implements EvilHangmanI {

	private int num_of_guess;
	private boolean running_total;
	private int lengthOfWord;
	private String mostfam;
	private int totalNumWords;
	private ArrayList<String> matchWords = new ArrayList<String>();
	private ArrayList<String> matchWords2 = new ArrayList<String>();
	private ArrayList<String> pattern = new ArrayList<String>();
	private ArrayList<Integer> familySize = new ArrayList<Integer>();
	private String[] allWords;
	
	public EvilHangman() {
		super();
		this.totalNumWords = loadWordFromDict();
		this.mostfam = "";
	}


	@Override
	public int loadWordFromDict() {
		// TODO Auto-generated method stub
		Words ws = new Words();
		this.totalNumWords = ws.getWords().length;
		allWords = new String[this.totalNumWords];
		allWords = ws.getWords();
		return allWords.length;
	}


	@Override
	public boolean wordWithSameLength(String S) {
		// TODO Auto-generated method stub
		if(S.length() == this.lengthOfWord)
		{ return true;}
		return false;
	}


	@Override
	public void addWordWithSameLengthToList() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.allWords.length; i++)
		{
			if(wordWithSameLength(this.allWords[i]) == true)
			{ matchWords.add(this.allWords[i]);}
		}

	}


	@Override
	public void findPattern(char e) {
		// TODO Auto-generated method stub
		// turn words into dashpattern
		String pat = "";
		for(int i = 0; i < this.matchWords.size(); i++)
		{
			pat = updatePattern(mostfam, this.matchWords.get(i), e); // use the old pattern to do new pattern
			if (existInPattern(pat) != true)
			{
				this.pattern.add(pat);
			}
		}

	}


	@Override
	public String padDashWord(String s, char e) {
		// TODO Auto-generated method stub
		String pa = "";
//		for(int i = 0; i < s.length(); i++)
//		{
//			if(s.charAt(i) != e)
//			{	pa += '_';}
//			else
//			{	pa += s.charAt(i);}
//		}
		// recursive 
		pa = padDashWordRe(s, 0 , e);
		return pa;
	}
	
	private String padDashWordRe(String s, int ind, char e)
	{
		if (ind == s.length() - 1)
		{
			if (s.charAt(ind) == e)
			{	return String.valueOf(s.charAt(ind));}
			else
			{ return "_";}
		}
		else
		{
			if (s.charAt(ind) == e)
			{	return String.valueOf(s.charAt(ind)) + padDashWordRe(s, ind + 1, e);}
			else
			{ return "_" + padDashWordRe(s, ind + 1, e);}
		}
	}


	@Override
	public boolean existInPattern(String s) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.pattern.size(); i++)
		{
			if(this.pattern.get(i).compareTo(s) == 0)
			{ return true;}
		}
		return false;
	}


	@Override
	public void updateMatchList(char e) {
		// TODO Auto-generated method stub
		// get largest family
		// get larget number
		int indOfLargestFamily = indexOfBiggestFamily();
		int numOfLargestFamily = this.familySize.get(indOfLargestFamily);
		String patternHasLargestFamily = this.pattern.get(indOfLargestFamily);
		this.mostfam = patternHasLargestFamily;
		// add words matching largest family to matchwords
		matchWordListAdd(patternHasLargestFamily, e);
		this.familySize.clear();
		this.pattern.clear();
	}


	@Override
	public void getFamilySize(char e) {
		// TODO Auto-generated method stub
		int size = 0;
		for(int i = 0; i < this.pattern.size(); i++)
		{
			//traverse list 
			size = sameNumPattern(this.pattern.get(i), e);
			this.familySize.add(size);
		}
	}


	@Override
	public int sameNumPattern(String s, char e) {
		// TODO Auto-generated method stub
		int cal = 0;
		for (int i = 0; i < this.matchWords.size(); i++)
		{
			if(samePatternWord(s, this.matchWords.get(i), e) == true)
			{	cal++;}
		}
		return cal;
	}


	@Override
	public boolean samePatternWord(String s1, String s2, char e) {
		// TODO Auto-generated method stub
		//s1 : pattern
		//s2 : original word
		for(int i = 0; i < s1.length(); i++)
		{
			if((s1.charAt(i) != '_' && s1.charAt(i) != s2.charAt(i)) || (s1.charAt(i) == '_' && s2.charAt(i) == e)) // ----, AbcD: A
			{ return false;}
		}
		return true;
	}


	@Override
	public int indexOfBiggestFamily() {
		int largest_ind = 0;
		int num = 0;
		for(int i = 0; i < this.familySize.size(); i++)
		{
			if(this.familySize.get(i) >= num)
			{
				num = this.familySize.get(i);
				largest_ind = i;
			}
		}
		return largest_ind;
	}


	@Override
	public void matchWordListAdd(String s, char e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.matchWords.size(); i++)
		{
			if (samePatternWord(s, matchWords.get(i), e) == true)
			{
				this.matchWords2.add(matchWords.get(i));
			}
		}

		this.matchWords.clear();
		for (int i = 0; i < this.matchWords2.size(); i++)
		{
			this.matchWords.add(this.matchWords2.get(i));
		}
		this.matchWords2.clear();
	}


	@Override
	public String updatePattern(String oldPa, String word, char e) {
		// TODO Auto-generated method stub
		String new_pa = "";

		for (int i = 0; i < oldPa.length(); i++)
		{
			if(word.charAt(i) != e && oldPa.charAt(i) == '_')
			{	new_pa += '_';}
			else
			{	new_pa += word.charAt(i);}
		}
		return new_pa;
	}


	@Override
	public boolean isGameWon() {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.mostfam.length(); i++)
		{
			if(this.mostfam.charAt(i) == '_')
			{return false;}
		}
		return true;
	}


	@Override
	public void ini_most() {
		// TODO Auto-generated method stub
		for (int i = 0; i < this.lengthOfWord; i++)
		{this.mostfam += "_";}
	}


	@Override
	public boolean validLen(int len) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if (len <= 0 || len > 29)
		{
			return false;
		}
		if (len == 26 || len == 27)
		{
			throw new IllegalArgumentException("no word has 26 or 27 length");
		}
		return true;
	}


	@Override
	public boolean validGuess(String in) {
		// TODO Auto-generated method stub
		if (in.length() != 1)
		{
			return false;
		}
		if (!Character.isLetter(in.charAt(0)))
		{
			return false;
		}
		return true;
	}


	@Override
	public int numOfLetterInAWord(String word, char e) {
		// TODO Auto-generated method stub
		int num = helper(word, 0, e);
		return num;
	}
	
	private int helper(String word, int ind, char e)
	{
		if(ind == word.length() - 1)
		{
			if (word.charAt(ind) == e){return 1;}
			else{return 0;}
		}
		else
		{
			if (word.charAt(ind) == e)
			{return 1 + helper(word, ind + 1, e);}
			else
			{return 0 + helper(word, ind + 1, e);}
		}
	}


	public int getNum_of_guess() {
		return num_of_guess;
	}


	public void setNum_of_guess(int num_of_guess) {
		this.num_of_guess = num_of_guess;
	}


	public boolean isRunning_total() {
		return running_total;
	}


	public void setRunning_total(boolean running_total) {
		this.running_total = running_total;
	}


	public int getLengthOfWord() {
		return lengthOfWord;
	}


	public void setLengthOfWord(int lengthOfWord) {
		this.lengthOfWord = lengthOfWord;
	}


	public String getMostfam() {
		return mostfam;
	}


	public void setMostfam(String mostfam) {
		this.mostfam = mostfam;
	}



	public ArrayList<String> getMatchWords() {
		return matchWords;
	}


	public void setMatchWords(ArrayList<String> matchWords) {
		this.matchWords = matchWords;
	}



	public ArrayList<String> getPattern() {
		return pattern;
	}


	public ArrayList<Integer> getFamilySize() {
		return familySize;
	}


	public void setAllWords(String[] allWords) {
		this.allWords = allWords;
	}
	
	public int gettotalNumWords()
	{
		return this.totalNumWords;
	}


	

	
	
}
