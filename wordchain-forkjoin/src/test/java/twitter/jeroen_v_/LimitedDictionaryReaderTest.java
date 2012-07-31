package twitter.jeroen_v_;

import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: jeroen
 * Date: 7/31/12
 * Time: 9:05 PM
 */
public class LimitedDictionaryReaderTest {
    @Test
    public void testReadNextWordFromDictionary() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cog", "dog"));
        LimitedDictionaryReader limitedDictionaryReader = new LimitedDictionaryReader(dictionary);
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("cat");
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("cog");
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("dog");
    }

    @Test
    public void testReadNextWordFromDictionaryNoMore() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cog"));
        LimitedDictionaryReader limitedDictionaryReader = new LimitedDictionaryReader(dictionary);
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("cat");
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("cog");
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isNullOrEmpty();
    }

    @Test
    public void testHasMoreWords() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cog", "dog"));
        LimitedDictionaryReader limitedDictionaryReader = new LimitedDictionaryReader(dictionary);
        assertThat(limitedDictionaryReader.hasMoreWords()).isTrue();
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("cat");
        assertThat(limitedDictionaryReader.hasMoreWords()).isTrue();
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("cog");
        assertThat(limitedDictionaryReader.hasMoreWords()).isTrue();
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("dog");
        assertThat(limitedDictionaryReader.hasMoreWords()).isFalse();
    }

    @Test
    public void testGetPointer() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cog"));
        LimitedDictionaryReader limitedDictionaryReader = new LimitedDictionaryReader(dictionary);
        assertThat(limitedDictionaryReader.getPointer()).isEqualTo(0);
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("cat");
        assertThat(limitedDictionaryReader.getPointer()).isEqualTo(1);
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("cog");
        assertThat(limitedDictionaryReader.getPointer()).isEqualTo(2);
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isNullOrEmpty();
        assertThat(limitedDictionaryReader.getPointer()).isEqualTo(2);
    }

    @Test
    public void testSetPointer() throws Exception {
        Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cog", "dog"));
        LimitedDictionaryReader limitedDictionaryReader = new LimitedDictionaryReader(dictionary);
        limitedDictionaryReader.setPointer(2);
        assertThat(limitedDictionaryReader.getPointer()).isEqualTo(2);
        assertThat(limitedDictionaryReader.readNextWordFromDictionary()).isEqualTo("dog");
        assertThat(limitedDictionaryReader.getPointer()).isEqualTo(3);
    }
}
