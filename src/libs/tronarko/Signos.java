package libs.tronarko;

import java.util.ArrayList;

public  enum Signos {

    TIGRE(1), RAPOSA(2), LEOPARDO(3), GAVIAO(4), TOURO(5), LOBO(6), GATO(7), CARPA(8), LEAO(9), SERPENTE(10);

    private int mValor;

    Signos(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public ArrayList<String> Listar() {

        ArrayList<String> mSignos = new ArrayList<String>();

        mSignos.add(Signos.TIGRE.toString());
        mSignos.add(Signos.RAPOSA.toString());
        mSignos.add(Signos.LEOPARDO.toString());
        mSignos.add(Signos.GAVIAO.toString());
        mSignos.add(Signos.TOURO.toString());
        mSignos.add(Signos.LOBO.toString());
        mSignos.add(Signos.GATO.toString());
        mSignos.add(Signos.CARPA.toString());
        mSignos.add(Signos.LEAO.toString());
        mSignos.add(Signos.SERPENTE.toString());

        return mSignos;
    }

    public String toString() {
        String ret = "";

        if (mValor == 1) {
            ret = "TIGRE";
        }
        if (mValor == 2) {
            ret = "RAPOSA";
        }
        if (mValor == 3) {
            ret = "LEOPARDO";
        }
        if (mValor == 4) {
            ret = "GAVIAO";
        }
        if (mValor == 5) {
            ret = "TOURO";
        }
        if (mValor == 6) {
            ret = "LOBO";
        }
        if (mValor == 7) {
            ret = "GATO";
        }
        if (mValor == 8) {
            ret = "CARPA";
        }
        if (mValor == 9) {
            ret = "LEAO";
        }
        if (mValor == 10) {
            ret = "SERPENTE";
        }

        return ret;
    }

    public static Signos get(int eValor) {
        Signos ret = null;

        switch (eValor) {
            case 1:
                ret = Signos.TIGRE;
                break;
            case 2:
                ret = Signos.RAPOSA;
                break;
            case 3:
                ret = Signos.LEOPARDO;
                break;
            case 4:
                ret = Signos.GAVIAO;
                break;
            case 5:
                ret = Signos.TOURO;
                break;
            case 6:
                ret = Signos.LOBO;
                break;
            case 7:
                ret = Signos.GATO;
                break;
            case 8:
                ret = Signos.CARPA;
                break;
            case 9:
                ret = Signos.LEAO;
                break;
            case 10:
                ret = Signos.SERPENTE;
                break;
        }

        return ret;
    }
}

