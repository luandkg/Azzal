package libs.rho_benchmark;

import libs.dkg.DKGObjeto;
import libs.tempo.Tempo;

public class RhoTimeStamp {

    private DKGObjeto mObjeto;

    public RhoTimeStamp(DKGObjeto eObjeto) {
        mObjeto = eObjeto;
    }

    public void iniciar() {
        mObjeto.identifique("Iniciar", Tempo.getTempoNano());
        mObjeto.identifique("TCIniciar", Tempo.getTempoCompleto());
    }

    public void terminar() {
        mObjeto.identifique("Terminar", Tempo.getTempoNano());
        mObjeto.identifique("TCTerminar", Tempo.getTempoCompleto());
    }


}
