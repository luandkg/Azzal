package apps.app_tozterum.treinamento;

import libs.entt.Entidade;

public class Exercicio {
    private Entidade mDados;

    public Exercicio(Entidade eDados) {
        mDados = eDados;
    }

    public Exercicio nome(String eNome) {
        mDados.at("Nome", eNome);
        return this;
    }

    public Exercicio repeticao(int eSeries, int eRepeticoes) {
        mDados.at("Series", eSeries);
        mDados.at("Repeticoes", eRepeticoes);
        return this;
    }

    public Exercicio carga(String eCarga) {
        mDados.at("Carga", eCarga);
        return this;
    }

    public Exercicio obs(String eObs) {
        mDados.at("Observacao", eObs);
        return this;
    }

    public Exercicio tipo(String eTipo) {
        mDados.at("Tipo", eTipo);
        return this;
    }

    public Exercicio tempo(String eTempo) {
        mDados.at("Tempo", eTempo);
        return this;
    }
}
