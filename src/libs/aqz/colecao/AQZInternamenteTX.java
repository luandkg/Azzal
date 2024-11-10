package libs.aqz.colecao;

import libs.aqz.utils.ItemDoBanco;
import libs.aqz.utils.OrquestradorBancario;
import libs.aqz.utils.Sequenciador;
import libs.armazenador.Armazenador;
import libs.armazenador.Particao;
import libs.armazenador.ParticaoPrimaria;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.tempo.Calendario;

public class AQZInternamenteTX {

    // LUAN FREITAS
    // IDEALIZADO EM : 2024 01 04
    // IMPLEMENTADO EM 2024 11 10

    private Armazenador mArmazenador;

    public final String AQZ_INIT = "@Init";

    public final String COLECOES_DADOS = "@ColecoesTX::Dados";
    public final String COLECOES_SEQUENCIAS= "@ColecoesTX::Sequencias";


    public AQZInternamenteTX(String arquivo_banco) {

        Armazenador.checar(arquivo_banco);
        mArmazenador = new Armazenador(arquivo_banco);


    }

    public AQZInternamenteTX(Armazenador eArmazenador) {
        mArmazenador = eArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }

    public void colecoes_criar(String colecao_nome) {

        String nome_original = colecao_nome;

        colecao_nome = colecao_nome.toUpperCase();


        ParticaoPrimaria s_inits = OrquestradorBancario.organizar_banco(mArmazenador, AQZ_INIT);
        ParticaoPrimaria s_bancos = OrquestradorBancario.organizar_banco(mArmazenador,COLECOES_DADOS );
        ParticaoPrimaria s_sequencias = OrquestradorBancario.organizar_banco(mArmazenador, COLECOES_SEQUENCIAS);


        Entidade init_bancos = new Entidade();
        ItemDoBanco ref_init_bancos = null;
        boolean init_bancos_existe = false;

        for (ItemDoBanco item : s_inits.getItens()) {
            Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (item_dkg.at("Nome").toUpperCase().contentEquals("COLECAO")) {
                ref_init_bancos = item;
                init_bancos = item_dkg;
                init_bancos_existe = true;
                break;
            }
        }


        if (!init_bancos_existe) {
            Entidade nova_init = new Entidade();
            nova_init.at("Nome", "COLECAO");
            nova_init.at("Corrente", 0);
            nova_init.at("Sequencia", 1);
            nova_init.at("DDC", Calendario.getTempoCompleto());
            nova_init.at("DDA", Calendario.getTempoCompleto());
            s_inits.adicionar(nova_init);

            for (ItemDoBanco item : s_inits.getItens()) {
                Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTexto());
                if (item_dkg.at("Nome").toUpperCase().contentEquals("COLECAO")) {
                    ref_init_bancos = item;
                    init_bancos = item_dkg;
                    init_bancos_existe = true;
                    break;
                }
            }

            if (!init_bancos_existe) {
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Init nao encontrada : @ColecoesTX::Dados");
            }

        }

        boolean existe = false;
        boolean criado = false;

        for (ItemDoBanco item : s_bancos.getItens()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTexto());
            if (obj_colecao.is("Status", "OK") && obj_colecao.at("Nome").toUpperCase().contentEquals(colecao_nome)) {
                existe = true;
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Já existe uma coleção com esse nome : " + colecao_nome);
            }
        }

        for (ItemDoBanco item : s_bancos.getItens()) {
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


            Particao particao = mArmazenador.criarParticao();


            Entidade nova_colecao = new Entidade();

            nova_colecao.at("BID", banco_id);
            nova_colecao.at("Status", "OK");
            nova_colecao.at("Nome", colecao_nome.toUpperCase());
            nova_colecao.at("NomeOriginal", nome_original);
            nova_colecao.at("DDC", Calendario.getTempoCompleto());
            nova_colecao.at("DDA", Calendario.getTempoCompleto());


            nova_colecao.at("Ponteiro", particao.ponteiro_inicial_do_banco);
            nova_colecao.at("Capitulos", particao.guardar_capitulos);
            nova_colecao.at("Cache", particao.guardar_cache);
            nova_colecao.at("PPPC", particao.guardar_primeira_pagina_do_primeiro_capitulo);
            nova_colecao.at("Indice", particao.guardar_local_indice);

            s_bancos.adicionar(nova_colecao);

        }

    }

    public Lista<ParticaoPrimaria> particoes_listar() {


        Lista<ParticaoPrimaria> colecoes = new Lista<ParticaoPrimaria>();


        ParticaoPrimaria s_bancos = OrquestradorBancario.organizar_banco(mArmazenador, COLECOES_DADOS);


        for (ItemDoBanco item : s_bancos.getItens()) {
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

                ParticaoPrimaria  particaoPrimaria=  new ParticaoPrimaria(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                colecoes.adicionar(particaoPrimaria);


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return colecoes;

    }


    public Lista<ColecaoTX> colecoes_listar() {


        Lista<ColecaoTX> colecoes = new Lista<ColecaoTX>();


        ParticaoPrimaria s_inits = OrquestradorBancario.organizar_banco(mArmazenador, AQZ_INIT);
        ParticaoPrimaria s_bancos = OrquestradorBancario.organizar_banco(mArmazenador, COLECOES_DADOS);
        ParticaoPrimaria s_sequencias = OrquestradorBancario.organizar_banco(mArmazenador, COLECOES_SEQUENCIAS);


        for (ItemDoBanco item : s_bancos.getItens()) {
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

                ParticaoPrimaria  particaoPrimaria=  new ParticaoPrimaria(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                ColecaoTX colecao = new ColecaoTX(particaoPrimaria.getNome(), mArmazenador, s_sequencias, particaoPrimaria);
                colecoes.adicionar(colecao);


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return colecoes;

    }

    public boolean colecoes_existe(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ColecaoTX b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return true;
            }
        }

        return false;
    }

    public ColecaoTX banco_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ColecaoTX b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return b;
            }
        }

        return null;
    }


    public ColecaoTX colecoes_remover(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        ParticaoPrimaria s_bancos = OrquestradorBancario.organizar_banco(mArmazenador, COLECOES_DADOS);
        ParticaoPrimaria s_sequencias = OrquestradorBancario.organizar_banco(mArmazenador, COLECOES_SEQUENCIAS);

        for (ItemDoBanco item : s_bancos.getItens()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTexto());

            String nome = obj_colecao.at("Nome");

            int banco_id = obj_colecao.atInt("BID", 0);

            long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro", 0);

            long local_itens = obj_colecao.atLong("Capitulos", 0);
            long local_cache = obj_colecao.atLong("Cache", 0);
            long local_corrente = obj_colecao.atLong("PPPC", 0);
            long local_indice = obj_colecao.atLong("Indice", 0);


            if (obj_colecao.is("Status", "OK") && nome.contentEquals(colecao_nome)) {
                ParticaoPrimaria particaoPrimaria_remover = new ParticaoPrimaria(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                particaoPrimaria_remover.limpar();
                obj_colecao.at("Status", "DESTRUIDO");
                Sequenciador.zerar_sequencial(s_sequencias, colecao_nome);

                //   fmt.print("DESTRUINDO :: {}", obj_colecao.toDocumento());

                item.atualizar(obj_colecao);
                break;
            }


        }


        return null;
    }

    public ColecaoTX colecoes_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ColecaoTX b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {

                // fmt.print("AQZ STATUS BANCO - {}",colecao_nome);
                return b;
            }
        }

        return null;
    }


    public ColecaoTX colecao_orgarnizar_e_obter(String eNomeColecao) {
        if (!colecoes_existe(eNomeColecao)) {
            colecoes_criar(eNomeColecao);
        }

        return colecoes_obter(eNomeColecao);
    }


    public Lista<ParticaoPrimaria> particoes_primarias() {
        return mArmazenador.getBancos();
    }


    public ParticaoPrimaria primarios_colecoes_obter(String colecao_nome) {

        for (ParticaoPrimaria b : mArmazenador.getBancos()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return b;
            }
        }

        return null;
    }

    public Armazenador getArmazenador(){return mArmazenador;}

}
