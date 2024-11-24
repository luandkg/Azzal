package libs.zettaquorum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.ItemAlocado;
import libs.luan.Lista;

public class ArmazemManipulador {

    private Armazem mArmazem;

    public ArmazemManipulador(Armazem eArmazem) {
        mArmazem = eArmazem;
    }


    public ItemAlocado adicionar(Entidade e) {
       return mArmazem.item_adicionar(ENTT.TO_DOCUMENTO(e));
    }

    public Lista<ItemAlocado> getItens() {
        return mArmazem.getItensAlocados();
    }

    public Lista<Entidade> getObjetos() {
        Lista<Entidade> itens = new Lista<Entidade>();

        for (ItemAlocado item : mArmazem.getItensAlocados()) {
            itens.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoUTF8()));
        }

        return itens;
    }

    public Lista<ItemEditavel> getEditaveis() {
        Lista<ItemEditavel> itens = new Lista<ItemEditavel>();

        for (ItemAlocado item : mArmazem.getItensAlocados()) {
            itens.adicionar(new ItemEditavel(item, ENTT.PARSER_ENTIDADE(item.lerTextoUTF8())));
        }

        return itens;
    }


    public long contagem() {
        return mArmazem.getItensUtilizadosContagem();
    }

    public void zerar() {
        mArmazem.zerar();
    }

}
