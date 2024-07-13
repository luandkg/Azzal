package apps.app_campeonatum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Aleatorio;
import libs.luan.Lista;
import libs.luan.fmt;
import libs.tronarko.Hazde;
import libs.tronarko.utils.StringTronarko;

public class CompeticaoDetalhada {


    public static void init(Entidade eCompeticao) {

        String s_primeio = "";
        String s_segundo = "";

        if (Aleatorio.aleatorio(100) >= 50) {
            s_primeio = "CAMPO";
            s_segundo = "BOLA";
        } else {
            s_primeio = "BOLA";
            s_segundo = "CAMPO";
        }

        fmt.print("\t - Competição : {} ({}) vs {} ({})", eCompeticao.at("T1"), s_primeio, eCompeticao.at("T2"), s_segundo);
        fmt.print("\t - Tozte      : {}", eCompeticao.at("Tozte"));
        fmt.print("\t - Hazde      : {}", eCompeticao.at("Hazde"));


        detalhar_jogo(eCompeticao);


    }

    public static void detalhar_jogo(Entidade eCompeticao) {

        StringTronarko st = new StringTronarko();

        Hazde hazde = st.parseHazdeSemUzzons(eCompeticao.at("Hazde"));

        Hazde tempo_1_inicio = hazde.getCopia();
        Hazde tempo_1_fim = hazde.getCopia().adicionar_Itta(50);


        int vencedor_gols = 0;
        int perdedor_gols = 0;

        int gols = 0;

        if (eCompeticao.at("Resultado.Decisao").contentEquals("JOGO")) {
            vencedor_gols = eCompeticao.atInt("Gols.Vencedor");
            perdedor_gols = eCompeticao.atInt("Gols.Perdedor");
        } else if (eCompeticao.at("Resultado.Decisao").contentEquals("PRORROGAÇÃO")) {

            gols = eCompeticao.atInt("Gols");

            vencedor_gols = gols;
            perdedor_gols = gols;
        } else if (eCompeticao.at("Resultado.Decisao").contentEquals("PENAULTS")) {

            gols = eCompeticao.atInt("Gols");

            vencedor_gols = gols;
            perdedor_gols = gols;

        }


        fmt.print("\t - 1T :: {} - {}", tempo_1_inicio.getTextoSemUzzonZerado(), tempo_1_fim.getTextoSemUzzonZerado());

        Hazde tempo_2_inicio = tempo_1_fim.getCopia().adicionar_Itta(15);
        Hazde tempo_2_fim = tempo_2_inicio.getCopia().adicionar_Itta(50);

        Hazde tempo_prorrogacao_1_inicio = tempo_2_fim.getCopia().adicionar_Itta(10);
        Hazde tempo_prorrogacao_1_fim = tempo_prorrogacao_1_inicio.getCopia().adicionar_Itta(10);

        Hazde tempo_prorrogacao_2_inicio = tempo_prorrogacao_1_fim.getCopia().adicionar_Itta(5);
        Hazde tempo_prorrogacao_2_fim = tempo_prorrogacao_2_inicio.getCopia().adicionar_Itta(10);


        fmt.print("\t - 2T :: {} - {}", tempo_2_inicio.getTextoSemUzzonZerado(), tempo_2_fim.getTextoSemUzzonZerado());

        if (eCompeticao.at("Resultado.Decisao").contentEquals("PRORROGAÇÃO")) {

            fmt.print("\t - 1P :: {} - {}", tempo_prorrogacao_1_inicio.getTextoSemUzzonZerado(), tempo_prorrogacao_1_fim.getTextoSemUzzonZerado());
            fmt.print("\t - 2P :: {} - {}", tempo_prorrogacao_2_inicio.getTextoSemUzzonZerado(), tempo_prorrogacao_2_fim.getTextoSemUzzonZerado());

        }


        Lista<Entidade> jogando = new Lista<Entidade>();


        CONSTRUIR_JOGO_ITTA_A_ITTA(jogando, tempo_1_inicio.getCopia(), tempo_1_fim.getCopia());
        CONSTRUIR_JOGO_ITTA_A_ITTA(jogando, tempo_2_inicio.getCopia(), tempo_2_fim.getCopia());

        MARCAR_GOL(jogando, vencedor_gols, eCompeticao.at("Vencedor"));
        MARCAR_GOL(jogando, perdedor_gols, eCompeticao.at("Perdedor"));


        Lista<Entidade> jogando_simplificado = FILTRAR_COM_STATUS(jogando);
        ENTT.EXIBIR_TABELA(jogando_simplificado);

        if (eCompeticao.at("Resultado.Decisao").contentEquals("PRORROGAÇÃO") || eCompeticao.at("Resultado.Decisao").contentEquals("PENAULTS")) {

            if (eCompeticao.at("Resultado.Decisao").contentEquals("PRORROGAÇÃO")) {

                vencedor_gols = eCompeticao.atInt("Gols.Vencedor") - gols;
                perdedor_gols = eCompeticao.atInt("Gols.Perdedor") - gols;

            }

            if (eCompeticao.at("Resultado.Decisao").contentEquals("PENAULTS")) {
                vencedor_gols = eCompeticao.atInt("Prorrogacao.Gols");
                perdedor_gols = eCompeticao.atInt("Prorrogacao.Gols");
            }


            Lista<Entidade> jogando2 = new Lista<Entidade>();

            CONSTRUIR_JOGO_ITTA_A_ITTA(jogando2, tempo_prorrogacao_1_inicio.getCopia(), tempo_prorrogacao_1_fim.getCopia());
            CONSTRUIR_JOGO_ITTA_A_ITTA(jogando2, tempo_prorrogacao_2_inicio.getCopia(), tempo_prorrogacao_2_fim.getCopia());


            MARCAR_GOL(jogando2, vencedor_gols, eCompeticao.at("Vencedor"));
            MARCAR_GOL(jogando2, perdedor_gols, eCompeticao.at("Perdedor"));


            Lista<Entidade> jogando_simplificado2 = FILTRAR_COM_STATUS(jogando2);
            ENTT.EXIBIR_TABELA(jogando_simplificado2);


        }

        if (eCompeticao.at("Resultado.Decisao").contentEquals("PENAULTS")) {


        }

    }


    public static void CONSTRUIR_JOGO_ITTA_A_ITTA(Lista<Entidade> jogo, Hazde eInicio, Hazde eFim) {

        while (eInicio.isMenorIgual(eFim)) {

            Entidade e = new Entidade();

            e.at("ID", jogo.getQuantidade());
            e.at("Hazde", eInicio.getTextoSemUzzonZerado());

            jogo.adicionar(e);
            eInicio = eInicio.adicionar_Itta(1);
        }

    }


    public static void MARCAR_GOL(Lista<Entidade> jogo, int gols_quantidade, String time) {

        for (int gol = 0; gol < gols_quantidade; gol++) {

            boolean marcado = false;

            while (!marcado) {

                Entidade aqui = jogo.get(Aleatorio.aleatorio(jogo.getQuantidade()));
                if (aqui.at("Status").isEmpty()) {
                    aqui.at("Status", "GOL - " + time);
                    marcado = true;
                }

            }

        }

    }


    public static Lista<Entidade> FILTRAR_COM_STATUS(Lista<Entidade> jogo) {

        Lista<Entidade> jogo_com_status = new Lista<Entidade>();
        for (Entidade e : jogo) {
            if (!e.at("Status").isEmpty()) {
                jogo_com_status.adicionar(e);
            }
        }

        return jogo_com_status;
    }


}
