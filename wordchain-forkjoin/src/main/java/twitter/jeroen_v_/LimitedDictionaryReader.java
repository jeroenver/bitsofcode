package twitter.jeroen_v_;

import java.util.LinkedList;
import java.util.Set;

/**
 * User: jeroen
 * Date: 7/20/12
 * Time: 9:35 PM
 */
public final class LimitedDictionaryReader implements DictionaryReader {
    private final LinkedList<String> dictionary = new LinkedList<String>();
    private int pointer = 0;

    public LimitedDictionaryReader(Set<String> dictionary) {
        this.dictionary.addAll(dictionary);
    }

    @Override
    public String readNextWordFromDictionary() {
        if (hasMoreWords()) {
            return dictionary.get(pointer++);
        } else {
            return null;
        }
    }

    @Override
    public boolean hasMoreWords() {
        return dictionary.size() > pointer;
    }

    @Override
    public int getPointer() {
        return pointer;
    }

    @Override
    public void setPointer(int pointer) {
        this.pointer = pointer;
    }
}
