package libs.luan;

public class Ref<T> {

    private T mValor;

    public Ref() {
        mValor = null;
    }

    public Ref(T eValor) {
        mValor = eValor;
    }

    public T get() {
        return mValor;
    }

    public void set(T eValor) {
        mValor = eValor;
    }
}