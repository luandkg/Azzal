package libs.Luan;

public class Opcional<T> {

    private T mValor;
    private boolean mTem;

    public Opcional(T valor) {
        mTem = true;
        mValor = valor;
    }

    public Opcional() {
        mTem = false;
        mValor = null;
    }


    public T get() {
        return mValor;
    }

    public void set(T valor) {
        mTem = true;
        mValor = valor;
    }

    public boolean isVazio() {
        return !mTem;
    }

    public boolean temValor() {
        return mTem;
    }


    public void esvaziar() {
        mTem = false;
        mValor = null;
    }
}
