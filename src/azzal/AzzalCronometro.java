package azzal;

public class AzzalCronometro {

    private double mTempo_inicio;
    private double mEsperar;

    private boolean mEsperado;
    private double mPassado;

    public AzzalCronometro(long eInit, long eEsperar) {

        mTempo_inicio = eInit;
        mEsperar = eEsperar;
        mEsperado = false;
        mPassado=eInit;

    }

    public void esperar(double mais) {

        mEsperado = false;
        mPassado+=mais;

        double agora = mPassado;

        if ((agora - mTempo_inicio) >= mEsperar) {
            mTempo_inicio = agora;
            mEsperado = true;
        }

    }


    public boolean foiEsperado() {
        return mEsperado;
    }

    public void zerar(long eAgora) {
        mTempo_inicio = eAgora;
        mEsperado = false;
    }
}

