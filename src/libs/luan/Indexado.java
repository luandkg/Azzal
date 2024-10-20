package libs.luan;

public class Indexado<T> {

    private int mIndex;
    private T mValor;

    private int mPosicional;

    public Indexado(int index,int posicional, T valor) {
        mIndex = index;
        mValor = valor;
        mPosicional=posicional;
    }

    public T get() {
        return mValor;
    }

    public int index() {
        return mIndex;
    }

    public void setIndex(int eIndex) {
        mIndex = eIndex;
    }

    public void set(T eValor) {
        mValor = eValor;
    }

    public int getPosicional(){return mPosicional;}

    public boolean isPrimeiro(){return mPosicional == Indexamento.POSICIONALMENTE_PRIMEIRO;}
    public boolean isPrimeiroEUltimo(){return mPosicional == Indexamento.POSICIONALMENTE_PRIMEIRO_E_ULTIMO;}

    public boolean isUltimo(){return mPosicional == Indexamento.POSICIONALMENTE_ULTIMO;}

}
