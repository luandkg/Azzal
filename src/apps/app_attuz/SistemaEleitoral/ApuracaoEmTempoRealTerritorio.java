package apps.app_attuz.SistemaEleitoral;

import libs.materializedview.MaterializedView10K;

import java.util.ArrayList;

public class ApuracaoEmTempoRealTerritorio {

    private int mCidadaos_obrigados;
    private ArrayList<CargoApuracao> cargos;

    public int urnas_da_eleicao = 0;

    public int presenca = 0;
    public int presenca_obrigatoria = 0;
    public int abstensao = 0;

    public int mostrar_em = 10;
    public int votos = 0;
    public int urnas_apuradas = 0;
    public String mTerritorio = "";

    public int total_de_urnas = 0;

    public ApuracaoEmTempoRealTerritorio(String territorio, int urnas,int cidadaos_obrigados) {

        mTerritorio = territorio;
        mCidadaos_obrigados = cidadaos_obrigados;
        total_de_urnas=urnas;

        cargos = new ArrayList<CargoApuracao>();

        cargos.add(new CargoApuracao("Presidencia"));
        cargos.add(new CargoApuracao("Senado"));
        cargos.add(new CargoApuracao("Secretaria"));

        for (CargoApuracao ca : cargos) {
            ca.criar_candidato("BRANCO");
            ca.criar_candidato("NULO");
        }

        urnas_apuradas = 0;

        presenca = 0;
        presenca_obrigatoria = 0;
        abstensao = 0;

        mostrar_em = 10;
        votos = 0;

    }

    public String getTerritorio() {
        return mTerritorio;
    }

    public ArrayList<CargoApuracao> getCargos() {
        return cargos;
    }

}
