package libs.aqz;

import libs.aqz.colecao.AZInternamenteUTF8;
import libs.aqz.colecao.ColecaoUTF8;
import libs.aqz.utils.ItemDoBancoUTF8;
import libs.aqz.utils.ObservadorItemUTF8;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.meta_functional.FuncaoAlfa;

import java.nio.charset.StandardCharsets;

public class SuperAQZ_UTF8 {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 04 como AQZ
    // REIMPLEMENTADO EM : 2024 11 12 - SuperAQZ_UTF8

    public static void COLECOES_EXIBIR(String arquivo_banco) {

        for (String banco : COLECOES_LISTAR(arquivo_banco)) {
            fmt.print("{}", banco);
        }

    }

    public static void COLECOES_ORGANIZAR(String arquivo_banco, String colecao) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);
        if (!aqz.colecoes_existe(colecao)) {
            aqz.colecoes_criar(colecao);
        }
        aqz.fechar();
    }

    public static Lista<String> COLECOES_LISTAR(String arquivo_banco) {

        Lista<String> colecoes = new Lista<String>();

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        for (ColecaoUTF8 b : aqz.colecoes_listar()) {
            colecoes.adicionar(b.getNome());
        }

        aqz.fechar();

        return colecoes;
    }

    public static long QUANTIDADE(String arquivo_banco, String colecao) {

        long quantidade = 0;

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        quantidade = aqz.banco_obter(colecao).getItensContagem();

        aqz.fechar();

        return quantidade;
    }

    public static Lista<Entidade> COLECAO_ITENS(String arquivo_banco, String colecao) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoUTF8()));
        }

        aqz.fechar();

        return objetos;
    }

    public static void EXIBIR_COLECAO(String arquivo_banco, String colecao_nome) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTextoUTF8()));
        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }

    public static void INSERIR(String arquivo_banco, String colecao, Entidade objeto) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        boolean adicionado = aqz.colecoes_obter(colecao).adicionar(objeto);

        //  fmt.print("AQZ STATUS :: {}",adicionado);

        aqz.fechar();
    }


    public static void INSERIR_VARIOS(String arquivo_banco, String colecao_nome, Lista<Entidade> objetos) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        ColecaoUTF8 colecaoAntigamente = aqz.colecoes_obter(colecao_nome);

        for (Entidade objeto : objetos) {
            colecaoAntigamente.adicionar(objeto);
        }

        aqz.fechar();
    }

    public static void LIMPAR_TUDO(String arquivo_banco, String colecao) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        aqz.colecoes_obter(colecao).limpar();
        aqz.colecoes_obter(colecao).zerarSequencial();

        aqz.fechar();
    }

    public static void COLECOES_DESTRUIR(String arquivo_banco, String colecao) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        aqz.colecoes_remover(colecao);

        aqz.fechar();
    }


    public static boolean UNICO_EXISTE(String arquivo_banco, String colecao_nome, String att_nome, String att_valor) {

        boolean ret = false;

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (e.is(att_nome, att_valor)) {
                ret = true;
                break;
            }
        }


        aqz.fechar();

        return ret;
    }

    public static Entidade UNICO_OBTER(String arquivo_banco, String colecao_nome, String att_nome, String att_valor) {

        boolean ret = false;
        Entidade ret_e = null;

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (e.is(att_nome, att_valor)) {
                ret = true;
                ret_e = e;
                break;
            }
        }


        aqz.fechar();

        return ret_e;
    }


    public static void EXIBIR_COLECAO_TAMANHO(String arquivo_banco, String colecao_nome) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {

            String item_dados = item.lerTextoUTF8();

            Entidade e_item = ENTT.CRIAR_EM(objetos);
            e_item.at("Tamanho", item_dados.getBytes(StandardCharsets.UTF_8).length);
            e_item.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(e_item.atLong("Tamanho")));

        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }


    public static boolean UNICO_ATUALIZAR(String arquivo_banco, String colecao_nome, String att_nome, String att_valor, Entidade item_atualizar) {

        boolean ret = false;

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (e.is(att_nome, att_valor)) {


                item_atualizar.at("ID", e.at("ID"));
                item_atualizar.tornar_primeiro("ID");

                item.atualizarUTF8(item_atualizar);

                ret = true;
                break;
            }
        }

        if (!ret) {
            item_atualizar.tornar_primeiro("ID");
            aqz.colecoes_obter(colecao_nome).adicionar(item_atualizar);
        }

        aqz.fechar();

        return ret;
    }

    public static ObservadorItemUTF8 OBTER_OBSERVADOR(String arquivo_banco, String colecao_nome, String att_nome, String att_valor) {

        boolean ret = false;
        ObservadorItemUTF8 ret_e = null;

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (e.is(att_nome, att_valor)) {
                ret = true;
                ret_e = new ObservadorItemUTF8(arquivo_banco, item.getPonteiroDados(), e);
                break;
            }
        }


        aqz.fechar();

        return ret_e;
    }



    public static void EXIBIR_ESTRUTURA(String arquivo_banco) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> objetos_internos = new Lista<Entidade>();
        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (ParticaoMestre item : aqz.particoes_primarias()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_internos.adicionar(banco_item);
        }

        for (ParticaoMestre item : aqz.particoes_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_internos, "ESTRUTURA INTERNA");
        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "ESTRUTURA PÚBLICA");


    }


    public static Unico<String> EM_FILTRO_UNICO(String arquivo_banco, String colecao_nome, FuncaoAlfa<String, Entidade> analisando) {

        Unico<String> filtrado = new Unico<String>(Strings.IGUALAVEL());

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            String aqui = analisando.fazer(e);
            filtrado.item(aqui);

        }


        aqz.fechar();

        return filtrado;
    }



    public static void EM_ATUALIZACAO(String arquivo_banco, String colecao_nome, FuncaoAlfa<RefBool, Entidade> analisando) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (analisando.fazer(e).get()) {
                item.atualizarUTF8(e);
            }
        }


        aqz.fechar();


    }

    public static void EXIBIR_AMOSTRA(String arquivo_banco, String colecao) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados));

    }

    public static void EXIBIR_DESCRITORES(String arquivo_banco, String colecao) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();


        ENTT.EXIBIR_TABELA(ENTT.GET_DESCRITORES(dados));

    }


    // FUNCOES TOP

    public static void EXIBIR_DISPERSAO(String arquivo_banco, String colecao, String att_nome) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);
        ENTT.EXIBIR_TABELA(dispersao);

    }


    public static Lista<Entidade> OBTER_DISPERSAO(String arquivo_banco, String colecao, String att_nome) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);
        return dispersao;

    }


    public static Lista<Entidade> OBTER_DISPERSAO_SEM(String arquivo_banco, String colecao, String att_nome, String att_parte_valor) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);
        return dispersao;

    }

    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_2ZONAS(String arquivo_banco, String colecao, String att_nome, String att_parte_valor) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);

        Lista<Entidade> pp_zonas_auto = ENTT.GET_2ZONAS_DE(dispersao, att_nome);
        ENTT.ZONA_ANALISAR(dispersao, pp_zonas_auto, att_nome, "Quantidade");


        return pp_zonas_auto;

    }

    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_4ZONAS(String arquivo_banco, String colecao, String att_nome, String att_parte_valor) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);

        Lista<Entidade> pp_zonas_auto = ENTT.GET_4ZONAS_DE(dispersao, att_nome);
        ENTT.ZONA_ANALISAR(dispersao, pp_zonas_auto, att_nome, "Quantidade");


        return pp_zonas_auto;

    }

    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_ZONAS(String arquivo_banco, String colecao, String att_nome, String att_parte_valor, Lista<Entidade> zonas) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);

        ENTT.ZONA_ANALISAR(dispersao, zonas, att_nome, "Quantidade");


        return zonas;

    }


    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_ZONAS_DOUBLE(String arquivo_banco, String colecao, String att_nome, String att_parte_valor, Lista<Entidade> zonas) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.GET_4ZONAS_DOUBLE_DE(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);

        ENTT.ZONA_ANALISAR_DOUBLE(dispersao, zonas, att_nome, "Quantidade");


        return zonas;

    }

    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_2ZONAS_DOUBLE(String arquivo_banco, String colecao, String att_nome, String att_parte_valor) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        //ENTT.ORDENAR_TEXTO(dispersao,att_nome);

        Lista<Entidade> pp_zonas_auto = ENTT.GET_2ZONAS_DOUBLE_DE(dispersao, att_nome);
        ENTT.ZONA_ANALISAR_DOUBLE(dispersao, pp_zonas_auto, att_nome, "Quantidade");


        return pp_zonas_auto;

    }

    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_4ZONAS_DOUBLE(String arquivo_banco, String colecao, String att_nome, String att_parte_valor) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        //  ENTT.ORDENAR_TEXTO(dispersao,att_nome);

        Lista<Entidade> pp_zonas_auto = ENTT.GET_4ZONAS_DOUBLE_DE(dispersao, att_nome);
        ENTT.ZONA_ANALISAR_DOUBLE(dispersao, pp_zonas_auto, att_nome, "Quantidade");


        return pp_zonas_auto;

    }

    public static void EM_DESTRUICAO(String arquivo_banco, String colecao_nome, FuncaoAlfa<RefBool, Entidade> analisando) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (analisando.fazer(e).get()) {
                aqz.colecoes_obter(colecao_nome).remover(item);
            }
        }


        aqz.fechar();


    }


    public static long EM_CONTAGEM(String arquivo_banco, String colecao_nome, FuncaoAlfa<RefBool, Entidade> analisando) {

        long i = 0;

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());
            if (analisando.fazer(e).get()) {
                i += 1;
            }
        }


        aqz.fechar();

        return i;
    }


    public static long COLECAO_CONTAGEM(String arquivo_banco, String colecao) {

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);

        long i = aqz.colecoes_obter(colecao).getItensContagem();

        aqz.fechar();

        return i;
    }


    public static Lista<Entidade> EM_DISPERSAO(String arquivo_banco, String colecao_nome, FuncaoAlfa<String, Entidade> analisando) {

        Lista<Entidade> filtrado = new Lista<Entidade>();

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            String aqui = analisando.fazer(e);

            Entidade disp = ENTT.GET_SEMPRE(filtrado, "Item", aqui);
            disp.at("Quantidade", disp.atIntOuPadrao("Quantidade", 0) + 1);


        }


        aqz.fechar();

        return filtrado;
    }

    public static Unico<String> FILTRAR_UNICOS(String arquivo_banco, String colecao_nome, String att_nome) {

        Unico<String> filtrado = new Unico<String>(Strings.IGUALAVEL());

        AZInternamenteUTF8 aqz = new AZInternamenteUTF8(arquivo_banco);


        for (ItemDoBancoUTF8 item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTextoUTF8());

            filtrado.item(e.at(att_nome));
        }


        aqz.fechar();

        return filtrado;
    }


}
