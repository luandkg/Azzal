package libs.ez;

import libs.arquivos.TX;
import libs.arquivos.binario.Arquivador;
import libs.dkg.DKG;
import libs.dkg.DKGFeatures;
import libs.dkg.DKGObjeto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;
import libs.luan.fmt;

public class AZZ {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 04
    // ATUALIZADO : 2024 01 04
    // ATUALIZADO : 2024 09 07 - ENTIDADES

    public static Entidade NOVA_ENTIDADE() {
        return new Entidade();
    }

    public static void COLECOES_CRIAR(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);
        aqz.colecoes_criar(colecao);
        aqz.fechar();
    }

    public static void COLECOES_ORGANIZAR(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);
        if (!aqz.colecoes_existe(colecao)) {
            aqz.colecoes_criar(colecao);
        }
        aqz.fechar();
    }

    public static Lista<String> COLECOES_LISTAR(String arquivo_banco) {

        Lista<String> colecoes = new Lista<String>();


        AZ aqz = new AZ(arquivo_banco);

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

        AZ aqz = new AZ(arquivo_banco);

        aqz.colecoes_obter(colecao).adicionar(objeto);

        aqz.fechar();
    }


    public static void INSERIR_VARIOS(String arquivo_banco, String colecao, Lista<Entidade> objetos) {

        AZ aqz = new AZ(arquivo_banco);

        for(Entidade objeto : objetos){
            aqz.colecoes_obter(colecao).adicionar(objeto);
        }

        aqz.fechar();
    }


    public static Lista<DKGObjeto> COLECAO_ITENS(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);

        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao).getItens()) {
            objetos.adicionar(DKG.PARSER_TO_OBJETO(item.lerTexto()));
        }

        aqz.fechar();

        return objetos;
    }

    public static Lista<Entidade> COLECAO_ENTIDADES(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao).getItens()) {
            objetos.adicionar(item.toEntidade());
        }

        aqz.fechar();

        return objetos;
    }

    public static Lista<Par<ItemDoBanco, Entidade>> COLECAO_ITENS_RAW(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);

        Lista<Par<ItemDoBanco, Entidade>> objetos = new Lista<Par<ItemDoBanco, Entidade>>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao).getItens()) {
            objetos.adicionar(new Par<ItemDoBanco, Entidade>(item,item.toEntidade()));
        }

        aqz.fechar();

        return objetos;
    }

    public static void EXIBIR_COLECAO(String arquivo_banco, String colecao_nome) {

        AZ aqz = new AZ(arquivo_banco);

        Lista<Entidade> objetos = new Lista<Entidade>();
        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(item.toEntidade());
        }

        ENTT.EXIBIR_TABELA_COM_NOME(objetos, colecao_nome);
        aqz.fechar();

    }


    public static long QUANTIDADE(String arquivo_banco, String colecao) {

        long quantidade = 0;

        AZ aqz = new AZ(arquivo_banco);

        quantidade = aqz.banco_obter(colecao).getItensContagem();


        aqz.fechar();

        return quantidade;
    }


    public static void LIMPAR_TUDO(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);

        aqz.colecoes_obter(colecao).limpar();
        aqz.colecoes_obter(colecao).zerarSequencial();

        aqz.fechar();
    }

    public static void COLECOES_DESTRUIR(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);

        aqz.colecoes_remover(colecao);

        aqz.fechar();
    }


    public static void EXIBIR_COLECAO_PRIMARIA(String arquivo_banco, String colecao_nome) {

        AZ aqz = new AZ(arquivo_banco);

        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();
        for (ItemDoBanco item : aqz.primarios_colecoes_obter(colecao_nome).getItens()) {
            objetos.adicionar(DKG.PARSER_TO_OBJETO(item.lerTexto()));
        }

        DKGFeatures.EXIBIR_TABELA(objetos);

        aqz.fechar();

    }


    public static void AUTO_ANALISAR(String arquivo_banco) {

        AZ aqz = new AZ(arquivo_banco);

        aqz.auto_analisar();

        Banco auto_analise = aqz.primarios_colecoes_obter("@AutoAnalise");
        auto_analise.limpar();

        long tamanho_total = 0;
        long tamanho_utilizado = 0;

        for (Banco colecao : aqz.colecoes_listar()) {

            Entidade item = AZZ.NOVA_ENTIDADE();
            item.at("Banco", colecao.getNome());
            item.at("Itens", colecao.getItensContagem());

            auto_analise.adicionar(item);

            tamanho_total += colecao.getTamanho();
            tamanho_utilizado += colecao.getTamanhoUtilizado();

        }


        aqz.fechar();

    }

    public static void EXIBIR_AUTO_ANALISE(String arquivo_banco) {

        AZ aqz = new AZ(arquivo_banco);

        Lista<Entidade> objetos_auto_analise = new Lista<Entidade>();

        for (ItemDoBanco item : aqz.primarios_colecoes_obter("@AutoAnalise").getItens()) {
            objetos_auto_analise.adicionar(item.toEntidade());
        }

        Lista<Entidade> objetos_analise = new Lista<Entidade>();

        for (ItemDoBanco item : aqz.primarios_colecoes_obter("@Analise").getItens()) {
            objetos_analise.adicionar(item.toEntidade());
        }

        aqz.fechar();

        ENTT.EXIBIR_TABELA(objetos_auto_analise);
        ENTT.EXIBIR_TABELA(objetos_analise);

    }


    public static void EXIBIR_ESTRUTURA(String arquivo_banco) {

        AZ aqz = new AZ(arquivo_banco);

        Lista<Entidade> objetos_internos = new Lista<Entidade>();
        Lista<Entidade> objetos_publicos = new Lista<Entidade>();

        for (Banco item : aqz.getColecoesInternas()) {
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

    public static void ANALISAR(String arquivo_banco) {

        AZ aqz = new AZ(arquivo_banco);

        aqz.analisar();

        aqz.fechar();

    }


    public static void DEFINIR_VIEW(String arquivo_banco, String view_nome, String colecao_nome, Lista<String> colunas) {

        AZ aqz = new AZ(arquivo_banco);

        if (aqz.views_existe(view_nome)) {
            aqz.views_remover(view_nome);
        }

        aqz.views_criar(view_nome, colecao_nome, colunas);

        aqz.fechar();
    }


    public static void EXIBIR_VIEW(String arquivo_banco, String view_nome) {

        view_nome = view_nome.toUpperCase();

        AZ aqz = new AZ(arquivo_banco);

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


    public static DKGObjeto NOVO_ITEM() {
        return new DKGObjeto("ITEM");
    }


    public static Opcional<Par<ItemDoBanco, Entidade>> COLECAO_ITEM_UNICO_RAW(String arquivo_banco, String colecao, String atributo_chave, String atributo_valor) {

        for (Par<ItemDoBanco, Entidade> raw_obj : AZZ.COLECAO_ITENS_RAW(arquivo_banco, colecao)) {
            if (raw_obj.getValor().is(atributo_chave,atributo_valor)) {
                return Opcional.OK(raw_obj);
            }
        }

        return Opcional.CANCEL();

    }


    public static void ATUALIZAR(String arquivo_banco, ItemDoBanco item, Entidade obj) {


        Arquivador mArquivador = new Arquivador(arquivo_banco);

        mArquivador.setPonteiro(item.getPonteiroDados());
        mArquivador.set_u8_array(TX.toListBytes(ENTT.TO_DOCUMENTO(obj)));

        mArquivador.encerrar();

    }


    public static void COLECAO_REMOVER_QUANDO(String arquivo_banco, String colecao, String att_nome, String att_valor) {

        AZ aqz = new AZ(arquivo_banco);

        AQColecao aq_colecao = aqz.colecoes_obter(colecao);

        for (ItemDoBanco item : aq_colecao.getItens()) {
            Entidade obj = item.toEntidade();
            if (obj.is(att_nome, att_valor)) {
                aq_colecao.remover(item);
            }
        }

        aqz.fechar();

    }

    public static void DUMP_ANALISE(String arquivo_banco) {

        AZ aqz = new AZ(arquivo_banco);

        Banco colecao_analise = aqz.primarios_colecoes_obter("@AutoAnalise");

        Lista<DKGObjeto> objetos = new Lista<DKGObjeto>();
        for (ItemDoBanco item : colecao_analise.getItens()) {
            DKGObjeto obj = DKG.PARSER_TO_OBJETO(item.lerTexto());
            objetos.adicionar(obj);
        }

        aqz.fechar();

        Lista<Entidade> entts = ENTT.TRANSFORMAR_DE_OBJETOS(objetos);
        ENTT.EXIBIR_TABELA(entts);

    }

    public static void EXIBIR_COLECOES(String arquivo_banco) {

        AZ aqz = new AZ(arquivo_banco);

        Lista<Entidade> colecoes = new Lista<Entidade>();

        for (Banco colecao : aqz.getColecoesInternas()) {

            Entidade item = new Entidade();
            item.at("Tipo", "INTERNO");
            item.at("Banco", colecao.getNome());
            item.at("Itens", colecao.getItens().getQuantidade());

            colecoes.adicionar(item);
        }


        for (Banco colecao : aqz.colecoes_listar()) {

            Entidade item = new Entidade();
            item.at("Tipo", "PUBLICO");
            item.at("Banco", colecao.getNome());
            item.at("Itens", colecao.getItens().getQuantidade());

            colecoes.adicionar(item);
        }


        aqz.fechar();

        ENTT.EXIBIR_TABELA(colecoes);
    }

    public static void EXIBIR_COLECAO_PUBLICA(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);

        for (Banco proc_colecao : aqz.colecoes_listar()) {
            if (proc_colecao.getNome().contentEquals(colecao)) {

                Lista<Entidade> entts = new Lista<Entidade>();
                for (ItemDoBanco item : proc_colecao.getItens()) {
                    entts.adicionar(ENTT.PARSER_RAW_TO_ENTIDADE(item.lerTexto()));
                }

                ENTT.EXIBIR_TABELA(entts);

                break;
            }
        }

        aqz.fechar();
    }

    public static void EXIBIR_ESQUEMA_PUBLICA(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);

        for (Banco proc_colecao : aqz.colecoes_listar()) {
            if (proc_colecao.getNome().contentEquals(colecao)) {

                Lista<Entidade> entts = new Lista<Entidade>();
                for (ItemDoBanco item : proc_colecao.getItens()) {
                    entts.adicionar(ENTT.PARSER_RAW_TO_ENTIDADE(item.lerTexto()));
                }

                ENTT.EXIBIR_TABELA(ENTT.GET_ATRIBUTOS(entts));

                break;
            }
        }

        aqz.fechar();
    }

    public static Lista<Entidade> OBTER_COLECAO_PUBLICA(String arquivo_banco, String colecao) {

        AZ aqz = new AZ(arquivo_banco);

        for (Banco proc_colecao : aqz.colecoes_listar()) {
            if (proc_colecao.getNome().contentEquals(colecao)) {

                Lista<Entidade> entts = new Lista<Entidade>();
                for (ItemDoBanco item : proc_colecao.getItens()) {
                    entts.adicionar(ENTT.PARSER_RAW_TO_ENTIDADE(item.lerTexto()));
                }

                return entts;

            }
        }

        aqz.fechar();

        return new Lista<Entidade>();
    }

    public static void EXIBIR_TUDO(String arquivo_banco) {

        AZ aqz = new AZ(arquivo_banco);

        for (Banco item : aqz.getColecoesInternas()) {

            Lista<Entidade> entts = ENTT.CRIAR_LISTA();
            for (ItemDoBanco ib : item.getItens()) {
                entts.adicionar(ENTT.PARSER_ENTIDADE_QUALQUER(ib.lerTexto()));
            }

            ENTT.EXIBIR_TABELA_COM_NOME(entts, "COLECAO INTERNA - " + item.getNome());
        }

        for (Banco item : aqz.colecoes_listar()) {

            Lista<Entidade> entts = ENTT.CRIAR_LISTA();

            for (ItemDoBanco ib : item.getItens()) {
                entts.adicionar(ib.toEntidade());
            }

            ENTT.EXIBIR_TABELA_COM_NOME(entts, "COLECAO PUBLICA - " + item.getNome());
        }

        aqz.fechar();



    }


    public static void EXIBIR_COLECAO_REORGANIZAR(String arquivo_banco, String colecao_nome) {

        AZ aqz = new AZ(arquivo_banco);

        for (ItemDoBanco item : aqz.colecoes_obter(colecao_nome).getItens()) {

            fmt.print("--------------------------------");
            if(item.lerTexto().startsWith("!ITEM ")){

                String ss = item.lerTexto().replace("!ITEM ","!Item ");
                item.atualizar(ss);

                fmt.print(">> {}"," ++ REFATORANDO");

            }else{
                fmt.print(">> {}","OK");
            }

        }

        aqz.fechar();

    }

    public static void REMOVER_SE_NAO_TIVER_CAMPO(String arquivo_banco, String colecao,String att_nome) {

        AZ aqz = new AZ(arquivo_banco);

       for(ItemDoBanco item :  aqz.colecoes_obter(colecao).getItens()){
           Entidade e_item =item.toEntidade();
           if(!e_item.atributo_existe(att_nome)){
               fmt.print("REMOVENDO VAZIO :: {}",item.lerTexto());
               item.getBanco().remover(item);
           }
       }

        aqz.fechar();
    }

}
