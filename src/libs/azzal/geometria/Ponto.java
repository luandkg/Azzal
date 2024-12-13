package libs.azzal.geometria;

import libs.luan.Igualavel;

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


    public boolean isIgual(int outro_x, int outro_y) {
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

    public boolean isDiferente(int outro_x, int outro_y) {
        if (this.getX() != outro_x || this.getY() != outro_y) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "X : " + mX + " , Y : " + mY;
    }

    public Ponto getCopia() {
        return new Ponto(mX, mY);
    }



    public static Igualavel<Ponto> IGUAL(){
        return new Igualavel<Ponto>() {
            @Override
            public boolean is(Ponto a, Ponto b) {
                return (a.isIgual(b));
            }
        };
    }


    public static Ponto DIFERENCA(Ponto p1,Ponto p2){

        int diff_x = (p2.getX() - p1.getX());
        int diff_y = (p2.getY() - p1.getY());

        return new Ponto(diff_x,diff_y);
    }


    public static double INCLINACAO(Ponto p1, Ponto p2){
        Ponto ponto_diff = Ponto.DIFERENCA(p1, p2);
        return ((double) ponto_diff.getY() / (double) ponto_diff.getX());
    }

    public static int DISTANCIA(Ponto p1, Ponto p2) {
        return DISTANCIA(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

    public static int DISTANCIA(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(((x1 - x2) * (x1 - x2)) + ((y1 - y2) * (y1 - y2)));
    }

}
