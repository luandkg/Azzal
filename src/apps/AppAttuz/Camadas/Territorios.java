package apps.AppAttuz.Camadas;

import apps.AppAttuz.Ferramentas.Mapeador;
import libs.Imaginador.Efeitos;
import libs.Imaginador.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Territorios {

    public static void init(BufferedImage mapa){

        BufferedImage  r1 = Efeitos.preto_branco(ImageUtils.getImagem("/home/luan/Imagens/Mapas/regioes/r1.png"));
        BufferedImage  r2 = Efeitos.preto_branco(ImageUtils.getImagem("/home/luan/Imagens/Mapas/regioes/r2.png"));
        BufferedImage  r3 = Efeitos.preto_branco(ImageUtils.getImagem("/home/luan/Imagens/Mapas/regioes/r3.png"));
        BufferedImage  r4 = Efeitos.preto_branco(ImageUtils.getImagem("/home/luan/Imagens/Mapas/regioes/r4.png"));
        BufferedImage  r5 = Efeitos.preto_branco(ImageUtils.getImagem("/home/luan/Imagens/Mapas/regioes/r5.png"));
        BufferedImage r6 = Efeitos.preto_branco(ImageUtils.getImagem("/home/luan/Imagens/Mapas/regioes/r6.png"));
        BufferedImage  r7 = Efeitos.preto_branco(ImageUtils.getImagem("/home/luan/Imagens/Mapas/regioes/r7.png"));

        Mapeador.mapear(mapa, r1, hexToColor("#FAEB57"));
        Mapeador.mapear(mapa, r2, hexToColor("#2DD652"));
        Mapeador.mapear(mapa, r3, hexToColor("#3E9BED"));
        Mapeador.mapear(mapa, r4, hexToColor("#FA7150"));
        Mapeador.mapear(mapa, r5, hexToColor("#FECEBD"));
        Mapeador.mapear(mapa, r6, hexToColor("#A12DD6"));
        Mapeador.mapear(mapa, r7, hexToColor("#5154DB"));

    }

    public static Color hexToColor(String colorStr) {
        return new Color(
                Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }
}
