package apps.app_atzum.analisadores;

import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumTerra;
import libs.azzal.Cores;
import libs.azzal.Renderizador;
import libs.imagem.Imagem;

public class AnalisadorTemperatura {

    private boolean[] tronarko_quente = null;
    private boolean[] tronarko_frio = null;
    private boolean[] tronarko_vazio = null;

    private AtzumTerra mPlaneta ;
    private  Atzum mAtzum ;

    public AnalisadorTemperatura() {

        mPlaneta = new AtzumTerra();
        mAtzum = new Atzum();


        tronarko_quente = new boolean[mPlaneta.getAltura() * mPlaneta.getLargura()];
        tronarko_frio = new boolean[mPlaneta.getAltura() * mPlaneta.getLargura()];
        tronarko_vazio = new boolean[mPlaneta.getAltura() * mPlaneta.getLargura()];


        for (int y = 0; y < mPlaneta.getAltura(); y++) {
            for (int x = 0; x < mPlaneta.getLargura(); x++) {
                if (mPlaneta.isTerra(x, y)) {


                    tronarko_quente[(y * mPlaneta.getLargura()) + x] = true;
                    tronarko_frio[(y * mPlaneta.getLargura()) + x] = true;
                    tronarko_vazio[(y * mPlaneta.getLargura()) + x] = true;
                } else {
                    tronarko_quente[(y * mPlaneta.getLargura()) + x] = false;
                    tronarko_frio[(y * mPlaneta.getLargura()) + x] = false;
                    tronarko_vazio[(y * mPlaneta.getLargura()) + x] = false;
                }
            }
        }

    }

    public void processarSuperarko(Renderizador render_fatores_climaticos) {

        for (int y = 0; y < mPlaneta.getAltura(); y++) {
            for (int x = 0; x < mPlaneta.getLargura(); x++) {
                if (mPlaneta.isTerra(x, y)) {

                    if (tronarko_quente[(y * mPlaneta.getLargura()) + x]) {
                        String fator_climatico = mAtzum.getFatorClimatico(render_fatores_climaticos.getPixel(x, y));

                        if (fator_climatico.contentEquals("") || fator_climatico.contentEquals("SECA") || fator_climatico.contentEquals("SECA_EXTREMA") || fator_climatico.contentEquals("TEMPESTADE_VENTO")) {

                        } else {
                            tronarko_quente[(y * mPlaneta.getLargura()) + x] = false;
                        }
                    }

                    if (tronarko_frio[(y * mPlaneta.getLargura()) + x]) {
                        String fator_climatico = mAtzum.getFatorClimatico(render_fatores_climaticos.getPixel(x, y));

                        if (fator_climatico.contentEquals("") || fator_climatico.contentEquals("CHUVA") || fator_climatico.contentEquals("NEVE") || fator_climatico.contentEquals("TEMPESTADE_CHUVA") || fator_climatico.contentEquals("TEMPESTADE_NEVE")) {

                        } else {
                            tronarko_frio[(y * mPlaneta.getLargura()) + x] = false;
                        }
                    }

                    if (tronarko_vazio[(y * mPlaneta.getLargura()) + x]) {
                        String fator_climatico = mAtzum.getFatorClimatico(render_fatores_climaticos.getPixel(x, y));

                        if (!fator_climatico.isEmpty()) {
                            tronarko_vazio[(y * mPlaneta.getLargura()) + x] = false;
                        }
                    }

                }
            }
        }

    }


    public void renderizar(){

        Cores mCores = new Cores();

        Renderizador render_tronarko_quente = Renderizador.construir(mPlaneta.getLargura(), mPlaneta.getAltura(), mCores.getPreto());
        Renderizador render_tronarko_frio = Renderizador.construir(mPlaneta.getLargura(), mPlaneta.getAltura(), mCores.getPreto());
        Renderizador render_tronarko_vazio = Renderizador.construir(mPlaneta.getLargura(), mPlaneta.getAltura(), mCores.getPreto());
        Renderizador render_tronarko_completo = Renderizador.construir(mPlaneta.getLargura(), mPlaneta.getAltura(), mCores.getPreto());

        for (int y = 0; y < mPlaneta.getAltura(); y++) {
            for (int x = 0; x < mPlaneta.getLargura(); x++) {
                if (mPlaneta.isTerra(x, y)) {

                    if (tronarko_quente[(y * mPlaneta.getLargura()) + x]) {
                        render_tronarko_quente.setPixel(x, y, mCores.getVermelho());
                        render_tronarko_completo.setPixel(x, y, mCores.getVermelho());
                    }

                    if (tronarko_frio[(y * mPlaneta.getLargura()) + x]) {
                        render_tronarko_frio.setPixel(x, y, mCores.getAzul());
                        render_tronarko_completo.setPixel(x, y, mCores.getAzul());
                    }

                    if (tronarko_vazio[(y * mPlaneta.getLargura()) + x]) {
                        render_tronarko_vazio.setPixel(x, y, mCores.getVerde());
                        render_tronarko_completo.setPixel(x, y, mCores.getVerde());
                    }

                }
            }
        }

        Imagem.exportar(render_tronarko_quente.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_ambiente_quente.png"));
        Imagem.exportar(render_tronarko_frio.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_ambiente_frio.png"));
        Imagem.exportar(render_tronarko_vazio.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_ambiente_vazio.png"));
        Imagem.exportar(render_tronarko_completo.toImagemSemAlfa(), AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_ambiente_completo.png"));

    }

}
