package apps.app_tozterum;


import algoritmos.AtividadesLL;
import apps.app_tozterum.stravamentos.StravaAnaliticamente;
import apps.app_tozterum.stravamentos.StravaQ7;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.PastaFS;
import libs.imagem.Imagem;
import libs.luan.*;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tempo.Horario;
import libs.tronarko.Megarko;
import libs.tronarko.eventos.Evento;
import libs.tronarko.eventos.Eventum;
import libs.tronarko.Hazde;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;
import libs.zetta.ItemColecionavel;
import libs.zetta.ZettaQuorum;
import libs.zetta.features.ZQC;
import servicos.ASSETS;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class BotLuan {

    public static void LUAN_BOM_DIA_UNICO(boolean esta_horario_correto) {


        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "INFOS(LL)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        Lista<Entidade> ll_infos = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        boolean is_disponivel = true;
        String Tozte_HOJE = Tronarko.getTozte().getTextoZerado();

        for (Entidade e : ll_infos) {
            if (e.is("Tozte", Tozte_HOJE) && e.is("Status", "BOMDIA")) {
                is_disponivel = false;
                break;
            }
        }


        String COLECAO_TOZTERUM_LUAN_BOMDIAS = "BOMDIAS(LL)";
        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, COLECAO_TOZTERUM_LUAN_BOMDIAS);


        if (is_disponivel) {

            Entidade novo_item = ZQC.NOVA_ENTIDADE();
            novo_item.at("Tozte", Tozte_HOJE);
            novo_item.at("Hazde", Tronarko.getHazde().getTextoZerado());
            novo_item.at("Status", "BOMDIA");

            if (!esta_horario_correto) {
                novo_item.at("SubStatus", "ACOMPANHAMENTO");
            }

            ZQC.INSERIR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN, novo_item);


            Lista<String> bom_dias = BomDia.GET_BOM_DIAS();


            Lista<Entidade> ll_bomdias = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, COLECAO_TOZTERUM_LUAN_BOMDIAS);
            Lista<String> bom_dia_retirar = ENTT.LISTAR_VALORES(ll_bomdias, "Texto");

            int bom_dia_maior_id = 0;

            for (Entidade retirar : ll_bomdias) {
                if (retirar.atIntOuPadrao("BID", 0) > bom_dia_maior_id) {
                    bom_dia_maior_id = retirar.atIntOuPadrao("BID", 0);
                }
            }

            for (String retirar : bom_dia_retirar) {
                bom_dias.remover(Strings.IGUALDADE(), retirar);
            }

            String bom_dia = Aleatorio.escolha_um(bom_dias);

            while (ll_bomdias.getQuantidade() > 3) {

                for (Entidade e : ll_bomdias) {
                    e.at("BID", e.atIntOuPadrao("BID", 0));
                }

                ENTT.ORDENAR_INTEIRO(ll_bomdias, "BID");
                ll_bomdias.removerIndex(0);
            }

            Entidade obj_bom_dia = ZQC.NOVA_ENTIDADE();
            obj_bom_dia.at("Texto", bom_dia);
            obj_bom_dia.at("DDC", Tronarko.getTronAgora().getTextoZerado());
            obj_bom_dia.at("BID", bom_dia_maior_id + 1);

            ll_bomdias.adicionar(obj_bom_dia);

            ZQC.LIMPAR_TUDO(ARQUIVO_GERAL_TOZTERUM, COLECAO_TOZTERUM_LUAN_BOMDIAS);

            for (Entidade item : ll_bomdias) {
                ZQC.INSERIR(ARQUIVO_GERAL_TOZTERUM, COLECAO_TOZTERUM_LUAN_BOMDIAS, item);
            }

            ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, "BOMDIAS(LL).LINHA_DO_TEMPO");

            // BOM DIA - LINHA DO TEMPO
            Entidade bom_dia_linha_do_tempo = ZQC.NOVA_ENTIDADE();
            bom_dia_linha_do_tempo.at("Tozte", Tozte_HOJE);
            bom_dia_linha_do_tempo.at("Hazde", Tronarko.getHazde().getTextoZerado());
            bom_dia_linha_do_tempo.at("BomDia", bom_dia);

            ZQC.INSERIR(ARQUIVO_GERAL_TOZTERUM, "BOMDIAS(LL).LINHA_DO_TEMPO", bom_dia_linha_do_tempo);


            Tozte HOJE = Tronarko.getTozte();


            TextoDocumento frase_bom_dia = new TextoDocumento();

            frase_bom_dia.adicionarLinha("\uD83D\uDD39 " + bom_dia + " : " + HOJE.getTextoZerado() + " - " + HOJE.getSuperarko_Status().toString() + " de " + HOJE.getHiperarko_Status().toString());


            Opcional<String> preguica = LUAN_GET_PREGUICA();

            if (preguica.isOK()) {
                frase_bom_dia.adicionarLinha(preguica.get());
            } else {

                Opcional<String> elogio = LUAN_GET_ELOGIO();

                if (elogio.isOK()) {
                    frase_bom_dia.adicionarLinha(elogio.get());
                }

            }


            Opcional<String> previsao_do_tempo = BotCondicoesClimaticas.OBTER_TEXTO_PREVISAO_DO_TEMPO_EM_BRASILIA_POR_TOZTE(Tronarko.getTozte());

            if (previsao_do_tempo.isOK()) {
                frase_bom_dia.adicionarLinha(previsao_do_tempo.get());
            }


            Opcional<String> op_alertas = BotCondicoesClimaticas.OBTER_TEXTO_ALERTAS_EM_TOZTE(Tozte_HOJE);

            if (op_alertas.isOK()) {
                frase_bom_dia.adicionarLinha(op_alertas.get());
            }


            Lista<Entidade> strava_luan_corrida = StravaQ7.GET_LUAN_CORRIDA_TRONARKO();
            Lista<Entidade> strava_luan_academia = StravaQ7.GET_LUAN_ACADEMIA_TRONARKO();

            // TREINOS
            int c_treinos = StravaAnaliticamente.TRONARKO_GET_TREINOS(strava_luan_academia, HOJE.getTronarko(), HOJE.getHiperarko());
            if (c_treinos > 0) {
                frase_bom_dia.adicionarLinha("\uD83D\uDCAA " + " Academia " + HOJE.getHiperarko_Status().toString() + " : " + c_treinos + " " + Portugues.singular_ou_plural(c_treinos, "treino", "treinos") + " !");
            }

            // CORRIDA
            double d_distancia_hiper = StravaAnaliticamente.TRONARKO_GET_DISTANCIA(strava_luan_corrida, HOJE.getTronarko(), HOJE.getHiperarko());

            if (d_distancia_hiper > 1) {
                String s_distancia_hiper = fmt.doubleNumC2(d_distancia_hiper) + " Km";
                frase_bom_dia.adicionarLinha("\uD83D\uDD25 " + " Corrida " + HOJE.getHiperarko_Status().toString() + " : " + s_distancia_hiper);

            }


            int mega_hoje = HOJE.getMegarko();

            for (Megarko mega : Tronarko.GET_MEGARKOS()) {
                if (mega.getValor() <= mega_hoje) {
                    double d_distancia_mega = StravaAnaliticamente.TRONARKO_MEGA_GET_DISTANCIA(strava_luan_corrida, HOJE.getTronarko(), HOJE.getHiperarko(), mega.getValor());

                    if (d_distancia_mega > 1) {
                        frase_bom_dia.adicionarLinha("\uD83D\uDD25 " + " Corrida M" + mega.getValor() + " : " + fmt.doubleNumC2(d_distancia_mega) + " Km");
                    }

                }
            }


            for (Megarko mega : Tronarko.GET_MEGARKOS()) {
                if (mega.getValor() <= mega_hoje) {
                    int m_treinos = StravaAnaliticamente.TRONARKO_MEGA_GET_TREINOS(strava_luan_academia, HOJE.getTronarko(), HOJE.getHiperarko(), mega.getValor());

                    if (m_treinos > 0) {
                        frase_bom_dia.adicionarLinha("\uD83D\uDCAA " + " Academia M" + mega.getValor() + " : " + m_treinos + " " + Portugues.singular_ou_plural(m_treinos, "treino", "treinos"));
                    }

                }
            }



            // Imagem.IMAGEM_TO_BYTES(OBTER_IMAGEM_BOM_DIA_LUAN(HOJE))).caption(frase_bom_dia.toDocumento());


            Opcional<String> mensagem_eventos = BotLuan.CRIAR_MENSAGENS_DOS_EVENTOS(HOJE);

            if (mensagem_eventos.isOK()) {

               //TronarkoBot.CRIAR_HIPERARKO())).caption(mensagem_eventos.get());

            }


        }

    }

    public static void LUAN_RETROSPECTIVA() {

        Tozte tozte_corrente = Tronarko.getTozte();

        if (tozte_corrente.getSuperarko() == 1) {
            Hazde hazde_corrente = Tronarko.getHazde();
            if (hazde_corrente.getArco() == 8) {


                String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
                String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "INFOS(LL)";

                ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

                Lista<Entidade> ll_infos = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

                boolean is_disponivel = true;
                String Tozte_HOJE = Tronarko.getTozte().getTextoZerado();

                for (Entidade e : ll_infos) {
                    if (e.is("Tozte", Tozte_HOJE) && e.is("Status", "RETROSPECTIVA")) {
                        is_disponivel = false;
                        break;
                    }
                }


                //  if (luan_finalizando.isDisponivel()) {
                if (is_disponivel) {


                    Entidade novo_item = ZQC.NOVA_ENTIDADE();
                    novo_item.at("Tozte", Tozte_HOJE);
                    novo_item.at("Hazde", Tronarko.getHazde().getTextoZerado());
                    novo_item.at("Status", "RETROSPECTIVA");

                    ZQC.INSERIR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN, novo_item);



                    ArrayList<BufferedImage> imagens = new ArrayList<BufferedImage>();

                    int hiperarko = 1;
                    while (hiperarko < tozte_corrente.getHiperarko()) {

                        BufferedImage imagem = BomDia.RETROSPECTIVA(new Tozte(50, hiperarko, tozte_corrente.getTronarko()));
                        imagens.add(imagem);


                        hiperarko += 1;
                    }

                    if (!imagens.isEmpty()) {



                    }


                    //luan_finalizando.marcar();
                    //luan_finalizando.salvar();
                }

            }
        }

    }

    public static Opcional<String> LUAN_GET_PREGUICA() {

        Opcional<String> preguica = new Opcional<String>();

        Tozte HOJE = Tronarko.getTozte();
        Lista<Entidade> strava_objetos_academia = StravaQ7.GET_LUAN_ACADEMIA_TRONARKO();
        Lista<Entidade> strava_objetos_corrida = StravaQ7.GET_LUAN_CORRIDA_TRONARKO();


        fmt.print("Hoje :: {}", HOJE.getTextoZerado());

        Lista<Entidade> todos = ENTT.CRIAR_LISTA();

        ENTT.ADICIONAR_VARIOS(todos, strava_objetos_academia);
        ENTT.ADICIONAR_VARIOS(todos, strava_objetos_corrida);

        Opcional<Tozte> tozte_recente = new Opcional<>();

        for (Entidade item : todos) {
            Tozte tozte_corrente = StringTronarko.PARSER_TOZTE(item.at("Tozte"));

            if (tozte_recente.isOK()) {
                if (tozte_corrente.isMaiorQue(tozte_recente.get())) {
                    tozte_recente.set(tozte_corrente);
                }
            } else {
                tozte_recente.set(tozte_corrente);
            }

        }

        if (tozte_recente.isOK()) {
            fmt.print("Recente :: {}", tozte_recente.get().getTextoZerado());

            if (tozte_recente.get().isMenorQue(HOJE)) {

                long diff = Tronarko.TOZTE_DIFERENCA(HOJE, tozte_recente.get());
                fmt.print("Recente :: {}", diff);


                if (diff >= 3 && diff < 5) {
                    preguica.set("A preguiça tá começando ein...");
                } else if (diff >= 5 && diff < 7) {
                    preguica.set("A preguiça tá evoluindo demais...");
                } else if (diff >= 7 && diff < 12) {
                    preguica.set("Sua preguiça está d+, tome uma atitude !");
                } else if (diff >= 12) {
                    preguica.set("Eita preguiça do caralho, toma vergonha na cara porra !");
                }

            }

        }

        return preguica;
    }

    public static Opcional<String> LUAN_GET_ELOGIO() {

        Opcional<String> elogio = new Opcional<String>();

        Tozte HOJE = Tronarko.getTozte();
        Lista<Entidade> strava_objetos_academia = StravaQ7.GET_LUAN_ACADEMIA_TRONARKO();


        fmt.print("Hoje :: {}", HOJE.getTextoZerado());

        Lista<Entidade> todos = ENTT.CRIAR_LISTA();

        ENTT.ADICIONAR_VARIOS(todos, strava_objetos_academia);

        Lista<Entidade> megarko_treinos = new Lista<Entidade>();

        for (Entidade item : todos) {
            Tozte tozte_corrente = StringTronarko.PARSER_TOZTE(item.at("Tozte"));

            if (tozte_corrente.getTronarko() == HOJE.getTronarko() && tozte_corrente.getHiperarko() == HOJE.getHiperarko() && tozte_corrente.getMegarko() == HOJE.getMegarko()) {
                megarko_treinos.adicionar(item);
            }

        }

        if (megarko_treinos.temItens()) {

            if (megarko_treinos.getQuantidade() == 5) {
                elogio.set("Nossa, vc está se esforçando esse megarko !");
            } else if (megarko_treinos.getQuantidade() == 6) {
                elogio.set("Nossa, vc está de parabéns esse megarko !");
            } else if (megarko_treinos.getQuantidade() == 7 || megarko_treinos.getQuantidade() == 8) {
                elogio.set("Caramba, vc está se superou esse megarko !");
            } else if (megarko_treinos.getQuantidade() == 9) {
                elogio.set("Caramba, vc está se superou esse megarko, exclente trabalho !");
            } else if (megarko_treinos.getQuantidade() == 10) {
                elogio.set("Caralho Luan, tú é pika das galáxias, excelente desempenho esse megarko !");
            }

        }

        return elogio;
    }


    public static void LUAN_ACOMPANHAR_NO_DIA() {


        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "INFOS(LL)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        Lista<Entidade> ll_infos = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        fmt.print("INFOS(LL)");
        ENTT.EXIBIR_TABELA_COM_NOME(ll_infos, "LUAN - @BOMDIA ACOMPANHAMENTO - STATUS");

        boolean is_disponivel = true;
        String Tozte_HOJE = Tronarko.getTozte().getTextoZerado();

        for (Entidade e : ll_infos) {
            if (e.is("Tozte", Tozte_HOJE) && (e.is("Status", "BOMDIA") || e.is("Status", "ACOMPANHAMENTO"))) {
                is_disponivel = false;
                break;
            }
        }


        if (is_disponivel) {

            Entidade novo_item = ZQC.NOVA_ENTIDADE();
            novo_item.at("Tozte", Tozte_HOJE);
            novo_item.at("Hazde", Tronarko.getHazde().getTextoZerado());
            novo_item.at("Status", "ACOMPANHAMENTO");

            ZQC.INSERIR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN, novo_item);


            ACOMPANHAR_STRAVA_INTERNAMENTE();

            Horario horario = Calendario.getHorario();

            if (horario.getHora() >= 6 && horario.getHora() <= 10) {
                BotLuan.LUAN_BOM_DIA_UNICO(false);
            }

        } else {


            fmt.print(">> ATUALIZAR ESPORTES !");

            long atividades_antes = STRAVA_QUANTIDADE_DE_ATIVIDADES_ESPOPTIVAS();

            ACOMPANHAR_STRAVA_INTERNAMENTE();

            long atividades_depois = STRAVA_QUANTIDADE_DE_ATIVIDADES_ESPOPTIVAS();

            if (atividades_depois > atividades_antes) {
                ATUALIZAR_MENSAGEM_ESPORTIVAS();
            }

            fmt.print(">> Esportes = {} :: {}", atividades_antes, atividades_depois);


        }


    }

    public static Opcional<String> CRIAR_MENSAGENS_DOS_EVENTOS(Tozte HOJE) {

        Eventum eventum = new Eventum();

        int quantos = 0;

        TextoDocumento mensagem_eventos = new TextoDocumento();

        String EMOJI_EVENTO = "✴\uFE0F";

        for (Evento ev : eventum.getProximosEventos(HOJE.getTronarko())) {
            fmt.print("{}", ev.getTozte().getTextoZerado());

            if (HOJE.isMenorIgualQue(ev.getTozte())) {
                long tt = ev.getTozte().getSuperarkosTotal() - HOJE.getSuperarkosTotal();
                fmt.print("{}", tt);

                if (tt <= 5) {

                    if (tt == 0) {
                        if (ev.isGrande()) {
                            String msg = EMOJI_EVENTO + ev.getNome() + " (" + ev.getTozte().getTextoZerado() + " ate " + ev.getGrande().getFim().getTextoZerado() + " )! ";
                            mensagem_eventos.adicionarLinha(msg);
                        } else {
                            String msg = EMOJI_EVENTO + ev.getNome() + " (" + ev.getTozte().getTextoZerado() + " )! ";
                            mensagem_eventos.adicionarLinha(msg);
                        }
                    } else {
                        if (ev.isGrande()) {
                            String msg = EMOJI_EVENTO + " +" + tt + " " + ev.getNome() + "( " + ev.getTozte().getTextoZerado() + " até " + ev.getGrande().getFim().getTextoZerado() + " ) ";
                            mensagem_eventos.adicionarLinha(msg);
                        } else {
                            String msg = EMOJI_EVENTO + " +" + tt + " " + ev.getNome() + "( " + ev.getTozte().getTextoZerado() + " ) ";
                            mensagem_eventos.adicionarLinha(msg);
                        }
                    }

                    quantos += 1;
                }

            }

            if (ev.isGrande()) {
                if (HOJE.isMaiorQue(ev.getGrande().getInicio()) && HOJE.isMenorQue(ev.getGrande().getFim())) {
                    String msg = EMOJI_EVENTO + " aproveite : " + ev.getNome() + " ( " + ev.getGrande().getInicio().getTextoZerado() + " até " + ev.getGrande().getFim().getTextoZerado() + " ) ! ";
                    mensagem_eventos.adicionarLinha(msg);
                    quantos += 1;
                }
            }


        }


        if (quantos > 0) {
            return Opcional.OK(mensagem_eventos.toDocumento());
        }

        return Opcional.CANCEL();
    }

    public static void ATUALIZAR_MENSAGEM_ESPORTIVAS() {

        Tozte HOJE = Tronarko.getTozte();
        String bom_dia = "Atualizando dados ";

        String FRASE_BOM_DIA = "\uD83D\uDD39 " + bom_dia + " : " + HOJE.getTextoZerado() + " - " + HOJE.getSuperarko_Status().toString() + " de " + HOJE.getHiperarko_Status().toString();



        byte[] imagem_bom_dia_bytes = Imagem.IMAGEM_TO_BYTES(OBTER_IMAGEM_BOM_DIA_LUAN(Tronarko.getTozte()));


    }


    public static void CNU_INFORMAR() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "CNU(LL)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        Lista<Entidade> ll_infos = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        boolean vamos_enviar = true;
        String Tozte_HOJE = Tronarko.getTozte().getTextoZerado();

        for (Entidade e : ll_infos) {
            if (e.is("Tozte", Tozte_HOJE) && e.is("Status", "ENVIADO")) {
                vamos_enviar = false;
                break;
            }
        }

        if (vamos_enviar) {

            String nesse_dia = Calendario.getDataHoje().getTempoLegivel();

            int mensagens = 0;

            for (Entidade evento : CNU.GET_EVENTOS_HOJE(nesse_dia)) {

                BufferedImage imagem = CNU.CRIAR_IMAGEM(nesse_dia, evento);


               //Imagem.IMAGEM_TO_BYTES(imagem)).caption("#CNU - Concurso Público Nacional Unificado Informa");

                mensagens += 1;
            }

            Entidade novo_item = ZQC.NOVA_ENTIDADE();
            novo_item.at("Tozte", Tozte_HOJE);
            novo_item.at("Hazde", Tronarko.getHazde().getTexto());
            novo_item.at("Mensagens", mensagens);
            novo_item.at("Status", "ENVIADO");

            ZQC.INSERIR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN, novo_item);

        }

    }


    public static void ATZUM_VIAGEM(int hora, Lista<Integer> horarios_possiveis) {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "ATZUM_VIAGEM(LL)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        Lista<Entidade> ll_infos = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        boolean is_disponivel = true;
        boolean is_agendado = false;
        int aguardando_essa_hora = 0;

        String Tozte_HOJE = Tronarko.getTozte().getTextoZerado();

        for (Entidade e : ll_infos) {
            if (e.is("Tozte", Tozte_HOJE) && e.is("Status", "OK")) {
                is_disponivel = false;
                break;
            } else if (e.is("Tozte", Tozte_HOJE) && e.is("Status", "AGENDADO")) {
                is_disponivel = false;
                is_agendado = true;
                aguardando_essa_hora = e.atIntOuPadrao("Quando", 0);
                break;
            }
        }

        fmt.print("Viagem -->> Disponível = {} : Agendado = {} Marcado = {}", Portugues.sim(is_disponivel), Portugues.sim(is_agendado), aguardando_essa_hora);


        if (is_disponivel && !is_agendado) {

            Entidade novo_item = ZQC.NOVA_ENTIDADE();
            novo_item.at("Tozte", Tozte_HOJE);
            novo_item.at("Hazde", Tronarko.getHazde().getTextoZerado());

            Integer escolhido = Aleatorio.escolha_um(horarios_possiveis);

            if (escolhido <= hora) {

                novo_item.at("Status", "OK");
                novo_item.at("TDS", Tronarko.getTronAgora().getTextoZerado());

             //   BotLuanConquistador.SINCRONIZAR_ATIVIDADES();
             //   BotLuanConquistador.INIT();

            } else {
                novo_item.at("Status", "AGENDADO");
                novo_item.at("TDA", Tronarko.getTronAgora().getTextoZerado());
                novo_item.at("Quando", escolhido);
            }

            ZQC.INSERIR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN, novo_item);
            is_agendado = false;

        }

        if (is_agendado && hora == aguardando_essa_hora) {

            ZettaQuorum aqz = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);

            for (ItemColecionavel item : aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN).getItensEditaveis()) {
                Entidade e_item = item.get();
                if (e_item.is("Tozte", Tozte_HOJE) && e_item.is("Status", "AGENDADO")) {
                    e_item.at("Status", "OK");
                    e_item.at("TDS", Tronarko.getTronAgora().getTextoZerado());
                    item.atualizar();
                    break;
                }
            }

            aqz.fechar();

           // BotLuanConquistador.SINCRONIZAR_ATIVIDADES();
          //  BotLuanConquistador.INIT();

        }


        if (is_agendado && hora >= aguardando_essa_hora) {

            ZettaQuorum aqz = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);

            for (ItemColecionavel item : aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN).getItensEditaveis()) {
                Entidade e_item = item.get();
                if (e_item.is("Tozte", Tozte_HOJE) && e_item.is("Status", "AGENDADO")) {
                    e_item.at("Status", "OK");
                    e_item.at("Arrombado", "SIM");
                    e_item.at("TDS", Tronarko.getTronAgora().getTextoZerado());
                    item.atualizar();
                    break;
                }
            }

            aqz.fechar();

          //  BotLuanConquistador.SINCRONIZAR_ATIVIDADES();
         //   BotLuanConquistador.INIT();

        }

    }


    public static void VER_CORRIDAS() {

        String arquivo_dados_corrida = ASSETS.GET_PASTA("coisas\\strava").getArquivo("luan.entts");
        Lista<Entidade> luan_correndo = ENTT.ABRIR(arquivo_dados_corrida);

        Lista<Entidade> corridas_ate_hoje = new Lista<Entidade>();
        Tozte tozte_referencia = new Tozte(10, 5, 7004);

        for (Entidade corrida : luan_correndo) {

            corrida.at("Tozte", Tronarko.getData(Data.toData(corrida.at("Data")).getTempoLegivel()).getTextoZerado());

            if (corrida.is("Tipo", "RUN") && !corrida.is("Viajou", "SIM")) {
                if (StringTronarko.PARSER_TOZTE(corrida.at("Tozte")).isMaiorIgualQue(tozte_referencia)) {
                    corridas_ate_hoje.adicionar(corrida);
                }
            }
        }

        ENTT.EXIBIR_TABELA(luan_correndo);

        fmt.print(">>> Correndo :: VIAGEM V2");
        ENTT.EXIBIR_TABELA(corridas_ate_hoje);

    }

    public static long STRAVA_QUANTIDADE_DE_ATIVIDADES_ESPOPTIVAS() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";

        ZettaQuorum aqz = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);
        aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN);

        long quantidade = aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).contagem();

        aqz.fechar();

        return quantidade;
    }

    public static void ACOMPANHAR_STRAVA_INTERNAMENTE() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";

        ZettaQuorum aqz = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);
        aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN);


        ENTT.EXIBIR_TABELA_COM_NOME(aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).getItens(), "ANTES");

        StravaQ7.ACOMPANHAR_COLECAO("118956021", aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN));


        for (ItemColecionavel item : aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).getItensEditaveis()) {

            if (item.get().at("ID").isEmpty()) {
                item.get().at("ID", item.get().at("@ID"));
                item.atualizar();
            }

        }


        Lista<Entidade> dados_depois = aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).getItens();

        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(dados_depois, "ID");
        ENTT.ORDENAR_TEXTO(dados_depois, "Data");


        ENTT.EXIBIR_TABELA_COM_NOME(dados_depois, "DEPOIS");

        aqz.fechar();

    }

    public static void MIGRAR_STRAVA() {

        PastaFS guardar_em = ASSETS.GET_PASTA("coisas\\strava");

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";


        Lista<Entidade> strava_luan = ENTT.ABRIR(guardar_em.getArquivo("luan.entts"));


        ZettaQuorum aqz = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);

        aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN);
        aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).zerar();

        for (Entidade strava : strava_luan) {
            aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).adicionar(strava);
        }

        Lista<Entidade> banco_dados = aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).getItens();

        aqz.fechar();


        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(strava_luan, "ID");
        ENTT.ATRIBUTO_TORNAR_PRIMEIRO(banco_dados, "ID");

        ENTT.EXIBIR_TABELA_COM_NOME(strava_luan, "STRAVA");
        ENTT.EXIBIR_TABELA_COM_NOME(banco_dados, "AZZ");

    }


    public static void ENVIAR_OI(String bom_dia, String Tozte_HOJE) {

        Tozte HOJE = Tronarko.getTozte();

        String FRASE_BOM_DIA = "\uD83D\uDD39 " + bom_dia + " : " + HOJE.getTextoZerado() + " - " + HOJE.getSuperarko_Status().toString() + " de " + HOJE.getHiperarko_Status().toString();


        FRASE_BOM_DIA += "\n";

        Opcional<String> preguica = LUAN_GET_PREGUICA();

        if (preguica.isOK()) {
            FRASE_BOM_DIA += "\n";
            FRASE_BOM_DIA += " " + preguica.get();
            FRASE_BOM_DIA += "\n";
        } else {

            Opcional<String> elogio = LUAN_GET_ELOGIO();

            if (elogio.isOK()) {
                FRASE_BOM_DIA += "\n";
                FRASE_BOM_DIA += " " + elogio.get();
                FRASE_BOM_DIA += "\n";
            }

        }


        Opcional<String> previsao_do_tempo = BotCondicoesClimaticas.OBTER_TEXTO_PREVISAO_DO_TEMPO_EM_BRASILIA_POR_TOZTE(Tronarko.getTozte());

        if (previsao_do_tempo.isOK()) {
            FRASE_BOM_DIA += previsao_do_tempo.get();
        }

        FRASE_BOM_DIA += "\n";


        Opcional<String> op_alertas = BotCondicoesClimaticas.OBTER_TEXTO_ALERTAS_EM_TOZTE(Tozte_HOJE);

        if (op_alertas.isOK()) {
            FRASE_BOM_DIA += op_alertas.get();
        }

        FRASE_BOM_DIA += "\n";


        Lista<Entidade> strava_luan_corrida = StravaQ7.GET_LUAN_CORRIDA_TRONARKO();
        Lista<Entidade> strava_luan_academia = StravaQ7.GET_LUAN_ACADEMIA_TRONARKO();

        // TREINOS
        int c_treinos = StravaAnaliticamente.TRONARKO_GET_TREINOS(strava_luan_academia, HOJE.getTronarko(), HOJE.getHiperarko());
        if (c_treinos > 0) {
            FRASE_BOM_DIA += "\n \uD83D\uDCAA " + " Academia " + HOJE.getHiperarko_Status().toString() + " : " + c_treinos + " " + Portugues.singular_ou_plural(c_treinos, "treino", "treinos") + " !";
        }

        // CORRIDA
        double d_distancia_hiper = StravaAnaliticamente.TRONARKO_GET_DISTANCIA(strava_luan_corrida, HOJE.getTronarko(), HOJE.getHiperarko());

        if (d_distancia_hiper > 1) {
            String s_distancia_hiper = fmt.doubleNumC2(d_distancia_hiper) + " Km";
            FRASE_BOM_DIA += "\n \uD83D\uDD25 " + " Corrida " + HOJE.getHiperarko_Status().toString() + " : " + s_distancia_hiper;
        }

        FRASE_BOM_DIA += "\n";

        int mega_hoje = HOJE.getMegarko();

        for (Megarko mega : Tronarko.GET_MEGARKOS()) {
            if (mega.getValor() <= mega_hoje) {
                double d_distancia_mega = StravaAnaliticamente.TRONARKO_MEGA_GET_DISTANCIA(strava_luan_corrida, HOJE.getTronarko(), HOJE.getHiperarko(), mega.getValor());

                if (d_distancia_mega > 1) {
                    String s_distancia_mega = fmt.doubleNumC2(d_distancia_mega) + " Km";
                    FRASE_BOM_DIA += "\n \uD83D\uDD25 " + " Corrida M" + mega.getValor() + " : " + s_distancia_mega;
                }

            }
        }


        for (Megarko mega : Tronarko.GET_MEGARKOS()) {
            if (mega.getValor() <= mega_hoje) {
                int m_treinos = StravaAnaliticamente.TRONARKO_MEGA_GET_TREINOS(strava_luan_academia, HOJE.getTronarko(), HOJE.getHiperarko(), mega.getValor());

                if (m_treinos > 0) {
                    FRASE_BOM_DIA += "\n \uD83D\uDCAA " + " Academia M" + mega.getValor() + " : " + m_treinos + " " + Portugues.singular_ou_plural(m_treinos, "treino", "treinos");
                }

            }
        }

        FRASE_BOM_DIA += "\n";


        Opcional<String> mensagem_eventos = CRIAR_MENSAGENS_DOS_EVENTOS(HOJE);

        OBTER_IMAGEM_BOM_DIA_LUAN(HOJE);

    }


    public static BufferedImage OBTER_IMAGEM_BOM_DIA_LUAN(Tozte tozte_marcado) {

        Lista<Entidade> previsao_do_tempo_brasilia_hoje = BotCondicoesClimaticas.GET_DADOS_BRASILIA_TEMPO_ACOMPANHAR_TEMPO(tozte_marcado);
        Lista<Entidade> previsao_do_tempo_sas_brasilia_hoje = BotCondicoesClimaticas.GET_SAS_BRASILIA(tozte_marcado);

        return BomDia.criarLuan(previsao_do_tempo_brasilia_hoje, previsao_do_tempo_sas_brasilia_hoje);
    }


    public static void LUAN_QUERO_OI_AGORA() {


        String Tozte_HOJE = Tronarko.getTozte().getTextoZerado();

        Lista<String> bom_dias = BomDia.GET_BOM_DIAS();

        String bom_dia = Aleatorio.escolha_um(bom_dias);


        Tozte HOJE = Tronarko.getTozte();
        Hazde AGORA = Tronarko.getHazde();


        TextoDocumento frase_bom_dia = new TextoDocumento();

        if (AGORA.getArco() < 5) {
            frase_bom_dia.adicionarLinha("\uD83D\uDD39 " + bom_dia + " : " + HOJE.getTextoZerado() + " - " + HOJE.getSuperarko_Status().toString() + " de " + HOJE.getHiperarko_Status().toString());
        } else {
            frase_bom_dia.adicionarLinha("\uD83D\uDD39 " + "E ae Luan," + " : " + HOJE.getTextoZerado() + " - " + HOJE.getSuperarko_Status().toString() + " de " + HOJE.getHiperarko_Status().toString());
        }


        Opcional<String> preguica = LUAN_GET_PREGUICA();

        if (preguica.isOK()) {
            frase_bom_dia.adicionarLinha(preguica.get());
        } else {

            Opcional<String> elogio = LUAN_GET_ELOGIO();

            if (elogio.isOK()) {
                frase_bom_dia.adicionarLinha(elogio.get());
            }

        }


        Opcional<String> previsao_do_tempo = BotCondicoesClimaticas.OBTER_TEXTO_PREVISAO_DO_TEMPO_EM_BRASILIA_POR_TOZTE(Tronarko.getTozte());

        if (previsao_do_tempo.isOK()) {
            frase_bom_dia.adicionarLinha(previsao_do_tempo.get());
            frase_bom_dia.adicionarLinha();
        }


        Opcional<String> op_alertas = BotCondicoesClimaticas.OBTER_TEXTO_ALERTAS_EM_TOZTE(Tozte_HOJE);

        if (op_alertas.isOK()) {
            frase_bom_dia.adicionarLinha(op_alertas.get());
            frase_bom_dia.adicionarLinha();
        }


        Lista<Entidade> strava_luan_corrida = StravaQ7.GET_LUAN_CORRIDA_TRONARKO();
        Lista<Entidade> strava_luan_academia = StravaQ7.GET_LUAN_ACADEMIA_TRONARKO();

        // TREINOS
        int c_treinos = StravaAnaliticamente.TRONARKO_GET_TREINOS(strava_luan_academia, HOJE.getTronarko(), HOJE.getHiperarko());
        if (c_treinos > 0) {
            frase_bom_dia.adicionarLinha("\uD83D\uDCAA " + " Academia " + HOJE.getHiperarko_Status().toString() + " : " + c_treinos + " " + Portugues.singular_ou_plural(c_treinos, "treino", "treinos") + " !");
        }

        frase_bom_dia.adicionarLinha();

        // CORRIDA
        double d_distancia_hiper = StravaAnaliticamente.TRONARKO_GET_DISTANCIA(strava_luan_corrida, HOJE.getTronarko(), HOJE.getHiperarko());

        if (d_distancia_hiper > 1) {
            String s_distancia_hiper = fmt.doubleNumC2(d_distancia_hiper) + " Km";
            frase_bom_dia.adicionarLinha("\uD83D\uDD25 " + " Corrida " + HOJE.getHiperarko_Status().toString() + " : " + s_distancia_hiper);

        }


        int mega_hoje = HOJE.getMegarko();

        for (Megarko mega : Tronarko.GET_MEGARKOS()) {
            if (mega.getValor() <= mega_hoje) {
                double d_distancia_mega = StravaAnaliticamente.TRONARKO_MEGA_GET_DISTANCIA(strava_luan_corrida, HOJE.getTronarko(), HOJE.getHiperarko(), mega.getValor());

                if (d_distancia_mega > 1) {
                    frase_bom_dia.adicionarLinha("\uD83D\uDD25 " + " Corrida M" + mega.getValor() + " : " + fmt.doubleNumC2(d_distancia_mega) + " Km");
                }

            }
        }

        frase_bom_dia.adicionarLinha();


        for (Megarko mega : Tronarko.GET_MEGARKOS()) {
            if (mega.getValor() <= mega_hoje) {
                int m_treinos = StravaAnaliticamente.TRONARKO_MEGA_GET_TREINOS(strava_luan_academia, HOJE.getTronarko(), HOJE.getHiperarko(), mega.getValor());

                if (m_treinos > 0) {
                    frase_bom_dia.adicionarLinha("\uD83D\uDCAA " + " Academia M" + mega.getValor() + " : " + m_treinos + " " + Portugues.singular_ou_plural(m_treinos, "treino", "treinos"));
                }

            }
        }



        Imagem.IMAGEM_TO_BYTES(OBTER_IMAGEM_BOM_DIA_LUAN(HOJE));

    }

    public static void QUERO_TREINOS() {


        Tozte HOJE = Tronarko.getTozte();

        Imagem.IMAGEM_TO_BYTES(OBTER_IMAGEM_BOM_DIA_LUAN(HOJE));

    }

    public static void VER_ATIVIDADES() {
        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";
        ZettaQuorum aqz = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);
        aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).exibir_colecao();
        aqz.fechar();
    }



        public static void REMOVER_ATIVIDADES_SEM_TIPO(){


        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";

        ZettaQuorum aqz = new ZettaQuorum(ARQUIVO_GERAL_TOZTERUM);
        for(ItemColecionavel ic : aqz.getColecaoSempre(ARQUIVO_GERAL_TOZTERUM_STRAVA_LUAN).getItensEditaveis()){
            Entidade item = ic.get();

            if(item.isVazio("Tipo")){
                ic.remover();
            }

        }

        aqz.fechar();
    }

    public static void ACADEMIA(){

        BotLuan.LUAN_ACOMPANHAR_NO_DIA();
        AtividadesLL.ACADEMIA();

        //  BotLuan.REMOVER_ATIVIDADES_SEM_TIPO();
        BotLuan.VER_ATIVIDADES();

        LuanTreinamento lt = new LuanTreinamento();
        lt.treino_rizno_7004();

        AtividadesLL.VER_PONTUACOES();

    }
}
