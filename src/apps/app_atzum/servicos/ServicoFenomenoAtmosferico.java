package apps.app_atzum.servicos;

import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumTerra;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.azzal.utilitarios.Cor;


public class ServicoFenomenoAtmosferico {


    public static Renderizador MAPA_TEMPESTADE_INICIAR() {

        Cores mCores = new Cores();

        AtzumTerra mapa_planeta = new AtzumTerra();

        Renderizador render = Renderizador.construir(mapa_planeta.getLargura(), mapa_planeta.getAltura(), mCores.getPreto());

        return render;
    }

    public static void PROCESSAR_FURACAO(AtzumTerra planeta, Atzum atzum, Renderizador mapa, Renderizador render_massas_de_ar) {

        Cor COR_AZUL = Atzum.FENOMENO_COR_FURACAO;

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if (planeta.isOceano(x, y)) {

                    String massa_de_ar = atzum.getMassaDeArTipo(render_massas_de_ar.getPixel(x, y));

                    if (massa_de_ar.contains("TEMPESTADE")){
                        mapa.drawPixel(x,y,COR_AZUL);
                    }

                }
            }
        }

    }

    public static void PROCESSAR_TORNADO(AtzumTerra planeta, Atzum atzum, Renderizador mapa, Renderizador render_massas_de_ar) {

        Cor COR_LARANJA = Atzum.FENOMENO_COR_TORNADO;

        for (int y = 0; y < planeta.getAltura(); y++) {
            for (int x = 0; x < planeta.getLargura(); x++) {
                if (planeta.isTerra(x, y)) {

                    String massa_de_ar = atzum.getMassaDeArTipo(render_massas_de_ar.getPixel(x, y));

                    if (massa_de_ar.contains("TEMPESTADE")){
                        mapa.drawPixel(x,y,COR_LARANJA);
                    }

                }
            }
        }

    }

}
