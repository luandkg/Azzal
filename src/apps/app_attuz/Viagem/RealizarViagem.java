package apps.app_attuz.Viagem;

import apps.app_attuz.Ferramentas.Local;
import apps.app_attuz.Localizador.ViagemIndexar;
import libs.azzal.utilitarios.Cronometro;
import libs.luan.Lista;
import libs.tronarko.Tron;
import libs.tronarko.Tronarko;

public class RealizarViagem {

    private int mais = 0;

    private Cronometro mCron;
    private int TEMPO_FLUXO = 500;

    public RealizarViagem() {

        mCron = new Cronometro(TEMPO_FLUXO);

    }

    public void viajar(Tronarko eTronarko, Viagem mViagem, Viajante EU, Lista<Local> mLocais) {

        mCron.esperar();
        if (mCron.foiEsperado()) {

            mais += 1;

            if (mais >= 100) {
                // mais = 0;
            }

            boolean procurar = true;

            if (procurar) {
                Tron eTron = eTronarko.getTronAgora();
                eTron.internalizar_Arco(mais);

                mViagem.viajar(EU, mLocais);

                //mProcurador.ondeEstou(EU, eTron.getTozte(), eTron.getHazde());
                //System.out.println("Localizando...");

                ViagemIndexar.procurando("/home/luan/Documentos/viagem.bzz", EU, eTron.getTozte(), eTron.getHazde());

            }

        }

    }


}
