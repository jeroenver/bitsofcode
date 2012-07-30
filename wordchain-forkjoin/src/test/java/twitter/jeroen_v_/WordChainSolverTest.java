package twitter.jeroen_v_;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
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
        WordChainSolver wordChainSolver = new WordChainSolver("cat", "dog", new LimitedDictionaryReader());
        Set<String> wordChain = wordChainSolver.findShortestChain();
        Set<String> expectedChain = new HashSet<String>(Arrays.asList("cat", "cot", "cog", "dog"));
        assertThat(wordChain).isEqualTo(expectedChain);
    }
}
