package apps.app_attuz.Assessorios;

public class Escala {

    private int valores[];
    private int mMaximo;
    private int mMinimo;

    public Escala() {
        mMaximo = 0;
        mMinimo = 0;
        valores = new int[100];
        for (int i = 0; i < 100; i++) {
            valores[i] = 0;
        }
    }

    public Escala(int eMinimo, int eMaximo) {
        mMinimo = eMinimo;
        mMaximo = eMaximo;
        valores = new int[100];
        for (int i = 0; i < 100; i++) {
            valores[i] = 0;
        }
    }

    public int getMaximo() {
        return mMaximo;
    }

    public int getMinimo() {
        return mMinimo;
    }

    public int set(int c, int v) {
        return valores[c] = v;
    }

    public int get(int c) {
        if (c < 0) {
            c = 0;
        }
        return valores[c];
    }
}
