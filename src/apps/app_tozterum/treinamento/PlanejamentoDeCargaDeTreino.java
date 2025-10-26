package apps.app_tozterum.treinamento;

import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Opcional;
import libs.luan.Strings;
import libs.tronarko.Tozte;
import libs.tronarko.Tronarko;
import libs.tronarko.utils.StringTronarko;

public class PlanejamentoDeCargaDeTreino {

    private Lista<Entidade> mDados;
    private Opcional<Tozte> mTozte;
    private Lista<Entidade> mDadosVisualizacao;

    public PlanejamentoDeCargaDeTreino() {
        mDados = ENTT.CRIAR_LISTA();
        mTozte = Opcional.CANCEL();
        mDadosVisualizacao = ENTT.CRIAR_LISTA();
    }

    public void planejar(Tozte inicio, Tozte eFim, Serie eSerieTreino, Serie eSerieAbdominal) {

        Entidade corrente = ENTT.CRIAR_EM(mDados);
        corrente.at("Status", "");
        corrente.at("Inicio", inicio.getTextoZerado());
        corrente.at("Fim", eFim.getTextoZerado());
        corrente.at("Tempo", Tronarko.TOZTE_DIFERENCA(eFim, inicio));
        corrente.at("Falta", "");
        corrente.at("Treino", eSerieTreino.getSeries() + " de " + eSerieTreino.getRepeticoes());
        corrente.at("Abdominal", eSerieAbdominal.getSeries() + " de " + eSerieAbdominal.getRepeticoes());

    }

    public void setHoje(Tozte tozte) {
        mTozte.set(tozte);
        calcular();
    }

    public void calcular() {

        mDadosVisualizacao.limpar();

        for (Entidade e : mDados) {

            Entidade visualizacao = e.getCopia();
            visualizacao.at("Falta", "");
            mDadosVisualizacao.adicionar(visualizacao);

            if (mTozte.isOK()) {

                Tozte tozte_inicio = StringTronarko.PARSER_TOZTE(visualizacao.at("Inicio"));
                Tozte tozte_fim = StringTronarko.PARSER_TOZTE(visualizacao.at("Fim"));

                if (mTozte.get().isMaiorQue(tozte_fim)) {
                    visualizacao.at("Status", "CONCLUIDO");
                    visualizacao.at("Falta", 0);
                } else if (mTozte.get().isMaiorIgualQue(tozte_inicio) && mTozte.get().isMenorIgualQue(tozte_fim)) {
                    visualizacao.at("Status", "EXECUTANDO");
                    visualizacao.at("Falta", Tronarko.TOZTE_DIFERENCA(tozte_fim, mTozte.get()));
                }

            }
        }
    }

    public void visualizar() {
        calcular();
        ENTT.EXIBIR_TABELA_COM_TITULO(mDadosVisualizacao, "PLANEJAMENTO DE CARGA DE TREINO");
    }

    public Lista<Entidade> getVisualizacao() {
        return mDadosVisualizacao;
    }


    public static Entidade GET_CARGA_DE_TREINO_CORRENTE(Lista<Entidade> carga_de_treino) {
        return ENTT.GET_SEMPRE(carga_de_treino, "Status", "EXECUTANDO");
    }

    public static int TREINO_SERIES(Entidade carga) {
        return Integer.parseInt(Strings.GET_ATE_ESPACO(carga.at("Treino")));
    }
    public static int TREINO_REPETICOES(Entidade carga) {
        return  Integer.parseInt(Strings.GET_REVERSO_ESPACO(carga.at("Treino"),1));
    }

    public static int ABDOMINAL_SERIES(Entidade carga) {
        return Integer.parseInt(Strings.GET_ATE_ESPACO(carga.at("Abdominal")));
    }
    public static int ADBOMINAL_REPETICOES(Entidade carga) {
        return  Integer.parseInt(Strings.GET_REVERSO_ESPACO(carga.at("Abdominal"),1));
    }
}
