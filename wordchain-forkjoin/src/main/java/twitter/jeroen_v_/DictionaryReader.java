package twitter.jeroen_v_;

/**
 * User: jeroen
 * Date: 7/20/12
 * Time: 9:37 PM
 */
public interface DictionaryReader {
    String readNextWordFromDictionary();

    boolean hasMoreWords();

    int getPointer();

    void setPointer(int pointer);
}
