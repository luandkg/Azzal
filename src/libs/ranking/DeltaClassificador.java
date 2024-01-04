package libs.ranking;

import libs.matematica.Matematica;

import java.util.ArrayList;

public class DeltaClassificador {

    private int mQ1;
    private int mQ2;
    private int mQ3;
    private int mQ4;

    private int mC1;
    private int mC2;
    private int mC3;
    private int mC4;


    public DeltaClassificador(int eQ1, int eQ2, int eQ3, int eQ4) {
        mQ1 = eQ1;
        mQ2 = eQ2;
        mQ3 = eQ3;
        mQ4 = eQ4;

        mC1 = 0;
        mC2 = 0;
        mC3 = 0;
        mC4 = 0;

    }

    public void classificar(ArrayList<RankeadoInteiro> todos) {

        mC1 = 0;
        mC2 = 0;
        mC3 = 0;
        mC4 = 0;

        for (RankeadoInteiro a : todos) {

            int atividades = a.getRanking();

            if (atividades >= mQ2) {
                if (atividades >= mQ1) {
                    mC1 += 1;
                } else {
                    mC2 += 1;
                }
            } else {
                if (atividades == mQ4) {
                    mC4 += 1;
                } else {
                    mC3 += 1;
                }
            }

        }

    }

    public int getC1() {
        return mC1;
    }

    public int getC2() {
        return mC2;
    }

    public int getC3() {
        return mC3;
    }

    public int getC4() {
        return mC4;
    }

    public int getC12() {
        return mC1 + mC2;
    }

    public int getC34() {
        return mC3 + mC4;
    }

    public double getP1() {
        return nivelar(mC1, (mC1 + mC2 + mC3 + mC4));
    }

    public double getP2() {
        return nivelar(mC2, (mC1 + mC2 + mC3 + mC4));
    }

    public double getP3() {
        return nivelar(mC3, (mC1 + mC2 + mC3 + mC4));
    }

    public double getP4() {
        return nivelar(mC4, (mC1 + mC2 + mC3 + mC4));
    }

    public double getP12() {
        return nivelar(mC1 + mC2, (mC1 + mC2 + mC3 + mC4));
    }

    public double getP34() {
        return nivelar(mC3 + mC4, (mC1 + mC2 + mC3 + mC4));
    }

    public double nivelar(int acc, int todos) {
        if (todos == 0) {
            return 0;
        } else {
            return Matematica.getPorcentagem(acc, todos);
        }
    }
}
