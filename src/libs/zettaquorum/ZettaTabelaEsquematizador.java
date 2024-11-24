package libs.zettaquorum;

import apps.app_campeonatum.VERIFICADOR;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.tronarko.Tronarko;

public class ZettaTabelaEsquematizador {

    private ArmazemManipulador mEsquema;

    public ZettaTabelaEsquematizador(ArmazemManipulador eEsquema){
        mEsquema=eEsquema;
    }

    public void criar_chave_primaria(String coluna_nome, int valor_inicial, int valor_passo) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

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

        mEsquema.adicionar(e_coluna);

    }

    public void criar_coluna(String coluna_nome, AZTabelaColunaTipo coluna_tipo) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

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

        mEsquema.adicionar(e_coluna);

    }

    public void criar_coluna_nao_obrigatoria(String coluna_nome, AZTabelaColunaTipo coluna_tipo) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

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

        mEsquema.adicionar(e_coluna);

    }

    public void criar_acao_inserivel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoInserivel coluna_auto_inserivel) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

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

        mEsquema.adicionar(e_coluna);

    }

    public void criar_acao_atualizavel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoAtualizavel coluna_auto_atualizavel) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

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

        mEsquema.adicionar(e_coluna);

    }

    public void criar_verificador(String coluna_regra_nome, String coluna_nome, Entidade verificador) {

        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains(" "), "AQZTabela : coluna não pode conter espacos !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(!coluna_nome.contains("@"), "AQZTabela : coluna não pode conter arrobas !");
        VERIFICADOR.DEVE_SER_VERDADEIRO(coluna_nome.trim().length() > 0, "AQZTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

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

        mEsquema.adicionar(e_coluna);

    }
}
