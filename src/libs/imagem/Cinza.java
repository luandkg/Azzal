package libs.imagem;

import java.awt.image.BufferedImage;

public class Cinza {

    public static BufferedImage toCinza(BufferedImage img) {

        BufferedImage original = img;

        int w = original.getWidth();
        int h = original.getHeight();

        BufferedImage processada = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (
                int y = 0;
                y < h; y++) {
            for (int x = 0; x < w; x++) {
                int colorido = original.getRGB(x, y);
                int cinza = escalaDeCinza(colorido);
                processada.setRGB(x, y, cinza);
            }
        }

        return processada;
    }

    public static int escalaDeCinza(int pixel) {
        int[] rgb = derivar(pixel);
        int r = rgb[0];
        int g = rgb[1];
        int b = rgb[2];
        int cinza = (r + g + b) / 3;
        rgb[0] = cinza;
        rgb[1] = cinza;
        rgb[2] = cinza;
        return integrar(rgb);
    }

    public static int[] derivar(int rgb) {
        int r = (rgb >> 16) & 0xFF;
        int g = (rgb >> 8) & 0xFF;
        int b = (rgb >> 0) & 0xFF;
        return new int[]{r, g, b};
    }

    public static int integrar(int[] rgb) {
        int r = (rgb[0] & 0xFF) << 16;
        int g = (rgb[1] & 0xFF) << 8;
        int b = (rgb[2] & 0xFF) << 0;
        return r | g | b;
    }

}
