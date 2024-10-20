package apps.app_atzum.analisadores;

import apps.app_atzum.AtzumCreator;
import libs.arquivos.GZ;
import libs.arquivos.Zipper;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.Lista;
import libs.luan.Strings;
import libs.luan.fmt;

public class AnalisadorEstruturado {

    public static void SENSORES_DADOS_ORGANIZAR() {

        String arquivo_sensores_captando = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_captando.ds");

        Lista<Entidade> sensores_dados = ENTT.CRIAR_LISTA_VALORES_DE(ENTT.PARSER(DS.buscar_item(arquivo_sensores_captando, "1.entts").get().getTexto()), Lista.CRIAR("Sensor", "X", "Y"));

        ENTT.ATRIBUTO_TODOS(sensores_dados, "Iniciado", "NAO");
        ENTT.ATRIBUTO_TODOS(sensores_dados, "Superarkos", 0);
        ENTT.ATRIBUTO_TODOS(sensores_dados, "Massas", "");
        ENTT.ATRIBUTO_TODOS(sensores_dados, "Fatores", "");

        fmt.print("Sensores : {}", ENTT.CONTAGEM(sensores_dados));

        // ENTT Entidade Normalizadora de Transformação de Tabelas
        //

        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(sensores_dados));
        fmt.print(">> Organizando sensores...");

        for (DSItem sensores_do_superarko : DS.ler_todos(arquivo_sensores_captando)) {

            String superarko = Strings.GET_ATE(sensores_do_superarko.getNome(), ".");

            fmt.print("\t + Organizando Superarko : {}", superarko);

            for (Entidade sensor_corrente : ENTT.PARSER(sensores_do_superarko.getTexto())) {

                //Entidade sensor_organizado = ENTT.GET_SEMPRE(sensores_dados, "Sensor", sensor_corrente.at("Sensor"));

                String sensor_nome = sensor_corrente.at("Sensor");

                double temperatura = sensor_corrente.atDouble("T" + superarko);
                double umidade = sensor_corrente.atDouble("U" + superarko);
                String massa_de_ar = sensor_corrente.at("M" + superarko);
                String fator_climatico = sensor_corrente.at("FC" + superarko);

                Entidade e_sensor = ENTT.GET_SEMPRE(sensores_dados, "Sensor", sensor_nome);

                e_sensor.at("Superarkos", e_sensor.atInt("Superarkos") + 1);

                if (e_sensor.is("Iniciado", "NAO")) {

                    e_sensor.at("Iniciado", "SIM");

                    e_sensor.at("tMin", temperatura);
                    e_sensor.at("tMax", temperatura);

                    e_sensor.at("uMin", umidade);
                    e_sensor.at("uMax", umidade);

                } else {

                    double tMin = e_sensor.atDouble("tMin");
                    double tMax = e_sensor.atDouble("tMax");

                    if (temperatura < tMin) {
                        e_sensor.at("tMin", temperatura);
                    }

                    if (temperatura > tMax) {
                        e_sensor.at("tMax", temperatura);
                    }

                    double uMin = e_sensor.atDouble("uMin");
                    double uMax = e_sensor.atDouble("uMax");

                    if (umidade < uMin) {
                        e_sensor.at("uMin", umidade);
                    }

                    if (umidade > uMax) {
                        e_sensor.at("uMax", umidade);
                    }
                }

                if (!massa_de_ar.isEmpty()) {
                    e_sensor.at(massa_de_ar, e_sensor.atIntOuPadrao(massa_de_ar, 0) + 1);

                    e_sensor.atConglomoreUnico("Massas", massa_de_ar);
                }

                if (!fator_climatico.isEmpty()) {
                    e_sensor.at(fator_climatico, e_sensor.atIntOuPadrao(fator_climatico, 0) + 1);

                    e_sensor.atConglomoreUnico("Fatores", fator_climatico);

                }


                //  ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(e_sensor));
                // break;

            }

            ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(sensores_dados));

        }

        fmt.print(">> Guardando dados dos sensores organizados !");

        //ENTT.GUARDAR(sensores_dados, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));

        fmt.print(">> Sensores organizados !");

        //   ENTT.ABRIR()
    }


    public static void COMPACTAR_SENSORES_SUPERARKO() {


        String arquivo_sensores_superarko = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_superarko.ds");
        String arquivo_sensores_superarko_compactado = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_superarko.dz");

       // DS.limpar(arquivo_sensores_superarko_compactado);

        Lista<Entidade> comparativo = ENTT.CRIAR_LISTA();

        long tamanho_total = 0;

        for (DSItem superarko_item : DS.ler_todos(arquivo_sensores_superarko)) {

            fmt.print("Superarko : {} -->> {}", superarko_item.getNome(),fmt.formatar_tamanho_precisao_dupla(superarko_item.getTamanho()));

          //  DS.adicionar(arquivo_sensores_superarko_compactado,superarko_item.getNome(), Zipper.COMPACTAR(superarko_item.getBytes()));

            Entidade e_item = ENTT.GET_SEMPRE(comparativo,"Superarko",superarko_item.getNome());
            e_item.at("Normal",fmt.formatar_tamanho_precisao_dupla(superarko_item.getTamanho()));

            tamanho_total+=superarko_item.getTamanho();
        }

        fmt.print("Modelo Completo : {} -->> {}", DS.contar(arquivo_sensores_superarko),fmt.formatar_tamanho_precisao_dupla(tamanho_total));


         long tamanho_compactado_total = 0;

        for (DSItem superarko_item : DS.ler_todos(arquivo_sensores_superarko_compactado)) {

            fmt.print("Superarko : {} -->> {}", superarko_item.getNome(),fmt.formatar_tamanho_precisao_dupla(superarko_item.getTamanho()));

            Entidade e_item = ENTT.GET_SEMPRE(comparativo,"Superarko",superarko_item.getNome());
            e_item.at("Compactado",fmt.formatar_tamanho_precisao_dupla(superarko_item.getTamanho()));

            tamanho_compactado_total+=superarko_item.getTamanho();
        }

        fmt.print("Modelo Completo Compactado : {} -->> {}", DS.contar(arquivo_sensores_superarko_compactado),fmt.formatar_tamanho_precisao_dupla(tamanho_compactado_total));

        ENTT.EXIBIR_TABELA(comparativo);


      //  Lista<Entidade> dados_bora_ver = ENTT.PARSER( Zipper.DESCOMPACTAR(DS.buscar_item(arquivo_sensores_superarko_compactado,"1.entts").get().getBytes()) );
       // ENTT.EXIBIR_TABELA(dados_bora_ver);

    }




}
