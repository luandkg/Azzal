package AppAttuz.Localizador;

import java.awt.*;
import java.awt.image.BufferedImage;

public class DroneCamera {

    private int quadro_x = 200;
    private int quadro_y = 240;
    private BufferedImage img;

    public DroneCamera() {
        img = new BufferedImage(quadro_x, quadro_y, BufferedImage.TYPE_INT_RGB);
    }

    public BufferedImage onGravar(BufferedImage mapa, int x, int y, int eu_x, int eu_y) {

        int height = mapa.getHeight();
        int width = mapa.getWidth();

        int ix = x;
        int ox = x + quadro_x;

        int mesmo_x = eu_x - x;
        int mesmo_y = eu_y - y;

        for (int i = 0; i < quadro_x; i++) {

            int iy = y;
            int oy = y + quadro_y;

            for (int j = 0; j < quadro_y; j++) {

                img.setRGB(i, j, Color.WHITE.getRGB());

                if (ix >= 0 && ix < width && ix < ox && iy >= 0 && iy < height && iy < oy) {

                    img.setRGB(i, j, mapa.getRGB(ix, iy));

                }

                iy += 1;

            }

            ix += 1;
        }

        int vermelho = Color.red.getRGB();

        for (int i = mesmo_x; i < mesmo_x + 5; i++) {
            for (int j = mesmo_y; j < mesmo_y + 5; j++) {
                img.setRGB(i, j, vermelho);
            }
        }


        return img;

    }


}
