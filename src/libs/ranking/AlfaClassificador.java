package libs.ranking;

import libs.matematica.Matematica;

import java.util.ArrayList;

public class AlfaClassificador {

    private int mQ1;

    private int mC1;
    private int mC2;



    public AlfaClassificador(int eQ1) {
        mQ1 = eQ1;


        mC1 = 0;
        mC2 = 0;

    }

    public void classificar(ArrayList<RankeadoInteiro> todos) {

        mC1 = 0;
        mC2 = 0;


        for (RankeadoInteiro a : todos) {

            int atividades = a.getRanking();

                if (atividades >= mQ1) {
                    mC1 += 1;
                } else {
                    mC2 += 1;
                }


        }

    }

    public int getC1() {
        return mC1;
    }

    public int getC2() {
        return mC2;
    }



    public int getC12() {
        return mC1 + mC2;
    }



    public double getP1() {
        return nivelar(mC1, (mC1 + mC2));
    }

    public double getP2() {
        return nivelar(mC2, (mC1 + mC2 ));
    }



    public double getP12() {
        return nivelar(mC1 + mC2, (mC1 + mC2 ));
    }



    public double nivelar(int acc, int todos) {
        if (todos == 0) {
            return 0;
        } else {
            return Matematica.getPorcentagem(acc, todos);
        }
    }
}
