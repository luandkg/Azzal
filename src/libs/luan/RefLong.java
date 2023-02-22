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


}
