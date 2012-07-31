package twitter.jeroen_v_;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: jeroen
 * Date: 7/20/12
 * Time: 9:18 PM
 */
public class WordChainSolverTest {

    @Test
    public void testSolveSimpleChain() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cot", "can", "cog", "dog"));
        DictionaryReaderFactory dictionaryReaderFactory = new LimitedDictionaryReaderFactory(dictionary);
        Set<String> wordChain = WordChainSolver.findShortestChain("cat", "dog", dictionaryReaderFactory);
        Set<String> expectedChain = new LinkedHashSet<String>(Arrays.asList("cat", "cot", "cog", "dog"));
        assertThat(wordChain).isEqualTo(expectedChain);
    }

    @Test
    public void testNoChainFound() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cog", "dog"));
        DictionaryReaderFactory dictionaryReaderFactory = new LimitedDictionaryReaderFactory(dictionary);
        Set<String> wordChain = WordChainSolver.findShortestChain("cat", "dog", dictionaryReaderFactory);
        assertThat(wordChain).isEmpty();
    }

    @Test
    public void testOnly2words() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cot", "can", "cog", "dog"));
        DictionaryReaderFactory dictionaryReaderFactory = new LimitedDictionaryReaderFactory(dictionary);
        Set<String> wordChain = WordChainSolver.findShortestChain("cat", "cot", dictionaryReaderFactory);
        Set<String> expectedChain = new LinkedHashSet<String>(Arrays.asList("cat", "cot"));
        assertThat(wordChain).isEqualTo(expectedChain);
    }

    @Test
    public void testAlternatePath() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cot", "can", "cog", "pan", "dog"));
        DictionaryReaderFactory dictionaryReaderFactory = new LimitedDictionaryReaderFactory(dictionary);
        Set<String> wordChain = WordChainSolver.findShortestChain("cat", "pan", dictionaryReaderFactory);
        Set<String> expectedChain = new LinkedHashSet<String>(Arrays.asList("cat", "can", "pan"));
        assertThat(wordChain).isEqualTo(expectedChain);
    }
}
