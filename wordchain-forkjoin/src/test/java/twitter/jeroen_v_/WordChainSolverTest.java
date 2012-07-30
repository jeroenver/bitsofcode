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
        WordChainSolver wordChainSolver = new WordChainSolver("cat", "dog", new LimitedDictionaryReader(dictionary));
        Set<String> wordChain = wordChainSolver.findShortestChain();
        Set<String> expectedChain = new LinkedHashSet<String>(Arrays.asList("cat", "cot", "cog", "dog"));
        assertThat(wordChain).isEqualTo(expectedChain);
    }

    @Test
    public void testNoChainFound() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cog", "dog"));
        WordChainSolver wordChainSolver = new WordChainSolver("cat", "dog", new LimitedDictionaryReader(dictionary));
        Set<String> wordChain = wordChainSolver.findShortestChain();
        assertThat(wordChain).isEmpty();
    }

    @Test
    public void testOnly2words() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cot", "can", "cog", "dog"));
        WordChainSolver wordChainSolver = new WordChainSolver("cat", "cot", new LimitedDictionaryReader(dictionary));
        Set<String> wordChain = wordChainSolver.findShortestChain();
        Set<String> expectedChain = new LinkedHashSet<String>(Arrays.asList("cat", "cot"));
        assertThat(wordChain).isEqualTo(expectedChain);
    }

    @Test
    public void testAlternatePath() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cot", "can", "cog", "pan", "dog"));
        WordChainSolver wordChainSolver = new WordChainSolver("cat", "pan", new LimitedDictionaryReader(dictionary));
        Set<String> wordChain = wordChainSolver.findShortestChain();
        Set<String> expectedChain = new LinkedHashSet<String>(Arrays.asList("cat", "can", "pan"));
        assertThat(wordChain).isEqualTo(expectedChain);
    }
}
