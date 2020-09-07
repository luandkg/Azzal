package Azzal.Utils;

import java.util.ArrayList;

public class Paleta {

    private ArrayList<Par<String, Cor>> mCores;

    public Paleta() {

        mCores = new ArrayList<Par<String, Cor>>();

    }

    public boolean existeChave(String eChave) {
        boolean ret = false;

        for (Par<String, Cor> ePar : mCores) {

            if (ePar.getChave().contentEquals(eChave)) {
                ret = true;
                break;
            }


        }

        return ret;
    }

    public void criar(String eNome, Cor eCor) {
        if (!existeChave(eNome)) {

            mCores.add(new Par<String, Cor>(eNome, eCor));

        }else{

            throw new IllegalArgumentException("Ja existe uma cor com esse nome : "+ eNome);
        }

    }


    public Cor getCor(String eChave) {
        Cor eCor = new Cor();

        for (Par<String, Cor> ePar : mCores) {

            if (ePar.getChave().contentEquals(eChave)) {
                eCor= ePar.getValor();
                break;
            }


        }

        return eCor;
    }


}
