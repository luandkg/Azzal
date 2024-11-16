package libs.luan;

public class DeslizadorEstrutural<T> {


    private boolean mTemProximo = true;
    private T mCorrente = null;

    public DeslizadorEstrutural(T eInicial) {

        mTemProximo = true;
        mCorrente = eInicial;

    }

    public T get() {
        return mCorrente;
    }

    public void setProximo(T eProximo) {
        mTemProximo = true;
        mCorrente = eProximo;
    }

    public boolean temProximo() {
        return mTemProximo;
    }

    public void finalizar() {
        mTemProximo = false;
    }
}
