package apps.app_attuz.Legendas;

import java.util.ArrayList;

public class Legenda {

    private ArrayList<String> mValores;

    public Legenda(int eQuantidade) {
        mValores = new ArrayList<String>();

        for (int v = 0; v < eQuantidade; v++) {
            mValores.add("");
        }
    }

    public String get(int v) {
        return mValores.get(v - 1);
    }

    public void set(int v, String eValor) {
        mValores.set(v - 1, eValor);
    }
}
