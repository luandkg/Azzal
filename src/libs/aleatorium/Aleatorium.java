package libs.aleatorium;

public class Aleatorium {

    private int mNumeros[];
    private int mColunas;
    private int mLinhas;
    private int mPonteiroX;
    private int mPonteiroY;
    private int c;

    private int MIN;
    private int MAX;

    public Aleatorium() {

        mColunas = 10;
        mLinhas = 10;
        c = 1;
        MIN = 0;
        MAX = 10;

        mNumeros = new int[mColunas * mLinhas];

        mPonteiroX = 0;
        mPonteiroY = 0;

        for (int i = 0; i < (mColunas * mLinhas); i++) {
            mNumeros[i] = 0;
        }

        int contando = 0;

        int SEEK = (int) (System.currentTimeMillis() / 1000);

        for (int linha = 0; linha < mLinhas; linha++) {
            for (int coluna = 0; coluna < mColunas; coluna++) {

                set(linha, coluna, MNG(contando, ((coluna + 1) * linha) + SEEK));

                contando += 1;

            }
        }

    }


    public int MNG(int v, int c) {
        return (v + 2) - (v * 5) - ((4 * c) / 5);
    }

    public int swap(int n) {

        while (n < MIN) {
            n += MAX;
        }


        while (n >= MAX) {
            n -= MAX;
        }


        return n;
    }

    public void set(int l, int c, int v) {
        mNumeros[(l * mColunas) + c] = swap(v);
    }

    public void mostrar() {

        System.out.print("\n");
        System.out.print("\n");

        for (int linha = 0; linha < mLinhas; linha++) {

            System.out.print("\t\t");

            for (int coluna = 0; coluna < mColunas; coluna++) {

                int valor = mNumeros[(linha * mColunas) + coluna];
                String sValor = String.valueOf(valor);
                while (sValor.length() < c) {
                    sValor = "0" + sValor;
                }
                System.out.print(" " + sValor);
            }

            System.out.print("\n");

        }

        System.out.print("\n");
        System.out.print("\n");


    }

    public int getPonteiro() {
        return mNumeros[(mPonteiroX * mColunas) + mPonteiroY];
    }


    public int getComDebug() {

        int v = getPonteiro();

        System.out.println(" -->> GET");
        System.out.println(" -->> PONTEIRO = " + mPonteiroX + ":" + mPonteiroY + " -->> " + v);
        System.out.println(" -->> ANTES");
        mostrar();


        mPonteiroX += 3 + v;
        mPonteiroY -= 8 + v;

        for (int linha = 0; linha < mLinhas; linha++) {

            for (int coluna = 0; coluna < mColunas; coluna++) {
                set(linha, coluna, MNG(mNumeros[(linha * mColunas) + coluna], v));
            }

        }


        System.out.println(" -->> DEPOIS");
        mostrar();
        System.out.println(" -->> RETORNOU = " + v);
        System.out.println(" -->> PONTEIRO = " + mPonteiroX + ":" + mPonteiroY + " -->> " + v);


        organizarPonteiros();

        return v;
    }

    public int get() {

        int v = getPonteiro();

        mPonteiroX += 3 + v;
        mPonteiroY -= 8 + v;

        for (int linha = 0; linha < mLinhas; linha++) {

            for (int coluna = 0; coluna < mColunas; coluna++) {
                set(linha, coluna, MNG(mNumeros[(linha * mColunas) + coluna], v));
            }

        }


        organizarPonteiros();

        return v;
    }

    private int mTrava = 5;
    private int mAntes = 0;

    public int get(int eMin, int eMax) {

        int intervalo = eMax - eMin;

        String e = String.valueOf(intervalo);

        String mSaida = "";

        int t = e.length();


        for (int i = 0; i < t; i++) {

            if (i > 0) {
                if (get() > mTrava) {
                    mSaida += String.valueOf(get());
                }
            } else {
                mSaida += String.valueOf(get());
            }

            mTrava = get();
        }

        int num = Integer.parseInt(mSaida) + mAntes;

        while (num > intervalo) {
            num -= intervalo;
        }

        int ret = eMin + num;
        mAntes = ret;

        System.out.println("Num = " + ret);


        return ret;
    }


    public void organizarPonteiros() {

        while (mPonteiroX < 0) {
            mPonteiroX += mColunas;
        }


        while (mPonteiroX >= mColunas) {
            mPonteiroX -= mColunas;
        }


        while (mPonteiroY < 0) {
            mPonteiroY += mLinhas;
        }


        while (mPonteiroY >= mLinhas) {
            mPonteiroY -= mLinhas;
        }

    }

}
