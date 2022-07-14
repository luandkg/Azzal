package apps.app_attuz.Assessorios;

import libs.Arquivos.QTT;
import libs.Imaginador.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderQTT {

    public static void render(String eArquivoQTT, String eArquivoImagem) {

        int mx = 10;
        int my = 10;

        System.out.println("Guardar Relevo");
        int[] valores = new int[mx * my];

        for (int y = 0; y < my; y++) {
            for (int x = 0; x < mx; x++) {

                if (x == y){
                    valores[(y * mx) + x] = 10;
                }else{
                    valores[(y * mx) + x] = 0;
                }


            }
        }

       // QTT.guardar(eArquivoQTT, mx, my, valores);


        QTT eQTT = QTT.getTudo(eArquivoQTT);

        BufferedImage imagem = new BufferedImage(eQTT.getLargura(), eQTT.getAltura(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < eQTT.getAltura(); y++) {
            for (int x = 0; x < eQTT.getLargura(); x++) {

                int valor = eQTT.getValor(x, y);

                if (valor > 0) {
                    imagem.setRGB(x, y, Color.RED.getRGB());
                } else {
                    imagem.setRGB(x, y, Color.WHITE.getRGB());
                }

            }
        }


        ImageUtils.exportar(imagem, eArquivoImagem);
    }
}
