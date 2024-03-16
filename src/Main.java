

import java.io.IOException;

import corrector.ISpellCorrector;
import corrector.SpellCorrector;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String dictionaryFileName = ("./data/dictionary.txt");
		String inputWord = "aop";
		
		ISpellCorrector corrector = new SpellCorrector();
		
		corrector.useDictionary(dictionaryFileName);
		String suggestion = corrector.suggestSimilarWord(inputWord);
		if (suggestion == null) {
		    suggestion = "No similar word found";
		}
		
		System.out.println("Suggestion is: " + suggestion);
	}

}
