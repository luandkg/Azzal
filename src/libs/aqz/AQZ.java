package libs.aqz;

import libs.armazenador.Armazenador;
import libs.armazenador.Banco;
import libs.armazenador.ItemDoBanco;
import libs.bs.Colecao;
import libs.bs.ObservadorItem;
import libs.bs.Sequenciador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.meta_functional.FuncaoAlfa;

import java.nio.charset.StandardCharsets;

public class AQZ {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 04
    // ATUALIZADO : 2024 01 04


    public static void COLECOES_CRIAR(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);
        aqz.colecoes_criar(colecao);
        aqz.fechar();
    }

    public static void COLECOES_ORGANIZAR(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);
        if (!aqz.colecoes_existe(colecao)) {
            aqz.colecoes_criar(colecao);
        }
        aqz.fechar();
    }

    public static Lista<String> COLECOES_LISTAR(String arquivo_banco) {

        Lista<String> colecoes = new Lista<String>();

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        for (Banco b : aqz.colecoes_listar()) {
            colecoes.adicionar(b.getNome());
        }

        aqz.fechar();

        return colecoes;
    }

    public static void COLECOES_EXIBIR(String arquivo_banco) {

        for (String banco : COLECOES_LISTAR(arquivo_banco)) {
            fmt.print("{}", banco);
        }

    }

    public static void INSERIR(String arquivo_banco, String colecao, Entidade objeto) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        boolean adicionado = aqz.colecoes_obter(colecao).adicionar(objeto);

        //  fmt.print("AQZ STATUS :: {}",adicionado);

        aqz.fechar();
    }

    public static void INSERIR_VARIOS(String arquivo_banco, String colecao_nome, Lista<Entidade> objetos) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Colecao colecao = aqz.colecoes_obter(colecao_nome);

        for (Entidade objeto : objetos) {
            colecao.adicionar(objeto);
        }

        aqz.fechar();
    }

    public static Lista<Entidade> COLECAO_ITENS(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
        }

        aqz.fechar();

        return objetos;
    }

    public static void EXIBIR_COLECAO(String arquivo_banco, String colecao_nome) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }


    public static void EXIBIR_COLECAO_TAMANHO(String arquivo_banco, String colecao_nome) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {

            String item_dados = item.lerTexto();

            Entidade e_item = ENTT.CRIAR_EM(objetos);
            e_item.at("Tamanho", item_dados.getBytes(StandardCharsets.UTF_8).length);
            e_item.at("TamanhoFormatado", fmt.formatar_tamanho_precisao_dupla(e_item.atLong("Tamanho")));

        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }

    public static long QUANTIDADE(String arquivo_banco, String colecao) {

        long quantidade = 0;

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        quantidade = aqz.banco_obter(colecao).getItensContagem();

        aqz.fechar();

        return quantidade;
    }

    public static void LIMPAR_TUDO(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        aqz.colecoes_obter(colecao).limpar();
        aqz.colecoes_obter(colecao).zerarSequencial();

        aqz.fechar();
    }

    public static void COLECOES_DESTRUIR(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        aqz.colecoes_remover(colecao);

        aqz.fechar();
    }

    public static void EXIBIR_COLECAO_PRIMARIA(String arquivo_banco, String colecao_nome) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBanco item : aqz.primarios_colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
        }

        ENTT.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }

    public static void AUTO_ANALISAR(String arquivo_banco) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        aqz.auto_analisar();

        aqz.fechar();

    }

    public static void ANALISAR(String arquivo_banco) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        aqz.analisar();

        aqz.fechar();

    }

    public static void DEFINIR_VIEW(String arquivo_banco, String view_nome, String colecao_nome, Lista<String> colunas) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        if (aqz.views_existe(view_nome)) {
            aqz.views_remover(view_nome);
        }

        aqz.views_criar(view_nome, colecao_nome, colunas);

        aqz.fechar();
    }

    public static void EXIBIR_VIEW(String arquivo_banco, String view_nome) {

        view_nome = view_nome.toUpperCase();

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Entidade view = aqz.views_obter(view_nome);

        Lista<String> colunas = new Lista<String>();
        String colecao_nome = view.at("Banco");

        for (Entidade col : view.getEntidades()) {
            colunas.adicionar(col.at("Nome"));
        }

        Lista<Entidade> objetos = new Lista<Entidade>();

        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade obj = ENTT.PARSER_ENTIDADE(item.lerTexto());

            // fmt.print(">>{}",item.lerTexto());

            Entidade obj_em_view = ENTT.CRIAR_EM(objetos);
            for (String coluna : colunas) {
                obj_em_view.at(coluna, obj.at(coluna));
            }

        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos, "@VIEW - " + view_nome);

    }

    public static void REMOVER_VIEW(String arquivo_banco, String view_nome) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        if (aqz.views_existe(view_nome)) {
            aqz.views_remover(view_nome);
        }

        aqz.fechar();
    }

    public static void EXIBIR_TUDO(String arquivo_banco) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        for (Banco banco : aqz.primarios_colecoes()) {

            Lista<Entidade> objetos = new Lista<Entidade>();
            for (ItemDoBanco item : banco.getItens()) {
                objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
            }

            ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "INTERNO - " + banco.getNome());

        }


        for (Banco banco : aqz.colecoes_listar()) {

            Lista<Entidade> objetos = new Lista<Entidade>();
            for (ItemDoBanco item : banco.getItens()) {
                objetos.adicionar(ENTT.PARSER_ENTIDADE(item.lerTexto()));
            }

            ENTT.EXIBIR_TABELA_COM_TITULO(objetos, "PUBLICO - " + banco.getNome());

        }


        aqz.fechar();

    }


    public static boolean UNICO_EXISTE(String arquivo_banco, String colecao_nome, String att_nome, String att_valor) {

        boolean ret = false;

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());
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

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e.is(att_nome, att_valor)) {
                ret = true;
                ret_e = e;
                break;
            }
        }


        aqz.fechar();

        return ret_e;
    }

    public static boolean UNICO_ATUALIZAR(String arquivo_banco, String colecao_nome, String att_nome, String att_valor, Entidade item_atualizar) {

        boolean ret = false;

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e.is(att_nome, att_valor)) {

                item_atualizar.at(att_nome, att_valor);

                item_atualizar.tornar_primeiro(att_nome);
                item_atualizar.tornar_primeiro("ID");

                item.atualizar(item_atualizar);

                ret = true;
                break;
            }
        }

        if (!ret) {
            item_atualizar.at(att_nome, att_valor);

            item_atualizar.tornar_primeiro(att_nome);
            item_atualizar.tornar_primeiro("ID");

            aqz.colecoes_obter(colecao_nome).adicionar(item_atualizar);
        }

        aqz.fechar();

        return ret;
    }

    public static ObservadorItem OBTER_OBSERVADOR(String arquivo_banco, String colecao_nome, String att_nome, String att_valor) {

        boolean ret = false;
        ObservadorItem ret_e = null;

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (e.is(att_nome, att_valor)) {
                ret = true;
                ret_e = new ObservadorItem(arquivo_banco, item.getPonteiroDados(), e);
                break;
            }
        }


        aqz.fechar();

        return ret_e;
    }

    public static Unico<String> EM_FILTRO_UNICO(String arquivo_banco, String colecao_nome, FuncaoAlfa<String, Entidade> analisando) {

        Unico<String> filtrado = new Unico<String>(Strings.IGUALAVEL());

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());

            String aqui = analisando.fazer(e);
            filtrado.item(aqui);

        }


        aqz.fechar();

        return filtrado;
    }

    public static Lista<Entidade> EM_DISPERSAO(String arquivo_banco, String colecao_nome, FuncaoAlfa<String, Entidade> analisando) {

        Lista<Entidade> filtrado = new Lista<Entidade>();

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());

            String aqui = analisando.fazer(e);

            Entidade disp = ENTT.GET_SEMPRE(filtrado, "Item", aqui);
            disp.at("Quantidade", disp.atIntOuPadrao("Quantidade", 0) + 1);


        }


        aqz.fechar();

        return filtrado;
    }

    public static void EM_DESTRUICAO(String arquivo_banco, String colecao_nome, FuncaoAlfa<RefBool, Entidade> analisando) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (analisando.fazer(e).get()) {
                aqz.colecoes_obter(colecao_nome).remover(item);
            }
        }


        aqz.fechar();


    }

    public static void EM_ATUALIZACAO(String arquivo_banco, String colecao_nome, FuncaoAlfa<RefBool, Entidade> analisando) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (analisando.fazer(e).get()) {
                item.atualizar(e);
            }
        }


        aqz.fechar();


    }

    public static long EM_CONTAGEM(String arquivo_banco, String colecao_nome, FuncaoAlfa<RefBool, Entidade> analisando) {

        long i = 0;

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (analisando.fazer(e).get()) {
                i += 1;
            }
        }


        aqz.fechar();

        return i;
    }


    public static long COLECAO_CONTAGEM(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        long i = aqz.colecoes_obter(colecao).getItensContagem();

        aqz.fechar();

        return i;
    }

    public static void EXIBIR_AMOSTRA(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados));

    }

    public static void EXIBIR_DESCRITORES(String arquivo_banco, String colecao) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();


        ENTT.EXIBIR_TABELA(ENTT.GET_DESCRITORES(dados));

    }

    public static void EXIBIR_DISPERSAO(String arquivo_banco, String colecao, String att_nome) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);
        ENTT.EXIBIR_TABELA(dispersao);

    }


    public static Lista<Entidade> OBTER_DISPERSAO(String arquivo_banco, String colecao, String att_nome) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);
        return dispersao;

    }


    public static Lista<Entidade> OBTER_DISPERSAO_SEM(String arquivo_banco, String colecao, String att_nome, String att_parte_valor) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);
        return dispersao;

    }

    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_2ZONAS(String arquivo_banco, String colecao, String att_nome, String att_parte_valor) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

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

        AZInternamente aqz = new AZInternamente(arquivo_banco);

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

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);

        ENTT.ZONA_ANALISAR(dispersao, zonas, att_nome, "Quantidade");


        return zonas;

    }


    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_ZONAS_DOUBLE(String arquivo_banco, String colecao, String att_nome, String att_parte_valor, Lista<Entidade> zonas) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.GET_4ZONAS_DOUBLE_DE(dados, att_nome);
        ENTT.ORDENAR_TEXTO(dispersao, att_nome);

        ENTT.ZONA_ANALISAR_DOUBLE(dispersao, zonas, att_nome, "Quantidade");


        return zonas;

    }

    public static Lista<Entidade> OBTER_DISPERSAO_SEM_EM_2ZONAS_DOUBLE(String arquivo_banco, String colecao, String att_nome, String att_parte_valor) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

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

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> dados = aqz.colecoes_obter(colecao).getObjetos();

        aqz.fechar();

        ENTT.SUBSTITUIR_PARTE(dados, att_nome, att_parte_valor, "");

        Lista<Entidade> dispersao = ENTT.DISPERSAO(dados, att_nome);
        //  ENTT.ORDENAR_TEXTO(dispersao,att_nome);

        Lista<Entidade> pp_zonas_auto = ENTT.GET_4ZONAS_DOUBLE_DE(dispersao, att_nome);
        ENTT.ZONA_ANALISAR_DOUBLE(dispersao, pp_zonas_auto, att_nome, "Quantidade");


        return pp_zonas_auto;

    }


    public static void EXIBIR_ESTRUTURA(String arquivo_banco) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> objetos_internos = new Lista<Entidade>();
        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (Banco item : aqz.primarios_colecoes()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_internos.adicionar(banco_item);
        }

        for (Banco item : aqz.colecoes_listar()) {
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

    public static void EXIBIR_ESTRUTURA_PUBLICA(String arquivo_banco) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (Banco item : aqz.colecoes_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", item.getNome());
            banco_item.at("Itens", item.getItensContagem());
            banco_item.at("Usabilidade", item.getUsabilidade());
            banco_item.at("Disponibilidade", item.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(item.getTamanho()));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "ESTRUTURA PÚBLICA");


    }

    public static void EXIBIR_ESTRUTURA_PUBLICA_DETALHADA(String arquivo_banco) {

        AZInternamente aqz = new AZInternamente(arquivo_banco);

        Lista<Entidade> objetos_publicos = new Lista<Entidade>();



        for (Banco banco : aqz.colecoes_listar()) {
            Entidade banco_item = new Entidade();

            banco_item.at("Nome", banco.getNome());
            banco_item.at("Itens", banco.getItensContagem());
            banco_item.at("Usabilidade", banco.getUsabilidade());
            banco_item.at("Disponibilidade", banco.getDisponibilidade());
            banco_item.at("Tamanho", fmt.formatar_tamanho(banco.getTamanho()));

            boolean isPrimeiro = true;
            long menor = 0;
            long maior = 0;

            for (ItemDoBanco item : banco.getItens()) {
                String item_dados = item.lerTexto();
                if (isPrimeiro) {
                    long tam = item_dados.getBytes(StandardCharsets.UTF_8).length;
                    menor = tam;
                    maior = tam;
                } else {
                    long tam = item_dados.getBytes(StandardCharsets.UTF_8).length;
                    if (tam < menor) {
                        menor = tam;
                    }
                    if (tam > maior) {
                        maior = tam;
                    }
                }
                isPrimeiro = false;
            }

            banco_item.at("Item.menor.tamanho",menor);
            banco_item.at("Item.menor.tamanho_formatado", fmt.formatar_tamanho_precisao_dupla(menor));
            banco_item.at("Item.maior.tamanho",maior);
            banco_item.at("Item.maior.tamanho_formatado", fmt.formatar_tamanho_precisao_dupla(maior));

            objetos_publicos.adicionar(banco_item);
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(objetos_publicos, "ESTRUTURA PÚBLICA");


    }


    public static Unico<String> FILTRAR_UNICOS(String arquivo_banco, String colecao_nome, String att_nome) {

        Unico<String> filtrado = new Unico<String>(Strings.IGUALAVEL());

        AZInternamente aqz = new AZInternamente(arquivo_banco);


        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            Entidade e = ENTT.PARSER_ENTIDADE(item.lerTexto());

            filtrado.item(e.at(att_nome));
        }


        aqz.fechar();

        return filtrado;
    }


    public static Lista<Entidade> GET_VOLUMES(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Lista<Entidade> e_volumes = aqz.volume_listar();
        aqz.fechar();

        return e_volumes;
    }

    public static void CRIAR_VOLUME(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        aqz.volume_criar();
        aqz.fechar();

    }

    public static Lista<Entidade> GET_VOLUMES_DADOS(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Lista<Entidade> e_volumes = aqz.volume_listar_dados();
        aqz.fechar();

        return e_volumes;
    }

    public static void VOLUMES_ZERAR(String arquivo_banco) {

        Armazenador mArmazenador = new Armazenador(arquivo_banco);
        AZVolumeInternamente aqz = new AZVolumeInternamente(mArmazenador);
        aqz.volumes_zerar();

        Banco mSequencias = Sequenciador.organizar_banco(mArmazenador, "@Sequencias");
        Sequenciador.organizar_sequencial(mSequencias, "@Volume.ChaveUnica");
        Sequenciador.zerar_sequencial(mSequencias, "@Volume.ChaveUnica");

        mArmazenador.fechar();

    }

    public static boolean TEM_BLOCO_DISPONIVEL(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        boolean tem = aqz.volume_tem_bloco_disponivel();
        aqz.fechar();

        return tem;
    }

    public static Opcional<Long> ARQUIVO_ALOCAR(String arquivo_banco, String eArquivoNome, String eConteudo) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Opcional<Long> tem = aqz.arquivo_alocar(eArquivoNome, eConteudo);
        aqz.fechar();

        return tem;
    }

    public static Opcional<Long> ARQUIVO_ALOCAR(String arquivo_banco, String eArquivoNome, byte[] eConteudo) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Opcional<Long> tem = aqz.arquivo_alocar(eArquivoNome, eConteudo);
        aqz.fechar();

        return tem;
    }

    public static void ARQUIVO_DUMP(String arquivo_banco) {

        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        aqz.arquivos_dump();
        aqz.fechar();

    }

    public static Opcional<Entidade> ARQUIVO_PROCURAR(String arquivo_banco, String proc_arquivo_nome) {
        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Opcional<Entidade> op = aqz.procurar_arquivo(proc_arquivo_nome);
        aqz.fechar();

        return op;
    }

    public static Opcional<AQZArquivoExternamente> ARQUIVO_PROCURAR_EXTERNAMENTE(String arquivo_banco, String proc_arquivo_nome) {
        AZVolumeInternamente aqz = new AZVolumeInternamente(arquivo_banco);
        Opcional<AQZArquivoExternamente> op = aqz.procurar_arquivo_externamente(proc_arquivo_nome);
        aqz.fechar();

        return op;
    }

    public static void VOLUMES_DUMP(String arquivo_banco) {
        Lista<Entidade> volumes_dados = AQZ.GET_VOLUMES_DADOS(arquivo_banco);
        ENTT.EXIBIR_TABELA_COM_NOME(volumes_dados, "@VOLUMES");
    }


    public static long VOLUME_BLOCOS_LIVRES(String arquivo_banco) {
        Lista<Entidade> volumes_dados = AQZ.GET_VOLUMES_DADOS(arquivo_banco);
        return ENTT.ATRIBUTO_LONG_SOMAR(volumes_dados, "Objetos.Livre");
    }

}
