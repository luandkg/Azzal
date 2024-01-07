package apps.app_corretor;


import apps.app_letrum.Fonte;
import apps.app_letrum.Maker.FonteRunTime;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.Windows;
import libs.azzal.cenarios.Cena;
import libs.azzal.utilitarios.Cor;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;

import java.awt.*;
import java.awt.image.BufferedImage;


public class AppCorretor extends Cena {


    private Fonte texto;
    private BufferedImage mImagem;
    private BufferedImage mPedaco;

    private int imagem_largura = 0;
    private int imagem_altura = 0;

    private int linha_y1 = 0;
    private int linha_y2 = 0;

    private int coluna_x1 = 0;
    private int coluna_x2 = 0;

    @Override
    public void iniciar(Windows eWindows) {

        eWindows.setTitle("Alpha");
        texto = new FonteRunTime(new Cor(0, 0, 0), 10);

        mImagem = Imagem.getImagem("/home/luan/Dropbox/CED_01/2022_11_15_15_52_43_pre.png");
        mImagem = Efeitos.preto_branco(mImagem);


        imagem_largura = mImagem.getWidth();
        imagem_altura = mImagem.getHeight();


        linha_y1 = primeira_linha(mImagem);
        linha_y2 = ultima_linha(mImagem);

        coluna_x1 = primeira_coluna(mImagem, linha_y1, linha_y2);
        coluna_x2 = ultima_coluna(mImagem, linha_y1, linha_y2);


        System.out.println("Y1 : " + linha_y1);
        System.out.println("Y2 : " + linha_y2);

        System.out.println("X1 : " + coluna_x1);
        System.out.println("X2 : " + coluna_x2);

        mPedaco = toPedaco(mImagem, coluna_x1, coluna_x2, linha_y1, linha_y2);

    }

    public BufferedImage toPedaco(BufferedImage img, int l1, int l2, int a1, int a2) {

        mPedaco = Imagem.criarEmBranco(l2 - l1, a2 - a1);

        int ia = 0;

        for (int y = a1; y < a2; y++) {

            int il = 0;

            for (int x = l1; x < l2; x++) {

                int pixel = img.getRGB(x, y);
                mPedaco.setRGB(il, ia, pixel);

                il += 1;
            }

            ia += 1;
        }


        return mPedaco;
    }

    public int primeira_linha(BufferedImage img) {

        int retornar_linha = 0;

        int largura = img.getWidth();
        int altura = img.getHeight();

        int metade_altura = altura / 2;

        for (int y = 0; y < metade_altura; y++) {

            int somando = 0;

            for (int x = 0; x < largura; x++) {
                int pixel = Cor.getInt(img.getRGB(x, y)).getRed();

                if (pixel < 100) {
                    somando += 1;
                }

            }

            if (somando >= largura) {
                retornar_linha = y;
            }

        }

        return retornar_linha;
    }

    public int ultima_linha(BufferedImage img) {

        int retornar_linha = 0;

        int largura = img.getWidth();
        int altura = img.getHeight();

        int metade_altura = altura / 2;

        for (int y = altura - 1; y > metade_altura; y--) {

            int somando = 0;

            for (int x = 0; x < largura; x++) {
                int pixel = Cor.getInt(img.getRGB(x, y)).getRed();

                if (pixel < 100) {
                    somando += 1;
                }

            }

            if (somando >= largura) {
                retornar_linha = y;
            }

        }

        return retornar_linha;
    }

    public int primeira_coluna(BufferedImage img, int a1, int a2) {

        int retornar_linha = 0;

        int largura = img.getWidth();
        int altura = img.getHeight();

        int metade_largura = largura / 2;
        int preciso = a2 - a1;

        for (int x = 0; x < metade_largura; x++) {

            int somando = 0;

            for (int y = a1; y < a2; y++) {

                int pixel = Cor.getInt(img.getRGB(x, y)).getRed();

                if (pixel < 100) {
                    somando += 1;
                }

            }

            if (somando >= preciso) {
                retornar_linha = x;
            }

        }

        return retornar_linha;
    }

    public int ultima_coluna(BufferedImage img, int a1, int a2) {

        int retornar_linha = 0;

        int largura = img.getWidth();
        int altura = img.getHeight();

        int metade_largura = largura / 2;
        int preciso = a2 - a1;

        for (int x = largura - 1; x > metade_largura; x--) {

            int somando = 0;

            for (int y = a1; y < a2; y++) {

                int pixel = Cor.getInt(img.getRGB(x, y)).getRed();

                if (pixel < 100) {
                    somando += 1;
                }

            }

            if (somando >= preciso) {
                retornar_linha = x;
            }

        }

        return retornar_linha;
    }

    @Override
    public void update(double dt) {


        getWindows().getMouse().liberar();


    }


    @Override
    public void draw(Renderizador mRenderizador) {

        mRenderizador.limpar(Color.WHITE);

        texto.setRenderizador(mRenderizador);


        mRenderizador.drawImagem(100, 100, mImagem);


        mRenderizador.drawLinha(100, 100 + linha_y1, 100 + imagem_largura + 100, 100 + linha_y1, Cores.hexToCor("#FDD835"));
        mRenderizador.drawLinha(100, 100 + linha_y2, 100 + imagem_largura + 100, 100 + linha_y2, Cores.hexToCor("#E64A19"));

        mRenderizador.drawLinha(100 + coluna_x1, 100 + linha_y1, 100 + coluna_x1, 100 + imagem_altura + 100, Cores.hexToCor("#FDD835"));
        mRenderizador.drawLinha(100 + coluna_x2, 100 + linha_y2, 100 + coluna_x2, 100 + imagem_altura + 100, Cores.hexToCor("#E64A19"));


        mRenderizador.drawImagem(500, 100, mPedaco);

        mRenderizador.drawImagem(700, 100, mPedaco);

        double fatias = (double) mPedaco.getWidth() / 6.0;

        double fatiando = 0;

        for (int f = 0; f <= 6; f++) {
            if (f > 1) {
                mRenderizador.drawLinha(700 + (int) fatiando, 100, 700 + (int) fatiando, 100 + mPedaco.getWidth() + 30, Cores.hexToCor("#F4511E"));
            }
            fatiando += fatias;
        }


        double linhas = (double) mPedaco.getHeight() / 11.0;

        double linearizando = 0;

        for (int f = 0; f <= 11; f++) {
            if (f > 0) {
                mRenderizador.drawLinha(700, 100 + (int) linearizando, 700 + mPedaco.getWidth() + 30, 100 + (int) linearizando, Cores.hexToCor("#F4511E"));
            }
            linearizando += linhas;
        }


    }


}
