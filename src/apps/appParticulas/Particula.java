package apps.appParticulas;

import azzal.geometria.Quadrado;

public class Particula {

    private static int AREIA_ID = 0;

    private int mID;
    private Quadrado mCorpo;

    public Particula(int eX, int eY, int eTam) {

        mID = AREIA_ID;

        AREIA_ID += 1;

        mCorpo = new Quadrado(eX, eY, eTam);

    }

    public Quadrado getCorpo() {
        return mCorpo;
    }

    public int getID() {
        return mID;
    }


    public int getX() {
        return mCorpo.getX();
    }

    public int getY() {
        return mCorpo.getY();
    }

    public int getX2() {
        return mCorpo.getX2();
    }

    public int getY2() {
        return mCorpo.getY2();
    }

    public int getLargura() {
        return mCorpo.getLargura();
    }

    public int getAltura() {
        return mCorpo.getAltura();
    }

    public void setX(int x) {
        mCorpo.setX(x);
    }

    public void setY(int y) {
        mCorpo.setY(y);
    }
}
