package libs.fuzzer;

import java.util.ArrayList;

public class Listagem {

    private ArrayList<ItemAcao> mItens;

    private int item_inicio;
    private int mMaximo;

    private int mx;
    private int my;

    private String mNome;

    public Listagem(int x, int y) {

        mNome = "";

        mx = x;
        my = y;

        mItens = new ArrayList<ItemAcao>();

        item_inicio = 0;
        mMaximo = 5;
    }

    public ArrayList<ItemAcao> getItens() {
        return mItens;
    }

    public String getNome() {
        return mNome;
    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public int getMaximo() {
        return mMaximo;
    }


    public void setMaximo(int eMaximo) {
        mMaximo = eMaximo;
    }

    public int getIndice() {
        return item_inicio;
    }

    public void setIndice(int i) {
        item_inicio = i;
    }

    public int getX() {
        return mx;
    }

    public int getY() {
        return my;
    }
}
