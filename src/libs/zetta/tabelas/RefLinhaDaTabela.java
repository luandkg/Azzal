package libs.zetta.tabelas;

import libs.entt.Entidade;
import libs.zetta.fazendario.ItemAlocado;
import libs.luan.Resultado;
import libs.zetta.persistencia.ArmazemManipulador;

public class RefLinhaDaTabela {

    private ZettaTabela mTabela;
    private ItemAlocado mItem;
    private Entidade mEntidade;
    private ArmazemManipulador mEsquema;
    private ArmazemManipulador mDados;
    private String mNome;

    public RefLinhaDaTabela(ZettaTabela eTabela, ArmazemManipulador eEsquema, ArmazemManipulador eDados, String eNome, ItemAlocado eItem, Entidade eEntidade) {
        mTabela = eTabela;
        mEsquema = eEsquema;
        mDados = eDados;
        mNome = eNome;
        mItem = eItem;
        mEntidade = eEntidade;
    }


    public Entidade getEntidade() {
        return mEntidade;
    }

    public Resultado<Boolean, String> atualizar(Entidade atualizando) {
        return ZettaTabelaManipuladorDeDados.atualizar(mEsquema, mDados, mNome, mItem, atualizando);
    }

}
