package apps.app_atzum.analisadores;

import apps.app_atzum.Atzum;
import apps.app_atzum.AtzumCreator;
import apps.app_atzum.utils.AtzumCreatorInfo;
import apps.app_campeonatum.VERIFICADOR;
import libs.arquivos.Quadrum;
import libs.arquivos.ds.DS;
import libs.arquivos.ds.DSItem;
import libs.entt.ENTT;
import libs.entt.Entidade;
import libs.luan.*;

public class AnalisadorClimaticoAntigo {

    public static void ANALISAR_SENSORES() {


        Lista<Entidade> sensores = Atzum.GET_SENSORES();

        fmt.print("Sensores : {}", ENTT.CONTAGEM(sensores));

        String arquivo_sensores_por_sensor_quadrum = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_sensor.qa");
        Quadrum sensores_organizando = new Quadrum(arquivo_sensores_por_sensor_quadrum);
        sensores_organizando.abrir();


        fmt.print("{}", sensores_organizando.get(5249, 2));
        fmt.print("{}", sensores_organizando.get(5249 + 1, 2));
        fmt.print("{}", sensores_organizando.get(5249 + 2, 2));
        fmt.print("{}", sensores_organizando.get(5249 + 3, 2));


        Entidade d5250 = ENTT.PARSER_ENTIDADE(sensores_organizando.get(5250, 1));
        fmt.print(">> {}", d5250.toTexto());
        fmt.print(">> {}", d5250.isValido("X") && d5250.isValido("Y"));


        fmt.print("---------------------------------------------");


        Lista<Entidade> dados_resumidos = new Lista<Entidade>();
        Lista<Entidade> fatores_climaticos = new Lista<Entidade>();
        Lista<Entidade> dados_estacoes = new Lista<Entidade>();


        Unico<String> tipos_climaticos = new Unico<String>(Strings.IGUALAVEL());

        for (Entidade p_sensor : sensores) {

            Entidade dados_sensor = ENTT.CRIAR_EM(dados_resumidos, "SensorID", p_sensor.at("SensorID"));


            dados_sensor.at("X", p_sensor.at("X"));
            dados_sensor.at("Y", p_sensor.at("Y"));


            String massa_anterior = "";
            int massa_duracao = 0;
            String massa_inicio = "";
            String massa_fim = "";


            for (int superarko = 1; superarko <= 500; superarko++) {


                Entidade e_sensor = ENTT.PARSER_ENTIDADE(sensores_organizando.get(p_sensor.atInt("SensorID"), superarko));


                fmt.print(">> {} :: {}",p_sensor.atInt("SensorID"),superarko);
                ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(e_sensor));
                fmt.print("++ {}",(e_sensor.isValido("X") && e_sensor.isValido("Y")));
                fmt.print("++ Vs = {}",e_sensor.at("T"+superarko));
                fmt.print("++ Vd = {}",e_sensor.atDouble("T"+superarko));


                if (e_sensor.isValido("X") && e_sensor.isValido("Y")) {

                    if (superarko == 1) {
                        dados_sensor.at("TMenor", e_sensor.atDouble("T1"));
                        dados_sensor.at("TMaior", e_sensor.atDouble("T1"));
                        dados_sensor.at("UMenor", e_sensor.atDouble("U1"));
                        dados_sensor.at("UMaior", e_sensor.atDouble("U1"));

                        dados_sensor.at("EstacoesTermicas", "");
                        dados_sensor.at("FatoresClimaticos", "");

                        massa_anterior = e_sensor.at("FC1");
                        massa_duracao = 0;
                        massa_inicio = "1";
                        massa_fim = "1";
                    }

                    dados_sensor.atSeMenor("TMenor", e_sensor.atDouble("T" + superarko));
                    dados_sensor.atSeMaior("TMaior", e_sensor.atDouble("T" + superarko));

                    dados_sensor.atSeMenor("UMenor", e_sensor.atDouble("U" + superarko));
                    dados_sensor.atSeMaior("UMaior", e_sensor.atDouble("U" + superarko));

                    String massa_corrente = e_sensor.at("FC" + superarko);
                    // fmt.print("Atual : {} - {}",cidade.at("Cidade"),massa_atual);

                    if (Strings.isValida(massa_corrente)) {
                        dados_sensor.at(massa_corrente, "SIM");
                        tipos_climaticos.item(massa_corrente);
                    }


                    if (Strings.isIgual(massa_anterior, massa_corrente)) {
                        massa_duracao += 1;
                        massa_fim = String.valueOf(superarko);
                    } else {
                        Entidade fator_climatico = new Entidade();
                        fator_climatico.at("SensorID", p_sensor.at("SensorID"));
                        fator_climatico.at("Fator", massa_anterior);
                        fator_climatico.at("Inicio", massa_inicio);
                        fator_climatico.at("Fim", massa_fim);
                        fator_climatico.at("Tempo", massa_duracao);

                        fatores_climaticos.adicionar(fator_climatico);


                        massa_anterior = massa_corrente;
                        massa_duracao = 1;
                        massa_inicio = String.valueOf(superarko);
                        massa_fim = String.valueOf(superarko);

                    }

                }

            }


            Entidade fator_climatico = new Entidade();
            fator_climatico.at("SensorID", p_sensor.at("SensorID"));
            fator_climatico.at("Fator", massa_anterior);
            fator_climatico.at("Inicio", massa_inicio);
            fator_climatico.at("Fim", massa_fim);
            fator_climatico.at("Tempo", massa_duracao);

            fatores_climaticos.adicionar(fator_climatico);

            Lista<String> fatores_existentes = new Lista<String>();

            for (String fator : tipos_climaticos) {
                if (dados_sensor.at(fator).contentEquals("SIM")) {
                    fatores_existentes.adicionar(fator);
                }
            }

            dados_sensor.at("FatoresClimaticos", Strings.LISTA_EM_LINHA_COM(fatores_existentes, "{", "}"));

            //   ENTT.EXIBIR_TABELA(ENTT.CRIAR_LISTA_COM(dados_sensor));


            String temperatura_anterior = "";
            int estacao_duracao = 0;
            String estacao_inicio = "";
            String estacao_fim = "";

            for (int superarko = 1; superarko <= 500; superarko++) {

                Entidade e_sensor = ENTT.PARSER_ENTIDADE(sensores_organizando.get(p_sensor.atInt("SensorID"), superarko));

                if (e_sensor.isValido("X") && e_sensor.isValido("Y")) {

                    if (superarko == 1) {
                        temperatura_anterior = TEMPERATURA_CLASSIFICAR(e_sensor.atDouble("T1"));
                        estacao_duracao = 0;
                        estacao_inicio = "1";
                        estacao_fim = "1";
                    }


                    String temperatura_corrente = TEMPERATURA_CLASSIFICAR(e_sensor.atDouble("T" + superarko));


                    if (Strings.isIgual(temperatura_anterior, temperatura_corrente)) {
                        estacao_duracao += 1;
                        estacao_fim = String.valueOf(superarko);
                    } else {
                        Entidade estacao_termica = new Entidade();
                        estacao_termica.at("SensorID", e_sensor.at("SensorID"));
                        estacao_termica.at("Estacao", temperatura_anterior);
                        estacao_termica.at("Inicio", estacao_inicio);
                        estacao_termica.at("Fim", estacao_fim);
                        estacao_termica.at("Tempo", estacao_duracao);
                        dados_estacoes.adicionar(estacao_termica);

                        estacao_duracao = 1;
                        estacao_inicio = String.valueOf(superarko);
                        estacao_fim = String.valueOf(superarko);
                    }

                    if (superarko == 500) {
                        Entidade estacao_termica = new Entidade();
                        estacao_termica.at("SensorID", p_sensor.at("SensorID"));
                        estacao_termica.at("Estacao", temperatura_anterior);
                        estacao_termica.at("Inicio", estacao_inicio);
                        estacao_termica.at("Fim", estacao_fim);
                        estacao_termica.at("Tempo", estacao_duracao);
                        dados_estacoes.adicionar(estacao_termica);
                    }

                    temperatura_anterior = temperatura_corrente;

                }


            }

            dados_sensor.at("EstacoesTermicas", Strings.LISTA_EM_LINHA_COM(ENTT.FILTRAR_UNICOS(ENTT.COLETAR(dados_estacoes, "SensorID", p_sensor.at("SensorID")), "Estacao"), "{", "}"));


            //  }


        }

        sensores_organizando.fechar();

        ENTT.EXIBIR_TABELA(dados_resumidos);
        ENTT.EXIBIR_TABELA(dados_estacoes);

        //ENTT.EXIBIR_TABELA(dados_resumidos);

        /// ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(dados_brutos));
        //ENTT.EXIBIR_TABELA(fatores_climaticos);

        //  ENTT.EXIBIR_TABELA(ENTT.GET_FATIA_INICIO_PEQUENO(fatores_climaticos));
        // ENTT.EXIBIR_TABELA(estacoes_termicas);

        ENTT.GUARDAR(fatores_climaticos, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_fatores_climaticos.entts"));
        ENTT.GUARDAR(dados_resumidos, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_resumo.entts"));
        ENTT.GUARDAR(dados_estacoes, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_estacoes.entts"));

    }

    public static void TESTE() {

        // ULTIMO : 5683 : 500
        String arquivo_sensores_por_superarko = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_superarko.ds");

        for (DSItem item : DS.ler_todos(arquivo_sensores_por_superarko)) {
            fmt.print(">> {}", item.getNome());

            if (item.isNome("1.entts")) {
                fmt.print(">> {}", item.getTexto());
            }
        }

        if (DS.contar(arquivo_sensores_por_superarko) > 0) {
            return;
        }

        String arquivo_sensores_por_sensor = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_sensor.ds");

        for (DSItem item : DS.ler_todos(arquivo_sensores_por_sensor)) {
            fmt.print(">> {}", item.getNome());

            if (item.isNome("5250")) {
                fmt.print(">> {}", item.getTexto());
            }
        }


        Lista<Entidade> sensores = Atzum.GET_SENSORES();
        ENTT.EXIBIR_TABELA(sensores);

        fmt.print("Sensores : {}", ENTT.CONTAGEM(sensores));

        String arquivo_sensores_por_sensor_quadrum = AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_por_sensor.qa");
        Quadrum sensores_organizando = new Quadrum(arquivo_sensores_por_sensor_quadrum);
        sensores_organizando.abrir();


        Entidade o_sensor = ENTT.PARSER_ENTIDADE(sensores_organizando.get(5250, 500));
        fmt.print(">> {}", o_sensor.toTexto());

        if (DS.contar(arquivo_sensores_por_sensor) > 0) {
            sensores_organizando.fechar();
            return;
        }

        for (Entidade p_sensor : sensores) {

            Entidade dados_sensor = ENTT.CRIAR("SensorID", p_sensor.at("SensorID"));
            dados_sensor.at("X", p_sensor.at("X"));
            dados_sensor.at("Y", p_sensor.at("Y"));

            for (int superarko = 1; superarko <= 500; superarko++) {
                Entidade e_sensor = ENTT.PARSER_ENTIDADE(sensores_organizando.get(p_sensor.atInt("SensorID"), superarko));

                fmt.print("\t >> Sensor {} : {}", e_sensor.at("SensorID"), superarko);

            }
        }

        sensores_organizando.fechar();


    }


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

    public static void VISUALIZAR() {

        Lista<Entidade> hiperestacoes_detalhado = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_hiperestacoes_detalhado.entts"));

        ENTT.SUBSTITUIR(hiperestacoes_detalhado, "0", "");

        ENTT.EXIBIR_TABELA(hiperestacoes_detalhado);

        Lista<Entidade> vegetacao = ENTT.CRIAR_LISTA();
        for (String hiper_estacao : ENTT.FILTRAR_UNICOS(hiperestacoes_detalhado, "Hiperestacao")) {
            Entidade estacao = ENTT.GET_SEMPRE(vegetacao, "Hiperestacao", hiper_estacao);

            int neve = 0;

            int agua = 0;
            int seca = 0;

            for (Entidade estacao_item : ENTT.COLETAR(hiperestacoes_detalhado, "Hiperestacao", hiper_estacao)) {


                if (estacao_item.isInteiro("NEVE")) {
                    neve += estacao_item.atInt("NEVE");
                } else if (estacao_item.isInteiro("TEMPESTADE_NEVE")) {
                    neve += estacao_item.atInt("TEMPESTADE_NEVE");
                }

                if (estacao_item.isInteiro("CHUVA")) {
                    agua += estacao_item.atInt("CHUVA");
                } else if (estacao_item.isInteiro("TEMPESTADE_CHUVA")) {
                    agua += estacao_item.atInt("TEMPESTADE_CHUVA");
                }

                if (estacao_item.isInteiro("SECA")) {
                    seca += estacao_item.atInt("SECA");
                } else if (estacao_item.isInteiro("SECA_EXTREMA")) {
                    seca += estacao_item.atInt("SECA_EXTREMA");
                } else if (estacao_item.isInteiro("ONDA_DE_CALOR")) {
                    seca += estacao_item.atInt("ONDA_DE_CALOR");
                }

            }

            if (neve > 0) {
                estacao.at("Nevasca", neve);
            }
            if (agua > 0) {
                estacao.at("Pluviosidade", agua);
            }
            if (seca > 0) {
                estacao.at("Seca", seca);
            }
        }

        ENTT.EXIBIR_TABELA(vegetacao);

        ENTT.SUBSTITUIR_INTEIRO_ABAIXO(vegetacao, 100, 0);
        ENTT.SUBSTITUIR(vegetacao, "0", "");

        ENTT.EXIBIR_TABELA(vegetacao);

        for (Entidade item_vegetacao : vegetacao) {
            int seca = item_vegetacao.atIntOuPadrao("Seca", 0);
            int pluviosidade = item_vegetacao.atIntOuPadrao("Pluviosidade", 0);
            int nevasca = item_vegetacao.atIntOuPadrao("Nevasca", 0);

            if (seca > 0 && pluviosidade == 0 && nevasca == 0) {
                item_vegetacao.at("Vegetacao", "DESERTO");
            } else if (nevasca > 0 && seca == 0 && pluviosidade == 0) {
                item_vegetacao.at("Vegetacao", "TUNDRA");
            } else {


                if (nevasca >= (pluviosidade + seca)) {
                    item_vegetacao.at("Vegetacao", "TAIGA");
                }

                if (pluviosidade >= ((nevasca + seca) / 2)) {
                    if (nevasca == 0 && seca == 0) {
                        item_vegetacao.at("Vegetacao", "MATA");
                    } else if (nevasca == 0 && seca > 0) {
                        item_vegetacao.at("Vegetacao", "FLORESTA TROPICAL");
                    } else if (nevasca > 0 && seca == 0) {
                        item_vegetacao.at("Vegetacao", "FLORESTA TEMPERADA");
                    } else {
                        item_vegetacao.at("Vegetacao", "FLORESTA");
                    }
                }

                if (seca >= (nevasca + pluviosidade)) {
                    item_vegetacao.at("Vegetacao", "SAVANA");
                }

            }


        }

        ENTT.SUBSTITUIR(vegetacao, "0", "");
        ENTT.EXIBIR_TABELA(vegetacao);


        // Lista<Entidade>mapa_sensores= ENTT.ABRIR_COM_LIMITE( AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v3.entts"),10);
        Lista<Entidade> mapa_sensores = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v3.entts"));

        // ENTT.EXIBIR_TABELA(mapa_sensores);

        ENTT.AT_ALTERAR_NOME(mapa_sensores, "Estacao.Tipo", "Hiperestacao");

        Lista<Entidade> mapa_hiperestacoes_climaticos = ENTT.DISPERSAO(mapa_sensores, "Hiperestacao");

        ENTT.EXIBIR_TABELA(mapa_hiperestacoes_climaticos);

        Lista<Entidade> mapa_hiperestacoes_climaticos_detalhado = ENTT.CRIAR_LISTA();


        for (Entidade clima : mapa_hiperestacoes_climaticos) {

            Lista<Entidade> clima_sensores = ENTT.COLETAR(mapa_sensores, "Hiperestacao", clima.at("Hiperestacao"));

            Unico<String> estacoes = new Unico<String>(Strings.IGUALAVEL());
            for (Entidade sensor : clima_sensores) {
                for (int i = 1; i <= 500; i++) {
                    estacoes.item(sensor.at("TS" + i));
                }
            }


            //ENTT.EXIBIR_TABELA_PREFIXO("\t",ENTT.GET_AMOSTRA_PEQUENA(clima_sensores));

            //   Entidade e_clima = ENTT.CRIAR_EM(mapa_hiperestacoes_climaticos_detalhado);
            //    e_clima.at("Hiperestacao",clima.at("Hiperestacao"));
            //  e_clima.at("Sensores",clima_sensores.getQuantidade());

            for (String estacao : estacoes) {

                int quantidade = 0;

                Unico<String> estacao_fatores_climaticos = new Unico<String>(Strings.IGUALAVEL());

                for (Entidade sensor : clima_sensores) {
                    boolean tem = false;
                    for (int i = 1; i <= 500; i++) {
                        if (Strings.isIgual(sensor.at("TS" + i), estacao)) {
                            tem = true;
                            //  break;
                            estacao_fatores_climaticos.item(sensor.at("FC" + i));
                        }
                    }
                    if (tem) {
                        quantidade += 1;
                    }
                }


                //e_clima.at(estacao,quantidade);

                if (quantidade > 0) {

                    Entidade e_clima = ENTT.CRIAR_EM(mapa_hiperestacoes_climaticos_detalhado);
                    e_clima.at("Hiperestacao", clima.at("Hiperestacao"));
                    // e_clima.at("Sensores",clima_sensores.getQuantidade());
                    e_clima.at("Estacao", estacao);

                    for (String fator_climatico : estacao_fatores_climaticos) {

                        int fatorando = 0;

                        for (Entidade sensor : clima_sensores) {
                            boolean tem_fc = false;
                            for (int i = 1; i <= 500; i++) {
                                if (Strings.isIgual(sensor.at("TS" + i), estacao)) {
                                    if (Strings.isIgual(sensor.at("FC" + i), fator_climatico)) {
                                        tem_fc = true;
                                        break;
                                    }
                                }
                            }
                            if (tem_fc) {
                                fatorando += 1;
                            }
                        }

                        e_clima.at(fator_climatico, fatorando);


                    }


                }
            }

        }

        for (Entidade e_clima : mapa_hiperestacoes_climaticos_detalhado) {

            e_clima.at("___", "");

            int nevasca = e_clima.atIntOuPadrao("NEVE", 0) + e_clima.atIntOuPadrao("TEMPESTADE_NEVE", 0);
            int pluviosidade = e_clima.atIntOuPadrao("CHUVA", 0) + e_clima.atIntOuPadrao("TEMPESTADE_CHUVA", 0);
            int secura = e_clima.atIntOuPadrao("SECA", 0) + e_clima.atIntOuPadrao("SECA_EXTREMA", 0) + e_clima.atIntOuPadrao("ONDA_DE_CALOR", 0);

            e_clima.at("Nevasca", nevasca);
            e_clima.at("Pluviosidade", pluviosidade);
            e_clima.at("Secura", secura);

            e_clima.at("____", "");
            e_clima.at("Vegetacao", "");

        }


        ENTT.SUBSTITUIR(mapa_hiperestacoes_climaticos_detalhado, "0", "");
        ENTT.EXIBIR_TABELA(mapa_hiperestacoes_climaticos_detalhado);

        if (ENTT.CONTAGEM(mapa_sensores) > 0) {
            return;
        }

        Lista<Entidade> mapa_sensores_vegetacao = ENTT.CRIAR_LISTA();
        for (Entidade sensor : mapa_sensores) {

            Entidade e_sensor = ENTT.CRIAR_EM(mapa_sensores_vegetacao);
            e_sensor.at("Sensor", sensor.at("Sensor"));


            int neve = 0;
            int agua = 0;
            int seca = 0;

            for (int fc = 1; fc <= 500; fc++) {
                String estacao_item = sensor.at("FC" + fc);
                if (Strings.isIgual(estacao_item, "NEVE")) {
                    neve += 1;
                } else if (Strings.isIgual(estacao_item, "TEMPESTADE_NEVE")) {
                    neve += 1;
                }

                if (Strings.isIgual(estacao_item, "CHUVA")) {
                    agua += 1;
                } else if (Strings.isIgual(estacao_item, "TEMPESTADE_CHUVA")) {
                    agua += 1;
                }

                if (Strings.isIgual(estacao_item, "SECA")) {
                    seca += 1;
                } else if (Strings.isIgual(estacao_item, "SECA_EXTREMA")) {
                    seca += 1;
                } else if (Strings.isIgual(estacao_item, "ONDA_DE_CALOR")) {
                    seca += 1;
                }

            }

            if (neve > 0) {
                e_sensor.at("Nevasca", neve);
            }
            if (agua > 0) {
                e_sensor.at("Pluviosidade", agua);
            }
            if (seca > 0) {
                e_sensor.at("Seca", seca);
            }

        }

        ENTT.EXIBIR_TABELA(mapa_sensores_vegetacao);

        ENTT.SUBSTITUIR_INTEIRO_ABAIXO(mapa_sensores_vegetacao, 100, 0);
        ENTT.SUBSTITUIR(mapa_sensores_vegetacao, "0", "");


        // VISU SENSORES

        for (Entidade item_vegetacao : mapa_sensores_vegetacao) {
            int seca = item_vegetacao.atIntOuPadrao("Seca", 0);
            int pluviosidade = item_vegetacao.atIntOuPadrao("Pluviosidade", 0);
            int nevasca = item_vegetacao.atIntOuPadrao("Nevasca", 0);

            if (seca > 0 && pluviosidade == 0 && nevasca == 0) {
                item_vegetacao.at("Vegetacao", "DESERTO");
            } else if (nevasca > 0 && seca == 0 && pluviosidade == 0) {
                item_vegetacao.at("Vegetacao", "TUNDRA");
            } else {


                if (nevasca >= (pluviosidade + seca)) {
                    item_vegetacao.at("Vegetacao", "TAIGA");
                }

                if (pluviosidade >= ((nevasca + seca) / 2)) {
                    if (nevasca == 0 && seca == 0) {
                        item_vegetacao.at("Vegetacao", "MATA");
                    } else if (nevasca == 0 && seca > 0) {
                        item_vegetacao.at("Vegetacao", "FLORESTA TROPICAL");
                    } else if (nevasca > 0 && seca == 0) {
                        item_vegetacao.at("Vegetacao", "FLORESTA TEMPERADA");
                    } else {
                        item_vegetacao.at("Vegetacao", "FLORESTA");
                    }
                }

                if (seca >= (nevasca + pluviosidade)) {
                    item_vegetacao.at("Vegetacao", "SAVANA");
                }

            }


        }

        ENTT.SUBSTITUIR(mapa_sensores_vegetacao, "0", "");
        //   ENTT.EXIBIR_TABELA(mapa_sensores_vegetacao);

        fmt.print("Desconhecido :: {}", ENTT.COLETAR(mapa_sensores_vegetacao, "Vegetacao", "").getQuantidade());

        //  ENTT.EXIBIR_TABELA(ENTT.DISPERSAO(mapa_sensores_vegetacao,"Vegetacao"));
    }


    public static void MAPEAR_CONDICOES_CLIMATICAS() {

        AtzumCreatorInfo.iniciar("AnalisadorClimatico.MAPEAR_CONDICOES_CLIMATICAS");

        Lista<Entidade> mapa_sensores = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v3.entts"));
        //  Lista<Entidade> mapa_sensores = ENTT.ABRIR_COM_LIMITE(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v3.entts"), 30);

        ENTT.AT_ALTERAR_NOME(mapa_sensores, "Estacao.Tipo", "Hiperestacao");

        ENTT.EXIBIR_TABELA(mapa_sensores);


        ENTT.EXIBIR_TABELA(FATORES_CLIMATICOS_INVESTIGAR(mapa_sensores));

        if (ENTT.CONTAGEM(mapa_sensores) > 0) {
            //    return;
        }

        Lista<Entidade> vegetacao = ENTT.CRIAR_LISTA();
        for (Entidade sensor : mapa_sensores) {
            Entidade e_sensor = ENTT.CRIAR_EM(vegetacao);
            e_sensor.at("Sensor", sensor.at("Sensor"));
            e_sensor.at("Hiperestacao", sensor.at("Hiperestacao"));


            e_sensor.at("Nevasca", FATOR_CLIMATICO_CONTAGEM(sensor, "NEVE") + FATOR_CLIMATICO_CONTAGEM(sensor, "TEMPESTADE_NEVE"));
            e_sensor.at("Chuva", FATOR_CLIMATICO_CONTAGEM(sensor, "CHUVA") + FATOR_CLIMATICO_CONTAGEM(sensor, "TEMPESTADE_CHUVA"));
            e_sensor.at("Secura", FATOR_CLIMATICO_CONTAGEM(sensor, "SECA") + FATOR_CLIMATICO_CONTAGEM(sensor, "SECA_EXTREMA") + FATOR_CLIMATICO_CONTAGEM(sensor, "ONDA_DE_CALOR"));

        }

        ENTT.EXIBIR_TABELA(vegetacao);

        ENTT.GUARDAR(vegetacao, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v5.entts"));

        AtzumCreatorInfo.terminar("AnalisadorClimatico.MAPEAR_CONDICOES_CLIMATICAS");

    }

    public static Lista<Entidade> FATORES_CLIMATICOS_INVESTIGAR(Lista<Entidade> sensores) {

        Unico<String> fatores_climaticos = new Unico<String>(Strings.IGUALAVEL());

        for (Entidade sensor : sensores) {
            for (int i = 1; i <= 500; i++) {
                fatores_climaticos.item(sensor.at("FC" + i));
            }
        }

        Lista<Entidade> entts = ENTT.CRIAR_LISTA();
        for (String item : fatores_climaticos.toLista()) {
            Entidade novo = ENTT.CRIAR_EM(entts);
            novo.at("FatorClimatico", item);
        }

        return entts;

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


    public static void OBSERVAR_DETALHES() {

        //  Lista<Entidade> mInformacoesDasCidades = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_cidades.entts"));
        Lista<Entidade> mInformacoesDasCidades = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));


        Lista<Entidade> cidade_mais_secas = ENTT.CRIAR_LISTA();

        for (Entidade cidade : mInformacoesDasCidades) {

            int umidade_baixa = 0;
            int temperatura_alta = 0;

            double tMin = cidade.atDouble("T1");
            double tMax = cidade.atDouble("T1");
            double umidade_geral = cidade.atDouble("U1");

            double uMin = cidade.atDouble("U1");
            double uMax = cidade.atDouble("U1");

            for (int superarko = 1; superarko <= 500; superarko++) {
                double umidade = cidade.atDouble("U" + superarko);
                double temperatura = cidade.atDouble("T" + superarko);

                if (temperatura < tMin) {
                    tMin = temperatura;
                }
                if (temperatura > tMax) {
                    tMax = temperatura;
                }

                if (umidade < uMin) {
                    uMin = umidade;
                }
                if (umidade > uMax) {
                    uMax = umidade;
                }

                if (umidade <= 25) {
                    umidade_baixa += 1;
                }
                if (temperatura > 30) {
                    temperatura_alta += 1;
                }


            }

            if (umidade_baixa == 500 && temperatura_alta == 500) {
                Entidade e_cidade = ENTT.CRIAR_EM(cidade_mais_secas);
                e_cidade.at("Cidade", cidade.at("Cidade"));
                e_cidade.atDouble("TMin", tMin);
                e_cidade.atDouble("TMax", tMax);
                e_cidade.atDouble("UMin", uMin);
                e_cidade.atDouble("UMax", uMax);
                e_cidade.atDouble("Umidade", umidade_geral);
                e_cidade.at("Ranking", fmt.f2(((tMin + tMax) - (umidade_geral * 3))));
            }
        }


        Lista<Entidade> cidade_mais_frias = ENTT.CRIAR_LISTA();

        for (Entidade cidade : mInformacoesDasCidades) {

            int umidade_alta = 0;
            int temperatura_baixa = 0;

            double tMin = cidade.atDouble("T1");
            double tMax = cidade.atDouble("T1");
            double umidade_geral = cidade.atDouble("U1");


            for (int superarko = 1; superarko <= 500; superarko++) {
                double umidade = cidade.atDouble("U" + superarko);
                double temperatura = cidade.atDouble("T" + superarko);

                if (temperatura < tMin) {
                    tMin = temperatura;
                }
                if (temperatura > tMax) {
                    tMax = temperatura;
                }

                if (umidade >= 75) {
                    umidade_alta += 1;
                }
                if (temperatura < 15) {
                    temperatura_baixa += 1;
                }
            }

            if (umidade_alta == 500 && temperatura_baixa == 500) {
                Entidade e_cidade = ENTT.CRIAR_EM(cidade_mais_frias);
                e_cidade.at("Cidade", cidade.at("Cidade"));
                e_cidade.atDouble("TMin", tMin);
                e_cidade.atDouble("TMax", tMax);
                e_cidade.atDouble("Umidade", umidade_geral);
                e_cidade.at("Ranking", fmt.f2((((tMin + tMax) * (-1)) + (umidade_geral * 3))));
            }
        }


        ENTT.ORDENAR_DOUBLE_DECRESCENTE(cidade_mais_secas, "Ranking");
        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_TOP10(cidade_mais_secas), "CIDADES +SECAS");

        ENTT.ORDENAR_DOUBLE_DECRESCENTE(cidade_mais_frias, "Ranking");
        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_TOP10(cidade_mais_frias), "CIDADES +FRIAS");


        Lista<Entidade> cidade_mais_chuvosas = ENTT.CRIAR_LISTA();

        for (Entidade cidade : mInformacoesDasCidades) {

            int chuva_contagem = 0;
            int neve_contagem = 0;
            int seca_contagem = 0;

            for (int superarko = 1; superarko <= 500; superarko++) {
                String fator_climatico = cidade.at("FC" + superarko);

                if (Strings.isIgual(fator_climatico, "CHUVA") || Strings.isIgual(fator_climatico, "TEMPESTADE_CHUVA") || Strings.isIgual(fator_climatico, "TEMPESTADE")) {
                    chuva_contagem += 1;
                } else if (Strings.isIgual(fator_climatico, "NEVE") || Strings.isIgual(fator_climatico, "TEMPESTADE_NEVE")) {
                    neve_contagem += 1;
                } else if (Strings.isIgual(fator_climatico, "SECA") || Strings.isIgual(fator_climatico, "SECA_EXTREMA") || Strings.isIgual(fator_climatico, "ONDA_DE_CALOR")) {
                    seca_contagem += 1;
                }

            }

            Entidade e_cidade = ENTT.CRIAR_EM(cidade_mais_chuvosas);
            e_cidade.at("Cidade", cidade.at("Cidade"));
            e_cidade.atInt("Chuva", chuva_contagem);
            e_cidade.atInt("Neve", neve_contagem);

        }

        ENTT.ORDENAR_INTEIRO_DECRESCENTE(cidade_mais_chuvosas, "Chuva");
        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_TOP10(cidade_mais_chuvosas), "CIDADES +CHUVOSAS");

        ENTT.ORDENAR_INTEIRO_DECRESCENTE(cidade_mais_chuvosas, "Neve");
        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_TOP10(cidade_mais_chuvosas), "CIDADES +NEVASCAS");
    }


    public static int ESTACAO_DURACAO_CONTAGEM(Entidade sensor, String proc) {

        int duracao = 0;

        for (int e = 1; e <= sensor.atInt("Estacoes"); e++) {
            if (Strings.GET_ATE(sensor.at("E" + e), " ").contentEquals(proc)) {
                duracao += Integer.parseInt(Strings.GET_REVERSO_ATE(sensor.at("E" + e), ":").replace(" ", ""));
            }
        }

        return duracao;
    }


    public static void ANALISE_TEMPORAL() {

        AtzumCreatorInfo.iniciar("AnalisadorClimatico.ANALISE_TEMPORAL");

        fmt.print(">> Obtendo dados dos sensores...");


        Lista<Entidade> modelos = ENTT.CRIAR_LISTA();

        Lista<Entidade> sensores_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_estacoes.entts"));

        fmt.print("Sensores : {}", ENTT.CONTAGEM(sensores_dados));

        Lista<Entidade> sensores_resumo = ENTT.CRIAR_LISTA();

        fmt.print(">> Organizando sensores...");
        for (Entidade sensor : sensores_dados) {

            Entidade e_sensor = ENTT.GET_SEMPRE(sensores_resumo, "Sensor", sensor.at("Sensor"));
            e_sensor.at("X", sensor.at("X"));
            e_sensor.at("Y", sensor.at("Y"));

            e_sensor.at("Estacao.Transicao", "");
            e_sensor.at("Estacao.Tipo", "");
            e_sensor.at("Estacao.Quantidade", "");
            e_sensor.at("Estacao.Unicas", "");


            int estacoes = sensor.atInt("Estacoes");


            Unico<String> estacoes_tipos = new Unico<String>(Strings.IGUALAVEL());
            Lista<String> estacoes_ciclo = new Lista<String>();

            for (int e = 1; e <= sensor.atInt("Estacoes"); e++) {
                e_sensor.at("E" + e, sensor.at("E" + e));
                estacoes_tipos.item(Strings.GET_ATE(sensor.at("E" + e), " "));
                estacoes_ciclo.adicionar(Strings.GET_ATE(sensor.at("E" + e), " "));
            }

            e_sensor.at("Estacao.Quantidade", estacoes);
            e_sensor.at("Estacao.Unicas", estacoes_tipos.getQuantidade());

            boolean modelo_existe = false;
            for (Entidade modelo : modelos) {
                if (modelo.atInt("Estacao.Quantidade") == estacoes) {

                    int iguais = 0;

                    for (int e = 1; e <= sensor.atInt("Estacoes"); e++) {
                        e_sensor.at("E" + e, sensor.at("E" + e));
                        String estacao_nome = Strings.GET_ATE(sensor.at("E" + e), " ");

                        if (modelo.at("E" + e).contentEquals(estacao_nome)) {
                            iguais += 1;
                        }

                    }

                    if (iguais == estacoes) {
                        modelo_existe = true;
                        break;
                    }
                }
            }

            if (!modelo_existe) {

                Entidade e_modelo = ENTT.CRIAR_EM(modelos);
                e_modelo.atInt("ModeloID", ENTT.CONTAGEM(modelos));
                e_modelo.at("Status", "");
                e_modelo.atInt("Estacao.Quantidade", sensor.atInt("Estacoes"));
                for (int e = 1; e <= sensor.atInt("Estacoes"); e++) {
                    String estacao_nome = Strings.GET_ATE(sensor.at("E" + e), " ");
                    e_modelo.at("E" + e, estacao_nome);
                }

            }


        }


        ENTT.EXIBIR_TABELA(ENTT.GET_AMOSTRA_PEQUENA(sensores_resumo));
        ENTT.EXIBIR_TABELA(modelos);


        fmt.print("Estações Maximo :: {}", ENTT.GET_INTEIRO_MAIOR(sensores_resumo, "Estacao.Quantidade"));
        fmt.print("Estações Unicas Maximo :: {}", ENTT.GET_INTEIRO_MAIOR(sensores_resumo, "Estacao.Unicas"));


        for (Entidade modelo_v1 : modelos) {

            if (modelo_v1.at("Status").isEmpty()) {
                modelo_v1.at("Status", "Original");

                int modelo_v1_quantidade = modelo_v1.atInt("Estacao.Quantidade");

                for (Entidade modelo_v2 : modelos) {
                    int modelo_v2_quantidade = modelo_v2.atInt("Estacao.Quantidade");
                    if (modelo_v1_quantidade == modelo_v2_quantidade) {
                        if (modelo_v2.at("Status").isEmpty()) {

                            Lista<String> modelo_v1_valores = new Lista<String>();
                            Lista<String> modelo_v2_valores = new Lista<String>();

                            for (int e = 1; e <= modelo_v1_quantidade; e++) {
                                modelo_v1_valores.adicionar(modelo_v1.at("E" + e));
                                modelo_v2_valores.adicionar(modelo_v2.at("E" + e));
                            }

                            if (modelo_v1_quantidade > 1) {

                                if (inferencia(modelo_v1_valores, modelo_v2_valores, modelo_v1_quantidade)) {
                                    modelo_v2.at("Status", "Duplicado");
                                }

                            }

                        }
                    }
                }

            }


        }

        ENTT.EXIBIR_TABELA(modelos);


        fmt.print("Modelos   :: {}", ENTT.CONTAGEM(modelos));
        fmt.print("Original  :: {}", ENTT.CONTAGEM(modelos, "Status", "Original"));
        fmt.print("Duplicado :: {}", ENTT.CONTAGEM(modelos, "Status", "Duplicado"));

        Lista<Entidade> modelos_validos = ENTT.COLETAR(modelos, "Status", "Original");
        ENTT.EXIBIR_TABELA(modelos_validos);

        AtzumCreatorInfo.terminar("AnalisadorClimatico.ANALISE_TEMPORAL");
        AtzumCreatorInfo.exibir_item("AnalisadorClimatico.ANALISE_TEMPORAL");


    }


    public static void INIT() {

        AtzumCreatorInfo.iniciar("AnalisadorClimatico.INIT");

        fmt.print(">> Obtendo dados dos sensores...");

        Lista<Entidade> sensores_dados = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));

        fmt.print("Sensores : {}", ENTT.CONTAGEM(sensores_dados));


        // PARTE 1
        Lista<Entidade> sensores_resumo = ENTT.CRIAR_LISTA();
        Lista<Entidade> sensores_hiperestacoes = ENTT.CRIAR_LISTA();

        fmt.print(">> Organizando sensores...");

        for (Entidade sensor : sensores_dados) {

            for (int i = 1; i <= 500; i++) {
                sensor.at("TS" + i, TEMPERATURA_CLASSIFICAR(sensor.atDouble("T" + i)));
            }


            Entidade e_resumo = ENTT.CRIAR_EM(sensores_resumo);
            e_resumo.at("Sensor", sensor.at("Sensor"));
            e_resumo.at("Hiperestacao", "");
            e_resumo.at("Periodos.Tipos", 0);
            e_resumo.at("Estacoes.Quantidade", 0);

            Entidade e_hiperestacao = ENTT.CRIAR_EM(sensores_hiperestacoes);
            e_hiperestacao.at("Sensor", sensor.at("Sensor"));
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

        }


        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_AMOSTRA_PEQUENA(sensores_hiperestacoes), "HIPERESTACOES - ANALISADA");


        ENTT.GUARDAR(sensores_hiperestacoes, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v2.entts"));


        parte_2(sensores_hiperestacoes);
        parte_3();
        parte_4();
        parte_5();

        AtzumCreatorInfo.terminar("AnalisadorClimatico.INIT");

    }


    public static void parte_2(Lista<Entidade> sensores_hiperestacoes) {


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


    public static void parte_3() {

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


    public static void parte_4() {

        Lista<Entidade> sensores_hiperestacoes_original = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores.entts"));

        Lista<Entidade> sensores_hiperestacoes = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v2.entts"));

        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_AMOSTRA_PEQUENA(sensores_hiperestacoes), "HIPERESTACOES ENTRADA");

        for (Entidade sensor : sensores_hiperestacoes) {

            int estacoes_quantidade = sensor.atInt("Estacoes.Quantidade");
            Lista<String> sensor_estacoes = new Lista<String>();

            for (int e = 1; e <= estacoes_quantidade; e++) {
                sensor_estacoes.adicionar(sensor.at("E" + e));
            }

            sensor.at("Hiperestacao", CLASSIFICAR_HIPERESTACAO(sensor_estacoes));

            Entidade original = ENTT.GET_SEMPRE(sensores_hiperestacoes_original, "Sensor", sensor.at("Sensor"));
            original.at("Hiperestacao", sensor.at("Hiperestacao"));


        }

        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.GET_AMOSTRA_PEQUENA(sensores_hiperestacoes), "HIPERESTACOES PROCESSADAS V6");

        ENTT.GUARDAR(sensores_hiperestacoes, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v6.entts"));
        ENTT.GUARDAR(sensores_hiperestacoes_original, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v7.entts"));


        ENTT.EXIBIR_TABELA_COM_TITULO(ENTT.DISPERSAO(sensores_hiperestacoes, "Hiperestacao"), "HIPERESTACOES PROCESSADAS V7");


    }

    public static void parte_5() {

        //MAPEAR_CONDICOES_CLIMATICAS

        AtzumCreatorInfo.iniciar("AnalisadorClimatico.MAPEAR_CONDICOES_CLIMATICAS");

        Lista<Entidade> mapa_sensores = ENTT.ABRIR(AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v7.entts"));

        ENTT.EXIBIR_TABELA(mapa_sensores);


        ENTT.EXIBIR_TABELA_COM_TITULO(FATORES_CLIMATICOS_INVESTIGAR(mapa_sensores), "FATORES CLIMATICOS - INVESTIGANDO");

        if (ENTT.CONTAGEM(mapa_sensores) > 0) {
            //    return;
        }

        Lista<Entidade> vegetacao = ENTT.CRIAR_LISTA();
        for (Entidade sensor : mapa_sensores) {
            Entidade e_sensor = ENTT.CRIAR_EM(vegetacao);
            e_sensor.at("Sensor", sensor.at("Sensor"));
            e_sensor.at("Hiperestacao", sensor.at("Hiperestacao"));


            e_sensor.at("Nevasca", FATOR_CLIMATICO_CONTAGEM(sensor, "NEVE") + FATOR_CLIMATICO_CONTAGEM(sensor, "TEMPESTADE_NEVE"));
            e_sensor.at("Chuva", FATOR_CLIMATICO_CONTAGEM(sensor, "CHUVA") + FATOR_CLIMATICO_CONTAGEM(sensor, "TEMPESTADE_CHUVA"));
            e_sensor.at("Secura", FATOR_CLIMATICO_CONTAGEM(sensor, "SECA") + FATOR_CLIMATICO_CONTAGEM(sensor, "SECA_EXTREMA") + FATOR_CLIMATICO_CONTAGEM(sensor, "ONDA_DE_CALOR"));

        }

        ENTT.EXIBIR_TABELA_COM_TITULO(vegetacao, "VEGETACAO");

        ENTT.GUARDAR(vegetacao, AtzumCreator.LOCAL_GET_ARQUIVO("build/tronarko/tronarko_sensores_v8.entts"));

        AtzumCreatorInfo.terminar("AnalisadorClimatico.MAPEAR_CONDICOES_CLIMATICAS");

    }

}
