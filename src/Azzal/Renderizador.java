package Azzal;

import Azzal.Formatos.*;
import Azzal.Utils.Cor;
import Azzal.Utils.Espelho;
import Azzal.Utils.Luz;
import Azzal.Utils.TransformadorDeCor;
import Luan.Iterador;
import Luan.Lista;
import Luan.Par;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

public class Renderizador {

    private int[] mPixels;
    private boolean[] mMapaDeAcao;
    private int[] mMapaDeLuz;

    private int mLargura;
    private int mAltura;

    private Lista<Luz> mLuzes;
    private Iterador<Luz> mLuzes_Iterador;

    private boolean mAmbiente_Existe;
    private Cor mAmbiente;


    public Renderizador(BufferedImage eImagem) {


        mPixels = ((DataBufferInt) eImagem.getRaster().getDataBuffer()).getData();

        mLargura = eImagem.getWidth();
        mAltura = eImagem.getHeight();

        mMapaDeAcao = new boolean[mLargura * mAltura];
        mMapaDeLuz = new int[mLargura * mAltura];

        mLuzes = new Lista<Luz>();
        mLuzes_Iterador = new Iterador<Luz>(mLuzes);


        mAmbiente_Existe = false;

    }

    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public void limpar(Color eCor) {

        int eCorLimpar = eCor.getRGB();

        for (int i = 0; i < mPixels.length; i++) {
            mPixels[i] = eCorLimpar;
        }


        mLuzes.limpar();


        for (int i = 0; i < mPixels.length; i++) {
            mMapaDeAcao[i] = true;
            mMapaDeLuz[i] = 0;
        }


    }

    public void drawImagem(int x, int y, BufferedImage img) {

        int eTam = mPixels.length;

        int[] img_Pixels = ((DataBufferInt) img.getRaster().getDataBuffer()).getData();

        for (int yi = 0; yi < img.getHeight(); yi++) {
            for (int xi = 0; xi < img.getWidth(); xi++) {


                int rx = x + xi;
                int ry = y + yi;

                int ePox = (ry * mLargura) + rx;

                if (ePox >= 0 && ePox < eTam) {
                    if (rx >= 0 && rx < mLargura && ry >= 0 && ry < mAltura) {
                        mPixels[ePox] = img.getRGB(xi, yi);
                        // mPixels[ePox] = img_Pixels[(yi*img.getWidth() )+ xi];

                    }
                }
            }
        }

    }

    public void limpar(Cor eCor) {


        for (int i = 0; i < mPixels.length; i++) {
            mPixels[i] = eCor.getValor();
        }


        mLuzes.limpar();


        for (int i = 0; i < mPixels.length; i++) {
            mMapaDeAcao[i] = true;
            mMapaDeLuz[i] = 0;
        }


    }

    public void drawPixel(int eX, int eY, Cor eCor) {

        int iCor = eCor.getValor();

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            mPixels[ePox] = iCor;

        }


    }

    public void drawPixelBruto(int eX, int eY, int eCor) {

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            mPixels[ePox] = eCor;

        }


    }


    public int getPixelBruto(int eX, int eY) {

        int e = 0;

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            e = mPixels[ePox];


        }

        return e;
    }


    public Cor getPixel(int eX, int eY) {
        Cor eCor = new Cor(0, 0, 0);
        eCor.setAlpha(0);

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            Color eColor = new Color(mPixels[ePox]);
            eCor.setAlpha(eColor.getAlpha());

            eCor.setRed(eColor.getRed());
            eCor.setGreen(eColor.getGreen());
            eCor.setBlue(eColor.getBlue());

        }

        return eCor;
    }

    public void drawLumnos(int eX, int eY, Cor eCor) {

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            int i1 = mMapaDeLuz[ePox];

            Cor eOriginal = Cor.getRGB(new Color(i1));
            Cor eNova = eCor;

            Cor eFinal = eOriginal;


            if (eNova.getAlpha() == 255) {
                eFinal = eNova;
            }


            int d = 255 * (eOriginal.getAlpha() + eNova.getAlpha()) - eOriginal.getAlpha() * eNova.getAlpha();
            int r = (eOriginal.getRed() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getRed()) / d;
            int g = (eOriginal.getGreen() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getGreen()) / d;
            int b = (eOriginal.getBlue() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getBlue()) / d;

            int a = d / 255;

            eFinal.setRed(r);
            eFinal.setGreen(g);
            eFinal.setBlue(b);
            eFinal.setAlpha(a);


            mMapaDeLuz[ePox] = eFinal.getValor();


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

    public void drawQuad(Quadrado eQuadrado, Cor eCor) {

        drawLinha(eQuadrado.getX(), eQuadrado.getY(), eQuadrado.getX2(), eQuadrado.getY(), eCor); // Linha Superior

        drawLinha(eQuadrado.getX(), eQuadrado.getY2(), eQuadrado.getX2(), eQuadrado.getY2(), eCor); // Linha Inferior

        drawLinha(eQuadrado.getX(), eQuadrado.getY(), eQuadrado.getX(), eQuadrado.getY2(), eCor); // Coluna Esquerda

        drawLinha(eQuadrado.getX2(), eQuadrado.getY(), eQuadrado.getX2(), eQuadrado.getY2(), eCor); // Coluna Direita


    }

    public void drawQuad(int px, int py, int tamanho, Cor eCor) {

        drawLinha(px, py, px + tamanho, py, eCor); // Linha Superior

        drawLinha(px, py + tamanho, px + tamanho, py + tamanho, eCor); // Linha Inferior

        drawLinha(px, py, px, py + tamanho, eCor); // Coluna Esquerda

        drawLinha(px + tamanho, py, px + tamanho, py + tamanho, eCor); // Coluna Direita


    }


    public void drawQuad_Pintado(Quadrado eQuadrado, Cor eCor) {

        int eX = eQuadrado.getX();
        int eY = eQuadrado.getY();

        int eX2 = eQuadrado.getX2();
        int eY2 = eQuadrado.getY2();

        for (int x = eX; x < eX2; x++) {
            for (int y = eY; y < eY2; y++) {
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

    public void drawRect(int px, int py, int largura, int altura, Cor eCor) {

        drawLinha(px, py, px + largura, py, eCor); // Linha Superior

        drawLinha(px, py + altura, px + largura, py + altura, eCor); // Linha Inferior

        drawLinha(px, py, px, py + altura, eCor); // Coluna Esquerda

        drawLinha(px + largura, py, px + largura, py + altura, eCor); // Coluna Direita


    }

    public void drawRect(Quadrado eQuadrado, Cor eCor) {

        drawLinha(eQuadrado.getX(), eQuadrado.getY(), eQuadrado.getX2(), eQuadrado.getY(), eCor); // Linha Superior

        drawLinha(eQuadrado.getX(), eQuadrado.getY2(), eQuadrado.getX2(), eQuadrado.getY2(), eCor); // Linha Inferior

        drawLinha(eQuadrado.getX(), eQuadrado.getY(), eQuadrado.getX(), eQuadrado.getY2(), eCor); // Coluna Esquerda

        drawLinha(eQuadrado.getX2(), eQuadrado.getY(), eQuadrado.getX2(), eQuadrado.getY2(), eCor); // Coluna Direita


    }

    public void drawRect_Border(Retangulo eRetangulo, int l, Cor eCor) {

        for (int i = 0; i < l; i++) {

            drawRect(new Retangulo(eRetangulo.getX() + (i), eRetangulo.getY() + (i), eRetangulo.getLargura() - (2 * i), eRetangulo.getAltura() - (2 * i)), eCor);

        }


    }

    public void drawRect_Pintado(Retangulo eRetangulo, Cor eCor) {

        int mX2 = eRetangulo.getX() + eRetangulo.getLargura();

        int iCor = eCor.getValor();

        for (int mX = eRetangulo.getX(); mX < mX2; mX++) {

            int mY2 = eRetangulo.getY() + eRetangulo.getAltura();

            for (int mY = eRetangulo.getY(); mY < mY2; mY++) {
                drawPixelBruto(mX, mY, iCor);
            }


        }


    }

    public void drawRect_Pintado(int px, int py, int largura, int altura, Cor eCor) {

        int mX2 = px + largura;

        int iCor = eCor.getValor();

        for (int mX = px; mX < mX2; mX++) {

            int mY2 = py + altura;

            for (int mY = py; mY < mY2; mY++) {
                drawPixelBruto(mX, mY, iCor);
            }


        }


    }

    public void drawRect_Pintado(Quadrado eQuadrado, Cor eCor) {

        int mX2 = eQuadrado.getX() + eQuadrado.getLargura();
        int iCor = eCor.getValor();

        for (int mX = eQuadrado.getX(); mX < mX2; mX++) {

            int mY2 = eQuadrado.getY() + eQuadrado.getAltura();

            for (int mY = eQuadrado.getY(); mY < mY2; mY++) {
                drawPixelBruto(mX, mY, iCor);
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

    public void drawCirculoCentralizado(int eX, int eY, int eRaio, Cor eCor) {
        drawCirculo(eX - eRaio, eY - eRaio, eRaio, eCor);
    }

    public void drawCirculoCentralizado(int eX, int eY, Circulo eCirculo, Cor eCor) {
        drawCirculo(eX - eCirculo.getRaio(), eY - eCirculo.getRaio(), eCirculo.getRaio(), eCor);
    }

    public void drawCirculo(Circulo eCirculo, Cor eCor) {
        drawCirculo(eCirculo.getX(), eCirculo.getY(), eCirculo.getRaio(), eCor);
    }

    public void drawCirculo(int x1, int y1, int raio, Cor eCor) {

        int x = 0;
        int y = raio;
        double d = 1.25 - (double) raio;

        int xi = x1 + raio;
        int yi = y1 + raio;

        while (x < y) {
            if (d < 0) {
                d = d + 2 * x + 3;
                x += 1;
            } else {
                d = d + 2 * (x - y) + 5;
                x += 1;
                y -= 1;
            }
            drawPixel(xi + x, yi + y, eCor);
            drawPixel(xi + x, yi - y, eCor);
            drawPixel(xi - x, yi + y, eCor);
            drawPixel(xi - x, yi - y, eCor);

            drawPixel(xi + y, yi + x, eCor);
            drawPixel(xi + y, yi - x, eCor);
            drawPixel(xi - y, yi + x, eCor);
            drawPixel(xi - y, yi - x, eCor);

        }

    }

    public void drawCirculo_Pintado(Circulo eCirculo, Cor eCor) {

        int x = eCirculo.getRaio();
        int y = 0;
        int xChange = 1 - (eCirculo.getRaio() << 1);
        int yChange = 0;
        int radiusError = 0;

        int xi = eCirculo.getX() + eCirculo.getRaio();
        int yi = eCirculo.getY() + eCirculo.getRaio();


        while (x >= y) {
            for (int i = xi - x; i <= xi + x; i++) {

                drawPixel(i, yi + y, eCor);
                drawPixel(i, yi - y, eCor);

            }
            for (int i = xi - y; i <= xi + y; i++) {

                drawPixel(i, yi + x, eCor);
                drawPixel(i, yi - x, eCor);
            }

            y++;
            radiusError += yChange;
            yChange += 2;
            if (((radiusError << 1) + xChange) > 0) {
                x--;
                radiusError += xChange;
                xChange += 2;
            }
        }
    }

    public void drawOval_Pintado(Oval eOval, Cor eCor) {
        int x = eOval.getRaioLargura();
        int x2 = eOval.getRaioAltura();

        int y = 0;
        int xChange = 1 - (eOval.getRaioLargura() << 1);
        int yChange = 0;
        int radiusError = 0;

        int xi = eOval.getX() + eOval.getRaioLargura();
        int yi = eOval.getY() + eOval.getRaioAltura();


        while (x >= y) {
            for (int i = xi - x; i <= xi + x; i++) {

                drawPixel(i, yi + y, eCor);
                drawPixel(i, yi - y, eCor);

            }
            for (int i = xi - y; i <= xi + y; i++) {

                drawPixel(i, yi + x2, eCor);
                drawPixel(i, yi - x2, eCor);
            }

            y++;
            radiusError += yChange;
            yChange += 2;
            if (((radiusError << 1) + xChange) > 0) {
                x--;
                radiusError += xChange;
                xChange += 2;
            }
        }
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

        // int eY2 = eY + eTam;
        int eY2 = eTam;

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

                for (int y = 0; y <= dy; y++) {

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
                drawLinha((int) curx1, scanlineY, (int) curx2, scanlineY, eCor);
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
                drawLinha((int) curx3, scanlineY, (int) curx4, scanlineY, eCor);
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

    public int trans(int i1, int i2) {


        int a1 = (i1 >> 24 & 0xff);
        int r1 = ((i1 & 0xff0000) >> 16);
        int g1 = ((i1 & 0xff00) >> 8);
        int b1 = (i1 & 0xff);

        int a2 = (i2 >> 24 & 0xff);
        int r2 = ((i2 & 0xff0000) >> 16);
        int g2 = ((i2 & 0xff00) >> 8);
        int b2 = (i2 & 0xff);

        float alpha_A = 255.0F / (float) a1;
        float alpha_B = 255.0F / (float) i2;


        int a = (int) ((a1 * alpha_A) + (a2 * alpha_B));
        int r = (int) ((r1 * alpha_A) + (r2 * alpha_B));
        int g = (int) ((g1 * alpha_A) + (g2 * alpha_B));
        int b = (int) ((b1 * alpha_A) + (b2 * alpha_B));

        if (a >= 255) {
            a -= 255;
        }
        if (r >= 255) {
            r -= 255;
        }
        if (g >= 255) {
            g -= 255;
        }
        if (b >= 255) {
            b -= 255;
        }

        return (r << 24 | g << 16 | b << 8 | a);

    }

    public void iluminar() {

        //  System.out.println("Iluminar");


        if (mAmbiente_Existe) {

            int eAmb = 0;

            for (int i = 0; i < mPixels.length; i++) {

                if (mMapaDeAcao[i]) {
                    mMapaDeLuz[i] = mAmbiente.getValor();
                }

            }

            //System.out.println("Ambiente : " + eAmb);
            System.out.println("Ambiente com Cor = { Alfa : " + mAmbiente.getAlpha() + " } ");

        }


        for (mLuzes_Iterador.iniciar(); mLuzes_Iterador.continuar(); mLuzes_Iterador.proximo()) {

            System.out.println(" -->> " + mLuzes_Iterador.getValor().getCor().toString());


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
                        //   drawLumnos(aX, aY, eCor);
                    }

                }
            }


        }

        int eTrocar = 0;

        for (int i = 0; i < mPixels.length; i++) {

            if (mMapaDeAcao[i]) {


                int i1 = mPixels[i];
                int i2 = mMapaDeLuz[i];

                Cor eOriginal = Cor.getRGB(new Color(i1));
                Cor eNova = Cor.getRGB(new Color(i2));

                Cor eFinal = eOriginal;

                if (eNova.getAlpha() == 255) {
                    eFinal = eNova;
                    eTrocar += 1;
                }


                int d = 255 * (eOriginal.getAlpha() + eNova.getAlpha()) - eOriginal.getAlpha() * eNova.getAlpha();
                int r = (eOriginal.getRed() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getRed()) / d;
                int g = (eOriginal.getGreen() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getGreen()) / d;
                int b = (eOriginal.getBlue() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getBlue()) / d;

                int a = d / 255;

                eFinal.setRed(r);
                eFinal.setGreen(g);
                eFinal.setBlue(b);
                eFinal.setAlpha(a);


                // mPixels[i] = eFinal.getValor();


            }
        }

        System.out.println("Trocar : " + eTrocar);


    }


    public void iluminara() {

        //  System.out.println("Iluminar");

        int eAmb = 0;

        if (mAmbiente_Existe) {

            for (int i = 0; i < mPixels.length; i++) {

                Cor eOriginal = Cor.getRGB(new Color(mPixels[i]));

                Cor eFinal = eOriginal;

                if (eOriginal.getAlpha() == 0 && mAmbiente.getAlpha() == 255) {
                    eFinal = mAmbiente;
                    eAmb += 1;
                }

                if (mAmbiente.getAlpha() == 0) {
                    eFinal = eOriginal;
                }


                int d = 255 * (eOriginal.getAlpha() + mAmbiente.getAlpha()) - eOriginal.getAlpha() * mAmbiente.getAlpha();
                int r = (eOriginal.getRed() * eOriginal.getAlpha() * (255 - mAmbiente.getAlpha()) + 255 * mAmbiente.getAlpha() * mAmbiente.getRed()) / d;
                int g = (eOriginal.getGreen() * eOriginal.getAlpha() * (255 - mAmbiente.getAlpha()) + 255 * mAmbiente.getAlpha() * mAmbiente.getGreen()) / d;
                int b = (eOriginal.getBlue() * eOriginal.getAlpha() * (255 - mAmbiente.getAlpha()) + 255 * mAmbiente.getAlpha() * mAmbiente.getBlue()) / d;

                int a = d / 255;

                eFinal.setRed(r);
                eFinal.setGreen(g);
                eFinal.setBlue(b);
                eFinal.setAlpha(a);


                mPixels[i] = eFinal.getValor();

            }

            //System.out.println("Ambiente : " + eAmb);
            // System.out.println("Ambiente Cor = { Alfa : " + mAmbiente.getAlpha() + " } ");

        }


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

        eAmb = 0;

        for (int i = 0; i < mPixels.length; i++) {

            //  float r = ((mMapaDeLuz[i] >> 16) & 0xff) / 255;
            //  float g = ((mMapaDeLuz[i] >> 8) & 0xff) / 255;
            //  float b = ((mMapaDeLuz[i]) & 0xff) / 255;


            if (mMapaDeAcao[i]) {


                int i1 = mPixels[i];
                int i2 = mMapaDeLuz[i];

                //  int c = combinar(i1, i2, 50);

                Cor eOriginal = Cor.getRGB(new Color(i1));
                Cor eNova = Cor.getRGB(new Color(i2));

                Cor eFinal = eOriginal;

                if (eOriginal.getAlpha() == 0 && eNova.getAlpha() == 255) {
                    eFinal = eNova;
                    eAmb += 1;
                }

                if (eNova.getAlpha() == 0) {
                    eFinal = eOriginal;
                }


                int d = 255 * (eOriginal.getAlpha() + eNova.getAlpha()) - eOriginal.getAlpha() * eNova.getAlpha();
                int r = (eOriginal.getRed() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getRed()) / d;
                int g = (eOriginal.getGreen() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getGreen()) / d;
                int b = (eOriginal.getBlue() * eOriginal.getAlpha() * (255 - eNova.getAlpha()) + 255 * eNova.getAlpha() * eNova.getBlue()) / d;

                int a = d / 255;

                eFinal.setRed(r);
                eFinal.setGreen(g);
                eFinal.setBlue(b);
                eFinal.setAlpha(a);


                //   mPixels[i] = eFinal.getValor();

                //  mPixels[i] = ((int)(((mPixels[i] >> 16) & 0xff) * r )| (int) ((mPixels[i] >> 8 & 0xff) * g)  | (int) ((mPixels[i] & 0xff) * b ));
                //   mPixels[i] = mMapaDeLuz[i];

            }
        }

        System.out.println("Ambiente : " + eAmb);
        System.out.println("Ambiente Cor = { Alfa : " + mAmbiente.getAlpha() + " } ");


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


    public void blend(int eX, int eY, int eL, int eA, Cor eCor) {

        for (int x = eX; x < (eX + eL); x++) {

            for (int y = eY; y < (eY + eA); y++) {


                Cor eOriginal = getPixel(x, y);

                Cor eFinal = eOriginal;

                if (eOriginal.getAlpha() == 0 || eCor.getAlpha() == 255)
                    eFinal = eCor;

                if (eCor.getAlpha() == 0)
                    eFinal = eOriginal;

                int d = 255 * (eOriginal.getAlpha() + eCor.getAlpha()) - eOriginal.getAlpha() * eCor.getAlpha();
                int r = (eOriginal.getRed() * eOriginal.getAlpha() * (255 - eCor.getAlpha()) + 255 * eCor.getAlpha() * eCor.getRed()) / d;
                int g = (eOriginal.getGreen() * eOriginal.getAlpha() * (255 - eCor.getAlpha()) + 255 * eCor.getAlpha() * eCor.getGreen()) / d;
                int b = (eOriginal.getBlue() * eOriginal.getAlpha() * (255 - eCor.getAlpha()) + 255 * eCor.getAlpha() * eCor.getBlue()) / d;

                int a = d / 255;

                eFinal.setRed(r);
                eFinal.setGreen(g);
                eFinal.setBlue(b);
                eFinal.setAlpha(a);


                drawPixel(x, y, eFinal);

            }


        }

    }

    public void setOpacidade(int eOpacidade) {
        if (!mAmbiente_Existe) {
            mAmbiente = new Cor(0, 0, 0);
            mAmbiente_Existe = true;
            mAmbiente.setAlpha(eOpacidade);
        } else {
            mAmbiente.setAlpha(eOpacidade);
        }
    }

    public void setAmbiente(int r, int g, int b) {
        if (!mAmbiente_Existe) {
            mAmbiente = new Cor(r, g, b);
            mAmbiente.setAlpha(255);
            mAmbiente_Existe = true;
        } else {
            mAmbiente.setRed(r);
            mAmbiente.setGreen(g);
            mAmbiente.setBlue(b);
        }
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

    public void espelhar(Ponto ePonto, Retangulo eArea) {

        int mX2 = eArea.getX() + eArea.getLargura();

        int x = 0;
        for (int mX = eArea.getX(); mX < mX2; mX++) {

            int mY2 = eArea.getY() + eArea.getAltura();

            int y = 0;

            for (int mY = eArea.getY(); mY < mY2; mY++) {


                if (mX >= 0 && mX < mLargura && mY >= 0 && mY < mAltura) {

                    int ePox = (mY * mLargura) + mX;

                    int eCopia = mPixels[ePox];

                    int oX = ePonto.getX() + x;
                    int oY = ePonto.getY() + y;

                    if (oX >= 0 && oX < mLargura && oY >= 0 && oY < mAltura) {

                        int oPox = (oY * mLargura) + oX;

                        mPixels[oPox] = eCopia;

                    }


                }

                y += 1;
            }

            x += 1;
        }

    }


    public void exportar(String eLocal) {

        BufferedImage mExportar = new BufferedImage(mLargura, mAltura, BufferedImage.TYPE_INT_ARGB);

        int[] mExportar_Pixels = ((DataBufferInt) mExportar.getRaster().getDataBuffer()).getData();

        int i = 0;
        for (int e : mPixels) {
            mExportar_Pixels[i] = e;
            i += 1;
        }

        try {
            ImageIO.write(mExportar, "png", new File(eLocal));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void exportarQuadro(Retangulo eQuadro, String eLocal) {


        BufferedImage mExportar = new BufferedImage(eQuadro.getLargura(), eQuadro.getAltura(), BufferedImage.TYPE_INT_ARGB);

        int[] mExportar_Pixels = ((DataBufferInt) mExportar.getRaster().getDataBuffer()).getData();

        int ri = 0;

        for (int y = eQuadro.getY(); y < eQuadro.getY2(); y++) {

            for (int x = eQuadro.getX(); x < eQuadro.getX2(); x++) {

                int ePox = (y * mLargura) + x;

                mExportar_Pixels[ri] = mPixels[ePox];
                ri += 1;

            }
        }


        try {
            ImageIO.write(mExportar, "png", new File(eLocal));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void receberDe(Renderizador eOutro, Ponto eLocal, Retangulo eArea) {


        int px = eArea.getX();
        int py = eArea.getY();

        int tix = eArea.getX() + eArea.getLargura();
        int tiy = eArea.getY() + eArea.getAltura();

        int riy = eLocal.getY();

        for (int piy = py; piy < tiy; piy++) {

            int rix = eLocal.getX();

            for (int pix = px; pix < tix; pix++) {

                drawPixelBruto(rix, riy, eOutro.getPixelBruto(pix, piy));

                rix += 1;
            }

            riy += 1;
        }


    }


    public void drawPixels(int xInicial, int yInicial, int[] mConteudo, int ml, int ma) {

        int pYInicial = yInicial;

        int geral = 0;

        for (int y = 0; y < ma; y++) {

            int pXInicial = xInicial;

            for (int x = 0; x < ml; x++) {


                if (pXInicial >= 0 && pXInicial < mLargura && pYInicial >= 0 && pYInicial < mAltura) {

                    int ePox = (pYInicial * mLargura) + pXInicial;

                    mPixels[ePox] = mConteudo[geral];

                }


                geral += 1;

                pXInicial += 1;
            }
            pYInicial += 1;
        }


    }


    public void drawArco(Circulo eCirculo, int l1, int l2, Cor eCor, boolean isDebug) {


        l1 = valide(l1);
        l2 = valide(l2);


        if (l1 > l2) {
            int l3 = l2;
            l2 = l1;
            l1 = l3;
        }

        boolean temQ1 = false;
        boolean temQ2 = false;
        boolean temQ3 = false;
        boolean temQ4 = false;


        if ((l1 >= 0 && l1 <= 90) || (l2 >= 0 && l2 <= 90)) {
            temQ1 = true;
        }
        if ((l1 >= 90 && l1 <= 180) || (l2 >= 90 && l2 <= 180)) {
            temQ2 = true;
        }

        if ((l1 >= 180 && l1 <= 270) || (l2 >= 180 && l2 <= 270)) {
            temQ3 = true;
        }
        if ((l1 >= 270 && l1 <= 360) || (l2 >= 270 && l2 <= 360)) {
            temQ4 = true;
        }

        double v1 = ((double) l1) * Math.PI / 180.0;
        double v2 = ((double) l2) * Math.PI / 180.0;

        Ponto pCentro = eCirculo.getCentro();

        int eRaio = eCirculo.getRaio() + 5;

        Ponto q1 = new Ponto((int) ((double) pCentro.getX() + ((double) eRaio) * Math.cos(v1)), (int) ((double) pCentro.getY() + ((double) eRaio) * Math.sin(v1)));
        Ponto q2 = new Ponto((int) ((double) pCentro.getX() + ((double) eRaio) * Math.cos(v2)), (int) ((double) pCentro.getY() + ((double) eRaio) * Math.sin(v2)));


        if (isDebug) {
            //   drawRect_Pintado(new Retangulo(q1.getX(), q1.getY(), 5, 5), Cor.getHexCor("#FF7F50"));
            //  drawRect_Pintado(new Retangulo(q2.getX(), q2.getY(), 5, 5), Cor.getHexCor("#FF7F50"));
        }

        int x0 = 0;
        int x1 = 0;
        int y0 = 0;
        int y1 = 0;


        if (q1.getX() < q2.getX()) {
            x0 = q1.getX();
            x1 = q2.getX();
        } else {
            x0 = q2.getX();
            x1 = q1.getX();
        }

        if (q1.getY() < q2.getY()) {
            y0 = q1.getY();
            y1 = q2.getY();
        } else {
            y0 = q2.getY();
            y1 = q1.getY();
        }


        if (isDebug) {
            drawRect_Pintado(new Retangulo(x0, y0, 5, 5), Cor.getHexCor("#c62828"));
            drawRect_Pintado(new Retangulo(x1, y1, 5, 5), Cor.getHexCor("#4169E1"));
        }


        int pX = x0;
        int pY = y0;

        int eLar = (x1 - x0);
        int eAlt = (y1 - y0);


        //   System.out.println("Lar : " + eLar);
        //  System.out.println("Alt : " + eAlt);


        //eRefString.set("X = " + pX + " Y = " + pY + " Lar = " + eLar + " Alt = " + eAlt);

        //   if (l1 <= 90 && l2 >= 90) {
        // System.out.println("+90");

        //  int fy = pY + eAlt;
        // if (fy < eFimY) {
        //    int dif = eFimY - fy;
        //     eAlt += dif;
        //   }
        //    }


        Retangulo eRe = new Retangulo(pX, pY, eLar, eAlt);
        Retangulo eCaixa = new Retangulo(pX, pY, eLar, eAlt);


        if (isDebug) {

            drawEixos(pCentro, eRaio, q1, q2);

            drawRect(eRe, Cor.getHexCor("#FFD700"));
            drawRect(eCaixa, Cor.getHexCor("#FF4500"));

        }


        int x = 0;
        int y = eCirculo.getRaio();
        double d = 1.25 - (double) eCirculo.getRaio();

        int xi = eCirculo.getX() + eCirculo.getRaio();
        int yi = eCirculo.getY() + eCirculo.getRaio();

        int iCor = eCor.getValor();

        while (x < y) {
            if (d < 0) {
                d = d + 2 * x + 3;
                x += 1;
            } else {
                d = d + 2 * (x - y) + 5;
                x += 1;
                y -= 1;
            }
            drawPixelSeDentro(xi + x, yi + y, eRe, iCor);
            drawPixelSeDentro(xi + x, yi - y, eRe, iCor);
            drawPixelSeDentro(xi - x, yi + y, eRe, iCor);
            drawPixelSeDentro(xi - x, yi - y, eRe, iCor);

            drawPixelSeDentro(xi + y, yi + x, eRe, iCor);
            drawPixelSeDentro(xi + y, yi - x, eRe, iCor);
            drawPixelSeDentro(xi - y, yi + x, eRe, iCor);
            drawPixelSeDentro(xi - y, yi - x, eRe, iCor);

        }


    }

    public int valide(int a) {

        while (a < 0) {
            a += 360;
        }

        while (a > 360) {
            a -= 360;
        }

        return a;

    }


    public void drawEixos(Ponto pCentro, int eRaio, Ponto q1, Ponto q2) {


        drawRect_Pintado(new Retangulo(pCentro.getX() - 2, pCentro.getY() - 2, 4, 4), Cor.getHexCor("#4169E1"));

        drawLinha(new Linha(pCentro.getX() - eRaio, pCentro.getY(), pCentro.getX() + eRaio, pCentro.getY()), Cor.getHexCor("#4169E1"));
        drawLinha(new Linha(pCentro.getX(), pCentro.getY() - eRaio, pCentro.getX(), pCentro.getY() + eRaio), Cor.getHexCor("#4169E1"));

        emPonto(pCentro, q1);
        emPonto(pCentro, q2);


    }

    public void emPonto(Ponto pCentro, Ponto eMarcado) {

        if (eMarcado.getY() < pCentro.getY()) {
            int eTam = pCentro.getY() - eMarcado.getY();
            drawLinha(new Linha(eMarcado.getX(), eMarcado.getY(), eMarcado.getX(), eMarcado.getY() + eTam), Cor.getHexCor("#b71c1c"));
        }

        if (eMarcado.getY() > pCentro.getY()) {
            int eTam = eMarcado.getY() - pCentro.getY();
            drawLinha(new Linha(eMarcado.getX(), pCentro.getY(), eMarcado.getX(), pCentro.getY() + eTam), Cor.getHexCor("#b71c1c"));
        }


        if (eMarcado.getX() > pCentro.getX()) {
            int eTam = eMarcado.getX() - pCentro.getX();
            drawLinha(new Linha(pCentro.getX(), eMarcado.getY(), pCentro.getX() + eTam, eMarcado.getY()), Cor.getHexCor("#b71c1c"));
        }

        if (eMarcado.getX() < pCentro.getX()) {
            int eTam = pCentro.getX() - eMarcado.getX();
            drawLinha(new Linha(eMarcado.getX(), eMarcado.getY(), eMarcado.getX() + eTam, eMarcado.getY()), Cor.getHexCor("#b71c1c"));
        }

    }


    public void drawPixelSeDentro(int eX, int eY, Retangulo mArea, int iCor) {

        if (eX >= mArea.getX() && eX < mArea.getX2() && eY >= mArea.getY() && eY < mArea.getY2()) {


            if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

                int ePox = (eY * mLargura) + eX;

                mPixels[ePox] = iCor;

            }

        }


    }


}