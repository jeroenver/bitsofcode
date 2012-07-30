package twitter.jeroen_v_;

import com.sun.deploy.util.OrderedHashSet;

import java.util.*;

/**
 * User: jeroen
 * Date: 7/20/12
 * Time: 9:35 PM
 */
public final class LimitedDictionaryReader implements DictionaryReader {
    private static Set<String> dictionary = new LinkedHashSet<String>(Arrays.asList("cat", "cot", "cog", "dog"));
    private Iterator dictionaryIterator = dictionary.iterator();

    @Override
    public String readNextWordFromDictionary() {
        return (String) dictionaryIterator.next();
    }

    @Override
    public boolean hasMoreWords() {
        return dictionaryIterator.hasNext();
    }
}
