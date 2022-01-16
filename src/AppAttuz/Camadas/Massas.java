package AppAttuz.Camadas;

import AppAttuz.CadaPonto;
import AppAttuz.Ferramentas.Unicidade;
import Imaginador.ImageUtils;

import java.awt.image.BufferedImage;

public class Massas {

    private int AGUA;
    private int TERRA;

    private int valores[];

    private int mLargura;
    private int mAltura;

    private int mContagemTerra;
    private int mContagemAgua;

    public Massas(String LOCAL, boolean isInverso) {

        TERRA = 1;
        AGUA = 0;

        if (isInverso) {
            TERRA = 0;
            AGUA = 1;
        }

        String arqTerra = LOCAL + "terra.png";

        BufferedImage mapa = ImageUtils.getImagem(arqTerra);

        mLargura = mapa.getWidth();
        mAltura = mapa.getHeight();

        mContagemTerra = 0;
        mContagemAgua = 0;

        valores = new int[mLargura * mAltura];

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

        //unico.listar();

        // ImageUtils.exportar(mapa, "/home/luan/Imagens/Mapas/terra_fake.png");

    }

    public Massas(String LOCAL) {

        TERRA = 1;
        AGUA = 0;


        String arqTerra = LOCAL + "terra.png";

        BufferedImage mapa = ImageUtils.getImagem(arqTerra);

        mLargura = mapa.getWidth();
        mAltura = mapa.getHeight();

        mContagemTerra = 0;
        mContagemAgua = 0;

        valores = new int[mLargura * mAltura];

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

        //unico.listar();

        // ImageUtils.exportar(mapa, "/home/luan/Imagens/Mapas/terra_fake.png");

    }

    public Massas(String LOCAL, int eTerra, int eAgua) {

        TERRA = eTerra;
        AGUA = eAgua;

        String arqTerra = LOCAL + "terra.png";

        BufferedImage mapa = ImageUtils.getImagem(arqTerra);

        mLargura = mapa.getWidth();
        mAltura = mapa.getHeight();

        mContagemTerra = 0;
        mContagemAgua = 0;

        valores = new int[mLargura * mAltura];

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

        //unico.listar();

        // ImageUtils.exportar(mapa, "/home/luan/Imagens/Mapas/terra_fake.png");

    }

    public int getLargura() {
        return mLargura;
    }

    public int getAltura() {
        return mAltura;
    }

    public int getContagemTerra() {
        return mContagemTerra;
    }

    public int getContagemAgua() {
        return mContagemAgua;
    }

    public int getContagem() {
        return mContagemAgua + mContagemTerra;
    }

    public int getProporcaoTerra() {
        return (int) (((double) getContagemTerra() / (double) getContagem()) * 100.0);
    }

    // public int getProporcaoAgua() {
    //    return (int) (((double) getContagemAgua() / (double) getContagem()) * 100.0);
    //  }//

    public int getProporcaoAgua() {
        return 100 - getProporcaoTerra();
    }

    public boolean isValido(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isTerra(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            int i = (y * mLargura) + x;
            return valores[i] == TERRA;
        } else {
            return false;
        }
    }

    public boolean isAgua(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            int i = (y * mLargura) + x;
            return valores[i] == AGUA;
        } else {
            return false;
        }
    }

    public void pintar_se_terra(int x, int y, int cor) {
        if (isTerra(x, y)) {
            int i = (y * mLargura) + x;
            valores[i] = cor;
        }
    }

    public void pintar_se_agua(int x, int y, int cor) {
        if (isAgua(x, y)) {
            int i = (y * mLargura) + x;
            valores[i] = cor;
        }
    }


    public void pintar(int x, int y, int cor) {
        int i = (y * mLargura) + x;
        valores[i] = cor;
    }

    public int getValor(int x, int y) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            int i = (y * mLargura) + x;
            return valores[i];
        }
        return 0;
    }

    public void setValor(int x, int y, int v) {
        if (x >= 0 && x < mLargura && y >= 0 && y < mAltura) {
            int i = (y * mLargura) + x;
            valores[i] = v;
        }
    }

    public int getTerra() {
        return TERRA;
    }

    public int getAgua() {
        return AGUA;
    }

    public static Massas copiarMassa(String LOCAL, Massas origem) {

        Massas copia = new Massas(LOCAL, origem.getTerra(), origem.getAgua());

        for (int y = 0; y < copia.getAltura(); y++) {
            for (int x = 0; x < copia.getLargura(); x++) {
                copia.setValor(x, y, origem.getValor(x, y));
            }
        }

        return copia;
    }

    public void paraCadaPonto(CadaPonto eCadaPonto) {

        for (int y = 0; y < getAltura(); y++) {
            for (int x = 0; x < getLargura(); x++) {
                eCadaPonto.onPonto(x,y);
            }
        }

    }

    public void zerar(){
        for(int i=0;i<valores.length;i++){
            valores[i] = 0;
        }
    }
}
