package libs.tronarko.Harrempluz;

import java.util.ArrayList;

public class Harrem {

    private int mTronarko;
    private int mHiperarko;
    private int mMegarko;
    private int mSigno;

    private ArrayList<HarremItem> mItens;

    public Harrem(int eTronarko, int eHiperarko, int eMegarko, int eSigno) {

        mTronarko = eTronarko;
        mHiperarko = eHiperarko;
        mMegarko = eMegarko;
        mSigno = eSigno;

        mItens = new ArrayList<HarremItem>();
    }

    public int getTronarko() {
        return mTronarko;
    }

    public int getHiperarko() {
        return mHiperarko;
    }

    public int getMegarko() {
        return mMegarko;
    }

    public int getSigno() {
        return mSigno;
    }


    public int getMegarkoDoTronarko() {
        return ((mHiperarko - 1) * 5) + mMegarko;
    }

    public void criarItem(String eNome, String eValor) {
        mItens.add(new HarremItem(eNome, eValor));
    }

    public ArrayList<HarremItem> getItens() {
        return mItens;
    }

    public String getItem(String eNome) {

        String ret = "";

        for (HarremItem eItem : mItens) {
            if (eItem.getNome().contentEquals(eNome)) {
                ret = eItem.getValor();
                break;
            }

        }


        return ret;
    }


}

