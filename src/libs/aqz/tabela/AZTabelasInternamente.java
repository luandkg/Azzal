package libs.aqz.tabela;

import libs.aqz.BancoBS;
import libs.aqz.colecao.ColecaoUTF8Antigamente;
import libs.aqz.utils.ItemDoBanco;
import libs.aqz.utils.OrquestradorBancario;
import libs.aqz.utils.Sequenciador;
import libs.armazenador.Armazenador;
import libs.armazenador.ParticaoPrimaria;
import libs.arquivos.binario.Arquivador;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.tempo.Calendario;

public class AZTabelasInternamente {

    // LUAN FREITAS
    // CRIADO EM : 2024 11 08

    private Armazenador mArmazenador;


    public AZTabelasInternamente(String arquivo_banco) {

        BancoBS.checar(arquivo_banco);
        mArmazenador = new Armazenador(arquivo_banco);


    }

    public AZTabelasInternamente(Armazenador eArmazenador) {
        mArmazenador = eArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }



    public ColecaoUTF8Antigamente tabela_orgarnizar_e_obter(String eNomeColecao){
        if (!tabelas_existe(eNomeColecao)) {
            tabelas_criar(eNomeColecao);
        }

        return tabelas_obter(eNomeColecao);
    }


    public boolean tabelas_existe(String tabela_nome) {

        tabela_nome = tabela_nome.toUpperCase();

        for (ParticaoPrimaria b : tabelas_listar()) {
            if (b.getNome().contentEquals(tabela_nome)) {
                return true;
            }
        }

        return false;
    }



    public void tabelas_criar(String tabela_nome) {

        String nome_original = tabela_nome;

        tabela_nome = tabela_nome.toUpperCase();


        ParticaoPrimaria s_tabelas_dados = OrquestradorBancario.organizar_banco(mArmazenador, "@TabelasDados");
        ParticaoPrimaria s_tabelas_esquemas = OrquestradorBancario.organizar_banco(mArmazenador, "@TabelasEsquemas");

        ParticaoPrimaria s_sequencias_dados = OrquestradorBancario.organizar_banco(mArmazenador, "@TabelasDados::Sequencias");
        ParticaoPrimaria s_sequencias_esquemas = OrquestradorBancario.organizar_banco(mArmazenador, "@TabelasEsquemas::Sequencias");


        tabela_criar_dados(s_tabelas_dados, s_sequencias_dados, nome_original, tabela_nome);
        tabela_criar_dados(s_tabelas_esquemas, s_sequencias_esquemas, nome_original, tabela_nome);

    }


    private void tabela_criar_dados(ParticaoPrimaria s_conjunto_de_bancos, ParticaoPrimaria s_conjunto_de_sequencias, String nome_original, String tabela_nome) {

        Entidade init_bancos = new Entidade();
        ItemDoBanco ref_init_bancos = null;
        boolean init_bancos_existe = false;

        for (ItemDoBanco item : s_conjunto_de_bancos.getItens()) {
            Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (item_dkg.at("Nome").toUpperCase().contentEquals("TABELA")) {
                ref_init_bancos = item;
                init_bancos = item_dkg;
                init_bancos_existe = true;
                break;
            }
        }


        if (!init_bancos_existe) {
            Entidade nova_init = new Entidade();
            nova_init.at("Nome", "TABELA");
            nova_init.at("Corrente", 0);
            nova_init.at("Sequencia", 1);
            nova_init.at("DDC", Calendario.getTempoCompleto());
            nova_init.at("DDA", Calendario.getTempoCompleto());
            s_conjunto_de_bancos.adicionar(nova_init);

            for (ItemDoBanco item : s_conjunto_de_bancos.getItens()) {
                Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTexto());
                if (item_dkg.at("Nome").toUpperCase().contentEquals("TABELA")) {
                    ref_init_bancos = item;
                    init_bancos = item_dkg;
                    init_bancos_existe = true;
                    break;
                }
            }

            if (!init_bancos_existe) {
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Init nao encontrada : TABELAS");
            }

        }

        boolean existe = false;
        boolean criado = false;

        for (ItemDoBanco item : s_conjunto_de_bancos.getItens()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (obj_colecao.is("Status", "OK") && obj_colecao.at("Nome").toUpperCase().contentEquals(tabela_nome)) {
                existe = true;
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Já existe uma tabela com esse nome : " + tabela_nome);
            }
        }

        for (ItemDoBanco item : s_conjunto_de_bancos.getItens()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (obj_colecao.is("Status", "DESTRUIDO")) {

                String nome_antigo = obj_colecao.at("Nome");

                int banco_id = obj_colecao.atInt("BID");

                long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro");

                long local_itens = obj_colecao.atLong("Capitulos");
                long local_cache = obj_colecao.atLong("Cache");
                long local_corrente = obj_colecao.atLong("PPPC");
                long local_indice = obj_colecao.atLong("Indice");


                ParticaoPrimaria particaoPrimaria_remover = new ParticaoPrimaria(nome_antigo, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                particaoPrimaria_remover.limpar();

                Sequenciador.zerar_sequencial(s_conjunto_de_sequencias, nome_antigo);


                int novo_id = init_bancos.atInt("Corrente", 0);

                init_bancos.at("Corrente", init_bancos.atInt("Corrente", 0) + init_bancos.atInt("Sequencia", 0));
                init_bancos.at("Sequencia", 1);
                init_bancos.at("DDA", Calendario.getTempoCompleto());

                ref_init_bancos.atualizar(init_bancos);

                obj_colecao.at("BID", novo_id);
                obj_colecao.at("Status", "OK");
                obj_colecao.at("Nome", tabela_nome.toUpperCase());
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
            nova_colecao.at("Nome", tabela_nome.toUpperCase());
            nova_colecao.at("NomeOriginal", nome_original);
            nova_colecao.at("DDC", Calendario.getTempoCompleto());
            nova_colecao.at("DDA", Calendario.getTempoCompleto());


            nova_colecao.at("Ponteiro", ponteiro_inicial_do_banco);
            nova_colecao.at("Capitulos", guardar_capitulos);
            nova_colecao.at("Cache", guardar_cache);
            nova_colecao.at("PPPC", guardar_primeira_pagina_do_primeiro_capitulo);
            nova_colecao.at("Indice", guardar_local_indice);

            s_conjunto_de_bancos.adicionar(nova_colecao);

        }

    }


    public Lista<ParticaoPrimaria> tabelas_listar() {


        Lista<ParticaoPrimaria> mBancos = new Lista<ParticaoPrimaria>();

        ParticaoPrimaria s_tabelas_dados = OrquestradorBancario.organizar_banco(mArmazenador, "@TabelasDados");

        for (ItemDoBanco item : s_tabelas_dados.getItens()) {
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


                mBancos.adicionar(new ParticaoPrimaria(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice));


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return mBancos;

    }

    public ColecaoUTF8Antigamente tabelas_obter(String tabela_nome) {

        tabela_nome = tabela_nome.toUpperCase();

        ParticaoPrimaria s_sequencias = OrquestradorBancario.organizar_banco(mArmazenador, "@TabelasDados::Sequencias");

        for (ParticaoPrimaria b : tabelas_listar()) {
            if (b.getNome().contentEquals(tabela_nome)) {

                // fmt.print("AQZ STATUS BANCO - {}",colecao_nome);

                return new ColecaoUTF8Antigamente(tabela_nome, mArmazenador, s_sequencias, b);
            }
        }

        return null;
    }


    public Lista<ParticaoPrimaria> esquemas_listar() {


        Lista<ParticaoPrimaria> mBancos = new Lista<ParticaoPrimaria>();

        ParticaoPrimaria s_tabelas_dados = OrquestradorBancario.organizar_banco(mArmazenador, "@TabelasEsquemas");

        for (ItemDoBanco item : s_tabelas_dados.getItens()) {
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


                mBancos.adicionar(new ParticaoPrimaria(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice));


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return mBancos;

    }

    public ColecaoUTF8Antigamente esquemas_obter(String tabela_nome) {

        tabela_nome = tabela_nome.toUpperCase();

        ParticaoPrimaria s_sequencias = OrquestradorBancario.organizar_banco(mArmazenador, "@TabelasEsquemas::Sequencias");

        for (ParticaoPrimaria b : esquemas_listar()) {
            if (b.getNome().contentEquals(tabela_nome)) {

                // fmt.print("AQZ STATUS BANCO - {}",colecao_nome);

                return new ColecaoUTF8Antigamente(tabela_nome, mArmazenador, s_sequencias, b);
            }
        }

        return null;
    }


    public Armazenador getArmazenador(){return mArmazenador;}


}
