package apps.app_citatte.urbanizacao;

import libs.azzal.geometria.Ponto;
import libs.azzal.geometria.Retangulo;

public class AreaTipada {


    public static final int AREA_PUBLICA = 1;
    public static final int AREA_PRIVADA = 2;


    public static final int PRIVADA_RESIDENCIAL = 4;
    public static final int PRIVADA_COMERCIAL = 6;


    private String mNome;
    private int mTipo;
    private int mSubTipo;
    private Retangulo mArea;

    public AreaTipada(int eTipo, String eNome, Retangulo eArea) {
        mTipo = eTipo;
        mNome = eNome;
        mSubTipo = 0;
        mArea = eArea;
    }

    public int getTipo() {
        return mTipo;
    }

    public String getNome() {
        return mNome;
    }


    public boolean isPublica() {
        return mTipo == AREA_PUBLICA;
    }

    public boolean isPrivada() {
        return mTipo == AREA_PRIVADA;
    }


    public void setSubTipo(int eSubTipo) {
        mSubTipo = eSubTipo;
    }

    public int getSubTipo() {
        return mSubTipo;
    }


    public boolean isComercial() {
        return mSubTipo == PRIVADA_COMERCIAL;
    }

    public boolean isResidencial() {
        return mSubTipo == PRIVADA_RESIDENCIAL;
    }

    public Retangulo getArea() {
        return mArea;
    }

    public Ponto getLocalizacao() {
        return new Ponto(mArea.getX(), mArea.getY());
    }

    public Ponto getCentroLocalizacao() {
        return new Ponto(mArea.getX() + mArea.getLargura() / 2, mArea.getY() + mArea.getAltura() / 2);
    }

}
