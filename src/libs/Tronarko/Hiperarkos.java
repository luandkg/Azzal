package libs.Tronarko;

public  enum Hiperarkos {

    DAZTO(1), HERTO(2), PURGO(3), NOPTO(4), FENCO(5), MOZTO(6), CARGO(7), RIZNO(8), SACNO(9), TORNO(10);

    private int mValor;

    Hiperarkos(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public static Hiperarkos get(int eValor) {
        Hiperarkos ret = null;

        switch (eValor) {
            case 1:
                ret = Hiperarkos.DAZTO;
                break;
            case 2:
                ret = Hiperarkos.HERTO;
                break;
            case 3:
                ret = Hiperarkos.PURGO;
                break;
            case 4:
                ret = Hiperarkos.NOPTO;
                break;
            case 5:
                ret = Hiperarkos.FENCO;
                break;
            case 6:
                ret = Hiperarkos.MOZTO;
                break;
            case 7:
                ret = Hiperarkos.CARGO;
                break;
            case 8:
                ret = Hiperarkos.RIZNO;
                break;
            case 9:
                ret = Hiperarkos.SACNO;
                break;
            case 10:
                ret = Hiperarkos.TORNO;
                break;
        }

        return ret;
    }

    public static String getNumerado(int eValor) {
        String ret = "";

        switch (eValor) {
            case 1:
                ret = "1 - " + Hiperarkos.DAZTO.toString();
                break;
            case 2:
                ret = "2 - " +Hiperarkos.HERTO.toString();
                break;
            case 3:
                ret = "3 - " +Hiperarkos.PURGO.toString();
                break;
            case 4:
                ret = "4 - " +Hiperarkos.NOPTO.toString();
                break;
            case 5:
                ret = "5 - " +Hiperarkos.FENCO.toString();
                break;
            case 6:
                ret = "6 - " +Hiperarkos.MOZTO.toString();
                break;
            case 7:
                ret = "7 - " +Hiperarkos.CARGO.toString();
                break;
            case 8:
                ret = "8 - " +Hiperarkos.RIZNO.toString();
                break;
            case 9:
                ret = "9 - " +Hiperarkos.SACNO.toString();
                break;
            case 10:
                ret = "10 - " +Hiperarkos.TORNO.toString();
                break;
        }

        return ret;
    }

    public String toString() {
        String ret = "";

        if (mValor == 1) {
            ret = "DAZTO";
        }
        if (mValor == 2) {
            ret = "HERTO";
        }
        if (mValor == 3) {
            ret = "PURGO";
        }
        if (mValor == 4) {
            ret = "NOPTO";
        }
        if (mValor == 5) {
            ret = "FENCO";
        }
        if (mValor == 6) {
            ret = "MOZTO";
        }
        if (mValor == 7) {
            ret = "CARGO";
        }
        if (mValor == 8) {
            ret = "RIZNO";
        }
        if (mValor == 9) {
            ret = "SACNO";
        }
        if (mValor == 10) {
            ret = "TORNO";
        }

        return ret;
    }
}
