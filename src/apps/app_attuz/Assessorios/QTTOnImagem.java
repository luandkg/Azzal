package apps.app_attuz.Assessorios;

import libs.arquivos.QTT;
import libs.imagem.Imagem;

import java.awt.image.BufferedImage;

public class QTTOnImagem {

    public static void relevo(String LOCAL, String eArquivoQTT, String eArquivoImagem) {

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        QTT eQTT = QTT.getTudo(eArquivoQTT);

        BufferedImage imagem = new BufferedImage(eQTT.getLargura(), eQTT.getAltura(), BufferedImage.TYPE_INT_RGB);

        Escala mRelevoTerra = EscalasPadroes.getEscalaTerrestre();
        Escala mRelevoAgua = EscalasPadroes.getEscalaAquatica();

        for (int y = 0; y < eQTT.getAltura(); y++) {
            for (int x = 0; x < eQTT.getLargura(); x++) {

                int valor = eQTT.getValor(x, y);

                if (tectonica.isTerra(x, y)) {

                    int nivel = valor / 100;
                    if (nivel >= mRelevoTerra.getMaximo()) {
                        nivel = mRelevoTerra.getMaximo() - 1;
                    }

                    imagem.setRGB(x, y, mRelevoTerra.get(nivel));

                } else {

                    int nivel = ((-1) * valor) / 100;
                    if (nivel >= mRelevoAgua.getMaximo()) {
                        nivel = mRelevoAgua.getMaximo() - 1;
                    }

                    imagem.setRGB(x, y, mRelevoAgua.get(nivel));

                }

            }
        }


        Imagem.exportar(imagem, eArquivoImagem);

    }

    public static void umidade(String LOCAL, String eArquivoQTT, String eArquivoImagem) {

        renderSoTerra(LOCAL, eArquivoQTT, EscalasPadroes.getEscalaUmidade(), eArquivoImagem);

    }

    public static void mar_distancia(String LOCAL, String eArquivoQTT, String eArquivoImagem) {

        renderSoTerra(LOCAL, eArquivoQTT, EscalasPadroes.getEscalaDistancia(), eArquivoImagem);

    }

    public static void temperatura(String LOCAL, String eArquivoQTT, String eArquivoImagem) {

        renderSoTerra(LOCAL, eArquivoQTT, EscalasPadroes.getEscalaTemperatura(), eArquivoImagem);

    }


    public static void renderSoTerra(String LOCAL, String eArquivoQTT, Escala mEscala, String eArquivoImagem) {

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        QTT eQTT = QTT.getTudo(eArquivoQTT);

        BufferedImage imagem = new BufferedImage(eQTT.getLargura(), eQTT.getAltura(), BufferedImage.TYPE_INT_RGB);


        for (int y = 0; y < eQTT.getAltura(); y++) {
            for (int x = 0; x < eQTT.getLargura(); x++) {

                int valor = eQTT.getValor(x, y);

                if (tectonica.isTerra(x, y)) {

                    int nivel = valor;
                    if (nivel >= mEscala.getMaximo()) {
                        nivel = mEscala.getMaximo() - 1;
                    }

                    imagem.setRGB(x, y, mEscala.get(nivel));
                } else {
                    imagem.setRGB(x, y, mEscala.get(0));
                }

            }
        }


        Imagem.exportar(imagem, eArquivoImagem);

    }

    public static void renderDuplo(String LOCAL, String eArquivoQTT, Escala mEscalaTerra, Escala mEscalaAgua, String eArquivoImagem) {

        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        QTT eQTT = QTT.getTudo(eArquivoQTT);

        BufferedImage imagem = new BufferedImage(eQTT.getLargura(), eQTT.getAltura(), BufferedImage.TYPE_INT_RGB);


        for (int y = 0; y < eQTT.getAltura(); y++) {
            for (int x = 0; x < eQTT.getLargura(); x++) {

                int valor = eQTT.getValor(x, y);

                if (tectonica.isTerra(x, y)) {

                    int nivel = valor;
                    if (nivel >= mEscalaTerra.getMaximo()) {
                        nivel = mEscalaTerra.getMaximo() - 1;
                    }

                    imagem.setRGB(x, y, mEscalaTerra.get(nivel));
                } else {
                    int nivel = valor;
                    if (nivel >= mEscalaAgua.getMaximo()) {
                        nivel = mEscalaAgua.getMaximo() - 1;
                    }

                    imagem.setRGB(x, y, mEscalaAgua.get(nivel));
                }

            }
        }


        Imagem.exportar(imagem, eArquivoImagem);

    }

}
