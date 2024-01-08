package libs.hashum;


import libs.luan.Strings;
import libs.luan.TabelaHash;

public class TabelaString<T> extends TabelaHash<String, T> {
    public TabelaString() {
        super(Strings.HASH(), Strings.IGUALDADE());
    }

}
