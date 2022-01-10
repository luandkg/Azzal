package Griff;

public class Griph {

    private boolean[] mDados;
    private int mLinhas;
    private int mColunas;

    private int MAX;

    public Griph() {

        mLinhas = 16;
        mColunas = 16;

        mDados = new boolean[mLinhas * mColunas];

        int i = 0;

        for (int y = 0; y < mLinhas; y++) {
            for (int x = 0; x < mColunas; x++) {
                mDados[i] = false;
                i += 1;
            }
        }

        MAX = mLinhas * mColunas;

    }

    public boolean[] getDados() {
        return mDados;
    }

    public int getLargura() {
        return mLinhas;
    }

    public int getAltura() {
        return mColunas;
    }

    public boolean get(int ex, int ey) {

        boolean v = false;
        int i = 0;

        for (int y = 0; y < mLinhas; y++) {
            for (int x = 0; x < mColunas; x++) {
                if (x == ex && y == ey) {
                    v = mDados[i];
                    break;
                }
                i += 1;
            }
        }

        return v;
    }

    public void set(int ex, int ey, boolean e) {

        int i = 0;

        for (int y = 0; y < mLinhas; y++) {
            for (int x = 0; x < mColunas; x++) {
                if (x == ex && y == ey) {
                    mDados[i] = e;
                    break;
                }
                i += 1;
            }
        }

    }


    public void mostrar() {


        System.out.println("");


        for (int y = 0; y < mLinhas; y++) {
            String Linha = "";
            for (int x = 0; x < mColunas; x++) {

                int i = (y * mColunas) + x;
                if (i < MAX) {
                    if (mDados[i]) {
                        Linha += " 0";
                    } else {
                        Linha += " 1";
                    }
                }

            }
            System.out.println(" [" + Linha + " ] ");
        }


    }

}
