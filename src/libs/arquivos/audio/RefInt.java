package libs.arquivos.audio;

public class RefInt {
    private int mValor;

    public RefInt() {
        mValor = 0;
    }

    public RefInt(int eInicio) {
        mValor = eInicio;
    }

    public int get() {
        return mValor;
    }

    public void mais(int v) {
        mValor += v;
    }
}
