package algoritmos;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;
import libs.tempo.Data;
import libs.tronarko.Tronarko;
import libs.zetta.features.ZQC;
import servicos.ASSETS;

public class AtividadesLL {

    public static void VER() {

        Lista<Entidade> dados = ZQC.COLECAO_ENTIDADES(ASSETS.GET_PASTA("coisas/tozterum").getArquivo("Tozterum.az"), "STRAVA_ACOMPANHAMENTO_DADOS(LL)");
        ENTT.EXIBIR_TABELA(dados);

        Lista<Entidade> atividade_por_tipo = ENTT.AGRUPAR(dados, "Tipo");

        for (Entidade grupo : atividade_por_tipo) {

            for (Entidade atv : grupo.getEntidades()) {
                atv.at("Tozte", Tronarko.getData(Data.toData(atv.at("Data")).getTempoLegivel()).getTextoZerado());
                atv.at("Tozte_Superarkos", Tronarko.getData(Data.toData(atv.at("Data")).getTempoLegivel()).getSuperarkosTotal());
            }

            ENTT.ORDENAR_LONG(grupo.getEntidades(), "Tozte_Superarkos");

            if (grupo.getEntidades().possuiObjetos()) {
                grupo.at("Quantidade", ENTT.CONTAGEM(grupo.getEntidades()));
                grupo.at("Primeiro", ENTT.GET_PRIMEIRO(grupo.getEntidades()).at("Tozte"));
                grupo.at("Ultimo", ENTT.GET_ULTIMO(grupo.getEntidades()).at("Tozte"));

                if (grupo.is("Tipo", "RUN") || grupo.is("Tipo", "WALK") || grupo.is("Tipo", "RIDE")) {

                    double distancia = 0.0;

                    for (Entidade atv : grupo.getEntidades()) {
                        if (atv.at("Distancia").endsWith(" km")) {
                            double valor = Double.parseDouble(atv.at("Distancia").replace(" km", ""));
                            distancia+=valor;
                        }
                    }

                    grupo.at("Distancia", fmt.f2(distancia) + " Km");
               }

                int tempo = 0;
                for (Entidade atv : grupo.getEntidades()) {
                    if (Strings.contar(atv.at("Tempo"), ":") == 1) {
                        int iMinutos = Integer.parseInt(Strings.GET_ATE(atv.at("Tempo"), ":"));
                        int iSegundos = Integer.parseInt(Strings.GET_DEPOIS(atv.at("Tempo"), ":"));
                        tempo += (iMinutos * 60 ) + (iSegundos );
                    } else if (Strings.contar(atv.at("Tempo"), ":") == 2) {
                        int iHoras = Integer.parseInt(Strings.GET_ATE(atv.at("Tempo"), ":"));
                        String iMinutosESegundos = Strings.GET_DEPOIS(atv.at("Tempo"), ":");
                        int iMinutos = Integer.parseInt(Strings.GET_ATE(iMinutosESegundos, ":"));
                        int iSegundos = Integer.parseInt(Strings.GET_DEPOIS(iMinutosESegundos, ":"));
                        tempo += (iHoras*60*60)+(iMinutos * 60 ) + (iSegundos );
                    }
                }
                grupo.at("Tempo", fmt.formatar_tempo(tempo));


            }

        }

        ENTT.AT_ALTERAR_NOME(atividade_por_tipo,"Tipo","Esporte");
        ENTT.AT_ALTERAR_NOME(atividade_por_tipo,"Quantidade","Atividades");

        ENTT.ORDENAR_INTEIRO_DECRESCENTE(atividade_por_tipo,"Atividades");
        ENTT.REMOVER_SE(atividade_por_tipo,"Esporte","WATER SPORT");

        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo,"Esporte","RUN","Corrida");
        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo,"Esporte","WORKOUT","Treinos");
        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo,"Esporte","SWIM","Natação");
        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo,"Esporte","WALK","Caminhadas");
        ENTT.ALTERAR_VALOR_SE(atividade_por_tipo,"Esporte","RIDE","Ciclismo");



        ENTT.EXIBIR_TABELA(atividade_por_tipo);

    }

}
