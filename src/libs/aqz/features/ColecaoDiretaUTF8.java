package libs.aqz.features;

import libs.aqz.AQZUTF8;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;

public class ColecaoDiretaUTF8 {

    private String mArquivo;
    private String mColecao;

    public ColecaoDiretaUTF8(String eArquivo, String eColecao) {
        mArquivo = eArquivo;
        mColecao = eColecao;
    }

    public void ORGANIZAR() {
        AQZUTF8.COLECOES_ORGANIZAR(mArquivo, mColecao);
    }

    public Lista<Entidade> COLECAO_ENTIDADES() {
        return AQZUTF8.COLECAO_ENTIDADES(mArquivo, mColecao);
    }

    public void INSERIR(Entidade insercao) {
        AQZUTF8.INSERIR(mArquivo, mColecao, insercao);
    }


    public long COLECAO_CONTAGEM() {
        return AQZUTF8.COLECAO_CONTAGEM(mArquivo, mColecao);
    }


    public void EXIBIR_COLECAO(){
        AQZUTF8.EXIBIR_COLECAO(mArquivo, mColecao);
    }


    public Opcional<Par<ItemDoBancoUTF8, Entidade>> GET_UNICO(String att_nome, String att_valor) {
        return AQZUTF8.GET_UNICO(mArquivo, mColecao,att_nome,att_valor);
    }

    public void ATUALIZAR(ItemDoBancoUTF8 item, Entidade entidade) {
         AQZUTF8.ATUALIZAR(mArquivo, mColecao,item,entidade);
    }


}
