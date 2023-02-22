package libs.luan;

public class RefBool {

    private boolean mValor;

    public RefBool() {
        mValor = true;
    }

    public RefBool(boolean eValor) {
        mValor = eValor;
    }

    public boolean get() {
        return mValor;
    }

    public void set(boolean eValor) {
        mValor = eValor;
    }

}
