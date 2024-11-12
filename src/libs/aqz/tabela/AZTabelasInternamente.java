package libs.aqz.tabela;

import libs.aqz.colecao.ColecaoUTF8;
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

public class AZTabelasInternamente {

    // LUAN FREITAS
    // CRIADO EM : 2024 11 08

    private Armazenador mArmazenador;


    public AZTabelasInternamente(String arquivo_banco) {

        Armazenador.checar(arquivo_banco);
        mArmazenador = new Armazenador(arquivo_banco);


    }

    public AZTabelasInternamente(Armazenador eArmazenador) {
        mArmazenador = eArmazenador;
    }

    public void fechar() {
        mArmazenador.fechar();
    }


    public ColecaoUTF8 tabela_orgarnizar_e_obter(String eNomeColecao) {
        if (!tabelas_existe(eNomeColecao)) {
            tabelas_criar(eNomeColecao);
        }

        return tabelas_obter(eNomeColecao);
    }


    public boolean tabelas_existe(String tabela_nome) {

        tabela_nome = tabela_nome.toUpperCase();

        for (ParticaoMestre b : tabelas_listar()) {
            if (b.getNome().contentEquals(tabela_nome)) {
                return true;
            }
        }

        return false;
    }


    public void tabelas_criar(String tabela_nome) {

        String nome_original = tabela_nome;

        tabela_nome = tabela_nome.toUpperCase();

        ParticaoMestre s_tabelas = mArmazenador.getParticaoMestre("@Tabelas");

        ParticaoMestre s_tabelas_dados = mArmazenador.getParticaoMestre("@TabelasDados");
        ParticaoMestre s_tabelas_esquemas = mArmazenador.getParticaoMestre("@TabelasEsquemas");

        ParticaoMestre s_sequencias_dados = mArmazenador.getParticaoMestre("@TabelasDados::Sequencias");
        ParticaoMestre s_sequencias_esquemas = mArmazenador.getParticaoMestre("@TabelasEsquemas::Sequencias");


        tabela_criar_dados(s_tabelas,"@TabelaID", s_tabelas_dados, s_sequencias_dados, nome_original, tabela_nome);
        tabela_criar_dados(s_tabelas,"@EsquemaID", s_tabelas_esquemas, s_sequencias_esquemas, nome_original, tabela_nome);

    }


    private void tabela_criar_dados(ParticaoMestre s_conjunto_mestre,String coluna_nome_sequenciador, ParticaoMestre s_conjunto_de_bancos, ParticaoMestre s_conjunto_de_sequencias, String nome_original, String tabela_nome) {


        Opcional<Par<ItemDoBancoTX, Entidade>> sequenciador = ProcuradorTX.procurar(s_conjunto_mestre, "Nome", coluna_nome_sequenciador);

        if (!sequenciador.isOK()) {

            Entidade nova_init = new Entidade();
            nova_init.at("Nome", coluna_nome_sequenciador);
            nova_init.at("Sequencia", 0);
            nova_init.at("Passo", 1);
            nova_init.at("DDC", Calendario.getTempoCompleto());
            nova_init.at("DDA", Calendario.getTempoCompleto());
            s_conjunto_mestre.adicionarTX(nova_init);

            sequenciador = ProcuradorTX.procurar(s_conjunto_mestre, "Nome", coluna_nome_sequenciador);
        }

        if (!sequenciador.isOK()) {
            mArmazenador.fechar();
            throw new RuntimeException("AQZ ERRO - Init nao encontrada : " + coluna_nome_sequenciador);
        }



        boolean existe = false;
        boolean criado = false;

        for (ItemDoBancoTX item : s_conjunto_de_bancos.getItensTX()) {
            Entidade obj_colecao = ENTT.PARSER_ENTIDADE(item.lerTextoTX());
            if (obj_colecao.is("Status", "OK") && obj_colecao.at("Nome").toUpperCase().contentEquals(tabela_nome)) {
                existe = true;
                mArmazenador.fechar();
                throw new RuntimeException("AQZ ERRO - Já existe uma tabela com esse nome : " + tabela_nome);
            }
        }

        for (ItemDoBancoTX item : s_conjunto_de_bancos.getItensTX()) {
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

                AZSequenciador.zerar_sequencial(s_conjunto_de_sequencias, nome_antigo);


                int novo_id = sequenciador.get().getValor().atInt("Sequencia");

                sequenciador.get().getValor().at("Sequencia", sequenciador.get().getValor().atInt("Sequencia") + sequenciador.get().getValor().atInt("Passo"));
                sequenciador.get().getValor().at("DDA", Calendario.getTempoCompleto());

                sequenciador.get().getChave().atualizarTX(sequenciador.get().getValor());

                obj_colecao.at("PID", novo_id);
                obj_colecao.at("Status", "OK");
                obj_colecao.at("Nome", tabela_nome.toUpperCase());
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
            nova_colecao.at("Nome", tabela_nome.toUpperCase());
            nova_colecao.at("NomeOriginal", nome_original);
            nova_colecao.at("DDC", Calendario.getTempoCompleto());
            nova_colecao.at("DDA", Calendario.getTempoCompleto());


            nova_colecao.at("Ponteiro", particao.ponteiro_inicial_do_banco);
            nova_colecao.at("Capitulos", particao.guardar_capitulos);
            nova_colecao.at("Cache", particao.guardar_cache);
            nova_colecao.at("PPPC", particao.guardar_primeira_pagina_do_primeiro_capitulo);
            nova_colecao.at("Indice", particao.guardar_local_indice);

            s_conjunto_de_bancos.adicionarTX(nova_colecao);

        }

    }


    public Lista<ParticaoMestre> tabelas_listar() {


        Lista<ParticaoMestre> mBancos = new Lista<ParticaoMestre>();

        ParticaoMestre s_tabelas_dados = mArmazenador.getParticaoMestre("@TabelasDados");

        for (ItemDoBancoTX item : s_tabelas_dados.getItensTX()) {
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


                mBancos.adicionar(new ParticaoMestre(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice));


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return mBancos;

    }

    public ColecaoUTF8 tabelas_obter(String tabela_nome) {

        tabela_nome = tabela_nome.toUpperCase();

        ParticaoMestre s_sequencias = mArmazenador.getParticaoMestre("@TabelasDados::Sequencias");

        for (ParticaoMestre b : tabelas_listar()) {
            if (b.getNome().contentEquals(tabela_nome)) {

                // fmt.print("AQZ STATUS BANCO - {}",colecao_nome);

                return new ColecaoUTF8(tabela_nome, mArmazenador, s_sequencias, b);
            }
        }

        return null;
    }


    public Lista<ParticaoMestre> esquemas_listar() {


        Lista<ParticaoMestre> mBancos = new Lista<ParticaoMestre>();

        ParticaoMestre s_tabelas_dados = mArmazenador.getParticaoMestre("@TabelasEsquemas");

        for (ItemDoBancoTX item : s_tabelas_dados.getItensTX()) {
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


                mBancos.adicionar(new ParticaoMestre(nome, mArmazenador, mArmazenador.getArquivador(), banco_id, ponteiro_inicial_do_banco, local_itens, local_cache, local_corrente, local_indice));


            }


            //    fmt.print("{}", obj_colecao.toDocumento());
        }


        return mBancos;

    }

    public ColecaoUTF8 esquemas_obter(String tabela_nome) {

        tabela_nome = tabela_nome.toUpperCase();

        ParticaoMestre s_sequencias = mArmazenador.getParticaoMestre("@TabelasEsquemas::Sequencias");

        for (ParticaoMestre b : esquemas_listar()) {
            if (b.getNome().contentEquals(tabela_nome)) {

                // fmt.print("AQZ STATUS BANCO - {}",colecao_nome);

                return new ColecaoUTF8(tabela_nome, mArmazenador, s_sequencias, b);
            }
        }

        return null;
    }


    public boolean esquema_existe(String esquema_nome) {

        esquema_nome = esquema_nome.toUpperCase();

        for (ParticaoMestre b : esquemas_listar()) {
            if (b.getNome().contentEquals(esquema_nome)) {
                return true;
            }
        }

        return false;
    }


    public Armazenador getArmazenador() {
        return mArmazenador;
    }


}
