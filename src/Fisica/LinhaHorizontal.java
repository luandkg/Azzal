package Fisica;

public class LinhaHorizontal {

    private int mX;
    private int mY;
    private int mTamanho;

    public LinhaHorizontal(int eX, int eY,int eTamanho) {
        this.mX = eX;
        this.mY = eY;
        this.mTamanho = eTamanho;

    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public int getTamanho() {
        return mTamanho;
    }


    public void setX(int eX) {
        mX = eX;
    }

    public void setY(int eY) {
        mY = eY;
    }

    public void setTamanho(int eTamanho) {
        mTamanho = eTamanho;
    }

    public int getX2() {
        return mX + mTamanho;
    }

}
