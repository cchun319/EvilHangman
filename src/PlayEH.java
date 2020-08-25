import java.util.ArrayList;
import java.util.Scanner;

public class PlayEH {

	public static void main(String[] args) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		play();
		
}
	public static void play()
	{
		EvilHangman EH = new EvilHangman();
		Scanner sc = new Scanner(System.in);
		System.out.println("Length of word: ");
		int len = sc.nextInt();
		while(EH.validLen(len) != true)
		{ 	System.out.println("invalid length.");
			len = sc.nextInt();}
		System.out.println("Running? [true/false]");
		boolean rt = sc.nextBoolean();
		System.out.println("# of guesses? ");
		int num = sc.nextInt();
		EH.setNum_of_guess(num);
		EH.setRunning_total(rt);
		EH.setLengthOfWord(len);
		EH.addWordWithSameLengthToList(); // initialization
		
		EH.ini_most();
		char guess;
		String guess_s = "";

		while(EH.getNum_of_guess() > 0) //keep guessing
		{
			System.out.println(EH.getMostfam());
			if (rt)
			{
				System.out.println("running total: " + EH.getMatchWords().size());
			}
			System.out.println("Remaining: " + EH.getNum_of_guess() + "\nguess: ");
			guess_s = sc.next();
			while(EH.validGuess(guess_s) != true)
			{
				System.out.println("invalid guess.");
				guess_s = sc.next();
			}
			guess = guess_s.charAt(0);
			EH.findPattern(guess);
			EH.getFamilySize(guess);
			EH.updateMatchList(guess);
			EH.setNum_of_guess(EH.getNum_of_guess() - 1);
			if(EH.isGameWon() == true)
			{
				System.out.println("WIN!");
				System.out.println(EH.getMostfam());
				return;
			}
		} 
		System.out.println(EH.getMostfam());
		System.out.println(EH.getMatchWords().get(0));
		System.out.println("LOSE!");
	}
}