package apps.AppAttuz.Ferramentas;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mapeador {

    public static void mapear(BufferedImage mapa, BufferedImage regiao, Color eCor) {

        int largura = regiao.getWidth();
        int altura = regiao.getHeight();

        ArrayList<String> unicos = new ArrayList<String>();

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                int c = regiao.getRGB(x, y);

                //if (!unicos.contains(String.valueOf(c))) {
                 //   unicos.add(String.valueOf(c));
                 //   System.out.println("COR :: " + String.valueOf(c));
                //}

                if (c != -1) {
                    mapa.setRGB(x, y, eCor.getRGB());
                }

            }
        }


    }

}
