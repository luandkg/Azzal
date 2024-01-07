package libs.aqz;

import libs.armazenador.Banco;
import libs.armazenador.ItemDoBanco;
import libs.dkg.DKG;
import libs.dkg.DKGFeatures;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.luan.fmt;

public class AQZ {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 04
    // ATUALIZADO : 2024 01 04

    public static void COLECOES_CRIAR(String arquivo_banco, String colecao) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);
        aqz.colecoes_criar(colecao);
        aqz.fechar();
    }

    public static void COLECOES_ORGANIZAR(String arquivo_banco, String colecao) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);
        if (!aqz.colecoes_existe(colecao)) {
            aqz.colecoes_criar(colecao);
        }
        aqz.fechar();
    }

    public static Lista<String> COLECOES_LISTAR(String arquivo_banco) {

        Lista<String> colecoes = new Lista<String>();


        AQZInterna aqz = new AQZInterna(arquivo_banco);

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


    public static void INSERIR(String arquivo_banco, String colecao, DKGObjeto objeto) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        aqz.colecoes_obter(colecao).adicionar(objeto);

        aqz.fechar();
    }


    public static Lista<DKGObjeto> COLECAO_ITENS(String arquivo_banco, String colecao) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao).getItens()) {
            objetos.adicionar(DKG.PARSER_TO_OBJETO(item.lerTexto()));
        }

        aqz.fechar();

        return objetos;
    }

    public static void EXIBIR_COLECAO(String arquivo_banco, String colecao_nome) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(DKG.PARSER_TO_OBJETO(item.lerTexto()));
        }

        DKGFeatures.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }


    public static long QUANTIDADE(String arquivo_banco, String colecao) {

        long quantidade = 0;

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        quantidade = aqz.banco_obter(colecao).getItensContagem();


        aqz.fechar();

        return quantidade;
    }


    public static void LIMPAR_TUDO(String arquivo_banco, String colecao) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        aqz.colecoes_obter(colecao).limpar();
        aqz.colecoes_obter(colecao).zerarSequencial();

        aqz.fechar();
    }

    public static void COLECOES_DESTRUIR(String arquivo_banco, String colecao) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        aqz.colecoes_remover(colecao);

        aqz.fechar();
    }


    public static void EXIBIR_COLECAO_PRIMARIA(String arquivo_banco, String colecao_nome) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();
        for (ItemDoBanco item : aqz.primarios_colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(DKG.PARSER_TO_OBJETO(item.lerTexto()));
        }

        DKGFeatures.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }


    public static void AUTO_ANALISAR(String arquivo_banco) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        aqz.auto_analisar();

        aqz.fechar();

    }

    public static void ANALISAR(String arquivo_banco) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        aqz.analisar();

        aqz.fechar();

    }


    public static void DEFINIR_VIEW(String arquivo_banco, String view_nome, String colecao_nome, Lista<String> colunas) {

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        if (aqz.views_existe(view_nome)) {
            aqz.views_remover(view_nome);
        }

        aqz.views_criar(view_nome, colecao_nome, colunas);

        aqz.fechar();
    }


    public static void EXIBIR_VIEW(String arquivo_banco, String view_nome) {

        view_nome = view_nome.toUpperCase();

        AQZInterna aqz = new AQZInterna(arquivo_banco);

        DKGObjeto view = aqz.views_obter(view_nome);


        Lista<String> colunas = new Lista<String>();
        String colecao_nome = view.identifique("Banco").getValor();

        for (DKGObjeto col : view.getObjetos()) {
            colunas.adicionar(col.identifique("Nome").getValor());
        }

        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            DKGObjeto obj = DKG.PARSER_TO_OBJETO(item.lerTexto());

            DKGObjeto obj_em_view = new DKGObjeto("View");
            for (String coluna : colunas) {
                obj_em_view.identifique(coluna, obj.identifique(coluna).getValor());
            }

            objetos.adicionar(obj_em_view);
        }

        DKGFeatures.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }
}
