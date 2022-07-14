package libs.Imaginador;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Efeitos {

    public static BufferedImage preto_branco(BufferedImage img) {

        BufferedImage blackAndWhiteImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = blackAndWhiteImg.createGraphics();
        graphics.drawImage(img, 0, 0, null);

        int height = img.getHeight();
        int width = img.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(img.getRGB(j, i));

                int red = (int) (c.getRed() * 0.350);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);

                int valor = red + green + blue;

                if (valor > 255) {
                    valor = 255;
                }

                Color newColor = new Color(valor, valor, valor);
                img.setRGB(j, i, newColor.getRGB());
            }
        }
        return blackAndWhiteImg;
    }

    public static BufferedImage reduzir(BufferedImage img,int nova_largura,int nova_altura) {

        BufferedImage reduzida = new BufferedImage(nova_largura, nova_altura, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics = reduzida.createGraphics();
        graphics.drawImage(img, 0, 0, nova_largura, nova_altura,null);

        return reduzida;
    }




    public static BufferedImage reduzirComAlfa(BufferedImage img,int nova_largura,int nova_altura) {

        BufferedImage reduzida = new BufferedImage(nova_largura, nova_altura, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = reduzida.createGraphics();
        graphics.drawImage(img, 0, 0, nova_largura, nova_altura,null);

        return reduzida;
    }

}
