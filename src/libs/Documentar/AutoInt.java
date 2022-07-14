package libs.Documentar;

public class AutoInt {

    private int mValor;

    public AutoInt(int eValor) {
        mValor = eValor;
    }

    public int get() {
        return mValor;
    }

    public void set(int eValor) {
        mValor = eValor;
    }

    public void somar(int parcela) {
        mValor += parcela;
    }

    public void subtrair(int parcela) {
        mValor -= parcela;
    }

    public void zerar() {
        mValor = 0;
    }

    public int mais_get(int mais) {
        mValor += mais;
        return mValor;
    }

    public int re_init(int eValor) {
        mValor = eValor;
        return mValor;
    }
}
