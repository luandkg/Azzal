package libs.aqz.volume;

import libs.aqz.colecao.ColecaoTX;
import libs.aqz.utils.AZSequenciador;
import libs.aqz.utils.ItemDoBancoTX;
import libs.armazenador.Armazenador;
import libs.armazenador.Particao;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.fmt;
import libs.tempo.Calendario;

public class AZInternamentePastas {

    // LUAN FREITAS
    // IDEALIZADO EM : 2024 01 04
    // IMPLEMENTADO EM 2024 11 10

    private Armazenador mArmazenador;

    public final String AQZ_INIT = "@Init";

    public final String COLECOES_DADOS = "@Pastas::Dados";
    public final String COLECOES_SEQUENCIAS = "@Pastas::Sequencias";


    public AZInternamentePastas(String arquivo_banco) {

        Armazenador.checar(arquivo_banco);
        mArmazenador = new Armazenador(arquivo_banco);


    }

    public AZInternamentePastas(Armazenador eArmazenador) {
        mArmazenador = eArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }

    public void colecoes_criar(String colecao_nome) {

        String nome_original = colecao_nome;

        colecao_nome = colecao_nome.toUpperCase();


        ParticaoMestre s_inits = mArmazenador.getParticaoMestre(AQZ_INIT);
        ParticaoMestre s_bancos = mArmazenador.getParticaoMestre(COLECOES_DADOS);
        ParticaoMestre s_sequencias = mArmazenador.getParticaoMestre(COLECOES_SEQUENCIAS);


        Entidade init_bancos = new Entidade();
        ItemDoBancoTX ref_init_bancos = null;
        boolean init_bancos_existe = false;

        for (ItemDoBancoTX item : s_inits.getItensTX()) {
            Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            if (item_dkg.at("Nome").toUpperCase().contentEquals("COLECAO")) {
                ref_init_bancos = item;
                init_bancos = item_dkg;
                init_bancos_existe = true;
                break;
            }
        }

        fmt.print("Pastando v1 :: {}", s_inits.getItensTX().getQuantidade());

        if (!init_bancos_existe) {
            Entidade nova_init = new Entidade();
            nova_init.at("PID", s_inits.getItensAlocadosContagem());
            nova_init.at("Nome", "COLECAO");
            nova_init.at("Corrente", 0);
            nova_init.at("Sequencia", 1);
            nova_init.at("DDC", Calendario.getTempoCompleto());
            nova_init.at("DDA", Calendario.getTempoCompleto());
            s_inits.adicionarTX(nova_init);

            fmt.print("Pastando v2 :: {}", s_inits.getItensTX().getQuantidade());

            for (ItemDoBancoTX item : s_inits.getItensTX()) {
                Entidade item_dkg = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
                if (item_dkg.at("Nome").toUpperCase().contentEquals("COLECAO")) {
                    ref_init_bancos = item;
                    init_bancos = item_dkg;
                    init_bancos_existe = true;
                    break;
                }
            }

            fmt.print("Pastando v3 :: {}", s_inits.getItensTX().getQuantidade());


            if (!init_bancos_existe) {
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Init nao encontrada : " + COLECOES_DADOS);
            }

        }

        boolean existe = false;
        boolean criado = false;

        for (ItemDoBancoTX item : s_bancos.getItensTX()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            if (obj_colecao.is("Status", "OK") && obj_colecao.at("Nome").toUpperCase().contentEquals(colecao_nome)) {
                existe = true;
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Já existe uma coleção com esse nome : " + colecao_nome);
            }
        }

        for (ItemDoBancoTX item : s_bancos.getItensTX()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            if (obj_colecao.is("Status", "DESTRUIDO")) {

                String nome_antigo = obj_colecao.at("Nome");

                int banco_id = obj_colecao.atInt("PID");

                long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro");

                long local_itens = obj_colecao.atLong("Capitulos");
                long local_cache = obj_colecao.atLong("Cache");
                long local_corrente = obj_colecao.atLong("PPPC");
                long local_indice = obj_colecao.atLong("Indice");


                ParticaoMestre particaoPrimaria_remover = new ParticaoMestre(nome_antigo, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                particaoPrimaria_remover.limpar();

                AZSequenciador.zerar_sequencial(s_sequencias, nome_antigo);


                int novo_id = init_bancos.atInt("Corrente");

                init_bancos.at("Corrente", init_bancos.atInt("Corrente") + init_bancos.atInt("Sequencia"));
                init_bancos.at("Sequencia", 1);
                init_bancos.at("DDA", Calendario.getTempoCompleto());

                ref_init_bancos.atualizarTX(init_bancos);

                obj_colecao.at("PID", novo_id);
                obj_colecao.at("Status", "OK");
                obj_colecao.at("Nome", colecao_nome.toUpperCase());
                obj_colecao.at("NomeOriginal", nome_original);
                obj_colecao.at("DDC", Calendario.getTempoCompleto());
                obj_colecao.at("DDA", Calendario.getTempoCompleto());

                item.atualizarTX(obj_colecao);
                criado = true;
                return;
            }
        }

        if (criado) {
            return;
        }


        if (!existe) {

            int banco_id = init_bancos.atInt("Corrente");

            init_bancos.at("Corrente", init_bancos.atInt("Corrente") + init_bancos.atInt("Sequencia"));
            init_bancos.at("Sequencia", 1);
            init_bancos.at("DDA", Calendario.getTempoCompleto());

            ref_init_bancos.atualizarTX(init_bancos);


            Particao particao = mArmazenador.criarParticao();


            Entidade nova_colecao = new Entidade();

            nova_colecao.at("PID", banco_id);
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

            s_bancos.adicionarTX(nova_colecao);

        }

    }

    public Lista<ParticaoMestre> particoes_listar() {


        Lista<ParticaoMestre> colecoes = new Lista<ParticaoMestre>();


        ParticaoMestre s_bancos = mArmazenador.getParticaoMestre(COLECOES_DADOS);


        for (ItemDoBancoTX item : s_bancos.getItensTX()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTextoTX());

            //  fmt.print("AQZ STATUS :: {}",ENTT.TO_DOCUMENTO(obj_colecao));

            if (obj_colecao.is("Status", "OK")) {

                String nome = obj_colecao.at("Nome");

                int banco_id = obj_colecao.atInt("PID");

                long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro");

                long local_itens = obj_colecao.atLong("Capitulos");
                long local_cache = obj_colecao.atLong("Cache");
                long local_corrente = obj_colecao.atLong("PPPC");
                long local_indice = obj_colecao.atLong("Indice");

                ParticaoMestre particaoPrimaria = new ParticaoMestre(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                colecoes.adicionar(particaoPrimaria);


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return colecoes;

    }


    public Lista<ColecaoTX> colecoes_listar() {


        Lista<ColecaoTX> colecoes = new Lista<ColecaoTX>();


        ParticaoMestre s_inits = mArmazenador.getParticaoMestre(AQZ_INIT);
        ParticaoMestre s_bancos = mArmazenador.getParticaoMestre(COLECOES_DADOS);
        ParticaoMestre s_sequencias = mArmazenador.getParticaoMestre(COLECOES_SEQUENCIAS);


        for (ItemDoBancoTX item : s_bancos.getItensTX()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTextoTX());

            //  fmt.print("AQZ STATUS :: {}",ENTT.TO_DOCUMENTO(obj_colecao));

            if (obj_colecao.is("Status", "OK")) {

                String nome = obj_colecao.at("Nome");

                int indice = obj_colecao.atInt("PID");

                long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro");

                long local_itens = obj_colecao.atLong("Capitulos");
                long local_cache = obj_colecao.atLong("Cache");
                long local_corrente = obj_colecao.atLong("PPPC");
                long local_indice = obj_colecao.atLong("Indice");

                ParticaoMestre particaoPrimaria = new ParticaoMestre(nome, mArmazenador, mArmazenador.getArquivador(), indice, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                ColecaoTX colecao = new ColecaoTX(indice, particaoPrimaria.getNome(), mArmazenador, s_sequencias, particaoPrimaria);
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

        ParticaoMestre s_bancos = mArmazenador.getParticaoMestre(COLECOES_DADOS);
        ParticaoMestre s_sequencias = mArmazenador.getParticaoMestre(COLECOES_SEQUENCIAS);

        for (ItemDoBancoTX item : s_bancos.getItensTX()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTextoTX());

            String nome = obj_colecao.at("Nome");

            int banco_id = obj_colecao.atInt("PID", 0);

            long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro", 0);

            long local_itens = obj_colecao.atLong("Capitulos", 0);
            long local_cache = obj_colecao.atLong("Cache", 0);
            long local_corrente = obj_colecao.atLong("PPPC", 0);
            long local_indice = obj_colecao.atLong("Indice", 0);


            if (obj_colecao.is("Status", "OK") && nome.contentEquals(colecao_nome)) {
                ParticaoMestre particaoPrimaria_remover = new ParticaoMestre(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice);
                particaoPrimaria_remover.limpar();
                obj_colecao.at("Status", "DESTRUIDO");
                AZSequenciador.zerar_sequencial(s_sequencias, colecao_nome);

                //   fmt.print("DESTRUINDO :: {}", obj_colecao.toDocumento());

                item.atualizarTX(obj_colecao);
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


    public Lista<ParticaoMestre> particoes_primarias() {
        return mArmazenador.getParticoes();
    }


    public ParticaoMestre primarios_colecoes_obter(String colecao_nome) {

        for (ParticaoMestre b : mArmazenador.getParticoes()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return b;
            }
        }

        return null;
    }

    public Armazenador getArmazenador() {
        return mArmazenador;
    }

}
