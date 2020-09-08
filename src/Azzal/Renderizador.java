package Azzal;

import Azzal.Formatos.*;
import Azzal.Utils.Cor;
import Azzal.Utils.Luz;
import Azzal.Utils.TransformadorDeCor;
import Luan.Iterador;
import Luan.Lista;
import Luan.Par;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Renderizador {

    private int[] mPixels;
    private boolean[] mMapaDeAcao;
    private int[] mMapaDeLuz;

    private int mLargura;
    private int mAltura;

    private Lista<Luz> mLuzes;
    private Iterador<Luz> mLuzes_Iterador;

    private Cor mAmbiente;

    public Renderizador(BufferedImage eImagem) {


        mPixels = ((DataBufferInt) eImagem.getRaster().getDataBuffer()).getData();

        mLargura = eImagem.getWidth();
        mAltura = eImagem.getHeight();

        mMapaDeAcao = new boolean[mLargura * mAltura];
        mMapaDeLuz = new int[mLargura * mAltura];

        mLuzes = new Lista<Luz>();
        mLuzes_Iterador = new Iterador<Luz>(mLuzes);

        mAmbiente = new Cor(0, 0, 0);
        mAmbiente.setAlpha(255);

    }

    public void limpar(Color eCor) {


        for (int i = 0; i < mPixels.length; i++) {
            mPixels[i] = eCor.getRGB();
        }


        mLuzes.limpar();


        for (int i = 0; i < mPixels.length; i++) {
            mMapaDeAcao[i] = true;
            mMapaDeLuz[i] = mAmbiente.getValor();
        }


    }

    public void drawPixel(int eX, int eY, Cor eCor) {

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            mPixels[ePox] = eCor.getValor();

        }


    }

    public void drawLumnos(int eX, int eY, Cor eCor) {

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            int i1 = mMapaDeLuz[ePox];
            int i2 = eCor.getValor();


            mMapaDeLuz[ePox] = combinar(i1, i2, 50);
            // mMapaDeLuz[ePox] = i2;


        }


    }

    public int getOpacidade() {
        return mAmbiente.getAlpha();
    }

    public void bloquearPixel(int eX, int eY) {

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            mMapaDeAcao[ePox] = false;

        }


    }

    public boolean getPodeIluminar(int eX, int eY) {

        boolean ret = false;

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            ret = mMapaDeAcao[ePox];

        }

        return ret;
    }


    public void drawPonto(int eX, int eY, Cor eCor) {

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            mPixels[ePox] = eCor.getValor();

        }

    }

    public void drawPonto(Ponto ePonto, Cor eCor) {

        if (ePonto.getX() >= 0 && ePonto.getX() < mLargura && ePonto.getY() >= 0 && ePonto.getY() < mAltura) {

            int ePox = (ePonto.getY() * mLargura) + ePonto.getX();

            mPixels[ePox] = eCor.getValor();

        }

    }

    public void drawQuad(int eX, int eY, int eLargura, int eAltura, Cor eCor) {

        for (int x = 0; x < eLargura; x++) {

            for (int y = 0; y < eAltura; y++) {

                drawPixel(eX + x, eY + y, eCor);

            }

        }

    }

    public void drawRect(Retangulo eRetangulo, Cor eCor) {

        drawLinha(eRetangulo.getX(), eRetangulo.getY(), eRetangulo.getX2(), eRetangulo.getY(), eCor); // Linha Superior

        drawLinha(eRetangulo.getX(), eRetangulo.getY2(), eRetangulo.getX2(), eRetangulo.getY2(), eCor); // Linha Inferior

        drawLinha(eRetangulo.getX(), eRetangulo.getY(), eRetangulo.getX(), eRetangulo.getY2(), eCor); // Coluna Esquerda

        drawLinha(eRetangulo.getX2(), eRetangulo.getY(), eRetangulo.getX2(), eRetangulo.getY2(), eCor); // Coluna Direita


    }

    public void drawRect(Quadrado eQuadrado, Cor eCor) {

        drawLinha(eQuadrado.getX(), eQuadrado.getY(), eQuadrado.getX2(), eQuadrado.getY(), eCor); // Linha Superior

        drawLinha(eQuadrado.getX(), eQuadrado.getY2(), eQuadrado.getX2(), eQuadrado.getY2(), eCor); // Linha Inferior

        drawLinha(eQuadrado.getX(), eQuadrado.getY(), eQuadrado.getX(), eQuadrado.getY2(), eCor); // Coluna Esquerda

        drawLinha(eQuadrado.getX2(), eQuadrado.getY(), eQuadrado.getX2(), eQuadrado.getY2(), eCor); // Coluna Direita


    }

    public void drawRect_Pintado(Retangulo eRetangulo, Cor eCor) {

        int mX2 = eRetangulo.getX() + eRetangulo.getLargura();

        for (int mX = eRetangulo.getX(); mX < mX2; mX++) {

            int mY2 = eRetangulo.getY() + eRetangulo.getAltura();

            for (int mY = eRetangulo.getY(); mY < mY2; mY++) {
                drawPixel(mX, mY, eCor);

            }


        }


    }

    public void drawRect_Pintado(Quadrado eQuadrado, Cor eCor) {

        int mX2 = eQuadrado.getX() + eQuadrado.getLargura();

        for (int mX = eQuadrado.getX(); mX < mX2; mX++) {

            int mY2 = eQuadrado.getY() + eQuadrado.getAltura();

            for (int mY = eQuadrado.getY(); mY < mY2; mY++) {
                drawPixel(mX, mY, eCor);

            }


        }


    }

    public void drawEsquema_AC(Retangulo eRetangulo, int eEspacador, Cor eCor) {

        drawLinha(eRetangulo.getX() - eEspacador, eRetangulo.getY() - eEspacador, eRetangulo.getX() - eEspacador + eRetangulo.getLargura(), eRetangulo.getY() - eEspacador, eCor); // Linha Superior

        drawLinha(eRetangulo.getX() + eEspacador, eRetangulo.getY() + eRetangulo.getAltura() + eEspacador, eRetangulo.getX() + eEspacador + eRetangulo.getLargura(), eRetangulo.getY() + eRetangulo.getAltura() + eEspacador, eCor); // Linha Inferior

        drawLinha(eRetangulo.getX() - eEspacador, eRetangulo.getY() - eEspacador, eRetangulo.getX() - eEspacador, eRetangulo.getY() - eEspacador + eRetangulo.getAltura(), eCor); // Coluna Esquerda

        drawLinha(eRetangulo.getX() + eRetangulo.getLargura() + eEspacador, eRetangulo.getY() + eEspacador, eRetangulo.getX() + eRetangulo.getLargura() + eEspacador, eRetangulo.getY() + eEspacador + eRetangulo.getAltura(), eCor); // Coluna Direita


    }


    public void drawEsquema_BD(Retangulo eRetangulo, int eEspacador, Cor eCor) {

        drawLinha(eRetangulo.getX() + eEspacador, eRetangulo.getY() - eEspacador, eRetangulo.getX() + eEspacador + eRetangulo.getLargura(), eRetangulo.getY() - eEspacador, eCor); // Linha Superior

        drawLinha(eRetangulo.getX() - eEspacador, eRetangulo.getY() + eRetangulo.getAltura() + eEspacador, eRetangulo.getX() - eEspacador + eRetangulo.getLargura(), eRetangulo.getY() + eRetangulo.getAltura() + eEspacador, eCor); // Linha Inferior


        drawLinha(eRetangulo.getX() - eEspacador, eRetangulo.getY() + eEspacador, eRetangulo.getX() - eEspacador, eRetangulo.getY() + eEspacador + eRetangulo.getAltura(), eCor); // Coluna Esquerda

        drawLinha(eRetangulo.getX() + eRetangulo.getLargura() + eEspacador, eRetangulo.getY() - eEspacador, eRetangulo.getX() + eRetangulo.getLargura() + eEspacador, eRetangulo.getY() - eEspacador + eRetangulo.getAltura(), eCor); // Coluna Direita


    }

    public void drawLinhaHorizontal(int eX, int eY, int eTam, Cor eCor) {

        int eX2 = eX + eTam;

        if (eX2 > 0) {

            for (int x = 0; x < eX2; x++) {

                drawPixel(eX + x, eY, eCor);

            }

        } else {

            for (int x = 0; x >= eX2; x--) {


                drawPixel(eX + x, eY, eCor);


            }

        }

    }

    public void drawLinhaVertical(int eX, int eY, int eTam, Cor eCor) {

        int eY2 = eY + eTam;

        if (eTam > 0) {

            for (int y = 0; y < eY2; y++) {

                drawPixel(eX, eY + y, eCor);

            }
        } else {

            for (int y = 0; y >= eY2; y--) {

                drawPixel(eX, eY + y, eCor);

            }

        }

    }

    public void drawLinha(int eX, int eY, int eX2, int eY2, Cor eCor) {
        drawLinha(new Linha(eX, eY, eX2, eY2), eCor);
    }

    public void drawLinha(Linha eLinha, Cor eCor) {


        int dx = eLinha.getX2() - eLinha.getX1();
        int dy = eLinha.getY2() - eLinha.getY1();

        // System.out.println("\t Dx : " + dx);
        // System.out.println("\t Dy : " + dy);


        if (dx == 0 && dy == 0) {


        } else if (dx == 0 && dy != 0) {

            //  System.out.println("LINHA VERTICAL");

            if (dy > 0) {

                for (int y = 0; y < dy; y++) {

                    drawPixel(eLinha.getX1(), eLinha.getY1() + y, eCor);

                }
            } else {

                for (int y = 0; y >= dy; y--) {

                    drawPixel(eLinha.getX1(), eLinha.getY1() + y, eCor);

                }

            }

        } else if (dx != 0 && dy == 0) {

            // System.out.println("LINHA HORIZONTAL");

            if (dx > 0) {

                for (int x = 0; x < dx; x++) {

                    drawPixel(eLinha.getX1() + x, eLinha.getY1(), eCor);

                }

            } else {

                for (int x = 0; x >= dx; x--) {


                    drawPixel(eLinha.getX1() + x, eLinha.getY1(), eCor);


                }

            }


        } else {

            //  System.out.println("LINHA DIAGONAL");


            // System.out.println("\t X : " + eLinha.getX1());
            // System.out.println("\t Y : " + eLinha.getY1());


            //  System.out.println("\t X2 : " + eLinha.getX2());
            //  System.out.println("\t Y2 : " + eLinha.getY2());


            int d = 0;

            dx = Math.abs(eLinha.getX2() - eLinha.getX1());
            dy = Math.abs(eLinha.getY2() - eLinha.getY1());

            int dx2 = 2 * dx; // slope scaling factors to
            int dy2 = 2 * dy; // avoid floating point

            int ix = eLinha.getX1() < eLinha.getX2() ? 1 : -1; // increment direction
            int iy = eLinha.getY1() < eLinha.getY2() ? 1 : -1;

            int x = eLinha.getX1();
            int y = eLinha.getY1();

            if (dx >= dy) {
                while (true) {
                    drawPixel(x, y, eCor);
                    if (x == eLinha.getX2())
                        break;
                    x += ix;
                    d += dy2;
                    if (d > dx) {
                        y += iy;
                        d -= dx2;
                    }
                }
            } else {
                while (true) {
                    drawPixel(x, y, eCor);
                    if (y == eLinha.getY2())
                        break;
                    y += iy;
                    d += dx2;
                    if (d > dy) {
                        x += ix;
                        d -= dy2;
                    }
                }
            }
            // System.out.println("-------------------------------------");

        }


    }


    public void drawLinha(int eX, int eY, int eX2, int eY2, int eRotacao, Cor eCor) {


        double c = Math.cos(eRotacao * 3.14 / 180);
        double s = Math.sin(eRotacao * 3.14 / 180);
        int oX = (int) Math.floor(eX * c + eY * s);
        int oY = (int) Math.floor(-eX * s + eY * c);
        int oX2 = (int) Math.floor(eX2 * c + eY2 * s);
        int oY2 = (int) Math.floor(-eX2 * s + eY2 * c);

        drawLinha(oX, oY, oX2, oY2, eCor);

    }


    public void drawTriangulo(Triangulo eTriangulo, Cor eCor) {

        drawLinha(eTriangulo.getA(), eCor);
        drawLinha(eTriangulo.getB(), eCor);
        drawLinha(eTriangulo.getC(), eCor);


    }

    public void drawTriangulo_Pintado(Triangulo eTriangulo, Cor eCor) {


        Ponto vTmp;
        Ponto vt1 = eTriangulo.getPonto_A();
        Ponto vt2 = eTriangulo.getPonto_B();
        Ponto vt3 = eTriangulo.getPonto_C();

        if (vt1.getY() > vt2.getY()) {
            vTmp = vt1;
            vt1 = vt2;
            vt2 = vTmp;
        }

        /* TRIANGULO COM v1.y <= v2.y */
        if (vt1.getY() > vt3.getY()) {
            vTmp = vt1;
            vt1 = vt3;
            vt3 = vTmp;
        }

        /* TRIANGULO COM v1.y <= v2.y E v1.y <= v3.y ENTAO TESTA v2 vs. v3 */
        if (vt2.getY() > vt3.getY()) {
            vTmp = vt2;
            vt2 = vt3;
            vt3 = vTmp;
        }


        /* PINTAR v1.y <= v2.y <= v3.y */
        /* check for trivial case of bottom-flat triangle */
        if (vt2.getY() == vt3.getY()) {

          //  fillBottomFlatTriangle(vt1, vt2, vt3, eCor);

            float invslope1 = (vt2.getX() - vt1.getX()) / (vt2.getY() - vt1.getY());
            float invslope2 = (vt3.getX() - vt1.getX()) / (vt3.getY() - vt1.getY());

            float curx1 = vt1.getX();
            float curx2 = vt1.getX();

            for (int scanlineY = vt1.getY(); scanlineY <= vt2.getY(); scanlineY++) {
                drawLinha((int) curx1, scanlineY, (int) curx2, scanlineY, eCor);
                curx1 += invslope1;
                curx2 += invslope2;
            }

        }
        /* check for trivial case of top-flat triangle */
        else if (vt1.getY() == vt2.getY()) {

          //  fillTopFlatTriangle(vt1, vt2, vt3,eCor);

            float invslope1 = (vt3.getX() - vt1.getX()) / (vt3.getY() - vt1.getY());
            float invslope2 = (vt3.getX() - vt2.getX()) / (vt3.getY() - vt2.getY());

            float curx1 = vt3.getX();
            float curx2 = vt3.getX();

            for (int scanlineY = vt3.getY(); scanlineY > vt1.getY(); scanlineY--) {
                drawLinha((int) curx1, scanlineY, (int) curx2, scanlineY,eCor);
                curx1 -= invslope1;
                curx2 -= invslope2;
            }

        } else {
            /* general case - split the triangle in a topflat and bottom-flat one */
            Ponto v4 = new Ponto((int) (vt1.getX() + ((float) (vt2.getY() - vt1.getY()) / (float) (vt3.getY() - vt1.getY())) * (vt3.getX() - vt1.getX())), vt2.getY());

           // fillBottomFlatTriangle(vt1, vt2, v4, eCor);

            float invslope1 = (vt2.getX() - vt1.getX()) / (vt2.getY() - vt1.getY());
            float invslope2 = (v4.getX() - vt1.getX()) / (v4.getY() - vt1.getY());

            float curx1 = vt1.getX();
            float curx2 = vt1.getX();

            for (int scanlineY = vt1.getY(); scanlineY <= vt2.getY(); scanlineY++) {
                drawLinha((int) curx1, scanlineY, (int) curx2, scanlineY, eCor);
                curx1 += invslope1;
                curx2 += invslope2;
            }



           // fillTopFlatTriangle(vt2, v4, vt3,eCor);
            float invslope3 = (vt3.getX() - vt2.getX()) / (vt3.getY() - vt2.getY());
            float invslope4 = (vt3.getX() - v4.getX()) / (vt3.getY() - v4.getY());

            float curx3 = vt3.getX();
            float curx4 = vt3.getX();

            for (int scanlineY = vt3.getY(); scanlineY > vt2.getY(); scanlineY--) {
                drawLinha((int) curx3, scanlineY, (int) curx4, scanlineY,eCor);
                curx3 -= invslope3;
                curx4 -= invslope4;
            }
        }


    }



    public void adicionarLuz(Ponto ePonto, Cor eCor, int eTamanho) {

        mLuzes.adicionar(new Luz(ePonto, eCor, eTamanho));

    }

    public void bloquear_Rect(Retangulo eRetangulo) {

        int mX2 = eRetangulo.getX() + eRetangulo.getLargura();

        for (int mX = eRetangulo.getX(); mX < mX2; mX++) {

            int mY2 = eRetangulo.getY() + eRetangulo.getAltura();

            for (int mY = eRetangulo.getY(); mY < mY2; mY++) {
                bloquearPixel(mX, mY);
            }


        }


    }

    public void bloquear_Rect(Quadrado eQuadrado) {

        int mX2 = eQuadrado.getX() + eQuadrado.getLargura();

        for (int mX = eQuadrado.getX(); mX < mX2; mX++) {

            int mY2 = eQuadrado.getY() + eQuadrado.getAltura();

            for (int mY = eQuadrado.getY(); mY < mY2; mY++) {
                bloquearPixel(mX, mY);
            }


        }


    }

    public void iluminar() {

        //  System.out.println("Iluminar");

        for (mLuzes_Iterador.iniciar(); mLuzes_Iterador.continuar(); mLuzes_Iterador.proximo()) {

            //   System.out.println(" -->> " + mLuzes_Iterador.getValor().getCor().toString());


            int eX_Inicio = mLuzes_Iterador.getValor().getX_Inicio();
            int eX_Fim = mLuzes_Iterador.getValor().getX_Fim();

            int eY_Inicio = mLuzes_Iterador.getValor().getY_Inicio();
            int eY_Fim = mLuzes_Iterador.getValor().getY_Fim();

            Cor eCor = mLuzes_Iterador.getValor().getCor();
            int eTam = mLuzes_Iterador.getValor().getTamanho();


            //  System.out.println("X ->> " + eX_Inicio + " :: " + eX_Fim);
            //  System.out.println("Y ->> " + eY_Inicio + " :: " + eY_Fim);

            for (int aX = eX_Inicio; aX < eX_Fim; aX++) {

                for (int aY = eY_Inicio; aY < eY_Fim; aY++) {


                    if (getPodeIluminar(aX, aY)) {
                        drawLumnos(aX, aY, eCor);
                    }

                }
            }


        }


        for (int i = 0; i < mPixels.length; i++) {

            //  float r = ((mMapaDeLuz[i] >> 16) & 0xff) / 255;
            //  float g = ((mMapaDeLuz[i] >> 8) & 0xff) / 255;
            //  float b = ((mMapaDeLuz[i]) & 0xff) / 255;


            if (mMapaDeAcao[i]) {


                int i1 = mPixels[i];
                int i2 = mMapaDeLuz[i];

                int c = combinar(i1, i2, 50);


                //  mPixels[i] = c;

                //  mPixels[i] = ((int)(((mPixels[i] >> 16) & 0xff) * r )| (int) ((mPixels[i] >> 8 & 0xff) * g)  | (int) ((mPixels[i] & 0xff) * b ));
                //   mPixels[i] = mMapaDeLuz[i];

            }
        }


    }


    public int combinar(int i1, int i2, int ratio) {

        float diff = (float) ratio / 255.0F;

        float iRatio = 1.0f - diff;

        int a1 = (i1 >> 24 & 0xff);
        int r1 = ((i1 & 0xff0000) >> 16);
        int g1 = ((i1 & 0xff00) >> 8);
        int b1 = (i1 & 0xff);

        int a2 = (i2 >> 24 & 0xff);
        int r2 = ((i2 & 0xff0000) >> 16);
        int g2 = ((i2 & 0xff00) >> 8);
        int b2 = (i2 & 0xff);

        int a = (int) ((a1 * iRatio) + (a2 * ratio));
        int r = (int) ((r1 * iRatio) + (r2 * ratio));
        int g = (int) ((g1 * iRatio) + (g2 * ratio));
        int b = (int) ((b1 * iRatio) + (b2 * ratio));

        return (r << 24 | g << 16 | b << 8 | a);

    }

    public void setOpacidade(int eOpacidade) {
        mAmbiente.setAlpha(eOpacidade);
    }

    public void setAmbiente(int r, int g, int b) {
        mAmbiente.setRed(r);
        mAmbiente.setGreen(g);
        mAmbiente.setBlue(b);
    }

    public void setAmbiente_Vermelho(int r) {
        mAmbiente.setRed(r);
    }

    public void setAmbiente_Verde(int g) {
        mAmbiente.setGreen(g);
    }

    public void setAmbiente_Azul(int b) {
        mAmbiente.setBlue(b);
    }
}
