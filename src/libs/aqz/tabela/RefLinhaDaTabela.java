package libs.aqz.tabela;

import libs.aqz.colecao.ColecaoUTF8;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.entt.Entidade;
import libs.luan.Resultado;

public class RefLinhaDaTabela {

    private AQZTabela mTabela;
    private ItemDoBancoUTF8 mItem;
    private Entidade mEntidade;
    private ColecaoUTF8 mEsquema;
    private ColecaoUTF8 mDados;
    private String mNome;

    public RefLinhaDaTabela(AQZTabela eTabela, ColecaoUTF8 eEsquema, ColecaoUTF8 eDados, String eNome, ItemDoBancoUTF8 eItem, Entidade eEntidade) {
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
        return AQZTabelaManipuladoraDeDados.atualizar(mEsquema, mDados, mNome, mItem, atualizando);
    }
}
