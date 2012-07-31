package twitter.jeroen_v_;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Class is not thread-safe, new instance for each thread!
 *
 * User: jeroen
 * Date: 7/20/12
 * Time: 9:17 PM
 */
public final class WordChainSolver extends RecursiveTask<Set<String>> {
    private final Set<String> wordChain;
    private final String firstWord, lastWord;
    private final DictionaryReader dictionaryReader;
    private final DictionaryReaderFactory dictionaryReaderFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(WordChainSolver.class);

    private WordChainSolver(String firstWord, String lastWord, Set<String> wordChain,
                            DictionaryReaderFactory dictionaryReaderFactory, DictionaryReader dictionaryReader) {
        this.firstWord = firstWord;
        this.lastWord = lastWord;
        this.dictionaryReaderFactory = dictionaryReaderFactory;
        this.dictionaryReader = dictionaryReader;
        this.wordChain = wordChain;
    }

    public static Set<String> findShortestChain(String firstWord, String lastWord, DictionaryReaderFactory dictionaryReaderFactory) {
        Set<String> wordChain = new LinkedHashSet<String>();
        wordChain.add(firstWord);
        WordChainSolver wordChainSolver = new WordChainSolver(firstWord, lastWord, wordChain,
                dictionaryReaderFactory, dictionaryReaderFactory.createDictionaryReader());
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(wordChainSolver);
    }

    private String findNextWordInChain() {
        while (dictionaryReader.hasMoreWords()) {
            String nextInDictionary = dictionaryReader.readNextWordFromDictionary();
            LOGGER.debug("next word in dictionary: " + nextInDictionary);
            if (onlyOneLetterDifferenceBetween(firstWord, nextInDictionary) && !wordChain.contains(nextInDictionary)) {
                return nextInDictionary;
            }
        }
        return null;
    }

    @Override
    protected Set<String> compute() {
        LOGGER.debug("current chain " + wordChain);
        if (onlyOneLetterDifferenceBetween(firstWord, lastWord)) {
            wordChain.add(lastWord);
            LOGGER.debug("possible solution found : " + wordChain);
            return wordChain;
        }
        String nextWord = findNextWordInChain();
        if (nextWord == null) {
            return new LinkedHashSet<String>();
        }
        //continue with clone of dictionary reader, do not add word
        Set<String> wordChainClone = new LinkedHashSet<String>();
        wordChainClone.addAll(wordChain);
        WordChainSolver solverFork2 = new WordChainSolver(firstWord, lastWord, wordChainClone, dictionaryReaderFactory,
                dictionaryReaderFactory.cloneDictionaryReader(dictionaryReader));
        //continue with current dictionary reader, do add word
        wordChain.add(nextWord);
        WordChainSolver solverFork1 = new WordChainSolver(nextWord, lastWord, wordChain, dictionaryReaderFactory, dictionaryReader);
        //fork - join
        solverFork1.fork();
        Set<String> chain2 = solverFork2.compute();
        Set<String> chain1 = solverFork1.join();
        //return smallest chain but cannot be empty
        if (correctedSize(chain1.size()) < correctedSize(chain2.size())) {
            LOGGER.debug("winner found, chain1: " + chain1 + "; and not chain2: " + chain2);
            return chain1;
        } else {
            LOGGER.debug("winner found, chain2: " + chain2 + "; and not chain1: " + chain1);
            return chain2;
        }
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

    private int correctedSize(int size){
        if(size == 0) return Integer.MAX_VALUE;
        else return size;
    }
}
