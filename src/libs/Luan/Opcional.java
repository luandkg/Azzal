package libs.Luan;

public class Opcional<T> {

    private T mValor;
    private boolean mTem;

    public Opcional(T valor) {

        if (valor == null) {
            mTem = false;
            mValor = null;
        } else {
            mTem = true;
            mValor = valor;
        }

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

    public boolean isOK() {
        return mTem;
    }

    public void esvaziar() {
        mTem = false;
        mValor = null;
    }
}
