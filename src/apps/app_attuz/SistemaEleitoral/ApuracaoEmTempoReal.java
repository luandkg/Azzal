package apps.app_attuz.SistemaEleitoral;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Regiao.Regiao;
import apps.app_attuz.Regiao.Regionalizador;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Aleatorio;
import libs.luan.Indexado;
import libs.luan.Indexamento;
import libs.materializedview.MaterializedView10K;

import java.util.ArrayList;

public class ApuracaoEmTempoReal {

    private int mCidadaos_obrigados;
    private ArrayList<CargoApuracao> cargos;

    public int urnas_da_eleicao = 0;

    public int presenca = 0;
    public int presenca_obrigatoria = 0;
    public int abstensao = 0;

    public int mostrar_em = 10;
    public int votos = 0;
    public int urnas_apuradas = 0;
    public String mLocalUrnas = "";

    private ArrayList<ApuracaoEmTempoRealTerritorio> mTerritorios;
    private Regionalizador r;


    public ApuracaoEmTempoReal(int cidadaos_obrigados, String local_territorios, String local_urnas) {

        mCidadaos_obrigados = cidadaos_obrigados;
        mLocalUrnas = local_urnas;

        cargos = new ArrayList<CargoApuracao>();

        cargos.add(new CargoApuracao("Presidencia"));
        cargos.add(new CargoApuracao("Senado"));
        cargos.add(new CargoApuracao("Secretaria"));

        for (CargoApuracao ca : cargos) {
            ca.criar_candidato("BRANCO");
            ca.criar_candidato("NULO");
        }

        urnas_da_eleicao = MaterializedView10K.contagem(local_urnas);
        urnas_apuradas = 0;

        presenca = 0;
        presenca_obrigatoria = 0;
        abstensao = 0;

        mostrar_em = Aleatorio.aleatorio_entre(10, 30);
        votos = 0;

        mTerritorios = new ArrayList<ApuracaoEmTempoRealTerritorio>();

        Arkazz a = new Arkazz();
        String LOCAL = "/home/luan/Imagens/Arkazz/";
        r = new Regionalizador(LOCAL);

        for (Indexado<Regiao> territorio : Indexamento.indexe(a.getRegioes())) {


            DKG documento_territorio = DKG.PARSER(MaterializedView10K.get(local_territorios, territorio.index()));

            DKGObjeto territorio_objeto = documento_territorio.unicoObjeto("Territorio");
            int urnas = territorio_objeto.identifique("Urnas").getInteiro(0);
            int cicadao_obrigados = territorio_objeto.identifique("IdCO").getInteiro(0);


            mTerritorios.add(new ApuracaoEmTempoRealTerritorio(territorio.get().getNome(), urnas, cicadao_obrigados));
        }
    }

    public boolean tem_mais_urnas() {
        return urnas_apuradas < urnas_da_eleicao;
    }

    public void mais() {


        if (urnas_apuradas < urnas_da_eleicao) {


            DKG urna_documento = DKG.PARSER(MaterializedView10K.get(mLocalUrnas, urnas_apuradas));
            DKGObjeto urna_objeto = urna_documento.unicoObjeto("Urna");

            String urna_da_cidade = urna_objeto.identifique("Cidade").getValor();

            System.out.println("\t -->> Abrindo Urna :: " + urna_objeto.identifique("Codigo").getValor());

            String territorio = r.getRegiaoDaCidade(urna_da_cidade);


            ApuracaoEmTempoRealTerritorio territorio_apuracao = null;

            System.out.println("\t -->> Territorio :: " + territorio + " ---- Cidade :: " + urna_da_cidade);

            for (ApuracaoEmTempoRealTerritorio proc_territorio : mTerritorios) {
                if (proc_territorio.getTerritorio().contentEquals(territorio)) {
                    territorio_apuracao = proc_territorio;
                    //  territorio_apuracao.urnas_apuradas += 1;
                    break;
                }
            }

            // System.out.println(urna_documento.toString());

            presenca += urna_objeto.identifique("Presenca").getInteiro(0);
            presenca_obrigatoria += urna_objeto.identifique("PresencaObrigatoria").getInteiro(0);
            abstensao += urna_objeto.identifique("Abstensao").getInteiro(0);
            votos += urna_objeto.identifique("Votos").getInteiro(0);

            for (DKGObjeto obj_cargo : urna_objeto.getObjetos()) {

                String sCargo = obj_cargo.identifique("Cargo").getValor();

                for (DKGObjeto obj_cargo_candidato : obj_cargo.getObjetos()) {

                    String sCandidato_nome = obj_cargo_candidato.identifique("Nome").getValor();
                    int sCandidato_votos = obj_cargo_candidato.identifique("Votos").getInteiro(0);

                    Apuracao.apurar_cargo(cargos, sCargo, sCandidato_nome, sCandidato_votos);

                    Apuracao.apurar_cargo(territorio_apuracao.getCargos(), sCargo, sCandidato_nome, sCandidato_votos);


                }
            }

            if (urnas_apuradas >= mostrar_em) {
                mostrar_em += Aleatorio.aleatorio_entre(10, 30);

                exibir();

            }

            urnas_apuradas += 1;
        }

    }

    public void exibir() {

        System.out.println("-------------------- APURACAO ----- -----------------------");

        System.out.println("Apuracao               : " + urnas_apuradas + " urnas de " + urnas_da_eleicao);
        System.out.println("Cidadaos Obrigatorios  : " + mCidadaos_obrigados);
        System.out.println("Presenca               : " + presenca);
        System.out.println("Presenca Obrigatoria   : " + presenca_obrigatoria);
        System.out.println("Abstenções             : " + (mCidadaos_obrigados - presenca_obrigatoria));
        System.out.println("Abstenções das Urnas   : " + abstensao);
        System.out.println("Votos                  : " + votos);

        for (CargoApuracao cargo : cargos) {

            System.out.println("--------------------- " + cargo.getNome() + " ----------------------");

            int total_votos = 0;

            for (CandidatoApuracao candidato : Apuracao.ordenar_mais_votados(cargo.getCandidatos())) {
                System.out.println("\t - " + candidato.getNome() + " = " + candidato.getVotos());
                total_votos += candidato.getVotos();
            }

            System.out.println("");
            System.out.println("\t - TOTAL = " + total_votos);


        }

        for (ApuracaoEmTempoRealTerritorio territorio : mTerritorios) {

            System.out.println("Territorio : " + territorio.getTerritorio() + " :: " + territorio.urnas_apuradas + " de " + territorio.total_de_urnas);

            int total_votos = 0;

            for (CargoApuracao cargo : territorio.getCargos()) {
                if (cargo.getNome().contentEquals("Presidencia")) {

                    for (CandidatoApuracao candidato : Apuracao.ordenar_mais_votados(cargo.getCandidatos())) {
                        System.out.println("\t - " + candidato.getNome() + " = " + candidato.getVotos());
                        total_votos += candidato.getVotos();
                    }


                }
            }

            System.out.println("");
            System.out.println("\t - TOTAL = " + total_votos);

        }

    }


}
