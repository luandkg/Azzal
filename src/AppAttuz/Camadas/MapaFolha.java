package AppAttuz.Camadas;

import Imaginador.ImageUtils;

import java.awt.image.BufferedImage;

public class MapaFolha {

    public static BufferedImage getMapa(String LOCAL) {
        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "terra.png");
        return mapa;
    }

    public static BufferedImage getMapaPolitico(String LOCAL) {
        BufferedImage mapa = ImageUtils.getImagem(LOCAL + "politico.png");
        return mapa;
    }
}
