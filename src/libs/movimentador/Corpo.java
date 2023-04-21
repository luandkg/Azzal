package libs.movimentador;

import libs.azzal.geometria.Ponto;

public class Corpo {

    private int mID;

    private int mX;
    private int mY;

    private Ponto mMin;
    private Ponto mMax;

    private Movimento onMovimento;
    private int mTipo;


    public Corpo(int eID, int eX, int eY) {
        mID = eID;
        mX = eX;
        mY = eY;
        mMin = new Ponto(0, 0);
        mMax = new Ponto(mX, mY);
        onMovimento = null;
        mTipo = -1;

    }

    public void setMovimento(int eTipo, Movimento eMovimento) {

        mTipo = eTipo;
        onMovimento = eMovimento;
        if (onMovimento != null) {
            onMovimento.setCorpo(this);
        }

    }

    public int getTipo() {
        return mTipo;
    }


    public void setPos(int eX, int eY) {
        mX = eX;
        mY = eY;
    }

    public void setMin(int eX, int eY) {
        mMin.setPos(eX, eY);
    }

    public void setMax(int eX, int eY) {
        mMax.setPos(eX, eY);
    }

    public Ponto getMin() {
        return mMin;
    }

    public Ponto getMax() {
        return mMax;
    }

    public int getID() {
        return mID;
    }

    public int getX() {
        return mX;
    }

    public int getY() {
        return mY;
    }

    public void setX(int eX) {
        mX = eX;
    }

    public void setY(int eY) {
        mY = eY;
    }

    public String toString() {
        return mID + " -->>  X : " + mX + " , Y : " + mY;
    }


    public void mover() {
        onMovimento.mover();
    }
}
