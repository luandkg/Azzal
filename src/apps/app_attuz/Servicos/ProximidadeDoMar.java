package apps.app_attuz.Servicos;

import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Assessorios.EscalasPadroes;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Ferramentas.MapaRender;
import apps.app_attuz.Ferramentas.MassaToQTT;
import apps.app_attuz.Ferramentas.Normalizador;
import libs.imagem.Imagem;
import libs.servittor.Servico;

import java.awt.image.BufferedImage;

public class ProximidadeDoMar extends Servico {

    private String LOCAL;

    public ProximidadeDoMar(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        marcarInicio();

        Escala mEscala = EscalasPadroes.getEscalaDistancia();

        BufferedImage mapa = Imagem.getImagem(LOCAL + "build/planeta.png");

        Massas massa = MassasDados.getTerraAgua(LOCAL);
        Massas tectonica = MassasDados.getTerraAgua(LOCAL);

        // Massas massa_continental = MassasDados.getMassaContinental(LOCAL);


        Normalizador normalizador = new Normalizador(mEscala.getMaximo());

        for (int y = 0; y < mapa.getHeight(); y++) {
            for (int x = 0; x < mapa.getWidth(); x++) {

                if (tectonica.isTerra(x, y)) {

                    int distancia = getProximidade(massa, massa, x, y);

                    normalizador.adicionar(distancia);

                    massa.setValor(x, y, distancia);

                }

            }
        }

        MapaRender.renderiza(mapa, tectonica, tectonica.getTerra(), massa, mEscala, normalizador, LOCAL + "build/mar.png");

        System.out.println("Guardar Proximidade - QTT");
        MassaToQTT.salvarTerra(tectonica, massa, LOCAL + "dados/mar_distancia.qtt");


        marcarFim();
        mostrarTempo();
    }

    public static int getProximidade(Massas tectonica, Massas eMassa, int px, int py, int mais_x, int mais_y) {

        int distancia = 0;

        px += mais_x;
        py += mais_y;

        while (px >= 0 && px < eMassa.getLargura() && py >= 0 && py < eMassa.getAltura()) {

            if (tectonica.isTerra(px, py)) {

                distancia += 1;

                px += mais_x;
                py += mais_y;

            } else {
                break;
            }

        }

        return distancia;
    }

    public static int getProximidadeDiagonal(Massas tectonica, Massas eMassa, int px, int py, int mais_x, int mais_y) {

        int distancia = 0;

        int ox = px;
        int oy = py;

        px += mais_x;
        py += mais_y;

        while (px >= 0 && px < eMassa.getLargura() && py >= 0 && py < eMassa.getAltura()) {
            if (tectonica.isTerra(px, py)) {
                distancia += 1;

                px += mais_x;
                py += mais_y;
            } else {
                break;
            }

        }

        distancia = (int) Math.sqrt(((ox - px) * (ox - px)) + ((oy - py) * (oy - py)));

        return distancia;
    }

    public static int min(int a, int b) {
        int ret = a;
        if (b < a) {
            ret = b;
        }
        return ret;
    }

    public static int getProximidade(Massas tectonica, Massas eMassa, int px, int py) {

        int distancia_x1 = getProximidade(tectonica, eMassa, px, py, -1, 0);
        int distancia_x2 = getProximidade(tectonica, eMassa, px, py, +1, 0);

        int distancia_y1 = getProximidade(tectonica, eMassa, px, py, 0, -1);
        int distancia_y2 = getProximidade(tectonica, eMassa, px, py, 0, +1);

        int distancia_xy1 = getProximidadeDiagonal(tectonica, eMassa, px, py, +1, +1);
        int distancia_xy2 = getProximidadeDiagonal(tectonica, eMassa, px, py, -1, -1);

        int distancia_xy3 = getProximidadeDiagonal(tectonica, eMassa, px, py, -1, +1);
        int distancia_xy4 = getProximidadeDiagonal(tectonica, eMassa, px, py, +1, -1);

        int ret = distancia_x1;

        ret = min(ret, distancia_x2);

        ret = min(ret, distancia_y1);
        ret = min(ret, distancia_y2);

        ret = min(ret, distancia_xy1);
        ret = min(ret, distancia_xy2);

        ret = min(ret, distancia_xy3);
        ret = min(ret, distancia_xy4);


        return ret;
    }
}
