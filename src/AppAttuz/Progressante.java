package AppAttuz;

public class Progressante {

    private int mCorrente;
    private int mMaximo;
    private int mTaxa;
    private int i = 0;

    public Progressante(int maximo) {

        mCorrente = 0;
        mMaximo = maximo;
        mTaxa = maximo / 100;
        i = 0;

    }

    public void emitir(int eValor) {

        if (eValor >= mCorrente) {
            System.out.println("PROGRESSO :: " + i);
            mCorrente += mTaxa;
            i += 1;
        }

    }

    public void emitir(int eValor, String eFrase) {

        if (eValor >= mCorrente) {
            System.out.println("\t - PROGRESSO :: " + i + (eFrase));
            mCorrente += mTaxa;
            i += 1;
        }

    }
}
