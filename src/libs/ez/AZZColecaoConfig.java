package libs.ez;

import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;

public class AZZColecaoConfig {

    private AZZColecao mAZZColecao;

    public AZZColecaoConfig(String eArquivo, String eColecao) {
        mAZZColecao = new AZZColecao(eArquivo, eColecao);
    }

    public Opcional<Par<ItemDoBanco, Entidade>> COLECAO_ITEM_UNICO_RAW(String atributo_chave, String atributo_valor) {

        for (Par<ItemDoBanco, Entidade> raw_obj : AZZ.COLECAO_ITENS_RAW(mAZZColecao.getArquivo(), mAZZColecao.getColecao())) {
            if (raw_obj.getValor().is(atributo_chave,atributo_valor)) {
                return Opcional.OK(raw_obj);
            }
        }

        return Opcional.CANCEL();

    }


    public void ORGANIZAR() {
        AZZ.COLECOES_ORGANIZAR(mAZZColecao.getArquivo(), mAZZColecao.getColecao());
    }

    public boolean ITEM(String atributo_chave) {

        boolean existe = false;

        AZ aqz = new AZ(mAZZColecao.getArquivo());

        for (ItemDoBanco item : aqz.colecoes_obter(mAZZColecao.getColecao()).getItens()) {
            Entidade raw_obj = item.toEntidade();

            if (raw_obj.is("Nome",atributo_chave)) {
                existe = true;
                break;
            }

        }

        if (!existe) {
            Entidade novo = AZZ.NOVA_ENTIDADE();
            novo.at("Nome", atributo_chave);

            aqz.colecoes_obter(mAZZColecao.getColecao()).adicionar(novo);
        }

        aqz.fechar();

        return existe;
    }

    public String ITEM_ATRIBUTO(String atributo_chave, String atributo_buscar) {

        String ret = "";

        AZ aqz = new AZ(mAZZColecao.getArquivo());

        for (ItemDoBanco item : aqz.colecoes_obter(mAZZColecao.getColecao()).getItens()) {
            Entidade raw_obj =item.toEntidade();

            if (raw_obj.is("Nome",atributo_chave)) {
                ret = raw_obj.at(atributo_buscar);
                break;
            }

        }

        aqz.fechar();

        return ret;
    }

    public void ITEM_ATRIBUTO_ATUALIZAR(String atributo_chave, String atributo_buscar, String valor) {

        ITEM(atributo_chave);


        AZ aqz = new AZ(mAZZColecao.getArquivo());

        for (ItemDoBanco item : aqz.colecoes_obter(mAZZColecao.getColecao()).getItens()) {
            Entidade raw_obj = item.toEntidade();

            if (raw_obj.is("Nome",atributo_chave)) {
                raw_obj.at(atributo_buscar,valor);

                item.atualizar(raw_obj);
                break;
            }

        }

        aqz.fechar();

    }


    public void EXIBIR() {

        AZZ.EXIBIR_COLECAO(mAZZColecao.getArquivo(), mAZZColecao.getColecao());

    }


}
