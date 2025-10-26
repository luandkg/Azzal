package apps.app_tozterum;


import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.fmt;
import libs.tempo.Calendario;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;
import libs.zetta.ItemColecionavel;
import libs.zetta.ZettaQuorum;
import libs.zetta.features.ZQC;
import servicos.ASSETS;
import servicos.INMET;
import servicos.TempoSAS;

public class BotCondicoesClimaticas {

    public static String GET_ARQUIVO_CONDICOES_CLIMATICAS() {
        return ASSETS.GET_PASTA("coisas\\tozterum").getArquivo("INMET.az");
    }

    public static void ACOMPANHAR_EM_SINCRONICIDADE() {

        String arquivo_colecao = GET_ARQUIVO_CONDICOES_CLIMATICAS();

        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "TOZTE(SINCRONICIDADE)");

        String tozte_corrente = Tronarko.getTozte().getTextoZerado();

        if (!ZQC.UNICO_EXISTE(arquivo_colecao, "TOZTE(SINCRONICIDADE)", "Tozte", tozte_corrente)) {

            Entidade e_sincronizar = ZQC.NOVA_ENTIDADE();
            e_sincronizar.at("Tozte", tozte_corrente);
            e_sincronizar.at("Disparado", Tronarko.getTronAgora().getTextoZerado());

            ZQC.INSERIR(arquivo_colecao, "TOZTE(SINCRONICIDADE)", e_sincronizar);

            BotCondicoesClimaticas.ACOMPANHAR_TEMPO();

            e_sincronizar.at("Tempo", Tronarko.getTronAgora().getTextoZerado());
            ZQC.UNICO_ATUALIZAR(arquivo_colecao, "TOZTE(SINCRONICIDADE)", "Tozte", tozte_corrente, e_sincronizar);

            BotCondicoesClimaticas.ACOMPANHAR_ALERTAS_CLIMATICOS();

            e_sincronizar.at("Alertas", Tronarko.getTronAgora().getTextoZerado());
            ZQC.UNICO_ATUALIZAR(arquivo_colecao, "TOZTE(SINCRONICIDADE)", "Tozte", tozte_corrente, e_sincronizar);

        }

        ZQC.EXIBIR_COLECAO(arquivo_colecao, "TOZTE(SINCRONICIDADE)");

    }

    public static void ACOMPANHAR_TEMPO() {

        String arquivo_colecao = GET_ARQUIVO_CONDICOES_CLIMATICAS();

        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "INMET");
        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "INMET.UPDATE");
        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "INMET.TIMELINE");
        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "INMET(DF)");
        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "SAS(DF)");
        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "SAS(DF).TIMELINE");


        String TOZTE_MOMENTO = Tronarko.getTozte().getTextoZerado();

        Lista<Entidade> inmet_dados = INMET.GET_DADOS();


        ZettaQuorum aqz = new ZettaQuorum(arquivo_colecao);

        Opcional<ItemColecionavel> op_item = aqz.getColecaoSempre("INMET.UPDATE").obter_opcional("Tozte", TOZTE_MOMENTO);

        boolean limpar = false;

        if (op_item.isOK()) {

        } else {

            if (ENTT.TEM_OBJETOS(inmet_dados)) {

                aqz.getColecaoSempre("INMET.UPDATE").zerar();

                Entidade novo = new Entidade();
                novo.at("Tozte", TOZTE_MOMENTO);
                aqz.getColecaoSempre("INMET.UPDATE").adicionar(novo);

                limpar = true;


            }


        }

        Opcional<ItemColecionavel> op_sas = aqz.getColecaoSempre("SAS(DF)").obter_opcional("Tozte", TOZTE_MOMENTO);

        if (op_sas.isOK()) {

            Entidade n_sas = TempoSAS.GET();
            if (n_sas.isValido("Cidade")) {

                Entidade e_sas = op_sas.get().get();

                e_sas.at("Fuso", n_sas.at("Fuso"));
                e_sas.at("Nascer", n_sas.at("Nascer"));
                e_sas.at("Por", n_sas.at("Por"));

                e_sas.at("Atualizado", "SIM");
                e_sas.at("Atualizacao", Tronarko.getTronAgora().getTextoZerado());
                e_sas.at("AtualizacaoContagem", e_sas.atIntOuPadrao("AtualizacaoContagem", 0) + 1);

                op_sas.get().atualizar();

            }


        } else {

            Entidade e_sas = TempoSAS.GET();
            if (e_sas.isValido("Cidade")) {
                e_sas.at("Atualizado", "NAO");
                e_sas.at("Atualizacao", "");
                e_sas.at("AtualizacaoContagem", 0);
                aqz.getColecaoSempre("SAS(DF)").adicionar(e_sas);
            }

        }

        Lista<Entidade> proximas_datas = TempoSAS.GET_PREVISAO();

        Tozte HOJE = Tronarko.getTozte();

        for (Entidade e : proximas_datas) {

            Tozte esse_tozte = StringTronarko.PARSER_TOZTE(e.at("Tozte"));

            if (esse_tozte.isMaiorIgualQue(HOJE)) {

                Opcional<ItemColecionavel> op_proximo = aqz.getColecaoSempre("SAS(DF)").obter_opcional("Tozte", e.at("Tozte"));
                if (op_proximo.isOK()) {

                    Entidade e_prox = op_proximo.get().get();
                    e_prox.at("Aproximacao", e_prox.atIntOuPadrao("Aproximacao", 0) + 1);
                    e_prox.at("Nascer", e.at("Nascer"));
                    e_prox.at("Por", e.at("Por"));

                    op_proximo.get().atualizar();

                } else {
                    aqz.getColecaoSempre("SAS(DF)").adicionar(e);
                }

            }


        }


        Lista<Entidade> previsa_do_tempo = TempoSAS.GET_PREVISAO_DO_TEMPO();

        for (Entidade previsao : previsa_do_tempo) {

            Tozte tozte_previsao = StringTronarko.PARSER_TOZTE(previsao.at("Tozte"));

            if (tozte_previsao.isMaiorIgualQue(HOJE)) {

                Opcional<ItemColecionavel> op_proximo = aqz.getColecaoSempre("SAS(DF)").obter_opcional("Tozte", previsao.at("Tozte"));
                if (op_proximo.isOK()) {

                    Entidade e_prox = op_proximo.get().get();

                    e_prox.at("Madrugada", previsao.at("Madrugada"));
                    e_prox.at("Manha", previsao.at("Manha"));
                    e_prox.at("Tarde", previsao.at("Tarde"));
                    e_prox.at("Noite", previsao.at("Noite"));

                    if (e_prox.isVazio("PrevisaoDoTempoPrimeiro")) {
                        e_prox.at("PrevisaoDoTempoPrimeiro", Tronarko.getTronAgora().getTextoZerado());
                    }

                    e_prox.at("PrevisaoDoTempoAlteracoes", e_prox.atIntOuPadrao("PrevisaoDoTempoAlteracoes", 0) + 1);
                    e_prox.at("PrevisaoDoTempoAtualizado", Tronarko.getTronAgora().getTextoZerado());

                    op_proximo.get().atualizar();
                }

            }


        }

        for (ItemColecionavel item : aqz.getColecaoSempre("SAS(DF)").getItensEditaveis()) {

            Tozte tozte_esse = StringTronarko.PARSER_TOZTE(item.get().at("Tozte"));

            if (tozte_esse.isMenorQue(HOJE)) {

                Entidade e_arquivar = item.get();
                e_arquivar.at("Arquivado", Tronarko.getTronAgora().getTextoZerado());
                aqz.getColecaoSempre("SAS(DF).TIMELINE").adicionar(e_arquivar);
                item.remover();

            }

        }

        aqz.fechar();


        if (ENTT.TEM_OBJETOS(inmet_dados)) {


            if (limpar) {
                ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "INMET.DADOS");

                for (Entidade inmet_item : inmet_dados) {
                    inmet_item.at("Tozte", Tronarko.getData(inmet_item.at("Data")).getTextoZerado());
                    ZQC.INSERIR(arquivo_colecao, "INMET.DADOS", inmet_item);
                }
            }

            String data = Calendario.getDataHoje().getTempo();

            ZQC.LIMPAR_TUDO(arquivo_colecao, "INMET");

            for (Entidade inmet_item : inmet_dados) {

                inmet_item.at("Tozte", Tronarko.getData(inmet_item.at("Data")).getTextoZerado());
                inmet_item.at("INMET_DATA_INSERIDA", data);

                ZQC.INSERIR(arquivo_colecao, "INMET", inmet_item);
            }


            Entidade nova_tl = ZQC.NOVA_ENTIDADE();
            nova_tl.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            nova_tl.at("Status", "INMET_OK");

            ZQC.INSERIR(arquivo_colecao, "INMET.TIMELINE", nova_tl);

        } else {

            Entidade nova_tl = ZQC.NOVA_ENTIDADE();
            nova_tl.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            nova_tl.at("Status", "INMET_ERRO");

            ZQC.INSERIR(arquivo_colecao, "INMET.TIMELINE", nova_tl);

        }


        if (ENTT.TEM_OBJETOS(previsa_do_tempo) && ENTT.TEM_OBJETOS(proximas_datas)) {

            Entidade nova_tl = ZQC.NOVA_ENTIDADE();
            nova_tl.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            nova_tl.at("Status", "SAS_OK");

            ZQC.INSERIR(arquivo_colecao, "SAS(DF).TIMELINE", nova_tl);

        } else if (ENTT.ZERO_OBJETOS(previsa_do_tempo) && ENTT.ZERO_OBJETOS(proximas_datas)) {

            Entidade nova_tl = ZQC.NOVA_ENTIDADE();
            nova_tl.at("Tron", Tronarko.getTronAgora().getTextoZerado());
            nova_tl.at("Status", "SAS_ERRO");

            ZQC.INSERIR(arquivo_colecao, "SAS(DF).TIMELINE", nova_tl);


        } else {

            Entidade nova_tl = ZQC.NOVA_ENTIDADE();
            nova_tl.at("Tron", Tronarko.getTronAgora().getTextoZerado());

            if (ENTT.ZERO_OBJETOS(proximas_datas)) {
                nova_tl.at("Info", "NASCER_E_POR_DO_SOL");
            }

            if (ENTT.ZERO_OBJETOS(previsa_do_tempo)) {
                nova_tl.at("Info", "PREVISAO_DO_TEMPO");
            }

            nova_tl.at("Status", "SAS_PARCIAL");

            ZQC.INSERIR(arquivo_colecao, "SAS(DF).TIMELINE", nova_tl);


        }


    }

    public static void ACOMPANHAR_ALERTAS_CLIMATICOS() {

        Lista<Entidade> alerta_dados = new Lista<Entidade>();

        alerta_dados.adicionar_varios(INMET.GET_ALERTAR_CLIMATICOS());
        alerta_dados.adicionar_varios(INMET.GET_ALERTAR_CLIMATICOS_V2());


        if (ENTT.TEM(alerta_dados)) {

            ENTT.EXIBIR_TABELA(alerta_dados);

            String arquivo_inmet = GET_ARQUIVO_CONDICOES_CLIMATICAS();

            String colecao_alertas = "ALERTAS_INMET";
            String colecao_alertas_arquivados = "ALERTAS_INMET_ARQUIVADOS";

            //  AZZ.LIMPAR_TUDO(arquivo_inmet,colecao_alertas);
            ZQC.COLECOES_ORGANIZAR(arquivo_inmet, colecao_alertas);
            ZQC.COLECOES_ORGANIZAR(arquivo_inmet, colecao_alertas_arquivados);

            Lista<Entidade> alertas_no_banco = ZQC.COLECAO_ENTIDADES(arquivo_inmet, colecao_alertas);

            for (Entidade alerta : alerta_dados) {
                //fmt.print(">> {}",alerta.at("Titulo"));
                //String horario = Calendario.GET_HORARIO_HM_DE_COMPLETO(alerta.at("Data.Inicio"));
                //fmt.print(">> {}","Hoje a partir das " +horario);

                if (!ENTT.EXISTE(alertas_no_banco, "AlertaRSS", alerta.at("AlertaRSS"))) {
                    ZQC.INSERIR(arquivo_inmet, colecao_alertas, alerta);
                }

            }

            fmt.print("INMET - TODOS OS ALERTAS ENCONTRADOS");
            ZQC.EXIBIR_COLECAO(arquivo_inmet, colecao_alertas);


            ZettaQuorum aqz = new ZettaQuorum(arquivo_inmet);

            aqz.getColecaoSempre(colecao_alertas);
            aqz.getColecaoSempre(colecao_alertas_arquivados);

            Tozte hoje = Tronarko.getTozte();

            for (ItemColecionavel item : aqz.getColecaoSempre(colecao_alertas).getItensEditaveis()) {
                Entidade e_item = item.get();

                Tozte tozte = StringTronarko.PARSER_TOZTE(e_item.at("Tozte"));

                long diff = Tronarko.TOZTE_DIFERENCA(hoje, tozte);
                e_item.at("Diff", diff);

                item.atualizar();

                if (diff < 30) {
                    //  aqz.colecoes_obter(colecao_alertas).remover(item);
                    //   e_item.at("Arquivado",Tronarko.getTronAgora().getTextoZerado());
                    //   aqz.colecoes_obter(colecao_alertas_arquivados).adicionar(e_item);
                }


            }

            aqz.fechar();


            ZQC.EXIBIR_COLECAO(arquivo_inmet, colecao_alertas);

        }


    }


    public static Lista<Entidade> GET_DADOS_BRASILIA_TEMPO_ACOMPANHAR_TEMPO(Tozte HOJE) {

        String arquivo_colecao = GET_ARQUIVO_CONDICOES_CLIMATICAS();

        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "INMET");

        Lista<Entidade> inmet_item = ZQC.COLECAO_ENTIDADES(arquivo_colecao, "INMET");

        Lista<Entidade> previsao_do_tempo_hoje = ENTT.COLETAR(inmet_item, "Tozte", HOJE.getTextoZerado());
        Lista<Entidade> previsao_do_tempo_brasilia_hoje = (ENTT.COLETAR(previsao_do_tempo_hoje, "Capital", "Brasília"));

        return previsao_do_tempo_brasilia_hoje;
    }

    public static Lista<Entidade> GET_SAS_BRASILIA(Tozte HOJE) {

        String arquivo_colecao = GET_ARQUIVO_CONDICOES_CLIMATICAS();

        ZQC.COLECOES_ORGANIZAR(arquivo_colecao, "SAS(DF)");

        Lista<Entidade> inmet_item = ZQC.COLECAO_ENTIDADES(arquivo_colecao, "SAS(DF)");

        Lista<Entidade> previsao_do_tempo_hoje = ENTT.COLETAR(inmet_item, "Tozte", HOJE.getTextoZerado());

        return previsao_do_tempo_hoje;
    }


    public static Opcional<String> OBTER_TEXTO_PREVISAO_DO_TEMPO_EM_BRASILIA_POR_TOZTE(Tozte tozte_marcado) {

        String previsao_do_tempo = "";

        Lista<Entidade> previsao_do_tempo_brasilia_hoje = BotCondicoesClimaticas.GET_DADOS_BRASILIA_TEMPO_ACOMPANHAR_TEMPO(tozte_marcado);
        Lista<Entidade> previsao_do_tempo_sas_brasilia_hoje = BotCondicoesClimaticas.GET_SAS_BRASILIA(tozte_marcado);

        String EMOJI_DIA = "⚡\uFE0F";

        if (previsao_do_tempo_brasilia_hoje.getQuantidade() == 3) {

            Entidade turno_manha = ENTT.GET_SEMPRE(previsao_do_tempo_brasilia_hoje, "Turno", "MANHÃ");
            Entidade turno_tarde = ENTT.GET_SEMPRE(previsao_do_tempo_brasilia_hoje, "Turno", "TARDE");
            Entidade turno_noite = ENTT.GET_SEMPRE(previsao_do_tempo_brasilia_hoje, "Turno", "NOITE");


            if (turno_manha.at("Resumo").contentEquals(turno_tarde.at("Resumo")) && turno_tarde.at("Resumo").contentEquals(turno_noite.at("Resumo"))) {
                previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois("DIA", 8) + " : " + turno_manha.at("Resumo");
            } else if (!turno_manha.at("Resumo").contentEquals(turno_tarde.at("Resumo")) && turno_tarde.at("Resumo").contentEquals(turno_noite.at("Resumo"))) {
                previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois(turno_manha.at("Turno"), 8) + " : " + turno_manha.at("Resumo");
                previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois("RESTO DO DIA", 8) + " : " + turno_tarde.at("Resumo");
            } else {

                previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois(turno_manha.at("Turno"), 8) + " : " + turno_manha.at("Resumo");
                previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois(turno_tarde.at("Turno"), 9) + " : " + turno_tarde.at("Resumo");
                previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois(turno_noite.at("Turno"), 9) + " : " + turno_noite.at("Resumo");

            }

        } else {

            if (previsao_do_tempo_sas_brasilia_hoje.getQuantidade() > 0) {
                Entidade previsao = previsao_do_tempo_sas_brasilia_hoje.get(0);

                if (previsao.isValido("Manha") && previsao.isValido("Tarde") && previsao.isValido("Noite")) {

                    previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois("Manhã", 8) + " : " + previsao.at("Manha");
                    previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois("Tarde", 9) + " : " + previsao.at("Tarde");
                    previsao_do_tempo += "\n " + EMOJI_DIA + " " + fmt.espacar_depois("Noite", 9) + " : " + previsao.at("Noite");

                }


            }

        }

        return Opcional.OK(previsao_do_tempo);
    }

    public static Opcional<String> OBTER_TEXTO_ALERTAS_EM_TOZTE(String tozte_marcado) {

        String arquivo_inmet = GET_ARQUIVO_CONDICOES_CLIMATICAS();
        String colecao_alertas = "ALERTAS_INMET";

        Lista<Entidade> alertas_no_banco = ZQC.COLECAO_ENTIDADES(arquivo_inmet, colecao_alertas);

        boolean tem_alertas = false;
        String texto_alertas = "";

        for (Entidade alerta : alertas_no_banco) {
            if (alerta.is("Tozte", tozte_marcado)) {

                String alerta_s = alerta.at("Descricao");

                if (alerta_s.contains("Chuva")) {
                    alerta_s = "⛈⛈⛈ " + alerta_s + "\n @AlertaINMET";
                }

                texto_alertas += "\n" + alerta_s;

                tem_alertas = true;
            }
        }

        if (tem_alertas) {
            return Opcional.OK(texto_alertas);
        } else {
            return Opcional.CANCEL();
        }

    }


}
