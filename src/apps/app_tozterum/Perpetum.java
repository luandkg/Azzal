package apps.app_tozterum;

import libs.meta_functional.AcaoBeta;

public class Perpetum {

    public void init() {
        MonitorumPerpetuo.EXECUTAR_POR_MINUTO(ACAO());
    }

    public AcaoBeta<Integer,Integer> ACAO() {
        return new AcaoBeta<Integer,Integer>() {
            @Override
            public void fazer(Integer arg1,Integer arg2) {

            }
        };
    }

    public static void AUTO_INIT(Perpetum ePerpetum) {
        ePerpetum.init();
    }

}
