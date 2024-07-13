package apps.app_attuz.SistemaEleitoral;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Ferramentas.Local;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Indexado;
import libs.luan.Indexamento;
import libs.luan.Lista;
import libs.materializedview.HiperMaterializedView10K;
import libs.materializedview.MaterializedView;
import libs.materializedview.MaterializedView10K;

import java.util.ArrayList;

public class Apuracao {

    public static void realizar_apuracao_das_urnas(String local_populacao, String local_urnas) {


        ArrayList<CargoApuracao> cargos = new ArrayList<CargoApuracao>();

        cargos.add(new CargoApuracao("Presidencia"));
        cargos.add(new CargoApuracao("Senado"));
        cargos.add(new CargoApuracao("Secretaria"));

        for (CargoApuracao ca : cargos) {
            ca.criar_candidato("BRANCO");
            ca.criar_candidato("NULO");
        }

        int contagem = MaterializedView.contagem(local_urnas);

        int presenca = 0;
        int presenca_obrigatoria = 0;

        for (int urna = 0; urna < contagem; urna++) {

            DKG urna_documento = DKG.PARSER(MaterializedView10K.get(local_urnas, urna));
            DKGObjeto urna_objeto = urna_documento.unicoObjeto("Urna");


            System.out.println("Abrindo Urna :: " + urna_objeto.identifique("Codigo").getValor());

            presenca += urna_objeto.identifique("Presenca").getInteiro(0);
            presenca_obrigatoria += urna_objeto.identifique("PresencaObrigatoria").getInteiro(0);


            for (DKGObjeto obj_cargo : urna_objeto.getObjetos()) {
                for (DKGObjeto obj_cargo_candidato : obj_cargo.getObjetos()) {
                    apurar_cargo(cargos, obj_cargo.identifique("Cargo").getValor(), obj_cargo_candidato.identifique("Nome").getValor(), obj_cargo_candidato.identifique("Votos").getInteiro(0));
                }
            }


        }

        System.out.println("Realizando contagem de cidadão obrigados a votar");
        Arkazz eArkazz = new Arkazz();

        Lista<Local> cidades = eArkazz.getCidades();

        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        int cidadaos_obrigados = 0;

        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());


            for (int pessoa = 0; pessoa < tamanho_populacao; pessoa++) {


                DKG pessoa_corrente = DKG.PARSER(HiperMaterializedView10K.get(local_populacao, cidade.index(), pessoa));

                DKGObjeto pessoa_objeto = pessoa_corrente.unicoObjeto("Pessoa");

                String nome = pessoa_objeto.identifique("Nome").getValor();
                String sexo = pessoa_objeto.identifique("Sexo").getValor();
                int ddn = pessoa_objeto.identifique("DDN").getInteiro(0);

                int sessao = pessoa_objeto.identifique("Sessao").getInteiro(0);
                int zona = pessoa_objeto.identifique("Zona").getInteiro(0);

                int idade = ANO_ATUAL - ddn;


                boolean vai_votar = false;
                if (idade >= isAdulto && idade <= isObrigatorio) {
                    cidadaos_obrigados += 1;
                }
            }
        }


        System.out.println("-------------------- APURACAO -----------------------");

        System.out.println("Presenca               : " + presenca);
        System.out.println("Presenca Obrigatoria   : " + presenca_obrigatoria);
        System.out.println("Abstenções             : " + (cidadaos_obrigados - presenca_obrigatoria));


        for (CargoApuracao cargo : cargos) {

            System.out.println("--------------------- " + cargo.getNome() + " ----------------------");

            for (CandidatoApuracao candidato : ordenar_mais_votados(cargo.getCandidatos())) {
                System.out.println("\t - " + candidato.getNome() + " = " + candidato.getVotos());
            }


        }


    }


    public static void realizar_apuracao_das_urnas_tempo_real(int cidadaos_obrigados, String local_territorios, String local_urnas) {


        ApuracaoEmTempoReal tempo_real = new ApuracaoEmTempoReal(cidadaos_obrigados, local_territorios, local_urnas);


        while (tempo_real.tem_mais_urnas()) {
            tempo_real.mais();
        }

        tempo_real.exibir();


    }


    public static ArrayList<CandidatoApuracao> ordenar_mais_votados(ArrayList<CandidatoApuracao> entradas) {

        int n = entradas.size();
        CandidatoApuracao temp = null;

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {

                if (entradas.get(j - 1).getVotos() <= (entradas.get(j).getVotos())) {
                    temp = entradas.get(j - 1);
                    entradas.set(j - 1, entradas.get(j));
                    entradas.set(j, temp);

                }

            }
        }
        return entradas;
    }


    public static void apurar_cargo(ArrayList<CargoApuracao> cargos, String eNome, String eCandidato, int votos) {

        boolean existe = false;
        for (CargoApuracao c : cargos) {
            if (c.getNome().contentEquals(eNome)) {
                existe = true;
                c.votar_quantidade(eCandidato, votos);
                break;
            }
        }
        if (!existe) {
            CargoApuracao c = new CargoApuracao(eNome);
            c.votar_quantidade(eCandidato, votos);
            cargos.add(c);
        }


    }

}
