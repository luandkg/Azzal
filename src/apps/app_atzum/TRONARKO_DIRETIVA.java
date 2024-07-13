package apps.app_atzum;

import libs.luan.fmt;
import libs.tronarko.Tron;

public class TRONARKO_DIRETIVA {


    public static void DIRETIVA(Tron t1, Tron t2){

        long v1 = t1.getHazde().getTotalEttons();
        long v2= t2.getHazde().getTotalEttons();


        fmt.print("Tronz +{}",(v2-v1)+" ez");

    }
}
