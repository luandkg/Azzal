package libs.tronarko;

public  enum Modarkos {

    OZZ(0), AZZ(1);

    private int mValor;

    Modarkos(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public String toString() {
        String ret = "";

        if (mValor == 0) {
            ret = "OZZ";
        }
        if (mValor == 1) {
            ret = "AZZ";
        }

        return ret;
    }
}
