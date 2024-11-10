package libs.aqz.tabela;

import apps.app_campeonatum.VERIFICADOR;
import libs.aqz.colecao.ColecaoUTF8;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.armazenador.Armazenador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.tronarko.Tronarko;

public class AQZTabela {

    private String mNome;

    private Armazenador mArmazenador;
    private ColecaoUTF8 mEsquema;
    private ColecaoUTF8 mDados;

    public AQZTabela(Armazenador eArmazenador, String eNome, ColecaoUTF8 eEsquema, ColecaoUTF8 eDados) {
      mArmazenador=eArmazenador;
        mNome = eNome;
        mEsquema = eEsquema;
        mDados = eDados;
    }


    public void exibir_esquema() {

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();

        ENTT.EXIBIR_TABELA_COM_NOME(esquema, "ESQUEMA :: " + mNome);

    }

    public void exibir_dados() {

        Lista<Entidade> dados = mDados.getObjetosUTF8();

        ENTT.EXIBIR_TABELA_COM_NOME(dados, "DADOS :: " + mNome);

    }


    public void zerar() {
        mEsquema.limpar();
        mEsquema.zerarSequencial();
        mDados.limpar();
        mDados.zerarSequencial();
    }

    public void limpar_dados() {
        mDados.limpar();
        mDados.zerarSequencial();
    }

    public boolean temEsquema() {
        return mEsquema.getItensContagem() > 0;
    }


    // MANIPULAR ESQUEMA

    public void criar_chave_primaria(String coluna_nome, int valor_inicial, int valor_passo) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();

        if (ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            throw new RuntimeException("AQZTabela : Coluna já existente - " + coluna_nome);
        }


        Entidade e_coluna = new Entidade();
        e_coluna.at("Formato", "PRIMARIA");
        e_coluna.at("Nome", coluna_nome);
        e_coluna.at("Tipo", "INTEIRO");
        e_coluna.at("Obrigatoria", "SIM");
        e_coluna.at("Inicial", valor_inicial);
        e_coluna.at("Corrente", valor_inicial);
        e_coluna.at("Passo", valor_passo);

        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        mEsquema.adicionarUTF8(e_coluna);

    }

    public void criar_coluna(String coluna_nome, AZTabelaColunaTipo coluna_tipo) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();

        if (ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            throw new RuntimeException("AQZTabela : Coluna já existente - " + coluna_nome);
        }


        Entidade e_coluna = new Entidade();
        e_coluna.at("Formato", "DADOS");
        e_coluna.at("Nome", coluna_nome);
        e_coluna.at("Tipo", coluna_tipo.toString());
        e_coluna.at("Obrigatoria", "SIM");
        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        mEsquema.adicionarUTF8(e_coluna);

    }

    public void criar_coluna_nao_obrigatoria(String coluna_nome, AZTabelaColunaTipo coluna_tipo) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();

        if (ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            throw new RuntimeException("AQZTabela : Coluna já existente - " + coluna_nome);
        }


        Entidade e_coluna = new Entidade();
        e_coluna.at("Formato", "DADOS");
        e_coluna.at("Nome", coluna_nome);
        e_coluna.at("Tipo", coluna_tipo.toString());
        e_coluna.at("Obrigatoria", "NAO");
        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        mEsquema.adicionarUTF8(e_coluna);

    }

    public void criar_acao_inserivel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoInserivel coluna_auto_inserivel) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();

        if (!ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            throw new RuntimeException("AQZTabela : Coluna nao existente - " + coluna_nome);
        }

        if (ENTT.EXISTE(esquema, "Regra", coluna_regra_nome)) {
            throw new RuntimeException("AQZTabela : Regra já existente - " + coluna_regra_nome);
        }


        Entidade e_coluna = new Entidade();
        e_coluna.at("Regra", coluna_regra_nome);
        e_coluna.at("Nome", coluna_nome);
        e_coluna.at("Tipo", coluna_tipo.toString());
        e_coluna.at("Formato", "INSERIVEL");
        e_coluna.at("Valoravel", coluna_auto_inserivel.toString());
        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        mEsquema.adicionarUTF8(e_coluna);

    }

    public void criar_acao_atualizavel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoModificavel coluna_auto_atualizavel) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();

        if (!ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            throw new RuntimeException("AQZTabela : Coluna nao existente - " + coluna_nome);
        }

        if (ENTT.EXISTE(esquema, "Regra", coluna_regra_nome)) {
            throw new RuntimeException("AQZTabela : Regra já existente - " + coluna_regra_nome);
        }


        Entidade e_coluna = new Entidade();
        e_coluna.at("Regra", coluna_regra_nome);
        e_coluna.at("Nome", coluna_nome);
        e_coluna.at("Tipo", coluna_tipo.toString());
        e_coluna.at("Formato", "ATUALIZAVEL");
        e_coluna.at("Valoravel", coluna_auto_atualizavel.toString());
        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        mEsquema.adicionarUTF8(e_coluna);

    }

    public void criar_verificador(String coluna_regra_nome, String coluna_nome, Entidade verificador) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();

        if (!ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            throw new RuntimeException("AQZTabela : Coluna nao existente - " + coluna_nome);
        }

        if (ENTT.EXISTE(esquema, "Regra", coluna_regra_nome)) {
            throw new RuntimeException("AQZTabela : Regra já existente - " + coluna_regra_nome);
        }


        Entidade e_coluna = new Entidade();
        e_coluna.at("Formato", "VERIFICAVEL");
        e_coluna.at("Regra", coluna_regra_nome);
        e_coluna.at("Coluna", coluna_nome);
        e_coluna.at("Tipo", "");
        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        verificador.at("Nome", "VERIFICADOR");
        e_coluna.getEntidades().adicionar(verificador);

        mEsquema.adicionarUTF8(e_coluna);

    }

    // MANIPULAR DADOS

    public Resultado<Boolean, String> adicionar(Entidade novo) {
        return AQZTabelaManipuladoraDeDados.adicionar(mArmazenador,mEsquema, mDados, mNome, novo);
    }



    public Opcional<Entidade> procurar_um(String att_nome, String att_valor) {

        for (ItemDoBancoUTF8 item : mDados.getItens()) {
            Entidade obj = item.toEntidadeUTF8();
            if (obj.is(att_nome, att_valor)) {
                return Opcional.OK(obj);
            }
        }

        return Opcional.CANCEL();
    }

    public Opcional<Entidade> procurar_um(String att_nome, int att_valor) {

        for (ItemDoBancoUTF8 item : mDados.getItens()) {
            Entidade obj = item.toEntidadeUTF8();
            if (obj.is(att_nome, att_valor)) {
                return Opcional.OK(obj);
            }
        }

        return Opcional.CANCEL();
    }


    public Opcional<Entidade> obter_diretamente(int id) {
        Opcional<ItemDoBancoUTF8> item = mDados.getIndexado(id);

        if (item.isOK()) {
            return Opcional.OK(ENTT.PARSER_ENTIDADE(item.get().lerTextoUTF8()));
        } else {
            return Opcional.CANCEL();
        }
    }




    public Opcional<RefLinhaDaTabela> procurar_ref(String att_nome, int att_valor) {

        for (ItemDoBancoUTF8 item : mDados.getItens()) {
            Entidade obj = item.toEntidadeUTF8();
            if (obj.is(att_nome, att_valor)) {
                return Opcional.OK(new RefLinhaDaTabela(this,mEsquema,mDados,mNome, item, obj));
            }
        }

        return Opcional.CANCEL();
    }

}
