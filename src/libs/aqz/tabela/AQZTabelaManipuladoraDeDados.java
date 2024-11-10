package libs.aqz.tabela;

import libs.aqz.colecao.ColecaoUTF8Antigamente;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.armazenador.Armazenador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.entt.Tag;
import libs.luan.*;
import libs.tronarko.Tronarko;

public class AQZTabelaManipuladoraDeDados {

    public static Resultado<Boolean, String> adicionar(Armazenador mArmazenador, ColecaoUTF8Antigamente mEsquema, ColecaoUTF8Antigamente mDados, String mNome, Entidade novo) {

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
                            adicao_erro = "Tabela " + mNome + " - Coluna " + coluna_nome + "::" + coluna_tipo + " = Dados inválidos de tipagem ! :: ( " + coluna_valor + " )";
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

                if (novo.tags().getQuantidade() < esquema_colunas_validar_antes.getQuantidade()) {

                    String att_faltou = "";

                    for (Entidade c : esquema_colunas_validar_antes) {
                        String coluna_nome = c.at("Nome");
                        if (!novo.atributo_existe(coluna_nome)) {
                            att_faltou += coluna_nome + " ";
                        }
                    }

                    adicao_valida = false;
                    adicao_erro = "Tabela " + mNome + " - Entidade com campos faltantes < " + att_faltou + ">  !";

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


            }

        } else {
            adicao_valida = false;
            adicao_erro = "Tabela " + mNome + " - Sem Esquema !";
        }


        if (adicao_valida) {
            Lista<Entidade> esquema_colunas_verificar = ENTT.COLETAR(esquema, "Formato", "VERIFICAVEL");

            for (Entidade verificar : esquema_colunas_verificar) {

                // fmt.print("-->> VERIFICAR :: {}", verificar.at("Regra"));

                Entidade verificador = ENTT.GET_SEMPRE(verificar.getEntidades(), "Nome", "VERIFICADOR");
                //  ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(verificador));

                String verificar_coluna = verificar.at("Coluna");
                String verificar_funcao = verificador.at("Tipo");
                String verificar_valor = verificador.at("Valor");

                // fmt.print("\t ++ Coluna    : {}", verificar_coluna);
                //  fmt.print("\t ++ Funcao    : {}", verificar_funcao);
                // fmt.print("\t ++ Valor     : {}", verificar_valor);


                String coluna_conteudo = adicao_entidade.at(verificar_coluna);
                //   fmt.print("\t ++ Conteudo  : {}", coluna_conteudo);

                boolean verificando = false;

                if (Strings.isIgual(verificar_funcao, "Valor::Tipo") && Strings.isIgual(verificar_valor, "UNICO")) {
                    if (!validar_existe_inserido(mDados, verificar_coluna, coluna_conteudo)) {
                        verificando = true;
                    }
                } else {
                    verificando = validar_verificacao(verificar_funcao, verificar_valor, coluna_conteudo);
                }


                //  fmt.print("\t ++ Resposta  : {}", Portugues.VALIDAR(verificando, "OK", "PROBLEMA"));

                if (!verificando) {
                    adicao_valida = false;
                    adicao_erro = "Tabela " + mNome + " - Coluna " + verificar_coluna + " -->> Verificação falhou ( " + verificar_funcao + " ) = " + coluna_conteudo + " :: " + verificar_valor + " !";
                    break;
                }

            }

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

            for (Entidade primaria : esquema_colunas_primarias) {

                long corrente = primaria.atLong("Corrente");

                adicao_entidade.at(primaria.at("Nome"), corrente);
                adicao_entidade.tornar_primeiro(primaria.at("Nome"));
                adicao_entidade.tornar_primeiro("@ID");

                corrente += primaria.atLong("Passo");
                primaria.at("Corrente", corrente);

                mEsquema.indexado_atualizar(primaria.atInt("ID"), primaria);

            }


            Opcional<Long> op_endereco = mDados.adicionarUTF8ComIDInterno(adicao_entidade);

            if (op_endereco.isOK()) {
                ItemDoBancoUTF8 item = mArmazenador.getItemDiretoUTF8(op_endereco.get());

                if (item.existe()) {
                    adicao_entidade.at("@PTR", op_endereco.get());

                    adicao_entidade.tornar_primeiro("@ID");
                    adicao_entidade.tornar_primeiro("@PTR");

                    item.atualizarUTF8(adicao_entidade);
                }

            }

            return Resultado.OK(true);
        } else {
            return Resultado.FALHAR("AQZTabela Erro : " + adicao_erro);

        }

    }

    public static Resultado<Boolean, String> atualizar(ColecaoUTF8Antigamente mEsquema, ColecaoUTF8Antigamente mDados, String mNome, ItemDoBancoUTF8 eItemExistente, Entidade novo) {

        boolean adicao_valida = true;
        String adicao_erro = "";

        Entidade adicao_entidade = new Entidade();

        Lista<Entidade> esquema = mEsquema.getObjetosUTF8();
        Lista<Entidade> esquema_colunas_validar_antes = ENTT.COLETAR_E_COLETAR(esquema, "Formato", "DADOS", "Obrigatoria", "SIM");
        Lista<Entidade> esquema_colunas_nao_obrigatorios = ENTT.COLETAR_E_COLETAR(esquema, "Formato", "DADOS", "Obrigatoria", "NAO");

        Lista<Entidade> esquema_colunas_auto_atualizavel = ENTT.COLETAR(esquema, "Formato", "ATUALIZAVEL");
        Lista<Entidade> esquema_colunas_primarias = ENTT.COLETAR(esquema, "Formato", "PRIMARIA");

        if (novo.atributo_existe("@ID")) {
            adicao_valida = false;
            adicao_erro = "Tabela " + mNome + " - Entidade atualizável não pode ter o atributo primario @ID  !";
            return Resultado.FALHAR("AQZTabela Erro : " + adicao_erro);
        }


        if (esquema_colunas_primarias.possuiObjetos()) {
            for (Entidade coluna : esquema_colunas_primarias) {
                if (novo.atributo_existe(coluna.at("Nome"))) {
                    adicao_valida = false;
                    adicao_erro = "Tabela " + mNome + " - Coluna " + coluna.at("Nome") + "::PRIMARIA" + " = Não pode ser atualizada !";
                    break;
                }
            }
        }

        if (!adicao_valida) {
            return Resultado.FALHAR("AQZTabela Erro : " + adicao_erro);
        }

        Entidade dados_existentes = eItemExistente.toEntidadeUTF8();


        if (esquema.possuiObjetos()) {

            if (novo.tags().getQuantidade() == esquema_colunas_validar_antes.getQuantidade() + esquema_colunas_nao_obrigatorios.getQuantidade()) {

                for (Entidade coluna : esquema_colunas_validar_antes) {
                    if (novo.atributo_existe(coluna.at("Nome"))) {

                        String coluna_nome = coluna.at("Nome");
                        String coluna_tipo = coluna.at("Tipo");

                        String coluna_valor = novo.at(coluna.at("Nome"));

                        boolean validar_tipagem = validar_tipagem(coluna_tipo, coluna_valor);

                        if (!validar_tipagem) {
                            adicao_valida = false;
                            adicao_erro = "Tabela " + mNome + " - Coluna " + coluna_nome + "::" + coluna_tipo + " = Dados inválidos de tipagem ! :: ( " + coluna_valor + " )";
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

                if (novo.tags().getQuantidade() < esquema_colunas_validar_antes.getQuantidade()) {

                    String att_faltou = "";

                    for (Entidade c : esquema_colunas_validar_antes) {
                        String coluna_nome = c.at("Nome");
                        if (!novo.atributo_existe(coluna_nome)) {
                            att_faltou += coluna_nome + " ";
                        }
                    }

                    adicao_valida = false;
                    adicao_erro = "Tabela " + mNome + " - Entidade com campos faltantes < " + att_faltou + ">  !";

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


            }

        } else {
            adicao_valida = false;
            adicao_erro = "Tabela " + mNome + " - Sem Esquema !";
        }


        if (adicao_valida) {
            Lista<Entidade> esquema_colunas_verificar = ENTT.COLETAR(esquema, "Formato", "VERIFICAVEL");

            for (Entidade verificar : esquema_colunas_verificar) {

                // fmt.print("-->> VERIFICAR :: {}", verificar.at("Regra"));

                Entidade verificador = ENTT.GET_SEMPRE(verificar.getEntidades(), "Nome", "VERIFICADOR");
                //  ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(verificador));

                String verificar_coluna = verificar.at("Coluna");
                String verificar_funcao = verificador.at("Tipo");
                String verificar_valor = verificador.at("Valor");

                // fmt.print("\t ++ Coluna    : {}", verificar_coluna);
                //  fmt.print("\t ++ Funcao    : {}", verificar_funcao);
                // fmt.print("\t ++ Valor     : {}", verificar_valor);


                String coluna_conteudo = adicao_entidade.at(verificar_coluna);
                //   fmt.print("\t ++ Conteudo  : {}", coluna_conteudo);

                boolean verificando = false;

                if (Strings.isIgual(verificar_funcao, "Valor::Tipo") && Strings.isIgual(verificar_valor, "UNICO")) {

                    if (Strings.isIgual(dados_existentes.at(verificar_coluna), coluna_conteudo)) {
                        verificando = true;
                    } else {
                        if (!validar_existe_inserido(mDados, verificar_coluna, coluna_conteudo)) {
                            verificando = true;
                        }
                    }


                } else {
                    verificando = validar_verificacao(verificar_funcao, verificar_valor, coluna_conteudo);
                }


                //  fmt.print("\t ++ Resposta  : {}", Portugues.VALIDAR(verificando, "OK", "PROBLEMA"));

                if (!verificando) {
                    adicao_valida = false;
                    adicao_erro = "Tabela " + mNome + " - Coluna " + verificar_coluna + " -->> Verificação falhou ( " + verificar_funcao + " ) = " + coluna_conteudo + " :: " + verificar_valor + " !";
                    break;
                }

            }

        }


        if (adicao_valida) {
            for (Entidade auto_atualizavel : esquema_colunas_auto_atualizavel) {

                String coluna_nome = auto_atualizavel.at("Nome");
                String coluna_tipo = auto_atualizavel.at("Tipo");
                String coluna_valoravel = auto_atualizavel.at("Valoravel");

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
                    adicao_erro = "Tabela " + mNome + " - Coluna " + coluna_nome + "::" + coluna_tipo + " = Dados inválidos de tipagem após auto_atualização !";
                    break;
                }

                adicao_entidade.at(coluna_nome, coluna_valor);

            }
        }


        if (adicao_valida) {


            novo.at("@ID", dados_existentes.at("@ID"));
            novo.at("@PTR", eItemExistente.getPonteiroDados());

            for (Entidade coluna : esquema_colunas_primarias) {
                novo.at(coluna.at("Nome"), dados_existentes.at(coluna.at("Nome")));
                novo.tornar_primeiro(coluna.at("Nome"));
            }

            for (Tag coluna : adicao_entidade.tags()) {
                novo.at(coluna.getNome(), coluna.getValor());
            }

            novo.tornar_primeiro("@ID");
            novo.tornar_primeiro("@PTR");

            eItemExistente.atualizarUTF8(novo);

            return Resultado.OK(true);
        } else {
            return Resultado.FALHAR("AQZTabela Erro : " + adicao_erro);

        }

    }


    public static boolean validar_tipagem(String coluna_tipo, String coluna_valor) {

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


    public static boolean validar_verificacao(String verificar_funcao, String verificar_valor, String coluna_conteudo) {
        boolean ret = false;

        if (Strings.isIgual(verificar_funcao, "Texto::TamanhoMaior")) {

            if (Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int texto_tamanho = coluna_conteudo.length();

                if (texto_tamanho > verificar_valor_i) {
                    ret = true;
                }
            }


        } else if (Strings.isIgual(verificar_funcao, "Texto::TamanhoMenor")) {

            if (Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int texto_tamanho = coluna_conteudo.length();

                if (texto_tamanho < verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Texto::TamanhoIgual")) {

            if (Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int texto_tamanho = coluna_conteudo.length();

                if (texto_tamanho == verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Texto::NaoConter")) {

            if (Strings.isIgual(verificar_valor, "NUMEROS")) {
                ret = !Strings.temDigito(coluna_conteudo);
            }

        } else if (Strings.isIgual(verificar_funcao, "Texto::Formato")) {

            if (Strings.isIgual(verificar_valor, "FRASE")) {
                ret = !coluna_conteudo.contains("\n");
            }

        } else if (Strings.isIgual(verificar_funcao, "Texto::Existe")) {


            Lista<String> valores_possiveis = new Lista<String>();

            for (String item : Strings.DIVIDIR_POR(verificar_valor, "\t")) {
                item = item.replace("\t", "");
                valores_possiveis.adicionar(item);
            }

            if (valores_possiveis.existe(Strings.IGUALAVEL(), coluna_conteudo)) {
                ret = true;
            }


        } else if (Strings.isIgual(verificar_funcao, "Inteiro::MaiorIgual")) {

            if (Matematica.isNumeroInteiro(coluna_conteudo) && Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int conteudo_valor_i = Integer.parseInt(coluna_conteudo);

                if (conteudo_valor_i >= verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Inteiro::MenorIgual")) {

            if (Matematica.isNumeroInteiro(coluna_conteudo) && Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int conteudo_valor_i = Integer.parseInt(coluna_conteudo);

                if (conteudo_valor_i <= verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Inteiro::Maior")) {

            if (Matematica.isNumeroInteiro(coluna_conteudo) && Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int conteudo_valor_i = Integer.parseInt(coluna_conteudo);

                if (conteudo_valor_i > verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Inteiro::Menor")) {

            if (Matematica.isNumeroInteiro(coluna_conteudo) && Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int conteudo_valor_i = Integer.parseInt(coluna_conteudo);

                if (conteudo_valor_i < verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Inteiro::Igual")) {

            if (Matematica.isNumeroInteiro(coluna_conteudo) && Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int conteudo_valor_i = Integer.parseInt(coluna_conteudo);

                if (conteudo_valor_i == verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Inteiro::Diferente")) {

            if (Matematica.isNumeroInteiro(coluna_conteudo) && Matematica.isNumeroInteiro(verificar_valor)) {

                int verificar_valor_i = Integer.parseInt(verificar_valor);
                int conteudo_valor_i = Integer.parseInt(coluna_conteudo);

                if (conteudo_valor_i != verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Real::MaiorIgual")) {

            if (Matematica.isNumero(coluna_conteudo) && Matematica.isNumero(verificar_valor)) {

                double verificar_valor_i = Double.parseDouble(verificar_valor);
                double conteudo_valor_i = Double.parseDouble(coluna_conteudo);

                if (conteudo_valor_i >= verificar_valor_i) {
                    ret = true;
                }
            }

        } else if (Strings.isIgual(verificar_funcao, "Real::MenorIgual")) {

            if (Matematica.isNumero(coluna_conteudo) && Matematica.isNumero(verificar_valor)) {

                double verificar_valor_i = Double.parseDouble(verificar_valor);
                double conteudo_valor_i = Double.parseDouble(coluna_conteudo);

                if (conteudo_valor_i <= verificar_valor_i) {
                    ret = true;
                }
            }

        }

        return ret;
    }


    public static boolean validar_existe_inserido(ColecaoUTF8Antigamente mDados, String att_nome, String att_valor) {

        boolean ret = false;

        for (ItemDoBancoUTF8 item : mDados.getItens()) {
            Entidade obj = item.toEntidadeUTF8();
            if (obj.is(att_nome, att_valor)) {
                ret = true;
                break;
            }
        }


        return ret;
    }

}
