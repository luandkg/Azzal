package LetrumArkaica;

import Azzal.Renderizador;
import Azzal.Utils.Cor;
import LetrumArkaica.Fonte;
import LetrumArkaica.FontePadrao;
import LetrumArkaica.Letra;

import java.util.ArrayList;

public class Letramento {

    private ArrayList<Letra> mLetras;
    private Renderizador mRenderizador;

    private int mAltura;
    private int mLargura;

    private int[] mPixels;

    private Cor mCor;
    private int mRawCor;

    public void setCor(Cor eCor) {
        mCor = eCor;
        mRawCor = mCor.getValor();
    }

    public Letramento() {

        mRenderizador = null;

        FontePadrao eFontePadrao = new FontePadrao();

        mAltura = eFontePadrao.getAltura();
        mLargura = eFontePadrao.getLargura();

        mLetras = eFontePadrao.getLetras();
        mPixels = eFontePadrao.getPixels();

        mCor = new Cor(255, 255, 255);
        mRawCor = mCor.getValor();

    }

    public Letramento(Fonte eFonte) {

        mRenderizador = null;

        mAltura = eFonte.getAltura();
        mLargura = eFonte.getLargura();

        mLetras = eFonte.getLetras();
        mPixels = eFonte.getPixels();


    }

    public ArrayList<Letra> getLetras() {
        return mLetras;
    }

    public int getAltura() {
        return mAltura;
    }


    public void atualizar(Renderizador eRenderizador) {
        mRenderizador = eRenderizador;
    }

    public void escreveLinha(int y, int x1, int x2, String eTexto1, String eTexto2) {

        escreve(x1, y, eTexto1);
        escreve(x2, y, eTexto2);


    }

    public void escreve(int x, int y, String eTexto) {


        int i = 0;
        int o = eTexto.length();

        while (i < o) {

            String letra = String.valueOf(eTexto.charAt(i));

            boolean enc = false;

            for (Letra eLetra : mLetras) {
                if (eLetra.isLetra(letra)) {

                    for (int posy = 0; posy < mAltura; posy++) {

                        int eComeco = (eLetra.getX1() + (mLargura * posy));
                        int eFim = (eLetra.getX2() + (mLargura * posy));

                        int posx = 0;

                        for (int passando = eComeco; passando < eFim; passando++) {

                            if (passando < mPixels.length){
                                int eCor = mPixels[passando];

                                if (eCor != -1) {
                                    mRenderizador.drawPixelBruto(x + posx, y + posy, eCor);
                                }

                            }

                            //    System.out.println(eCor + " :: " + mRawCor);


                            posx += 1;

                        }


                    }


                    int d = (eLetra.getX2() - eLetra.getX1());
                    if (d > 0) {
                        x += d;
                    }
                    enc = true;
                    break;
                }
            }

            if (!enc) {
                if (letra.contentEquals(" ")) {
                    x += 10;
                } else {
                    x += 30;
                    System.out.println("NAO : " + letra);
                }
            }
            i += 1;
        }


    }

    public int getLarguraDe(String eTexto) {

        int x = 0;

        int i = 0;
        int o = eTexto.length();

        while (i < o) {

            String letra = String.valueOf(eTexto.charAt(i));

            boolean enc = false;

            for (Letra eLetra : mLetras) {
                if (eLetra.isLetra(letra)) {

                    int d = (eLetra.getX2() - eLetra.getX1());
                    if (d > 0) {
                        x += d;
                    }
                    enc = true;
                    break;
                }
            }

            if (!enc) {
                if (letra.contentEquals(" ")) {
                    x += 10;
                } else {
                    x += 30;
                    System.out.println("NAO : " + letra);
                }
            }
            i += 1;
        }

        return x;
    }


    public void desenharGrade(int lx, int ly) {

        int i = 0;
        int ix = 0;


        int a = 0;
        int a2 = mPixels.length;
        while (a < a2) {

            mRenderizador.drawPixelBruto(lx + i, ly + ix, mPixels[a]);

            i += 1;
            if (i >= mLargura) {
                i = 0;
                ix += 1;

            }
            a += 1;
        }


        for (Letra eLetra : getLetras()) {
            for (int y = 0; y < mAltura; y++) {
                mRenderizador.drawPixel(lx + eLetra.getX1(), ly + y, new Cor(255, 0, 0));
                mRenderizador.drawPixel(lx + eLetra.getX2(), ly + y, new Cor(0, 255, 0));
            }
        }


    }

    public void desenharGradeGrande(int lx, int ly) {

        int i = 0;
        int ix = 0;


        int a = 0;
        int a2 = mPixels.length;
        while (a < a2) {


            int mLocalX = lx + (i * 5);
            int mLocalY = ly + (ix * 5);

            for (int zzy = 0; zzy < 5; zzy++) {
                for (int zzx = 0; zzx < 5; zzx++) {

                    mRenderizador.drawPixelBruto(mLocalX + zzx, mLocalY + zzy, mPixels[a]);

                }
            }

            i += 1;
            if (i >= mLargura) {
                i = 0;
                ix += 1;

            }
            a += 1;
        }


        for (Letra eLetra : getLetras()) {
            for (int y = 0; y < mAltura * 5; y++) {
                mRenderizador.drawPixel(lx + (eLetra.getX1() * 5), ly + y, new Cor(255, 0, 0));
                mRenderizador.drawPixel(lx + (eLetra.getX2() * 5), ly + y, new Cor(0, 255, 0));
            }
        }


    }

}

