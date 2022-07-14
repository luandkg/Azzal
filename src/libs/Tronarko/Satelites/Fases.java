package libs.Tronarko.Satelites;

public enum Fases {

    NOVA(0),
    MINGUANTE(1),
    QUADRO_MINGUANTE(2),
    MINGUANTE_GIBOSA(3),

    CHEIA(4),
    CRESCENTE_GIBOSA(5),
    QUADRO_CRESCENTE(6),
    CRESCENTE(7);

    private int mValor;

    Fases(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public String toString() {
        String ret = "";

        if (mValor == 0) {
            ret = "NOVA";
        } else if (mValor == 1) {
            ret = "MINGUANTE";
        } else if (mValor == 2) {
            ret = "QUADRO_MINGUANTE";
        } else if (mValor == 3) {
            ret = "MINGUANTE_GIBOSA";
        } else if (mValor == 4) {
            ret = "CHEIA";
        } else if (mValor == 5) {
            ret = "CRESCENTE_GIBOSA";
        } else if (mValor == 6) {
            ret = "QUADRO_CRESCENTE";
        } else if (mValor == 7) {
            ret = "CRESCENTE";
        }
        return ret;
    }
}
