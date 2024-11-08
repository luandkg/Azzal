package libs.aqz.tabela;

import apps.app_campeonatum.VERIFICADOR;
import libs.aqz.colecao.ColecaoUTF8;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.entt.Tag;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Resultado;
import libs.luan.Strings;
import libs.tronarko.Tronarko;

public class AQZTabela {

    private String mNome;

    private ColecaoUTF8 mEsquema;
    private ColecaoUTF8 mDados;

    public AQZTabela(String eNome, ColecaoUTF8 eEsquema, ColecaoUTF8 eDados) {
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

    public void criar_coluna_auto_inserivel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoInserivel coluna_auto_inserivel) {

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

    public void criar_coluna_auto_atualizavel(String coluna_regra_nome, String coluna_nome, AZTabelaColunaTipo coluna_tipo, AZTabelaAutoModificavel coluna_auto_atualizavel) {

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

        boolean adicao_valida = true;
        String adicao_erro = "";

        Entidade adicao_entidade = new Entidade();

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();
        Lista<Entidade> esquema_colunas_validar_antes = ENTT.COLETAR_E_COLETAR(esquema, "Formato", "DADOS", "Obrigatoria", "SIM");
        Lista<Entidade> esquema_colunas_auto_inserivel = ENTT.COLETAR(esquema, "Formato", "INSERIVEL");
        Lista<Entidade> esquema_colunas_primarias = ENTT.COLETAR(esquema, "Formato", "PRIMARIA");

        if (esquema.possuiObjetos()) {

            if (novo.tags().getQuantidade() == esquema_colunas_validar_antes.getQuantidade()) {

                for (Entidade coluna : esquema_colunas_validar_antes) {
                    if (novo.atributo_existe(coluna.at("Nome"))) {

                        String coluna_nome = coluna.at("Nome");
                        String coluna_tipo = coluna.at("Tipo");

                        String coluna_valor = novo.at(coluna.at("Nome"));

                        boolean validar_tipagem = validar_tipagem(coluna_tipo, coluna_valor);

                        if (!validar_tipagem) {
                            adicao_valida = false;
                            adicao_erro = "Tabela " + mNome + " - Coluna " + coluna_nome + "::" + coluna_tipo + " = Dados inválidos de tipagem !";
                            break;
                        }


                        adicao_entidade.at(coluna_nome, coluna_valor);
                    } else {
                        adicao_valida = false;
                        adicao_erro = "Tabela " + mNome + " - Coluna não encontrada : " + coluna.at("Nome") + " !";
                        break;
                    }
                }

            } else {
                adicao_valida = false;
                String atts_necessarios = "";
                for (Entidade c : esquema_colunas_validar_antes) {
                    atts_necessarios += c.at("Nome") + " ";
                }
                String att_adicionando = "";
                for (Tag c : novo.tags()) {
                    att_adicionando += c.getNome() + " ";
                }
                adicao_erro = "Tabela " + mNome + " - Entidade com campos invalidos <" + att_adicionando + "> <" + atts_necessarios + ">" + " !";
            }

        } else {
            adicao_valida = false;
            adicao_erro = "Tabela " + mNome + " - Sem Esquema !";
        }

        if (adicao_valida) {
            for (Entidade auto_inserivel : esquema_colunas_auto_inserivel) {

                String coluna_nome = auto_inserivel.at("Nome");
                String coluna_tipo = auto_inserivel.at("Tipo");
                String coluna_valoravel = auto_inserivel.at("Valoravel");

                String coluna_valor = "";

                if (Strings.isIgual(coluna_valoravel, "TRON")) {
                    coluna_valor = Tronarko.getTronAgora().getTextoZerado();
                } else if (Strings.isIgual(coluna_valoravel, "TOZTE")) {
                    coluna_valor = Tronarko.getTozte().getTextoZerado();
                } else if (Strings.isIgual(coluna_valoravel, "HAZDE")) {
                    coluna_valor = Tronarko.getHazde().getTextoZerado();
                }

                boolean validar_tipagem = validar_tipagem(coluna_tipo, coluna_valor);

                if (!validar_tipagem) {
                    adicao_valida = false;
                    adicao_erro = "Tabela " + mNome + " - Coluna " + coluna_nome + "::" + coluna_tipo + " = Dados inválidos de tipagem após auto_insersão !";
                    break;
                }

                adicao_entidade.at(coluna_nome, coluna_valor);

            }
        }


        if (adicao_valida) {

            for (ItemDoBancoUTF8 item : mEsquema.getItens()) {
                Entidade primaria = item.toEntidadeUTF8();
                if (primaria.is("Formato", "PRIMARIA")) {

                    long corrente = primaria.atLong("Corrente");

                    adicao_entidade.at(primaria.at("Nome"), corrente);
                    adicao_entidade.tornar_primeiro(primaria.at("Nome"));
                    adicao_entidade.tornar_primeiro("@ID");

                    corrente += primaria.atLong("Passo");
                    primaria.at("Corrente", corrente);

                    item.atualizarUTF8(primaria);
                }
            }


            mDados.adicionarUTF8ComIDInterno(adicao_entidade);
            return Resultado.OK(true);
        } else {
            return Resultado.FALHAR("AQZTabela Erro : " + adicao_erro);

        }

    }


    public boolean validar_tipagem(String coluna_tipo, String coluna_valor) {

        boolean adicao_valida = true;

        if (coluna_tipo.contentEquals("TEXTO")) {
        } else if (coluna_tipo.contentEquals("INTEIRO")) {
            if (!Matematica.isNumeroInteiro(coluna_valor)) {
                adicao_valida = false;
            }
        } else if (coluna_tipo.contentEquals("REAL")) {
            if (!Matematica.isNumero(coluna_valor)) {
                adicao_valida = false;
            }
        } else if (coluna_tipo.contentEquals("LOGICO")) {
            if (Strings.isDiferente(coluna_valor, "SIM") && Strings.isDiferente(coluna_valor, "NAO")) {
                adicao_valida = false;
            }
        }

        return adicao_valida;
    }

}
