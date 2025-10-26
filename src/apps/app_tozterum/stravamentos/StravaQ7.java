package apps.app_tozterum.stravamentos;


import apps.app_tozterum.BomDia;
import apps.app_tozterum.TelegramTozterum;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.fs.PastaFS;
import libs.imagem.Imagem;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.tempo.Calendario;
import libs.tempo.Data;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.zetta.ItemColecionavel;
import libs.zetta.ZettaColecao;
import libs.zetta.features.ZQC;
import servicos.ASSETS;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StravaQ7 {


    public static Lista<Entidade> GET_LUAN_CORRIDA_TRONARKO() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);
        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);


        ENTT.EXIBIR_TABELA(dados);

        Lista<Entidade> novos_dados = new Lista<Entidade>();

        for (Entidade e : ENTT.COLETAR(dados, "Tipo", "RUN")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));
            novo.at("Altitude", e.at("Altitude"));
            novo.at("Distancia", e.at("Distancia"));

            novos_dados.adicionar(novo);
        }


        // ENTT.EXIBIR_TABELA(novos_dados);

        //  ENTT.GUARDAR(novos_dados,guardar_em.getArquivo("t_luan.entts"));

        return novos_dados;
    }

    public static Lista<Entidade> GET_LUAN_ACADEMIA_TRONARKO() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);
        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);


        ENTT.EXIBIR_TABELA(dados);

        Lista<Entidade> novos_dados = new Lista<Entidade>();

        for (Entidade e : ENTT.COLETAR(dados, "Tipo", "WORKOUT")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));

            novos_dados.adicionar(novo);
        }


        return novos_dados;
    }

    public static Lista<Entidade> GET_LUAN_AQUATICO_TRONARKO() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);
        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);


        ENTT.EXIBIR_TABELA(dados);

        Lista<Entidade> novos_dados = new Lista<Entidade>();

        for (Entidade e : ENTT.COLETAR_OU_COLETAR(dados, "Tipo", "WATER SPORT", "Tipo", "SWIM")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));
            novo.at("Altitude", e.at("Altitude"));
            novo.at("Distancia", e.at("Distancia"));

            novos_dados.adicionar(novo);
        }


        return novos_dados;
    }

    public static StravaAtividadesClassificadas GET_LUAN_ATIVIDADES_CLASSIFICADAS_TRONARKO() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(LL)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);
        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);


        ENTT.EXIBIR_TABELA(dados);

        StravaAtividadesClassificadas sac = new StravaAtividadesClassificadas();

        for (Entidade e : ENTT.COLETAR(dados, "Tipo", "RUN")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));
            novo.at("Altitude", e.at("Altitude"));
            novo.at("Distancia", e.at("Distancia"));

            sac.adicionar_corrida(novo);
        }

        for (Entidade e : ENTT.COLETAR(dados, "Tipo", "WORKOUT")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));

            sac.adicionar_academia(novo);
        }


        for (Entidade e : ENTT.COLETAR_OU_COLETAR(dados, "Tipo", "WATER SPORT", "Tipo", "SWIM")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));
            novo.at("Altitude", e.at("Altitude"));
            novo.at("Distancia", e.at("Distancia"));

            sac.adicionar_aquatico(novo);
        }


        return sac;
    }


    public static void EXIBIR_LUAN() {

        PastaFS guardar_em = ASSETS.GET_PASTA("coisas\\strava");
        Lista<Entidade> tudo = ENTT.ABRIR(guardar_em.getArquivo("luan.entts"));

        ENTT.EXIBIR_TABELA_COM_NOME(tudo, "LUAN FREITAS");
    }

    public static Lista<Entidade> GET_GG_ACADEMIA_TRONARKO() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(GG)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);
        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        ENTT.EXIBIR_TABELA(dados);

        Lista<Entidade> novos_dados = new Lista<Entidade>();

        for (Entidade e : ENTT.COLETAR(dados, "Tipo", "WORKOUT")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));

            novos_dados.adicionar(novo);
        }


        return novos_dados;
    }

    public static Lista<Entidade> GET_GG_CORRIDA_TRONARKO() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(GG)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);
        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        ENTT.EXIBIR_TABELA(dados);

        Lista<Entidade> novos_dados = new Lista<Entidade>();

        for (Entidade e : ENTT.COLETAR(dados, "Tipo", "RUN")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));
            novo.at("Altitude", e.at("Altitude"));
            novo.at("Distancia", e.at("Distancia"));

            novos_dados.adicionar(novo);
        }


        return novos_dados;
    }

    public static Lista<Entidade> GET_GG_AQUATICO_TRONARKO() {

        String ARQUIVO_GERAL_TOZTERUM = TelegramTozterum.GET_ARQUIVO_TOZTERUM();
        String ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN = "STRAVA_ACOMPANHAMENTO_DADOS(GG)";

        ZQC.COLECOES_ORGANIZAR(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);
        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ARQUIVO_GERAL_TOZTERUM, ARQUIVO_GERAL_TOZTERUM_INFOS_LUAN);

        ENTT.EXIBIR_TABELA(dados);

        Lista<Entidade> novos_dados = new Lista<Entidade>();

        for (Entidade e : ENTT.COLETAR_OU_COLETAR(dados, "Tipo", "WATER SPORT", "Tipo", "SWIM")) {
            Entidade novo = new Entidade();
            novo.at("Tipo", e.at("Tipo"));

            Data data = Data.toData(e.at("Data"));
            novo.at("Tozte", Tronarko.getData(data.getDia(), data.getMes(), data.getAno()).getTextoZerado());

            novo.at("Nome", e.at("Nome"));
            novo.at("Tempo", e.at("Tempo"));
            novo.at("Altitude", e.at("Altitude"));
            novo.at("Distancia", e.at("Distancia"));

            novos_dados.adicionar(novo);
        }


        return novos_dados;
    }


    public static void ACOMPANHAR_COLECAO(String perfil_atleta_id, ZettaColecao colecao_strava) {

        Lista<Entidade> recentes = StravaQ6.PERFIL_GET_DADOS_RECENTE(perfil_atleta_id);


        Lista<Entidade> dados = colecao_strava.getItens();

        ENTT.EXIBIR_TABELA_COM_NOME(dados, "@Existentes");


        Lista<Entidade> atualizacoes = new Lista<Entidade>();
        Lista<Entidade> insercoes = new Lista<Entidade>();


        for (Entidade novo : recentes) {
            String novo_tipo = novo.at("Tipo");
            String novo_data = novo.at("Data");
            String novo_tempo = novo.at("Tempo");
            String novo_nome = novo.at("Nome");

            boolean existe = false;

            for (Entidade existente : dados) {

                String existente_tipo = existente.at("Tipo");
                String existente_data = existente.at("Data");
                String existente_tempo = existente.at("Tempo");
                String existente_nome = existente.at("Nome");

                if (existente_tipo.contentEquals(novo_tipo) && existente_data.contentEquals(novo_data) && existente_tempo.contentEquals(novo_tempo)) {

                    if (!novo_nome.contentEquals(existente_nome)) {
                        existente.at("Editado", "SIM");
                        existente.at("EditadoData", Calendario.getDataHoje().getTempoInverso());
                        existente.at("Edicoes", existente.atIntOuPadrao("Edicoes", 0) + 1);
                        existente.at("EditadoNomeAnteriormente", existente.at("Nome"));
                        existente.at("Nome", novo_nome);

                        atualizacoes.adicionar(existente);
                    }


                    existe = true;
                    break;
                }

            }


            if (!existe) {
                novo.at("Conquistado", Calendario.getTempoCompleto());
                insercoes.adicionar(novo);
            }

        }


        for (Entidade atu : atualizacoes) {

            for (ItemColecionavel item : colecao_strava.getItensEditaveis()) {
                Entidade e_item = item.get();

                if (atu.atInt("@ID") == e_item.atInt("@ID") && !item.get().at("Nome").contentEquals(atu.at("Nome"))) {

                    item.get().at("Editado", "SIM");
                    item.get().at("EditadoData", Calendario.getDataHoje().getTempoInverso());
                    item.get().at("Edicoes", item.get().atIntOuPadrao("Edicoes", 0) + 1);
                    item.get().at("EditadoNomeAnteriormente", item.get().at("Nome"));
                    item.get().at("Nome", atu.at("Nome"));

                    item.atualizar();
                }

            }

        }

        for (Entidade ins : insercoes) {
            colecao_strava.adicionar(ins);
        }

        ENTT.EXIBIR_TABELA_COM_NOME(insercoes, "@Inserindo");
        ENTT.EXIBIR_TABELA_COM_NOME(atualizacoes, "@Atualizando");

        // ENTT.GUARDAR(dados, arquivo);
    }


    public static void init_retrospectiva() {

        PastaFS guardar_em = ASSETS.GET_PASTA("coisas\\strava");

        fmt.print("HOJE : {}", Tronarko.getTozte().getTextoZerado());
        Imagem.exportar(BomDia.RETROSPECTIVA(new Tozte(50, 1, 7004)), guardar_em.getArquivo("R1.png"));
        Imagem.exportar(BomDia.RETROSPECTIVA(new Tozte(50, 2, 7004)), guardar_em.getArquivo("R2.png"));
        Imagem.exportar(BomDia.RETROSPECTIVA(new Tozte(50, 3, 7004)), guardar_em.getArquivo("R3.png"));
        Imagem.exportar(BomDia.RETROSPECTIVA(new Tozte(50, 4, 7004)), guardar_em.getArquivo("R4.png"));


    }


    public static void COM_API() {

        String access_token = "5e3b8fea5db8aa754579dd7f536edd15f05ad9dd";


        String link = "https://www.strava.com/api/v3/athlete/activities";

        try {
            URL url = new URL(link);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json; utf-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", "Bearer " + access_token);
            con.setDoOutput(true);

            String txt = Strings.GET_STRING_VIEW(con.getInputStream().readAllBytes());

            fmt.print("{}", txt);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
