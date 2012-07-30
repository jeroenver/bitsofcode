package twitter.jeroen_v_;

import java.util.Iterator;
import java.util.Set;

/**
 * User: jeroen
 * Date: 7/20/12
 * Time: 9:35 PM
 */
public final class LimitedDictionaryReader implements DictionaryReader {
    private Iterator dictionaryIterator;

    public LimitedDictionaryReader(Set<String> dictionary) {
        dictionaryIterator = dictionary.iterator();
    }

    @Override
    public String readNextWordFromDictionary() {
        return (String) dictionaryIterator.next();
    }

    @Override
    public boolean hasMoreWords() {
        return dictionaryIterator.hasNext();
    }
}
