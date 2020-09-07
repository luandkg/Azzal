package Azzal;

import Azzal.Formatos.Ponto;
import Azzal.Utils.Cor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Renderizador {

    private int[] mPixels;

    private int mLargura;
    private int mAltura;

    public Renderizador(BufferedImage eImagem) {


        mPixels = ((DataBufferInt) eImagem.getRaster().getDataBuffer()).getData();

        mLargura = eImagem.getWidth();
        mAltura = eImagem.getHeight();


    }

    public void limpar(Color eCor) {


        for (int i = 0; i < mPixels.length; i++) {
            mPixels[i] = eCor.getRGB();
        }


    }

    public void drawPixel(int eX, int eY, Cor eCor) {

        if (eX >= 0 && eX < mLargura && eY >= 0 && eY < mAltura) {

            int ePox = (eY * mLargura) + eX;

            mPixels[ePox] = eCor.getValor();

        }


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

    public void drawRect(int eX, int eY, int eLargura, int eAltura, Cor eCor) {

        drawLinha(eX , eY, 0, eAltura, eCor);

        drawLinha(eX+eLargura , eY, 0, eAltura, eCor);

        drawLinha(eX , eY, eLargura, 0, eCor);

        drawLinha(eX , eY+eAltura, eLargura, 0, eCor);
        

    }

    public void drawEsquema(int eX, int eY, int eLargura, int eAltura,int eEspacador, Cor eCor) {

        drawLinha(eX-eEspacador , eY-eEspacador, eLargura, 0, eCor); // Linha Superior

        drawLinha(eX+eEspacador , eY+eAltura+eEspacador, eLargura, 0, eCor); // Linha Inferior



        drawLinha(eX - eEspacador, eY-eEspacador, 0, eAltura, eCor); // Coluna Esquerda

        drawLinha(eX+eLargura+eEspacador , eY+eEspacador, 0, eAltura, eCor); // Coluna Direita



    }

    public void drawLinha(int eX, int eY, int eX2, int eY2, Cor eCor) {


        if (eX2 == 0 && eY2 == 0) {


        } else if (eX2 == 0 && eY2 != 0) {

            if (eY2>0){
                for (int y = 0; y < eY2; y++) {

                    drawPixel(eX , eY + y, eCor);

                }
            }else{

                for (int y = 0; y >= eY2; y--) {

                    drawPixel(eX , eY + y, eCor);

                }

            }


        } else if (eX2 != 0 && eY2 == 0) {

            if (eX2>0) {

                for (int x = 0; x < eX2; x++) {

                    drawPixel(eX + x , eY , eCor);

                }

            }else{

                for (int x = 0; x >= eX2; x--) {


                    drawPixel(eX + x , eY , eCor);


                }

            }


        } else {

            System.out.println("LINHA DIAGONAL - NAO IMPLEMENTADA");

        }


    }


}
