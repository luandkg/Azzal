package apps.app_attuz.SistemaEleitoral;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Ferramentas.Local;
import libs.luan.Aleatorio;
import libs.luan.Indexado;
import libs.luan.Indexamento;
import libs.luan.fmt;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.materializedview.HiperMaterializedView10K;
import libs.materializedview.MaterializedView10K;

import java.util.ArrayList;

public class Votacao {

    private static Urna urna_do_cidadao(ArrayList<Urna> urnas, int HORARIO_COMECAR_ARCO, int HORARIO_COMECAR_ITTAS, String eCidade, String eZona, String eSessao) {
        String codigo = eCidade + "::" + eZona + "::" + eSessao;
        boolean enc = false;
        Urna ret = null;

        for (Urna u : urnas) {
            if (u.getUnicidade().contentEquals(codigo)) {
                ret = u;
                enc = true;
                break;
            }
        }
        if (!enc) {
            ret = new Urna(eCidade, eZona, eSessao);
            ret.setComecar(HORARIO_COMECAR_ARCO, HORARIO_COMECAR_ITTAS);
            ret.setFila(Aleatorio.alatorio_entre(5, 10));
            urnas.add(ret);
        }
        return ret;
    }

    public static void realizar_eleicao(String local_populacao, String local_urnas, Eleicao eleicao) {

        eleicao.embaralhar();
        Arkazz eArkazz = new Arkazz();

        ArrayList<Local> cidades = eArkazz.getCidades();
        ArrayList<Urna> sessoes_de_votacao = new ArrayList<Urna>();

        int HORARIO_COMECAR_ARCO = 3;
        int HORARIO_COMECAR_ITTAS = 0;

        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

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
                boolean vai_votar_obrigado = false;

                if (idade >= isAdulto && idade <= isObrigatorio) {
                    vai_votar = true;
                    vai_votar_obrigado = true;
                }

                if (idade > isObrigatorio) {

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");
                    vai_votar = pessoa_politica.identifique("VouVotar").isValor("SIM");

                }

                if (vai_votar) {

                    Urna urna = urna_do_cidadao(sessoes_de_votacao, HORARIO_COMECAR_ARCO, HORARIO_COMECAR_ITTAS, String.valueOf(cidade.get().getNome()), String.valueOf(zona), String.valueOf(sessao));

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");
                    DKGObjeto politica_eleicao = pessoa_politica.unicoObjeto("Eleicao");

                    politica_eleicao.limpar();

                    boolean estou_votando = Aleatorio.aleatorio(100) > 10;
                    //  estou_votando = true;

                    if (estou_votando) {

                        DKGObjeto pessoa_politica_presidencia = pessoa_politica.unicoObjeto("Presidencia");
                        DKGObjeto pessoa_politica_senado = pessoa_politica.unicoObjeto("Senado");
                        DKGObjeto pessoa_politica_secretaria = pessoa_politica.unicoObjeto("Secretaria");


                        //    System.out.println("-->> " + nome + " :: " + pessoa_politica.identifique("SensoDeCidadania").getInteiro(0));

                        String sPresidencia = "NULO";
                        String sSenado = "NULO";
                        String sSecretaria = "NULO";

                        if (pessoa_politica_presidencia.identifique("Escolhi").isValor("SIM")) {
                            sPresidencia = pessoa_politica_presidencia.identifique("Candidato").getValor();
                        } else {
                            if (Aleatorio.aleatorio(100) > 70) {
                                if (Aleatorio.aleatorio(100) > 50) {
                                    sPresidencia = "BRANCO";
                                } else {
                                    sPresidencia = "NULO";
                                }
                            } else {
                                sPresidencia = Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getPresidencia()));
                            }
                        }

                        if (pessoa_politica_senado.identifique("Escolhi").isValor("SIM")) {
                            sSenado = pessoa_politica_senado.identifique("Candidato").getValor();
                        } else {
                            if (Aleatorio.aleatorio(100) > 70) {
                                if (Aleatorio.aleatorio(100) > 50) {
                                    sSenado = "BRANCO";
                                } else {
                                    sSenado = "NULO";
                                }
                            } else {
                                sSenado = Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getSenado()));
                            }
                        }

                        if (pessoa_politica_secretaria.identifique("Escolhi").isValor("SIM")) {
                            sSecretaria = pessoa_politica_secretaria.identifique("Candidato").getValor();
                        } else {
                            if (Aleatorio.aleatorio(100) > 70) {
                                if (Aleatorio.aleatorio(100) > 50) {
                                    sSecretaria = "BRANCO";
                                } else {
                                    sSecretaria = "NULO";
                                }
                            } else {
                                sSecretaria = Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getSecretaria()));
                            }
                        }


                        politica_eleicao.identifique("Votou").setValor("SIM");
                        politica_eleicao.identifique("TempoComecou").setValor(urna.getTempo());

                        politica_eleicao.identifique("Presidencia").setValor(sPresidencia);
                        politica_eleicao.identifique("Senado").setValor(sSenado);
                        politica_eleicao.identifique("Secretaria").setValor(sSecretaria);

                        urna.guardar_voto("Presidencia", sPresidencia);
                        urna.guardar_voto("Senado", sSenado);
                        urna.guardar_voto("Secretaria", sSecretaria);

                        urna.votar(Aleatorio.alatorio_entre(5, 20));

                        urna.marcar_presenca();

                        if (vai_votar_obrigado) {
                            urna.marcar_presenca_obrigatoria();
                        }

                        if (urna.nao_tem_ninguem_esperando()) {
                            urna.passar_tempo(Aleatorio.alatorio_entre(10, 30));
                            urna.setFila(Aleatorio.alatorio_entre(5, 10));
                        }

                        politica_eleicao.identifique("TempoTerminou").setValor(urna.getTempo());


                    } else {
                        politica_eleicao.identifique("Votou").setValor("NAO");
                        urna.marcar_abstensao();

                    }

                    HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());

                }

            }


        }

        int contagem = MaterializedView10K.contagem(local_urnas);

        if (contagem < sessoes_de_votacao.size()) {
            MaterializedView10K.construir(local_urnas, sessoes_de_votacao.size());
        }

        int contando_votos = 0;

        System.out.println("--------------------- SESSOES DE VOTACAO ----------------------");
        for (Indexado<Urna> urna : Indexamento.embaralhar(sessoes_de_votacao)) {
            System.out.println("\t - " + fmt.espacar_depois(urna.get().getUnicidade(), 30) + "   -->>   " + urna.get().getTempo() + " :: " + urna.get().getContagemDeVotos());

            DKG urna_documento = new DKG();
            DKGObjeto urna_objeto = urna_documento.unicoObjeto("Urna");

            urna_objeto.identifique("Codigo").setValor(urna.get().getUnicidade());
            urna_objeto.identifique("Cidade").setValor(urna.get().getCidade());

            urna_objeto.identifique("TempoComecou").setValor(urna.get().getTempoComecou());
            urna_objeto.identifique("TempoTerminou").setValor(urna.get().getTempo());

            urna_objeto.identifique("Presenca").setInteiro(urna.get().getPresenca());
            urna_objeto.identifique("PresencaObrigatoria").setInteiro(urna.get().getPresencaObrigatoria());
            urna_objeto.identifique("Abstensao").setInteiro(urna.get().getAbstensao());

            urna_objeto.identifique("Votos").setInteiro(urna.get().getContagemDeVotos());

            for (CargoApuracao cargo : urna.get().getCargos()) {

                DKGObjeto urna_objeto_cargo = urna_objeto.criarObjeto(cargo.getNome());
                urna_objeto_cargo.identifique("Cargo").setValor(cargo.getNome());


                for (CandidatoApuracao candidato : cargo.getCandidatos()) {
                    DKGObjeto urna_objeto_cargo_candidato = urna_objeto_cargo.criarObjeto("Candidato");
                    urna_objeto_cargo_candidato.identifique("Nome").setValor(candidato.getNome());
                    urna_objeto_cargo_candidato.identifique("Votos").setInteiro(candidato.getVotos());
                }

            }


            MaterializedView10K.set(local_urnas, urna.index(), urna_documento.toString());

            //  System.out.println(urna_documento.toString());
            contando_votos += urna.get().getContagemDeVotos();
        }

        System.out.println("Votos Registrados = " + contando_votos);

    }


}
