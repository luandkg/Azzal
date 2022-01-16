package AppLetras;

import Azzal.Cenarios.Cena;
import Azzal.Mouse;
import Azzal.Utils.*;
import Azzal.Renderizador;
import Azzal.Windows;
import Griff.Griph;
import Griff.Griphattor;
import Griff.Griphizz;


import java.awt.*;
import java.util.Random;


public class Letras extends Cena {

    private Griphattor mGriphattor;
    private Griphizz mGriphizz;

    private Mouse mMouse;

    private Griph mQuadro;
    private Cor eTextoCor;

    private int mQuadroX;
    private int mQuadroY;
    private int mQuadroTamanho;

    private int mBTN_X;
    private int mBTN_Y;
    private int mBTN_QUAD;

    private Matriz mMatriz;

    private Cronometro mC1;
    private Random rd;

    public Letras() {

        mGriphattor = new Griphattor();
        mGriphizz = new Griphizz();

        mQuadro = new Griph();
        eTextoCor = new Cor(120, 40, 50);

        mQuadroX = 150;
        mQuadroY = 150;
        mQuadroTamanho = 50;

        mBTN_QUAD = 100;
        mBTN_X = 1000;
        mBTN_Y = 150;

        mMatriz = new Matriz(10, 10);


        mMatriz.setLinha(0, 0, "LUAN");
        mMatriz.setLinha(3, 1, "LUAN");
        mMatriz.setLinha(5, 2, "LUAN");

        //mQuadro=mGriphattor.getO();
        mC1 = new Cronometro(800);
        rd = new Random();

    }


    @Override
    public void iniciar(Windows eWindows) {
        eWindows.setTitle("Letras");
        mMouse = eWindows.getMouse();
    }

    public String getAle() {
        String mAlfabeto = "ABCDEFGHIJLKLMNOPQRSTUVWXYZ0123456789";
        int r = rd.nextInt(mAlfabeto.length());
        return String.valueOf(mAlfabeto.charAt(r));
    }

    public String getAles(int n) {
        String ret = "";
        for (int i = 0; i < n; i++) {
            ret += getAle();
        }
        return ret;
    }

    public void matrizGerador() {

        int r = rd.nextInt(100);

        if (r >= 0 && r < 30) {
            mMatriz.descer("   " + getAles(2) + " " + getAle() + "90PLAS");
        } else if (r >= 30 && r < 50) {
            mMatriz.descer(getAles(6) + "ZADM0" + getAles(2) + " " + getAle());
        } else if (r >= 50 && r < 60) {
            mMatriz.descer(getAle() + " " + getAles(5) + "L OL0" + getAles(3));
        } else if (r >= 60 && r < 70) {
            mMatriz.descer(getAles(3) + " " + getAles(3) + " L N0" + getAles(3));
        } else if (r >= 70 && r < 80) {
            mMatriz.descer("ALETEIA_" + getAles(3));
        } else if (r >= 80 && r < 100) {
            mMatriz.descer("   MOCREIA" + getAles(3));
        } else {
            mMatriz.descer(getAles(4) + "  " + getAles(2) + "890A  D ");
        }

    }

    private boolean mIniciado = false;
    private int mQuantos;
    private int mL1;
    private int mL2;
    private int mL3;

    private int mT1;
    private int mT2;
    private int mT3;

    private int mB1;
    private int mB2;
    private int mB3;

    private int mI1;
    private int mI2;
    private int mI3;

    private int mMin1;
    private int mMin2;
    private int mMin3;

    public void matrizAleatoria() {

        String eLinha = getAles(10);
        if (!mIniciado) {

            mIniciado = true;
            mQuantos = 0;
            mI1 = 0;

            mB1 = rd.nextInt(100);
            mB2 = rd.nextInt(100);
            mB3 = rd.nextInt(100);


            mL1 = rd.nextInt(10);
            mL2 = rd.nextInt(10);
            mL3 = rd.nextInt(10);

            mT1 = rd.nextInt(15);
            mT2 = rd.nextInt(15);
            mT3 = rd.nextInt(15);

            mMin1 = 5;
            mMin2 = 3;
            mMin3 = 8;


        } else {

            if (mB1 >= 50) {
                eLinha = trocarPos(eLinha, mL1, " ");
            }

            if (mB2 >= 50) {
                eLinha = trocarPos(eLinha, mL2, " ");
            }

            if (mB3 >= 50) {
                eLinha = trocarPos(eLinha, mL3, " ");
            }

            mI1 += 1;
            mI2 += 1;
            mI3 += 1;

            if (mI1 > mT1) {
                mI1 = 0;
                mB1 = rd.nextInt(100);
                mL1 = rd.nextInt(10);
                mT1 = rd.nextInt(15);

            }

            if (mI2 > mT2) {
                mI2 = 0;
                mB2 = rd.nextInt(100);
                mL2 = rd.nextInt(10);
                mT2 = rd.nextInt(15);

            }

            if (mI3 > mT3) {
                mI3 = 0;
                mB3 = rd.nextInt(100);
                mL3 = rd.nextInt(10);

                if (rd.nextInt(100) >= 50) {
                    mT3 += rd.nextInt(3);
                } else {
                    mT3 -= rd.nextInt(3);
                }
                if (mT3>15){
                    mT3 = rd.nextInt(15) + 5;
                }

                if (mT3<0){
                    mT3 = rd.nextInt(15) + 5;
                }
            }
        }

        mMatriz.descer(eLinha);


    }

    public String trocarPos(String eLinha, int ePos, String eColocar) {

        int i = 0;
        int o = eLinha.length();

        String eNova = "";

        while (i < o) {
            String c = String.valueOf(eLinha.charAt(i));

            if (i == ePos) {
                eNova += eColocar;
            } else {
                eNova += c;
            }


            i += 1;
        }

        return eNova;
    }

    @Override
    public void update(double dt) {

        mC1.esperar();

        if (mC1.foiEsperado()) {

            // matrizGerador();
            matrizAleatoria();

        }

        if (mMouse.isClicked()) {

            if (!mMouse.isPressed()) {

                if (mMouse.getX() >= mBTN_X && mMouse.getX() < (mBTN_X + mBTN_QUAD)) {
                    if (mMouse.getY() >= mBTN_Y && mMouse.getY() < (mBTN_Y + mBTN_QUAD)) {

                        System.out.println("########################################");
                        System.out.println();

                        System.out.println(" private Griph carregar_Letra(){ ");
                        System.out.println("\t Griph mLetra = new Griph();");


                        for (int iy = 0; iy < 16; iy++) {
                            for (int ix = 0; ix < 16; ix++) {
                                boolean e = mQuadro.get(ix, iy);
                                if (e) {
                                    System.out.println("\t mLetra.set(" + ix + ", " + iy + ", true);");
                                }
                            }
                        }

                        System.out.println("\t  return mLetra;");
                        System.out.println("}");
                    }
                }

                int mQuadroXFim = mQuadroX + (mQuadroTamanho * 16);
                int mQuadroYFim = mQuadroY + (mQuadroTamanho * 16);

                if (mMouse.getX() >= mQuadroX && mMouse.getX() < mQuadroXFim) {
                    if (mMouse.getY() >= mQuadroY && mMouse.getY() < mQuadroYFim) {


                        int eX = 0;
                        int iX = mMouse.getX() - mQuadroX;
                        while (iX >= mQuadroTamanho) {
                            iX -= mQuadroTamanho;
                            eX += 1;
                        }

                        int eY = 0;
                        int iY = mMouse.getY() - mQuadroY;
                        while (iY >= mQuadroTamanho) {
                            iY -= mQuadroTamanho;
                            eY += 1;
                        }

                        // System.out.println("Peca Clicado X = " + eX + " Y = " + eY);

                        if (eX >= 0 && eX < 16) {
                            if (eY >= 0 && eY < 16) {
                                mQuadro.set(eX, eY, !mQuadro.get(eX, eY));
                            }
                        }

                    }
                }


                mMouse.liberar();
            }
        }

    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        //  mRenderizador.drawQuad(300, 200, 100, 100, new Cor(120, 40, 50));

        //   mRenderizador.drawQuad(300, 500, 100, 100, new Cor(120, 40, 50));

        Cor eVazio = new Cor(50, 50, 50);
        Cor ePreenchico = new Cor(50, 150, 40);

        drawQuadro(mRenderizador, mQuadroX, mQuadroY, mQuadro, eVazio, ePreenchico);


        mGriphizz.drawGriph(mRenderizador, 30, 50, mQuadro, eTextoCor);


        mRenderizador.drawQuad(mBTN_X, mBTN_Y, mBTN_QUAD, mBTN_QUAD, ePreenchico);

        mGriphizz.drawString(mRenderizador, mGriphattor, "ABCDEFGHIJKLM", eTextoCor, 1000, 600);
        mGriphizz.drawString(mRenderizador, mGriphattor, "NOPQRSTUVWXYZ.:_-><", eTextoCor, 1000, 625);
        mGriphizz.drawString(mRenderizador, mGriphattor, "0123456789+-/*", eTextoCor, 1000, 650);

        mGriphizz.drawString(mRenderizador, mGriphattor, "LUAN ALVES FREITAS", eTextoCor, 1000, 700);

        mGriphizz.drawString(mRenderizador, mGriphattor, "IDADE = 28º", ePreenchico, 1000, 725);

        int ePosX = 1000;
        int ePosY = 400;

        for (int x = 0; x < mMatriz.getColunas(); x++) {
            for (int y = 0; y < mMatriz.getLinhas(); y++) {
                mGriphizz.drawString(mRenderizador, mGriphattor, mMatriz.get(x, y), ePreenchico, ePosX + (x * 16), ePosY + (y * 16));
            }
        }

    }


    public void drawQuadro(Renderizador mRenderizador, int eX, int eY, Griph eGriph, Cor eVazio, Cor ePreenchido) {

        int aX = eX;
        int aY = eY;

        int aXFim = eGriph.getLargura();
        int aYFim = eGriph.getAltura();

        int eTam = mQuadroTamanho;
        int eDentro = eTam - 2;

        for (int iy = 0; iy < aYFim; iy++) {
            for (int ix = 0; ix < aXFim; ix++) {

                boolean e = eGriph.get(ix, iy);

                if (e) {
                    mRenderizador.drawQuad(aX + (ix * eTam), aY + (iy * eTam), eDentro, eDentro, ePreenchido);
                } else {
                    mRenderizador.drawQuad(aX + (ix * eTam), aY + (iy * eTam), eDentro, eDentro, eVazio);
                }

            }

        }

    }

}
