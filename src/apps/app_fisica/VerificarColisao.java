package apps.app_fisica;

import libs.azzal.geometria.Retangulo;

public class VerificarColisao {

    private boolean mEncontroC4C1;
    private boolean mC4C1;
    private Retangulo mC1;
    private Retangulo mC4;
    private boolean mJaColidiu;

    public VerificarColisao(Retangulo eC1, Retangulo eC4) {
        mC1 = eC1;
        mC4 = eC4;

        mEncontroC4C1 = false;
        mC4C1 = false;
        mJaColidiu = false;
    }

    public boolean colidiu() {

        boolean ret = false;

        if (!mEncontroC4C1 && !mC4C1) {

            int diff = mC4.getX() - mC1.getX();
            if (diff <= 0) {
                mEncontroC4C1 = true;
                mC4C1 = true;
                ret = true;
                mJaColidiu = true;
            }

        } else if (mEncontroC4C1 && mC4C1) {
            mC4C1 = false;
        }

        return ret;
    }

    public boolean jaColidiu() {
        return mJaColidiu;
    }

    public void retirarColisao() {
        mJaColidiu = false;
    }

}
