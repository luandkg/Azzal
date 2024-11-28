package testes;

import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fazendario.Fazendario;
import libs.fazendario.IndiceLocalizado;
import libs.fts.FTS;
import libs.luan.*;
import libs.matematica.IntervaloLong;
import libs.matematica.Matematica;
import libs.portugues.PortuguesFTS;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.TronarkoAleatorium;
import libs.zettaquorum.*;

public class TesteZettaQuorum {


    public static void init_estresse() {
        for (int i = 0; i < 100; i++) {
            init();
        }
    }

    public static void init_grande_estresse() {

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        long contagem = 0;


        while (contagem < 100) {

            init();

            ZettaQuorum zeta = new ZettaQuorum(arquivo_zeta);
            ZettaColecao tronarkum = zeta.getColecaoSempre("@Tronarkum");

            contagem = tronarkum.contagem();
            zeta.fechar();

            fmt.print("++ Contagem : {}", contagem);
        }


    }

    public static void init() {

        fmt.print("----------------- ZETA QUORUM ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        ZettaQuorum zeta = new ZettaQuorum(arquivo_zeta);

        ZettaColecao tronarkum = zeta.getColecaoSempre("@Tronarkum");

        //tronarkum.zerar();

        TronarkoAleatorium ta = new TronarkoAleatorium(Tronarko.TOZTE_PRIMEIRO(6900), Tronarko.TOZTE_ULTIMO(7100));

        int adicionar_quantidade = Aleatorio.aleatorio_entre(5, 100);
        int remover_quantidade = Aleatorio.aleatorio_entre(10, 30);
        int atualizar_quantidade = Aleatorio.aleatorio_entre(10, 30);

        for (int a = 0; a < adicionar_quantidade; a++) {

            fmt.print(">> Inserindo : {}", a);

            Entidade item = new Entidade();
            item.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            item.at("Valor", PessoaNomeadorDeAkkax.get());
            item.at("TTS", ta.getTozte().getTextoZerado());

            tronarkum.adicionar(item);

        }


        int passando = Aleatorio.aleatorio_entre(10, 50);

        for (ItemColecionavel item : tronarkum.getItensEditaveis()) {

            if (passando == 0) {
                fmt.print("-- Removendo item : {}", item.get().at("@ID"));

                passando = Aleatorio.aleatorio_entre(10, 50);
                remover_quantidade -= 1;
                item.remover();
            }

            if (remover_quantidade < 0) {
                break;
            }
            passando -= 1;
        }


        int atualizar_passando = Aleatorio.aleatorio_entre(10, 50);

        for (ItemColecionavel item : tronarkum.getItensEditaveis()) {

            if (atualizar_passando == 0) {
                fmt.print("-- Atualizando item : {}", item.get().at("@ID"));

                atualizar_passando = Aleatorio.aleatorio_entre(10, 50);
                atualizar_quantidade -= 1;

                item.get().at("Atualizado", Tronarko.getTronAgora().getTextoZerado());
                item.get().at("Atualizacoes", item.get().atIntOuPadrao("Atualizacoes", 0) + 1);


                Lista<Entidade> palavras = ENTT.CRIAR_LISTA();

                Lista<String> palavras_texto = new Lista<String>();
                for (String palavra : Strings.DIVIDIR_POR(item.get().at("Texto"), " ")) {
                    palavra = palavra.trim();
                    if (!palavra.isEmpty()) {
                        palavras_texto.adicionar(palavra);
                    }
                }

                for (String palavra : palavras_texto) {
                    Entidade e_palavra = ENTT.GET_SEMPRE(palavras, "Palavra", palavra);
                    e_palavra.at("Ranking", Strings.CONTAGEM_EM_LISTA(palavras_texto, palavra));
                }

                item.get().at("FTS", Strings.LINEARIZAR(ENTT.TO_DOCUMENTO(palavras)));
                item.atualizar();
            }

            if (atualizar_quantidade < 0) {
                break;
            }
            atualizar_passando -= 1;
        }


        tronarkum.exibir_colecao();
        // tronarkum.exibir_indice();

        fmt.print("Contagem     : {}", tronarkum.contagem());

        Opcional<IndiceLocalizado> op_maior = tronarkum.getIndiceMaior();

        fmt.print("Maior Indice : {}", OpcionalZ.OBTER_OU_ERRO(op_maior, "NÃO FOI POSSÍVEL ENCONTRAR O MAIOR INDICE !"));

        Opcional<IndiceLocalizado> op_indice = tronarkum.procurar_indice(5);

        fmt.print("Indice(5)    : {}", OpcionalZ.OBTER_PONTEIRO_OU_ERRO(op_indice, "NÃO FOI POSSÍVEL ENCONTRAR O PONTEIRO !"));

        Opcional<Entidade> op_indice_valor = tronarkum.procurar_item_por_indice(5);

        OpcionalZ.EXIBIR_ITEM_OU_ERRO(op_indice_valor, "Indice(5) -->> NÃO FOI POSSÍVEL ENCONTRAR O ITEM !");

        if (op_maior.isOK()) {

            Opcional<IndiceLocalizado> op_indice_ponteiro = tronarkum.procurar_indice(op_maior.get().getIndice());
            Opcional<Entidade> op_ultimo = tronarkum.procurar_item_por_indice(op_maior.get().getIndice());


            fmt.print("Indice(" + op_maior.get().getIndice() + ")    : {}", OpcionalZ.OBTER_PONTEIRO_OU_ERRO(op_indice_ponteiro, "NÃO FOI POSSÍVEL ENCONTRAR O PONTEIRO !"));
            OpcionalZ.EXIBIR_ITEM_OU_ERRO(op_ultimo, "Indice(" + op_maior.get().getIndice() + ") -->> NÃO FOI POSSÍVEL ENCONTRAR O ITEM !");

        }

        zeta.dump();

        zeta.fechar();
    }


    public static void init_paginar() {

        fmt.print("----------------- ZETA QUORUM ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        ZettaQuorum zeta = new ZettaQuorum(arquivo_zeta);

        ZettaColecao tronarkum = zeta.getColecaoSempre("@Tronarkum");

        long contagem = tronarkum.contagem();

        fmt.print("++ Contagem : {}", contagem);


        for (Indexado<IntervaloLong> intervalo : Indexamento.indexe(Matematica.PAGINAR_LONG(contagem, 10))) {
            ENTT.EXIBIR_TABELA_COM_NOME(tronarkum.getItensIntervalo(intervalo.get().getInicio(), intervalo.get().getFim()), "TRONARKUM :: PÁGINA ( " + intervalo.index() + " )");
        }

        //tronarkum.exibir_colecao();

        zeta.fechar();
    }

    public static void ver_estrutura() {

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        fmt.print(">> DUMP : FAZENDARIO");
        Fazendario fazendario = new Fazendario(arquivo_zeta);
        fazendario.dump_armazens_existentes_completo();
        fazendario.fechar();

        fmt.print("");
        fmt.print(">> DUMP : COLEÇÕES");
        ZettaQuorum colecoes = new ZettaQuorum(arquivo_zeta);
        colecoes.dump();
        colecoes.fechar();

        fmt.print("");
        fmt.print(">> DUMP : PASTAS");
        ZettaPastas pastas = new ZettaPastas(arquivo_zeta);
        pastas.dump();
        pastas.fechar();

        fmt.print("");
        fmt.print(">> DUMP : TABELAS");
        ZettaTabelas tabelas = new ZettaTabelas(arquivo_zeta);
        tabelas.dump();
        tabelas.fechar();
    }


    public static void init_fts() {

        fmt.print("----------------- ZETA QUORUM ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        ZettaQuorum zeta = new ZettaQuorum(arquivo_zeta);

        ZettaColecao fts = zeta.getColecaoSempre("@FTS");

        if (fts.contagem() == 0) {
            Lista<Entidade> dados = ENTT.ABRIR("/home/luan/assets/mangas/saint_seiya.entts");
            ENTT.EXIBIR_TABELA(dados);

            for (Entidade e : dados) {
                fmt.print(">> Adicionando dados");
                fts.adicionar(e);
            }
        }


        for (ItemColecionavel item : fts.getItensEditaveis()) {

            if (!item.get().atributo_existe("ResumoFTS")) {
                item.get().at("ResumoFTS", FTS.PARSER_TO_DOCUMENTO(item.get().at("Resumo")));
                item.atualizar();
            }

        }

        // fts.exibir_colecao();

        fts.exibir_colecao(0, 10);

        PortuguesFTS portugues = new PortuguesFTS();

        Lista<Entidade> fts_analisado = new Lista<Entidade>();

        for (Entidade item : fts.getItens()) {

            fmt.print("------------------------------");
            fmt.print("Resumo :: {}", Strings.LINEARIZAR(item.at("Resumo")));
            // fmt.print("FTS    :: {}",Strings.LINEARIZAR(item.at("ResumoFTS") ));

            if (item.atributo_existe("ResumoFTS")) {

                Lista<Entidade> fts_dados = FTS.GET_DOCUMENTO(item.at("ResumoFTS"));

                FTS.RETIRAR_PALAVRAS(fts_dados, portugues.getArtigos());
                FTS.RETIRAR_PALAVRAS(fts_dados, portugues.getPreposicoes());
                FTS.RETIRAR_PALAVRAS(fts_dados, portugues.getPronomes());
                FTS.RETIRAR_PALAVRAS(fts_dados, portugues.getPronomesPossessivos());

                FTS.ORDENAR_MAIOR_RANKING(fts_dados);

                ENTT.EXIBIR_TABELA(ENTT.SLICE(fts_dados, 0, 10));

                FTS.MERGE(fts_analisado, ENTT.SLICE(fts_dados, 0, 10));


            }

        }

        zeta.fechar();

        ENTT.EXIBIR_TABELA(fts_analisado);

        FTS.ORDENAR_MAIOR_RANKING(fts_analisado);
        // FTS.ORDENAR_MAIOR_CITACAO(fts_analisado);

        ENTT.EXIBIR_TABELA(ENTT.SLICE(fts_analisado, 0, 10));


    }

    public static void init_replicacao() {

        fmt.print("----------------- ZETA QUORUM :: REPLICACAO ------------------");

        String arquivo_zeta = "/home/luan/assets/teste_fazendas/zeta.az";

        ZettaQuorum zeta = new ZettaQuorum(arquivo_zeta);


        ZettaColecao alpha = zeta.getColecaoSempre("@Alpha");
        ZettaColecao beta = zeta.getColecaoSempre("@Beta");

        ZettaColecao zeta_logs = zeta.getColecaoSempre("@Zeta::Logs");

        // alpha.zerar();
        // beta.zerar();
        //zeta_logs.zerar();

        fmt.print("++ Alpha Contagem : {}", alpha.contagem());
        fmt.print("++ Beta  Contagem : {}", beta.contagem());

        if (Aleatorio.aleatorio(100) > 60) {
            int novos_alpha = Aleatorio.aleatorio_entre(2, 5);
            for (int i = 0; i < novos_alpha; i++) {

                Entidade novo = new Entidade();
                novo.at("Valor", Aleatorio.aleatorio(100));
                novo.at("Nome", PessoaNomeadorDeAkkax.get());
                novo.at("TTC", Tronarko.getTronAgora().getTextoZerado());

                alpha.adicionar(novo);
            }
        }

        if (Aleatorio.aleatorio(100) > 50) {

            for (ItemColecionavel item : alpha.getItensEditaveis()) {
                if (Aleatorio.aleatorio(100) > 50) {

                    item.get().at("Atualizacoes", item.get().atIntOuPadrao("Atualizacoes", 0) + 1);
                    item.get().at("TTA", Tronarko.getTronAgora().getTextoZerado());
                    item.get().at("Anterior", item.get().at("Valor"));
                    item.get().at("Valor", Aleatorio.aleatorio(100));

                    item.atualizar();
                }
            }

        }

        alpha.exibir_colecao();

        fmt.print("Removendo de alfa");

        if (Aleatorio.aleatorio(100) > 60) {

            for (ItemColecionavel item : alpha.getItensEditaveis()) {
                if (Aleatorio.aleatorio(100) > 70) {
                    item.remover();
                }
            }

        }

        alpha.exibir_colecao();
        beta.exibir_colecao();


        fmt.print(">> OP : SIMETRIA");

        Lista<Entidade> dados_primarios = alpha.getItens();
        Lista<Entidade> dados_secundarios = beta.getItens();


        fmt.print(">> OP : SIMETRIA :: NOVOS");


        Lista<Entidade> novos = ENTT.ANALISAR_OBTER_NOVOS(dados_primarios, dados_secundarios, "@ID", "@RefID");

        fmt.print("Quantidade : {}", ENTT.CONTAGEM(novos));

        for (Entidade novo : novos) {
            novo.at("@RefID", novo.at("@ID"));
            novo.at("@Inserido", Tronarko.getTronAgora().getTextoZerado());
        }

        ENTT.ATRIBUTO_REMOVER(novos, "@ID");
        ENTT.ATRIBUTO_REMOVER(novos, "@PTR");

        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(novos, "@RefID");

        ENTT.EXIBIR_TABELA_COM_NOME(novos, "SIMETRIA -- NOVOS");

        //tronarkum.exibir_colecao();


        beta.adicionar_varios(novos);

        if (novos.getQuantidade() > 0) {
            Entidade log = new Entidade();
            log.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            log.at("Status", "Adicionando");
            log.at("Quantidade", ENTT.CONTAGEM(novos));

            zeta_logs.adicionar(log);
        }

        beta.exibir_colecao();


        fmt.print(">> OP : SIMETRIA :: EXCLUIDOS");
        Lista<Entidade> excluidos = ENTT.ANALISAR_OBTER_EXCLUIDOS(dados_primarios, dados_secundarios, "@ID", "@RefID");

//ENTT.REMOVER_ITEM_SE_TIVER_ATRIBUTO(excluidos,"@Removido");

        fmt.print("Quantidade : {}", ENTT.CONTAGEM(excluidos));

        ENTT.EXIBIR_TABELA_COM_NOME(excluidos, "SIMETRIA -- EXCLUIDOS");


        Lista<ItemColecionavel> itens_atualizaveis = beta.getItensEditaveis();

        for (Entidade e : excluidos) {
            //  beta.procurar_item_por_indice(e.atLong("@RefID"))

            fmt.print("Procurar para remover : {}", e.at("@RefID"));

            for (ItemColecionavel item : itens_atualizaveis) {
                if (!item.isRemovido() && item.get().is("@RefID", e.at("@RefID"))) {
                    fmt.print(":: Removendo {}", item.get().at("@RefID"));

                    //  item.get().at("@Removido", Tronarko.getTronAgora().getTextoZerado());

                    //  item.atualizar();
                    item.remover();
                    break;
                }
            }

        }

        if (excluidos.getQuantidade() > 0) {
            Entidade log = new Entidade();
            log.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            log.at("Status", "Removendo");
            log.at("Quantidade", ENTT.CONTAGEM(novos));
            zeta_logs.adicionar(log);
        }

        fmt.print(">> OP : SIMETRIA :: EM COMUM");
        Lista<Par<Entidade, Entidade>> em_comum = ENTT.ANALISAR_OBTER_EM_COMUM_PARES(dados_primarios, dados_secundarios, "@ID", "@RefID");

        fmt.print("Quantidade : {}", em_comum.getQuantidade());

        fmt.print(">> OP : SIMETRIA :: ATUALIZADOS");

        Lista<Par<Entidade, Entidade>> editados = new Lista<Par<Entidade, Entidade>>();

        for (Par<Entidade, Entidade> dupla : em_comum) {

            Lista<String> alpha_atributos = ENTT.GET_ATRIBUTOS_NOMES(dupla.getChave());
            Lista<String> beta_atributos = ENTT.GET_ATRIBUTOS_NOMES(dupla.getValor());

            alpha_atributos = Strings.RETIRAR_ITEM_SE_COMECAR_COM(alpha_atributos, "@");
            beta_atributos = Strings.RETIRAR_ITEM_SE_COMECAR_COM(beta_atributos, "@");

            if (Strings.LISTAS_IGUAIS(alpha_atributos, beta_atributos)) {

                boolean editado = false;

                for (String att : alpha_atributos) {
                    if (Strings.isDiferente(dupla.getChave().at(att), dupla.getValor().at(att))) {
                        editado = true;
                        break;
                    }
                }

                if (editado) {
                    editados.adicionar(dupla);
                }

            } else {
                editados.adicionar(dupla);
            }

        }

        fmt.print("Quantidade : {}", editados.getQuantidade());


        for (Par<Entidade, Entidade> dupla : editados) {

            ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(dupla.getChave(), dupla.getValor()));

            for (ItemColecionavel item : itens_atualizaveis) {
                if (!item.isRemovido() && item.get().is("@RefID", dupla.getValor().at("@RefID"))) {
                    fmt.print(":: Atualizando {}", item.get().at("@RefID"));

                    Lista<String> beta_atributos = ENTT.GET_ATRIBUTOS_NOMES(dupla.getValor());
                    beta_atributos = Strings.RETIRAR_ITEM_SE_COMECAR_COM(beta_atributos, "@");

                    for (String att : beta_atributos) {
                        item.get().at_remover(att);
                    }

                    item.get().at("@Atualizado", Tronarko.getTronAgora().getTextoZerado());


                    Lista<String> alpha_atributos = ENTT.GET_ATRIBUTOS_NOMES(dupla.getChave());
                    alpha_atributos = Strings.RETIRAR_ITEM_SE_COMECAR_COM(alpha_atributos, "@");

                    for (String att : alpha_atributos) {
                        item.get().at(att, dupla.getChave().at(att));
                    }

                    item.atualizar();
                    break;
                }
            }

        }

        if (editados.getQuantidade() > 0) {
            Entidade log = new Entidade();
            log.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            log.at("Status", "Atualizando");
            log.at("Quantidade", editados.getQuantidade());

            zeta_logs.adicionar(log);
        }

        alpha.exibir_colecao();
        beta.exibir_colecao();

        fmt.print("++ Alpha Contagem : {}", alpha.contagem());
        fmt.print("++ Beta  Contagem : {}", beta.contagem());

        zeta_logs.exibir_colecao();

        zeta.fechar();
    }

}
