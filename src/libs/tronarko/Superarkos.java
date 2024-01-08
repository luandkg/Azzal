package libs.tronarko;

public enum Superarkos {

    ALFA(1), BETA(2), GAMA(3), DELTA(4), EPSILON(5), IOTA(6), KAPA(7), ZETA(8), SIGMA(9), OMEGA(10);

    private int mValor;

    Superarkos(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public static Superarkos get(int eValor) {
        Superarkos ret = null;

        switch (eValor) {
            case 1:
                ret = Superarkos.ALFA;
                break;
            case 2:
                ret = Superarkos.BETA;
                break;
            case 3:
                ret = Superarkos.GAMA;
                break;
            case 4:
                ret = Superarkos.DELTA;
                break;
            case 5:
                ret = Superarkos.EPSILON;
                break;
            case 6:
                ret = Superarkos.IOTA;
                break;
            case 7:
                ret = Superarkos.KAPA;
                break;
            case 8:
                ret = Superarkos.ZETA;
                break;
            case 9:
                ret = Superarkos.SIGMA;
                break;
            case 10:
                ret = Superarkos.OMEGA;
                break;
        }

        return ret;
    }

    public String toString() {
        String ret = "";

        if (mValor == 1) {
            ret = "ALFA";
        }
        if (mValor == 2) {
            ret = "BETA";
        }
        if (mValor == 3) {
            ret = "GAMA";
        }
        if (mValor == 4) {
            ret = "DELTA";
        }
        if (mValor == 5) {
            ret = "EPSILON";
        }
        if (mValor == 6) {
            ret = "IOTA";
        }
        if (mValor == 7) {
            ret = "KAPA";
        }
        if (mValor == 8) {
            ret = "ZETA";
        }
        if (mValor == 9) {
            ret = "SIGMA";
        }
        if (mValor == 10) {
            ret = "OMEGA";
        }

        return ret;
    }

    public String getCapital() {
        String ret = "";

        if (mValor == 1) {
            ret = "ALF";
        }
        if (mValor == 2) {
            ret = "BET";
        }
        if (mValor == 3) {
            ret = "GAM";
        }
        if (mValor == 4) {
            ret = "DEL";
        }
        if (mValor == 5) {
            ret = "EPS";
        }
        if (mValor == 6) {
            ret = "IOT";
        }
        if (mValor == 7) {
            ret = "KAP";
        }
        if (mValor == 8) {
            ret = "ZET";
        }
        if (mValor == 9) {
            ret = "SIG";
        }
        if (mValor == 10) {
            ret = "OME";
        }

        return ret;
    }
}
