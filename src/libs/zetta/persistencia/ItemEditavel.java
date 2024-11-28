package libs.zetta.persistencia;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.zetta.fazendario.ItemAlocado;

public class ItemEditavel {

    private ItemAlocado mItem;
    private Entidade mEntidade;

    public ItemEditavel(ItemAlocado eItem, Entidade eEntidade) {
        mItem = eItem;
        mEntidade = eEntidade;
    }

    public String at(String nome){
        return mEntidade.at(nome);
    }

    public int atInt(String nome){
        return mEntidade.atInt(nome);
    }

    public long atLong(String nome){
        return mEntidade.atLong(nome);
    }

    public boolean is(String nome,String valor){
        return mEntidade.is(nome,valor);
    }

    public void atLong(String nome,long valor){
        mEntidade.atLong(nome,valor);
    }

    public void atualizar() {
        mItem.atualizarUTF8(ENTT.TO_DOCUMENTO(mEntidade));
    }

    public Entidade getEntidade(){
        return mEntidade;
    }
}
