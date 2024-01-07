package apps.app_citatte.cidade_beta;

import libs.azzal.geometria.Ponto;

public class CidadeItem extends Ponto {

    public static final int LOTE = 1;
    public static final int ESTRADA = 2;

    private int mTipo;

    public CidadeItem(int eTipo, int px, int py) {
        super(px, py);
        mTipo = eTipo;
    }


    public int getTipo() {
        return mTipo;
    }


    public boolean isLote() {
        return mTipo == LOTE;
    }

    public boolean isEstrada() {
        return mTipo == ESTRADA;
    }

}
