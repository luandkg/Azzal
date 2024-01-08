package apps.app_attuz.Ferramentas;

import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Assessorios.Massas;
import libs.imagem.Imagem;

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

    public static void renderiza(BufferedImage mapa, Massas tectonica, int VALOR_PADRAO, Massas massa, Escala mRelevo, Normalizador normalizador, String arq) {

        equilibrador(tectonica, VALOR_PADRAO, massa, mRelevo, normalizador);
        Imagem.exportar(Pintor.colorir(mapa, massa, mRelevo), arq);

    }

    public static void renderiza(BufferedImage mapa, Massas tectonica, int VALOR_PADRAO, MassaComNormal eMassaComNormal, Escala mRelevo, String arq) {

        equilibrador(tectonica, VALOR_PADRAO, eMassaComNormal.getDados(), mRelevo, eMassaComNormal.getNormalizado());
        Imagem.exportar(Pintor.colorir(mapa, eMassaComNormal.getDados(), mRelevo), arq);

    }

    public static BufferedImage renderizaImagem(BufferedImage mapa, Massas tectonica, int VALOR_PADRAO, MassaComNormal eMassaComNormal, Escala mRelevo) {

        equilibrador(tectonica, VALOR_PADRAO, eMassaComNormal.getDados(), mRelevo, eMassaComNormal.getNormalizado());
        return Pintor.colorir(mapa, eMassaComNormal.getDados(), mRelevo);

    }

    public static void renderizaSoPontos(BufferedImage mapa_entrada, Massas tectonica, int VALOR_PADRAO, Massas massa, Escala eEscala, Normalizador normalizador, String arq) {

        normalizador.equilibrar();

        BufferedImage mapa = Imagem.getCopia(mapa_entrada);

        for (int y = 0; y < massa.getAltura(); y++) {
            for (int x = 0; x < massa.getLargura(); x++) {
                if (tectonica.getValor(x, y) == VALOR_PADRAO) {

                    if (massa.getValor(x, y) > 100) {

                        int real = normalizador.intervalo(eEscala.getMinimo(), eEscala.getMaximo(), normalizador.get(massa.getValor(x, y)));
                        massa.setValor(x, y, real);

                        int n = massa.getValor(x, y);
                        mapa.setRGB(x, y, eEscala.get(n));

                        for (int yr = 0; yr < 10; yr++) {
                            for (int r = 0; r < 10; r++) {

                                int ex = x + r;
                                int ey = y + yr;

                                if (ex >= 0 && ex < mapa.getWidth() && ey >= 0 && ey < mapa.getHeight()) {
                                    mapa.setRGB(ex, ey, eEscala.get(n));
                                }

                            }
                        }
                    }

                }
            }
        }

        Imagem.exportar(mapa, arq);

    }
}
