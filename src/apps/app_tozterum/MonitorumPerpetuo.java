package apps.app_tozterum;


import libs.luan.MutString;
import libs.meta_functional.AcaoBeta;
import libs.tempo.Calendario;

public class MonitorumPerpetuo {

    public static void EXECUTAR_POR_MINUTO(AcaoBeta<Integer,Integer> eAcao) {

        MutString tempo = new MutString();

        while (true) {
            tempo.set(Calendario.getHoraMinuto());
            if (tempo.mudou()) {
                eAcao.fazer(Calendario.getHoraInteiro(),Calendario.getMinutoInteiro());
            }
        }
    }


}
