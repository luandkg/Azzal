package libs.zettaquorum;

import libs.aqz.tabela.AQZTabelaManipuladoraDeDados;
import libs.entt.Entidade;
import libs.fazendario.ItemAlocado;
import libs.luan.Resultado;

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
