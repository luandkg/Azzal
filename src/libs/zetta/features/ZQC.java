package libs.zetta.features;


import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;
import libs.meta_functional.Acao;
import libs.meta_functional.AcaoAlfa;
import libs.zetta.ItemColecionavel;
import libs.zetta.ZettaColecao;
import libs.zetta.ZettaQuorum;

public class ZQC {

    public static void COLECOES_ORGANIZAR(String arquivo_banco, String colecao_nome) {
        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);
        zetta.getColecaoSempre(colecao_nome);
        zetta.fechar();
    }

    public static void LIMPAR_TUDO(String arquivo_banco, String colecao_nome) {
        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);
        zetta.getColecaoSempre(colecao_nome).zerar();
        zetta.fechar();
    }

    public static void DESTRUIR_COLECAO(String arquivo_banco, String colecao_nome) {
        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);
        zetta.destruirColecao(colecao_nome);
        zetta.fechar();
    }

    public static Entidade NOVA_ENTIDADE() {
        return new Entidade();
    }

    public static void INSERIR(String eArquivo, String colecao_nome, Entidade item) {
        ZettaQuorum zetta = new ZettaQuorum(eArquivo);
        zetta.getColecaoSempre(colecao_nome).adicionar(item);
        zetta.fechar();
    }

    public static void INSERIR_VARIOS(String arquivo_banco, String colecao_nome, Lista<Entidade> novos) {
        ZettaQuorum aqz = new ZettaQuorum(arquivo_banco);

        ZettaColecao colecao = aqz.getColecaoSempre(colecao_nome);

        for (Entidade novo : novos) {
            colecao.adicionar(novo);
        }

        aqz.fechar();
    }

    public static Lista<Entidade> COLECAO_ENTIDADES(String eArquivo, String colecao_nome) {
        ZettaQuorum zetta = new ZettaQuorum(eArquivo);
        Lista<Entidade> dados = zetta.getColecaoSempre(colecao_nome).getItens();
        zetta.fechar();
        return dados;
    }

    public static long COLECAO_CONTAGEM(String eArquivo, String colecao_nome) {
        ZettaQuorum zetta = new ZettaQuorum(eArquivo);
        long contagem = zetta.getColecaoSempre(colecao_nome).contagem();
        zetta.fechar();
        return contagem;
    }

    public static void REMOVER_EM_LOTE(String arquivo_banco, String colecao_nome, String att_nome, Lista<String> att_valores) {
        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);
        ZettaColecao colecao = zetta.getColecaoSempre(colecao_nome);

        for (ItemColecionavel item : colecao.getItensEditaveis()) {

            Entidade e_item = item.get();

            if (att_valores.existe(Strings.IGUALAVEL(), e_item.at(att_nome))) {
                item.remover();
            }

        }


        zetta.fechar();

    }


    public static void EXIBIR_COLECAO(String arquivo_banco, String colecao_nome) {

        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);

        ENTT.EXIBIR_TABELA_COM_TITULO(zetta.getColecaoSempre(colecao_nome).getItens(), colecao_nome);

        zetta.fechar();

    }


    public static void EXIBIR_COLECAO_ORDENADOR_POR(String arquivo_banco, String colecao_nome, String att_nome) {

        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);

        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.ORDENAR_TEXTO(zetta.getColecaoSempre(colecao_nome).getItens(), att_nome), colecao_nome);

        zetta.fechar();

    }


    public static void EXIBIR_COLECAO_ALGUNS(String arquivo_banco, String colecao_nome) {

        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);

        long parcela = zetta.getColecaoSempre(colecao_nome).contagem() / 100;

        long indo = 0;

        Lista<Entidade> seleciondas = new Lista<Entidade>();

        for (int i = 0; i < 100; i++) {
            Opcional<Entidade> op = zetta.getColecaoSempre(colecao_nome).procurar_item_por_indice(indo);
            if (op.isOK()) {
                seleciondas.adicionar(op.get());
            }
            indo += parcela;
        }
        ENTT.EXIBIR_TABELA_COM_TITULO(seleciondas, colecao_nome);

        zetta.fechar();

    }

    public static void EXIBIR_TUDO(String arquivo_banco) {

        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);
        zetta.dump();
        zetta.exibir_tudo();
        zetta.fechar();

    }

    public static void EXIBIR_COLECOES_RESUMO(String arquivo_banco) {

        ZettaQuorum zetta = new ZettaQuorum(arquivo_banco);

        Lista<Entidade> resumo = new Lista<Entidade>();
        for (ZettaColecao colecao : zetta.getColecoes()) {
            Entidade e_colecao = ENTT.CRIAR_EM(resumo, "ID", colecao.getIdentificador());
            e_colecao.at("Nome", colecao.getNome());
            e_colecao.at("Quantidade", colecao.contagem());
        }

        zetta.fechar();

        ENTT.EXIBIR_TABELA_COM_NOME(resumo, "ZETTA QUORUM - COLEÇÕES");
    }

    public static boolean UNICO_EXISTE(String arquivo_banco, String colecao_nome, String att_nome, String att_valor) {

        boolean ret = false;

        ZettaQuorum aqz = new ZettaQuorum(arquivo_banco);


        for (Entidade item : aqz.getColecaoSempre(colecao_nome).getItens()) {
            if (item.is(att_nome, att_valor)) {
                ret = true;
                break;
            }
        }


        aqz.fechar();

        return ret;
    }

    public static boolean UNICO_ATUALIZAR(String arquivo_banco, String colecao_nome, String att_nome, String att_valor, Entidade item_atualizar) {

        boolean ret = false;

        ZettaQuorum aqz = new ZettaQuorum(arquivo_banco);


        for (ItemColecionavel item : aqz.getColecaoSempre(colecao_nome).getItensEditaveis()) {
            Entidade e = item.get();
            if (e.is(att_nome, att_valor)) {


                item_atualizar.at("ID", e.at("ID"));
                item_atualizar.tornar_primeiro("ID");

                item.atualizar();

                ret = true;
                break;
            }
        }

        if (!ret) {
            item_atualizar.tornar_primeiro("ID");
            aqz.getColecaoSempre(colecao_nome).adicionar(item_atualizar);
        }

        aqz.fechar();

        return ret;
    }

    public static Opcional<Par<ItemColecionavel, Entidade>> GET_UNICO(String arquivo_banco, String colecao_nome, String att_nome, String att_valor) {

        Opcional<Par<ItemColecionavel, Entidade>> ret = Opcional.CANCEL();

        ZettaQuorum aqz = new ZettaQuorum(arquivo_banco);

        ZettaColecao colecao = aqz.getColecaoSempre(colecao_nome);
        for (ItemColecionavel item : colecao.getItensEditaveis()) {
            Entidade e_item = item.get();
            if (e_item.is(att_nome, att_valor)) {
                ret = Opcional.OK(new Par<ItemColecionavel, Entidade>(item, e_item));
                break;
            }

        }


        aqz.fechar();

        return ret;
    }

    public static void ATUALIZAR(String arquivo_banco, String colecao_nome, AcaoAlfa<ItemColecionavel> acao) {
        ZettaQuorum aqz = new ZettaQuorum(arquivo_banco);

        ZettaColecao colecao = aqz.getColecaoSempre(colecao_nome);
        for (ItemColecionavel item : colecao.getItensEditaveis()) {
            acao.fazer(item);
        }

        aqz.fechar();
    }


    public static void PROCESSAR_EM_INTERVALOS(String arquivo, String colecao_nome, int intervalo, boolean mostrar_processamento, AcaoAlfa<Entidade> eAcao) {

        ZettaQuorum zq = new ZettaQuorum(arquivo);
        ZettaColecao colecao = zq.getColecaoSempre(colecao_nome);


        long quantidade = colecao.contagem();

        int iniciando = 0;
        int finalizando = 0;

        while (finalizando < quantidade) {
            finalizando += intervalo;

            if (mostrar_processamento) {
                fmt.print(">> Processando {} - {} :: {}", iniciando, finalizando, quantidade);
            }

            for (Entidade item : colecao.getItensIntervalo(iniciando, finalizando)) {
                eAcao.fazer(item);
            }

            iniciando = finalizando + 1;
        }


        zq.fechar();


    }

    public static void PROCESSAR_EM_INTERVALOS_COM_ANALITICO(String arquivo, String colecao_nome, int intervalo, boolean mostrar_processamento, int intervalo_analitico, AcaoAlfa<Entidade> eAcao, Acao eAcaoAnalitica) {

        ZettaQuorum zq = new ZettaQuorum(arquivo);
        ZettaColecao colecao = zq.getColecaoSempre(colecao_nome);


        long quantidade = colecao.contagem();

        int iniciando = 0;
        int finalizando = 0;

        int analiticamente_contagem = 0;

        while (finalizando < quantidade) {
            finalizando += intervalo;

            if (mostrar_processamento) {
                fmt.print(">> Processando {} - {} :: {}", iniciando, finalizando, quantidade);
            }

            for (Entidade item : colecao.getItensIntervalo(iniciando, finalizando)) {
                eAcao.fazer(item);

                analiticamente_contagem+=1;
                if(analiticamente_contagem==intervalo_analitico){
                    analiticamente_contagem=0;
                    eAcaoAnalitica.fazer();
                }
            }

            iniciando = finalizando + 1;
        }


        zq.fechar();


    }
}
