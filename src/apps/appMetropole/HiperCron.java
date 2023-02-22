package apps.appMetropole;


public class HiperCron {

    private double mAcumulando;
    private long mEsperar;

    private boolean mEsperado;

    public HiperCron(long eEsperar) {

        mAcumulando = 0;
        mEsperar = eEsperar*100;
        mEsperado = false;
    }

    public void esperar(double dt) {

        mEsperado = false;
        mAcumulando += dt;

        if (mAcumulando >= mEsperar) {
            mAcumulando = mAcumulando - mEsperar;
            mEsperado = true;
        }

    }


    public boolean foiEsperado() {
        return mEsperado;
    }

    public void zerar() {
        mAcumulando = 0;
        mEsperado = false;
    }
}
