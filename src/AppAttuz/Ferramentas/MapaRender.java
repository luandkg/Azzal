package AppAttuz.Ferramentas;

import AppAttuz.Camadas.Massas;
import AppAttuz.Normalizador;
import Imaginador.ImageUtils;

import java.awt.image.BufferedImage;

public class MapaRender {

    public static void equilibrador(Massas eOrigem, int VALOR_PADRAO, Massas massa, Escala mRelevo, Normalizador normalizador) {

        normalizador.equilibrar();

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {
                if (eOrigem.getValor(x, y) == VALOR_PADRAO) {
                    int real = normalizador.intervalo(mRelevo.getMinimo(), mRelevo.getMaximo(), normalizador.get(massa.getValor(x, y)));
                    massa.setValor(x, y, real);
                }
            }
        }

    }

    public static void renderiza(BufferedImage mapa, Massas tectonica, int VALOR_PADRAO, Massas massa, Escala mRelevo, Normalizador  normalizador, String arq) {

        equilibrador(tectonica, VALOR_PADRAO, massa, mRelevo, normalizador);
        ImageUtils.exportar(Pintor.colorir(mapa, massa, mRelevo), arq);

    }
}
