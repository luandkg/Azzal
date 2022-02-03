package Arquivos.Audio;

public class RefInt {
    private int mValor;

    public RefInt() {
        mValor = 0;
    }

    public int get() {
        return mValor;
    }

    public void mais(int v) {
        mValor += v;
    }
}
