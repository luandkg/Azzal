package libs.luan;

public class ItemCiclico<T> {

    private int mPosAnterior;
    private int mPosCorrente;
    private int mPosProximo;
    private T mObjeto;
    private T mObjetoProximo;
    private T mObjetoAnterior;

    public ItemCiclico(int pos_anterior, int pos_corrente, int pos_proximo, T objetoAnterior,T objeto,T objetoProximo) {

        mPosAnterior = pos_anterior;
        mPosCorrente = pos_corrente;
        mPosProximo = pos_proximo;

        mObjetoAnterior=objetoAnterior;
        mObjeto = objeto;
        mObjetoProximo=objetoProximo;

    }


    public int getPosAnterior() {
        return mPosAnterior;
    }

    public int getPosCorrente() {
        return mPosCorrente;
    }

    public int getPosProximo() {
        return mPosProximo;
    }

    public T getObjeto() {
        return mObjeto;
    }

    public T getObjetoProximo(){
        return mObjetoProximo;
    }
    public T getObjetoAnterior(){
        return mObjetoAnterior;
    }

}
