package libs.luan;

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

    public static <T> Opcional<T> OK(T eValor) {
        return new Opcional<T>(eValor);
    }

    public static <T> Opcional<T> CANCEL() {
        return new Opcional<T>(null);
    }


    public static<T> boolean IS_OK(Opcional<T> op){
        return op.isOK();
    }
    public static<T> boolean IS_VAZIO(Opcional<T> op){
        return op.isVazio();
    }


    public static<T> T SOME(Opcional<T> opcional){
        return opcional.get();
    }


}
