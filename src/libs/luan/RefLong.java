package libs.luan;

public class RefLong {

    private long mValor;

    public RefLong() {
        mValor = 0;
    }

    public RefLong(long eValor) {
        mValor = eValor;
    }

    public long get() {
        return mValor;
    }

    public void set(long eValor) {
        mValor = eValor;
    }

    public void subtrair(int v) {
        mValor -= v;
    }

    public void somar(int v) {
        mValor += v;
    }


    public static RefLong TO(long valor) {
        return new RefLong(valor);
    }

    public static RefLong SUBTRAIR(RefLong a, RefLong b) {
        return new RefLong(a.get() - b.get());
    }


}
