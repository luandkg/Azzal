package apps.app_fisica;

public class LinhaVertical {

    private int mX;
    private int mY;
    private int mTamanho;

    public LinhaVertical(int eX, int eY,int eTamanho) {
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

    public int getY2() {
        return mY + mTamanho;
    }

}
