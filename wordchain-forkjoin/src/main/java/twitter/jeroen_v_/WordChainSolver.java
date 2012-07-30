package twitter.jeroen_v_;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * User: jeroen
 * Date: 7/20/12
 * Time: 9:17 PM
 */
public final class WordChainSolver {
    private final Set<String> wordChain = new LinkedHashSet<String>();
    private final String firstWord, lastWord;
    private DictionaryReader dictionaryReader;
    private static final Logger LOGGER = LoggerFactory.getLogger(WordChainSolver.class);

    public WordChainSolver(String firstWord, String lastWord, DictionaryReader dictionaryReader) {
        this.firstWord = firstWord;
        this.lastWord = lastWord;
        this.dictionaryReader = dictionaryReader;
        wordChain.add(firstWord);
    }

    public Set<String> findShortestChain() {
        return findRestOfChain(firstWord);
    }

    private Set<String> findRestOfChain(String currentWord) {
        LOGGER.debug("current chain " + wordChain);
        if (onlyOneLetterDifferenceBetween(currentWord, lastWord)) {
            wordChain.add(lastWord);
            return wordChain;
        }
        if (!dictionaryReader.hasMoreWords()) {
            //no chain found
            return new HashSet<String>();
        }
        String nextInDictionary = dictionaryReader.readNextWordFromDictionary();
        LOGGER.debug("next word in dictionary: " + nextInDictionary);
        if (onlyOneLetterDifferenceBetween(currentWord, nextInDictionary)) {
            wordChain.add(nextInDictionary);
            return findRestOfChain(nextInDictionary);
        }
        return findRestOfChain(currentWord);
    }

    private boolean onlyOneLetterDifferenceBetween(String firstWord, String secondWord) {
        if (firstWord.length() != secondWord.length()) {
            return false;
        }
        char[] firstWordChars = firstWord.toCharArray();
        char[] secondWordChars = secondWord.toCharArray();
        int diffCounter = 0;
        for (int i = 0; i < firstWordChars.length; i++) {
            if (firstWordChars[i] != secondWordChars[i]) {
                diffCounter++;
            }
            if (diffCounter > 1) {
                return false;
            }
        }
        if (diffCounter == 1) {
            return true;
        } else {
            return false;
        }
    }
}
