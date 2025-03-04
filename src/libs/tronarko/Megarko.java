package libs.tronarko;

public enum Megarko {

    Megarko_1(1), Megarko_2(2), Megarko_3(3), Megarko_4(4), Megarko_5(5);

    private int mValor;

    Megarko(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public String toString() {
        String ret = "";

        if (mValor == 1) {
            ret = "MEGARKO_1";
        }else if (mValor == 2) {
            ret = "MEGARKO_2";
        }else if (mValor == 3) {
            ret = "MEGARKO_3";
        }else if (mValor == 4) {
            ret = "MEGARKO_4";
        }else if (mValor == 5) {
            ret = "MEGARKO_5";
        }

        return ret;
    }

}
