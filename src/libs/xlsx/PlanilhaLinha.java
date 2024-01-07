package libs.xlsx;

import libs.luan.fmt;

import java.util.ArrayList;

public class PlanilhaLinha {

    private ArrayList<String> mColunas;

    public PlanilhaLinha() {
        mColunas = new ArrayList<String>();
    }

    public void adicionar(String s) {
        mColunas.add(s);
    }

    public int maxColunas() {
        return mColunas.size();
    }

    public ArrayList<String> getColunas() {
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
