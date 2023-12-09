package apps.app_attuz.Populacionar;

import apps.app_attuz.Arkazz.Arkazz;
import apps.app_attuz.Ferramentas.Local;
import apps.app_attuz.Regiao.Regiao;
import apps.app_attuz.Sociedade.PessoaNomeadorDeAkkax;
import apps.app_attuz.Regiao.Regionalizador;
import libs.luan.*;
import libs.dkg.DKG;
import libs.dkg.DKGObjeto;
import libs.materializedview.HiperMaterializedView10K;
import libs.materializedview.MaterializedView10K;

import java.util.ArrayList;

public class Populacionar {

    public static void zerar(String local_populacao) {

        HiperMaterializedView10K.construir(local_populacao);

    }

    public static void criar_pessoas(String local_populacao) {

        Arkazz eArkazz = new Arkazz();

        Lista<Local> cidades = eArkazz.getCidades();


        // Construir arquivos das cidades com pessoas em branco
        int minimo = 50;
        int maximo = 160;

        boolean aumentar = false;

        if (aumentar) {
            minimo = 300;
            maximo = 1000;
        }


        if (!HiperMaterializedView10K.isConstruida(local_populacao)) {
            HiperMaterializedView10K.construir(local_populacao);
        }


        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            if (!HiperMaterializedView10K.isColecaoConstruida(local_populacao, cidade.index())) {

                int tamanho_populacao = Aleatorio.alatorio_entre(minimo, maximo);

                System.out.println("Construindo " + cidade.get().getNome() + " com " + tamanho_populacao + " pessoas !");

                HiperMaterializedView10K.organizar_colecao(local_populacao, cidade.index(), tamanho_populacao);

            } else {
                System.out.println("Já Construido " + cidade.get().getNome() + " com " + HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index()) + " pessoas !");
            }


        }

    }


    public static void organizar_pessoas(String LOCAL, String local_territorios, String local_populacao) {

        Arkazz eArkazz = new Arkazz();


        int CPUID = 0;

        int idade_maxima = 80;
        int ano_de_nascimento = 2022 - idade_maxima;
        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        Regionalizador eRegionalizador = new Regionalizador(LOCAL);

        // Construir perfil das pessoas
        for (Indexado<Local> cidade : Indexamento.indexe(eArkazz.getCidades())) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());

            int qq_homem = 0;
            int qq_mulher = 0;

            int qq_crianca = 0;
            int qq_adulto = 0;
            int qq_obrigatorio = 0;

            int idade_menor = 0;
            int idade_maior = 0;

            int sessoes = (tamanho_populacao / (5)) + 1;
            int zonas = (sessoes / 6) + 1;

            int por_sessao = tamanho_populacao / sessoes;
            int indo_sessao = 0;

            int por_zona = sessoes / zonas;
            int indo_zona = 0;

            int sessao_corrente = 1;
            int zona_corrente = 1;


            for (int pessoa = 0; pessoa < tamanho_populacao; pessoa++) {


                String nome = PessoaNomeadorDeAkkax.get();
                String sexo = Aleatorio.aleatorio_escolha("Homem", "Mulher");
                int ddn = Aleatorio.aleatorio_a_partir(ano_de_nascimento, idade_maxima);

                DKG pessoa_corrente = new DKG();
                DKGObjeto pessoa_objeto = pessoa_corrente.unicoObjeto("Pessoa");
                pessoa_objeto.identifique("ID", pessoa);
                pessoa_objeto.identifique("CPUID", CPUID);

                pessoa_objeto.identifique("Cidade", cidade.get().getNome());


                String regiao = eRegionalizador.getRegiao(cidade.get().getX(), cidade.get().getY());


                pessoa_objeto.identifique("Regiao", regiao);
                //System.out.println("Cidade " + cidade.getNome() + " -->> " + regiao);


                pessoa_objeto.identifique("Nome", nome);
                pessoa_objeto.identifique("Sexo", sexo);
                pessoa_objeto.identifique("DDN", ddn);

                pessoa_objeto.identifique("Zona", zona_corrente);
                pessoa_objeto.identifique("Sessao", sessao_corrente);


                HiperMaterializedView10K.set(local_populacao, cidade.index(), pessoa, pessoa_corrente.toString());

                //   System.out.println("Pessoa " + CPUID + " Sexo = " + sexo + " DDN = " + ddn + " -->> " + nome);


                int idade = ANO_ATUAL - ddn;

                if (idade >= isAdulto) {
                    qq_adulto += 1;
                } else {
                    qq_crianca += 1;
                }

                if (idade >= isAdulto && idade <= isObrigatorio) {
                    qq_obrigatorio += 1;
                }

                if (sexo.contentEquals("Homem")) {
                    qq_homem += 1;
                } else {
                    qq_mulher += 1;
                }

                if (pessoa == 0) {
                    idade_menor = idade;
                    idade_maior = idade;
                } else {
                    if (idade < idade_menor) {
                        idade_menor = idade;
                    }
                    if (idade > idade_maior) {
                        idade_maior = idade;
                    }
                }

                System.out.println(pessoa_corrente.toString());
                indo_sessao += 1;

                if (indo_sessao > por_sessao) {

                    indo_sessao = 0;
                    sessao_corrente += 1;
                    indo_zona += 1;

                    if (indo_zona > por_zona) {
                        indo_zona = 0;
                        zona_corrente += 1;
                    }
                }

                CPUID += 1;
            }

            System.out.println("Cidade :: " + cidade.get().getNome() + " -->> " + cidade.get().getX() + " :: " + cidade.get().getY());
            System.out.println("\t - Pessoas   : " + tamanho_populacao);
            System.out.println("\t - Homens    : " + qq_homem);
            System.out.println("\t - Mulheres  : " + qq_mulher);
            System.out.println("\t - Idades  : [ " + idade_menor + " - " + idade_maior + " ] ");
            System.out.println("\t - Crianças  : " + qq_crianca);
            System.out.println("\t - Adultos   : " + qq_adulto);
            System.out.println("\t - Obrigatório   : " + qq_obrigatorio);

            System.out.println("\t - Zonas   = " + zonas);
            System.out.println("\t - Sessoes = " + sessoes);

            int urnas = sessoes;

            System.out.println("\t - Urnas = " + urnas);

            System.out.println("\t - Pessoas por sessao   = " + por_sessao);

            DKG documento_territorio = new DKG();
            DKGObjeto territorio_objeto = documento_territorio.unicoObjeto("Territorio");
            territorio_objeto.identifique("Urnas").setInteiro(urnas);

            MaterializedView10K.set(local_territorios, get_regiao_id(cidade.get().getNome()), documento_territorio.toString());

        }


    }


    public static int get_regiao_id(String cidade) {

        int id = 0;
        String territorio = "";

        Arkazz a = new Arkazz();
        String LOCAL = "/home/luan/Imagens/Arkazz/";
        Regionalizador r = new Regionalizador(LOCAL);

        for (Local c : a.getCidades()) {
            if (c.getNome().contentEquals(cidade)) {


                territorio = r.getRegiao(c.getX(), c.getY());

                break;
            }
        }

        for (Indexado<Regiao> c : Indexamento.indexe(a.getRegioes())) {
            if (c.get().getNome().contentEquals(territorio)) {
                id = c.index();
                break;
            }
        }

        return id;
    }

    public static void analisar_pessoas(String local_populacao) {

        Arkazz eArkazz = new Arkazz();
        Lista<Local> cidades = eArkazz.getCidades();


        int isAdulto = 18;
        int isObrigatorio = 60;

        int ANO_ATUAL = 2022;

        // Construir perfil das pessoas
        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());

            int qq_homem = 0;
            int qq_mulher = 0;

            int qq_crianca = 0;
            int qq_adulto = 0;
            int qq_obrigatorio = 0;

            int idade_menor = 0;
            int idade_maior = 0;

            int maior_sessao = 0;
            int maior_zona = 0;


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

                if (idade >= isAdulto) {
                    qq_adulto += 1;
                } else {
                    qq_crianca += 1;
                }

                if (idade >= isAdulto && idade <= isObrigatorio) {
                    qq_obrigatorio += 1;
                }

                if (sexo.contentEquals("Homem")) {
                    qq_homem += 1;
                } else {
                    qq_mulher += 1;
                }

                if (pessoa == 0) {

                    idade_menor = idade;
                    idade_maior = idade;

                    maior_zona = zona;
                    maior_sessao = sessao;

                } else {

                    idade_menor = Matematica.menor(idade, idade_menor);
                    idade_maior = Matematica.maior(idade, idade_maior);

                    maior_zona = Matematica.maior(zona, maior_zona);
                    maior_sessao = Matematica.maior(sessao, maior_sessao);

                }

                System.out.println(pessoa_corrente.toString());


            }

            System.out.println("Cidade :: " + cidade.get().getNome() + " -->> " + cidade.get().getX() + " :: " + cidade.get().getY());
            System.out.println("\t - Pessoas   : " + tamanho_populacao);
            System.out.println("\t - Homens    : " + qq_homem);
            System.out.println("\t - Mulheres  : " + qq_mulher);
            System.out.println("\t - Idades  : [ " + idade_menor + " - " + idade_maior + " ] ");
            System.out.println("\t - Crianças  : " + qq_crianca);
            System.out.println("\t - Adultos   : " + qq_adulto);
            System.out.println("\t - Obrigatório   : " + qq_obrigatorio);

            System.out.println("\t - Zonas   = " + maior_zona);
            System.out.println("\t - Sessoes = " + maior_sessao);

        }


    }

    public static void shuffle_pessoas(String local_populacao) {

        Arkazz eArkazz = new Arkazz();

        Lista<Local> cidades = eArkazz.getCidades();

        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());

            for (int pessoa = 0; pessoa < tamanho_populacao; pessoa++) {


                int p1 = Aleatorio.aleatorio(tamanho_populacao);
                int p2 = Aleatorio.aleatorio(tamanho_populacao);

                String pessoa_a = HiperMaterializedView10K.get(local_populacao, cidade.index(), p1);
                String pessoa_b = HiperMaterializedView10K.get(local_populacao, cidade.index(), p2);

                HiperMaterializedView10K.set(local_populacao, cidade.index(), p1, pessoa_b);
                HiperMaterializedView10K.set(local_populacao, cidade.index(), p2, pessoa_a);

            }


        }


    }

    public static void mostrar_populacoes(String local_populacao) {

        Arkazz eArkazz = new Arkazz();
        Lista<Local> cidades = eArkazz.getCidades();

        int populacao = 0;

        for (Indexado<Local> cidade : Indexamento.indexe(cidades)) {

            int tamanho_populacao = HiperMaterializedView10K.colecao_contagem(local_populacao, cidade.index());

            System.out.println("\t - " + fmt.espacar_depois(cidade.get().getNome(), 30) + "    -->>    " + fmt.espacar_depois(tamanho_populacao, 3) + " pessoas");

            populacao += tamanho_populacao;
        }

        System.out.println("");
        System.out.println("");
        System.out.println("\t - " + fmt.espacar_depois("TOTAL ARKAZZ", 30) + "    -->>    " + fmt.espacar_depois(populacao, 3) + " pessoas");

    }

}
