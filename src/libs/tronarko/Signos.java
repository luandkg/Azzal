package libs.tronarko;


import libs.luan.Lista;

public enum Signos {

    TIGRE(1), RAPOSA(2), LEOPARDO(3), GAVIAO(4), TOURO(5), LOBO(6), GATO(7), CARPA(8), LEAO(9), SERPENTE(10);

    private int mValor;

    Signos(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public static Lista<String> listar_nomes() {

        Lista<String> mSignos = new Lista<String>();

        mSignos.adicionar(Signos.TIGRE.toString());
        mSignos.adicionar(Signos.RAPOSA.toString());
        mSignos.adicionar(Signos.LEOPARDO.toString());
        mSignos.adicionar(Signos.GAVIAO.toString());
        mSignos.adicionar(Signos.TOURO.toString());
        mSignos.adicionar(Signos.LOBO.toString());
        mSignos.adicionar(Signos.GATO.toString());
        mSignos.adicionar(Signos.CARPA.toString());
        mSignos.adicionar(Signos.LEAO.toString());
        mSignos.adicionar(Signos.SERPENTE.toString());

        return mSignos;
    }

    public static Lista<Signos> listar() {

        Lista<Signos> mSignos = new Lista<Signos>();

        mSignos.adicionar(Signos.TIGRE);
        mSignos.adicionar(Signos.RAPOSA);
        mSignos.adicionar(Signos.LEOPARDO);
        mSignos.adicionar(Signos.GAVIAO);
        mSignos.adicionar(Signos.TOURO);
        mSignos.adicionar(Signos.LOBO);
        mSignos.adicionar(Signos.GATO);
        mSignos.adicionar(Signos.CARPA);
        mSignos.adicionar(Signos.LEAO);
        mSignos.adicionar(Signos.SERPENTE);

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

