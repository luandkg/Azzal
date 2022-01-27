package Tronarko;

public  enum Epocas {

    CRIACAO(0), DEUSES(1), REINOS(2), IMPERIOS(3);

    private int mValor;

    Epocas(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public String toString() {
        String ret = "";

        if (mValor == 0) {
            ret = "CRIACAO";
        }
        if (mValor == 1) {
            ret = "DEUSES";
        }
        if (mValor == 2) {
            ret = "REINOS";
        }
        if (mValor == 3) {
            ret = "IMPERIOS";
        }

        return ret;
    }
}
