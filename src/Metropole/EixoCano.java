package Metropole;

public class EixoCano {

    private boolean mStatus;
    private int mPos;
    private int mQuantidade;

    public EixoCano(int ePos) {
        mStatus = false;
        mPos = ePos;
        mQuantidade = 0;
    }

    public int getPos() {
        return mPos;
    }

    public boolean isCheio() {
        return mStatus == true;
    }

    public boolean isVazio() {
        return mStatus == false;
    }

    public void encher(int eQuantidade) {
        mStatus = true;
        mQuantidade = eQuantidade;
    }

    public void esvaziar() {
        mStatus = false;
        mQuantidade = 0;
    }

    public int getQuantidade() {
        return mQuantidade;
    }
}