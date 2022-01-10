package UI;

import Azzal.Formatos.Retangulo;

public class Toggle {

    private String mNome;
    private Retangulo mRect;
    private boolean mStatus;

    private int mTAX;
    private int mTAY;

    public Toggle(String eNome, Retangulo eRect) {
        mNome = eNome;
        mRect = eRect;
        mStatus = false;
        mTAX = 0;
        mTAY = 0;
    }

    public String getNome() {
        return mNome;
    }

    public Retangulo getRetangulo() {
        return mRect;
    }

    public boolean isDentro(int eX, int eY) {
        return mRect.isDentro(eX, eY);
    }

    public boolean getStatus() {
        return mStatus;
    }

    public void setStatus(boolean e) {
        mStatus = e;
    }

    public void mudar() {
        mStatus = !mStatus;
    }

    public void setTextoAlinhamento(int eX, int eY) {
        mTAX = eX;
        mTAY = eY;
    }

    public int getTextoAlinhamentoX(){return mTAX;}

    public int getTextoAlinhamentoY(){return mTAY;}

}
