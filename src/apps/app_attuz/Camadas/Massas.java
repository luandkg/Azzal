package apps.app_attuz.Camadas;

public class Massas {

    private int AGUA;
    private int TERRA;

    private int valores[];

    private int mLargura;
    private int mAltura;

    private int mContagemTerra;
    private int mContagemAgua;

    public Massas(int eTerra, int eAgua, int eLargura, int eAltura, int eQuantidadeTerra, int eQuantidadeAgua, int[] eValores) {

        TERRA = eTerra;
        AGUA = eAgua;
        mLargura = eLargura;
        mAltura = eAltura;
        mContagemTerra = eQuantidadeTerra;
        mContagemAgua = eQuantidadeAgua;
        valores = eValores;

    }



    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public int getContagemTerra() {
        return mContagemTerra;
    }

    public int getContagemAgua() {
        return mContagemAgua;
    }

    public int getContagem() {
        return mContagemAgua + mContagemTerra;
    }

    public int getProporcaoTerra() {
        return (int) (((double) getContagemTerra() / (double) getContagem()) * 100.0);
    }

    // public int getProporcaoAgua() {
    //    return (int) (((double) getContagemAgua() / (double) getContagem()) * 100.0);
    //  }//

    public int getProporcaoAgua() {
        return 100 - getProporcaoTerra();
    }

    public boolean isValido(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTerra(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            int i = (y * mLargura) + x;
            return valores[i] == TERRA;
        } else {
            return false;
        }
    }

    public boolean isAgua(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            int i = (y * mLargura) + x;
            return valores[i] == AGUA;
        } else {
            return false;
        }
    }

    public void pintar_se_terra(int x, int y, int cor) {
        if (isTerra(x, y)) {
            int i = (y * mLargura) + x;
            valores[i] = cor;
        }
    }

    public void pintar_se_agua(int x, int y, int cor) {
        if (isAgua(x, y)) {
            int i = (y * mLargura) + x;
            valores[i] = cor;
        }
    }


    public void pintar(int x, int y, int cor) {
        int i = (y * mLargura) + x;
        valores[i] = cor;
    }

    public int getValor(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            int i = (y * mLargura) + x;
            return valores[i];
        }
        return 0;
    }

    public void setValor(int x, int y, int v) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            int i = (y * mLargura) + x;
            valores[i] = v;
        }
    }

    public int getTerra() {
        return TERRA;
    }

    public int getAgua() {
        return AGUA;
    }



    public void paraCadaPonto(CadaPonto eCadaPonto) {

        for (int y = 0; y < getAltura(); y++) {
            for (int x = 0; x < getLargura(); x++) {
                eCadaPonto.onPonto(x, y);
            }
        }

    }

    public void zerar() {
        for (int i = 0; i < valores.length; i++) {
            valores[i] = 0;
        }
    }

    public int[] getTudo() {
        return valores;
    }
}
