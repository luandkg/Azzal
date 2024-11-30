package libs.zetta_monitorum;

import libs.entt.ENTT;
import libs.zetta.ZettaColecao;
import libs.zetta.ZettaQuorum;

public class ZettaQuorumMonitorum {

    private  ZettaQuorum mZetaQuorum;
    private  ZettaColecao mLogs;

    public ZettaQuorumMonitorum(String eArquivo) {
         mZetaQuorum = new ZettaQuorum(eArquivo);

         mLogs = mZetaQuorum.getColecaoSempre("@ZettaQuorumMonitorumLogs");

    }

    public void fechar(){
        mZetaQuorum.fechar();
    }


    public ZettaColecaoMonitorum getColecaoSempre(String eColecao){
        ZettaColecao colecao = mZetaQuorum.getColecaoSempre(eColecao);

        return new ZettaColecaoMonitorum(mLogs,colecao);
    }

    public void logs_limpar(){
        mLogs.zerar();
    }


    public void dump(){
        mZetaQuorum.dump();
    }

    public void logs_ver(){
        ENTT.EXIBIR_TABELA_COM_NOME(mLogs.getItens(),"LOGS");
    }

}
