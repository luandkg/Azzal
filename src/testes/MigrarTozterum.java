package testes;

import libs.aqz.AQZTX;
import libs.aqz.AQZUTF8;
import libs.aqz.MigrattorAQZ;
import libs.aqz.extincao.AQZExtincao;
import libs.aqz.extincao.AQZUTF8Antigamente;
import libs.luan.fmt;

public class MigrarTozterum {

    public static void migrar_tozterum() {

        String arquivo_banco_origem = "/home/luan/assets/coisas_backup/Tozterum.az";
        String arquivo_banco_origem_inmet = "/home/luan/assets/coisas_backup/inmet/INMET.az";
        String arquivo_banco_origem_metro = "/home/luan/assets/coisas_backup/Metropoles.az";
        String arquivo_banco_origem_viagens = "/home/luan/assets/coisas_backup/conquistador/luan_viajante.az";


        String arquivo_banco_destino = "/home/luan/assets/coisas/Tozterum.az";
        String arquivo_banco_destino_inmet = "/home/luan/assets/coisas/inmet/INMET.az";
        String arquivo_banco_destino_metro = "/home/luan/assets/coisas/Metropoles.az";
        String arquivo_banco_destino_viagens = "/home/luan/assets/coisas/conquistador/luan_viajante.az";

        boolean migrar_tronarkum = true;
        boolean migrar_inmet = true;
        boolean migrar_metropoles = true;
        boolean migrar_viagens = true;


        AQZExtincao.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco_origem);
        AQZExtincao.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_origem);


        AQZTX.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_destino);
        AQZUTF8.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_destino);


        if (migrar_tronarkum) {
            //  AQZTX.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);

            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "INFOS(LL)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "STRAVA(LL)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "BOMDIAS(LL)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "CNU(LL)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "BOMDIAS(LL).LINHA_DO_TEMPO");

            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "INFOS(GG)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "STRAVA(GG)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "BOMDIAS(GG)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "CNU(GG)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "BOMDIAS(GG).LINHA_DO_TEMPO");

            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "CRONOLOGICAMENTE");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "ATZUM_VIAGEM(LL)");
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, "BANCO.TAMANHO");


            MIGRAR_UTF8(arquivo_banco_origem, arquivo_banco_destino, "STRAVA_ACOMPANHAMENTO_DADOS(LL)");
            MIGRAR_UTF8(arquivo_banco_origem, arquivo_banco_destino, "STRAVA_ACOMPANHAMENTO_DADOS(GG)");
        }

        AQZUTF8.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_destino);


        if (migrar_inmet) {
            MIGRAR_TUDO_TX_PARA_UTF8(arquivo_banco_origem_inmet, arquivo_banco_destino_inmet);
        }

        AQZUTF8.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_destino_inmet);


        AQZExtincao.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco_origem_metro);
        AQZUTF8.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_destino_metro);

        if (migrar_metropoles) {
            MIGRAR_TUDO_TX_PARA_UTF8(arquivo_banco_origem_metro, arquivo_banco_destino_metro);
        }

        AQZUTF8.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_destino_metro);


        AQZExtincao.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco_origem_viagens);
        AQZUTF8.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_destino_viagens);

        if (migrar_viagens) {
            MIGRAR_TUDO_TX_PARA_UTF8(arquivo_banco_origem_viagens, arquivo_banco_destino_viagens);
        }

        AQZUTF8.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco_destino_viagens);

    }


    public static void ver_tozterum() {

        String arquivo_banco_destino = "/home/luan/assets/coisas/Tozterum.az";

        AQZUTF8.EXIBIR_TUDO(arquivo_banco_destino);

    }

    public static void realizar_transferencias() {

        String arquivo_banco_origem_inmet = "/home/luan/assets/coisas/inmet/INMET.az";
        String arquivo_banco_destino_inmet_v2 = "/home/luan/assets/coisas/inmet/INMET_v2.az";

        //AQZUTF8.TRANSFERIR_COLECOES(arquivo_banco_origem_inmet, arquivo_banco_destino_inmet_v2);
        AQZUTF8.EXIBIR_TUDO_EM_AMOSTRA(arquivo_banco_destino_inmet_v2);


        String arquivo_banco_origem_tozterum = "/home/luan/assets/coisas/Tozterum.az";
        String arquivo_banco_origem_tozterum_v2 = "/home/luan/assets/coisas/Tozterum_v2.az";

        AQZUTF8.TRANSFERIR_COLECOES(arquivo_banco_origem_tozterum, arquivo_banco_origem_tozterum_v2);
        AQZUTF8.EXIBIR_TUDO_EM_AMOSTRA(arquivo_banco_origem_tozterum_v2);

        String arquivo_banco_origem_metropoles = "/home/luan/assets/coisas/Metropoles.az";
        String arquivo_banco_origem_metropoles_v2 = "/home/luan/assets/coisas/Metropoles_v2.az";

        AQZUTF8.TRANSFERIR_COLECOES(arquivo_banco_origem_metropoles, arquivo_banco_origem_metropoles_v2);
        AQZUTF8.EXIBIR_TUDO_EM_AMOSTRA(arquivo_banco_origem_metropoles_v2);

        String arquivo_banco_origem_viajante = "/home/luan/assets/coisas/conquistador/luan_viajante.az";
        String arquivo_banco_origem_viajante_v2 = "/home/luan/assets/coisas/conquistador/luan_viajante_v2.az";

        AQZUTF8.TRANSFERIR_COLECOES(arquivo_banco_origem_viajante, arquivo_banco_origem_viajante_v2);
        AQZUTF8.EXIBIR_TUDO_EM_AMOSTRA(arquivo_banco_origem_viajante_v2);

    }

    public static void ver_inmet() {

        String arquivo_banco_origem_inmet = "/home/luan/assets/coisas/inmet/INMET.az";
        String arquivo_banco_destino_inmet_v2 = "/home/luan/assets/coisas/inmet/INMET_v2.az";


        //  String arquivo_banco_destino = "/home/luan/assets/coisas/inmet/INMET.az";

        AQZUTF8.EXIBIR_TUDO_EM_AMOSTRA(arquivo_banco_origem_inmet);

    }

    public static void MIGRAR_TUDO(String arquivo_banco_origem, String arquivo_banco_destino) {

        for (String colecao : AQZExtincao.COLECOES_LISTAR(arquivo_banco_origem)) {
            MIGRAR_TX(arquivo_banco_origem, arquivo_banco_destino, colecao);
        }

    }

    public static void MIGRAR_TUDO_TX_PARA_UTF8(String arquivo_banco_origem, String arquivo_banco_destino) {

        for (String colecao : AQZExtincao.COLECOES_LISTAR(arquivo_banco_origem)) {
            MIGRAR_TX_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, colecao);
        }

    }


    public static void MIGRAR_TX(String arquivo_banco_origem, String arquivo_banco_destino, String colecao_nome) {

        AQZExtincao.EXIBIR_COLECAO(arquivo_banco_origem, colecao_nome);

        MigrattorAQZ.MIGRAR_TX_BRUTO_PARA_TX_STRING_VIEW(arquivo_banco_origem, arquivo_banco_destino, colecao_nome);

        AQZTX.EXIBIR_COLECAO(arquivo_banco_destino, colecao_nome);

    }

    public static void MIGRAR_UTF8(String arquivo_banco_origem, String arquivo_banco_destino, String colecao_nome) {

        fmt.print("Migrar UTF-8 -> UTF-8 :: " + colecao_nome);

        AQZUTF8Antigamente.EXIBIR_CONTEUDO(arquivo_banco_origem, colecao_nome);

        MigrattorAQZ.MIGRAR_UTF8(arquivo_banco_origem, arquivo_banco_destino, colecao_nome);

        AQZUTF8.EXIBIR_COLECAO(arquivo_banco_destino, colecao_nome);

    }

    public static void MIGRAR_TX_PARA_UTF8(String arquivo_banco_origem, String arquivo_banco_destino, String colecao_nome) {

        fmt.print("Migrar TX -> UTF-8 :: " + colecao_nome);

        AQZExtincao.EXIBIR_COLECAO(arquivo_banco_origem, colecao_nome);

        MigrattorAQZ.MIGRAR_TX_BRUTO_PARA_UTF8(arquivo_banco_origem, arquivo_banco_destino, colecao_nome);

        AQZUTF8.EXIBIR_COLECAO(arquivo_banco_destino, colecao_nome);

    }

}
