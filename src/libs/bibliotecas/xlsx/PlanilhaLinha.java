package libs.bibliotecas.xlsx;

import libs.luan.Lista;
import libs.luan.fmt;

public class PlanilhaLinha {

    private Lista<String> mColunas;

    public PlanilhaLinha() {
        mColunas = new Lista<String>();
    }

    public void adicionar(String s) {
        mColunas.adicionar(s);
    }

    public int maxColunas() {
        return mColunas.getQuantidade();
    }

    public Lista<String> getColunas() {
        return mColunas;
    }


    public String getString() {
        String s = "";

        for (String col : mColunas) {
            s += fmt.espacar_depois(col, 20);
        }

        return s;
    }
}
