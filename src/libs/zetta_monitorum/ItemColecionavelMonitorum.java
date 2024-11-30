package libs.zetta_monitorum;

import libs.entt.Entidade;
import libs.zetta.ItemColecionavel;
import libs.zetta.ZettaColecao;

public class ItemColecionavelMonitorum {

    private ZettaColecaoMonitorum mColecao;
    private ItemColecionavel mItemColecionavel;
    private ZettaColecao mLogs;

    public ItemColecionavelMonitorum(ZettaColecao eLogs,ZettaColecaoMonitorum eColecao, ItemColecionavel eItemColecionavel) {
        mLogs = eLogs;
        mItemColecionavel = eItemColecionavel;
        mColecao=eColecao;
    }


    public Entidade get() {
        return mItemColecionavel.get();
    }


    public void referenciar(String att_nome, ZettaColecaoMonitorum colecao, Entidade nova_referencia) {

        if (mItemColecionavel.isRemovido()) {
            throw new RuntimeException("ItemColecionavel removido !");
        }

        long referencia_id = colecao.adicionar(nova_referencia);

        get().at(att_nome, referencia_id);

        atualizar();
    }

    public void atualizar() {

        mItemColecionavel.atualizar();

        mColecao.logs_atualizar(false,true,false,false,false,false,false);
    }

    public void remover(){
        mItemColecionavel.remover();
        mColecao.logs_atualizar(false,false,true,false,false,false,false);

    }


}
