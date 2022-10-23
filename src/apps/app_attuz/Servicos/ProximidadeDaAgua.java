package apps.app_attuz.Servicos;

import apps.app_attuz.Assessorios.Progressante;
import apps.app_attuz.Assessorios.Massas;
import apps.app_attuz.Assessorios.MassasDados;
import apps.app_attuz.Assessorios.EscalasPadroes;
import apps.app_attuz.Assessorios.Escala;
import apps.app_attuz.Ferramentas.MapaRender;
import apps.app_attuz.Ferramentas.MassaToQTT;
import apps.app_attuz.Ferramentas.Normalizador;
import libs.Imaginador.ImageUtils;
import libs.Luan.fmt;
import libs.Servittor.Servico;

public class ProximidadeDaAgua extends Servico {

    private String LOCAL;

    public ProximidadeDaAgua(String eLOCAL) {
        LOCAL = eLOCAL;
    }

    @Override
    public void onInit() {

        marcarInicio();

        Escala mEscala = EscalasPadroes.getEscalaDistancia();


        Massas massa = MassasDados.getTerraAgua(LOCAL);
        Massas tectonica = MassasDados.getTerraAgua(LOCAL);


        Normalizador normalizador = new Normalizador(mEscala.getMaximo());

        Progressante progresso = new Progressante(tectonica.getAltura() * tectonica.getLargura());

        for (int y = 0; y < tectonica.getAltura(); y++) {
            for (int x = 0; x < tectonica.getLargura(); x++) {

                if (tectonica.isTerra(x, y)) {

                    int distancia = getProximidade(tectonica, massa, x, y);

                    normalizador.adicionar(distancia);

                    massa.setValor(x, y, distancia);

                    String v = fmt.format(" PONTO ( {esq5} , {esq5} ) :: {esq9} ", x, y, distancia);

                    progresso.emitir((y * tectonica.getAltura()) + x, v);

                } else {
                    progresso.vazio((y * tectonica.getAltura()) + x);
                }

            }
        }


        MapaRender.renderiza(ImageUtils.getImagem(LOCAL + "build/planeta.png"), tectonica, tectonica.getTerra(), massa, mEscala, normalizador, LOCAL + "build/agua_distancia.png");

        System.out.println("Guardar Proximidade - QTT");
        MassaToQTT.salvarTerra(tectonica,massa, LOCAL + "dados/agua_distancia.qtt");


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
