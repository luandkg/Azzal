package apps.app_attuz.Servicos;

import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Assessorios.EscalasPadroes;
import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Ferramentas.Pintor;
import azzal.Renderizador;
import libs.Luan.Integers;
import libs.Imaginador.ImageUtils;
import libs.Servittor.Servico;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Conveccionador extends Servico {

    private int mLargura;
    private int mAltura;

    private int CARTOGRAFIA_LARGURA;
    private int CARTOGRAFIA_ALTURA;

    private String LOCAL;

    public Conveccionador(String eLOCAL) {
        LOCAL = eLOCAL;

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "build/planeta.png");

        CARTOGRAFIA_LARGURA = mapa.getWidth() / 18;
        CARTOGRAFIA_ALTURA = mapa.getHeight() / 18;

        mLargura = mapa.getWidth();
        mAltura = mapa.getHeight();

    }



    @Override
    public void onInit() {

        marcarInicio();

        Escala mEscala = EscalasPadroes.getEscalaConveccao();

        println("Criando mapa de Convecção ....");

        //genConveccao(LOCAL, mEscala);

        genOceanoCorrentes(LOCAL);

        marcarFim();
        mostrarTempo();
    }

    private void genConveccao(String LOCAL, Escala mEscala) {

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Massas eMassa = MassasDados.getTerraAgua(LOCAL);


        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {

                int v = getConveccaoModular(y);

                if (eMassa.isTerra(x, y)) {
                    eMassa.setValor(x, y, v + 1);
                }

                if (y == (mapa.getHeight() / 2)) {
                    eMassa.pintar(x, y, mEscala.getMaximo());

                    eMassa.pintar(x, y - 1, 2);
                    eMassa.pintar(x, y + 1, 2);

                    eMassa.pintar(x, y - 2, 2);
                    eMassa.pintar(x, y + 2, 2);

                }


            }
        }


        ImageUtils.exportar(Pintor.colorir(mapa, eMassa, mEscala), LOCAL + "build/conveccao.png");


    }


    public int getTamanhoLatitude() {
        return CARTOGRAFIA_ALTURA;
    }

    public int getTamanhoLongitude() {
        return CARTOGRAFIA_LARGURA;
    }

    public int getAlturaFaixa() {
        return mAltura / 6;
    }

    public int getLatidadeNoMapa(int p) {

        int FAIXA = mAltura / 6;

        if (p >= 0) {
            return (mAltura / 2) + (p * FAIXA);
        } else {
            p = p * (-1);
            return (mAltura / 2) - (p * FAIXA);
        }
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
        return enc;
    }

    public static BufferedImage aplicarLatitudes(String LOCAL, BufferedImage mapa) {

        Cartografia onCartografia = new Cartografia(LOCAL);

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {


                if (onCartografia.isLinhaLatitude(x, y)) {

                    Color cor = Color.BLUE;

                    if (onCartografia.getLatitudeModular(y) == 0) {
                        cor = Color.RED;
                    }
                    mapa.setRGB(x, y, Color.red.getRGB());

                    mapa.setRGB(x, y + 1, cor.getRGB());
                    mapa.setRGB(x, y + 2, cor.getRGB());
                    mapa.setRGB(x, y + 3, cor.getRGB());

                }


            }


        }

        return mapa;
    }

    public ArrayList<Integer> getCentrosDeLatitudes() {

        ArrayList<Integer> centros_de_faixas = new ArrayList<Integer>();

        for (int p = 0; p < 3; p++) {

            int pos_p = p;
            int neg_p = p * (-1);

            centros_de_faixas.add(getLatidadeNoMapa(neg_p));

            centros_de_faixas.add(getLatidadeNoMapa(pos_p));

        }

        return Integers.ordenar(centros_de_faixas);
    }


    private void genOceanoCorrentes(String LOCAL) {

        Escala mEscalaMovimento = EscalasPadroes.getEscalaConveccaoMovimento();

        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");

        Massas eMassa = MassasDados.getAguaTerra(LOCAL);
        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        int ACIMA = 1;
        int ABAIXO = 3;

        int NORMAL = 2;

        int metade = mapa.getHeight() / 2;

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {

                int v = getConveccaoModular(y);


                if (eMassa.isAgua(x, y) && (v == 2)) {
                    eMassa.setValor(x, y, NORMAL);


                } else if (eMassa.isAgua(x, y) && (v == 1)) {
                    eMassa.setValor(x, y, NORMAL);


                } else if (eMassa.isAgua(x, y) && (v == 0)) {
                    eMassa.setValor(x, y, NORMAL);


                }


            }
        }


        irSubindo(tectonica, eMassa, 0, 150);

        ImageUtils.exportar(Pintor.colorir(mapa, eMassa, mEscalaMovimento), LOCAL + "build/oceano_correntes.png");

        Renderizador r = new Renderizador(ImageUtils.getCopia(ImageUtils.getImagem(LOCAL + "build/oceano_correntes.png")));


        r.exportarSemAlfa(LOCAL + "build/oceano_correntes.png");

    }

    public void passar_subindo(Massas tectonica, Massas eMassa, int x, int y) {

        int ACIMA = 1;
        int ABAIXO = 3;

        while (x < tectonica.getLargura()) {

            eMassa.setValor(x, y, ACIMA);
            eMassa.setValor(x, y + 1, ACIMA);
            eMassa.setValor(x, y + 2, ACIMA);

            if (tectonica.isAgua(x, y)) {
                x += 1;
            } else {
                while (y > 0) {
                    if (tectonica.isAgua(x, y)) {
                        break;
                    }
                    y -= 1;
                }
            }
        }

    }

    public void passar_descendo(Massas tectonica, Massas eMassa, int x, int y) {

        int ACIMA = 1;
        int ABAIXO = 3;

        while (x < tectonica.getLargura()) {

            eMassa.setValor(x, y, ABAIXO);
            eMassa.setValor(x, y + 1, ABAIXO);
            eMassa.setValor(x, y + 2, ABAIXO);

            if (tectonica.isAgua(x, y)) {
                x += 1;
            } else {
                while (y < tectonica.getAltura()) {
                    if (tectonica.isAgua(x, y)) {
                        break;
                    }
                    y += 1;
                }
            }
        }

    }


    public void irSubindo(Massas tectonica, Massas eMassa, int x, int y) {

        int ACIMA = 1;
        int ABAIXO = 3;

        while (x < tectonica.getLargura()) {

            eMassa.setValor(x, y, ACIMA);

            eMassa.setValor(x, y + 1, ACIMA);
            eMassa.setValor(x, y + 2, ACIMA);
            eMassa.setValor(x, y - 1, ACIMA);
            eMassa.setValor(x, y - 2, ACIMA);

            eMassa.setValor(x + 1, y, ACIMA);

            eMassa.setValor(x + 1, y + 1, ACIMA);
            eMassa.setValor(x + 1, y + 2, ACIMA);
            eMassa.setValor(x + 1, y - 1, ACIMA);
            eMassa.setValor(x + 1, y - 2, ACIMA);


            if (tectonica.isAgua(x, y)) {
                x += 10;
            } else {
                break;
            }
        }

    }


    public boolean futuro(Massas eMassa, int x, int y) {
        boolean ret = false;

        for (int xi = x; xi < (x + 20); xi++) {
            if (eMassa.isTerra(xi, y)) {
                ret = true;
                break;
            }
        }

        return ret;
    }

    public boolean passado(Massas eMassa, int x, int y) {
        boolean ret = false;

        for (int xi = x - 20; xi <= x; xi++) {
            if (eMassa.isTerra(xi, y)) {

                System.out.println("C :: " + x + " parei " + xi);

                ret = true;
                break;
            }
        }

        return ret;
    }

    public int getConveccao(int p) {

        int parte = mAltura / 6;

        int conv = 0;

        int c = 0;

        while (p >= parte) {
            c += 1;
            p -= parte;
        }

        if (c < 3) {
            conv = 2 - c;
        } else {
            conv = c - 3;
        }

        return conv;
    }


    public int getConveccaoModular(int p) {

        int v = getConveccao(p);

        if (v < 0) {
            return v * (-1);
        }

        return v;
    }
}
