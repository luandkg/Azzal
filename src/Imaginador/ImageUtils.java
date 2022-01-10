package Imaginador;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageUtils {

    public static BufferedImage getImagem(String eArquivo) {
        BufferedImage IMG_MOSTRANDO = null;

        try {
            IMG_MOSTRANDO = ImageIO.read(new File(eArquivo));
        } catch (IOException e) {
        }

        return IMG_MOSTRANDO;
    }

    public static void exportar(BufferedImage eImage, String eArquivo) {
        try {
            ImageIO.write(eImage, "PNG", new File(eArquivo));
        } catch (IOException e) {
        }
    }

    public static BufferedImage getCopia(BufferedImage eImagem) {

        int largura = eImagem.getWidth();
        int altura = eImagem.getHeight();

        BufferedImage copia = new BufferedImage(eImagem.getWidth(), eImagem.getHeight(), BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                copia.setRGB(x, y, eImagem.getRGB(x, y));

            }
        }

        return copia;
    }

}
