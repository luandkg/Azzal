package apps.app_atzum.analisadores;

import apps.app_atzum.AtzumCreator;
import apps.app_atzum.utils.AtzumCriativoLog;
import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class AnalisadorClimatico {

    public static String TEMPERATURA_CLASSIFICAR(double temperatura) {

        String status = "AMBIENTE";

        if (temperatura < -10) {
            status = "FRIO_EXTREMO";
        } else if (temperatura >= -10 && temperatura <= 15) {
            status = "FRIO";
        } else if (temperatura >= 30 && temperatura <= 35) {
            status = "QUENTE";
        } else if (temperatura > 35) {
            status = "QUENTE_EXTREMO";
        }

        return status;
    }

    public static String CLASSIFICAR_HIPERESTACAO(Lista<String> sensor_estacoes) {
        String ret = "";

        Unico<String> unicamente = new Unico<String>(Strings.IGUALAVEL());
        for (String item : sensor_estacoes) {
            unicamente.item(item);
        }

        if (unicamente.getQuantidade() == 1) {
            ret = unicamente.toLista().get(0);
        } else if (unicamente.getQuantidade() == 2) {
            Lista<String> estacoes_unicas = unicamente.toLista();

            String estacao_alfa = estacoes_unicas.get(0);
            String estacao_beta = estacoes_unicas.get(1);

            Lista<Trio<String, String, String>> estacoes_transicoes = new Lista<Trio<String, String, String>>();

            estacoes_transicoes.adicionar(new Trio<>("ESQUENTANDO", "AMBIENTE", "QUENTE"));
            estacoes_transicoes.adicionar(new Trio<>("ESFRIANDO", "AMBIENTE", "FRIO"));

            estacoes_transicoes.adicionar(new Trio<>("ESQUENTANDO_EXTREMO", "QUENTE", "QUENTE_EXTREMO"));
            estacoes_transicoes.adicionar(new Trio<>("ESFRIANDO_EXTREMO", "FRIO", "FRIO_EXTREMO"));


            estacoes_transicoes.adicionar(new Trio<>("ESQUENTANDO", "AMBIENTE", "QUENTE_EXTREMO"));
            estacoes_transicoes.adicionar(new Trio<>("ESFRIANDO", "AMBIENTE", "FRIO_EXTREMO"));

            ret = inferir_dupla(estacoes_transicoes, estacao_alfa, estacao_beta);

        } else if (sensor_estacoes.getQuantidade() == 3) {


            if (inferencia(Lista.CRIAR("FRIO_EXTREMO", "FRIO", "AMBIENTE"), sensor_estacoes, 3)) {
                ret = "SAZONAL_FRIO";
            } else if (inferencia(Lista.CRIAR("QUENTE_EXTREMO", "QUENTE", "AMBIENTE"), sensor_estacoes, 3)) {
                ret = "SAZONAL_QUENTE";
            }

        } else if (sensor_estacoes.getQuantidade() == 4) {


            if (inferencia(Lista.CRIAR("AMBIENTE", "FRIO", "AMBIENTE", "QUENTE"), sensor_estacoes, 4)) {
                ret = "SAZONAL";
            } else if (inferencia(Lista.CRIAR("FRIO_EXTREMO", "FRIO", "AMBIENTE", "FRIO"), sensor_estacoes, 4)) {
                ret = "SAZONAL_FRIO";
            } else if (inferencia(Lista.CRIAR("QUENTE_EXTREMO", "QUENTE", "AMBIENTE", "QUENTE"), sensor_estacoes, 4)) {
                ret = "SAZONAL_QUENTE";
            }

        } else if (sensor_estacoes.getQuantidade() == 5) {

            if (inferencia(Lista.CRIAR("AMBIENTE", "FRIO", "FRIO_EXTREMO", "FRIO", "QUENTE"), sensor_estacoes, 5)) {
                ret = "SAZONAL_FRIO";
            } else if (inferencia(Lista.CRIAR("QUENTE", "FRIO", "FRIO_EXTREMO", "FRIO", "AMBIENTE"), sensor_estacoes, 5)) {
                ret = "SAZONAL_FRIO";
            } else if (inferencia(Lista.CRIAR("AMBIENTE", "QUENTE", "QUENTE_EXTREMO", "QUENTE", "FRIO"), sensor_estacoes, 5)) {
                ret = "SAZONAL_QUENTE";
            }

        } else if (sensor_estacoes.getQuantidade() == 6) {

            if (inferencia(Lista.CRIAR("AMBIENTE", "QUENTE", "AMBIENTE", "FRIO", "FRIO_EXTREMO", "FRIO"), sensor_estacoes, 6)) {
                ret = "SAZONAL_FRIO_EXTREMO";
            } else if (inferencia(Lista.CRIAR("AMBIENTE", "FRIO", "AMBIENTE", "QUENTE", "QUENTE_EXTREMO", "QUENTE"), sensor_estacoes, 6)) {
                ret = "SAZONAL_QUENTE_EXTREMO";
            }

        } else if (sensor_estacoes.getQuantidade() == 8) {

            if (inferencia(Lista.CRIAR("QUENTE_EXTREMO", "QUENTE", "AMBIENTE", "FRIO", "FRIO_EXTREMO", "FRIO", "AMBIENTE", "QUENTE"), sensor_estacoes, 8)) {
                ret = "SAZONAL_EXTREMO";
            }

        }

        if (ret.isEmpty() && unicamente.getQuantidade() == 3) {
            Lista<String> tipos_unicos = unicamente.toLista();
            if (Lista.IS_IGUAL_ORDENADO(Strings.IGUALDADE(), Ordenador.ORDENAR_STRING_NAO_SENSITIVA(), Lista.CRIAR("FRIO", "FRIO_EXTREMO", "AMBIENTE"), tipos_unicos)) {
                ret = "OSCILATORIO_FRIO";
                ret = "SAZONAL_FRIO_EXTREMO";
            }
        }

        if (ret.isEmpty() && unicamente.getQuantidade() == 3) {
            Lista<String> tipos_unicos = unicamente.toLista();
            if (Lista.IS_IGUAL_ORDENADO(Strings.IGUALDADE(), Ordenador.ORDENAR_STRING_NAO_SENSITIVA(), Lista.CRIAR("QUENTE", "QUENTE_EXTREMO", "AMBIENTE"), tipos_unicos)) {
                ret = "OSCILATORIO_QUENTE";
                ret = "SAZONAL_QUENTE_EXTREMO";
            }
        }

        if (ret.isEmpty() && unicamente.getQuantidade() == 5) {
            Lista<String> tipos_unicos = unicamente.toLista();
            if (Lista.IS_IGUAL_ORDENADO(Strings.IGUALDADE(), Ordenador.ORDENAR_STRING_NAO_SENSITIVA(), Lista.CRIAR("AMBIENTE", "FRIO", "QUENTE", "FRIO_EXTREMO", "QUENTE_EXTREMO"), tipos_unicos)) {
                ret = "SAZONAL_EXTREMO";
            }
        }

        if (ret.isEmpty() && unicamente.getQuantidade() == 4) {
            Lista<String> tipos_unicos = unicamente.toLista();
            if (Lista.IS_IGUAL_ORDENADO(Strings.IGUALDADE(), Ordenador.ORDENAR_STRING_NAO_SENSITIVA(), Lista.CRIAR("AMBIENTE", "FRIO", "FRIO_EXTREMO", "QUENTE_EXTREMO"), tipos_unicos)) {
                ret = "SAZONAL_EXTREMO";
            }
        }

        if (ret.isEmpty() && unicamente.getQuantidade() == 4) {
            Lista<String> tipos_unicos = unicamente.toLista();
            if (Lista.IS_IGUAL_ORDENADO(Strings.IGUALDADE(), Ordenador.ORDENAR_STRING_NAO_SENSITIVA(), Lista.CRIAR("AMBIENTE", "FRIO", "QUENTE", "FRIO_EXTREMO"), tipos_unicos)) {
                ret = "SAZONAL_EXTREMO";
            }
        }

        if (ret.isEmpty() && unicamente.getQuantidade() == 4) {
            Lista<String> tipos_unicos = unicamente.toLista();
            if (Lista.IS_IGUAL_ORDENADO(Strings.IGUALDADE(), Ordenador.ORDENAR_STRING_NAO_SENSITIVA(), Lista.CRIAR("AMBIENTE", "FRIO", "QUENTE", "QUENTE_EXTREMO"), tipos_unicos)) {
                ret = "SAZONAL_EXTREMO";
            }
        }

        if (ret.isEmpty() && unicamente.getQuantidade() == 3) {
            Lista<String> tipos_unicos = unicamente.toLista();
            if (Lista.IS_IGUAL_ORDENADO(Strings.IGUALDADE(), Ordenador.ORDENAR_STRING_NAO_SENSITIVA(), Lista.CRIAR("AMBIENTE", "FRIO", "QUENTE_EXTREMO"), tipos_unicos)) {
                ret = "SAZONAL_EXTREMO";
            }
        }

        if (ret.isEmpty() && unicamente.getQuantidade() == 3) {
            Lista<String> tipos_unicos = unicamente.toLista();
            if (Lista.IS_IGUAL_ORDENADO(Strings.IGUALDADE(), Ordenador.ORDENAR_STRING_NAO_SENSITIVA(), Lista.CRIAR("AMBIENTE", "FRIO", "QUENTE"), tipos_unicos)) {
                ret = "SAZONAL";
            }
        }


        if (ret.isEmpty()) {
            ENTT.EXIBIR_TABELA(ENTT.LISTA_TO_ENTIDADES(sensor_estacoes));
            VERIFICADOR.ERRAR("Hiperestacao desconhecida !");
        }


        return ret;
    }

    public static String inferir_dupla(Lista<Trio<String, String, String>> estacoes_transicoes, String alfa, String beta) {

        String ret = "";

        for (Trio<String, String, String> variando : estacoes_transicoes) {

            if (variando.getValor1().contentEquals(alfa) && variando.getValor2().contentEquals(beta)) {
                ret = variando.getChave();
                break;
            } else if (variando.getValor1().contentEquals(beta) && variando.getValor2().contentEquals(alfa)) {
                ret = variando.getChave();
                break;
            }

        }

        return ret;
    }

    public static boolean inferencia(Lista<String> modelo, Lista<String> observado, int maximo) {
        boolean ret = false;

        for (int eixo = 0; eixo < maximo; eixo++) {

            int indice_modelo = 0;
            int indice_observando = eixo;

            int igual = 0;

            //fmt.print("----------");
            for (int indice = 0; indice < maximo; indice++) {

                // fmt.print("\t ++ COMP ->> {} : {}",modelo.get(indice_modelo),observado.get(indice_observando));

                if (modelo.get(indice_modelo).contentEquals(observado.get(indice_observando))) {
                    igual += 1;
                }

                indice_modelo += 1;
                indice_observando += 1;
                if (indice_observando == maximo) {
                    indice_observando = 0;
                }
                if (indice_modelo == maximo) {
                    indice_modelo = 0;
                }
            }

            //  fmt.print("\t ++ {}",igual);

            if (igual == maximo) {
                ret = true;
                break;
            }


        }


        return ret;
    }


    public static void INIT() {

        AtzumCriativoLog.iniciar("AnalisadorClimatico.INIT");

        PARTE_1();
        VER_DADOS_SENSORES_V2();
        PARTE_2();
        PARTE_3();
        PARTE_4();
        PARTE_5();
        VER_V8();

        AtzumCriativoLog.terminar("AnalisadorClimatico.INIT");

    }

    public static void PARTE_1(){

        fmt.print(">> Obtendo dados dos sensores...");

        String arquivo_sensores_por_sensor = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_sensor.ds");


        fmt.print("Sensores : {}", DS.contar(arquivo_sensores_por_sensor));


        // PARTE 1
        Lista<Entidade> sensores_resumo = ENTT.CRIAR_LISTA();
        Lista<Entidade> sensores_hiperestacoes = ENTT.CRIAR_LISTA();

        Unico<String> fatores_climaticos = new Unico<String>(Strings.IGUALAVEL());

        fmt.print(">> Organizando sensores...");

        for (DSItem sensor_item : DS.ler_todos(arquivo_sensores_por_sensor)) {

            Entidade sensor = ENTT.PARSER_ENTIDADE(sensor_item.getTexto());
            sensor.at("Sensor", sensor.at("X") + "::" + sensor.at("Y"));

            //     ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(sensor));

            if (sensor.at("T1").trim().length() > 0) {

                for (int i = 1; i <= 500; i++) {
                    sensor.at("TS" + i, TEMPERATURA_CLASSIFICAR(sensor.atDouble("T" + i)));
                    fatores_climaticos.item(sensor.at("FC" + i));
                }


                Entidade e_resumo = ENTT.CRIAR_EM(sensores_resumo);
                e_resumo.at("SensorID", sensor.at("SensorID"));
                e_resumo.at("Sensor", sensor.at("Sensor"));
                e_resumo.at("X", sensor.at("X"));
                e_resumo.at("Y", sensor.at("Y"));

                e_resumo.at("Hiperestacao", "");
                e_resumo.at("Periodos.Tipos", 0);
                e_resumo.at("Estacoes.Quantidade", 0);

                Entidade e_hiperestacao = ENTT.CRIAR_EM(sensores_hiperestacoes);
                e_hiperestacao.at("SensorID", sensor.at("SensorID"));
                e_hiperestacao.at("Sensor", sensor.at("Sensor"));
                e_hiperestacao.at("X", sensor.at("X"));
                e_hiperestacao.at("Y", sensor.at("Y"));
                e_hiperestacao.at("Hiperestacao", "");
                e_hiperestacao.at("Estacoes.Quantidade", 0);


                for (int h = 0; h < 10; h++) {

                    int h_iniciar = (h * 50) + 1;
                    int h_terminar = h_iniciar + 49;

                    String estacao_mais_repetida = sensor.at("TS" + h_iniciar);
                    int estacao_mais_repetida_quantidade = 0;

                    String estacao_corrente = sensor.at("TS" + h_iniciar);
                    int estacao_quantidade = 0;

                    for (int s = h_iniciar; s <= h_terminar; s++) {
                        String ts = sensor.at("TS" + s);
                        if (Strings.isIgual(estacao_corrente, ts)) {
                            estacao_quantidade += 1;
                        } else {

                            if (estacao_quantidade > estacao_mais_repetida_quantidade) {
                                estacao_mais_repetida_quantidade = estacao_quantidade;
                                estacao_mais_repetida = estacao_corrente;
                            }

                            estacao_corrente = ts;
                            estacao_quantidade = 0;

                        }
                    }

                    if (estacao_quantidade > estacao_mais_repetida_quantidade) {
                        estacao_mais_repetida_quantidade = estacao_quantidade;
                        estacao_mais_repetida = estacao_corrente;
                    }

                    e_resumo.at("P" + (h + 1), estacao_mais_repetida);
                    e_resumo.at("Q" + (h + 1), estacao_mais_repetida_quantidade);


                }

                Unico<String> periodos = new Unico<String>(Strings.IGUALAVEL());

                int estacoes_quantidade = 0;
                Lista<String> estacoes = new Lista<String>();

                String estacao_anterior = e_resumo.at("P" + 1);

                String estacao_antes = "";

                for (int e = 1; e <= 10; e++) {
                    String estacao = e_resumo.at("P" + e);
                    periodos.item(estacao);
                    estacao_antes = estacao;
                    if (Strings.isDiferente(estacao_anterior, estacao)) {
                        estacoes.adicionar(estacao_anterior);
                        estacao_anterior = estacao;
                        estacoes_quantidade += 1;
                    }
                }

                if (Strings.isDiferente(estacao_antes, e_resumo.at("P" + 1))) {
                    estacoes_quantidade += 1;
                    estacoes.adicionar(estacao_antes);
                }

                if (estacoes_quantidade == 0) {
                    estacoes_quantidade = 1;
                    estacoes.adicionar(e_resumo.at("P" + 1));
                }

                e_resumo.at("Periodos.Tipos", periodos.getQuantidade());
                e_resumo.at("Estacoes.Quantidade", estacoes_quantidade);

                e_hiperestacao.at("Estacoes.Quantidade", estacoes_quantidade);


                int e = 1;
                for (String estacao : estacoes) {
                    e_hiperestacao.at("E" + e, estacao);
                    e += 1;
                }

                ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(e_hiperestacao));


            }


        }


        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_AMOSTRA_PEQUENA(sensores_hiperestacoes), "HIPERESTACOES - ANALISADA");


        ENTT.GUARDAR(sensores_hiperestacoes, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v2.entts"));

        Lista<Entidade> fatores_climaticos_dados = ENTT.CRIAR_LISTA();
        for (String fator : fatores_climaticos.toLista()) {
            Entidade e_fator = ENTT.CRIAR_EM(fatores_climaticos_dados);
            e_fator.at("FatorClimatico", fator);
        }

        ENTT.GUARDAR(fatores_climaticos_dados, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_fatores_climaticos.entts"));

    }

    public static void VER_DADOS_SENSORES_V2() {

        Lista<Entidade> dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v2.entts"));

        ENTT.EXIBIR_TABELA(dados);
        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados));

    }

    public static void PARTE_2() {

        Lista<Entidade> sensores_hiperestacoes = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v2.entts"));

        // PARTE 2

        Lista<Entidade> estacoes_combinacoes = new Lista<Entidade>();
        for (Entidade sensor : sensores_hiperestacoes) {

            Lista<String> sensor_estacoes = new Lista<String>();

            for (int e = 1; e <= sensor.atInt("Estacoes.Quantidade"); e++) {
                sensor_estacoes.adicionar(sensor.at("E" + e));
            }

            boolean ja_existe = false;
            for (Entidade combinacao : estacoes_combinacoes) {
                if (combinacao.atInt("Estacoes.Quantidade") == sensor.atInt("Estacoes.Quantidade")) {

                    Lista<String> combinacao_estacoes = new Lista<String>();

                    for (int e = 1; e <= combinacao.atInt("Estacoes.Quantidade"); e++) {
                        combinacao_estacoes.adicionar(combinacao.at("E" + e));
                    }

                    if (Lista.IS_IGUAL(Strings.IGUALDADE(), sensor_estacoes, combinacao_estacoes)) {
                        ja_existe = true;
                        break;
                    }

                }
            }

            if (!ja_existe) {
                Entidade combinacao = ENTT.CRIAR_EM(estacoes_combinacoes);
                combinacao.at("ID", ENTT.CONTAGEM(estacoes_combinacoes));
                combinacao.at("Hiperestacao", "");
                combinacao.at("Status", "");
                combinacao.at("Estacoes.Quantidade", sensor.atInt("Estacoes.Quantidade"));
                for (int e = 1; e <= combinacao.atInt("Estacoes.Quantidade"); e++) {
                    combinacao.at("E" + e, sensor.at("E" + e));
                }
            }

        }

        ENTT.ORDENAR_INTEIRO(estacoes_combinacoes, "Estacoes.Quantidade");
        ENTT.EXIBIR_TABELA_COM_TITULO(estacoes_combinacoes, "ESTACOES - COMBINADAS - A3");

        ENTT.GUARDAR(estacoes_combinacoes, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v3.entts"));


    }

    public static void PARTE_3() {

        // PARTE 3

        Lista<Entidade> estacoes_combinacoes = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v3.entts"));

        for (Entidade sensor : estacoes_combinacoes) {

            if (sensor.isVazio("Status")) {
                int estacoes_quantidade = sensor.atInt("Estacoes.Quantidade");
                Lista<String> sensor_estacoes = new Lista<String>();

                for (int e = 1; e <= estacoes_quantidade; e++) {
                    sensor_estacoes.adicionar(sensor.at("E" + e));
                }


                sensor.at("Status", "OK");
                sensor.at("Hiperestacao", CLASSIFICAR_HIPERESTACAO(sensor_estacoes));


                for (Entidade outro_sensor : estacoes_combinacoes) {
                    if (outro_sensor.isVazio("Status")) {
                        int outro_estacoes_quantidade = outro_sensor.atInt("Estacoes.Quantidade");
                        if (outro_estacoes_quantidade == estacoes_quantidade) {

                            Lista<String> outro_sensor_estacoes = new Lista<String>();

                            for (int e = 1; e <= outro_estacoes_quantidade; e++) {
                                outro_sensor_estacoes.adicionar(outro_sensor.at("E" + e));
                            }

                            if (inferencia(sensor_estacoes, outro_sensor_estacoes, estacoes_quantidade)) {
                                outro_sensor.at("Status", "Inferido");
                            }

                        }
                    }
                }


            }


        }


        ENTT.EXIBIR_TABELA_COM_TITULO(estacoes_combinacoes, "ESTACOES COMBINADAS A4");
        ENTT.GUARDAR(estacoes_combinacoes, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v4.entts"));


        estacoes_combinacoes = ENTT.COLETAR(estacoes_combinacoes, "Status", "OK");
        ENTT.EXIBIR_TABELA_COM_TITULO(estacoes_combinacoes, "ESTACOES COMBINADAS A5");


        ENTT.GUARDAR(estacoes_combinacoes, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v5.entts"));


    }

    public static void PARTE_4() {

        // Lista<Entidade> sensores_hiperestacoes_original = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));

        Lista<Entidade> sensores_hiperestacoes = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v2.entts"));

        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_AMOSTRA_PEQUENA(sensores_hiperestacoes), "HIPERESTACOES ENTRADA");

        for (Entidade hiper_estacao : sensores_hiperestacoes) {

            int estacoes_quantidade = hiper_estacao.atInt("Estacoes.Quantidade");
            Lista<String> sensor_estacoes = new Lista<String>();

            for (int e = 1; e <= estacoes_quantidade; e++) {
                sensor_estacoes.adicionar(hiper_estacao.at("E" + e));
            }

            hiper_estacao.at("Hiperestacao", CLASSIFICAR_HIPERESTACAO(sensor_estacoes));

            //   Entidade original = ENTT.GET_SEMPRE(sensores_hiperestacoes_original, "Sensor", hiper_estacao.at("Sensor"));
            //   original.at("Hiperestacao", hiper_estacao.at("Hiperestacao"));


        }

        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_AMOSTRA_PEQUENA(sensores_hiperestacoes), "HIPERESTACOES PROCESSADAS V6");

        ENTT.GUARDAR(sensores_hiperestacoes, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v6.entts"));
        // ENTT.GUARDAR(sensores_hiperestacoes_original, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v7.entts"));


        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.DISPERSAO(sensores_hiperestacoes, "Hiperestacao"), "HIPERESTACOES PROCESSADAS V7");


    }

    public static int FATOR_CLIMATICO_CONTAGEM(Entidade sensor, String fator_climatico) {

        int contagem = 0;

        for (int i = 1; i <= 500; i++) {
            if (Strings.isIgual(sensor.at("FC" + i), fator_climatico)) {
                contagem += 1;
            }

        }

        return contagem;
    }


    public static void PARTE_5() {

        //MAPEAR_CONDICOES_CLIMATICAS

        AtzumCriativoLog.iniciar("AnalisadorClimatico.MAPEAR_CONDICOES_CLIMATICAS");

        //   Lista<Entidade> mapa_sensores = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v7.entts"));

        //    ENTT.EXIBIR_TABELA(mapa_sensores);

        Lista<Entidade> fatores_climaticos = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_fatores_climaticos.entts"));

        ENTT.EXIBIR_TABELA_COM_TITULO(fatores_climaticos, "FATORES CLIMATICOS - INVESTIGANDO");

        Lista<Entidade> sensores_hiperestacoes = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v6.entts"));


        Lista<Entidade> dados_sensores_v8 = ENTT.CRIAR_LISTA();

        for (DSItem item_sensor : DS.ler_todos(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_sensor.ds"))) {

            Entidade sensor = ENTT.PARSER_ENTIDADE(item_sensor.getTexto());

            Entidade e_sensor = ENTT.CRIAR_EM(dados_sensores_v8);
            e_sensor.at("SensorID", sensor.at("SensorID"));
            e_sensor.at("Sensor", sensor.at("Sensor"));
            e_sensor.at("X", sensor.at("X"));
            e_sensor.at("Y", sensor.at("Y"));

            e_sensor.at("Hiperestacao", ENTT.GET_SEMPRE(sensores_hiperestacoes, "SensorID", sensor.at("SensorID")).at("Hiperestacao"));


            e_sensor.at("Nevasca", FATOR_CLIMATICO_CONTAGEM(sensor, "NEVE") + FATOR_CLIMATICO_CONTAGEM(sensor, "TEMPESTADE_NEVE"));
            e_sensor.at("Chuva", FATOR_CLIMATICO_CONTAGEM(sensor, "CHUVA") + FATOR_CLIMATICO_CONTAGEM(sensor, "TEMPESTADE_CHUVA"));
            e_sensor.at("Secura", FATOR_CLIMATICO_CONTAGEM(sensor, "SECA") + FATOR_CLIMATICO_CONTAGEM(sensor, "SECA_EXTREMA") + FATOR_CLIMATICO_CONTAGEM(sensor, "ONDA_DE_CALOR"));

            ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(e_sensor));
        }

        ENTT.EXIBIR_TABELA_COM_TITULO(dados_sensores_v8, "SENSORES - V8");

        ENTT.GUARDAR(dados_sensores_v8, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v8.entts"));

        AtzumCriativoLog.terminar("AnalisadorClimatico.MAPEAR_CONDICOES_CLIMATICAS");

    }

    public static void VER_V8() {

        Lista<Entidade> dados_sensores_v8 = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v8.entts"));
        ENTT.EXIBIR_TABELA_COM_TITULO(dados_sensores_v8, "SENSORES - V8");


    }


}
