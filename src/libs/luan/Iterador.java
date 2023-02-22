package libs.luan;

public class Iterador<T> {

    private Lista<T> mLista;
    private Vetor<T> mVetor;

    private boolean mIniciado;
    private boolean mFinalizado;
    private int mIndice;
    private int mQuantidade;

    private int mTipo;

    public Iterador(Lista<T> eLista) {

        mLista = eLista;

        mIniciado = false;
        mFinalizado = false;
        mIndice = 0;
        mQuantidade = 0;

        mTipo = 0;

    }

    public Iterador(Vetor<T> eVetor) {

        mVetor = eVetor;

        mIniciado = false;
        mFinalizado = false;
        mIndice = 0;
        mQuantidade = 0;

        mTipo = 1;

    }

    public void iniciar() {

        mIniciado = true;
        mFinalizado = false;

        mIndice = 0;

        if (mTipo == 0) {
            mQuantidade = mLista.getQuantidade();
        } else if (mTipo == 1) {
            mQuantidade = mVetor.getCapacidade();
        }


    }

    public boolean continuar() {
        if (mIniciado) {

            if (mIndice < mQuantidade) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }
    }

    public void proximo() {

        if (mIniciado) {

            if (mFinalizado == false) {
                mIndice += 1;
            }

            if (mIndice >= mQuantidade) {
                mFinalizado = true;
            }
        }

    }

    public int getIndice() {

        if (mIniciado == false) {
            throw new IllegalArgumentException("Iterador nao iniciado !");
        }

        if (mFinalizado) {
            throw new IllegalArgumentException("Iterador finalizado !");
        }

        return mIndice;

    }

    public T getValor() {

        if (mIniciado == false) {
            throw new IllegalArgumentException("Iterador nao iniciado !");
        }

        if (mFinalizado) {
            throw new IllegalArgumentException("Iterador finalizado !");
        }

        if (mTipo == 0) {
            return mLista.getValor(mIndice);
        } else if (mTipo == 1) {
            return mVetor.get(mIndice);
        }

        return null;

    }

    public boolean iniciado() {
        return mIniciado;
    }

    public boolean finalizado() {
        return mFinalizado;
    }


    public boolean temAntes() {

        if (mIniciado == false) {
            throw new IllegalArgumentException("Iterador nao iniciado !");
        } else {

            if (mIndice > 0) {
                return true;
            } else {
                return false;
            }

        }

    }

    public boolean temDepois() {

        if (mIniciado == false) {
            throw new IllegalArgumentException("Iterador nao iniciado !");
        } else {


            if ((mIndice + 1) < mQuantidade) {
                return true;
            } else {
                return false;
            }


        }

    }

    public int getAnteriorIndice() {
        if (temAntes()) {

            return mIndice - 1;

        } else {

            throw new IllegalArgumentException("Iterador nao possui anterior !");

        }
    }

    public int getProximoIndice() {
        if (temDepois()) {

            return mIndice + 1;

        } else {

            throw new IllegalArgumentException("Iterador nao possui proximo !");

        }
    }


    public T getAnteriorValor() {
        if (temAntes()) {


            if (mTipo == 0) {
                return mLista.getValor(mIndice - 1);
            } else if (mTipo == 1) {
                return mVetor.get(mIndice - 1);
            }

        } else {

            throw new IllegalArgumentException("Iterador nao possui anterior !");

        }

        return null;

    }

    public T getProximoValor() {
        if (temDepois()) {


            if (mTipo == 0) {
                return mLista.getValor(mIndice + 1);
            } else if (mTipo == 1) {
                return mVetor.get(mIndice + 1);
            }

        } else {

            throw new IllegalArgumentException("Iterador nao possui proximo !");

        }

        return null;

    }

    public void sair() {
        mFinalizado = true;
        mIndice = mQuantidade;
    }


    public void paraCada(EmCada emCada) {

        for (iniciar(); continuar(); proximo()) {

            emCada.fazer(getValor());

        }

    }
}
