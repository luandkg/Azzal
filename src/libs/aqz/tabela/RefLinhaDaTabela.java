package libs.aqz.tabela;

import libs.aqz.colecao.ColecaoUTF8Antigamente;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.entt.Entidade;
import libs.luan.Resultado;

public class RefLinhaDaTabela {

    private AQZTabela mTabela;
    private ItemDoBancoUTF8 mItem;
    private Entidade mEntidade;
    private ColecaoUTF8Antigamente mEsquema;
    private ColecaoUTF8Antigamente mDados;
    private String mNome;

    public RefLinhaDaTabela(AQZTabela eTabela, ColecaoUTF8Antigamente eEsquema, ColecaoUTF8Antigamente eDados, String eNome, ItemDoBancoUTF8 eItem, Entidade eEntidade) {
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
