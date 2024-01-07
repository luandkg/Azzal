package libs.aqz;

import libs.armazenador.Armazenador;
import libs.armazenador.Banco;
import libs.armazenador.ItemDoBanco;
import libs.arquivos.binario.Arquivador;
import libs.az.AZColecionador;
import libs.az.AZSequenciador;
import libs.az.Colecao;
import libs.dkg.DKG;
import libs.dkg.DKGFeatures;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.luan.fmt;
import libs.tempo.Calendario;

public class AQZInterna {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 04
    // ATUALIZADO : 2024 01 04
    private Armazenador mArmazenador;

    public AQZInterna(String arquivo_banco) {

        AZColecionador.checar(arquivo_banco);
        mArmazenador = new Armazenador(arquivo_banco);


    }

    public void fechar() {
        mArmazenador.fechar();
    }

    public void colecoes_criar(String colecao_nome) {

        String nome_original = colecao_nome;

        colecao_nome = colecao_nome.toUpperCase();


        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_bancos = AZSequenciador.organizar_banco(mArmazenador, "@Bancos");
        Banco s_sequencias = AZSequenciador.organizar_banco(mArmazenador, "@Sequencias");
        Banco s_auto_analises = AZSequenciador.organizar_banco(mArmazenador, "@AutoAnalise");
        Banco s_analises = AZSequenciador.organizar_banco(mArmazenador, "@Analise");
        Banco s_views = AZSequenciador.organizar_banco(mArmazenador, "@Views");


        DKGObjeto init_bancos = new DKGObjeto("INIT");
        ItemDoBanco ref_init_bancos = null;
        boolean init_bancos_existe = false;

        for (ItemDoBanco item : s_inits.getItens()) {
            DKGObjeto item_dkg = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (item_dkg.identifique("Nome").getValor().toUpperCase().contentEquals("BANCOS")) {
                ref_init_bancos = item;
                init_bancos = item_dkg;
                init_bancos_existe = true;
                break;
            }
        }


        if (!init_bancos_existe) {
            DKGObjeto nova_init = new DKGObjeto("INIT");
            nova_init.identifique("Nome", "BANCOS");
            nova_init.identifique("Corrente", 0);
            nova_init.identifique("Sequencia", 1);
            nova_init.identifique("DDC", Calendario.getTempoCompleto());
            nova_init.identifique("DDA", Calendario.getTempoCompleto());
            s_inits.adicionar(nova_init.toDocumento());

            for (ItemDoBanco item : s_inits.getItens()) {
                DKGObjeto item_dkg = DKG.PARSER_TO_OBJETO(item.lerTexto());
                if (item_dkg.identifique("Nome").getValor().toUpperCase().contentEquals("BANCOS")) {
                    ref_init_bancos = item;
                    init_bancos = item_dkg;
                    init_bancos_existe = true;
                    break;
                }
            }

            if (!init_bancos_existe) {
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Init nao encontrada : BANCOS");
            }

        }

        boolean existe = false;
        boolean criado = false;

        for (ItemDoBanco item : s_bancos.getItens()) {
            DKGObjeto obj_colecao = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (obj_colecao.identifique("Status").isValor("OK") && obj_colecao.identifique("Nome").getValor().toUpperCase().contentEquals(colecao_nome)) {
                existe = true;
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Já existe uma coleção com esse nome : " + colecao_nome);
            }
        }

        for (ItemDoBanco item : s_bancos.getItens()) {
            DKGObjeto obj_colecao = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (obj_colecao.identifique("Status").isValor("DESTRUIDO")) {

                String nome_antigo = obj_colecao.identifique("Nome").getValor();

                int banco_id = obj_colecao.identifique("BID").getInteiro(0);

                long ponteiro_inicial_do_banco = obj_colecao.identifique("Ponteiro").getLong(0);

                long local_itens = obj_colecao.identifique("Capitulos").getLong(0);
                long local_cache = obj_colecao.identifique("Cache").getLong(0);
                long local_corrente = obj_colecao.identifique("PPPC").getLong(0);
                long local_indice = obj_colecao.identifique("Indice").getLong(0);


                Banco banco_remover = new Banco(nome_antigo, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                banco_remover.limpar();

                AZSequenciador.zerar_sequencial(s_sequencias, nome_antigo);


                int novo_id = init_bancos.identifique("Corrente").getInteiro(0);

                init_bancos.identifique("Corrente", init_bancos.identifique("Corrente").getInteiro(0) + init_bancos.identifique("Sequencia").getInteiro(0));
                init_bancos.identifique("Sequencia", 1);
                init_bancos.identifique("DDA", Calendario.getTempoCompleto());

                ref_init_bancos.atualizar(init_bancos.toDocumento());

                obj_colecao.identifique("BID", novo_id);
                obj_colecao.identifique("Status", "OK");
                obj_colecao.identifique("Nome", colecao_nome.toUpperCase());
                obj_colecao.identifique("NomeOriginal", nome_original);
                obj_colecao.identifique("DDC", Calendario.getTempoCompleto());
                obj_colecao.identifique("DDA", Calendario.getTempoCompleto());

                item.atualizar(obj_colecao.toDocumento());
                criado = true;
                return;
            }
        }

        if (criado) {
            return;
        }


        if (!existe) {

            int banco_id = init_bancos.identifique("Corrente").getInteiro(0);

            init_bancos.identifique("Corrente", init_bancos.identifique("Corrente").getInteiro(0) + init_bancos.identifique("Sequencia").getInteiro(0));
            init_bancos.identifique("Sequencia", 1);
            init_bancos.identifique("DDA", Calendario.getTempoCompleto());

            ref_init_bancos.atualizar(init_bancos.toDocumento());

            Arquivador real_arquivador = mArmazenador.getArquivador();

            long ponteiro_final_arquivo = real_arquivador.getLength();

            real_arquivador.setPonteiro(ponteiro_final_arquivo);


            long ponteiro_inicial_do_banco = real_arquivador.getPonteiro();

            for (int ponta = 0; ponta < 1024; ponta++) {
                real_arquivador.set_u64(0);
            }


            long guardar_capitulos = real_arquivador.getPonteiro();

            real_arquivador.set_u8((byte) Armazenador.MARCADOR_SUMARIO);
            real_arquivador.set_u64((byte) 0);
            real_arquivador.set_u64((byte) 0);
            real_arquivador.set_u64((byte) 0);

            for (int capitulo = 0; capitulo < Armazenador.MAX_CAPITULOS; capitulo++) {
                real_arquivador.set_u64(0);
            }

            long guardar_cache = real_arquivador.getPonteiro();

            real_arquivador.set_u8((byte) Armazenador.MARCADOR_CACHE);
            for (int item = 0; item < Armazenador.MAX_ITENS_DO_CACHE; item++) {
                real_arquivador.set_u64(0);
            }

            long guardar_primeiro_capitulo = real_arquivador.getPonteiro();

            real_arquivador.set_u8((byte) Armazenador.MARCADOR_CAPITULO);
            for (int item = 0; item < Armazenador.MAX_PAGINAS; item++) {
                real_arquivador.set_u64(0);
            }

            long guardar_primeira_pagina_do_primeiro_capitulo = real_arquivador.getPonteiro();

            real_arquivador.set_u8((byte) Armazenador.MARCADOR_PAGINA);
            for (int item = 0; item < Armazenador.MAX_ITENS_POR_PAGINA; item++) {
                real_arquivador.set_u8((byte) 0);
                real_arquivador.set_u64(0);
            }


            long guardar_local_indice = real_arquivador.getPonteiro();

            System.out.println("CRIAR INDICE EM :: " + guardar_local_indice);

            real_arquivador.set_u8((byte) Armazenador.MARCADOR_INDICE);
            real_arquivador.set_u64(0);
            real_arquivador.set_u64(0);

            for (int item = 0; item < Armazenador.MAX_ITENS_DO_INDICE; item++) {
                real_arquivador.set_u64(0);
            }


            real_arquivador.setPonteiro(guardar_capitulos + 1);
            real_arquivador.set_u64(guardar_primeiro_capitulo);

            real_arquivador.setPonteiro(guardar_primeiro_capitulo + 1);
            real_arquivador.set_u64(guardar_primeira_pagina_do_primeiro_capitulo);


            DKGObjeto nova_colecao = new DKGObjeto("COLECAO");

            nova_colecao.identifique("BID", banco_id);
            nova_colecao.identifique("Status", "OK");
            nova_colecao.identifique("Nome", colecao_nome.toUpperCase());
            nova_colecao.identifique("NomeOriginal", nome_original);
            nova_colecao.identifique("DDC", Calendario.getTempoCompleto());
            nova_colecao.identifique("DDA", Calendario.getTempoCompleto());


            nova_colecao.identifique("Ponteiro").setLong(ponteiro_inicial_do_banco);
            nova_colecao.identifique("Capitulos").setLong(guardar_capitulos);
            nova_colecao.identifique("Cache").setLong(guardar_cache);
            nova_colecao.identifique("PPPC").setLong(guardar_primeira_pagina_do_primeiro_capitulo);
            nova_colecao.identifique("Indice").setLong(guardar_local_indice);

            s_bancos.adicionar(nova_colecao.toDocumento());

        }

    }


    public Lista<Banco> colecoes_listar() {


        Lista<Banco> mBancos = new Lista<Banco>();


        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_bancos = AZSequenciador.organizar_banco(mArmazenador, "@Bancos");

        for (ItemDoBanco item : s_bancos.getItens()) {
            DKGObjeto obj_colecao = DKG.PARSER_TO_OBJETO(item.lerTexto());


            if (obj_colecao.identifique("Status").isValor("OK")) {

                String nome = obj_colecao.identifique("Nome").getValor();

                int banco_id = obj_colecao.identifique("BID").getInteiro(0);

                long ponteiro_inicial_do_banco = obj_colecao.identifique("Ponteiro").getLong(0);

                long local_itens = obj_colecao.identifique("Capitulos").getLong(0);
                long local_cache = obj_colecao.identifique("Cache").getLong(0);
                long local_corrente = obj_colecao.identifique("PPPC").getLong(0);
                long local_indice = obj_colecao.identifique("Indice").getLong(0);


                mBancos.adicionar(new Banco(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice));


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return mBancos;

    }

    public boolean colecoes_existe(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (Banco b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return true;
            }
        }

        return false;
    }

    public Banco banco_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (Banco b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return b;
            }
        }

        return null;
    }


    public Banco colecoes_remover(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        Banco s_bancos = AZSequenciador.organizar_banco(mArmazenador, "@Bancos");
        Banco s_sequencias = AZSequenciador.organizar_banco(mArmazenador, "@Sequencias");

        for (ItemDoBanco item : s_bancos.getItens()) {
            DKGObjeto obj_colecao = DKG.PARSER_TO_OBJETO(item.lerTexto());

            String nome = obj_colecao.identifique("Nome").getValor();

            int banco_id = obj_colecao.identifique("BID").getInteiro(0);

            long ponteiro_inicial_do_banco = obj_colecao.identifique("Ponteiro").getLong(0);

            long local_itens = obj_colecao.identifique("Capitulos").getLong(0);
            long local_cache = obj_colecao.identifique("Cache").getLong(0);
            long local_corrente = obj_colecao.identifique("PPPC").getLong(0);
            long local_indice = obj_colecao.identifique("Indice").getLong(0);


            if (obj_colecao.identifique("Status").isValor("OK") && nome.contentEquals(colecao_nome)) {
                Banco banco_remover = new Banco(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                banco_remover.limpar();
                obj_colecao.identifique("Status").setValor("DESTRUIDO");
                AZSequenciador.zerar_sequencial(s_sequencias, colecao_nome);

                //   fmt.print("DESTRUINDO :: {}", obj_colecao.toDocumento());

                item.atualizar(obj_colecao.toDocumento());
                break;
            }


        }


        return null;
    }

    public Colecao colecoes_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        Banco s_sequencias = AZSequenciador.organizar_banco(mArmazenador, "@Sequencias");

        for (Banco b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return new Colecao(colecao_nome, mArmazenador, s_sequencias, b);
            }
        }

        return null;
    }


    public Banco primarios_colecoes_obter(String colecao_nome) {

        for (Banco b : mArmazenador.getBancos()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return b;
            }
        }

        return null;
    }


    public void auto_analisar() {

        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_bancos = AZSequenciador.organizar_banco(mArmazenador, "@Bancos");
        Banco s_sequencias = AZSequenciador.organizar_banco(mArmazenador, "@Sequencias");
        Banco s_auto_analises = AZSequenciador.organizar_banco(mArmazenador, "@AutoAnalise");
        Banco s_analises = AZSequenciador.organizar_banco(mArmazenador, "@Analise");


        Banco colecao_analise = primarios_colecoes_obter("@AutoAnalise");
        colecao_analise.limpar();

        Lista<DKGObjeto> objetos_analisados = new Lista<DKGObjeto>();

        for (Banco b : mArmazenador.getBancos()) {

            DKGObjeto obj_analise = new DKGObjeto("AutoAnalise");

            obj_analise.identifique("BID").setLong(b.getID());
            obj_analise.identifique("Nome").setValor(b.getNome());

            long itens_alocados = b.getItensAlocadosContagem();
            long itens_utilizados = b.getItensContagem();

            obj_analise.identifique("Itens_Disponiveis").setLong(itens_alocados);
            obj_analise.identifique("Itens_Utilizados").setLong(itens_utilizados);

            obj_analise.identifique("Disponibilidade").setInteiro(b.getDisponibilidade());
            obj_analise.identifique("Usabilidadade").setInteiro(b.getUsabilidade());

            obj_analise.identifique("Capitulos_Contagem").setLong(b.getCapitulosContagem());
            obj_analise.identifique("Paginas_Contagem").setLong(b.getPaginasContagem());

            obj_analise.identifique("Indice_Disponibilidade").setInteiro(b.getIndiceDisponibilidade());
            obj_analise.identifique("Indice_Usabilidade").setInteiro(b.getIndiceUsabilidade());
            obj_analise.identifique("Indice_Paginas").setLong(b.getIndicePaginasContagem());
            obj_analise.identifique("Indice_Contagem").setLong(b.getIndiceItensTotalContagem());
            obj_analise.identifique("Indice_Utilizados").setLong(b.getIndiceItensUtilizadoContagem());

            objetos_analisados.adicionar(obj_analise);

        }

        for (DKGObjeto obj_analise : DKGFeatures.ordenar_objetos_texto(objetos_analisados, "Nome")) {
            colecao_analise.adicionar(obj_analise.toDocumento());
        }

        // colecao_analise.primeiro_campo("ID");
    }

    public void analisar() {

        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_bancos = AZSequenciador.organizar_banco(mArmazenador, "@Bancos");
        Banco s_sequencias = AZSequenciador.organizar_banco(mArmazenador, "@Sequencias");
        Banco s_auto_analises = AZSequenciador.organizar_banco(mArmazenador, "@AutoAnalise");
        Banco s_analises = AZSequenciador.organizar_banco(mArmazenador, "@Analise");


        Banco colecao_analise = primarios_colecoes_obter("@Analise");
        colecao_analise.limpar();

        Lista<DKGObjeto> objetos_analisados = new Lista<DKGObjeto>();

        for (Banco b : colecoes_listar()) {

            DKGObjeto obj_analise = new DKGObjeto("Analise");

            obj_analise.identifique("BID").setLong(b.getID());
            obj_analise.identifique("Nome").setValor(b.getNome());

            long itens_alocados = b.getItensAlocadosContagem();
            long itens_utilizados = b.getItensContagem();

            obj_analise.identifique("Itens_Disponiveis").setLong(itens_alocados);
            obj_analise.identifique("Itens_Utilizados").setLong(itens_utilizados);

            obj_analise.identifique("Disponibilidade").setInteiro(b.getDisponibilidade());
            obj_analise.identifique("Usabilidadade").setInteiro(b.getUsabilidade());

            obj_analise.identifique("Capitulos_Contagem").setLong(b.getCapitulosContagem());
            obj_analise.identifique("Paginas_Contagem").setLong(b.getPaginasContagem());

            obj_analise.identifique("Indice_Disponibilidade").setInteiro(b.getIndiceDisponibilidade());
            obj_analise.identifique("Indice_Usabilidade").setInteiro(b.getIndiceUsabilidade());
            obj_analise.identifique("Indice_Paginas").setLong(b.getIndicePaginasContagem());
            obj_analise.identifique("Indice_Contagem").setLong(b.getIndiceItensTotalContagem());
            obj_analise.identifique("Indice_Utilizados").setLong(b.getIndiceItensUtilizadoContagem());

            obj_analise.identifique("Cache_Itens").setLong(b.getItensEmCacheContagem());

            objetos_analisados.adicionar(obj_analise);

        }

        for (DKGObjeto obj_analise : DKGFeatures.ordenar_objetos_texto(objetos_analisados, "Nome")) {
            colecao_analise.adicionar(obj_analise.toDocumento());
        }

        // colecao_analise.primeiro_campo("ID");
    }


    public void views_criar(String view_nome, String colecao_nome, Lista<String> colunas) {

        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_views = AZSequenciador.organizar_banco(mArmazenador, "@Views");

        if (!views_existe(view_nome)) {


            DKGObjeto init_views = new DKGObjeto("INIT");
            ItemDoBanco ref_init_views = null;
            boolean init_views_existe = false;

            for (ItemDoBanco item : s_inits.getItens()) {
                DKGObjeto item_dkg = DKG.PARSER_TO_OBJETO(item.lerTexto());
                if (item_dkg.identifique("Nome").getValor().toUpperCase().contentEquals("VIEWS")) {
                    ref_init_views = item;
                    init_views = item_dkg;
                    init_views_existe = true;
                    break;
                }
            }


            if (!init_views_existe) {
                DKGObjeto nova_init = new DKGObjeto("INIT");
                nova_init.identifique("Nome", "VIEWS");
                nova_init.identifique("Corrente", 0);
                nova_init.identifique("Sequencia", 1);
                nova_init.identifique("DDC", Calendario.getTempoCompleto());
                nova_init.identifique("DDA", Calendario.getTempoCompleto());
                s_inits.adicionar(nova_init.toDocumento());

                for (ItemDoBanco item : s_inits.getItens()) {
                    DKGObjeto item_dkg = DKG.PARSER_TO_OBJETO(item.lerTexto());
                    if (item_dkg.identifique("Nome").getValor().toUpperCase().contentEquals("VIEWS")) {
                        ref_init_views = item;
                        init_views = item_dkg;
                        init_views_existe = true;
                        break;
                    }
                }

                if (!init_views_existe) {
                    mArmazenador.fechar();
                    throw new RuntimeException("AQZ ERRO - Init nao encontrada : VIEWS");
                }

            }

            int view_id = init_views.identifique("Corrente").getInteiro(0);

            init_views.identifique("Corrente", init_views.identifique("Corrente").getInteiro(0) + init_views.identifique("Sequencia").getInteiro(0));
            ref_init_views.atualizar(init_views.toDocumento());

            DKGObjeto obj_view = new DKGObjeto("View");
            obj_view.identifique("ID", view_id);
            obj_view.identifique("Nome", view_nome.toUpperCase());
            obj_view.identifique("NomeOriginal", view_nome);
            obj_view.identifique("Banco", colecao_nome.toUpperCase());

            for (String coluna : colunas) {
                DKGObjeto obj_view_coluna = obj_view.criarObjeto("Coluna");
                obj_view_coluna.identifique("Nome", coluna);
            }


            s_views.adicionar(obj_view.toDocumento());
        }

    }

    public Lista<String> views_listar() {

        Lista<String> lista = new Lista<String>();

        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_views = AZSequenciador.organizar_banco(mArmazenador, "@Views");

        for (ItemDoBanco item : s_views.getItens()) {
            DKGObjeto obj = DKG.PARSER_TO_OBJETO(item.lerTexto());
            lista.adicionar(obj.identifique("Nome").getValor().toUpperCase());
        }

        return lista;
    }

    public boolean views_existe(String view_nome) {
        view_nome = view_nome.toUpperCase();

        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_views = AZSequenciador.organizar_banco(mArmazenador, "@Views");

        for (ItemDoBanco item : s_views.getItens()) {
            DKGObjeto obj = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (obj.identifique("Nome").getValor().toUpperCase().contentEquals(view_nome)) {
                return true;
            }
        }

        return false;
    }

    public DKGObjeto views_obter(String view_nome) {
        view_nome = view_nome.toUpperCase();

        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_views = AZSequenciador.organizar_banco(mArmazenador, "@Views");

        for (ItemDoBanco item : s_views.getItens()) {
            DKGObjeto obj = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (obj.identifique("Nome").getValor().toUpperCase().contentEquals(view_nome)) {
                return obj;
            }
        }

        return null;
    }


    public void views_remover(String view_nome) {
        view_nome = view_nome.toUpperCase();

        Banco s_inits = AZSequenciador.organizar_banco(mArmazenador, "@Init");
        Banco s_views = AZSequenciador.organizar_banco(mArmazenador, "@Views");

        for (ItemDoBanco item : s_views.getItens()) {
            DKGObjeto obj = DKG.PARSER_TO_OBJETO(item.lerTexto());
            if (obj.identifique("Nome").getValor().toUpperCase().contentEquals(view_nome)) {
                s_views.remover(item);
                break;
            }
        }

    }

}
