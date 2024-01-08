package libs.aqz;

import libs.az.AZ;
import libs.dkg.DKGObjeto;

public class MigrarToAQZ {


    public static void MIGRAR(String arquivo_az, String colecao_az, String arquivo_aqz, String colecao_aqz) {

        AQZ.COLECOES_ORGANIZAR(arquivo_aqz, colecao_aqz);
        AQZ.LIMPAR_TUDO(arquivo_aqz, colecao_aqz);

        for (DKGObjeto obj : AZ.GET_COLECAO(arquivo_az, colecao_az)) {
            AQZ.INSERIR(arquivo_aqz, colecao_aqz, obj);
        }

    }


}
