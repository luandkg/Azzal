package libs.tronarko.Harrempluz;

public enum Item{

    COR(0), ELEMENTO(1), METAL(2), QUALIDADE(3), DEFEITO(4), NUMERO_10(5), NUMERO_100(6), VOGAL(7), CONSOANTE(8),
    LETRA(9), CRISTAL(10), DIRECAO(11);

    private int mValor;

    Item(int eValor) {
        mValor = eValor;
    }

    public int getValor() {
        return mValor;
    }

    public String toString() {
        String ret = "";

        if (mValor == 0) {
            ret = "COR";
        }
        if (mValor == 1) {
            ret = "ELEMENTO";
        }
        if (mValor == 2) {
            ret = "METAL";
        }
        if (mValor == 3) {
            ret = "QUALIDADE";
        }
        if (mValor == 4) {
            ret = "DEFEITO";
        }
        if (mValor == 5) {
            ret = "NUMERO_10";
        }
        if (mValor == 6) {
            ret = "NUMERO_100";
        }
        if (mValor == 7) {
            ret = "VOGAL";
        }
        if (mValor == 8) {
            ret = "CONSOANTE";
        }
        if (mValor == 9) {
            ret = "LETRA";
        }
        if (mValor == 10) {
            ret = "CRISTAL";
        }
        if (mValor == 11) {
            ret = "DIRECAO";
        }
        return ret;
    }
}
