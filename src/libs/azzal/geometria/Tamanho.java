package libs.azzal.geometria;

public class Tamanho {

    private int mLargura;
    private int mAltura;

    public Tamanho() {
        mLargura = 0;
        mAltura = 0;
    }

    public Tamanho(int eLargura, int eAltura) {
        mLargura = eLargura;
        mAltura = eAltura;
    }

    public void setPos(int eLargura, int eAltura) {
        mLargura = eLargura;
        mAltura = eAltura;
    }

    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public void setLargura(int eLargura) {
        mLargura = eLargura;
    }

    public void setAltura(int eAltura) {
        mAltura = eAltura;
    }

    public String toString() {
        return "Largura : " + mLargura + " , Altura : " + mAltura;
    }
}
