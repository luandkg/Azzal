package apps.app_attuz.SistemaEleitoral;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Ferramentas.Local;
import apps.app_attuz.Regiao.Regiao;
import apps.app_attuz.Regiao.Regionalizador;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.luan.Aleatorio;
import libs.luan.Indexado;
import libs.luan.Indexamento;
import libs.luan.Lista;
import libs.materializedview.HiperMaterializedView10K;
import libs.materializedview.MaterializedView10K;

import java.util.ArrayList;

public class SistemaEleitoral {

    public static int cidadaos_obrigados_a_votar(String local_populacao) {

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


                if (idade >= isAdulto && idade <= isObrigatorio) {
                    cidadaos_obrigados += 1;
                }


            }
        }

        return cidadaos_obrigados;
    }

    public static void direitos_politicos(String LOCAL, String local_populacao, String local_territorios) {

        Arkazz eArkazz = new Arkazz();


        int idade_maxima = 80;
        int ano_de_nascimento = 2022 - idade_maxima;
        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        Regionalizador eRegionalizador = new Regionalizador(LOCAL);

        CargoApuracao territorios = new CargoApuracao("Territorios");

        // Construir perfil das pessoas
        for (Indexado<Local> cidade : Indexamento.indexe(eArkazz.getCidades())) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());

            for (int pessoa = 0; pessoa < tamanho_populacao; pessoa++) {

                String dados = HiperMaterializedView10K.get(local_populacao, cidade.index(), pessoa);

                DKG pessoa_corrente = DKG.PARSER(dados);

                DKGObjeto pessoa_objeto = pessoa_corrente.unicoObjeto("Pessoa");

                String nome = pessoa_objeto.identifique("Nome").getValor();

                int ddn = pessoa_objeto.identifique("DDN").getInteiro(0);
                int idade = ANO_ATUAL - ddn;

                if (idade >= isAdulto && idade <= isObrigatorio) {

                    String cidade_de_votacao = pessoa_objeto.identifique("Cidade").getValor();
                    String territorio = eRegionalizador.getRegiaoDaCidade(cidade_de_votacao);

                    territorios.votar(territorio);

                }

            }

        }


        for (Indexado<Regiao> territorio : Indexamento.indexe(eArkazz.getRegioes())) {

            DKG documento_territorio = DKG.PARSER(MaterializedView10K.get(local_territorios, territorio.index()));

            DKGObjeto territorio_objeto = documento_territorio.unicoObjeto("Territorio");
            int cicadao_obrigados = 0;

            for (CandidatoApuracao c : territorios.getCandidatos()) {
                if (c.getNome().contentEquals(territorio.get().getNome())) {
                    cicadao_obrigados = c.getVotos();
                    break;
                }
            }

            territorio_objeto.identifique("IdCO").getInteiro(cicadao_obrigados);

            MaterializedView10K.set(local_territorios, territorio.index(), documento_territorio.toString());
        }


    }

    public static void tomar_decisao(String local_populacao, Eleicao eleicao) {

        eleicao.embaralhar();

        Arkazz eArkazz = new Arkazz();
        Lista<Local> cidades = eArkazz.getCidades();


        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        // Construir perfil das pessoas
        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());


            for (int pessoa = 0; pessoa < tamanho_populacao; pessoa++) {


                String dados = HiperMaterializedView10K.get(local_populacao, cidade.index(), pessoa);

                DKG pessoa_corrente = DKG.PARSER(dados);

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
                }

                if (idade > isObrigatorio) {

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");
                    String vou_votar = pessoa_politica.identifique("VouVotar").getValor();

                    if (vou_votar.contentEquals("SIM")) {
                        System.out.println("\t - " + nome + " :: Quero votar mesmo tendo mais que " + idade);
                        vai_votar = true;
                    } else if (vou_votar.contentEquals("NAO")) {
                        System.out.println("\t - " + nome + " :: Não vou votar porque tenho mais que " + idade);
                    } else {

                        System.out.println("\t - " + nome + " :: Tenho mais que " + idade + " estou indeciso");

                        String escolha_se_vou = Aleatorio.aleatorio_escolha("SIM", "NAO", "INDECISO");

                        pessoa_politica.identifique("VouVotar").setValor(escolha_se_vou);

                        System.out.println("\t - " + nome + " :: Tenho mais que " + idade + " pensando : " + escolha_se_vou);


                        HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());

                        vou_votar = pessoa_politica.identifique("VouVotar").getValor();
                        if (vou_votar.contentEquals("SIM")) {
                            vai_votar = true;
                        }
                    }

                }

                if (vai_votar) {


                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");

                    DKGObjeto pessoa_politica_presidencia = pessoa_politica.unicoObjeto("Presidencia");
                    DKGObjeto pessoa_politica_senado = pessoa_politica.unicoObjeto("Senado");
                    DKGObjeto pessoa_politica_secretaria = pessoa_politica.unicoObjeto("Secretaria");


                    int senso_de_cidadania = pessoa_politica.identifique("SensoDeCidadania").getInteiro(0);

                    if (senso_de_cidadania == 0) {
                        if (Aleatorio.aleatorio(100) > 80) {
                            senso_de_cidadania = Aleatorio.aleatorio_entre(50, 100);
                        } else {
                            senso_de_cidadania = Aleatorio.aleatorio_entre(0, 50);
                        }
                    } else {

                        if (senso_de_cidadania >= 50) {

                            String candidato_presidente = Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getPresidencia()));

                            pessoa_politica_presidencia.identifique("Escolhi").setValor("SIM");
                            pessoa_politica_presidencia.identifique("Candidato").setValor(candidato_presidente);
                            pessoa_politica_presidencia.identifique("ModoDeDecisao").setValor("SensoDeCidadania");

                        }

                        if (senso_de_cidadania >= 50) {

                            String candidato_senado = Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getSenado()));

                            pessoa_politica_senado.identifique("Escolhi").setValor("SIM");
                            pessoa_politica_senado.identifique("Candidato").setValor(candidato_senado);
                            pessoa_politica_senado.identifique("ModoDeDecisao").setValor("SensoDeCidadania");

                        }

                        if (senso_de_cidadania >= 50) {

                            String candidato_secretaria = Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getSecretaria()));

                            pessoa_politica_secretaria.identifique("Escolhi").setValor("SIM");
                            pessoa_politica_secretaria.identifique("Candidato").setValor(candidato_secretaria);
                            pessoa_politica_secretaria.identifique("ModoDeDecisao").setValor("SensoDeCidadania");

                        }

                    }

                    pessoa_politica.identifique("SensoDeCidadania").setInteiro(senso_de_cidadania);

                    int devo_escolher_presidente = Aleatorio.aleatorio(100);


                    if (!pessoa_politica_presidencia.identifique("Escolhi").isValor("SIM") && devo_escolher_presidente >= 50) {
                        if (!pessoa_politica_presidencia.identifique("Escolhi").isValor("SIM")) {
                            pessoa_politica_presidencia.identifique("Candidato").setValor(Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getPresidencia())));
                            pessoa_politica_presidencia.identifique("Escolhi").setValor("SIM");
                            pessoa_politica_presidencia.identifique("ModoDeDecisao").setValor("Aleatoria");
                        }
                    }

                    int devo_escolher_senador = Aleatorio.aleatorio(100);


                    if (!pessoa_politica_senado.identifique("Escolhi").isValor("SIM") && devo_escolher_senador >= 50) {
                        if (!pessoa_politica_senado.identifique("Escolhi").isValor("SIM")) {
                            pessoa_politica_senado.identifique("Candidato").setValor(Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getSenado())));
                            pessoa_politica_senado.identifique("Escolhi").setValor("SIM");
                            pessoa_politica_senado.identifique("ModoDeDecisao").setValor("Aleatoria");
                        }
                    }

                    int devo_escolher_secretario = Aleatorio.aleatorio(100);


                    if (!pessoa_politica_secretaria.identifique("Escolhi").isValor("SIM") && devo_escolher_secretario >= 50) {
                        if (!pessoa_politica_secretaria.identifique("Escolhi").isValor("SIM")) {
                            pessoa_politica_secretaria.identifique("Candidato").setValor(Aleatorio.escolha_um(Eleicao.fazer_lista_de_nomes(eleicao.getSecretaria())));
                            pessoa_politica_secretaria.identifique("Escolhi").setValor("SIM");
                            pessoa_politica_secretaria.identifique("ModoDeDecisao").setValor("Aleatoria");
                        }
                    }

                    HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());


                    System.out.println("------------------------------");
                    System.out.println("\t - Nome           : " + nome);
                    System.out.println("\t - Senso          : " + pessoa_politica.identifique("SensoDeCidadania").getInteiro(0));


                    if (pessoa_politica_presidencia.identifique("Escolhi").isValor("SIM")) {
                        System.out.println("\t - ModoDeDecisao  : " + pessoa_politica_presidencia.identifique("ModoDeDecisao").getValor());
                        System.out.println("\t - Presidencia    : " + pessoa_politica_presidencia.identifique("Candidato").getValor());
                    }
                    if (pessoa_politica_senado.identifique("Escolhi").isValor("SIM")) {
                        System.out.println("\t - ModoDeDecisao  : " + pessoa_politica_senado.identifique("ModoDeDecisao").getValor());
                        System.out.println("\t - Senado         : " + pessoa_politica_senado.identifique("Candidato").getValor());
                    }
                    if (pessoa_politica_secretaria.identifique("Escolhi").isValor("SIM")) {
                        System.out.println("\t - ModoDeDecisao  : " + pessoa_politica_secretaria.identifique("ModoDeDecisao").getValor());
                        System.out.println("\t - Secretaria     : " + pessoa_politica_secretaria.identifique("Candidato").getValor());
                    }
                }

            }


        }


    }


    public static void realizar_debate(String local_populacao, String cargo_politico, ArrayList<String> candidatos_nomes) {


        System.out.println("------------------ DEBATE ---------------------");

        ArrayList<Candidato> candidatos = new ArrayList<Candidato>();

        ArrayList<Candidato> candidatos_p1 = new ArrayList<Candidato>();
        ArrayList<Candidato> candidatos_p2 = new ArrayList<Candidato>();
        ArrayList<Candidato> candidatos_p3 = new ArrayList<Candidato>();

        int inicio = 50;
        int valor = inicio;

        System.out.println("------- BLOCO 1 ---------");

        for (String c : candidatos_nomes) {
            if (valor > 0) {
                int vc = Aleatorio.aleatorio(valor);
                valor -= vc;
                candidatos.add(new Candidato("", vc, c));
                System.out.println("\t - " + c + " -->> Popularidade = " + vc);

            }

        }

        System.out.println("------- BLOCO 2 ---------");

        int somando = 0;
        for (Candidato c : candidatos) {
            somando += c.getNumero();
        }

        valor = 20;


        for (Candidato c : candidatos) {
            if (valor > 0) {
                int vc = Aleatorio.aleatorio(valor);
                if (Aleatorio.aleatorio(100) > 20) {
                    valor -= vc;
                    c.mudarNumero(c.getNumero() + vc);
                } else {
                    valor += vc;
                    c.mudarNumero(c.getNumero() - vc);
                }

                System.out.println("\t - " + c.getNome() + " -->> Popularidade = " + c.getNumero());
            }

        }


        somando = 0;
        for (Candidato c : candidatos) {
            somando += c.getNumero();
        }

        System.out.println("------- BLOCO 3 ---------");

        valor = 20;

        for (Candidato c : candidatos) {
            if (valor > 0) {

                int vc = Aleatorio.aleatorio(valor);

                if (Aleatorio.aleatorio(100) > 30) {
                    valor -= vc;
                    c.mudarNumero(c.getNumero() + vc);
                } else {
                    valor += vc;
                    c.mudarNumero(c.getNumero() - vc);
                }

                System.out.println("\t - " + c.getNome() + " -->> Popularidade = " + c.getNumero());
            }

        }

        for (Candidato c : candidatos) {

            if (c.getNumero() > 0) {
                assistindo_debate(local_populacao, cargo_politico, c.getNumero(), c.getNome());
            }

        }

        System.out.println("Total :: " + somando);
    }

    public static void assistindo_debate(String local_populacao, String cargo_politico, int chance, String canditado) {

        Arkazz eArkazz = new Arkazz();
        Lista<Local> cidades = eArkazz.getCidades();


        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        // Construir perfil das pessoas
        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());


            for (int pessoa = 0; pessoa < tamanho_populacao; pessoa++) {


                String dados = HiperMaterializedView10K.get(local_populacao, cidade.index(), pessoa);

                DKG pessoa_corrente = DKG.PARSER(dados);

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
                }

                if (idade > isObrigatorio) {

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");
                    String vou_votar = pessoa_politica.identifique("VouVotar").getValor();

                    if (vou_votar.contentEquals("SIM")) {
                        vai_votar = true;
                    }

                }

                if (vai_votar) {


                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");

                    DKGObjeto pessoa_politica_cargo = pessoa_politica.unicoObjeto(cargo_politico);

                    int senso_de_cidadania = pessoa_politica.identifique("SensoDeCidadania").getInteiro(0);

                    int estou_assistindo = Aleatorio.aleatorio(100);
                    int devo = Aleatorio.aleatorio(100);

                    if (estou_assistindo >= 50) {

                        System.out.println("Assistindo debate :: " + nome + " em " + estou_assistindo + " :: " + chance + " --- " + devo);
                        System.out.println("\t - MODO : " + pessoa_politica_cargo.identifique("Escolhi").getValor());

                        System.out.println("\t - Não escolhi, sera que devo votar nesse : " + devo);

                        int em_chance = 100 - chance;


                        if (devo >= em_chance) {
                            System.out.println("\t - Vou votar : " + canditado);

                            pessoa_politica_cargo.identifique("Escolhi").setValor("SIM");
                            pessoa_politica_cargo.identifique("Candidato").setValor(canditado);
                            pessoa_politica_cargo.identifique("ModoDeDecisao").setValor("Debate");

                            HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());

                        } else {
                            System.out.println("\t - Não Vou votar : " + canditado);
                        }


                    }


                }

            }


        }


    }

    public static void indecisao(String local_populacao, Eleicao eleicao) {

        Arkazz eArkazz = new Arkazz();
        Lista<Local> cidades = eArkazz.getCidades();


        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        int indecisos = 0;
        int decididos = 0;

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
                String cidadania = "";

                if (idade >= isAdulto && idade <= isObrigatorio) {
                    vai_votar = true;
                    cidadania = "Voto Obrigatório";
                }

                if (idade > isObrigatorio) {

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");
                    String vou_votar = pessoa_politica.identifique("VouVotar").getValor();

                    if (vou_votar.contentEquals("SIM")) {
                        cidadania = "Quero votar mesmo tendo mais que " + idade;
                        vai_votar = true;
                    } else if (vou_votar.contentEquals("NAO")) {
                        cidadania = "Não vou votar porque tenho mais que " + idade;
                    } else {

                        cidadania = "Tenho mais que " + idade + " pensando : " + pessoa_politica.identifique("VouVotar").getValor();
                        vai_votar = pessoa_politica.identifique("VouVotar").isValor("SIM");

                    }

                }

                if (vai_votar) {


                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");

                    DKGObjeto pessoa_politica_presidencia = pessoa_politica.unicoObjeto("Presidencia");
                    DKGObjeto pessoa_politica_senado = pessoa_politica.unicoObjeto("Senado");
                    DKGObjeto pessoa_politica_secretaria = pessoa_politica.unicoObjeto("Secretaria");

                    //    System.out.println("-->> " + nome + " :: " + pessoa_politica.identifique("SensoDeCidadania").getInteiro(0));

                    if (pessoa_politica.identifique("SensoDeCidadania").getInteiro(0) < 50) {


                        if (Aleatorio.aleatorio(100) > 70) {
                            pessoa_politica_presidencia.identifique("Escolhi").setValor("NAO");
                            HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());
                        }

                        if (Aleatorio.aleatorio(100) > 70) {
                            pessoa_politica_senado.identifique("Escolhi").setValor("NAO");
                            HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());
                        }

                        if (Aleatorio.aleatorio(100) > 70) {
                            pessoa_politica_secretaria.identifique("Escolhi").setValor("NAO");
                            HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());
                        }

                    }


                }

            }


        }

    }

    public static void limpar_pensamento_politico(String local_populacao) {

        Arkazz eArkazz = new Arkazz();
        Lista<Local> cidades = eArkazz.getCidades();


        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        int indecisos = 0;
        int decididos = 0;

        // Construir perfil das pessoas
        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());


            for (int pessoa = 0; pessoa < tamanho_populacao; pessoa++) {


                DKG pessoa_corrente = DKG.PARSER(HiperMaterializedView10K.get(local_populacao, cidade.index(), pessoa));

                DKGObjeto pessoa_objeto = pessoa_corrente.unicoObjeto("Pessoa");

                String nome = pessoa_objeto.identifique("Nome").getValor();
                int ddn = pessoa_objeto.identifique("DDN").getInteiro(0);

                int idade = ANO_ATUAL - ddn;

                if (idade >= isAdulto) {

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");

                    pessoa_politica.limpar();

                    HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());

                }


            }


        }

    }

    public static void visualizar_pensamento_politico(String local_populacao, Eleicao eleicao) {

        Arkazz eArkazz = new Arkazz();
        Lista<Local> cidades = eArkazz.getCidades();


        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        int presidencia_indecisos = 0;
        int presidencia_decididos = 0;

        int senado_indecisos = 0;
        int senado_decididos = 0;

        int todos = 0;

        // Construir perfil das pessoas
        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());


            for (int pessoa = 0; pessoa < tamanho_populacao; pessoa++) {


                String dados = HiperMaterializedView10K.get(local_populacao, cidade.index(), pessoa);

                DKG pessoa_corrente = DKG.PARSER(dados);

                DKGObjeto pessoa_objeto = pessoa_corrente.unicoObjeto("Pessoa");

                String nome = pessoa_objeto.identifique("Nome").getValor();
                String sexo = pessoa_objeto.identifique("Sexo").getValor();
                int ddn = pessoa_objeto.identifique("DDN").getInteiro(0);

                int sessao = pessoa_objeto.identifique("Sessao").getInteiro(0);
                int zona = pessoa_objeto.identifique("Zona").getInteiro(0);

                int idade = ANO_ATUAL - ddn;


                boolean vai_votar = false;
                String cidadania = "";

                if (idade >= isAdulto && idade <= isObrigatorio) {
                    vai_votar = true;
                    cidadania = "Voto Obrigatório";
                }

                if (idade > isObrigatorio) {

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");
                    String vou_votar = pessoa_politica.identifique("VouVotar").getValor();

                    if (vou_votar.contentEquals("SIM")) {
                        cidadania = "Quero votar mesmo tendo mais que " + idade;
                        vai_votar = true;
                    } else if (vou_votar.contentEquals("NAO")) {
                        cidadania = "Não vou votar porque tenho mais que " + idade;
                    } else {

                        cidadania = "Tenho mais que " + idade + " pensando : " + pessoa_politica.identifique("VouVotar").getValor();
                        vai_votar = pessoa_politica.identifique("VouVotar").isValor("SIM");

                    }

                }

                if (vai_votar) {

                    System.out.println(pessoa_corrente.toString());

                    DKGObjeto pessoa_politica = pessoa_objeto.unicoObjeto("Politicamente");

                    DKGObjeto pessoa_politica_presidencia = pessoa_politica.unicoObjeto("Presidencia");
                    DKGObjeto pessoa_politica_senado = pessoa_politica.unicoObjeto("Senado");


                    System.out.println("-------------------------------------------------------------");
                    System.out.println("\t - Nome               : " + nome);
                    System.out.println("\t - Cidadania          : " + cidadania);
                    System.out.println("\t - Senso              : " + pessoa_politica.identifique("SensoDeCidadania").getInteiro(0));
                    System.out.println("\t - ModoDeDecisao      : " + pessoa_politica.identifique("ModoDeDecisao").getValor());
                    System.out.println("\t - Escolhi Presidente : " + pessoa_politica_presidencia.identifique("Escolhi").getValor());
                    System.out.println("\t - Escolhi Senado     : " + pessoa_politica_senado.identifique("Escolhi").getValor());

                    if (pessoa_politica_presidencia.identifique("Escolhi").isValor("SIM")) {
                        System.out.println("\t - Presidente    : " + pessoa_politica_presidencia.identifique("Presidente").getValor());
                        presidencia_decididos += 1;
                    } else {
                        presidencia_indecisos += 1;
                    }

                    if (pessoa_politica_senado.identifique("Escolhi").isValor("SIM")) {
                        System.out.println("\t - Senado        : " + pessoa_politica_senado.identifique("Presidente").getValor());
                        senado_decididos += 1;
                    } else {
                        senado_indecisos += 1;
                    }

                    //System.out.println();

                    todos += 1;
                }

            }


        }

        System.out.println("");
        System.out.println("-------------------------------------");
        System.out.println("\t - Todos : " + todos);
        System.out.println("\t - Presidente Escolheram : " + presidencia_decididos);
        System.out.println("\t - Presidente Idencisos  : " + presidencia_indecisos);
        System.out.println("\t - Senado Escolheram : " + senado_decididos);
        System.out.println("\t - Senado Idencisos  : " + senado_indecisos);

    }

}
