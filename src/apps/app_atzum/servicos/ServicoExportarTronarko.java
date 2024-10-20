package apps.app_atzum.servicos;

import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.AtzumProcessoCriativoEmTarefas;
import libs.arquivos.Zipper;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.FS;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;

public class ServicoExportarTronarko {

    public static void EXPORTAR_SENSORES_SUPERARKO() {

        String tronarko_corrente = AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO();

        fmt.print(">> Tronarko Corrente : {}", tronarko_corrente);

        String arquivo_sensores_superarko = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_superarko.ds");
        String arquivo_sensores_superarko_compactado = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/tronarko_sensores_por_superarko_" + tronarko_corrente + ".dz");

        DS.limpar(arquivo_sensores_superarko_compactado);

        Lista<Entidade> comparativo = ENTT.CRIAR_LISTA();

        long tamanho_total = 0;

        for (DSItem superarko_item : DS.ler_todos(arquivo_sensores_superarko)) {

            fmt.print("Superarko : {} -->> {}", superarko_item.getNome(), fmt.formatar_tamanho_precisao_dupla(superarko_item.getTamanho()));

            DS.adicionar(arquivo_sensores_superarko_compactado, superarko_item.getNome(), Zipper.COMPACTAR(superarko_item.getBytes()));

            Entidade e_item = ENTT.GET_SEMPRE(comparativo, "Superarko", superarko_item.getNome());
            e_item.at("Normal", fmt.formatar_tamanho_precisao_dupla(superarko_item.getTamanho()));

            tamanho_total += superarko_item.getTamanho();
        }

        fmt.print("Modelo Completo : {} -->> {}", DS.contar(arquivo_sensores_superarko), fmt.formatar_tamanho_precisao_dupla(tamanho_total));


        long tamanho_compactado_total = 0;

        for (DSItem superarko_item : DS.ler_todos(arquivo_sensores_superarko_compactado)) {

            // fmt.print("Superarko : {} -->> {}", superarko_item.getNome(),fmt.formatar_tamanho_precisao_dupla(superarko_item.getTamanho()));

            Entidade e_item = ENTT.GET_SEMPRE(comparativo, "Superarko", superarko_item.getNome());
            e_item.at("Compactado", fmt.formatar_tamanho_precisao_dupla(superarko_item.getTamanho()));

            tamanho_compactado_total += superarko_item.getTamanho();
        }

        fmt.print("Modelo Completo Compactado : {} -->> {}", DS.contar(arquivo_sensores_superarko_compactado), fmt.formatar_tamanho_precisao_dupla(tamanho_compactado_total));

        ENTT.EXIBIR_TABELA(comparativo);


        //  Lista<Entidade> dados_bora_ver = ENTT.PARSER( Zipper.DESCOMPACTAR(DS.buscar_item(arquivo_sensores_superarko_compactado,"1.entts").get().getBytes()) );
        // ENTT.EXIBIR_TABELA(dados_bora_ver);

    }

    public static void EXPORTAR_MODELOS() {

        String tronarko_corrente = AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO();

        fmt.print(">> Tronarko Corrente : {}", tronarko_corrente);

        Lista<Entidade> dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v9.entts"));

        for (Entidade e : dados) {
            e.at("Sensor", e.at("X") + "::" + e.at("Y"));
        }

        ENTT.EXIBIR_TABELA(dados);

        ENTT.GUARDAR(dados, AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/tronarko_modelos_" + tronarko_corrente + ".entts"));

    }

    public static void EXPORTAR_INFOGRAFICOS() {

        String tronarko_corrente = AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO();

        fmt.print(">> Tronarko Corrente : {}", tronarko_corrente);

        FS.COPIAR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_info_modelo_climatico.png"), AtzumCreator.LOCAL_GET_ARQUIVO("comparativos/tronarko_info_" + tronarko_corrente + "_modelo_climatico.png"));
        FS.COPIAR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_info_modelo_vegetacao.png"), AtzumCreator.LOCAL_GET_ARQUIVO("comparativos/tronarko_info_" + tronarko_corrente + "_modelo_vegetacao.png"));

    }


    public static void EXPORTAR_DADOS_CIDADES() {

        String tronarko_corrente = AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO();

        fmt.print(">> Tronarko Corrente : {}", tronarko_corrente);

        String arquivo_sensores_por_sensor = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_sensor.ds");
        String arquivo_cidades_dados = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/tronarko_cidades_" + tronarko_corrente + ".entts");

        DS.limpar(arquivo_cidades_dados);

        Lista<Entidade> cidades = Atzum.GET_CIDADES_NOMES();
        ENTT.EXIBIR_TABELA(cidades);

        long tamanho_total = 0;

        for (DSItem sensor_item : DS.ler_todos(arquivo_sensores_por_sensor)) {

            fmt.print("Sensor : {}", sensor_item.getNome());

            Entidade sensor = ENTT.PARSER_ENTIDADE(sensor_item.getTexto());
            sensor.at("Sensor", sensor.at("X") + "::" + sensor.at("Y"));

            boolean existe_cidade_nesse_sensor = false;
            String cidade_id = "";
            String cidade_nome = "";

            for (Entidade cidade : cidades) {
                if (Strings.isIgual(cidade.at("Cidade"), sensor.at("Sensor"))) {
                    existe_cidade_nesse_sensor = true;

                    cidade_id = cidade.at("CidadeID");
                    cidade_nome = cidade.at("Nome");

                    break;
                }
            }

            if (existe_cidade_nesse_sensor) {
                sensor.at("CidadeID", cidade_id);
                sensor.at("CidadeNome", cidade_nome);

                ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(sensor));

                DS.adicionar(arquivo_cidades_dados, sensor.at("CidadeNome"), ENTT.TO_DOCUMENTO(sensor));

            }


        }


    }


    public static void CONSOLIDAR_DADOS_CIDADES() {

        String tronarko_corrente = AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO();

        fmt.print(">> Tronarko Corrente : {}", tronarko_corrente);


        String arquivo_cidades_dados = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/tronarko_cidades_" + tronarko_corrente + ".entts");
        String arquivo_cidades_modelos = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/tronarko_modelos_" + tronarko_corrente + ".entts");

        Lista<Entidade> cidades = Atzum.GET_CIDADES_NOMES();
        Lista<Entidade> dados = ENTT.ABRIR(arquivo_cidades_dados);
        Lista<Entidade> modelos = ENTT.ABRIR(arquivo_cidades_modelos);

        fmt.print("Cidades : {}", ENTT.CONTAGEM(cidades));
        fmt.print("Cidades : {}", ENTT.CONTAGEM(dados));
        fmt.print("Modelos : {}", ENTT.CONTAGEM(modelos));

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados));
        // ENTT.EXIBIR_TABELA(ENTT.DISPERSAO(dados,"CidadeNome"));
        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(modelos));


        Lista<Entidade> dados_consolidados = ENTT.CRIAR_LISTA();

        for (Entidade cidade : cidades) {

            Entidade e_cidade = ENTT.CRIAR_EM(dados_consolidados);
            e_cidade.at("CidadeID", cidade.at("CidadeID"));
            e_cidade.at("CidadeNome", cidade.at("Nome"));
            e_cidade.at("SensorID", "");
            e_cidade.at("CidadePos", cidade.at("X") + "::" + cidade.at("Y"));
            e_cidade.at("X", cidade.at("X"));
            e_cidade.at("Y", cidade.at("Y"));


            Entidade e_sensor = ENTT.GET_SEMPRE(dados, "Sensor", e_cidade.at("CidadePos"));
            e_cidade.at("SensorID", e_sensor.at("SensorID"));

            Entidade e_modelo = ENTT.GET_SEMPRE(modelos, "Sensor", e_cidade.at("CidadePos"));
            e_cidade.at("Hiperestacao", e_modelo.at("Hiperestacao"));
            e_cidade.at("Vegetacao", e_modelo.at("Vegetacao"));
            e_cidade.at("Nevasca", e_modelo.at("Nevasca"));
            e_cidade.at("Chuva", e_modelo.at("Chuva"));
            e_cidade.at("Secura", e_modelo.at("Secura"));


            double tMenor = e_sensor.atDouble("T1");
            double tMaior = e_sensor.atDouble("T1");

            double uMenor = e_sensor.atDouble("U1");
            double uMaior = e_sensor.atDouble("U1");

            e_cidade.at("tMin", tMenor);
            e_cidade.at("tMax", tMaior);

            e_cidade.at("uMin", uMenor);
            e_cidade.at("uMaxuM", uMaior);

            for (int superarko = 1; superarko <= 500; superarko++) {

                e_cidade.at("T" + superarko, e_sensor.at("T" + superarko));
                e_cidade.at("U" + superarko, e_sensor.at("U" + superarko));
                e_cidade.at("M" + superarko, e_sensor.at("M" + superarko));
                e_cidade.at("FC" + superarko, e_sensor.at("FC" + superarko));
                e_cidade.at("IC" + superarko, e_sensor.at("IC" + superarko));

                if(e_sensor.atDouble("T" + superarko)<tMenor){
                    tMenor=  e_sensor.atDouble("T" + superarko);
                }
                if(e_sensor.atDouble("T" + superarko)>tMaior){
                    tMaior=  e_sensor.atDouble("T" + superarko);
                }

                if(e_sensor.atDouble("U" + superarko)<uMenor){
                    uMenor=  e_sensor.atDouble("U" + superarko);
                }
                if(e_sensor.atDouble("U" + superarko)>uMaior){
                    uMaior=  e_sensor.atDouble("U" + superarko);
                }

            }

            e_cidade.at("tMin", tMenor);
            e_cidade.at("tMax", tMaior);

            e_cidade.at("uMin", uMenor);
            e_cidade.at("uMax", uMaior);

        }

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados_consolidados));

        ENTT.GUARDAR(dados_consolidados, AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/tronarko_cidades_geral_" + tronarko_corrente + ".entts"));

    }


    public static void VER_DADOS_VEGETACAO() {

        String tronarko_corrente = AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO();

        fmt.print(">> Tronarko Corrente : {}", tronarko_corrente);

        String arquivo_cidades_modelos = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/tronarko_modelos_" + tronarko_corrente + ".entts");
        Lista<Entidade> modelos = ENTT.ABRIR(arquivo_cidades_modelos);

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(modelos));

        Lista<Entidade> modelos_vegetacao = ENTT.CRIAR_LISTA();

        for (Entidade vegetacao : ENTT.AGRUPAR(modelos, "Vegetacao")) {

            Entidade e_vegetacao = ENTT.CRIAR_EM(modelos_vegetacao, "Vegetacao", vegetacao.at("Vegetacao"));

            e_vegetacao.at("Sensores", ENTT.CONTAGEM(vegetacao.getEntidades()));

            e_vegetacao.at("Nevasca.Menor", ENTT.GET_INTEIRO_MENOR(vegetacao.getEntidades(), "Nevasca"));
            e_vegetacao.at("Nevasca.Maior", ENTT.GET_INTEIRO_MAIOR(vegetacao.getEntidades(), "Nevasca"));

            e_vegetacao.at("Chuva.Menor", ENTT.GET_INTEIRO_MENOR(vegetacao.getEntidades(), "Chuva"));
            e_vegetacao.at("Chuva.Maior", ENTT.GET_INTEIRO_MAIOR(vegetacao.getEntidades(), "Chuva"));

            e_vegetacao.at("Secura.Menor", ENTT.GET_INTEIRO_MENOR(vegetacao.getEntidades(), "Secura"));
            e_vegetacao.at("Secura.Maior", ENTT.GET_INTEIRO_MAIOR(vegetacao.getEntidades(), "Secura"));

        }

        ENTT.EXIBIR_TABELA(modelos_vegetacao);

    }

    public static void VER_DADOS_CIDADES() {

        String tronarko_corrente = AtzumProcessoCriativoEmTarefas.GET_BETA_TRONARKO();

        fmt.print(">> Tronarko Corrente : {}", tronarko_corrente);

        String arquivo_cidades_consolidados = AtzumCreator.LOCAL_GET_ARQUIVO("tronarkos/tronarko_cidades_geral_" + tronarko_corrente + ".entts");
        Lista<Entidade> cidades = ENTT.ABRIR(arquivo_cidades_consolidados);

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(cidades));

    }
}
