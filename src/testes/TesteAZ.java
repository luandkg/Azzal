package testes;

import libs.aqz.*;

public class TesteAZ {


    public static void init() {

        String arquivo_banco = "/home/luan/assets/Migratorium.az";

        AQZ.EXIBIR_ESTRUTURA_INTERNA(arquivo_banco);
        AQZ.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);
      //  AQZTX.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);

      //  MIGRAR_TX(arquivo_banco, "INFOS(LL)");
       // MIGRAR_TX(arquivo_banco, "STRAVA(LL)");
      //  MIGRAR_TX(arquivo_banco, "BOMDIAS(LL)");
     //   MIGRAR_TX(arquivo_banco, "CNU(LL)");
     //   MIGRAR_TX(arquivo_banco, "BOMDIAS(LL).LINHA_DO_TEMPO");

     //   MIGRAR_TX(arquivo_banco, "INFOS(GG)");
    //    MIGRAR_TX(arquivo_banco, "STRAVA(GG)");
    //    MIGRAR_TX(arquivo_banco, "BOMDIAS(GG)");
    //    MIGRAR_TX(arquivo_banco, "CNU(GG)");
    //    MIGRAR_TX(arquivo_banco, "BOMDIAS(GG).LINHA_DO_TEMPO");

    //    MIGRAR_TX(arquivo_banco, "CRONOLOGICAMENTE");
    //    MIGRAR_TX(arquivo_banco, "ATZUM_VIAGEM(LL)");
    //    MIGRAR_TX(arquivo_banco, "BANCO.TAMANHO");


      //  MIGRAR_UTF8(arquivo_banco, "STRAVA_ACOMPANHAMENTO_DADOS(LL)");
    //    MIGRAR_UTF8(arquivo_banco, "STRAVA_ACOMPANHAMENTO_DADOS(GG)");

        AQZTX.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);
        AQZUTF8.EXIBIR_ESTRUTURA_PUBLICA(arquivo_banco);


        //AQZTX.EXIBIR_COLECAO(arquivo_banco, "BOMDIAS(LL)");
        //AQZTX.EXIBIR_TUDO(arquivo_banco);

      //  AQZUTF8Antigamente.EXIBIR_CONTEUDO(arquivo_banco,"STRAVA_ACOMPANHAMENTO_DADOS(LL)");
     //   AQZUTF8Antigamente.EXIBIR_CONTEUDO(arquivo_banco,"STRAVA_ACOMPANHAMENTO_DADOS(GG)");

        //  AQZUTF8Antigamente.EXIBIR_CONTEUDO(arquivo_banco,"STRAVA_ACOMPANHAMENTO_DADOS(GG)");


      //  AQZUTF8.EXIBIR_COLECAO(arquivo_banco,"STRAVA_ACOMPANHAMENTO_DADOS(LL)");
       // AQZUTF8.EXIBIR_COLECAO(arquivo_banco,"STRAVA_ACOMPANHAMENTO_DADOS(GG)");

    }


    public static void MIGRAR_TX(String arquivo_banco, String colecao_nome) {

        AQZ.EXIBIR_COLECAO(arquivo_banco, colecao_nome);

        MigrattorAQZ.MIGRAR_TX_BRUTO_PARA_TX_STRING_VIEW(arquivo_banco, colecao_nome);

        AQZTX.EXIBIR_COLECAO(arquivo_banco, colecao_nome);

    }

    public static void MIGRAR_UTF8(String arquivo_banco, String colecao_nome) {

        AQZUTF8Antigamente.EXIBIR_CONTEUDO(arquivo_banco, colecao_nome);

        MigrattorAQZ.MIGRAR_UTF8(arquivo_banco, colecao_nome);

        AQZUTF8.EXIBIR_COLECAO(arquivo_banco, colecao_nome);

    }

}
