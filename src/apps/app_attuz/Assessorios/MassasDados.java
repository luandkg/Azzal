package apps.app_attuz.Assessorios;

import libs.imagem.Imagem;

import java.awt.image.BufferedImage;

public class MassasDados {

    public static Massas getTerraAgua(String LOCAL){


        int TERRA = 1;
       int AGUA = 0;


        BufferedImage mapa = Imagem.getImagem(LOCAL + "build/planeta.png");


        int  mLargura = mapa.getWidth();
        int mAltura = mapa.getHeight();

        int  mContagemTerra = 0;
        int  mContagemAgua = 0;

        int[] valores = new int[mLargura * mAltura];

        for (int i = 0; i < valores.length; i++) {
            valores[i] = 0;
        }

        Unicidade unico = new Unicidade();

        for (int y = 0; y < mAltura; y++) {
            for (int x = 0; x < mLargura; x++) {

                int real = mapa.getRGB(x, y);
                unico.em(real);

                int i = (y * mLargura) + x;

                if (real == -1) {
                    valores[i] = AGUA;
                    mContagemAgua += 1;
                } else {
                    valores[i] = TERRA;
                    mContagemTerra += 1;
                }


            }
        }

        return new Massas(TERRA,AGUA,mLargura,mAltura,mContagemTerra,mContagemAgua,valores);
    }

    public static Massas getAguaTerra(String LOCAL){

        int TERRA = 0;
        int AGUA = 1;

        BufferedImage mapa = Imagem.getImagem(LOCAL + "build/planeta.png");

        int  mLargura = mapa.getWidth();
        int mAltura = mapa.getHeight();

        int  mContagemTerra = 0;
        int  mContagemAgua = 0;

        int[] valores = new int[mLargura * mAltura];

        for (int i = 0; i < valores.length; i++) {
            valores[i] = 0;
        }

        Unicidade unico = new Unicidade();

        for (int y = 0; y < mAltura; y++) {
            for (int x = 0; x < mLargura; x++) {

                int real = mapa.getRGB(x, y);
                unico.em(real);

                int i = (y * mLargura) + x;

                if (real == -1) {
                    valores[i] = AGUA;
                    mContagemAgua += 1;
                } else {
                    valores[i] = TERRA;
                    mContagemTerra += 1;
                }


            }
        }

        return new Massas(TERRA,AGUA,mLargura,mAltura,mContagemTerra,mContagemAgua,valores);
    }

    public static Massas getMassaContinental(String LOCAL) {

       int TERRA = 1;
        int AGUA = 0;


        String arqTerra = LOCAL + "massa_continental.png";

        BufferedImage mapa = Imagem.getImagem(arqTerra);

        int   mLargura = mapa.getWidth();
        int   mAltura = mapa.getHeight();

        int  mContagemTerra = 0;
        int  mContagemAgua = 0;

        int[] valores = new int[mLargura * mAltura];

        for (int i = 0; i < valores.length; i++) {
            valores[i] = 0;
        }

        Unicidade unico = new Unicidade();

        for (int y = 0; y < mAltura; y++) {
            for (int x = 0; x < mLargura; x++) {

                int real = mapa.getRGB(x, y);
                unico.em(real);

                int i = (y * mLargura) + x;

                if (real == -1) {
                    valores[i] = AGUA;
                    mContagemAgua += 1;
                } else {
                    valores[i] = TERRA;
                    mContagemTerra += 1;
                }


            }
        }

        return new Massas(TERRA,AGUA,mLargura,mAltura,mContagemTerra,mContagemAgua,valores);

    }


}
