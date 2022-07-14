package libs.Tronarko;

public  enum Hizarkos {

    HITTARIUM(1), DEGGOVIUM(2), NUZTIUM(3), HARBARIUM(4);

    private int mValor;

    Hizarkos(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public String toString() {
        String ret = "";

        if (mValor == 1) {
            ret = "HITTARIUM";
        }
        if (mValor == 2) {
            ret = "DEGGOVIUM";
        }
        if (mValor == 3) {
            ret = "NUZTIUM";
        }
        if (mValor == 4) {
            ret = "HARBARIUM";
        }

        return ret;
    }
}

