package apps.app_attuz.Ferramentas;

import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Assessorios.Massas;
import libs.imagem.Imagem;

import java.awt.image.BufferedImage;

public class Pintor {

    public static BufferedImage colorir(BufferedImage mapa_entrada, Massas eMassa, Escala eEscala) {
        BufferedImage mapa = Imagem.getCopia(mapa_entrada);

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
