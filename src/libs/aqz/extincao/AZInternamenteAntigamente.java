package libs.aqz.extincao;

import libs.armazenador.Armazenador;
import libs.aqz.extincao.armazenador_antigo.ArmazenadorEmExtincao;
import libs.armazenador.ParticaoEmExtincao;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.tempo.Calendario;

public class AZInternamenteAntigamente {

    // LUAN FREITAS
    // CRIADO EM : 2024 01 04
    // ATUALIZADO : 2024 01 04
    // ATUALIZADO : 2024 11 05 - COLECAO EM UTF-8

    private ArmazenadorEmExtincao mArmazenador;


    public AZInternamenteAntigamente(String arquivo_banco) {

        ArmazenadorEmExtincao.checar(arquivo_banco);
        mArmazenador = new ArmazenadorEmExtincao(arquivo_banco);


    }

    public AZInternamenteAntigamente(ArmazenadorEmExtincao eArmazenador) {
        mArmazenador = eArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }

    public void colecoes_criar(String colecao_nome) {

        String nome_original = colecao_nome;

        colecao_nome = colecao_nome.toUpperCase();


        ParticaoEmExtincao s_inits = mArmazenador.getParticaoEmExtincao("@Init");
        ParticaoEmExtincao s_bancos = mArmazenador.getParticaoEmExtincao("@Bancos");
        ParticaoEmExtincao s_sequencias = mArmazenador.getParticaoEmExtincao("@Sequencias");
        ParticaoEmExtincao s_auto_analises = mArmazenador.getParticaoEmExtincao("@AutoAnalise");
        ParticaoEmExtincao s_analises = mArmazenador.getParticaoEmExtincao("@Analise");
        ParticaoEmExtincao s_views = mArmazenador.getParticaoEmExtincao("@Views");


        Entidade init_bancos = new Entidade();
        ItemDoBancoEmExtincao ref_init_bancos = null;
        boolean init_bancos_existe = false;

        for (ItemDoBancoEmExtincao item : s_inits.getItens()) {
            Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (item_dkg.at("Nome").toUpperCase().contentEquals("BANCOS")) {
                ref_init_bancos = item;
                init_bancos = item_dkg;
                init_bancos_existe = true;
                break;
            }
        }


        if (!init_bancos_existe) {
            Entidade nova_init = new Entidade();
            nova_init.at("Nome", "BANCOS");
            nova_init.at("Corrente", 0);
            nova_init.at("Sequencia", 1);
            nova_init.at("DDC", Calendario.getTempoCompleto());
            nova_init.at("DDA", Calendario.getTempoCompleto());
            s_inits.adicionar(nova_init);

            for (ItemDoBancoEmExtincao item : s_inits.getItens()) {
                Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTexto());
                if (item_dkg.at("Nome").toUpperCase().contentEquals("BANCOS")) {
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

        for (ItemDoBancoEmExtincao item : s_bancos.getItens()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (obj_colecao.is("Status", "OK") && obj_colecao.at("Nome").toUpperCase().contentEquals(colecao_nome)) {
                existe = true;
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Já existe uma coleção com esse nome : " + colecao_nome);
            }
        }

        for (ItemDoBancoEmExtincao item : s_bancos.getItens()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (obj_colecao.is("Status", "DESTRUIDO")) {

                String nome_antigo = obj_colecao.at("Nome");

                int banco_id = obj_colecao.atInt("BID");

                long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro");

                long local_itens = obj_colecao.atLong("Capitulos");
                long local_cache = obj_colecao.atLong("Cache");
                long local_corrente = obj_colecao.atLong("PPPC");
                long local_indice = obj_colecao.atLong("Indice");


                ParticaoEmExtincao particaoEmExtincao_remover = new ParticaoEmExtincao(nome_antigo, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                particaoEmExtincao_remover.limpar();

                Sequenciador.zerar_sequencial(s_sequencias, nome_antigo);


                int novo_id = init_bancos.atInt("Corrente", 0);

                init_bancos.at("Corrente", init_bancos.atInt("Corrente", 0) + init_bancos.atInt("Sequencia", 0));
                init_bancos.at("Sequencia", 1);
                init_bancos.at("DDA", Calendario.getTempoCompleto());

                ref_init_bancos.atualizar(init_bancos);

                obj_colecao.at("BID", novo_id);
                obj_colecao.at("Status", "OK");
                obj_colecao.at("Nome", colecao_nome.toUpperCase());
                obj_colecao.at("NomeOriginal", nome_original);
                obj_colecao.at("DDC", Calendario.getTempoCompleto());
                obj_colecao.at("DDA", Calendario.getTempoCompleto());

                item.atualizar(obj_colecao);
                criado = true;
                return;
            }
        }

        if (criado) {
            return;
        }


        if (!existe) {

            int banco_id = init_bancos.atInt("Corrente", 0);

            init_bancos.at("Corrente", init_bancos.at("Corrente", 0) + init_bancos.at("Sequencia", 0));
            init_bancos.at("Sequencia", 1);
            init_bancos.at("DDA", Calendario.getTempoCompleto());

            ref_init_bancos.atualizar(init_bancos);

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

            //   System.out.println("CRIAR INDICE EM :: " + guardar_local_indice);

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


            Entidade nova_colecao = new Entidade();

            nova_colecao.at("BID", banco_id);
            nova_colecao.at("Status", "OK");
            nova_colecao.at("Nome", colecao_nome.toUpperCase());
            nova_colecao.at("NomeOriginal", nome_original);
            nova_colecao.at("DDC", Calendario.getTempoCompleto());
            nova_colecao.at("DDA", Calendario.getTempoCompleto());


            nova_colecao.at("Ponteiro", ponteiro_inicial_do_banco);
            nova_colecao.at("Capitulos", guardar_capitulos);
            nova_colecao.at("Cache", guardar_cache);
            nova_colecao.at("PPPC", guardar_primeira_pagina_do_primeiro_capitulo);
            nova_colecao.at("Indice", guardar_local_indice);

            s_bancos.adicionar(nova_colecao);

        }

    }


    public Lista<ParticaoEmExtincao> colecoes_listar() {


        Lista<ParticaoEmExtincao> mBancos = new Lista<ParticaoEmExtincao>();


        ParticaoEmExtincao s_inits = mArmazenador.getParticaoEmExtincao(  "@Init");
        ParticaoEmExtincao s_bancos = mArmazenador.getParticaoEmExtincao( "@Bancos");

        for (ItemDoBancoEmExtincao item : s_bancos.getItens()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTexto());

            //  fmt.print("AQZ STATUS :: {}",ENTT.TO_DOCUMENTO(obj_colecao));

            if (obj_colecao.is("Status", "OK")) {

                String nome = obj_colecao.at("Nome");

                int banco_id = obj_colecao.atInt("BID");

                long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro");

                long local_itens = obj_colecao.atLong("Capitulos");
                long local_cache = obj_colecao.atLong("Cache");
                long local_corrente = obj_colecao.atLong("PPPC");
                long local_indice = obj_colecao.atLong("Indice");


                mBancos.adicionar(new ParticaoEmExtincao(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice));


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return mBancos;

    }

    public boolean colecoes_existe(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ParticaoEmExtincao b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return true;
            }
        }

        return false;
    }

    public ParticaoEmExtincao banco_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ParticaoEmExtincao b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return b;
            }
        }

        return null;
    }


    public ParticaoEmExtincao colecoes_remover(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        ParticaoEmExtincao s_bancos = mArmazenador.getParticaoEmExtincao(  "@Bancos");
        ParticaoEmExtincao s_sequencias = mArmazenador.getParticaoEmExtincao(  "@Sequencias");

        for (ItemDoBancoEmExtincao item : s_bancos.getItens()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTexto());

            String nome = obj_colecao.at("Nome");

            int banco_id = obj_colecao.atInt("BID", 0);

            long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro", 0);

            long local_itens = obj_colecao.atLong("Capitulos", 0);
            long local_cache = obj_colecao.atLong("Cache", 0);
            long local_corrente = obj_colecao.atLong("PPPC", 0);
            long local_indice = obj_colecao.atLong("Indice", 0);


            if (obj_colecao.is("Status", "OK") && nome.contentEquals(colecao_nome)) {
                ParticaoEmExtincao particaoEmExtincao_remover = new ParticaoEmExtincao(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                particaoEmExtincao_remover.limpar();
                obj_colecao.at("Status", "DESTRUIDO");
                Sequenciador.zerar_sequencial(s_sequencias, colecao_nome);

                //   fmt.print("DESTRUINDO :: {}", obj_colecao.toDocumento());

                item.atualizar(obj_colecao);
                break;
            }


        }


        return null;
    }

    public ColecaoAntigamente colecoes_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        ParticaoEmExtincao s_sequencias =mArmazenador.getParticaoEmExtincao(  "@Sequencias");

        for (ParticaoEmExtincao b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {

                // fmt.print("AQZ STATUS BANCO - {}",colecao_nome);

                return new ColecaoAntigamente(colecao_nome, mArmazenador, s_sequencias, b);
            }
        }

        return null;
    }


    public ColecaoAntigamente colecao_orgarnizar_e_obter(String eNomeColecao) {
        if (!colecoes_existe(eNomeColecao)) {
            colecoes_criar(eNomeColecao);
        }

        return colecoes_obter(eNomeColecao);
    }


    public ColecaoUTF8Antigamente colecoes_obter_utf8(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        ParticaoEmExtincao s_sequencias = mArmazenador.getParticaoEmExtincao( "@Sequencias");

        for (ParticaoEmExtincao b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return new ColecaoUTF8Antigamente(colecao_nome, mArmazenador, s_sequencias, b);
            }
        }

        return null;
    }

    public Lista<ParticaoEmExtincao> particoes_primarias() {
        return mArmazenador.getBancos();
    }


    public ParticaoEmExtincao primarios_colecoes_obter(String colecao_nome) {

        for (ParticaoEmExtincao b : mArmazenador.getBancos()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return b;
            }
        }

        return null;
    }


    public void auto_analisar() {

        ParticaoEmExtincao s_inits = mArmazenador.getParticaoEmExtincao(  "@Init");
        ParticaoEmExtincao s_bancos = mArmazenador.getParticaoEmExtincao(  "@Bancos");
        ParticaoEmExtincao s_sequencias = mArmazenador.getParticaoEmExtincao(  "@Sequencias");
        ParticaoEmExtincao s_auto_analises =mArmazenador.getParticaoEmExtincao(  "@AutoAnalise");
        ParticaoEmExtincao s_analises = mArmazenador.getParticaoEmExtincao(  "@Analise");


        ParticaoEmExtincao colecao_analise = primarios_colecoes_obter("@AutoAnalise");
        colecao_analise.limpar();

        Lista<Entidade> objetos_analisados = new Lista<Entidade>();

        for (ParticaoEmExtincao b : mArmazenador.getBancos()) {

            Entidade obj_analise = new Entidade();

            obj_analise.at("BID", b.getID());
            obj_analise.at("Nome", b.getNome());

            long itens_alocados = b.getItensAlocadosContagem();
            long itens_utilizados = b.getItensContagem();

            obj_analise.at("Itens_Disponiveis", itens_alocados);
            obj_analise.at("Itens_Utilizados", itens_utilizados);

            obj_analise.at("Disponibilidade", b.getDisponibilidade());
            obj_analise.at("Usabilidadade", b.getUsabilidade());

            obj_analise.at("Capitulos_Contagem", b.getCapitulosContagem());
            obj_analise.at("Paginas_Contagem", b.getPaginasContagem());

            obj_analise.at("Indice_Disponibilidade", b.getIndiceDisponibilidade());
            obj_analise.at("Indice_Usabilidade", b.getIndiceUsabilidade());
            obj_analise.at("Indice_Paginas", b.getIndicePaginasContagem());
            obj_analise.at("Indice_Contagem", b.getIndiceItensTotalContagem());
            obj_analise.at("Indice_Utilizados", b.getIndiceItensUtilizadoContagem());

            objetos_analisados.adicionar(obj_analise);

        }

        for (Entidade obj_analise : ENTT.ORDENAR_TEXTO(objetos_analisados, "Nome")) {
            colecao_analise.adicionar(obj_analise);
        }

        // colecao_analise.primeiro_campo("ID");
    }

    public void analisar() {

        ParticaoEmExtincao s_inits =mArmazenador.getParticaoEmExtincao(  "@Init");
        ParticaoEmExtincao s_bancos = mArmazenador.getParticaoEmExtincao(  "@Bancos");
        ParticaoEmExtincao s_sequencias = mArmazenador.getParticaoEmExtincao(  "@Sequencias");
        ParticaoEmExtincao s_auto_analises = mArmazenador.getParticaoEmExtincao(  "@AutoAnalise");
        ParticaoEmExtincao s_analises = mArmazenador.getParticaoEmExtincao(  "@Analise");


        ParticaoEmExtincao colecao_analise = primarios_colecoes_obter("@Analise");
        colecao_analise.limpar();

        Lista<Entidade> objetos_analisados = new Lista<Entidade>();

        for (ParticaoEmExtincao b : colecoes_listar()) {

            Entidade obj_analise = new Entidade();

            obj_analise.at("BID", b.getID());
            obj_analise.at("Nome", b.getNome());

            long itens_alocados = b.getItensAlocadosContagem();
            long itens_utilizados = b.getItensContagem();

            obj_analise.at("Itens_Disponiveis", itens_alocados);
            obj_analise.at("Itens_Utilizados", itens_utilizados);

            obj_analise.at("Disponibilidade", b.getDisponibilidade());
            obj_analise.at("Usabilidadade", b.getUsabilidade());

            obj_analise.at("Capitulos_Contagem", b.getCapitulosContagem());
            obj_analise.at("Paginas_Contagem", b.getPaginasContagem());

            obj_analise.at("Indice_Disponibilidade", b.getIndiceDisponibilidade());
            obj_analise.at("Indice_Usabilidade", b.getIndiceUsabilidade());
            obj_analise.at("Indice_Paginas", b.getIndicePaginasContagem());
            obj_analise.at("Indice_Contagem", b.getIndiceItensTotalContagem());
            obj_analise.at("Indice_Utilizados", b.getIndiceItensUtilizadoContagem());

            obj_analise.at("Cache_Itens", b.getItensEmCacheContagem());

            objetos_analisados.adicionar(obj_analise);

        }

        for (Entidade obj_analise : ENTT.ORDENAR_TEXTO(objetos_analisados, "Nome")) {
            colecao_analise.adicionar(obj_analise);
        }

        // colecao_analise.primeiro_campo("ID");
    }





}
