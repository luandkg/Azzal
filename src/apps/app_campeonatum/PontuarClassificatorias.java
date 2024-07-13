package apps.app_campeonatum;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Aleatorio;
import libs.luan.Lista;

public class PontuarClassificatorias {

    public static void PONTUAR_VITORIAS(Lista<Entidade> grupo_analise, int resultado) {

        int vitorias_maior = 0;

        for (Entidade competicao : grupo_analise) {

            if (competicao.atInt("Vitorias") > vitorias_maior) {
                vitorias_maior = competicao.atInt("Vitorias");
            }
        }

        int maior_quantidade = 0;

        for (Entidade competicao : grupo_analise) {
            if (competicao.atInt("Vitorias") == vitorias_maior) {
                maior_quantidade += 1;
            }
        }

        if (maior_quantidade == 1) {
            for (Entidade competicao : grupo_analise) {
                if (competicao.atInt("Vitorias") == vitorias_maior) {
                    competicao.at("Classificacao", resultado);
                    competicao.at("Classificacao.Status", "VITORIAS");

                }
            }
        } else if (maior_quantidade > 1) {
            boolean foi_classificado = CLASSIFICAR_POR_GOLS_ACUMULADO(ENTT.COLETAR(grupo_analise, "Vitorias", vitorias_maior), resultado);
        }


    }

    public static boolean CLASSIFICAR_POR_GOLS_ACUMULADO(Lista<Entidade> grupo_analise, int resultado) {

        int gols_maior = 0;
        boolean foi_classificado = false;

        for (Entidade competicao : grupo_analise) {
            if (competicao.atInt("Gols.Acumulado") > gols_maior) {
                gols_maior = competicao.atInt("Gols.Acumulado");
            }
        }

        int maior_quantidade = 0;

        for (Entidade competicao : grupo_analise) {
            if (competicao.atInt("Gols.Acumulado") == gols_maior) {
                maior_quantidade += 1;
            }
        }

        if (maior_quantidade == 1) {
            for (Entidade competicao : grupo_analise) {
                if (competicao.atInt("Gols.Acumulado") == gols_maior) {
                    competicao.at("Classificacao", resultado);
                    competicao.at("Classificacao.Status", "GOLS ACUMULADO");

                    foi_classificado = true;
                }
            }
        } else if (maior_quantidade > 1) {
            foi_classificado = CLASSIFICAR_POR_GOLS_PARTIDA(ENTT.COLETAR(grupo_analise, "Gols.Acumulado", gols_maior), resultado);
        }

        return foi_classificado;
    }

    public static boolean CLASSIFICAR_POR_GOLS_PARTIDA(Lista<Entidade> grupo_analise, int resultado) {

        int gols_maior = 0;
        boolean foi_classificado = false;

        for (Entidade competicao : grupo_analise) {
            if (competicao.atInt("Gols.Maximo") > gols_maior) {
                gols_maior = competicao.atInt("Gols.Maximo");
            }
        }

        int maior_quantidade = 0;

        for (Entidade competicao : grupo_analise) {
            if (competicao.atInt("Gols.Maximo") == gols_maior) {
                maior_quantidade += 1;
            }
        }

        if (maior_quantidade == 1) {
            for (Entidade competicao : grupo_analise) {
                if (competicao.atInt("Gols.Maximo") == gols_maior) {
                    competicao.at("Classificacao", resultado);
                    competicao.at("Classificacao.Status", "GOLS PARTIDA");

                    foi_classificado = true;
                }
            }
        } else if (maior_quantidade > 1) {
            Lista<Entidade> listagem = ENTT.COLETAR(grupo_analise, "Gols.Maximo", gols_maior);

            int escolhido = Aleatorio.aleatorio(listagem.getQuantidade());

            Entidade competicao = listagem.get(escolhido);
            competicao.at("Classificacao", resultado);
            competicao.at("Classificacao.Status", "SORTEIO");
            foi_classificado = true;

            // foi_classificado=  CLASSIFICAR_POR_GOLS_PARTIDA(ENTT.COLETAR(grupo_analise,"Gols.Acumulado",gols_maior),resultado);
        }

        return foi_classificado;
    }

}
