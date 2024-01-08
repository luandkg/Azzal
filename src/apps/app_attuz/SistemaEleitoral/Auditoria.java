package apps.app_attuz.SistemaEleitoral;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Ferramentas.Local;
import libs.luan.Indexado;
import libs.luan.Indexamento;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Lista;
import libs.materializedview.HiperMaterializedView10K;

import java.util.ArrayList;

public class Auditoria {

    public static void realizar_apuracao(String local_populacao) {

        Arkazz eArkazz = new Arkazz();

        Lista<Local> cidades = eArkazz.getCidades();
        ArrayList<Urna> urnas = new ArrayList<Urna>();

        ArrayList<CargoApuracao> cargos = new ArrayList<CargoApuracao>();

        cargos.add(new CargoApuracao("Presidencia"));
        cargos.add(new CargoApuracao("Senado"));
        cargos.add(new CargoApuracao("Secretaria"));

        for (CargoApuracao ca : cargos) {
            ca.criar_candidato("BRANCO");
            ca.criar_candidato("NULO");
        }


        int HORARIO_COMECAR_ARCO = 3;
        int HORARIO_COMECAR_ITTAS = 0;

        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        int presenca = 0;
        int abstencoes = 0;

        int cidadaos_obrigatorios = 0;
        int presenca_obrigatoria = 0;

        // Construir perfil das pessoas
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
                    vai_votar = true;
                    cidadaos_obrigatorios += 1;
                }

                if (idade > isObrigatorio) {

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");
                    vai_votar = pessoa_politica.identifique("VouVotar").isValor("SIM");

                }

                if (vai_votar) {

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");
                    DKGObjeto politica_eleicao = pessoa_politica.unicoObjeto("Eleicao");


                    if (politica_eleicao.identifique("Votou").isValor("SIM")) {

                        auditar_cargo(cargos, "Presidencia", politica_eleicao.identifique("Presidencia").getValor());
                        auditar_cargo(cargos, "Senado", politica_eleicao.identifique("Senado").getValor());
                        auditar_cargo(cargos, "Secretaria", politica_eleicao.identifique("Secretaria").getValor());

                        presenca += 1;

                        if (idade >= isAdulto && idade <= isObrigatorio) {
                            presenca_obrigatoria += 1;
                        }

                    } else {
                        if (idade >= isAdulto && idade <= isObrigatorio) {
                            abstencoes += 1;
                        }
                    }


                }

            }


        }

        System.out.println("----------------- AUDITORIA --------------------------");
        System.out.println("Cidadaos Obrigatorios   : " + cidadaos_obrigatorios);
        System.out.println("Presenca                : " + presenca);
        System.out.println("Presenca Obrigatoria    : " + presenca_obrigatoria);
        System.out.println("Abstenções              : " + abstencoes);


        for (CargoApuracao cargo : cargos) {

            System.out.println("-------------------------------------------");
            System.out.println("Cargo : " + cargo.getNome());
            for (CandidatoApuracao candidato : ordenar_mais_votados(cargo.getCandidatos())) {
                System.out.println("\t - " + candidato.getNome() + " = " + candidato.getVotos());
            }

        }

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


    public static void auditar_cargo(ArrayList<CargoApuracao> cargos, String eNome, String eCandidato) {

        boolean existe = false;
        for (CargoApuracao c : cargos) {
            if (c.getNome().contentEquals(eNome)) {
                existe = true;
                c.votar(eCandidato);
                break;
            }
        }
        if (!existe) {
            CargoApuracao c = new CargoApuracao(eNome);
            c.votar(eCandidato);
            cargos.add(c);
        }


    }


}
