package libs.zetta.features;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.zetta.ZettaArquivo;
import libs.zetta.ZettaPasta;

public class ZettaArquivoENTT {

    private ZettaArquivo mArquivo;

    public ZettaArquivoENTT(ZettaArquivo eArquivo){
        mArquivo=eArquivo;
    }

    public Lista<Entidade> getEntidades(){
      return  ENTT.PARSER(Strings.GET_STRING_VIEW(mArquivo.getBytes()));
    }

    public void atualizar(Lista<Entidade> novos_dados){
        mArquivo.atualizar_dados(Strings.GET_STRING_VIEW_BYTES(ENTT.TO_DOCUMENTO(novos_dados)));
    }

    public static void CRIAR_ARQUIVO(ZettaPasta pasta_calendario,String eArquivoNome,Lista<Entidade> novos_dados){
        pasta_calendario.adicionar_ou_atualizar(eArquivoNome, GUARDAR_BYTES(novos_dados));
    }

    public static byte[] GUARDAR_BYTES(Lista<Entidade> novos_dados){
       return Strings.GET_STRING_VIEW_BYTES(ENTT.TO_DOCUMENTO(novos_dados));
    }
}
