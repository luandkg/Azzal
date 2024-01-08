package apps.app_attuz.Ferramentas;

import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import libs.imagem.Efeitos;
import libs.imagem.Imagem;

import java.awt.image.BufferedImage;

public class UnirMapas {

    public static void unir(String LOCAL, String eArquivoTerra, String eArquivoAgua, String eArquivoResultante) {

        Massas massa = MassasDados.getTerraAgua(LOCAL);

        BufferedImage mapa = Imagem.getImagem(LOCAL + "build/planeta.png");

        BufferedImage terra = Imagem.getImagem(LOCAL + eArquivoTerra);
        BufferedImage mar = Imagem.getImagem(LOCAL + eArquivoAgua);

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {
                if (massa.isTerra(x, y)) {
                    mapa.setRGB(x, y, terra.getRGB(x, y));
                } else {
                    mapa.setRGB(x, y, mar.getRGB(x, y));
                }
            }
        }


        Imagem.exportar(mapa, LOCAL + eArquivoResultante);

    }

    public static void lado_a_lado(String LOCAL, String lado_a, String lado_b, String eArquivoResultante) {

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        BufferedImage img_verao = Efeitos.reduzir(Imagem.getImagem(LOCAL + lado_a), tectonica.getLargura() / 3, tectonica.getAltura() / 3);
        BufferedImage img_inverno = Efeitos.reduzir(Imagem.getImagem(LOCAL + lado_b), tectonica.getLargura() / 3, tectonica.getAltura() / 3);

        BufferedImage mapa = new BufferedImage(((tectonica.getLargura() / 3) * 2) + 20, (tectonica.getAltura() / 3) + 30, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < img_verao.getHeight(); y++) {
            for (int x = 0; x < img_verao.getWidth(); x++) {
                mapa.setRGB(x, y, img_verao.getRGB(x, y));
            }
        }

        for (int y = 0; y < img_inverno.getHeight(); y++) {
            for (int x = 0; x < img_inverno.getWidth(); x++) {
                mapa.setRGB(x + img_verao.getWidth() + 10, y, img_inverno.getRGB(x, y));
            }
        }


        Imagem.exportar(mapa, LOCAL + eArquivoResultante);

    }
}
