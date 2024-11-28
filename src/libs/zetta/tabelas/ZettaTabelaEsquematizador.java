package libs.zetta.tabelas;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Resultado;
import libs.tronarko.Tronarko;
import libs.zetta.persistencia.ArmazemManipulador;

public class ZettaTabelaEsquematizador {

    private ArmazemManipulador mEsquema;

    public ZettaTabelaEsquematizador(ArmazemManipulador eEsquema){
        mEsquema=eEsquema;
    }

    private void FALHAR_SE(Resultado<String,String> resultado ,boolean condicao,String erro){
        if(condicao){
            resultado.errar(erro);
        }
    }

    public Resultado<String,String> criar_chave_primaria(String coluna_nome, int valor_inicial, int valor_passo) {

        Resultado<String,String> resultado = new Resultado<String,String>("OK");


        FALHAR_SE(resultado,coluna_nome.contains(" "), "ZettaTabela : coluna não pode conter espacos !");
        FALHAR_SE(resultado,coluna_nome.contains("@"), "ZettaTabela : coluna não pode conter arrobas !");
        FALHAR_SE(resultado, coluna_nome.trim().isEmpty(), "ZettaTabela : coluna precisa ter nome !");

        if(resultado.isErro()){
            return resultado;
        }

        Lista<Entidade> esquema = mEsquema.getObjetos();

        if (ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            resultado.errar("ZettaTabela : Coluna já existente - " + coluna_nome);
        }

        if(resultado.isErro()){
            return resultado;
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

        return resultado;
    }

    public Resultado<String,String> criar_coluna(String coluna_nome, AZTabelaColunaTipo coluna_tipo) {

        Resultado<String,String> resultado = new Resultado<String,String>("OK");

        FALHAR_SE(resultado,coluna_nome.contains(" "), "ZettaTabela : coluna não pode conter espacos !");
        FALHAR_SE(resultado,coluna_nome.contains("@"), "ZettaTabela : coluna não pode conter arrobas !");
        FALHAR_SE(resultado, coluna_nome.trim().isEmpty(), "ZettaTabela : coluna precisa ter nome !");

        if(resultado.isErro()){
            return resultado;
        }

        Lista<Entidade> esquema = mEsquema.getObjetos();

        if (ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            resultado.errar("ZettaTabela : Coluna já existente - " + coluna_nome);
        }

        if(resultado.isErro()){
            return resultado;
        }
        Entidade e_coluna = new Entidade();
        e_coluna.at("Formato", "DADOS");
        e_coluna.at("Nome", coluna_nome);
        e_coluna.at("Tipo", coluna_tipo.toString());
        e_coluna.at("Obrigatoria", "SIM");
        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        mEsquema.adicionar(e_coluna);
        return resultado;

    }

    public Resultado<String,String> criar_coluna_nao_obrigatoria(String coluna_nome, AZTabelaColunaTipo coluna_tipo) {

        Resultado<String,String> resultado = new Resultado<String,String>("OK");

        FALHAR_SE(resultado,coluna_nome.contains(" "), "ZettaTabela : coluna não pode conter espacos !");
        FALHAR_SE(resultado,coluna_nome.contains("@"), "ZettaTabela : coluna não pode conter arrobas !");
        FALHAR_SE(resultado, coluna_nome.trim().isEmpty(), "ZettaTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

        if (ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            resultado.errar("ZettaTabela : Coluna já existente - " + coluna_nome);
        }
        if(resultado.isErro()){
            return resultado;
        }

        Entidade e_coluna = new Entidade();
        e_coluna.at("Formato", "DADOS");
        e_coluna.at("Nome", coluna_nome);
        e_coluna.at("Tipo", coluna_tipo.toString());
        e_coluna.at("Obrigatoria", "NAO");
        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        mEsquema.adicionar(e_coluna);
        return resultado;

    }

    public Resultado<String,String> criar_acao_inserivel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoInserivel coluna_auto_inserivel) {

        Resultado<String,String> resultado = new Resultado<String,String>("OK");

        FALHAR_SE(resultado,coluna_nome.contains(" "), "ZettaTabela : coluna não pode conter espacos !");
        FALHAR_SE(resultado,coluna_nome.contains("@"), "ZettaTabela : coluna não pode conter arrobas !");
        FALHAR_SE(resultado, coluna_nome.trim().isEmpty(), "ZettaTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

        if (!ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            resultado.errar("ZettaTabela : Coluna nao existente - " + coluna_nome);
        }

        if (ENTT.EXISTE(esquema, "Regra", coluna_regra_nome)) {
            resultado.errar("ZettaTabela : Regra já existente - " + coluna_regra_nome);
        }

        if(resultado.isErro()){
            return resultado;
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
        return resultado;

    }

    public Resultado<String,String> criar_acao_atualizavel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoAtualizavel coluna_auto_atualizavel) {

        Resultado<String,String> resultado = new Resultado<String,String>("OK");

        FALHAR_SE(resultado,coluna_nome.contains(" "), "ZettaTabela : coluna não pode conter espacos !");
        FALHAR_SE(resultado,coluna_nome.contains("@"), "ZettaTabela : coluna não pode conter arrobas !");
        FALHAR_SE(resultado, coluna_nome.trim().isEmpty(), "ZettaTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

        if (!ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            resultado.errar("ZettaTabela : Coluna nao existente - " + coluna_nome);
        }

        if (ENTT.EXISTE(esquema, "Regra", coluna_regra_nome)) {
            resultado.errar("ZettaTabela : Regra já existente - " + coluna_regra_nome);
        }

        if(resultado.isErro()){
            return resultado;
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
        return resultado;

    }

    public Resultado<String,String> criar_verificador(String coluna_regra_nome, String coluna_nome, Entidade verificador) {

        Resultado<String,String> resultado = new Resultado<String,String>("OK");

        FALHAR_SE(resultado,coluna_nome.contains(" "), "ZettaTabela : coluna não pode conter espacos !");
        FALHAR_SE(resultado,coluna_nome.contains("@"), "ZettaTabela : coluna não pode conter arrobas !");
        FALHAR_SE(resultado,coluna_nome.trim().isEmpty(), "ZettaTabela : coluna precisa ter nome !");

        Lista<Entidade> esquema = mEsquema.getObjetos();

        if (!ENTT.EXISTE(esquema, "Nome", coluna_nome)) {
            resultado.errar("ZettaTabela : Coluna nao existente - " + coluna_nome);
        }

        if (ENTT.EXISTE(esquema, "Regra", coluna_regra_nome)) {
            resultado.errar("ZettaTabela : Regra já existente - " + coluna_regra_nome);
        }

        if(resultado.isErro()){
            return resultado;
        }

        Entidade e_coluna = new Entidade();
        e_coluna.at("Formato", "VERIFICAVEL");
        e_coluna.at("Regra", coluna_regra_nome);
        e_coluna.at("Coluna", coluna_nome);
        e_coluna.at("Tipo", "");
        e_coluna.at("DDC", Tronarko.getTronAgora().getTextoZerado());
        e_coluna.at("DDM", Tronarko.getTronAgora().getTextoZerado());

        verificador.at("Nome", "VERIFICADOR");

        verificador.tornar_primeiro("Nome");

        e_coluna.getEntidades().adicionar(verificador);

        mEsquema.adicionar(e_coluna);
        return resultado;

    }
}
