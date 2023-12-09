package libs.azzal.geometria;

public class Ponto {

    private int mX;
    private int mY;

    public Ponto() {
        mX = 0;
        mY = 0;
    }

    public Ponto(int eX, int eY) {
        mX = eX;
        mY = eY;
    }

    public void setPos(int eX, int eY) {
        mX = eX;
        mY = eY;
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


    public boolean isIgual(Ponto outro) {
        if (this.getX() == outro.getX() && this.getY() == outro.getY()) {
            return true;
        }
        return false;
    }


    public boolean isIgual(int outro_x,int outro_y) {
        if (this.getX() == outro_x && this.getY() == outro_y) {
            return true;
        }
        return false;
    }

    public boolean isDiferente(Ponto outro) {
        if (this.getX() != outro.getX() || this.getY() != outro.getY()) {
            return true;
        }
        return false;
    }

    public boolean isDiferente(int outro_x,int outro_y) {
        if (this.getX() != outro_x || this.getY() != outro_y) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "X : " + mX + " , Y : " + mY;
    }

    public Ponto getCopia(){return new Ponto(mX,mY);}
}
