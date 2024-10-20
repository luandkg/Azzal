package libs.ez;

import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;

public class AZZColecao {

    private String mArquivo;
    private String mColecao;

    public AZZColecao(String eArquivo, String eColecao) {
        mArquivo = eArquivo;
        mColecao = eColecao;
    }



    public String getArquivo(){return mArquivo;}
    public String getColecao(){return mColecao;}

    public void ORGANIZAR() {
        AZZ.COLECOES_ORGANIZAR(mArquivo, mColecao);
    }



    public void INSERIR(Entidade obj) {
        AZZ.INSERIR(mArquivo, mColecao, obj);
    }

    public void ATUALIZAR(ItemDoBanco item, Entidade obj) {
        AZZ.ATUALIZAR(mArquivo, item, obj);
    }


    public Opcional<Par<ItemDoBanco, Entidade>> GET_UNICO(String eChave, String eValor) {
        return AZZ.COLECAO_ITEM_UNICO_RAW(mArquivo, mColecao, eChave, eValor);
    }


    public void EXIBIR_COLECAO() {
        AZZ.EXIBIR_COLECAO(mArquivo, mColecao);
    }

    public Lista<Entidade> COLECAO_ITENS() {
       return AZZ.COLECAO_ENTIDADES(mArquivo, mColecao);
    }

    public Lista<Entidade> COLECAO_ENTIDADES() {
        return AZZ.COLECAO_ENTIDADES(mArquivo, mColecao);
    }

    public void LIMPAR() {
        AZZ.LIMPAR_TUDO(mArquivo, mColecao);
    }

    public void EXIBIR() {

        AZZ.EXIBIR_COLECAO(mArquivo, mColecao);

    }

    public long GET_QUANTIDADE(){
       return AZZ.QUANTIDADE(mArquivo, mColecao);
    }

}
