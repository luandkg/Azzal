package libs.azzal.utilitarios;

import libs.azzal.geometria.Ponto;

public class Luz {

    private Ponto mPonto;
    private Cor mCor;
    private int mTamanho;

    public Luz(Ponto ePonto, Cor eCor, int eTamanho) {

        mPonto = ePonto;
        mCor = eCor;
        mTamanho = eTamanho;


    }

    public Ponto getPonto() {
        return mPonto;
    }

    public int getX() {
        return mPonto.getX();
    }

    public int getY() {
        return mPonto.getY();
    }

    public int getX_Inicio() {
        return mPonto.getX() - (mTamanho / 2);
    }

    public int getX_Fim() {
        return mPonto.getX() + (mTamanho / 2);
    }

    public int getY_Inicio() {
        return mPonto.getY() - (mTamanho / 2);
    }

    public int getY_Fim() {
        return mPonto.getY() + (mTamanho / 2);
    }


    public Cor getCor() {
        return mCor;
    }

    public int getTamanho() {
        return mTamanho;
    }

}
