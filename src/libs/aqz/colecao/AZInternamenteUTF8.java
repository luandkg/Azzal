package libs.aqz.colecao;

import libs.aqz.utils.AZSequenciador;
import libs.aqz.utils.ItemDoBancoTX;
import libs.aqz.utils.ProcuradorTX;
import libs.armazenador.Armazenador;
import libs.armazenador.Particao;
import libs.armazenador.ParticaoMestre;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Par;
import libs.tempo.Calendario;

public class AZInternamenteUTF8 {

    // LUAN FREITAS
    // CRIADO : 2024 11 05 - COLECAO EM UTF-8
    // IMPLEMENTADO EM 2024 11 10

    private Armazenador mArmazenador;

    public final String AQZ_INIT = "@Init";

    public final String COLECOES_DADOS = "@ColecoesUTF8::Dados";
    public final String COLECOES_SEQUENCIAS = "@ColecoesUTF8::Sequencias";


    public AZInternamenteUTF8(String arquivo_banco) {

        Armazenador.CHECAR(arquivo_banco);
        mArmazenador = new Armazenador(arquivo_banco);


    }

    public AZInternamenteUTF8(Armazenador eArmazenador) {
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

        String coluna_nome_sequenciador="UTF8";

        Opcional<Par<ItemDoBancoTX, Entidade>> sequenciador = ProcuradorTX.procurar(s_inits, "Nome", coluna_nome_sequenciador);

        if (!sequenciador.isOK()) {

            Entidade nova_init = new Entidade();
            nova_init.at("Nome", coluna_nome_sequenciador);
            nova_init.at("Sequencia", 0);
            nova_init.at("Passo", 1);
            nova_init.at("DDC", Calendario.getTempoCompleto());
            nova_init.at("DDA", Calendario.getTempoCompleto());
            s_inits.adicionarTX(nova_init);

            sequenciador = ProcuradorTX.procurar(s_inits, "Nome", coluna_nome_sequenciador);
        }

        if (!sequenciador.isOK()) {
            mArmazenador.fechar();
            throw new RuntimeException("AQZ ERRO - Init nao encontrada : " + coluna_nome_sequenciador);
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



                int novo_id = sequenciador.get().getValor().atInt("Sequencia");

                sequenciador.get().getValor().at("Sequencia", sequenciador.get().getValor().atInt("Sequencia") + sequenciador.get().getValor().atInt("Passo"));
                sequenciador.get().getValor().at("DDA", Calendario.getTempoCompleto());

                sequenciador.get().getChave().atualizarTX(sequenciador.get().getValor());



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


            int novo_id = sequenciador.get().getValor().atInt("Sequencia");

            sequenciador.get().getValor().at("Sequencia", sequenciador.get().getValor().atInt("Sequencia") + sequenciador.get().getValor().atInt("Passo"));
            sequenciador.get().getValor().at("DDA", Calendario.getTempoCompleto());

            sequenciador.get().getChave().atualizarTX(sequenciador.get().getValor());




            Particao particao = mArmazenador.criarParticao();


            Entidade nova_colecao = new Entidade();

            nova_colecao.at("PID", novo_id);
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


    public Lista<ColecaoUTF8> colecoes_listar() {


        Lista<ColecaoUTF8> colecoes = new Lista<ColecaoUTF8>();


        ParticaoMestre s_inits = mArmazenador.getParticaoMestre(AQZ_INIT);
        ParticaoMestre s_bancos = mArmazenador.getParticaoMestre(COLECOES_DADOS);
        ParticaoMestre s_sequencias = mArmazenador.getParticaoMestre(COLECOES_SEQUENCIAS);


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
                ColecaoUTF8 colecao = new ColecaoUTF8(particaoPrimaria.getNome(), mArmazenador, s_sequencias, particaoPrimaria);
                colecoes.adicionar(colecao);


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return colecoes;

    }

    public boolean colecoes_existe(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ColecaoUTF8 b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return true;
            }
        }

        return false;
    }

    public ColecaoUTF8 banco_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ColecaoUTF8 b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {
                return b;
            }
        }

        return null;
    }


    public ColecaoUTF8 colecoes_remover(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        ParticaoMestre s_bancos = mArmazenador.getParticaoMestre(COLECOES_DADOS);
        ParticaoMestre s_sequencias = mArmazenador.getParticaoMestre(COLECOES_SEQUENCIAS);

        for (ItemDoBancoTX item : s_bancos.getItensTX()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTextoTX());

            String nome = obj_colecao.at("Nome");

            int banco_id = obj_colecao.atInt("PID");

            long ponteiro_inicial_do_banco = obj_colecao.atLong("Ponteiro");

            long local_itens = obj_colecao.atLong("Capitulos");
            long local_cache = obj_colecao.atLong("Cache");
            long local_corrente = obj_colecao.atLong("PPPC");
            long local_indice = obj_colecao.atLong("Indice");


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

    public ColecaoUTF8 colecoes_obter(String colecao_nome) {

        colecao_nome = colecao_nome.toUpperCase();

        for (ColecaoUTF8 b : colecoes_listar()) {
            if (b.getNome().contentEquals(colecao_nome)) {

                // fmt.print("AQZ STATUS BANCO - {}",colecao_nome);
                return b;
            }
        }

        return null;
    }


    public void colecao_orgarnizar(String eNomeColecao) {
        if (!colecoes_existe(eNomeColecao)) {
            colecoes_criar(eNomeColecao);
        }
    }

    public ColecaoUTF8 colecao_orgarnizar_e_obter(String eNomeColecao) {
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
