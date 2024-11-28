package libs.zetta.tabelas;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.ItemAlocado;
import libs.luan.Lista;
import libs.luan.Matematica;
import libs.luan.Strings;
import libs.zetta.persistencia.ArmazemManipulador;

public class Validador {

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


    public static boolean validar_existe_inserido(ArmazemManipulador mDados, String att_nome, String att_valor) {

        boolean ret = false;

        for (ItemAlocado item : mDados.getItens()) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (obj.is(att_nome, att_valor)) {
                ret = true;
                break;
            }
        }


        return ret;
    }

}
