package apps.app_attuz.Ferramentas;

import azzal.utilitarios.Cor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mapeador {

    public static void mapear(BufferedImage mapa, BufferedImage regiao, Cor eCor) {

        int largura = regiao.getWidth();
        int altura = regiao.getHeight();

        int cor_selecionada = eCor.getValor();

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {

                int c = mapa.getRGB(x, y);

                if (c == cor_selecionada) {
                    regiao.setRGB(x, y, cor_selecionada);
                }

            }
        }


    }

}
