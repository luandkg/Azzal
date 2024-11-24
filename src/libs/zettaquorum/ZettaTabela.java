package libs.zettaquorum;


import libs.aqz.tabela.AQZTabelaManipuladoraDeDados;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Armazem;
import libs.fazendario.ArmazemIndiceSumario;
import libs.fazendario.ItemAlocado;
import libs.luan.Lista;
import libs.luan.Resultado;

public class ZettaTabela {

    private String mNome;
    private ArmazemManipulador mEsquema;
    private ArmazemManipulador mDados;
    private ZettaSequenciador mSequenciador;
    private ArmazemIndiceSumario mIndice;

    private ZettaTabelaEsquematizador mEsquematizador;

    public ZettaTabela(String eNome, Armazem eEsquema, Armazem eDados, ZettaSequenciador eSequenciador, ArmazemIndiceSumario eIndice) {
        mNome = eNome;
        mEsquema = new ArmazemManipulador(eEsquema);
        mDados = new ArmazemManipulador(eDados);
        mSequenciador = eSequenciador;
        mIndice = eIndice;

        mEsquematizador = new ZettaTabelaEsquematizador(mEsquema);
    }

    public void zerar() {

        mDados.zerar();
        mEsquema.zerar();
        mIndice.zerar();
        mSequenciador.zerar();

    }


    public Lista<Entidade> getItens() {

        Lista<Entidade> lista = new Lista<Entidade>();

        for (ItemAlocado item : mDados.getItens()) {

            Entidade e_item = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            e_item.at("@PTR", item.getPonteiroDados());

            e_item.tornar_primeiro("@PTR");
            e_item.tornar_primeiro("@ID");

            lista.adicionar(e_item);
        }

        return lista;
    }

    public void exibir_esquema() {
        ENTT.EXIBIR_TABELA_COM_NOME(mEsquema.getObjetos(), "ESQUEMA :: " + mNome);
    }

    public void exibir_dados() {
        ENTT.EXIBIR_TABELA_COM_NOME(getItens(), "DADOS :: " + mNome);
    }

    public boolean temEsquema() {
        return mEsquema.contagem() > 0;
    }



    // ESQUEMA

    public void criar_chave_primaria(String coluna_nome, int valor_inicial, int valor_passo) {
        mEsquematizador.criar_chave_primaria(coluna_nome,valor_inicial,valor_passo);
    }

    public void criar_coluna(String coluna_nome, AZTabelaColunaTipo coluna_tipo) {
        mEsquematizador.criar_coluna(coluna_nome,coluna_tipo);
    }

    public void criar_coluna_nao_obrigatoria(String coluna_nome, AZTabelaColunaTipo coluna_tipo) {
        mEsquematizador.criar_coluna_nao_obrigatoria(coluna_nome,coluna_tipo);
    }

    public void criar_acao_inserivel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoInserivel coluna_auto_inserivel) {
        mEsquematizador.criar_acao_inserivel(coluna_regra_nome,coluna_nome,coluna_tipo,coluna_auto_inserivel);
    }

    public void criar_acao_atualizavel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoAtualizavel coluna_auto_atualizavel) {
        mEsquematizador.criar_acao_atualizavel(coluna_regra_nome,coluna_nome,coluna_tipo,coluna_auto_atualizavel);
    }

    public void criar_verificador(String coluna_regra_nome, String coluna_nome, Entidade verificador) {
        mEsquematizador.criar_verificador(coluna_regra_nome,coluna_nome,verificador);
    }


    // MANIPULAR DADOS

    public Resultado<Boolean, String> adicionar(Entidade novo) {
        return ZettaTabelaManipuladorDeDados.adicionar(mEsquema,mDados,mIndice,mSequenciador, mNome, novo);
    }

}
