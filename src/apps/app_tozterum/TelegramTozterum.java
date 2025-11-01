package apps.app_tozterum;

import libs.arquivos.binario.Arquivador;
import libs.dkg.DKGObjeto;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.meta_functional.AcaoBeta;
import libs.tempo.Calendario;
import libs.tronarko.Hazde;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.zetta.ItemColecionavel;
import libs.zetta.ZettaColecao;
import libs.zetta.ZettaQuorum;
import libs.zetta.features.ZQC;
import servicos.ASSETS;



public class TelegramTozterum extends Perpetum {

    public static final String TRONARKO_BOT_TOKEN = "6815773158:AAFZWouxOPwtDw16OQSEiAOqfo1KBlCXVcY";

    private RefString s_turno = new RefString(Calendario.getTurno());

    private RefInt s_10 = new RefInt(11);
    private RefInt s_30 = new RefInt(31);
    private RefInt s_500 = new RefInt(501);
    private RefInt s_metropoles = new RefInt(0);

    private static String TOZTERUM_IDENTIFICADOR = "";
    private static int TOZTERUM_SEQUENCIAL = 0;

    private static int AUTO_ANALISE_MAXIMO = 0;
    private static int AUTO_ANALISE_CONTADOR = 0;
    private static int AUTO_ANALISE_ITTAS = 0;


    public static boolean GG_NOTIFICAR = false;
    private static boolean EXECUTAR_EXTERNO_PLANEJAMENTO = false;


    public TelegramTozterum() {

        fmt.print(">> ATUALIZAR TEMPO INMET");

        BotCondicoesClimaticas.ACOMPANHAR_EM_SINCRONICIDADE();


        TOZTERUM_IDENTIFICADOR = Tronarko.getTronAgora().getTextoZerado();
        TOZTERUM_SEQUENCIAL = 1;

        AUTO_ANALISE_MAXIMO = 500 + Aleatorio.aleatorio(10) * 100;
        AUTO_ANALISE_CONTADOR = 0;
        AUTO_ANALISE_ITTAS = 0;

    }

    public static String GET_ARQUIVO_TOZTERUM() {
        return ASSETS.GET_PASTA("coisas/tozterum").getArquivo("Tozterum.az");
    }

    @Override
    public AcaoBeta<Integer, Integer> ACAO() {
        AcaoBeta<Integer, Integer> acao = new AcaoBeta<Integer, Integer>() {
            @Override
            public void fazer(Integer hora, Integer minuto) {

                Tozte tozte_corrente = Tronarko.getTozte();
                Hazde hazde_corrente = Tronarko.getHazde();

                STATUS(s_turno.get(), s_10.get(), s_30.get(), s_500.get(), s_metropoles.get());

                fmt.print("HOJE : {} -->> {}", Tronarko.getTozte().getTextoZerado(), Tronarko.getHazde().getTextoSemUzzonZerado());


                if (hora >= 7 && hora <= 17) {

                    s_metropoles.somar(1);

                    if (s_metropoles.isIgual(5)) {

                        fmt.print(">> METROPOLES DF ->> INICIANDO");

                        fmt.print(">> METROPOLES DF ->> OK");

                    }

                    if (s_metropoles.isIgual(10)) {

                        fmt.print(">> METROPOLES BRASIL ->> INICIANDO");

                        fmt.print(">> METROPOLES DF ->> OK");

                    }

                    if (s_metropoles.isIgual(20)) {

                        // DESATIVADO 2024 11 12

                        //  fmt.print(">> METROPOLES ELEICOES 2024 ->> INICIANDO");
                        // Metropoles2024.ACOMPANHAR_EL24();
                        // fmt.print(">> METROPOLES DF ->> OK");

                    }

                    if (s_metropoles.isMaiorIgual(20)) {
                        s_metropoles.set(0);
                    }

                }

                if (hora == 6) {
                    BotLuan.LUAN_BOM_DIA_UNICO(true);

                   // BotGG.GG_BOM_DIA_UNICO(GG_NOTIFICAR);

                    fmt.print(">> TUDO OK");
                }


                if (hora == 9 || hora == 15 || hora == 21) {
                    BotLuan.ATZUM_VIAGEM(hora, Lista.CRIAR(9, 15, 21));
                }

                if (tozte_corrente.getSuperarko() == 1 && hazde_corrente.getArco() == 8) {
                    BotLuan.LUAN_RETROSPECTIVA();
                    if (GG_NOTIFICAR) {
                     //   BotGG.GG_RETROSPECTIVA(GG_NOTIFICAR);
                    }
                }


                //TAMAGOTCHI();

                // CADA10(s_10,hora);
                CADA_TURNO(s_turno, hora);
                CADA30(s_30, hora);
                CADA500(s_500, hora);

                //    BotPlanejamento.SEDF_TAREFAS(hora);




            }
        };


        return acao;
    }



    public static void CADA10(RefInt contador, int hora) {
        contador.somar(1);

        if (contador.get() >= 10 && (hora < 4 || hora > 8)) {
            contador.set(0);


        }

    }


    public static void CADA_TURNO(RefString turno, int hora) {

        String turno_anterior = turno.get();
        String turno_corrente = Calendario.getTurno();

        if (Strings.isDiferente(turno_anterior, turno_corrente)) {
            turno.set(turno_corrente);

            fmt.print("\t ->> Mudança de turno = {} -> {}", turno_anterior, turno_corrente);


            Lista<Entidade> dados_turnos = ZQC.COLECAO_ENTIDADES(GET_ARQUIVO_TOZTERUM(), "Turnos");

            int turno_id = ENTT.INTEIRO_MAIOR(dados_turnos, "TurnoID") + 1;

            ZQC.INSERIR(GET_ARQUIVO_TOZTERUM(), "Turnos", ENTT.CRIAR("TurnoID", String.valueOf(turno_id), "Momento", Tronarko.getTronAgora().getTextoZerado(), "Anterior", turno_anterior, "Corrente", turno_corrente));

            if (ZQC.COLECAO_CONTAGEM(GET_ARQUIVO_TOZTERUM(), "Turnos") > 50) {

                int turno_eliminar_abaixo = turno_id - 50;

                int turno_eliminar_abaixo_primeiro = 0;
                int turno_eliminar_abaixo_ultimo = 0;

                String momento_primeiro = "";
                String momento_ultimo = "";


                Lista<String> remover_turnos = new Lista<String>();

                boolean primeiro = true;


                for (Entidade e_turno : dados_turnos) {
                    if (e_turno.atInt("TurnoID") < turno_eliminar_abaixo) {
                        remover_turnos.adicionar(e_turno.at("TurnoID"));

                        if (primeiro) {
                            turno_eliminar_abaixo_primeiro = e_turno.atInt("TurnoID");
                            turno_eliminar_abaixo_ultimo = e_turno.atInt("TurnoID");

                            momento_primeiro=e_turno.at("Momento");
                            momento_ultimo=e_turno.at("Momento");

                            primeiro = false;
                        }else{
                            if(e_turno.atInt("TurnoID")<turno_eliminar_abaixo_primeiro){
                                momento_primeiro=e_turno.at("Momento");
                                turno_eliminar_abaixo_primeiro = e_turno.atInt("TurnoID");
                            }
                            if(e_turno.atInt("TurnoID")>turno_eliminar_abaixo_ultimo){
                                momento_ultimo=e_turno.at("Momento");
                                turno_eliminar_abaixo_ultimo = e_turno.atInt("TurnoID");
                            }
                        }
                    }
                }

                ZQC.REMOVER_EM_LOTE(GET_ARQUIVO_TOZTERUM(), "Turnos", "TurnoID", remover_turnos);

                if (remover_turnos.getQuantidade() > 0) {
                    ZQC.INSERIR(GET_ARQUIVO_TOZTERUM(), "Turnos::Logs", ENTT.CRIAR( "Iniciado",momento_primeiro, "Finalizado", momento_ultimo, "Status", Tronarko.getTronAgora().getTextoZerado()));
                }

            }

            BotCondicoesClimaticas.ACOMPANHAR_TEMPO();
            BotCondicoesClimaticas.ACOMPANHAR_ALERTAS_CLIMATICOS();

        }


    }

    public static void CADA30(RefInt contador, int hora) {

        contador.somar(1);
        if (contador.get() >= 30) {
            contador.set(0);

            BotCondicoesClimaticas.ACOMPANHAR_EM_SINCRONICIDADE();

            if (hora >= 8 && hora <= 18) {

                BotLuan.LUAN_ACOMPANHAR_NO_DIA();
                if (GG_NOTIFICAR) {
                 //   BotGG.GG_ACOMPANHAR_NO_DIA(GG_NOTIFICAR);
                }

                BotLuan.CNU_INFORMAR();
                if (GG_NOTIFICAR) {
                //    BotGG.CNU_INFORMAR(GG_NOTIFICAR);
                }


                fmt.print(">> TUDO OK");
            }

            if (hora == 18) {

            }

        }


    }


    public static void STATUS(String aturno, int a10, int a30, int a500, int am) {

        String ARQUIVO_GERAL_TOZTERUM = GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "CRONOLOGICAMENTE";


        ZettaQuorum zetta = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);

        zetta.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        String Tozte_HOJE = Tronarko.getTozte().getTextoZerado();

        boolean is_disponivel = true;

        for (ItemColecionavel item : zetta.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN).getItensEditaveis()) {
            Entidade item_dados = item.get();
            if (item_dados.is("Tozte", Tozte_HOJE)) {

                item_dados.at("Atualizada", Tronarko.getHazde().getTextoZerado());
                item_dados.at("Quantidade", item_dados.atInt("Quantidade") + 1);

                item_dados.at("Turno", aturno);
                item_dados.at("S10", a10);
                item_dados.at("S30", a30);
                item_dados.at("S500", a500);
                item_dados.at("Metropoles", am);

                item.atualizar();

                is_disponivel = false;
                break;
            }
        }

        if (is_disponivel) {

            Entidade item = new Entidade();
            item.at("Tozte", Tozte_HOJE);
            item.at("TozterumID", TOZTERUM_IDENTIFICADOR);
            item.at("TozterumSEQ", TOZTERUM_SEQUENCIAL);
            item.at("Iniciada", Tronarko.getHazde().getTextoZerado());
            item.at("Atualizada", Tronarko.getHazde().getTextoZerado());
            item.at("Quantidade", 1);
            zetta.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN).adicionar(item);

            TOZTERUM_SEQUENCIAL += 1;
        }

        zetta.fechar();


        if (AUTO_ANALISE_ITTAS != Tronarko.getHazde().getItta()) {

            AUTO_ANALISE_CONTADOR += 1;

            if (AUTO_ANALISE_CONTADOR >= AUTO_ANALISE_MAXIMO) {
                AUTO_ANALISE_CONTADOR = 0;
                AUTO_ANALISE_MAXIMO = 500 + Aleatorio.aleatorio(10) * 100;

                fmt.print(">> AUTO ANALISE - INICIADA");

                long tamanho_total = Arquivador.GET_TAMANHO(ARQUIVO_GERAL_TOZTERUM);

                zetta = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);


                zetta.getColecaoSempre("BANCO.TAMANHO");

                String TOZTE_CORRENTE = Tronarko.getTozte().getTextoZerado();

                ZettaColecao banco_tamanho = zetta.getColecaoSempre("BANCO.TAMANHO");

                Opcional<ItemColecionavel> op_existe = banco_tamanho.obter_opcional("Tozte", TOZTE_CORRENTE);

                if (op_existe.isOK()) {

                    Entidade e_item = op_existe.get().get();
                    e_item.at("Atualizado", e_item.atIntOuPadrao("Atualizado", 0) + 1);
                    e_item.at("TempoAtualizado", Tronarko.getTronAgora().getTextoZerado());
                    e_item.at("Tamanho", fmt.formatar_tamanho_precisao_dupla(tamanho_total));

                    op_existe.get().atualizar();

                } else {

                    Entidade e_item = new Entidade();
                    e_item.at("Tozte", Tronarko.getTozte().getTextoZerado());
                    e_item.at("TempoInsercao", Tronarko.getTronAgora().getTextoZerado());
                    e_item.at("TempoAtualizado", Tronarko.getTronAgora().getTextoZerado());
                    e_item.at("Atualizado", 0);
                    e_item.at("Tamanho", fmt.formatar_tamanho_precisao_dupla(tamanho_total));
                    banco_tamanho.adicionar(e_item);

                }


                zetta.fechar();

                fmt.print(">> AUTO ANALISE - CONCLUÍDA");

            }
        }


    }


    public static void DEBUG(String arquivo) {

        fmt.print("");
        fmt.print("\t >> TelegramTozterum::debug -->> Arquivo = {}", arquivo);
        fmt.print("");

        ZQC.COLECOES_ORGANIZAR(arquivo, "BOMDIAS(LL).LINHA_DO_TEMPO");
        // AZZ.REMOVER_SE_NAO_TIVER_CAMPO(arquivo, "BOMDIAS(LL).LINHA_DO_TEMPO","Tozte");


        // AZZ.COLECOES_EXIBIR(arquivo);

        ZQC.EXIBIR_COLECAO(arquivo, "INFOS(LL)");
        ZQC.EXIBIR_COLECAO(arquivo, "STRAVA(LL)");
        ZQC.EXIBIR_COLECAO(arquivo, "BOMDIAS(LL)");
        // AZZ.EXIBIR_COLECAO(arquivo,"BOMDIAS(LL).LINHA_DO_TEMPO");


        fmt.print("----------------------------------------");

        ZQC.EXIBIR_COLECAO(arquivo, "INFOS(GG)");
        ZQC.EXIBIR_COLECAO(arquivo, "CNU(GG)");


        fmt.print("----------------------------------------");


        ZQC.EXIBIR_COLECAO(arquivo, "CRONOLOGICAMENTE");

        //  AQZUTF8.AUTO_ANALISAR(arquivo);
        //  AQZUTF8.EXIBIR_AUTO_ANALISE(arquivo);

        //  AQZUTF8.EXIBIR_ESTRUTURA(arquivo);

        ZQC.EXIBIR_COLECAO(arquivo, "CNU(GG)");

        //  AQZUTF8.EXIBIR_COLECAO_REORGANIZAR(arquivo, "INFOS(LL)");

        fmt.print("----------------------------------------");
        ZQC.EXIBIR_TUDO(arquivo);

    }


    public static void CADA500(RefInt contador, int hora) {

        contador.somar(1);
        if (contador.get() >= 500) {
            contador.set(0);

            fmt.print(">> ATUALIZAR TEMPO INMET");

            BotCondicoesClimaticas.ACOMPANHAR_TEMPO();
            BotCondicoesClimaticas.ACOMPANHAR_ALERTAS_CLIMATICOS();

        }


    }


}
