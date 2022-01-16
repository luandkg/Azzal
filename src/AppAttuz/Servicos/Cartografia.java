package AppAttuz.Servicos;

import AppAttuz.Camadas.Massas;
import AppAttuz.EscalasPadroes;
import AppAttuz.Ferramentas.Escala;
import AppAttuz.Ferramentas.Pintor;
import Luan.Integers;
import Imaginador.ImageUtils;
import Servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Cartografia extends Servico {

    private int mLargura;
    private int mAltura;

    private int CARTOGRAFIA_LARGURA ;
    private int CARTOGRAFIA_ALTURA ;

    private String LOCAL;

    public Cartografia(String eLOCAL) {
        LOCAL = eLOCAL;

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        CARTOGRAFIA_LARGURA = mapa.getWidth() / 18;
        CARTOGRAFIA_ALTURA = mapa.getHeight() / 18;

        mLargura=  mapa.getWidth();
        mAltura=  mapa.getHeight();

    }


    public int getLatitude(int p) {

        int metade = mAltura / 2;

        int valor = 0;

        if (p < metade) {
            valor = - ((metade-p)/CARTOGRAFIA_ALTURA) ;
        } else if (p >= metade) {
            p-=metade;
            valor = (p/CARTOGRAFIA_ALTURA);
        }

        return valor;
    }

    public int getLongitude(int p) {
        int metade = mLargura / 2;


        int valor = 0;

        if (p < metade) {
            valor = - ((metade-p)/CARTOGRAFIA_LARGURA) ;
        } else if (p >= metade) {
            p-=metade;
            valor = (p/CARTOGRAFIA_LARGURA);
        }

        return valor;
    }

    public int getLatitudeModular(int p) {

        int e_lat = getLatitude(p);

        if (e_lat < 0) {
            e_lat = e_lat * (-1);
        }

        return e_lat;
    }

    public int getLongitudeModular(int p) {

        int e_lon = getLongitude(p);

        if (e_lon < 0) {
            e_lon = e_lon * (-1);
        }

        return e_lon;
    }

    @Override
    public void onInit( ) {

        Escala mEscala = EscalasPadroes.getEscalaLatitude();

        println("Criando mapa de Latitude....");
        genLatitude(LOCAL, mEscala);

        println("Criando mapa de Longitude....");
        genLongitude(LOCAL, mEscala);

    }

    private void genLatitude(String LOCAL, Escala mEscala) {

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Massas eMassa = new Massas(LOCAL);

        Cartografia onCartografia = new Cartografia(LOCAL);

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {

                int lat = onCartografia.getLatitudeModular(y);

                if (eMassa.isTerra(x, y)) {

                    int e_lat = lat  + 1;
                    eMassa.setValor(x, y, e_lat);

                }

                if (y == (mapa.getHeight() / 2)) {
                    eMassa.pintar(x, y, mEscala.getMaximo());

                    eMassa.pintar(x, y-1, mEscala.getMaximo());
                    eMassa.pintar(x, y+1, mEscala.getMaximo());

                    eMassa.pintar(x, y-2, mEscala.getMaximo());
                    eMassa.pintar(x, y+2, mEscala.getMaximo());

                }


            }
        }




        ImageUtils.exportar(Pintor.colorir(mapa, eMassa, mEscala), LOCAL + "build/lat.png");


    }

    private void genLongitude(String LOCAL, Escala mEscala) {

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Massas eMassa = new Massas(LOCAL);
        Cartografia onCartografia = new Cartografia(LOCAL);

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {

                int lon = onCartografia.getLongitudeModular(x);

                if (eMassa.isTerra(x, y)) {

                    int e_lon = lon  + 1;

                    eMassa.pintar_se_terra(x, y, e_lon);


                }

                if (x == (mapa.getWidth() / 2)) {

                    eMassa.pintar(x, y, mEscala.getMaximo());

                    eMassa.pintar(x-1, y, mEscala.getMaximo());
                    eMassa.pintar(x+1, y, mEscala.getMaximo());

                    eMassa.pintar(x-2, y, mEscala.getMaximo());
                    eMassa.pintar(x+2, y, mEscala.getMaximo());

                }


            }
        }

        ImageUtils.exportar(Pintor.colorir(mapa, eMassa, mEscala), LOCAL + "build/lon.png");


    }

    public int getTamanhoLatitude(){return CARTOGRAFIA_ALTURA;}
    public int getTamanhoLongitude(){return CARTOGRAFIA_LARGURA;}


    public int getLatidadeNoMapa(int p){
        if (p>=0){
            return (mAltura/2)+(p*CARTOGRAFIA_ALTURA);
        }else{
            p=p*(-1);
            return (mAltura/2)-(p*CARTOGRAFIA_ALTURA) ;
        }
    }

    public boolean isLinhaLongitude(int x, int y) {

        int metade = mLargura / 2;

        boolean enc = false;


        for (int i = metade; i >= 0; i -= CARTOGRAFIA_ALTURA) {
            if (i == x) {
                enc = true;
                break;
            }
        }

        if (!enc) {

            for (int i = metade; i < mLargura; i += CARTOGRAFIA_ALTURA) {
                if (i == x) {
                    enc = true;
                    break;
                }
            }
        }
        return enc ;
    }

    public boolean isLinhaLatitude(int x, int y) {

        int metade = mAltura / 2;

        boolean enc = false;


        for (int i = metade; i >= 0; i -= CARTOGRAFIA_ALTURA) {
            if (i == y) {
                enc = true;
                break;
            }
        }

        if (!enc) {

            for (int i = metade; i < mLargura; i += CARTOGRAFIA_ALTURA) {
                if (i == y) {
                    enc = true;
                    break;
                }
            }
        }
        return enc ;
    }

    public static BufferedImage aplicarLatitudes(String LOCAL,BufferedImage mapa){

        Cartografia onCartografia = new Cartografia(LOCAL);

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {


                if (onCartografia.isLinhaLatitude(x,y)){

                    Color cor = Color.BLUE;

                    if (onCartografia.getLatitudeModular(y) == 0){
                        cor = Color.RED;
                    }
                    mapa.setRGB(x, y, Color.red.getRGB());

                    mapa.setRGB(x, y+1, cor.getRGB());
                    mapa.setRGB(x, y+2, cor.getRGB());
                    mapa.setRGB(x, y+3, cor.getRGB());

                }



            }



        }

        return mapa;
    }

    public ArrayList<Integer> getCentrosDeLatitudes(){

        ArrayList<Integer> centros_de_faixas = new ArrayList<Integer>();

        for (int p = 0; p < 9; p++) {

            int pos_p = p;
            int neg_p = p * (-1);

            centros_de_faixas.add(getLatidadeNoMapa(neg_p) - 60);

            centros_de_faixas.add(getLatidadeNoMapa(pos_p) + 40);


        }

        return Integers.ordenar(centros_de_faixas);
    }

}
