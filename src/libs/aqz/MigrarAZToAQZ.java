package libs.aqz;

import libs.bs.BancoBS;
import libs.entt.Entidade;
import libs.luan.Lista;

public class MigrarAZToAQZ {


    public static void MIGRAR(String arquivo_az, String colecao_az, String arquivo_aqz, String colecao_aqz) {

        AQZ.COLECOES_ORGANIZAR(arquivo_aqz, colecao_aqz);
        AQZ.LIMPAR_TUDO(arquivo_aqz, colecao_aqz);


        BancoBS.checar(arquivo_az);

        BancoBS m = new BancoBS(arquivo_az);

        Lista<Entidade> colecao = m.getColecao(colecao_az).getObjetos();

        for (Entidade obj : colecao) {
            AQZ.INSERIR(arquivo_aqz, colecao_aqz, obj);
        }

        m.fechar();




    }


}
