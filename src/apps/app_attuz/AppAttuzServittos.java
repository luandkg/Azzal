package apps.app_attuz;

import apps.app_attuz.Populacionar.Populacionar;
import apps.app_attuz.Regiao.Regionalizador;
import apps.app_attuz.SistemaEleitoral.Apuracao;
import apps.app_attuz.SistemaEleitoral.Eleicao;
import apps.app_attuz.SistemaEleitoral.SistemaEleitoral;
import apps.app_attuz.SistemaEleitoral.Votacao;
import libs.materializedview.MaterializedView10K;

public class AppAttuzServittos {

    public static void init() {

        String LOCAL = "/home/luan/Imagens/Simples/";
        LOCAL = "/home/luan/Imagens/Arkazz/";


        ViagemOrganizador.construir();


        boolean criar = false;
        boolean renderizar = false;


        if (criar) {
            WorldBuilding.criar(LOCAL);
        }

        if (renderizar) {
            WorldBuilding.renderQTT(LOCAL);
        }

        //WorldBuilding.biomas(LOCAL);

        //RenderQTT.render(eLocal + "dados/relevo.qtt",eLocal + "dados/relevo.png");

        //ViagemCompleta.motrarCidades();
        //ViagemCompleta.remontar_GuiaDeViagem();


        String local_territorios = LOCAL + "/territorios.mv";
        String local_populacao = LOCAL + "/populacao.hmv";
        String local_urnas = LOCAL + "/urnas.mv";


        boolean mudar_populacao = false;
        boolean mostrar_populacao = false;


        if (mudar_populacao) {

            Regionalizador r = new Regionalizador(LOCAL);

            int regioes = r.getRegioes().size();

            if (MaterializedView10K.contagem(local_territorios) != regioes) {
                MaterializedView10K.construir(local_territorios, regioes);
            }

            Populacionar.zerar(local_populacao);

            Populacionar.criar_pessoas(local_populacao);

            Populacionar.organizar_pessoas(LOCAL, local_territorios, local_populacao);

            Populacionar.shuffle_pessoas(local_populacao);

        }


        // Populacionar.analisar_pessoas(local_populacao);
        if (mostrar_populacao) {
            Populacionar.mostrar_populacoes(local_populacao);
        }


        Eleicao eleicao_2022 = new Eleicao();

        eleicao_2022.presidencia("X33", 33, "Okkan de Vaztu");
        eleicao_2022.presidencia("R15", 15, "Halina Maz");
        eleicao_2022.presidencia("Z40", 40, "Molkore et Rhinz");


        eleicao_2022.senado("X33", 330, "Martta della Cruttus");
        eleicao_2022.senado("X33", 331, "Horban Vontzille");
        eleicao_2022.senado("X33", 332, "Gabz Xaun");

        eleicao_2022.senado("R15", 150, "Olla Moccatzim");
        eleicao_2022.senado("R15", 151, "Getto Zina");
        eleicao_2022.senado("R15", 152, "Putzo Carbon");

        eleicao_2022.senado("Z40", 400, "Inna Domnus");
        eleicao_2022.senado("Z40", 401, "Eggon Domnus");
        eleicao_2022.senado("Z40", 402, "Saiga Domnus");

        eleicao_2022.secretaria("X33", 3300, "Palluz Noze");
        eleicao_2022.secretaria("X33", 3301, "Milic Zondareum");
        eleicao_2022.secretaria("X33", 3302, "Orkz Mollante");

        eleicao_2022.secretaria("R15", 1500, "Ilba Scaparim");
        eleicao_2022.secretaria("R15", 1501, "Hurgo Bomptor");
        eleicao_2022.secretaria("R15", 1502, "Valgro Allas");

        eleicao_2022.secretaria("Z40", 4000, "Hindrige Elaffanto");
        eleicao_2022.secretaria("Z40", 4001, "Virga Aztaze");
        eleicao_2022.secretaria("Z40", 4002, "Malla Ongan");


        SistemaEleitoral.direitos_politicos(LOCAL, local_populacao, local_territorios);


        boolean realizar_tempo_de_eleicao = false;
        boolean realizar_eleicao = false;
        boolean realizar_apuracao = true;

        if (realizar_tempo_de_eleicao) {

            SistemaEleitoral.limpar_pensamento_politico(local_populacao);

            SistemaEleitoral.tomar_decisao(local_populacao, eleicao_2022);

            SistemaEleitoral.indecisao(local_populacao, eleicao_2022);

            SistemaEleitoral.realizar_debate(local_populacao, "Presidencia", Eleicao.fazer_lista_de_nomes(eleicao_2022.getPresidencia()));
            SistemaEleitoral.realizar_debate(local_populacao, "Senado", Eleicao.fazer_lista_de_nomes(eleicao_2022.getSenado()));
            SistemaEleitoral.realizar_debate(local_populacao, "Secretaria", Eleicao.fazer_lista_de_nomes(eleicao_2022.getSecretaria()));

            // PesquisaDeOpiniao.realizar("IPQ", local_populacao, "Presidencia");

            SistemaEleitoral.indecisao(local_populacao, eleicao_2022);


            SistemaEleitoral.realizar_debate(local_populacao, "Presidencia", Eleicao.fazer_lista_de_nomes(eleicao_2022.getPresidencia()));
            SistemaEleitoral.realizar_debate(local_populacao, "Senado", Eleicao.fazer_lista_de_nomes(eleicao_2022.getSenado()));
            SistemaEleitoral.realizar_debate(local_populacao, "Secretaria", Eleicao.fazer_lista_de_nomes(eleicao_2022.getSecretaria()));

            SistemaEleitoral.indecisao(local_populacao, eleicao_2022);


            SistemaEleitoral.realizar_debate(local_populacao, "Presidencia", Eleicao.fazer_lista_de_nomes(eleicao_2022.getPresidencia()));
            SistemaEleitoral.realizar_debate(local_populacao, "Senado", Eleicao.fazer_lista_de_nomes(eleicao_2022.getSenado()));
            SistemaEleitoral.realizar_debate(local_populacao, "Secretaria", Eleicao.fazer_lista_de_nomes(eleicao_2022.getSecretaria()));


            SistemaEleitoral.indecisao(local_populacao, eleicao_2022);

        }


        if (realizar_eleicao) {


            System.out.println("Iniciando eleição");
            Votacao.realizar_eleicao(local_populacao, local_urnas, eleicao_2022);
            System.out.println("Terminando eleição");


        }

        // SistemaEleitoral.visualizar_pensamento_politico(local_populacao, eleicao_2022);

        if (realizar_apuracao) {

            System.out.println("Realizando contagem de cidadãos obrigados a votar");
            int cidadaos_obrigados = SistemaEleitoral.cidadaos_obrigados_a_votar(local_populacao);
            System.out.println("Cidadao Obrigados = " + cidadaos_obrigados);


            Apuracao.realizar_apuracao_das_urnas_tempo_real(cidadaos_obrigados, local_territorios, local_urnas);
        }

        // Auditoria.realizar_apuracao(local_populacao);


    }


}
