package AppAttuz.Ferramentas;

import AppAttuz.Camadas.Massas;
import Imaginador.ImageUtils;

import java.awt.image.BufferedImage;

public class Pintor {

    public static BufferedImage colorir(BufferedImage mapa_entrada, Massas eMassa, Escala eEscala) {
        BufferedImage mapa = ImageUtils.getCopia(mapa_entrada);

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {

                int n = eMassa.getValor(x, y);
                if (n<eEscala.getMaximo()){
                    mapa.setRGB(x, y, eEscala.get(n));
                }else{
                    mapa.setRGB(x, y, eEscala.get(eEscala.getMaximo()-1));
                }

            }
        }

        return mapa;
    }

}
