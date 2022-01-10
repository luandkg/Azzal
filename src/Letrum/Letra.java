package Letrum;

public class Letra {

    private String ml;

    private int mx1;
    private int mx2;
    private int my1;
    private int my2;

    public Letra(String l, int x1, int y1, int x2, int y2) {
        ml = l;
        mx1 = x1;
        mx2 = x2;
        my1 = y1;
        my2 = y2;
    }

    public void redefinir(String l, int x1, int y1, int x2, int y2) {
        ml = l;
        mx1 = x1;
        mx2 = x2;
        my1 = y1;
        my2 = y2;
    }

    public String getL() {
        return ml;
    }

    public int getX1() {
        return mx1;
    }

    public int getX2() {
        return mx2;
    }

    public int getY1() {
        return my1;
    }

    public int getY2() {
        return my2;
    }

    public boolean igual(String a) {
        return ml.contentEquals(a);
    }
}
