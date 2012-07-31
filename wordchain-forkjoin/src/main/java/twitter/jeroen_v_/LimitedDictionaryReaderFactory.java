package twitter.jeroen_v_;

import java.util.Set;

/**
 * User: jeroen
 * Date: 7/30/12
 * Time: 10:56 PM
 */
public final class LimitedDictionaryReaderFactory implements DictionaryReaderFactory {
    private final Set<String> dictionary;

    public LimitedDictionaryReaderFactory(Set<String> dictionary) {
        this.dictionary = dictionary;
    }

    @Override
    public DictionaryReader createDictionaryReader() {
        return new LimitedDictionaryReader(dictionary);
    }

    @Override
    public DictionaryReader cloneDictionaryReader(DictionaryReader dictionaryReader) {
        LimitedDictionaryReader limitedDictionaryReader = new LimitedDictionaryReader(dictionary);
        limitedDictionaryReader.setPointer(dictionaryReader.getPointer());
        return limitedDictionaryReader;
    }
}
