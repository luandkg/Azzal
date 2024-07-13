package apps.app_campeonatum;

import libs.entt.Entidade;
import libs.luan.Aleatorio;
import libs.luan.Lista;

public class Competicao {

    public static void REALIZAR_PARTIDAS(Lista<Entidade> TIMES_COMPETICAO) {

        for (Entidade competicao : TIMES_COMPETICAO) {

            if (Aleatorio.aleatorio(100) >= 70) {

                int q1 = Aleatorio.aleatorio(5);

                competicao.at("Resultado", "EMPATE");
                competicao.at("Resultado.Status", q1 + " == " + q1);
                competicao.at("Gols", q1);

            } else {

                int q1 = 1 + Aleatorio.aleatorio(5);
                int q2 = q1 + Aleatorio.aleatorio(5);

                if (q1 == q2) {
                    q2 += 1;
                }

                competicao.at("Resultado", "VITÓRIA");
                competicao.at("Resultado.Status", q2 + " > " + q1);

                if (Aleatorio.aleatorio(100) >= 50) {
                    competicao.at("Vencedor", competicao.at("T1"));
                    competicao.at("Perdedor", competicao.at("T2"));
                } else {
                    competicao.at("Vencedor", competicao.at("T2"));
                    competicao.at("Perdedor", competicao.at("T1"));

                }

                competicao.at("Gols.Vencedor", q2);
                competicao.at("Gols.Perdedor", q1);

            }

        }

    }

    public static void REALIZAR_PARTIDAS_MATA_MATA(Lista<Entidade> TIMES_COMPETICAO) {


        for (Entidade competicao : TIMES_COMPETICAO) {

            competicao.at("Resultado.Modo", "MATA_MATA");
            competicao.at("Resultado.Decisao", "JOGO");
            competicao.at("Resultado", "");
            competicao.at("Vencedor", "");
            competicao.at("Perdedor", "");

            if (Aleatorio.aleatorio(100) >= 70) {

                // PARTIDA COM EMPATE


                int gols_empate = Aleatorio.aleatorio(5);

                competicao.at("Resultado", "EMPATE");
                competicao.at("Resultado.Status", gols_empate + " == " + gols_empate);
                competicao.at("Gols", gols_empate);

                competicao.at("Prorrogacao", "SIM");

                competicao.at("Antes.Resultado", "EMPATE");
                competicao.at("Antes.Resultado.Status", gols_empate + " == " + gols_empate);
                competicao.at("Antes.Gols", gols_empate);


                Entidade e_partida = new Entidade();
                e_partida.at("Tipo", "PARTIDA");
                e_partida.at("Resultado", "EMPATE");
                e_partida.at("Gols", competicao.at("Gols"));

                competicao.getEntidades().adicionar(e_partida);


                if (Aleatorio.aleatorio(100) >= 70) {

                    int prorrogacao_gols = Aleatorio.aleatorio(2);
                    gols_empate = gols_empate + prorrogacao_gols;
                    competicao.at("Prorrogacao.Resultado", "EMPATE");
                    competicao.at("Prorrogacao.Gols", prorrogacao_gols);

                    Entidade e_prorrogacao = new Entidade();
                    e_prorrogacao.at("Tipo", "PRORROGAÇÃO");
                    e_prorrogacao.at("Resultado", "EMPATE");
                    e_prorrogacao.at("Gols", competicao.at("Prorrogacao.Gols"));

                    competicao.getEntidades().adicionar(e_prorrogacao);

                    REALIZAR_PENAULTS(competicao, gols_empate);

                } else {

                    competicao.at("Prorrogacao.Resultado", "VITÓRIA");
                    competicao.at("Resultado.Decisao", "PRORROGAÇÃO");

                    competicao.at("Resultado", "VITÓRIA");

                    int prorrogacao_gols = Aleatorio.aleatorio(2);
                    int gols_antes = gols_empate;

                    int menor = gols_empate + 1 + prorrogacao_gols;
                    int maior = gols_empate + Aleatorio.aleatorio(2);

                    if (menor == maior) {
                        maior += 1;
                    }

                    competicao.at("Resultado", "VITÓRIA");
                    competicao.at("Resultado.Status", maior + " > " + menor);

                    if (Aleatorio.aleatorio(100) >= 50) {
                        competicao.at("Vencedor", competicao.at("T1"));
                        competicao.at("Perdedor", competicao.at("T2"));

                        competicao.at("Prorrogacao.Vencedor", competicao.at("T1"));
                        competicao.at("Prorrogacao.Perdedor", competicao.at("T2"));

                    } else {
                        competicao.at("Vencedor", competicao.at("T2"));
                        competicao.at("Perdedor", competicao.at("T1"));

                        competicao.at("Prorrogacao.Vencedor", competicao.at("T2"));
                        competicao.at("Prorrogacao.Perdedor", competicao.at("T1"));
                    }

                    competicao.at("Gols.Vencedor", maior);
                    competicao.at("Gols.Perdedor", menor);

                    Entidade e_prorrogacao = new Entidade();
                    e_prorrogacao.at("Tipo", "PRORROGAÇÃO");
                    e_prorrogacao.at("Resultado", "VITÓRIA");
                    e_prorrogacao.at("Vencedor", competicao.at("Prorrogacao.Vencedor"));
                    e_prorrogacao.at("Perdedor", competicao.at("Prorrogacao.Perdedor"));
                    e_prorrogacao.at("Gols.Vencedor", competicao.atInt("Gols.Vencedor") - gols_antes);
                    e_prorrogacao.at("Gols.Perdedor", competicao.atInt("Gols.Perdedor") - gols_antes);


                    competicao.getEntidades().adicionar(e_prorrogacao);

                }

            } else {

                // PARTIDA COM VITORIA

                int menor = Aleatorio.aleatorio(5);
                int maior = Aleatorio.aleatorio(5);

                if (menor == maior) {
                    int mais = Aleatorio.aleatorio(2);
                    if (mais == 0) {
                        mais = 1;
                    }
                    maior += mais;
                } else if (menor > maior) {
                    int temp = maior;
                    maior = menor;
                    menor = temp;
                }

                competicao.at("Resultado", "VITÓRIA");
                competicao.at("Resultado.Status", maior + " > " + menor);

                if (Aleatorio.aleatorio(100) >= 50) {
                    competicao.at("Vencedor", competicao.at("T1"));
                    competicao.at("Perdedor", competicao.at("T2"));
                } else {
                    competicao.at("Vencedor", competicao.at("T2"));
                    competicao.at("Perdedor", competicao.at("T1"));
                }

                competicao.at("Gols.Vencedor", maior);
                competicao.at("Gols.Perdedor", menor);


                Entidade e_partida = new Entidade();
                e_partida.at("Tipo", "PARTIDA");
                e_partida.at("Resultado", "VITÓRIA");

                e_partida.at("Vencedor", competicao.at("Vencedor"));
                e_partida.at("Perdedor", competicao.at("Perdedor"));
                e_partida.at("Gols.Vencedor", competicao.at("Gols.Vencedor"));
                e_partida.at("Gols.Perdedor", competicao.at("Gols.Perdedor"));

                competicao.getEntidades().adicionar(e_partida);

            }

        }

    }


    public static void REALIZAR_PENAULTS(Entidade competicao, int q1) {
        competicao.at("Penaults", "SIM");
        competicao.at("Resultado.Decisao", "PENAULTS");

        boolean disputa_em_penaults = true;

        int penault = 0;

        int acumulado_t1 = q1;
        int acumulado_t2 = q1;


        while (disputa_em_penaults) {
            penault += 1;
            competicao.at("Penaults.Quantidade", penault);

            int pena_t1 = Aleatorio.aleatorio(3);
            int pena_t2 = Aleatorio.aleatorio(3);

            acumulado_t1 += pena_t1;
            acumulado_t2 += pena_t2;

            Entidade e_penault = new Entidade();
            e_penault.at("Tipo", "PENAULT");
            e_penault.at("Penault.ID", penault);

            competicao.getEntidades().adicionar(e_penault);

            if (pena_t1 != pena_t2) {

                e_penault.at("Resultado", "VITÓRIA");

                competicao.at("Resultado", "VITÓRIA");
                competicao.at("Penaults_Partida_" + penault + "_Status", "VITÓRIA");

                if (acumulado_t1 > acumulado_t2) {
                    competicao.at("Vencedor", competicao.at("T1"));
                    competicao.at("Perdedor", competicao.at("T2"));

                    competicao.at("Gols.Vencedor", acumulado_t1);
                    competicao.at("Gols.Perdedor", acumulado_t2);

                    competicao.at("Penaults_Partida_" + penault + "_Vencedor", competicao.at("T1"));
                    competicao.at("Penaults_Partida_" + penault + "_Perdedor", competicao.at("T2"));


                    e_penault.at("Vencedor", competicao.at("T1"));
                    e_penault.at("Perdedor", competicao.at("T2"));

                    e_penault.at("Gols.Vencedor", pena_t1);
                    e_penault.at("Gols.Perdedor", pena_t2);

                    competicao.at("Resultado.Status", acumulado_t1 + " > " + acumulado_t2);

                } else {
                    competicao.at("Vencedor", competicao.at("T2"));
                    competicao.at("Perdedor", competicao.at("T1"));

                    competicao.at("Gols.Vencedor", acumulado_t2);
                    competicao.at("Gols.Perdedor", acumulado_t1);

                    competicao.at("Penaults_Partida_" + penault + "_Vencedor", competicao.at("T2"));
                    competicao.at("Penaults_Partida_" + penault + "_Perdedor", competicao.at("T1"));

                    e_penault.at("Vencedor", competicao.at("T2"));
                    e_penault.at("Perdedor", competicao.at("T1"));

                    e_penault.at("Gols.Vencedor", pena_t2);
                    e_penault.at("Gols.Perdedor", pena_t1);

                    competicao.at("Resultado.Status", acumulado_t2 + " > " + acumulado_t1);

                }


                disputa_em_penaults = false;
            } else {

                e_penault.at("Resultado", "EMPATE");
                e_penault.at("Gols", pena_t1);

                competicao.at("Penaults_Partida_" + penault + "_Status", "EMPATE");
                competicao.at("Penaults_Partida_" + penault + "_Gols", pena_t1);
            }


        }

    }
}
