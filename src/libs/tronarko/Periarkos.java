package libs.tronarko;

public  enum Periarkos {

    UD(1), AD(2), ED(3), OD(4);

    private int mValor;

    Periarkos(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public String toString() {
        String ret = "";

        if (mValor == 1) {
            ret = "UD";
        }
        if (mValor == 2) {
            ret = "AD";
        }
        if (mValor == 3) {
            ret = "ED";
        }
        if (mValor == 4) {
            ret = "OD";
        }

        return ret;
    }
}
