package Azzal.Utils;

public class Cronometro {

    private long mTempo_inicio;
    private long mEsperar;

    private boolean mEsperado;

    public Cronometro(long eEsperar) {

        mTempo_inicio = System.nanoTime();
        mEsperar = eEsperar * 1000000;
        mEsperado = false;
    }

    public void esperar() {

        mEsperado = false;

        if ((System.nanoTime() - mTempo_inicio) >= mEsperar) {
            mTempo_inicio = System.nanoTime();
            mEsperado = true;
        }

    }

    public long diff() {
        return (System.nanoTime() - mTempo_inicio);
    }

    public boolean foiEsperado() {
        return mEsperado;
    }

    public void zerar() {
        mTempo_inicio = System.nanoTime();
        mEsperado = false;
    }
}
