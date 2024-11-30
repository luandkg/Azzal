package libs.zetta;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.zetta.fazendario.ArmazemIndiceSumario;
import libs.zetta.fazendario.ItemAlocado;

public class ItemColecionavel {

    private ArmazemIndiceSumario mIndice;
    private ItemAlocado mItem;
    private Entidade mEntidade;

    private boolean mRemovido;

    public ItemColecionavel(ArmazemIndiceSumario eIndice, ItemAlocado eItem, Entidade eEntidade) {
        mIndice = eIndice;
        mItem = eItem;
        mEntidade = eEntidade;
        mRemovido = false;
    }

    public Entidade get() {

        if (mRemovido) {
            throw new RuntimeException("ItemColecionavel removido !");
        }

        return mEntidade;
    }


    public void atualizar() {

        if (mRemovido) {
            throw new RuntimeException("ItemColecionavel removido !");
        }

        mItem.atualizarUTF8(ENTT.TO_DOCUMENTO(mEntidade));
    }


    public void remover() {
        if (mRemovido) {
            throw new RuntimeException("ItemColecionavel removido !");
        }

        mRemovido = true;

        mIndice.remover(mEntidade.atLong("@ID"));
        mItem.remover();

    }

    public boolean isRemovido(){
        return mRemovido;
    }

    public void referenciar(String att_nome,ZettaColecao colecao,Entidade nova_referencia) {

        if (mRemovido) {
            throw new RuntimeException("ItemColecionavel removido !");
        }

        long referencia_id = colecao.adicionar(nova_referencia);

        get().at(att_nome, referencia_id);

        atualizar();
    }
}
