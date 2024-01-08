package libs.arquivos.video;

public class Q4 {

    private int mX1;
    private int mY1;

    private int mX2;
    private int mY2;

    public Q4(int eX1, int eY1, int eX2, int eY2) {
        this.mX1 = eX1;
        this.mY1 = eY1;
        this.mX2 = eX2;
        this.mY2 = eY2;

    }

    public int getX() {
        return mX1;
    }

    public int getY() {
        return mY1;
    }

    public int getX2() {
        return mX2;
    }

    public int getY2() {
        return mY2;
    }

    public void setX1(int eX1) {
        mX1 = eX1;
    }

    public void setY1(int eY1) {
        mY1 = eY1;
    }

    public void setX2(int eX2) {
        mX2 = eX2;
    }

    public void setY2(int eY2) {
        mY2 = eY2;
    }


    public boolean isIgual(Q4 q2) {

        boolean ret = false;

        if (this.getX() == q2.getX() && this.getY() == q2.getY() && this.getX2() == q2.getX2() && this.getY2() == q2.getY2()) {
            ret = true;
        }

        return ret;

    }

    public String toString() {
        return "X1 : " + mX1 + " Y1 : " + mY1 + " X2 : " + mX2 + " Y2 : " + mY2;
    }
}
