package twitter.jeroen_v_;

/**
 * User: jeroen
 * Date: 7/30/12
 * Time: 10:55 PM
 */
public interface DictionaryReaderFactory {
    DictionaryReader createDictionaryReader();

    DictionaryReader cloneDictionaryReader(DictionaryReader dictionaryReader);
}
