package libs.azzal.utilitarios;

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

        long agora = System.nanoTime();

        if ((agora- mTempo_inicio) >= mEsperar) {
            mTempo_inicio = agora;
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
