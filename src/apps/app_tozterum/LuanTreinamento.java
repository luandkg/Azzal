package apps.app_tozterum;

import apps.app_tozterum.treinamento.*;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Par;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;

public class LuanTreinamento {


    public void treino_rizno_7004() {


        // 3 SEMANAS CADA CARGA

        PlanejamentoDeCargaDeTreino pct = new PlanejamentoDeCargaDeTreino();

        Tozte tozte_corrente = Tronarko.getData("02/03/2025");


        Lista<Par<Serie,Serie>> evolucao_das_series = new Lista<Par<Serie,Serie>>();

        evolucao_das_series.adicionar(new Par<Serie,Serie>(Fisica.SERIE(3,12),Fisica.SERIE(3,15)));
        evolucao_das_series.adicionar(new Par<Serie,Serie>(Fisica.SERIE(3,15),Fisica.SERIE(4,15)));
        evolucao_das_series.adicionar(new Par<Serie,Serie>(Fisica.SERIE(4,10),Fisica.SERIE(5,10)));
        evolucao_das_series.adicionar(new Par<Serie,Serie>(Fisica.SERIE(4,12),Fisica.SERIE(5,12)));
        evolucao_das_series.adicionar(new Par<Serie,Serie>(Fisica.SERIE(4,15),Fisica.SERIE(5,15)));

        for(Par<Serie,Serie> series :evolucao_das_series ){

            Tozte t_inicio = tozte_corrente.getCopia();
            tozte_corrente=tozte_corrente.adicionar_Superarko(21);
            Tozte t_fim =  tozte_corrente.getCopia();

            pct.planejar(t_inicio,t_fim,series.getChave(),series.getValor());
        }



        pct.setHoje(Tronarko.getTozte());
        pct.visualizar();

        Lista<Entidade> treino_v1 =  TreinamentoRetornoQ4.treino_1(pct.getVisualizacao());
        Lista<Entidade> treino_v2 =  TreinamentoRetornoQ4.treino_2(pct.getVisualizacao());
        Lista<Entidade> treino_v3 =  TreinamentoRetornoQ4.treino_3(pct.getVisualizacao());


        Treinamento.EXPORTAR(treino_v1,"treino_v1");
        Treinamento.EXPORTAR(treino_v2,"treino_v2");
        Treinamento.EXPORTAR(treino_v3,"treino_v3");


        ENTT.EXIBIR_TABELA_COM_NOME(treino_v1, "TREINO :: SEGUNDA && QUINTA");
        ENTT.EXIBIR_TABELA_COM_NOME(treino_v2, "TREINO :: TERÇA && SEXTA");
        ENTT.EXIBIR_TABELA_COM_NOME(treino_v3, "TREINO :: QUARTA");

    }



}
